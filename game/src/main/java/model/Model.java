package model;

import java.awt.event.KeyEvent;
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
        
        Score1 = 0;
        Score2 = 0;
        
        Collider.setWIDTH(width);
        
    }

    @Override
    public void addObserver(Observer observer) {
        
        if(listeners == null)
            listeners = new ArrayList<>();
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
    
    public int getEnemyX(){
        return enemy.getX();
    }
    
    public int getEnemyY(){
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
    
    public void movePlayer(int pressed, int released){
        player.setMove(pressed, released);
    }
    
    public void start(){
        this.gameProcces();
    }
    
    private void gameProcces(){
        while (true) {            
            ball.refresh();
            enemy.refresh();
            player.refresh();
            int res = 0;
            if((ball.getX() < 100) || (ball.getX() > (width - 100)))
                res = Collider.collisionCheck(player, enemy, ball);
            if(res == 1)
                Score1 += 1;
            if(res == -1)
                Score2 += 1;
            
            if(Score1 == 10 || Score2 == 10){
                gameEnd = true;
            }

            update();
            
            if(Score1 == 10 || Score2 == 10){
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
