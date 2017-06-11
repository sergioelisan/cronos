package edu.cronos.util;

/**
 * Interface que cria um contrato viabilizando o objeto que a implementa a ser
 * notificado quando há uma atualizacao no observado.
 *
 * @author sergio lisan e carlos melo
 */
public interface Observador {

    /**
     * metodo chamado pelo sujeito observado quando este realiza alguma
     * atualizacao que acha necessaria ser notificada os seus observadores
     */
    void update();
}
