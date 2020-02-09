package lab1;

import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * Main
 */
public class Main {
    public static void main(String[] args) {
        Parser parser = new Parser();
        
        try {
            parser.parse("test.txt");
        } catch (IOException ex) {
            Logger.getLogger(Main.class.getName()).log(Level.SEVERE, null, ex);
        }
        
    }
}