package lab2.calculator.operations;

import java.util.logging.Level;
import java.util.logging.Logger;
import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public class Pop extends Validator implements Operations {

    private static final Logger LOG = Logger.getLogger(Validator.class.getName());

    @Override
    public void doOperation(Context context, String[] args) throws ArgumentsException, ContextException {
        LOG.log(Level.FINE, "Entering {0}.doOpearation", this.getClass().getName());
        validate(context, 1, args, 0);
        context.getNums().pop();
        LOG.log(Level.FINE, "Successfully did {0}.doOpearation", this.getClass().getName());
    }
}
