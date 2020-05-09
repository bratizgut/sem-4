package lab2.calculator;

/**
 *
 * @author bratizgut
 */
import java.lang.reflect.InvocationTargetException;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import lab2.calculator.operations.Operations;
import lab2.exeptions.ArgumentsException;
import lab2.exeptions.ContextException;
import lab2.exeptions.PropertiesNotFoundException;

public class Calculator {
    
    private static final Logger LOG = Logger.getLogger(Calculator.class.getName());
    
    public void calculate(Scanner reader) throws PropertiesNotFoundException {
        LOG.log(Level.FINE, "Entered calculator.calculate");
        Context context = new Context();
        
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (!line.equals("END")) {
                if (!line.startsWith("#")) {
                    String[] tokens = line.split("\\s+");
                    String operationName = tokens[0];
                    String[] arguments = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);
                    
                    try {
                        Operations operation = Factory.getInstance().createOperation(operationName);            
                        try {
                            LOG.log(Level.FINE, "Enter doOperation from {0}", operation.getClass().getName());
                            operation.doOperation(context, arguments);
                        } catch (ArgumentsException | ContextException | ArithmeticException ex) {
                            LOG.log(Level.WARNING, ex.getMessage());
                        }
                    } catch (ClassNotFoundException | IllegalAccessException | IllegalArgumentException | InstantiationException | NoSuchMethodException | InvocationTargetException ex) {
                        LOG.log(Level.WARNING, "Could not create {0}", operationName);
                    }
                }
            } else {
                LOG.log(Level.FINE, "Reached \"END\" command.");
                return;
            }
        }
    }
}
