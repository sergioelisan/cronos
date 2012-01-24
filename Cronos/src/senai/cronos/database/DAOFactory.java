package senai.cronos.database;

import java.sql.SQLException;
import java.util.Date;
import senai.cronos.database.dao.*;
import senai.cronos.entidades.*;

/**
 *
 * Fabrica de DAO's... Recebe uma classe, de uma entidade do sistema e retorna o 
 * seu respectivo DAO.
 * 
 * @author Sergio Lisan
 */
public class DAOFactory {
    
    /**
     * Recebe uma classe para retornar um DAO especifico
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static DAO getDao(Class c) throws ClassNotFoundException, SQLException {
        
        if (c.equals(Docente.class)) {
            return new DAODocente();
            
        } else if (c.equals(Horario.class)) {
            return new DAOHorario();
            
        } else if (c.equals(Laboratorio.class)) {
            return new DAOLaboratorio();
            
        } else if (c.equals(Nucleo.class)) {
            return new DAONucleo();
            
        } else if (c.equals(Turma.class)) {
            return new DAOTurma();
            
        } else if (c.equals(UnidadeCurricular.class)) {
            return new DAOUnidadeCurricular();
            
        } else if (c.equals(Date.class)) {
            return new DAOFeriado();
            
        } else
            return null;
    }
    
}
