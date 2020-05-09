package lab2.calculator.operations;

import java.util.logging.*;
import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Define extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 0, args, 2);
        context.getVars().put(args[0], Double.parseDouble(args[1]));
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());

    }

    @Override
    public void validate(Context context, int reqContext, String[] args, int reqArgs) throws ArgumentsException, ContextException {

        super.validate(context, reqContext, args, reqArgs);
        try {
            Double.parseDouble(args[1]);
        } catch (NumberFormatException ex) {
            throw new ArgumentsException("Can not assign " + args[0] + " as " + args[1]);
        }
        try {
            Double.parseDouble(args[0]);
            throw new ArgumentsException("Can not assign " + args[0] + " as " + args[1]);
        } catch (NumberFormatException ex) {
        }
    }
}
