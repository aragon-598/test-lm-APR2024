package com.lifemiles.util;

public class UtilFunctions implements IUtilFunctions {

    @Override
    public boolean esOperador(char c) {
        return c == '+' || c == '-' || c == '*' || c == '/';
    }
    
    @Override
    public int obtenerPrecedencia(char operador) {
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
}
