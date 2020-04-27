/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Substraction;
import lab2.calculator.Context;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class SubstractionTest {

    @Test
    public void testDoOperation() {
        Context context = new Context();
        Substraction instance = new Substraction();
        String[] args = new String[]{};
        instance.doOperation(context, args);
        assertTrue(context.getNums().isEmpty());
        context.getNums().push(5d);
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(5d));
        context.getNums().push(6d);
        assertTrue(context.getNums().peek().equals(6d));
        instance.doOperation(context, args);
        assertTrue(context.getNums().peek().equals(6 - 5d));
    }
    
}
