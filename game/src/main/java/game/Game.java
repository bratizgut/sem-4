/**
 *
 * @author bratizgut
 */

import view.MainFrame;
import model.Model;
import controller.Controller;
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Properties;

public class Game {
    public static void main(String[] args) {
        
        Properties properties = new Properties();
        FileInputStream fileInputStream;
        
        try{
            
            fileInputStream = new FileInputStream("gameprops.properties");
            properties.load(fileInputStream);
            
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
            
            Model model = new Model(width, height, ballSpeed, ballRad, playerSpeed, enemySpeed, paneLength, paneWidth);
            Controller controller = new Controller(model);
            MainFrame mainFrame = new MainFrame(width, height, ballImg, paneImg, controller);
            
            model.addObserver(mainFrame.getGameFrame());
            model.start();
        } catch (IOException | NumberFormatException ex) {
            
        }
    }
}
