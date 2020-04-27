package lab2.calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Print extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        if(validate(context, args)){
            System.out.println(context.getNums().peek());
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
        return true;
    }
    
}
