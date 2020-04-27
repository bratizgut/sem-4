package model;

/**
 *
 * @author bratizgut
 */
public class Collider {
    
    static   int WIDTH;
    
    static void setWIDTH(int width){
        WIDTH = width;
    }
    
    static int collisionCheck(Player player, Enemy enemy, Ball ball){
        
        if(ball.getX() <= 0){
            if(((player.getY() - ball.getRad()) < ball.getY()) && ((player.getY() + player.getPaneLength()) > ball.getY())){
                if(ball.getDx() < 0){
                    ball.setDx(-ball.getDx());
                    ball.setDy(player.getDy());
                }
            } else {
                ball.respawn();
                return -1;
            }
        }
        
        if((ball.getX() + ball.getRad()) >= WIDTH){         
            if(((enemy.getY() - ball.getRad()) < ball.getY()) && ((enemy.getY() + enemy.getPaneLength()) > (ball.getY()))){
                if(ball.getDx() > 0){
                    ball.setDx(-ball.getDx());
                    ball.setDy(enemy.getDy());
                }
            } else {
                ball.respawn();
                return 1;
            }
        }

        return 0;
    }
}
