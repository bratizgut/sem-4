/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Sqrt;
import lab2.calculator.Context;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

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
        instance.doOperation(context, args);
        assertTrue(context.getNums().isEmpty());
        context.getNums().push(4d);
        assertTrue(context.getNums().peek().equals(4d));
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(2d));
        context.getNums().push(-10d);
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(-10d));
    }
    
}
