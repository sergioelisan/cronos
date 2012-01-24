package senai.cronos.util;

import java.util.Objects;

/**
 * Classe que representa uma par de dois elementos
 * 
 * @author Sergio Lisan e carlos melo
 */
public class Tupla<K, V> {

    public Tupla() {
    }

    public Tupla(K primeiro, V segundo) {
        this.primeiro = primeiro;
        this.segundo = segundo;
    }

    public K getPrimeiro() {
        return primeiro;
    }

    public void setPrimeiro(K primeiro) {
        this.primeiro = primeiro;
    }

    public V getSegundo() {
        return segundo;
    }

    public void setSegundo(V segundo) {
        this.segundo = segundo;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Tupla<K, V> other = (Tupla<K, V>) obj;
        if (!Objects.equals(this.primeiro, other.primeiro)) {
            return false;
        }
        if (!Objects.equals(this.segundo, other.segundo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 97 * hash + Objects.hashCode(this.primeiro);
        hash = 97 * hash + Objects.hashCode(this.segundo);
        return hash;
    }

    @Override
    public String toString() {
        return "Tupla { " + "primeiro = " + primeiro + ", segundo = " + segundo + '}';
    }
    
    private K primeiro;
    private V segundo;
    
}
