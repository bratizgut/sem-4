package lab2.calculator;

import java.util.HashMap;
import java.util.Stack;

/**
 *
 * @author bratizgut
 */
public class Context {
    private final Stack<Double> nums;
    private final HashMap<String, Double> vars;
    
    public Context(){
        vars = new HashMap<>() ;
        nums = new Stack<>();
    }

    public Stack<Double> getNums() {
        return nums;
    }

    public HashMap<String, Double> getVars() {
        return vars;
    }
}
