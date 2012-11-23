/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.horario;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import senai.cronos.Main;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Turno;
import senai.util.Tupla;

/**
 *
 * @author Sergio
 */
public class HorarioDocente {

    private HorarioDocente(Map<Date, Map<Turno, Tupla<Aula, Aula>>> horario) {
        this.horario = horario;
    }

    /**
     * Gera uma estrutura vazia, pronta para receber dados
     *
     * @return horarioDocente
     */
    public static HorarioDocente create() {
        Map<Date, Map<Turno, Tupla<Aula, Aula>>> horario = new HashMap<>();

        for (Date dia : Main.CALENDARIO.getDiasUteis()) {
            Map<Turno, Tupla<Aula, Aula>> diaDeTrabalho = new HashMap<>();
            diaDeTrabalho.put(Turno.MANHA, new Tupla<>(Aula.PADRAO, Aula.PADRAO));
            diaDeTrabalho.put(Turno.TARDE, new Tupla<>(Aula.PADRAO, Aula.PADRAO));
            diaDeTrabalho.put(Turno.NOITE, new Tupla<>(Aula.PADRAO, Aula.PADRAO));
            horario.put(dia, diaDeTrabalho);
        }

        return new HorarioDocente(horario);
    }

    /**
     * Cria uma ocupação para o docente
     *
     * @param dia
     * @param turno
     * @param aula
     * @param half
     */
    public void add(Date dia, Turno turno, Aula aula, Integer half) {
        horario.get(dia).get(turno).insert(aula, half);
    }

    /**
     * Remove uma aula para o docente
     * @param dia
     * @param turno
     * @param half
     */
    public void remove(Date dia, Turno turno, Integer half) {
        horario.get(dia).get(turno).insert(Aula.create(), half);
    }

    /**
     * Retorna uma aula em um determinado momento do dia
     * @param dia
     * @param turno
     * @param half
     * @return
     */
    public Aula getAula(Date dia, Turno turno, Integer half) {
        return (Aula) horario.get(dia).get(turno).get(half);
    }

    /**
     * Retorna o horário
     *
     * @return horario
     */
    public Map<Date, Map<Turno, Tupla<Aula, Aula>>> getHorario() {
        return horario;
    }

    /**
     * Calcula a percentagem de ocupação do docente
     * @return
     */
    public double getPercentualOcupacao() {
        // TODO Desenvolver a logica de calculo da ocupacao de um docente
        return 0.1;
    }

    /**
     * Verifica se o docente ja possui uma ocupacao em determinado horario
     * @param tn
     * @param dia
     * @param metade
     * @return
     */
    public boolean isDisponivel(Date dia, Turno tn, Integer metade) {
        return this.getAula(dia, tn, metade).equals(Aula.PADRAO);
    }

    /**
     * Estrutura de dados que armazena o horario do docente
     */
    private Map<Date, Map<Turno, Tupla<Aula, Aula>>> horario;
}
