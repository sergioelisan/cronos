package edu.cronos.util;

import java.util.ArrayList;
import java.util.List;
import java.util.Objects;

/**
 * Classe que representa uma par de dois elementos
 *
 * @author Sergio Lisan e carlos melo
 */
public class Tupla<K, V> {

    public static final Integer PRIMEIRA = 0;
    public static final Integer SEGUNDA = 1;
    /**
     * vetor que armazena os elementos da tupla
     */
    private List<Object> elementos;

    public Tupla() {
        elementos = new ArrayList<>();
    }

    public Tupla(K primeiro, V segundo) {
        this();
        elementos.add(Tupla.PRIMEIRA, primeiro);
        elementos.add(Tupla.SEGUNDA, segundo);
    }

    /**
     * Insere de acordo com a posicao passada com parametro
     *
     * @param objeto
     * @param posicao
     */
    public void set(Object objeto, Integer posicao) {
        list().set(posicao, objeto);
    }

    /**
     * Retorna um objeto alocado na posicao passada como parametro
     *
     * @param posicao
     * @return
     */
    public Object get(Integer posicao) {
        return list().get(posicao);
    }

    /**
     * empacota os elementos da tupla numa lista
     * <p>
     * NOT SAFE!!!
     *
     * @return array with tuple elements
     */
    public List<Object> list() {
        return elementos;
    }

    /**
     * verifica a existencia de um objeto na tupla
     */
    public boolean contains(Object obj) {
        return list().contains(obj);
    }

    public K getPrimeiro() {
        return (K) get(Tupla.PRIMEIRA);
    }

    public void setPrimeiro(K primeiro) {
        set(primeiro, Tupla.PRIMEIRA);
    }

    public V getSegundo() {
        return (V) get(Tupla.SEGUNDA);
    }

    public void setSegundo(V segundo) {
        set(segundo, Tupla.SEGUNDA);
    }

    @Override
    // hashcode()
    // <editor-fold defaultstate="collapsed">
    public int hashCode() {
        int hash = 5;
        hash = 89 * hash + Objects.hashCode(this.elementos);
        return hash;
    }
    // </editor-fold>

    @Override
    // equals()
    // <editor-fold defaultstate="collapsed">
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tupla<K, V> other = (Tupla<K, V>) obj;
        return Objects.equals(this.elementos, other.elementos);
    }
    // </editor-fold>

    @Override
    // toString()
    // <editor-fold defaultstate="collapsed">
    public String toString() {
        return "Tupla{" + "elementos=" + elementos + '}';
    }
    // </editor-fold>
}
