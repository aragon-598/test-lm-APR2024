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
}
