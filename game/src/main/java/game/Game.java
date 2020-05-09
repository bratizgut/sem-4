package game;

/**
 *
 * @author bratizgut
 */

import view.MainFrame;
import model.Model;
import controller.Controller;
import java.io.IOException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;

public class Game {
    
    private Model model;
    private Controller controller;
    private MainFrame view;

    public Game() {
        
        Properties properties = new Properties();
        
        try{

            properties.load(Objects.requireNonNull(this.getClass().getResourceAsStream("/gameprops.properties")));
            
            int width = Integer.parseInt(properties.getProperty("fieldWidth"));
            int height = Integer.parseInt(properties.getProperty("fieldHeight"));
            
            int ballSpeed = Integer.parseInt(properties.getProperty("ballSpeed"));
            int ballRad = Integer.parseInt(properties.getProperty("ballRad"));
            String ballImg = properties.getProperty("ballImg");
            
            int playerSpeed = Integer.parseInt(properties.getProperty("playerSpeed"));
            int enemySpeed = Integer.parseInt(properties.getProperty("enemySpeed"));
            
            int paneLength = Integer.parseInt(properties.getProperty("paneLength"));
            int paneWidth = Integer.parseInt(properties.getProperty("paneWidth"));
            String paneImg = properties.getProperty("paneImg");
            
            model = new Model(width, height, ballSpeed, ballRad, playerSpeed, enemySpeed, paneLength, paneWidth);
            controller = new Controller(model);
            view = new MainFrame(width, height, ballImg, ballRad, paneImg, paneLength, paneWidth, controller);
            
            model.addObserver(view.getGameFrame());
            
        } catch (IOException | NumberFormatException | NullPointerException ex) {
            Logger.getLogger(Game.class.getName()).log(Level.SEVERE, null, ex);
            System.exit(1);
        }
    }
    
    public void startGame() {
        model.start();
    }
}
