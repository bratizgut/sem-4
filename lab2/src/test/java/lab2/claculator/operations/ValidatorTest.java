/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Validator;
import lab2.calculator.Context;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class ValidatorTest {

    @Test
    public void testValidate() {
        Context context = new Context();
        Validator instance = new Validator();
        String[] args = new String[]{};
        context.getNums().push(10d);
        context.getNums().push(10d);
        assertTrue(instance.validate(context, args));
        context.getNums().pop();
        assertFalse(instance.validate(context, args));
        args = new String[]{"10"};
        context.getNums().push(10d);
        assertFalse(instance.validate(context, args));
    }
    
}
