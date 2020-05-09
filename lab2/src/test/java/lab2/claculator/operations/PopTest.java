/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Pop;
import lab2.calculator.Context;
import lab2.exeptions.ArgumentsException;
import lab2.exeptions.ContextException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class PopTest {
 
    @Test
    public void testDoOperation() {
        Context context = new Context();
        Pop instance = new Pop();
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
        assertTrue(context.getNums().isEmpty());
        
        context.getNums().push(-10d);
        context.getNums().push(15d);
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getNums().peek().equals(-10d));
        assertFalse(context.getNums().isEmpty());
    }
    
}
