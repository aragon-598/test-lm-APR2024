package com.lifemiles;


import com.lifemiles.interfaces.ExpressionConverter;
import com.lifemiles.interfaces.ExpressionEvaluator;

public class App {
    
    public static void main(String[] args) {
        ExpressionConverter converter = new PostfixConverter();
        ExpressionEvaluator evaluator = new PostfixEvaluator();
        ExpressionProcessor processor = new ExpressionProcessor(converter, evaluator);
        
        String infixExpression = "1 + 2.5 / -9 * 4";
        System.out.println(processor.processExpression(infixExpression));
    }


}
