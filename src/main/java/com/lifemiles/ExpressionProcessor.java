package com.lifemiles;

import com.lifemiles.interfaces.ExpressionConverter;
import com.lifemiles.interfaces.ExpressionEvaluator;

public class ExpressionProcessor {
    private final ExpressionConverter converter;
    private final ExpressionEvaluator evaluator;

    public ExpressionProcessor(ExpressionConverter converter, ExpressionEvaluator evaluator) {
        this.converter = converter;
        this.evaluator = evaluator;
    }

    public String processExpression(String infixExpression) {
        try {
            String postfixExpression = converter.convertExpression(infixExpression);
        double result = evaluator.evaluateExpression(postfixExpression);
        return "INFIX_EXPRESSION: " + infixExpression + "\n" +
               "POSTFIX_EXPRESSION: " + postfixExpression + "\n" +
               "VALUE: " + result + "\n";
        } catch (Exception e) {
            return " MENSAJE: " + e.getMessage();
        }
    }
}
