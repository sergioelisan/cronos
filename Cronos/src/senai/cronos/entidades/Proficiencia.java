package senai.cronos.entidades;

import java.util.Objects;

/**
 *
 * Classe que representa a experiencia do professor em uma determinada unidade curricular
 * @author Carlos Melo e Sergio Lisan
 */
public class Proficiencia implements Comparable<Proficiencia> {

    private Integer matricula;
    private UnidadeCurricular unidadecurricular = new UnidadeCurricular();
    private int nivel = 0;
    private int lecionado = 0;

    public Proficiencia() {
    }

    public Proficiencia(UnidadeCurricular unidadecurricular, int nivel, int lecionado) {
        this.unidadecurricular = unidadecurricular;
        this.nivel = nivel;
        this.lecionado = lecionado;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }
    
    public int getLecionado() {
        return lecionado;
    }

    public void setLecionado(int lecionado) {
        this.lecionado = lecionado;
    }

    public int getNivel() {
        return nivel;
    }

    public void setNivel(int nivel) {
        this.nivel = nivel;
    }

    public UnidadeCurricular getDisciplina() {
        return unidadecurricular;
    }

    public void setUnidadecurricular(UnidadeCurricular unidadecurricular) {
        this.unidadecurricular = unidadecurricular;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Proficiencia other = (Proficiencia) obj;
        if (!Objects.equals(this.unidadecurricular, other.unidadecurricular)) {
            return false;
        }
        if (this.nivel != other.nivel) {
            return false;
        }
        if (this.lecionado != other.lecionado) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 79 * hash + Objects.hashCode(this.unidadecurricular);
        hash = 79 * hash + this.nivel;
        hash = 79 * hash + this.lecionado;
        return hash;
    }

    @Override
    public String toString() {
        return "Proficiencia{" + "unidadecurricular=" + unidadecurricular + ", nivel=" + nivel + ", lecionado=" + lecionado + '}';
    }

    @Override
    public int compareTo(Proficiencia o) {
        return nivel > o.nivel ? 1 : (nivel == o.nivel ? 0 : -1);
    }
}
