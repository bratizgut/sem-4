package lab2.calculator.operations;

import static java.lang.Math.sqrt;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Sqrt extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 1, args, 0);
        Stack<Double> varsStack = context.getNums();
        varsStack.push(sqrt(varsStack.pop()));
        LOG.log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
    }

    @Override
    public void validate(Context context, int reqContext, String[] args, int reqArgs) throws ArgumentsException, ContextException {
        super.validate(context, reqContext, args, reqArgs);
        if (context.getNums().peek() < 0) {
            throw new ArithmeticException("Sqrt of a negative number");
        }
    }
}
