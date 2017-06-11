/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cronos.horario;

import edu.cronos.CronosAPI;
import edu.cronos.entidades.Aula;
import edu.cronos.entidades.Docente;
import edu.cronos.entidades.Turma;
import edu.cronos.entidades.Turno;
import edu.cronos.util.Tupla;

import java.util.Date;

/**
 * Classe responsável por gerar o horário do docente.
 *
 * @author Sergio
 */
public class GeradorHorarioDocente {

    /**
     * Recebe um docente como parametro e retorna o Horario dele
     *
     * @param doc
     * @return
     */
    public HorarioDocente generate(Docente doc) throws Exception {
        HorarioDocente horarioDocente = HorarioDocente.create(doc);

        for (Turma turma : CronosAPI.<Turma>get(Turma.class)) {
            Turno turno = turma.getTurno();
            Horario wrapper = turma.getHorario();

            for (Date dia : wrapper.getHorario().keySet()) {
                Tupla<Aula, Aula> diaDeTrabalho = wrapper.getHorario().get(dia);

                if (diaDeTrabalho.getPrimeiro().getDocente().equals(doc)) {
                    horarioDocente.add(dia, turno, diaDeTrabalho.getPrimeiro(), Tupla.PRIMEIRA);
                } else if (diaDeTrabalho.getSegundo().getDocente().equals(doc)) {
                    horarioDocente.add(dia, turno, diaDeTrabalho.getSegundo(), Tupla.SEGUNDA);
                }

            }
        }

        return horarioDocente;
    }
}
