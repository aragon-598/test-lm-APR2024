package com.lifemiles.util;

public interface IUtilFunctions {
    boolean esOperador(char c);
    int obtenerPrecedencia(char operador);
    boolean validarExpresionInfija(String expression);
}
