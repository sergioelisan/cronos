package senai.cronos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import senai.cronos.database.DAOFactory;
import senai.cronos.database.dao.DAO;
import senai.cronos.database.dao.DAOLaboratorio;
import senai.cronos.database.dao.DAONucleo;
import senai.cronos.entidades.*;
import senai.cronos.logica.Disciplinas;
import senai.cronos.logica.Docentes;
import senai.cronos.logica.Preferencias;
import senai.cronos.logica.Turmas;

/**
 *
 * Classe que representa uma fachada para esconder o funcionamento do subsistema.
 * Abstrai toda a complexidade para as camadas superiores.
 * 
 * @author Sergio Lisan e Carlos melo
 */
public class Fachada {
    
    private Fachada() {
        
    }

    /**
     * Adiciona um objeto de uma entidade de classe T ao banco de dados.
     * @param <T>
     * @param objeto
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void add(Object objeto) throws ClassNotFoundException, SQLException {
        DAO dao = DAOFactory.getDao(objeto.getClass());
        dao.add(objeto);
    }

    /**
     * Remove um objeto de uma classe T, pela sua id, do banco de dados
     * @param <T>
     * @param u
     * @param id
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void remove(Class c, Integer id) throws ClassNotFoundException, SQLException {
        DAO dao = DAOFactory.getDao(c);
        dao.remove(id);
    }

    /**
     * Altera um objeto da classe T no banco de dados
     * @param <T>
     * @param objeto
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static void update(Object objeto) throws ClassNotFoundException, SQLException {
        DAO dao = DAOFactory.getDao(objeto.getClass());
        dao.update(objeto);
    }

    /**
     * 
     * @param <T>
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static <T> List<T> get(Class c) throws ClassNotFoundException, SQLException {
        DAO<T> dao = DAOFactory.getDao(c);
        return dao.get();
    }

    /**
     * Retorna um determinado objeto obtido do banco identificado pela sua ID
     * @param <T>
     * @param c
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static <T> T get(Class c, Integer id) throws ClassNotFoundException, SQLException {
        DAO<T> dao = DAOFactory.getDao(c);
        return dao.get(id);
    }

    /**
     * Retorna um docente buscando pelo nome
     * @param nome
     * @return 
     */
    public static Docente buscaDocente(String nome) throws ClassNotFoundException, SQLException {
        return new Docentes().buscaDocente(nome);
    }

    /**
     * retorna os docentes de um determinado nucleo.
     * @param nucleo
     * @return 
     */
    public static List<Docente> buscaDocente(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return new Docentes().buscaDocentes(nucleo);
    }

    /**
     * Retorna o melhor docente de uma unidade curricular
     * @param uc
     * @return 
     */
    public static Docente melhorDocente(UnidadeCurricular uc) throws ClassNotFoundException, SQLException {
        return new Disciplinas().melhorDocente(uc);
    }

    /**
     * Retorna uma disciplina (Unidade Curricular) identificada pelo seu nome
     * @param nome
     * @return 
     */
    public static UnidadeCurricular buscaDisciplina(String nome) throws ClassNotFoundException, SQLException {
        return new Disciplinas().buscaDisciplina(nome);
    }

    /**
     * Retorna disciplinas de um determinado nucleo
     * @param nucleo
     * @return 
     */
    public static List<UnidadeCurricular> buscaDisciplinas(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return new Disciplinas().buscaDisciplina(nucleo);
    }

    /**
     * Retorna uma lista de disciplinas para cada nucleo e modulo espeficidados
     * @param nucleo
     * @param modulo
     * @return 
     */
    public static List<UnidadeCurricular> buscaDisciplinas(Nucleo nucleo, Integer modulo) throws ClassNotFoundException, SQLException {
        return new Disciplinas().buscaDisciplina(nucleo, modulo);
    }

    /**
     * Retorna uma turma identificada pelo nome
     * @param nome
     * @return 
     */
    public static Turma buscaTurma(String nome) throws ClassNotFoundException, SQLException {
        return new Turmas().buscaTurma(nome);
    }
    
    /**
     * busca turmas a partir de um nucleo especifico
     * @param nucleo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static List<Turma> buscaTurma(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return new Turmas().buscaTurma(nucleo);
    }
    
    /**
     * retorna um nucleo pelo nome
     * @param nome
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Nucleo buscaNucleo(String nome) throws ClassNotFoundException, SQLException{
        for(Nucleo nc : new DAONucleo().get()) {
            if(nc.getNome().equals(nome))
                return nc;
        }
        
        return null;
    }
    
    /**
     * retorna um laboratorio pelo nome
     * @param nome
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Laboratorio buscaLab(String nome) throws ClassNotFoundException, SQLException{
        for(Laboratorio lab : new DAOLaboratorio().get()) {
            if(lab.getNome().equals(nome))
                return lab;
        }
        
        return null;
    }
    
    public static int getAulasDia() {
        return Preferencias.instance().getAulasDia();
    }
    
    public static int getAlternancia() {
        return Preferencias.instance().getAlternancia();
    }
    
    public static Date getInicioCalendario() throws ParseException {
        return Preferencias.instance().getInicioCalendario();
    }
    
    public static Date getFimCalendario() throws ParseException {
        return Preferencias.instance().getFimCalendario();
    }
    
    public static void setAulasDia(int aulas) {
        Preferencias.instance().setAulasDia(aulas);
    }
    
    public static void setAlternacia(int alternancia) {
        Preferencias.instance().setAlternancia(alternancia);
    }
    
    public static void setInicioCalendario(Date inicio) {
        Preferencias.instance().setInicioCalendario(inicio);
    }
    
    public static void setFimCalendario(Date fim) {
        Preferencias.instance().setFimCalendario(fim);
    }
}
