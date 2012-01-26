package senai.cronos.entidades;

import java.util.Date;
import java.util.Map;
import java.util.Objects;
import java.util.TreeMap;
import senai.cronos.Main;
import senai.cronos.util.calendario.Calendario;
import senai.cronos.util.Tupla;

/**
 * Classe que representa um horario de uma turma. Cada turma tem um turno e um dicionario
 * de dias, em que o Dia é a chave e a unidadecurricular ministrada no momento é o elemento.
 * 
 * @author sergio lisan e carlos melo
 */
public class Horario {
    
    /**
     * construtor vazio
     */
    public Horario() {
        for (Date util : Main.calendario.getDiasUteis()) {
            horario.put(util, new Tupla<Aula, Aula>());
        }
        
    }
    
    /**
     * construtor que recebe uma turma e coloca os dias de acordo com o calendario do sistema
     * @param turma 
     */
    public Horario(Turma turma) {
        this();
        this.turma = turma;
        
        
    }
    
    /**
     * Construtor que recebe um horario pronto e uma turma
     * @param turma
     * @param horario 
     */
    public Horario(Turma turma, Map<Date, Tupla<Aula,Aula>> horario) {
        this.turma = turma;
        this.horario = horario;
    }

    public Map<Date, Tupla<Aula, Aula>> getHorario() {
        return horario;
    }

    public void setHorario(Map<Date, Tupla<Aula, Aula>> horario) {
        this.horario = horario;
    }

    public Turma getTurma() {
        return turma;
    }

    public void setTurma(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.turma, other.turma)) {
            return false;
        }
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.turma);
        hash = 29 * hash + Objects.hashCode(this.horario);
        return hash;
    }

    
    private Turma turma = new Turma();
    private Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();
}
