package lab2.calculator;

/**
 *
 * @author bratizgut
 */
import java.io.IOException;
import java.io.InputStream;
import java.lang.reflect.InvocationTargetException;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.operations.Operations;
import lab2.exeptions.CommandNotFoundException;
import lab2.exeptions.PropertiesNotFoundException;

public class Factory {

    private final static Logger LOG = Logger.getLogger(Factory.class.getName());

    private final Properties properties = new Properties();

    private static volatile Factory instance;
    
    private Factory() throws PropertiesNotFoundException {
        InputStream propStream = Factory.class.getResourceAsStream("/config.properties");
        if(propStream == null)
            throw new PropertiesNotFoundException("Properties could not be found.");
        try {
            properties.load(propStream);
        } catch (IOException ex) {
            throw new PropertiesNotFoundException("Properties could not be loaded.");
        }
    }
    
    public static Factory getInstance() throws PropertiesNotFoundException {
        if(instance == null) {
            synchronized (Factory.class) {
                if(instance == null) { 
                    instance = new Factory();
                }
            }
        }
        return instance;
    }

    public Operations createOperation(String operationName) throws CommandNotFoundException {
        LOG.log(Level.FINE, "Trying to create class for command {0}", operationName);
        String className = properties.getProperty(operationName);
        if(className == null){
            throw new CommandNotFoundException("Command " + operationName + " not found.");
        }
        Operations operation;
        try {
            operation = (Operations) Class.forName(className).getDeclaredConstructor().newInstance();
        } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | SecurityException | InvocationTargetException ex) {
            throw new CommandNotFoundException("Command " + operationName + " could not be created.");
        }
        return operation;
    }

}
