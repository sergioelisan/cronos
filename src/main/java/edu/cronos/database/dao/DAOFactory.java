package edu.cronos.database.dao;

import java.sql.SQLException;

/**
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

        switch (c.getName()) {
            case "edu.cronos.entidades.Docente":
                return DAODocente.getInstance();

            case "edu.cronos.entidades.Laboratorio":
                return DAOLaboratorio.getInstance();

            case "edu.cronos.entidades.Nucleo":
                return DAONucleo.getInstance();

            case "edu.cronos.entidades.Turma":
                return DAOTurma.getInstance();

            case "edu.cronos.entidades.Proficiencia":
                return DAOProficiencia.getInstance();

            case "edu.cronos.entidades.UnidadeCurricular":
                return DAOUnidadeCurricular.getInstance();

            case "edu.cronos.util.date.Feriado":
                return DAOFeriado.getInstance();

            default:
                throw new IllegalArgumentException(c.getName() + " não possui DAO");
        }

    }
}
