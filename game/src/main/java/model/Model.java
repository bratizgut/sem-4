package model;

import java.util.ArrayList;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bratizgut
 */
public class Model implements Observable {

    private final Ball ball;
    private final Player player;
    private final Enemy enemy;

    private int Score1;
    private int Score2;

    private final int width;
    private final int height;

    private ArrayList<Observer> listeners;

    private boolean gameEnd = false;

    public Model(int width, int height, int ballSpeed, int ballRad, int playerSpeed, int enemySpeed, int paneLength, int paneWidth) {

        this.width = width;
        this.height = height;

        ball = new Ball(width, height, ballSpeed, ballRad);
        player = new Player(width, height, playerSpeed, paneLength, paneWidth);
        enemy = new Enemy(width, height, enemySpeed, paneLength, paneWidth);

        ball.addObserver(enemy);

        this.modelInit();

        Collider.setWIDTH(width);
        Collider.setPaneWidth(paneWidth);

    }

    private void modelInit() {
        Score1 = 0;
        Score2 = 0;
        ball.respawn();
        player.respawn();
        enemy.respawn();
    }

    private class GameThread extends Thread {

        public GameThread() {
        }

        @Override
        public void run() {
            while (true) {
                ball.refresh();
                enemy.refresh();
                player.refresh();
                int res = 0;
                if ((ball.getX() < 100) || (ball.getX() > (width - 100))) {
                    res = Collider.collisionCheck(player, enemy, ball);
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
                    Thread.sleep(10);
                } catch (InterruptedException ex) {
                    Logger.getLogger(Model.class.getName()).log(Level.SEVERE, null, ex);
                }
            }
        }

    }

    @Override
    public void addObserver(Observer observer) {

        if (listeners == null) {
            listeners = new ArrayList<>();
        }
        listeners.add(observer);

    }

    @Override
    public void deleteObserver(Observer observer) {
        listeners.remove(observer);
    }

    public int getBallX() {
        return ball.getX();
    }

    public int getBallY() {
        return ball.getY();
    }

    public int getPlayerX() {
        return player.getX();
    }

    public int getPlayerY() {
        return player.getY();
    }

    public int getEnemyX() {
        return enemy.getX();
    }

    public int getEnemyY() {
        return enemy.getY();
    }

    public int getScore1() {
        return Score1;
    }

    public int getScore2() {
        return Score2;
    }

    public boolean isGameEnd() {
        return gameEnd;
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

    public void movePlayer(int pressed, int released) {
        player.setMove(pressed, released);
    }

    public synchronized void start() {
        GameThread gameThread = new GameThread();
        this.modelInit();
        this.gameEnd = false;
        gameThread.start();
    }
}
