package edu.cronos.util;

/**
 * Interface em que o sujeito que a implementa devera ter estes metodos que
 * permite a adicao, remocao e noficacao de objetos observadores
 *
 * @author sergio lisan e carlos melo
 */
public interface Observado {

    /**
     * registra um novo observador
     *
     * @param o
     */
    void registra(Observador o);

    /*
     * remove um observador
     */
    void remove(Observador o);

    /**
     * notifica os observadores
     *
     * @param o
     */
    void notifica();
}
