package model;

/**
 *
 * @author bratizgut
 */

public class Player {

    private final int x;
    private int y;
    private int dy;

    private final int speed;
    private final int paneLength;
    private final int paneWidth;

    private boolean UP;
    private boolean DOWN;

    private final int WIDTH;
    private final int HEIGHT;

    public Player(int width, int height, int speed, int paneLength, int paneWidth) {
        this.WIDTH = width;
        this.HEIGHT = height;

        dy = 0;
        this.speed = speed;
        this.paneLength = paneLength;
        this.paneWidth = paneWidth;

        x = 0;
        
        y = (HEIGHT - paneLength) / 2;

        UP = false;
        DOWN = false;

    }
    
    public  void respawn() {
        y = (HEIGHT - paneLength) / 2;

        UP = false;
        DOWN = false;
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

    public void setMove(int pressed, int released) {

        if(pressed == Direction.UP){
            UP = true;
        } else if(pressed == Direction.DOWN){
            DOWN = true;
        }
        
        if(released  == Direction.UP){
            UP = false;
        } else if(released == Direction.DOWN){
            DOWN = false;
        }
        
    }

    public void refresh() {
        if (UP && !DOWN) {
            dy = -speed;
        }
        if (!UP && DOWN) {
            dy = speed;
        }
        if (!UP && !DOWN) {
            dy = 0;
        }

        y += dy;

        if (y < 0) {
            y = 0;
        }
        if (y > (HEIGHT - paneLength)) {
            y = HEIGHT - paneLength;
        }
    }

}
