package com.lifemiles.util;

import java.util.HashMap;
import java.util.Map;

public class UtilFunctions implements IUtilFunctions {

    private static Map<Character, Integer> operators = new HashMap<>();

    static {
        operators.put('+', 1);
        operators.put('-', 1);
        operators.put('*', 2);
        operators.put('/', 2);
    }

    @Override
    public boolean esOperador(char c) {
        return operators.containsKey(c);
    }
    
    @Override
    public int obtenerPrecedencia(char operador) {
        return operators.getOrDefault(operador, -1);
    }

    @Override
    public boolean validarExpresionInfija(String expression) {
        boolean numeroValido = false;
        for (int i = 0; i < expression.length(); i++) {
            char caracter = expression.charAt(i);
            if (Character.isDigit(caracter)) {
                numeroValido = true;
            } else if (esOperador(caracter)) {
                if (!numeroValido) {
                    return false; // Número no válido seguido de un operador
                }
                numeroValido = false;
            } else if (caracter == '.') {
                // Verificar que hay al menos un dígito antes y después del punto decimal
                if (i == 0 || i == expression.length() - 1 || !Character.isDigit(expression.charAt(i - 1)) || !Character.isDigit(expression.charAt(i + 1))) {
                    return false;
                }
            }else if(caracter=='('){
                return false;
            }else if (caracter == ' ') {
                // Ignorar espacios en blanco
            } else {
                return false; // Carácter no válido
            }
        }
        return numeroValido; // La expresión debe terminar con un número válido
    }
}
