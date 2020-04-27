package controller;

/**
 *
 * @author bratizgut
 */

import java.awt.event.KeyEvent;
import model.Model;
import model.Direction;

public class Controller {

    private final Model model;
    
    public Controller(Model objModel) {
        model = objModel;
    }
    
    public void control(KeyEvent pressed, KeyEvent released){
        if (pressed != null) {
            int key = pressed.getKeyCode();
            if (key == KeyEvent.VK_W) {
                model.movePlayer(Direction.UP, Direction.NONE);
            }
            if (key == KeyEvent.VK_S) {
                model.movePlayer(Direction.DOWN, Direction.NONE);
            }
        }
        if (released != null) {
            int key = released.getKeyCode();
            if (key == KeyEvent.VK_W) {
                model.movePlayer(Direction.NONE, Direction.UP);
            }
            if (key == KeyEvent.VK_S) {
                model.movePlayer(Direction.NONE, Direction.DOWN);
            }
        }
    }
    
}