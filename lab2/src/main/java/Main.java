/**
 *
 * @author bratizgut
 */
import java.io.FileInputStream;
import java.io.IOException;
import java.util.Arrays;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import lab2.calculator.Calculator;

public class Main {
    private final static Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) {
        LogManager logManager = LogManager.getLogManager();
        try {
            logManager.readConfiguration(new FileInputStream("log.properties"));
        } catch (IOException ex){
            LOG.log(Level.WARNING, "Can not read properties for logging");
        }
        String inFileName = null;
        if (args.length > 2) {
            LOG.log(Level.SEVERE,"Too much arguments for program {0}. End of work.", Arrays.toString(args));
            return;
        } else if (args.length == 2) {
            inFileName = args[1];
        }
        Calculator calculator = new Calculator();
        LOG.log(Level.FINE, "Create calculator");
        try {
            calculator.calculate(inFileName);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE,"Can not open file.", ex);
        }
        LOG.log(Level.INFO, "Successfully ended calculation!");
    }
}
