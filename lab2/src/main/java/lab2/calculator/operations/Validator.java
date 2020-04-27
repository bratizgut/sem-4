package lab2.calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Validator {
    
    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    public boolean validate(Context context, String[] args){
        if (args.length > 0) {
            LOG.log(Level.WARNING, "Don't need arguments for this operation");
            return false;
        }
        if(context.getNums().size() < 2){
            LOG.log(Level.SEVERE, "Too few elements on stack");
            return false;
        }
        return true;
    }
}
