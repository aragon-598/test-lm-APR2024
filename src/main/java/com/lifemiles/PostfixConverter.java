package com.lifemiles;

import java.util.LinkedList;
import java.util.Queue;
import java.util.Stack;

import com.lifemiles.interfaces.ExpressionConverter;
import com.lifemiles.util.IUtilFunctions;
import com.lifemiles.util.UtilFunctions;

public class PostfixConverter implements ExpressionConverter{

    private IUtilFunctions utilFunctions;

    public PostfixConverter() {
        utilFunctions = new UtilFunctions();
    }

    @Override
    public String convertExpression(String infixExpression) {
        Stack<Character> pila = new Stack<>();
        Queue<Character> colaSalida = new LinkedList<>();
        
        for (int i = 0; i < infixExpression.length(); i++) {
            char caracter = infixExpression.charAt(i);
            
            if (Character.isDigit(caracter)) {
                colaSalida.add(caracter);
            } else if (caracter == '(') {
                throw new IllegalArgumentException("La expresión "+infixExpression+" no es valida");
            } else if (caracter == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    throw new IllegalArgumentException("La expresión "+infixExpression+" no es valida");
                }
            } else if (utilFunctions.esOperador(caracter)) {
                while (!pila.isEmpty() && utilFunctions.obtenerPrecedencia(caracter) <= utilFunctions.obtenerPrecedencia(pila.peek())) {
                    colaSalida.add(pila.pop());
                }
                pila.push(caracter);
            }
        }
        
        // vaciando la pila
        while (!pila.isEmpty()) {
            colaSalida.add(pila.pop());
        }
        
        // construyendo la cadena resultante
        StringBuilder resultado = new StringBuilder();
        for (char c : colaSalida) {
            resultado.append(c);
            resultado.append(' '); // poner un espacio entre cada char
        }
        
        return resultado.toString();
    }
    
}
