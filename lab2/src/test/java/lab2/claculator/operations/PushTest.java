/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Push;
import lab2.calculator.operations.Define;
import lab2.calculator.Context;
import org.junit.jupiter.api.Test;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class PushTest {
 
    @Test
    public void testDoOperation() {
        Context context = new Context();
        String[] args = new String[]{};
        Push instance = new Push();
        instance.doOperation(context, args);
        assertTrue(context.getNums().isEmpty());
        instance.doOperation(context, new String[]{"5", "10"});
        assertTrue(context.getNums().isEmpty());
        instance.doOperation(context, new String[]{"5"});
        assertTrue(context.getNums().peek().equals(5d));
        Define define = new Define();
        define.doOperation(context, new String[]{"a", "10"});
        instance.doOperation(context, new String[]{"a"});
        assertTrue(context.getNums().peek().equals(10d));
    }

}
