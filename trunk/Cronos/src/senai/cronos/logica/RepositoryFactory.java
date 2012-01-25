/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.logica;

import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Laboratorio;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.util.Feriado;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class RepositoryFactory {
    
    public static Repository getRepository(Class c) {
        
        if (c.equals(Docente.class)) {
            return Docentes.instance();
            
        } else if (c.equals(Horario.class)) {
            return Horarios.instance();
            
        } else if (c.equals(Laboratorio.class)) {
            return Laboratorios.instance();
            
        } else if (c.equals(Nucleo.class)) {
            return Nucleos.instance();
            
        } else if (c.equals(Turma.class)) {
            return Turmas.instance();
            
        } else if (c.equals(UnidadeCurricular.class)) {
            return Disciplinas.instance();
            
        } else if (c.equals(Feriado.class)) {
            return Feriados.instance();
            
        } else
            return null;
    }
}
