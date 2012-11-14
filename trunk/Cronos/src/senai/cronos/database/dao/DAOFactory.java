package senai.cronos.database.dao;

import java.sql.SQLException;
import senai.cronos.entidades.*;
import senai.cronos.util.calendario.Feriado;

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

        if (c.equals(Docente.class)) {
            return DAODocente.getInstance();

        } else if (c.equals(Horario.class)) {
            return DAOHorario.getInstance();

        } else if (c.equals(Laboratorio.class)) {
            return DAOLaboratorio.getInstance();

        } else if (c.equals(Nucleo.class)) {
            return DAONucleo.getInstance();

        } else if (c.equals(Turma.class)) {
            return DAOTurma.getInstance();

        } else if (c.equals(Proficiencia.class)) {
            return DAOProficiencia.getInstance();

        } else if (c.equals(UnidadeCurricular.class)) {
            return DAOUnidadeCurricular.getInstance();

        } else if (c.equals(Feriado.class)) {
            return DAOFeriado.getInstance();

        } else {
            throw new IllegalArgumentException("DAO Inexistente para a classe: " + c.getName());
        }
    }
}
