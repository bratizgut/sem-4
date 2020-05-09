package lab2.calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Push extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 0, args, 1);
        try {
            context.getNums().push(Double.parseDouble(args[0]));
        } catch (NumberFormatException ex) {
            context.getNums().push(context.getVars().get(args[0]));
        }
        LOG.log(Level.FINE, "Successfully pushed {0} on stack", args);
    }

    @Override
    public void validate(Context context, int reqContext, String[] args, int reqArgs) throws ArgumentsException, ContextException {
        super.validate(context, reqContext, args, reqArgs);
        try {
            Double.parseDouble(args[0]);
        } catch (NumberFormatException ex) {
            if (!context.getVars().containsKey(args[0])) {
                throw new ArgumentsException("Invalid argument");
            }
        }
    }
}
