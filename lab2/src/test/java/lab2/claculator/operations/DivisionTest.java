/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Division;
import lab2.calculator.Context;
import lab2.exeptions.ArgumentsException;
import lab2.exeptions.ContextException;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class DivisionTest {
 
    @Test
    public void testDoOperation() {
        Context context = new Context();
        Division instance = new Division();
        String[] args = new String[]{};
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException | ArithmeticException ex) {
        }
        assertTrue(context.getNums().isEmpty());
        
        context.getNums().push(5d);
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException | ArithmeticException ex) {
        }
        assertTrue(context.getNums().peek().equals(5d));
        
        context.getNums().push(6d);
        assertTrue(context.getNums().peek().equals(6d));
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException | ArithmeticException ex) {
        }
        assertTrue(context.getNums().peek().equals(6 / 5d));
        
        context.getNums().push(0d);
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException | ArithmeticException ex) {
        }
        assertTrue(context.getNums().peek().equals(0d));
    }
    
}
