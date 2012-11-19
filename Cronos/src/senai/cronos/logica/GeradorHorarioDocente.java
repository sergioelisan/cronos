/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.logica;

import java.util.Date;
import senai.cronos.database.vectors.Turmas;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Tupla;

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
    public HorarioDocente generate(Docente doc) {
        HorarioDocente horarioDocente = doc.getHorarioDocente();
        
        for (Turma turma : Turmas.instance().getTurmas() ) {
            Turno turno = turma.getTurno();
            Horario horario = turma.getHorario();
            
            for (Date dia : horario.getHorario().keySet()) {
                Tupla<Aula, Aula> diaDeTrabalho = horario.getHorario().get(dia);
                if (diaDeTrabalho.getPrimeiro().getDocente().equals(doc)) {
                    horarioDocente.add(dia, turno, diaDeTrabalho.getPrimeiro(), Tupla.PRIMERA);
                } else if (diaDeTrabalho.getSegundo().getDocente().equals(doc)) {
                    horarioDocente.add(dia, turno, diaDeTrabalho.getSegundo(), Tupla.SEGUNDA);
                }                    
            }            
        }
        
        return horarioDocente;
    }
    
}
