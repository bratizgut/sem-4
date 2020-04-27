package lab2.calculator;

/**
 *
 * @author bratizgut
 */
import java.io.IOException;
import java.nio.file.Paths;
import java.util.Scanner;
import java.util.logging.Logger;
import java.util.logging.Level;
import lab2.calculator.operations.Operations;

public class Calculator {

    private static final Logger LOG = Logger.getLogger(Calculator.class.getName());

    public void calculate(String inFileName) throws IOException {
        LOG.log(Level.FINE, "Enter calculator.calculate({0})", inFileName);
        Scanner reader;
        if (inFileName == null) {
            reader = new Scanner(System.in);
            LOG.log(Level.FINE, "Create reader from System.in");
        } else {
            reader = new Scanner(Paths.get(inFileName).toAbsolutePath());
            LOG.log(Level.FINE, "Create reader from {0}.", inFileName);
        }
        Context context = new Context();
        while (reader.hasNext()) {
            String line = reader.nextLine();
            if (!line.equals("END")) {
                if (!line.startsWith("#")) {
                    String[] tokens = line.split("\\s+");
                    String operationName = tokens[0];
                    String[] arguments = new String[tokens.length - 1];
                    System.arraycopy(tokens, 1, arguments, 0, tokens.length - 1);
                    Operations operation = Factory.getInstance().createOperation(operationName);
                    if (operation != null) {
                        LOG.log(Level.FINE, "Enter doOperation from {0}", operation.getClass().getName());
                        operation.doOperation(context, arguments);
                    } else {
                        LOG.log(Level.WARNING, "Unknown command {0}.", operationName);
                    }
                }
            } else {
                LOG.log(Level.FINE, "Reached \"END\" command.");
                return;
            }
        }
    }
}
