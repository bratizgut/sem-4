package lab2.calculator.operations;

import lab2.calculator.Context;
import lab2.exeptions.*;

/**
 *
 * @author bratizgut
 */
public interface Operations {
    void doOperation(Context context, String[] args) throws ArgumentsException, ContextException, ArithmeticException;
}
