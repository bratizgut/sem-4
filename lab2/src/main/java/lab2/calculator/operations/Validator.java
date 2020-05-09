package lab2.calculator.operations;

import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Validator {
    
    public void validate(Context context, int reqContext, String[] args, int reqArgs) throws ArgumentsException, ContextException {
        if (args.length < reqArgs){
            throw new ArgumentsException("Too few arguments");
        }
        if (args.length > reqArgs) {
            throw new ArgumentsException("Too many arguments");
        }
        if (context.getNums().size() < reqContext){
            throw new ContextException("Too few elements on stack");
        }
    }
}
