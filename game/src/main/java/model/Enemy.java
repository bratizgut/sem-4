package model;

import java.awt.event.KeyEvent;

/**
 *
 * @author bratizgut
 */
public class Enemy implements Observer {
        
    private final int x;
    private int y;
    private int dy;

    private int ballY;
    
    private final int speed;
    private final int paneLength;
    private final int paneWidth;
    
    private final int WIDTH;
    private final int HEIGHT;

    public Enemy(int width, int height, int speed, int paneLength, int paneWidth) {
        this.WIDTH = width;
        this.HEIGHT = height;
        
        dy = 0;
        this.speed = speed;
        this.paneLength = paneLength;
        this.paneWidth = paneWidth;
        
        x = WIDTH - paneWidth;
        y = (HEIGHT - paneLength) / 2;                
    }

    public int getX() {
        return x;
    }

    public int getY() {
        return y;
    }

    public int getDy() {
        return dy;
    }

    public int getPaneLength() {
        return paneLength;
    }

    public int getPaneWidth() {
        return paneWidth;
    }
    
    public void refresh(){
        int midCoord = (2*y + paneLength) / 2;
        if(midCoord < ballY) {
            dy = speed;
        } else if (midCoord > ballY) {
            dy = -speed;
        }
        if((y >= 0) && (y + paneLength <= HEIGHT)){
            y += dy;
        } else {
            if(y < 0){
                y = 0;
            } else {
                y = HEIGHT - paneLength;
            }
        }
    }  

    @Override
    public void handleEvent(Observable o) {
        Ball obj = (Ball) o;
        this.ballY = obj.getY();
    }
    
}
