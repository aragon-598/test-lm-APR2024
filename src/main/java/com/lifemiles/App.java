package com.lifemiles;

import java.util.Stack;
import java.util.Queue;
import java.util.LinkedList;

public class App {
    
    // metodo para verificar si un char es un operador
    private static boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    // metodo para obtener la precedencia de un operador
    private static int obtenerPrecedencia(char operador) {
        switch (operador) {
            case '+':
            case '-':
                return 1;
            case '*':
            case '/':
                return 2;
            default:
                return -1;
        }
    }
    
    // metodo para evaluar una expresión postfija
    private static double evaluarPostfijo(String expresionPostfija) {
        Stack<Double> pila = new Stack<>();
        String[] tokens = expresionPostfija.split("\\s+");
        
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
                        pila.push(operand1 / operand2);
                        break;
                }
            }
        }
        
        return pila.pop();
    }
    
    // metodo para convertir la expresión infija a postfija
    public static String convertirPostfijo(String expresionInfija) {
        Stack<Character> pila = new Stack<>();
        Queue<Character> colaSalida = new LinkedList<>();
        
        for (int i = 0; i < expresionInfija.length(); i++) {
            char caracter = expresionInfija.charAt(i);
            
            if (Character.isDigit(caracter)) {
                colaSalida.add(caracter);
            } else if (caracter == '(') {
                throw new IllegalArgumentException("La expresión "+expresionInfija+" no es valida");
            } else if (caracter == ')') {
                while (!pila.isEmpty() && pila.peek() != '(') {
                    throw new IllegalArgumentException("La expresión "+expresionInfija+" no es valida");
                }
            } else if (esOperador(caracter)) {
                while (!pila.isEmpty() && obtenerPrecedencia(caracter) <= obtenerPrecedencia(pila.peek())) {
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

    public static String response(String expresionInfija) {
        try {
            String expresionPostfija = convertirPostfijo(expresionInfija);
            
            double resultado = evaluarPostfijo(expresionPostfija);
            String resultResponse = String.valueOf(resultado);

            return "INFIX_EXPRESSION: "+expresionInfija+"\n"+
                    "POSTFIX_EXPRESSION: "+expresionPostfija+"\n"+
                    "VALUE: "+resultResponse+"\n";
        } catch (Exception e) {
            return "Mensaje: "+e.getMessage();
        }
    }
    
    public static void main(String[] args) {
            String expresionInfija = "1+2.5/3*4";

            System.out.println(response(expresionInfija));
           
    }


}
