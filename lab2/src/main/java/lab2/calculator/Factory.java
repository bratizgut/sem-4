package lab2.calculator;

/**
 *
 * @author bratizgut
 */
import java.io.IOException;
import java.lang.reflect.InvocationTargetException;
import java.util.Objects;
import java.util.Properties;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.operations.Operations;
import lab2.exeptions.PropertiesNotFoundException;

public class Factory {

    private final static Logger LOG = Logger.getLogger(Factory.class.getName());

    private final Properties properties = new Properties();

    private static volatile Factory instance;
    
    private Factory() throws IOException, NullPointerException {
        properties.load(Objects.requireNonNull(Factory.class.getResourceAsStream("config.properties")));
    }
    
    public static Factory getInstance() throws PropertiesNotFoundException {
        if(instance == null) {
            synchronized (Factory.class) {
                if(instance == null) {
                    try {
                    instance = new Factory();
                    } catch (IOException | NullPointerException ex) {
                    }
                }
            }
        }
        if(instance == null) {
            throw new PropertiesNotFoundException();
        }
        return instance;
    }

    public Operations createOperation(String operationName) throws ClassNotFoundException, NoSuchMethodException, InstantiationException, IllegalAccessException, IllegalArgumentException, InvocationTargetException, NullPointerException {
        String className = properties.getProperty(operationName);
        LOG.log(Level.FINE, "Trying to create class for command {0}", operationName);
        Operations operation;
        operation = (Operations) Class.forName(className).getDeclaredConstructor().newInstance();
        if (operation != null) {
            LOG.log(Level.FINE, "Created operation {0}", className);
        }
        return operation;
    }

}
