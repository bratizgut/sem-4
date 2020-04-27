package lab2.calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;

/**
 *
 * @author bratizgut
 */
public class Push extends Validator implements Operations{

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());
    
    @Override
    public void doOperation(Context context, String[] args) {
        Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        if(validate(context, args)){
            try{
                context.getNums().push(Double.parseDouble(args[0]));               
            } catch(NumberFormatException ex){
                context.getNums().push(context.getVars().get(args[0]));
            }
            Logger.getLogger(this.getClass().getName()).log(Level.FINE, "Successfully pushed {0} on stack", args);
        }
    }
    
    @Override
    public boolean validate(Context context, String[] args){
        if(args.length < 1){
            LOG.log(Level.WARNING, "Too few arguments for this operation");
            return false;
        }
        if(args.length > 1){
            LOG.log(Level.WARNING, "Too many arguments for this operation");
            return false;
        }
        try{
            Double.parseDouble(args[0]);
        } catch(NumberFormatException ex){
            if(!context.getVars().containsKey(args[0])){
                LOG.log(Level.WARNING, "Can not push {0}", args);
                return false;
            }
        }
        return true;
    }
}
