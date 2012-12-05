package senai.cronos.database.cache;

import senai.cronos.entidades.*;
import senai.util.date.Feriado;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class CacheFactory {

    public static Cache getRepository(Class c) {

        switch(c.getName() ) {
            case "senai.cronos.entidades.Docente": 
                return Docentes.instance();
                
            case "senai.cronos.entidades.Laboratorio": 
                return Laboratorios.instance();
                
            case "senai.cronos.entidades.Nucleo": 
                return Nucleos.instance();
                
            case "senai.cronos.entidades.Turma": 
                return Turmas.instance();
                            
            case "senai.cronos.entidades.UnidadeCurricular": 
                return UnidadesCurriculares.instance();
                
            case "senai.util.date.Feriado": 
                return Feriados.instance();
                
            default:
                throw new IllegalArgumentException(c.getName() + " não possui Repository");
        } 
        
    }
    
}
