package server.model;

import java.util.ArrayList;
import server.message.ModelState;

/**
 *
 * @author bratizgut
 */
public class Model implements Observable {

    private final Ball ball;
    private final Player player1;
    private final Player player2;

    private int Score1;
    private int Score2;

    private final int width;
    private final int height;

    private boolean gameEnd = false;
    private boolean connectionFlag = true;

    private boolean p1Ready = true;
    private boolean p2Ready = true;

    private GameThread gameThread;

    private final ArrayList<Observer> listeners;

    public Model(int width, int height, int ballSpeed, int ballRad, int playerSpeed, int enemySpeed, int paneLength, int paneWidth) {

        this.width = width;
        this.height = height;

        ball = new Ball(width, height, ballSpeed, ballRad);
        player1 = new Player(width, height, playerSpeed, paneLength, paneWidth, 1);
        player2 = new Player(width, height, playerSpeed, paneLength, paneWidth, 2);

        this.modelInit();

        listeners = new ArrayList<>();

        Collider.setWIDTH(width);
        Collider.setPaneWidth(paneWidth);

    }

    private void modelInit() {
        Score1 = 0;
        Score2 = 0;
        ball.respawn();
        player1.respawn();
        player2.respawn();
    }

    @Override
    public void addObserver(Observer observer) {
        listeners.add(observer);
    }

    @Override
    public void deleteObserver(Observer observer) {
        listeners.remove(observer);
    }

    @Override
    public void update() {
        if (listeners != null) {
            for (Observer i : listeners) {
                if (i != null) {
                    i.handleEvent(this);
                }
            }
        }
    }

    private class GameThread extends Thread {

        @Override
        public void run() {
            while (!isInterrupted()) {
                ball.refresh();
                player2.refresh();
                player1.refresh();
                int res = 0;
                if ((ball.getX() < 100) || (ball.getX() > (width - 100))) {
                    res = Collider.collisionCheck(player1, player2, ball);
                }
                if (res == 1) {
                    Score1 += 1;
                }
                if (res == -1) {
                    Score2 += 1;
                }

                if (Score1 == 10 || Score2 == 10) {
                    gameEnd = true;
                }

                update();

                if (Score1 == 10 || Score2 == 10) {
                    break;
                }

                try {
                    Thread.sleep(20);
                } catch (InterruptedException ex) {
                    break;
                }
            }
        }

    }

    public boolean isGameEnd() {
        return gameEnd;
    }
    
    public ModelState getModelState(){
        return new ModelState(player1.getX(), player1.getY(), player2.getX(), player2.getY(), ball.getX(), ball.getY(), Score1, Score2, gameEnd, connectionFlag, p1Ready, p2Ready);
    }

    public void setPlayerReady(int num) {
        if (num == 1) {
            p1Ready = true;
        } else {
            p2Ready = true;
        }
    }

    public void movePlayer(int pressed, int released, int playerNum) {
        if (playerNum == 1) {
            player1.setMove(pressed, released);
        }
        if (playerNum == 2) {
            player2.setMove(pressed, released);
        }
    }

    public synchronized void start() {
        if (p1Ready && p2Ready) {
            p1Ready = false;
            p2Ready = false;
            gameThread = new GameThread();
            this.modelInit();
            this.gameEnd = false;
            gameThread.start();
        }
    }

    public void end() {
        connectionFlag = false;
        update();
        gameThread.interrupt();
    }
    
}
