package common.model;

/**
 *
 * @author bratizgut
 */
public class Collider {
    
    static int WIDTH;
    static int paneWidth;
    
    static void setWIDTH(int width){
        WIDTH = width;
    }

    static void setPaneWidth(int paneWidth) {
        Collider.paneWidth = paneWidth;
    }
    
    static int collisionCheck(Player player, Player enemy, Ball ball){
        
        if(ball.getX() <= paneWidth){
            if(((player.getY() - ball.getRad()) < ball.getY()) && ((player.getY() + player.getPaneLength()) > ball.getY())){
                if(ball.getDx() < 0){
                    ball.setDx(-ball.getDx());
                    ball.setDy(player.getDy());
                }
            } else if (ball.getX() <= 0){
                ball.respawn();
                ball.setMove(false);
                return -1;
            }
        }
        
        if((ball.getX() + ball.getRad()) >= (WIDTH - paneWidth)){         
            if(((enemy.getY() - ball.getRad()) < ball.getY()) && ((enemy.getY() + enemy.getPaneLength()) > (ball.getY()))){
                if(ball.getDx() > 0){
                    ball.setDx(-ball.getDx());
                    ball.setDy(enemy.getDy());
                }
            } else if ((ball.getX() + ball.getRad()) >= WIDTH) {
                ball.respawn();
                ball.setMove(false);
                return 1;
            }
        }

        return 0;
    }
}
