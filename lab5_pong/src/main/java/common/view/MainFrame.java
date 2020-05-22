package client.view;

import client.Observable;
import client.Observer;
import client.controller.Controller;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyAdapter;
import java.awt.event.KeyEvent;
import java.util.Objects;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import server.message.InitMessage;

/**
 *
 * @author bratizgut
 */
public class MainFrame extends JFrame {

    private final int WINDOW_WIDTH;
    private final int WINDOW_HEIGHT;
    private final int BORDER_SIZE = 10;

    private final GameFrame gameFrame;

    private final Controller controller;

    private final JLabel scoreLabel;
    private final JLabel gameOverLabel;
    private final JLabel connectionStoppedLabel;

    private final JLabel p1ReadyLabel;
    private final JLabel p2ReadyLabel;

    private final String ballImgName;
    private final int ballRad;

    private final String paneImgName;
    private final int paneLength;
    private final int paneWidth;

    public MainFrame(InitMessage message, String ballImg, String paneImg, Controller control) {

        WINDOW_WIDTH = message.width + 2 * BORDER_SIZE;
        WINDOW_HEIGHT = message.height + 2 * BORDER_SIZE;

        ballImgName = ballImg;
        this.ballRad = message.ballRad;

        paneImgName = paneImg;
        this.paneLength = message.paneLength;
        this.paneWidth = message.paneWidth;

        controller = control;

        super.setTitle("Game");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        super.setUndecorated(true);
        super.setResizable(false);

        super.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                controller.passKeyToServer(e, null);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.passKeyToServer(null, e);
                if (e.getKeyCode() == KeyEvent.VK_ESCAPE) {
                    control.closeConnection();
                    System.exit(0);
                }
            }

        });

        gameFrame = new GameFrame();
        gameFrame.setLayout(new FlowLayout());

        Font font = new Font(null, Font.BOLD, 20);

        scoreLabel = new JLabel();
        scoreLabel.setFont(font);
        scoreLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, 20));
        scoreLabel.setHorizontalAlignment(JLabel.CENTER);
        scoreLabel.setVerticalAlignment(JLabel.TOP);

        gameOverLabel = new JLabel("<html>Game over!<br>Press space to confirm you are ready<br>Press ESC to exit.</html>");
        gameOverLabel.setFont(font);
        gameOverLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);

        connectionStoppedLabel = new JLabel("<html>Connection stopped!<br>Press ESC to exit.</html>");
        connectionStoppedLabel.setFont(font);
        connectionStoppedLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        connectionStoppedLabel.setHorizontalAlignment(JLabel.CENTER);
        connectionStoppedLabel.setVerticalAlignment(JLabel.CENTER);

        p1ReadyLabel = new JLabel("<html>ready</html>");
        p1ReadyLabel.setFont(font);
        p1ReadyLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        p1ReadyLabel.setHorizontalAlignment(JLabel.LEFT);
        p1ReadyLabel.setVerticalAlignment(JLabel.CENTER);

        p2ReadyLabel = new JLabel("<html>ready</html>");
        p2ReadyLabel.setFont(font);
        p2ReadyLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        p2ReadyLabel.setHorizontalAlignment(JLabel.RIGHT);
        p2ReadyLabel.setVerticalAlignment(JLabel.CENTER);

        gameFrame.add(scoreLabel);
        gameFrame.add(gameOverLabel);
        gameFrame.add(connectionStoppedLabel);
        gameFrame.add(p1ReadyLabel);
        gameFrame.add(p2ReadyLabel);

        super.add(gameFrame);
        
        gameFrame.setConnetionFlag(message.connectionFlag);

        super.setVisible(true);

    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private class GameFrame extends JPanel implements ActionListener, Observer {

        Timer timer = new Timer(1, this);

        private Image ballImg;
        private Image paneImg;

        private int ballX;
        private int ballY;

        private int player1X;
        private int player1Y;

        private int player2X;
        private int player2Y;

        private int Score1 = 0;
        private int Score2 = 0;

        private boolean gameEnd = false;
        private boolean connetionFlag = true;

        private boolean p1Ready = false;
        private boolean p2Ready = false;

        public GameFrame() {
            try {
                ballImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/" + ballImgName))).getImage();
                paneImg = new ImageIcon(Objects.requireNonNull(this.getClass().getResource("/" + paneImgName))).getImage();
            } catch (NullPointerException ex) {
                Logger.getLogger(MainFrame.class.getName()).log(Level.SEVERE, null, ex);
                System.exit(2);
            }
            timer.start();
        }

        @Override
        public void paint(Graphics g) {
            if (connetionFlag) {
                if (!gameEnd) {
                    g.setColor(Color.CYAN);
                    g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                    g.drawImage(ballImg, ballX + BORDER_SIZE, ballY + BORDER_SIZE, ballRad, ballRad, null);
                    g.drawImage(paneImg, player1X + BORDER_SIZE, player1Y + BORDER_SIZE, paneWidth, paneLength, null);
                    g.drawImage(paneImg, player2X + BORDER_SIZE, player2Y + BORDER_SIZE, paneWidth, paneLength, null);
                    scoreLabel.setText(Integer.toString(Score1) + " " + Integer.toString(Score2));
                    scoreLabel.paint(g);
                } else {
                    g.setColor(Color.CYAN);
                    g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                    gameOverLabel.paint(g);
                    if (p1Ready) {
                        p1ReadyLabel.paint(g);
                    }
                    if (p2Ready) {
                        p2ReadyLabel.paint(g);
                    }
                }
            } else {
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                connectionStoppedLabel.paint(g);
            }
        }

        public void setConnetionFlag(boolean connetionFlag) {
            this.connetionFlag = connetionFlag;
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void handleEvent(Observable o) {
            Controller obj = (Controller) o;
            player1X = obj.getPlayer1X();
            player1Y = obj.getPlayer1Y();
            player2X = obj.getPlayer2X();
            player2Y = obj.getPlayer2Y();
            ballX = obj.getBallX();
            ballY = obj.getBallY();
            Score1 = obj.getScore1();
            Score2 = obj.getScore2();
            gameEnd = obj.isGameEnd();
            connetionFlag = obj.isConnectionFlag();
            p1Ready = obj.isP1Ready();
            p2Ready = obj.isP2Ready();
        }
    }

}
