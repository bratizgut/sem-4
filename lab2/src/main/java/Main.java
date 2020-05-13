/**
 *
 * @author bratizgut
 */
import java.io.IOException;
import java.io.InputStream;
import java.nio.file.Paths;
import java.util.Arrays;
import java.util.Scanner;
import java.util.logging.Level;
import java.util.logging.LogManager;
import java.util.logging.Logger;
import lab2.calculator.Calculator;
import lab2.exeptions.PropertiesNotFoundException;

public class Main {
    private final static Logger LOG = Logger.getLogger(Main.class.getName());
    
    public static void main(String[] args) throws IOException {
        
        LogManager logManager = LogManager.getLogManager();
        try {
            InputStream logStream = Main.class.getClassLoader().getResourceAsStream("log.properties");
            if(logStream == null){
                LOG.log(Level.WARNING, "Can not read properties for logging");
            }
            logManager.readConfiguration(logStream);
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
        
        Scanner reader;
        if (inFileName == null) {
            reader = new Scanner(System.in);
            LOG.log(Level.FINE, "Create reader from System.in");
        } else {
            reader = new Scanner(Paths.get(inFileName).toAbsolutePath());
            LOG.log(Level.FINE, "Create reader from {0}.", inFileName);
        }
        
        Calculator calculator = new Calculator();
        LOG.log(Level.FINE, "Create calculator");
        
        try {
            calculator.calculate(reader);
        } catch (PropertiesNotFoundException ex) {
            LOG.log(Level.SEVERE, ex.getMessage());
        }
        
        LOG.log(Level.INFO, "Successfully ended calculation!");
    }
}
