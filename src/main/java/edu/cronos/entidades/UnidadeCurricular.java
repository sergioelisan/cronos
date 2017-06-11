package edu.cronos.entidades;

import java.io.Serializable;
import java.util.Objects;

/**
 * @author sergio lisan e carlos melo
 */
public class UnidadeCurricular implements Comparable<UnidadeCurricular>, Serializable {

    /**
     * ideintificador unico
     */
    private Integer id = 0;
    /**
     * disciplina
     */
    private String nome = "";
    /**
     * nucleo
     */
    private Nucleo nucleo = new Nucleo();
    /**
     * carga horaria
     */
    private Integer cargaHoraria = 0;
    /**
     * modulo
     */
    private Integer modulo = 0;
    /**
     * descricao do conteudo da disciplina
     */
    private String conteudoProgramatico = "";
    /**
     * laboratorio
     */
    private Laboratorio lab = new Laboratorio();

    public UnidadeCurricular() {
    }

    public UnidadeCurricular(String nome, Nucleo nucleo,
                             int cargaHoraria, Integer modulo, String conteudoProgramatico, Laboratorio lab) {
        this.nome = nome;
        this.nucleo = nucleo;
        this.cargaHoraria = cargaHoraria;
        this.modulo = modulo;
        this.conteudoProgramatico = conteudoProgramatico;
        this.lab = lab;
    }

    public Laboratorio getLab() {
        return lab;
    }

    public void setLab(Laboratorio lab) {
        this.lab = lab;
    }

    public int getCargaHoraria() {
        return cargaHoraria;
    }

    public void setCargaHoraria(int cargaHoraria) {
        this.cargaHoraria = cargaHoraria;
    }

    public String getConteudoProgramatico() {
        return conteudoProgramatico;
    }

    public void setConteudoProgramatico(String conteudoProgramatico) {
        this.conteudoProgramatico = conteudoProgramatico;
    }

    public Integer getModulo() {
        return modulo;
    }

    public void setModulo(Integer modulo) {
        this.modulo = modulo;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Nucleo getNucleo() {
        return nucleo;
    }

    public void setNucleo(Nucleo nucleo) {
        this.nucleo = nucleo;
    }

    public Integer getId() {
        return id;
    }

    public void setId(Integer id) {
        this.id = id;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }

        if (getClass() != obj.getClass()) {
            return false;
        }

        final UnidadeCurricular other = (UnidadeCurricular) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }

        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nucleo, other.nucleo)) {
            return false;
        }
        if (!Objects.equals(this.cargaHoraria, other.cargaHoraria)) {
            return false;
        }
        if (!Objects.equals(this.modulo, other.modulo)) {
            return false;
        }
        return Objects.equals(this.lab, other.lab);
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 67 * hash + (this.id ^ (this.id >>> 32));
        hash = 67 * hash + Objects.hashCode(this.nome);
        return hash;
    }

    @Override
    public String toString() {
        return "[" + nome + "] "
                + "nucleo (" + nucleo + ")"
                + ", carga - " + cargaHoraria
                + ", modulo - " + modulo
                + ", lab (" + lab + ")"
                + ", ementa - " + conteudoProgramatico;
    }

    @Override
    public int compareTo(UnidadeCurricular o) {
        return modulo > o.modulo ? 1 : modulo == o.modulo ? 0 : -1;
    }
}
