package lab2.calculator.operations;

import static java.lang.Math.sqrt;
import java.util.Stack;
import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Sqrt extends Validator implements Operations{

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        if(validate(context, args)){
            Stack<Double> varsStack = context.getNums();
            varsStack.push(sqrt(varsStack.pop()));
            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
        }
    }
    
    
    @Override 
    public boolean validate(Context context, String[] args){
        if (args.length > 0) {
            LOG.log(Level.WARNING, "Don't need arguments for this operation");
            return false;
        }
        if(context.getNums().size() < 1){
            LOG.log(Level.SEVERE, "Too few elements on stack");
            return false;
        }
        if(context.getNums().peek() < 0){
            LOG.log(Level.WARNING, "Trying to Sqrt from a negative number");
            return false;
        }
        return true;
    }
}
