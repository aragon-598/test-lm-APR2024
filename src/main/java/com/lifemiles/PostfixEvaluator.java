package com.lifemiles;

import java.util.Stack;

import com.lifemiles.interfaces.ExpressionEvaluator;

public class PostfixEvaluator implements ExpressionEvaluator {

    @Override
    public double evaluateExpression(String postfixExpression) {
        Stack<Double> pila = new Stack<>();
        String[] tokens = postfixExpression.split("\\s+");
        
        for (String token : tokens) {
            if (Character.isDigit(token.charAt(0))) {
                pila.push(Double.parseDouble(token));
            } else {
                double operand2 = pila.pop();
                double operand1 = pila.pop();
                switch (token.charAt(0)) {
                    case '+':
                        pila.push(operand1 + operand2);
                        break;
                    case '-':
                        pila.push(operand1 - operand2);
                        break;
                    case '*':
                        pila.push(operand1 * operand2);
                        break;
                    case '/':
                        if (operand2 <1)
                            throw new IllegalArgumentException("La expresión infija no es válida");
                        pila.push(operand1 / operand2);
                }
            }
        }
        
        return pila.pop();
    }
    
}
