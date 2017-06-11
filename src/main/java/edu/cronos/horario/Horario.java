package edu.cronos.horario;

import edu.cronos.Main;
import edu.cronos.entidades.Aula;
import edu.cronos.util.Tupla;
import edu.cronos.util.date.DateUtil;

import java.util.*;

/**
 * Classe wrapper da estrutura de dados que compoe um horario. Prove serviços de
 * extração de determinados tipos de dados do horario.
 *
 * @author sergio lisan e carlos melo
 */
public class Horario {

    /**
     * estrutura de dados que essa classe envolve
     */
    private Map<Date, Tupla<Aula, Aula>> horario;

    /**
     * Construtor padrao
     */
    public Horario() {
        this.horario = Horario.create();
    }

    /**
     * Construtor padrao
     */
    public Horario(List<Date> diasUteis) {
        this.horario = Horario.create(diasUteis);
    }

    /**
     * construtor vazio
     */
    public Horario(Map<Date, Tupla<Aula, Aula>> horario) {
        this.horario = horario;
    }

    /**
     * Cria uma instancia vazia padrao para Horario
     *
     * @return horario
     */
    public static Map<Date, Tupla<Aula, Aula>> create() {
        Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();
        for (Date diaUtil : Main.CALENDARIO.getDiasUteis()) {
            horario.put(diaUtil, new Tupla<>(Aula.VAZIA, Aula.VAZIA));
        }

        return horario;
    }

    /**
     * cria um horario a partir de uma lista sugerida de dias
     *
     * @param diasUteis
     * @return
     */
    public static Map<Date, Tupla<Aula, Aula>> create(List<Date> diasUteis) {
        Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();
        for (Date diaUtil : diasUteis) {
            horario.put(diaUtil, new Tupla<>(Aula.VAZIA, Aula.VAZIA));
        }

        return horario;
    }

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

        aulas.remove(Aula.VAZIA);
        return aulas;
    }

    public Set<Aula> getAulasSemestre(int semestre) {
        Set<Aula> aulas = new HashSet<>();

        for (Map<Date, Tupla<Aula, Aula>> horariomes : getSemestre(semestre).values()) {
            for (Date dia : horariomes.keySet()) {
                for (Object aula : horariomes.get(dia).list()) {
                    aulas.add((Aula) aula);
                }
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
     * divide o horario em meses
     *
     * @param horario
     * @return
     */
    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> separaHorarioEmMeses() {
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horarios = new TreeMap<>();
        TreeSet<Date> dias = new TreeSet<>(getHorario().keySet());

        for (Integer mes = DateUtil.getMes(dias.first()); mes <= DateUtil.getMes(dias.last()); mes++) {
            horarios.put(mes, new TreeMap<Date, Tupla<Aula, Aula>>());
        }

        for (Date dia : getHorario().keySet()) {
            horarios.get(DateUtil.getMes(dia)).put(dia, getHorario().get(dia));
        }

        return horarios;
    }

    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> getSemestre(int semestre) {
        if (semestre == 1) {
            return getMeses(2, 3, 4, 5, 6);
        } else {
            return getMeses(7, 8, 9, 10, 11, 12);
        }
    }

    /**
     * Retorna os primeiros horarios do ano
     *
     * @return
     */
    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> getPrimeiroSemestre() {
        return getMeses(2, 3, 4, 5, 6);
    }

    /**
     * Retorna os horarios dos primeiros meses do ano
     *
     * @return
     */
    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> getSegundoSemestre() {
        return getMeses(7, 8, 9, 10, 11, 12);
    }

    /**
     * retorna os horarios de determinados meses
     *
     * @param meses
     * @return
     */
    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> getMeses(int... meses) {
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horarioParticionado = separaHorarioEmMeses();
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horarioResultante = new TreeMap<>();

        for (int mes : meses) {
            horarioResultante.put(mes, horarioParticionado.get(mes));
        }

        return horarioResultante;
    }

    /**
     * verifica se o horario esta vazio e nao tem nenhuma aula alocada
     *
     * @return
     */
    public boolean isVazio() {
        for (Date dia : getHorario().keySet()) {
            Tupla<Aula, Aula> diaDeAula = getHorario().get(dia);
            if (!diaDeAula.getPrimeiro().equals(Aula.VAZIA) && !diaDeAula.getSegundo().equals(Aula.VAZIA)) {
                return false;
            }
        }

        return true;
    }

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
}
