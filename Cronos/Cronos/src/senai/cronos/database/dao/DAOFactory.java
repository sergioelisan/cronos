package senai.cronos.database.dao;

import java.sql.SQLException;
import senai.cronos.entidades.*;
import senai.util.date.Feriado;

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
     *
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static DAO getDao(Class c) throws ClassNotFoundException, SQLException {

        switch(c.getName() ) {
            case "senai.cronos.entidades.Docente": 
                return DAODocente.getInstance();
                
            case "senai.cronos.entidades.Laboratorio": 
                return DAOLaboratorio.getInstance();
                
            case "senai.cronos.entidades.Nucleo": 
                return DAONucleo.getInstance();
                
            case "senai.cronos.entidades.Turma": 
                return DAOTurma.getInstance();
                
            case "senai.cronos.entidades.Proficiencia": 
                return DAOProficiencia.getInstance();
                
            case "senai.cronos.entidades.UnidadeCurricular": 
                return DAOUnidadeCurricular.getInstance();
                
            case "senai.util.date.Feriado": 
                return DAOFeriado.getInstance();
                
            default:
                throw new IllegalArgumentException(c.getName() + " não possui DAO");
        }       
        
    }
    
}
