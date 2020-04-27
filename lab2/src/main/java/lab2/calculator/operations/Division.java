package lab2.calculator.operations;

import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Division extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        if (validate(context, args)) {
            Stack<Double> varsStack = context.getNums();
            varsStack.push(varsStack.pop() / varsStack.pop());
            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
        }
    }

    @Override
    public boolean validate(Context context, String[] args) {
        boolean res = super.validate(context, args);
        if (res) {
            Stack<Double> varStack = context.getNums();
            double var = varStack.pop();
            if (varStack.peek().equals(0d)) {
                LOG.log(Level.WARNING, "Dividing by zero");
                res = false;
            }
            varStack.push(var);
        }
        return res;
    }

}
