package view;

import controller.Controller;
import java.awt.Color;
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
    
    private final GameFrame gameFrame;
    
    private final Controller controller;
    
    private final JLabel label;
    private final JLabel gameOverLabel;
    
    private final String ballImgName;
    private final String paneImgName;
    
    public MainFrame(int width, int height, String ballImg, String paneImg, Controller control) {
        
        WINDOW_WIDTH = width + 5;
        WINDOW_HEIGHT = height + 30;
        
        ballImgName = ballImg;
        paneImgName = paneImg;
        
        controller = control;
        
        setTitle("Game");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(WINDOW_WIDTH, WINDOW_HEIGHT);
//        setUndecorated(true);
        setResizable(false);
        
        label = new JLabel();
        gameOverLabel = new JLabel();
        
        gameFrame = new GameFrame();
        add(gameFrame);
        this.addKeyListener(new KeyAdapter() {

            @Override
            public void keyPressed(KeyEvent e) {
                controller.control(e, null);
            }

            @Override
            public void keyReleased(KeyEvent e) {
                controller.control(null, e);
            }
            
        });
        
        gameFrame.add(label);
        gameFrame.add(gameOverLabel);
        Font font = new Font(null, Font.BOLD, 20);
        gameOverLabel.setFont(font);
        gameOverLabel.setText("Game Over!");  
        
        setVisible(true);
    }

    public GameFrame getGameFrame() {
        return gameFrame;
    }

    private class GameFrame extends JPanel implements ActionListener, Observer {
        
        Timer timer = new Timer(1, this);
        
        private final Image ballImg = new ImageIcon(ballImgName).getImage();
        private final Image paneImg = new ImageIcon(paneImgName).getImage();
        
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
                g.setColor(Color.WHITE);
                g.fillRect(0, 0, WINDOW_WIDTH, WINDOW_HEIGHT);
                g.drawImage(ballImg, ballX, ballY, null);
                g.drawImage(paneImg, playerX, playerY, null);
                g.drawImage(paneImg, enemyX, enemyY, null);
                label.setText(Integer.toString(Score1) + " " + Integer.toString(Score2));
                label.paint(g);
            } else {
                g.setColor(Color.WHITE);
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
