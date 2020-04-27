package model;

import java.util.ArrayList;
import java.util.Random;

/**
 *
 * @author bratizgut
 */
public class Ball implements Observable{
    
    private int x;
    private int y;
    private int dx;
    private int dy;
    
    private final int rad;
    private final int speed;
    
    private final int WIDTH;
    private final int HEIGHT;
    
    private ArrayList<Observer> listeners;
    
    private final Random gen = new Random(System.currentTimeMillis());

    public Ball(int width, int height, int speed, int rad) {
        WIDTH = width;
        HEIGHT = height;
        
        this.speed = speed;
        this.rad = rad;
        
        dx = this.speed;
        dy = this.speed;
        x = width / 2;
        y = height / 2;
        
        listeners = new ArrayList<>();
    }

    public int getX() {
        return x;
    }

    public void setX(int x) {
        this.x = x;
    }

    public int getY() {
        return y;
    }

    public void setY(int y) {
        this.y = y;
    }

    public int getDx() {
        return dx;
    }
    
    public void setDx(int dx) {
        this.dx = Integer.signum(dx)*speed;
    }

    public int getDy() {
        return dy;
    }

    public void setDy(int paneDy) {
        if(paneDy!= 0){
            if (Integer.signum(paneDy) == Integer.signum(dy)) {
                dy += Integer.signum(dy);
            } else {
                dy -= Integer.signum(dy);
            }
            if(dy == 0){
                dy = Integer.signum(paneDy);
            }
        }
    }

    public int getRad() {
        return rad;
    }
    
    public void respawn(){
        x = WIDTH / 2;
        y = HEIGHT / 2;
        dx = - Integer.signum(dx) * speed;
        dy = - Integer.signum(dy) * speed;
        if(dy == 0){
            dy = speed;
        }
    }
    
    public void refresh(){
        if(dx > 0){
            if((x + rad) < WIDTH){
                x += dx;
            } else {
                x = WIDTH - rad;
                dx = -dx;
            }
        } else {
            if(x > 0){
                x += dx;
            } else {
                x = 0;
                dx = -dx;
            }
        }
        if(dy > 0){
            if((y + rad) < HEIGHT){
                y += dy;
            } else {
                y = HEIGHT - rad;
                dy = -dy;
            }
        } else {
            if(y > 0){
                y += dy;
            } else {
                y = 0;
                dy = -dy;
            }
        }
        update();
    }

    @Override
    public void addObserver(Observer observer) { 
        listeners = new ArrayList<>();
        listeners.add(observer); 
    }

    @Override
    public void deleteObserver(Observer observer) {
        listeners.remove(observer);
    }

    @Override
    public void update() {
        for(Observer i : listeners){
            i.handleEvent(this);
        }
    }
    
}
