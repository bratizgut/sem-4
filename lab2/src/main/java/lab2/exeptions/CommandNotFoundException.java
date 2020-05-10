package lab2.exeptions;

/**
 *
 * @author bratizgut
 */
public class CommandNotFoundException extends Exception {
    public CommandNotFoundException() {
        super();
    }
    
    public CommandNotFoundException(String message) {
        super(message);
    }
}
