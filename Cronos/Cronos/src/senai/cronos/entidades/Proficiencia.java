package senai.cronos.entidades;

import java.util.Objects;

/**
 *
 * Classe que representa a experiencia do professor em uma determinada unidade curricular
 * @author Carlos Melo e Sergio Lisan
 */
public class Proficiencia implements Comparable<Proficiencia> {

    public Proficiencia() {
    }

    public Proficiencia(Docente docente, UnidadeCurricular unidadecurricular, int scoretemp, int lecionado) {
        this.docente = docente;
        this.unidadecurricular = unidadecurricular;
        this.scoretemp = scoretemp;
        this.lecionado = lecionado;
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }
    
    public int getLecionado() {
        return lecionado;
    }

    public void setLecionado(int lecionado) {
        this.lecionado = lecionado;
    }

    public int getScoreTemp() {
        return scoretemp;
    }

    public void setScoreTemp(int scoretemp) {
        this.scoretemp = scoretemp;
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
        if (this.scoretemp != other.scoretemp) {
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
        hash = 79 * hash + this.scoretemp;
        hash = 79 * hash + this.lecionado;
        return hash;
    }

    @Override
    public String toString() {
        return "Proficiencia{" + "unidadecurricular=" + unidadecurricular.getId() + ", lecionado=" + lecionado + '}';
    }

    @Override
    public int compareTo(Proficiencia o) {
        return scoretemp > o.scoretemp ? 1 : (scoretemp == o.scoretemp ? 0 : -1);
    }
    
    private Docente docente = new Docente();
    private UnidadeCurricular unidadecurricular = new UnidadeCurricular();
    private int scoretemp = 0;
    private int lecionado = 0;

}
