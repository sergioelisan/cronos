package edu.cronos.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 * Representa uma sala de aula ou um laboratorio da escola
 *
 * @author Carlos Melo e sergio lisan
 */
public class Laboratorio implements Serializable {

    private Integer id = 0;
    private String nome = "";
    private String descricao = "";

    public Laboratorio() {
    }

    public Laboratorio(String nome, String descricao) {
        this.nome = nome;
        this.descricao = descricao;
    }

    public String getDescricao() {
        return descricao;
    }

    public void setDescricao(String descricao) {
        this.descricao = descricao;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Laboratorio other = (Laboratorio) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        return Objects.equals(this.descricao, other.descricao);
    }

    public int hashCode() {
        int hash = 3;
        hash = 97 * hash + Objects.hashCode(this.id);
        hash = 97 * hash + Objects.hashCode(this.nome);
        hash = 97 * hash + Objects.hashCode(this.descricao);
        return hash;
    }

    public String toString() {
        return "Laboratorio{" + "id = " + id + ", nome = " + nome + ", descricao = " + descricao + '}';
    }
}
