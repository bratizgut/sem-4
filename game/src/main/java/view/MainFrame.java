package view;

import controller.Controller;
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
import javax.swing.ImageIcon;
import javax.swing.JFrame;
import javax.swing.JPanel;
import javax.swing.JLabel;
import javax.swing.Timer;
import model.Model;
import model.Observable;

import model.Observer;
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
    
    private final String ballImgName;
    private final int ballRad;
    
    private final String paneImgName;
    private final int paneLength;
    private final int paneWidth;
    
    public MainFrame(int width, int height, String ballImg, int ballRad, String paneImg, int paneLength, int paneWidth, Controller control) {
        
        WINDOW_WIDTH = width + 2 *BORDER_SIZE;
        WINDOW_HEIGHT = height + 2 * BORDER_SIZE;
        
        ballImgName = ballImg;
        this.ballRad = ballRad;
        
        paneImgName = paneImg;
        this.paneLength = paneLength;
        this.paneWidth = paneWidth;
        
        controller = control;
        
        super.setTitle("Game");
        super.setDefaultCloseOperation(EXIT_ON_CLOSE);
        super.setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
        super.setUndecorated(true);
        super.setResizable(false);
        
        super.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                controller.control(e, null);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.control(null, e);
                if(e.getKeyCode() == KeyEvent.VK_ESCAPE){  
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
        
        gameOverLabel = new JLabel("<html>Game over!<br>Press space to restart<br>Press ESC to exit.</html>");
        gameOverLabel.setFont(font);
        gameOverLabel.setPreferredSize(new Dimension(WINDOW_WIDTH, WINDOW_HEIGHT));
        gameOverLabel.setHorizontalAlignment(JLabel.CENTER);
        gameOverLabel.setVerticalAlignment(JLabel.CENTER);
        
        gameFrame.add(scoreLabel);
        gameFrame.add(gameOverLabel);
        
        super.add(gameFrame);
        
        super.setVisible(true);
        
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private class GameFrame extends JPanel implements ActionListener, Observer {
        
        Timer timer = new Timer(1, this);
        
        private final Image ballImg = new ImageIcon(this.getClass().getResource("/" + ballImgName)).getImage();
        private final Image paneImg = new ImageIcon(this.getClass().getResource("/" + paneImgName)).getImage();
        
        private int ballX;
        private int ballY;
        
        private int playerX;
        private int playerY;
        
        private int enemyX;
        private int enemyY;
        
        private int Score1 = 0;
        private int Score2 = 0;
        
        private boolean gameEnd = false;

        public GameFrame() {
            timer.start();
        }
        
        @Override
        public void paint(Graphics g) {
            if(!gameEnd){
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                g.drawImage(ballImg, ballX + BORDER_SIZE, ballY + BORDER_SIZE, ballRad, ballRad, null);
                g.drawImage(paneImg, playerX + BORDER_SIZE, playerY + BORDER_SIZE, paneWidth, paneLength, null);
                g.drawImage(paneImg, enemyX + BORDER_SIZE, enemyY + BORDER_SIZE, paneWidth, paneLength, null);
                scoreLabel.setText(Integer.toString(Score1) + " " + Integer.toString(Score2));
                
                scoreLabel.paint(g);
            } else {
                g.setColor(Color.CYAN);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                gameOverLabel.paint(g);
            }
        }

        @Override
        public void actionPerformed(ActionEvent e) {
            repaint();
        }

        @Override
        public void handleEvent(Observable o) {
            Model obj = (Model)o;
            ballX = obj.getBallX();
            ballY = obj.getBallY();
            
            playerX = obj.getPlayerX();
            playerY = obj.getPlayerY();
            
            enemyX = obj.getEnemyX();
            enemyY = obj.getEnemyY();
            
            Score1 = obj.getScore1();
            Score2 = obj.getScore2();
            
            gameEnd = obj.isGameEnd();
        }
    } 
    
}
