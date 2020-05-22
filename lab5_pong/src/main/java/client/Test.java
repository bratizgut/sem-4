package client;

import common.view.MainFrame;
import java.io.IOException;
import java.io.InputStream;
import java.io.ObjectInputStream;
import java.io.PrintWriter;
import java.net.Socket;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import common.message.InitMessage;

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
            
            try {
                InitMessage message = (InitMessage)inputStream.readObject();
                Controller controller = new Controller(pw, inputStream);
                MainFrame view = new MainFrame(message, controller);
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
