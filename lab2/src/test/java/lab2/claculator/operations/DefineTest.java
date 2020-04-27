/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Define;
import lab2.calculator.Context;
import static org.junit.jupiter.api.Assertions.*;

/**
 *
 * @author bratizgut
 */
public class DefineTest {

    @org.junit.jupiter.api.Test
    public void testDoOperation() {
        Context context = new Context();
        Define instance = new Define();
        String[] args = new String[]{"a", "a"};
        instance.doOperation(context, args);
        assertTrue(context.getVars().isEmpty());
        args = new String[]{"4", "a"};
        instance.doOperation(context, args);
        args = new String[]{};
        instance.doOperation(context, args);
        assertTrue(context.getVars().isEmpty());
        args = new String[]{"a", "4"};
        instance.doOperation(context, args);
        assertTrue(context.getVars().get("a").equals(4d));
    }
    
}
