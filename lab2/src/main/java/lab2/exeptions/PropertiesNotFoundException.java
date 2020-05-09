package lab2.exeptions;

/**
 *
 * @author bratizgut
 */
public class PropertiesNotFoundException extends Exception {

    public PropertiesNotFoundException() {
        super();
    }
    
    public PropertiesNotFoundException(String message) {
        super(message);
    }
    
}
