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
        Queue<String> colaSalida = new LinkedList<>(); // Usamos una cola de cadenas en lugar de una cola de caracteres
        
        StringBuilder numeroActual = new StringBuilder(); // Para construir los números con decimales
        
        for (int i = 0; i < infixExpression.length(); i++) {
            char caracter = infixExpression.charAt(i);
            
            if (Character.isDigit(caracter) || caracter == '.') {
                numeroActual.append(caracter);
            } else {
                if (numeroActual.length() > 0) {
                    colaSalida.add(numeroActual.toString());
                    numeroActual.setLength(0); // Reinicia el StringBuilder
                }
                
                if (caracter == '(') {
                    throw new IllegalArgumentException("Se ha producido algún error al procesar la expresión"); //("Se ha producido algún error al procesar la expresión");
                } else if (caracter == ')') {
                    return "Se ha producido algún error al procesar la expresión";
                } else if (utilFunctions.esOperador(caracter)) {
                    while (!pila.isEmpty() && utilFunctions.obtenerPrecedencia(caracter) <= utilFunctions.obtenerPrecedencia(pila.peek())) {
                        colaSalida.add(pila.pop().toString());
                    }
                    pila.push(caracter);
                }
            }
        }
        
        if (numeroActual.length() > 0) {
            colaSalida.add(numeroActual.toString());
        }
        
        // vaciando la pila
        while (!pila.isEmpty()) {
            colaSalida.add(pila.pop().toString());
        }
        
        // construyendo la cadena resultante
        StringBuilder resultado = new StringBuilder();
        for (String c : colaSalida) {
            resultado.append(c);
            resultado.append(' '); // poner un espacio entre cada char
        }
        
        return resultado.toString();
    }
    
}
