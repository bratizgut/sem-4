/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Sqrt;
import lab2.calculator.Context;
import lab2.exeptions.ArgumentsException;
import lab2.exeptions.ContextException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;
import org.junit.jupiter.api.function.Executable;

/**
 *
 * @author bratizgut
 */
public class SqrtTest {
 
    @Test
    public void testDoOperation() {
        Context context = new Context();
        Sqrt instance = new Sqrt();
        String[] args = new String[]{};
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getNums().isEmpty());
        
        context.getNums().push(4d);
        assertTrue(context.getNums().peek().equals(4d));
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getNums().peek().equals(2d));
        
        assertThrows(ArithmeticException.class, new Executable() {
            @Override
            public void execute() throws Throwable {
                Context context = new Context();
                Sqrt instance = new Sqrt();
                String[] args = new String[]{};
                context.getNums().push(-10d);
                instance.doOperation(context, args);
            }
        });
        
    }
    
}
