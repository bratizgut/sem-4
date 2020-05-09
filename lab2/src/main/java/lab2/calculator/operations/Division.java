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
public class Division extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException, ArithmeticException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 2, args, 0);
            Stack<Double> varsStack = context.getNums();
            varsStack.push(varsStack.pop() / varsStack.pop());
            LOG.log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
    }

    @Override
    public void validate(Context context, int reqContext, String[] args, int reqArgs) throws ArgumentsException, ContextException {
        super.validate(context, reqContext, args, reqArgs);
            Stack<Double> varStack = context.getNums();
            double var = varStack.pop();
            if (varStack.peek().equals(0d)) {
                throw new ArithmeticException("Dividing by zero");
            }
            varStack.push(var);
    }

}
