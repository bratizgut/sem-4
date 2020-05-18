package client;

import client.controller.Controller;
import client.view.MainFrame;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import server.message.InitMessage;

/**
 *
 * @author bratizgut
 */
public class Test {
    public static void main(String[] args) throws InterruptedException {
        int port = 1777;
        
        try {
            Socket servSocket = new Socket("127.0.0.1", port);
            
            PrintWriter pw = new PrintWriter(servSocket.getOutputStream(), true);
            
            pw.println("123");
            
            InputStream stream = servSocket.getInputStream();
            ObjectInputStream inputStream = new ObjectInputStream(stream);
            
            Properties properties = new Properties();

            properties.load(Objects.requireNonNull(Test.class.getResourceAsStream("/gameprops.properties")));
            
            int width = Integer.parseInt(properties.getProperty("fieldWidth"));
            pw.println(width);
            int height = Integer.parseInt(properties.getProperty("fieldHeight"));
            pw.println(height);
            
            int ballSpeed = Integer.parseInt(properties.getProperty("ballSpeed"));
            pw.println(ballSpeed);
            int ballRad = Integer.parseInt(properties.getProperty("ballRad"));
            pw.println(ballRad);
            String ballImg = properties.getProperty("ballImg");
            
            int playerSpeed = Integer.parseInt(properties.getProperty("playerSpeed"));
            pw.println(playerSpeed);
            
            int paneLength = Integer.parseInt(properties.getProperty("paneLength"));
            pw.println(paneLength);
            int paneWidth = Integer.parseInt(properties.getProperty("paneWidth"));
            pw.println(paneWidth);
            String paneImg = properties.getProperty("paneImg");
            
            try {
                InitMessage message = (InitMessage)inputStream.readObject();
                Controller controller = new Controller(pw, inputStream);
                MainFrame view = new MainFrame(message, ballImg, paneImg, controller);
                controller.addObserver(view.getGameFrame());
                controller.start();
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
            }
        } catch (IOException ex) {
            Logger.getLogger(Test.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
}
