/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Division;
import lab2.calculator.Context;
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
        instance.doOperation(context, args);
        assertTrue(context.getNums().isEmpty());
        context.getNums().push(5d);
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(5d));
        context.getNums().push(6d);
        assertTrue(context.getNums().peek().equals(6d));
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(6 / 5d));
        context.getNums().push(0d);
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(0d));
    }
    
}
