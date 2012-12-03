/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.horario;

import java.util.Date;
import senai.cronos.Fachada;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.Turno;
import senai.util.Tupla;

/**
 *
 * Classe responsável por gerar o horário do docente.
 *
 * @author Sergio
 */
public class GeradorHorarioDocente {

    /**
     * Recebe um docente como parametro e retorna o Horario dele
     * @param doc
     * @return
     */
    public HorarioDocente generate(Docente doc) throws Exception {
        HorarioDocente horarioDocente = HorarioDocente.create();

        for (Turma turma : Fachada.<Turma>get(Turma.class)) {
            Turno turno = turma.getTurno();
            Horario horario = turma.getHorarioWrapper();

            for (Date dia : horario.getHorario().keySet()) {
                Tupla<Aula, Aula> diaDeTrabalho = horario.getHorario().get(dia);

                if (diaDeTrabalho.getPrimeiro().getDocente().equals(doc))
                    horarioDocente.add(dia, turno, diaDeTrabalho.getPrimeiro(), Tupla.PRIMEIRA);

                else if (diaDeTrabalho.getSegundo().getDocente().equals(doc))
                    horarioDocente.add(dia, turno, diaDeTrabalho.getSegundo(), Tupla.SEGUNDA);

            }
        }

        return horarioDocente;
    }

}
