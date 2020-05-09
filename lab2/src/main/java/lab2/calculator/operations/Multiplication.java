package lab2.calculator.operations;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Multiplication extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Multiplication.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 2, args, 0);
        Stack<Double> varsStack = context.getNums();
        varsStack.push(varsStack.pop() * varsStack.pop());
        LOG.log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
    }

}
