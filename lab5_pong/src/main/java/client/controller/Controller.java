package client.controller;

/**
 *
 * @author bratizgut
 */
import client.Observable;
import client.Observer;
import java.awt.event.KeyEvent;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.util.ArrayList;
import server.message.ModelState;

public class Controller extends Thread implements Observable {

    private ArrayList<Observer> listeners;

    private final PrintWriter pw;
    private final ObjectInputStream reader;

    private int player1X;
    private int player1Y;

    private int player2X;
    private int player2Y;

    private int ballX;
    private int ballY;

    private int Score1;
    private int Score2;

    private boolean gameEnd = false;
    private boolean connectionFlag = true;

    private boolean p1Ready;
    private boolean p2Ready;

    public Controller(PrintWriter pw, ObjectInputStream reader) {
        listeners = new ArrayList<>();
        this.pw = pw;
        this.reader = reader;
    }

    @Override
    public void run() {
        while (!isInterrupted()) {
            try {
                try {
                    ModelState message = (ModelState) reader.readObject();
                    player1X = message.player1X;
                    player1Y = message.player1Y;
                    player2X = message.player2X;
                    player2Y = message.player2Y;
                    ballX = message.ballX;
                    ballY = message.ballY;
                    Score1 = message.Score1;
                    Score2 = message.Score2;
                    gameEnd = message.gameEnd;
                    connectionFlag = message.connectionFlag;
                    p1Ready = message.P1Ready;
                    p2Ready = message.P2Ready;
                    if (!connectionFlag) {
                        interrupt();
                    }
                    update();
                } catch (ClassNotFoundException ex) {
                    interrupt();
                }
            } catch (IOException ex) {
                connectionFlag = false;
                update();
                interrupt();
            }
        }
    }

    public void passKeyToServer(KeyEvent pressed, KeyEvent released) {
        if (connectionFlag) {
            if (pressed != null) {
                int key = pressed.getKeyCode();
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    pw.println("w ");
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    pw.println("s ");
                }
                if (key == KeyEvent.VK_SPACE) {
                    if (gameEnd) {
                        pw.println("r");
                    }
                }
            }
            if (released != null) {
                int key = released.getKeyCode();
                if (key == KeyEvent.VK_W || key == KeyEvent.VK_UP) {
                    pw.println(" w");
                }
                if (key == KeyEvent.VK_S || key == KeyEvent.VK_DOWN) {
                    pw.println(" s");
                }
            }
        }
    }

    public void closeConnection() {
        pw.println("end");
    }

    public int getPlayer1X() {
        return player1X;
    }

    public int getPlayer1Y() {
        return player1Y;
    }

    public int getPlayer2X() {
        return player2X;
    }

    public int getPlayer2Y() {
        return player2Y;
    }

    public int getBallX() {
        return ballX;
    }

    public int getBallY() {
        return ballY;
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

    public boolean isConnectionFlag() {
        return connectionFlag;
    }

    public boolean isP1Ready() {
        return p1Ready;
    }

    public boolean isP2Ready() {
        return p2Ready;
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

}
