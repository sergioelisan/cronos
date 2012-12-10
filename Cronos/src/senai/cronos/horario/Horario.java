package senai.cronos.horario;

import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.Set;
import java.util.TreeMap;
import senai.cronos.Main;
import senai.cronos.entidades.Aula;
import senai.util.Tupla;

/**
 * Classe wrapper da estrutura de dados que compoe um horario. Prove serviços de
 * extração de determinados tipos de dados do horario.
 *
 * @author sergio lisan e carlos melo
 */
public class Horario {

    /**
     * Cria uma instancia vazia padrao para Horario
     *
     * @return horario
     */
    public static Map<Date, Tupla<Aula, Aula>> create() {
        Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();
        for (Date diaUtil : Main.CALENDARIO.getDiasUteis()) {
            horario.put(diaUtil, new Tupla<>(Aula.PADRAO, Aula.PADRAO));
        }

        return horario;
    }

    /**
     * Construtor padrao
     */
    public Horario() {
        this.horario = Horario.create();
    }

    /**
     * construtor vazio
     */
    public Horario(Map<Date, Tupla<Aula, Aula>> horario) {
        this.horario = horario;
    }

    /*
     * Serviços prestados por esta classe
     */

    /**
     * Retorna instancias das aulas lecionadas dentro do horario
     */
    public Set<Aula> getAulas() {
        Set<Aula> aulas = new HashSet<>();

        for (Date dia : horario.keySet()) {
            for (Object aula : horario.get(dia).list()) {
                aulas.add((Aula) aula);
            }
        }

        return aulas;
    }

    /**
     * Retorna os dias de ocorrencia de uma determinada aula
     *
     * @param aula a ser pesquisada
     * @return dias sendo o conjunto de dias em que aula esta/foi lecionada.
     */
    public Map<Date, Tupla<Boolean, Boolean>> getDiasLecionados(Aula aula) {
        Map<Date, Tupla<Boolean, Boolean>> diasLecionados = new TreeMap<>();

        for (Date dia : horario.keySet()) {
            if (horario.get(dia).contains(aula)) {
                Tupla<Boolean, Boolean> aulas = new Tupla<>(Boolean.FALSE, Boolean.FALSE);
                if (horario.get(dia).getPrimeiro().equals(aula)) {
                    aulas.setPrimeiro(Boolean.TRUE);
                }
                if (horario.get(dia).getSegundo().equals(aula)) {
                    aulas.setSegundo(Boolean.TRUE);
                }

                diasLecionados.put(dia, aulas);
            }

        }

        return diasLecionados;
    }

    /**
     * verifica se o horario esta vazio e nao tem nenhuma aula alocada
     * @return
     */
    public boolean isVazio() {
        for (Date dia : getHorario().keySet() ) {
            Tupla<Aula, Aula> diaDeAula = getHorario().get(dia);
            if (!diaDeAula.getPrimeiro().equals(Aula.PADRAO) && !diaDeAula.getSegundo().equals(Aula.PADRAO) )
                return false;
        }

        return true;
    }

    /*
     * equals, hashcode e campos da instancia
     */
    /**
     * @return the horario
     */
    public Map<Date, Tupla<Aula, Aula>> getHorario() {
        return horario;
    }

    /**
     * @param horario the horario to set
     */
    public void setHorario(Map<Date, Tupla<Aula, Aula>> horario) {
        this.horario = horario;
    }

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
        final Horario other = (Horario) obj;
        if (!Objects.equals(this.horario, other.horario)) {
            return false;
        }
        return true;
    }
    // </editor-fold>

    @Override
    // hashcode()
    // <editor-fold defaultstate="collapsed">
    public int hashCode() {
        int hash = 7;
        hash = 29 * hash + Objects.hashCode(this.getHorario());
        return hash;
    }
    // </editor-fold>
    /**
     * estrutura de dados que essa classe envolve
     */
    private Map<Date, Tupla<Aula, Aula>> horario;
}
