/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Validator;
import lab2.calculator.Context;
import lab2.exeptions.*;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class ValidatorTest {

    @Test
    public void testValidate() {
        
        assertDoesNotThrow(() -> {
            Context context = new Context();
            Validator instance = new Validator();
            String[] args = new String[]{};
            context.getNums().push(10d);
            context.getNums().push(10d);
            instance.validate(context, 2, args, 0);
        });
        
        assertThrows(ContextException.class, () -> {
            Context context = new Context();
            Validator instance = new Validator();
            String[] args = new String[]{};
            context.getNums().push(10d);
            instance.validate(context, 2, args, 0);
        });
        
        assertDoesNotThrow(() -> {
            Context context = new Context();
            Validator instance = new Validator();
            context.getNums().push(10d);
            context.getNums().push(10d);
            instance.validate(context, 2, new String[]{"test"}, 1);
        });
        
        assertThrows(ArgumentsException.class, () -> {
            Context context = new Context();
            Validator instance = new Validator();
            String[] args = new String[]{};
            context.getNums().push(10d);
            context.getNums().push(10d);
            instance.validate(context, 2, args, 1);
        });
        
    }
    
}


