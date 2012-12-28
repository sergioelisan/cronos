package senai.cronos.entidades;

import senai.cronos.horario.Horario;
import java.util.Date;
import java.util.GregorianCalendar;
import java.util.Objects;

/**
 *
 * Classe que representa uma turma no sistema
 *
 * @author Carlos Melo e Sergio Lisan
 */
public class Turma {

    public Turma() {
    }

    public Date getEntrada() {
        return entrada;
    }

    public void setEntrada(Date entrada) {
        this.entrada = entrada;
    }

    public int getHabilitacao() {
        return habilitacao;
    }

    public void setHabilitacao(int habilitacao) {
        this.habilitacao = habilitacao;
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

    public Nucleo getNucleo() {
        return nucleo;
    }

    public void setNucleo(Nucleo nucleo) {
        this.nucleo = nucleo;
    }

    public Date getSaida() {
        return saida;
    }

    public void setSaida(Date saida) {
        this.saida = saida;
    }

    public Turno getTurno() {
        return turno;
    }

    public void setTurno(Turno turno) {
        this.turno = turno;
    }

    /**
     * @return the horario
     */
    public Horario getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Horario horario) {
        this.horario = horario;
    }

    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Turma other = (Turma) obj;
        if (!Objects.equals(this.id, other.id)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (!Objects.equals(this.nucleo, other.nucleo)) {
            return false;
        }
        if (!Objects.equals(this.entrada, other.entrada)) {
            return false;
        }
        if (!Objects.equals(this.saida, other.saida)) {
            return false;
        }
        if (this.turno != other.turno) {
            return false;
        }
        if (this.habilitacao != other.habilitacao) {
            return false;
        }
        return true;
    }

    public int hashCode() {
        int hash = 3;
        hash = 67 * hash + Objects.hashCode(this.id);
        hash = 67 * hash + Objects.hashCode(this.nome);
        hash = 67 * hash + Objects.hashCode(this.nucleo);
        hash = 67 * hash + Objects.hashCode(this.entrada);
        hash = 67 * hash + Objects.hashCode(this.saida);
        hash = 67 * hash + (this.turno != null ? this.turno.hashCode() : 0);
        hash = 67 * hash + this.habilitacao;
        return hash;
    }

    public String toString() {
        return "Turma{"
                + "id = " + id
                + ", nome = " + nome
                + ", nucleo = " + nucleo
                + ", entrada = " + entrada
                + ", saida = " + saida
                + ", turno = " + turno
                + ", habilitacao = " + habilitacao + '}';
    }
    /**
     * identificador
     */
    private Integer id = 0;
    /**
     * nome da turma
     */
    private String nome = "";
    /**
     * nucleo que ela pertence
     */
    private Nucleo nucleo = new Nucleo();
    /**
     * data de entrada da turma
     */
    private Date entrada = new Date();
    /**
     * saida da turma, setada inicialmente no EPOCH, para evitar o
     * nullpointerexception
     */
    private Date saida = new GregorianCalendar(2100, 0, 1).getTime();
    /**
     * Turno da turma
     */
    private Turno turno = Turno.MANHA;
    /**
     * horario da turma
     */
    private Horario horario = new Horario();
    /**
     * habilitacao
     */
    private int habilitacao = 0;
}
