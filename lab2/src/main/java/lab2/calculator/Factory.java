package lab2.calculator;

/**
 *
 * @author bratizgut
 */

import java.io.FileInputStream;
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.operations.Operations;

public class Factory {
    private final static Logger LOG = Logger.getLogger(Factory.class.getName());
    
    private final Properties properties = new Properties();
    
    private Factory() {
        try {
            FileInputStream stream = new FileInputStream("config.properties");
            properties.load(stream);
        } catch (IOException ex) {
            LOG.log(Level.SEVERE,"Can not load properties.", ex);
            System.exit(0);
        }
    }
    
    private static Factory Instance;
    public static Factory getInstance(){
        if (Instance == null){
            Instance = new Factory();
        }
        return Instance;
    }
    
    public Operations createOperation(String operationName) {
        String className = properties.getProperty(operationName);
        LOG.log(Level.FINE,"Trying to create class for command {0}", operationName);
        Operations operation = null;
        try {
            operation = (Operations) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (NullPointerException | ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            LOG.log(Level.WARNING,"Can not create class.");
        }
        if (operation != null) {
            LOG.log(Level.FINE, "Created operation {0}", className);
        }
        return operation;
    }
    
}
