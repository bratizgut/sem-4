/*
 * To change this license header, choose License Headers in Project Properties.
 * To change this template file, choose Tools | Templates
 * and open the template in the editor.
 */
package lab2.claculator.operations;

import lab2.calculator.operations.Define;
import lab2.calculator.Context;
import lab2.exeptions.ArgumentsException;
import lab2.exeptions.ContextException;
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
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getVars().isEmpty());
        
        args = new String[]{"4", "a"};
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        args = new String[]{};
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getVars().isEmpty());
        
        args = new String[]{"a", "4"};
        try {
            instance.doOperation(context, args);
        } catch (ArgumentsException | ContextException ex) {
        }
        assertTrue(context.getVars().get("a").equals(4d));
    }
    
}
