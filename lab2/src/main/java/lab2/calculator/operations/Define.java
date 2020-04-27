package lab2.calculator.operations;

import java.util.HashMap;
import java.util.logging.*;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Define implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        if(validate(context, args)){
            context.getVars().put(args[0], Double.parseDouble(args[1]));
            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        }
    }
    
    public boolean validate(Context context, String[] args){
        
        HashMap<String, Double> vars = context.getVars();
        if(args.length < 2){
            LOG.log(Level.WARNING, "Too few arguments for Define");
            return false;
        }
        if(args.length > 2){
            LOG.log(Level.WARNING, "Too much arguments for Define");
            return false;
        }
        try{
            Double.parseDouble(args[1]);
        } catch(NumberFormatException ex){
            LOG.log(Level.WARNING, "Can not assign {0} as {1}", args);
            return false;
        }
        try{
            Double.parseDouble(args[0]);
            LOG.log(Level.WARNING, "Can not assign {0} as {1}", args);
            return false;
        } catch (NumberFormatException ex){
        }

        return true;
    }
}
