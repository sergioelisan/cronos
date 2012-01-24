package senai.cronos.util;

import java.util.Random;

/**
 *
 * Classe com utilidades para numeros aleatorios
 * 
 * @author Sergio Lisan e Carlos Melo
 */
public class Aleatorio {

    /**
     * Gera um numero aleatorio
     * @param inicio
     * @param fim
     * @return 
     */
    public static int alec(int inicio, int fim) { 
        int fator = new Random().nextInt(4);
        return fator;
    }
}
