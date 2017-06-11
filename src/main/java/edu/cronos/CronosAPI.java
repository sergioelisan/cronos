package edu.cronos;

import edu.cronos.database.cache.*;
import edu.cronos.database.dao.DAO;
import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.*;
import edu.cronos.properties.Preferencias;
import edu.cronos.util.Observador;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;

/**
 * Interface Pública do EDU Cronos. Métodos que acessam os subsistemas da
 * aplicação e oferecem serviços para o próprio sistema e apps clientes.
 *
 * @author Sergio Lisan e Carlos melo
 */
public class CronosAPI {

    private CronosAPI() {
    }

    /**
     * encerra o banco de dados e sai da aplicacao
     */
    public static void quit() {
        Main.quit();
    }

    /* 
     * 
     * API de Persistencia (usa o Cache para acelerar a aplicação) 
     * 
     */

    /**
     * Adiciona um objeto de uma entidade de classe T ao banco de dados.
     *
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
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void remove(Class c, Serializable id) throws ClassNotFoundException, SQLException {
        DAO dao = DAOFactory.getDao(c);
        dao.remove(id);
    }

    /**
     * Altera um objeto da classe T no banco de dados
     *
     * @param objeto
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static void update(Object objeto) throws ClassNotFoundException, SQLException {
        DAO dao = DAOFactory.getDao(objeto.getClass());
        dao.update(objeto);
    }

    /**
     * retorna uma lista de objetos de uma determinada classe
     *
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static <T> List<T> get(Class c) throws ClassNotFoundException, SQLException {
        Cache<T> repositorio = CacheFactory.getRepository(c);
        return repositorio.get();
    }

    /**
     * Retorna um determinado objeto obtido do banco identificado pela sua ID
     *
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static <T> T get(Class c, Integer id) throws ClassNotFoundException, SQLException {
        Cache<T> repositorio = CacheFactory.getRepository(c);
        return repositorio.get(c, id);
    }

    /**
     * @param c
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static <T> void clear(Class c) throws ClassNotFoundException, SQLException {
        Cache<T> repositorio = CacheFactory.getRepository(c);
        repositorio.clear();
    }

    /*
     * 
     * API de assinatura de eventos na base de dados
     * 
     */
    public static void subscribe(Class assistido, Observador assinante) throws Exception {
        DAOFactory.getDao(assistido).registra(assinante);
        assinante.update();
    }

    /*
     * 
     * API de busca (usa o Cache para acelerar a aplicacão)
     * 
     */

    /**
     * Retorna um docente buscando pelo nome
     *
     * @param nome
     * @return
     */
    public static Docente buscaDocenteNome(String nome) throws ClassNotFoundException, SQLException {
        return Docentes.instance().buscaDocenteNome(nome);
    }

    /**
     * procura por um docente por sua matricula
     *
     * @param matricula
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Docente buscaDocenteMatricula(String matricula) throws ClassNotFoundException, SQLException {
        return Docentes.instance().buscaDocenteMatricula(matricula);
    }

    public static boolean existeDocente(String matricula) throws ClassNotFoundException, SQLException {
        return Docentes.instance().existeDocente(matricula);
    }

    /**
     * retorna os docentes de um determinado nucleo.
     *
     * @param nucleo
     * @return
     */
    public static List<Docente> buscaDocentes(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return Docentes.instance().buscaDocentes(nucleo);
    }

    /**
     * retorna uma lista com os melhores docentes para uma determinada
     * disciplina
     * <p>
     * c
     *
     * @return
     * @throws Exception
     */
    public static List<Docente> bestDocentes(UnidadeCurricular uc) throws Exception {
        return Docentes.instance().bestDocentes(uc);
    }

    /**
     * Retorna uma disciplina (Unidade Curricular) identificada pelo seu nome
     *
     * @param nome
     * @return
     */
    public static UnidadeCurricular buscaDisciplina(String nome) throws ClassNotFoundException, SQLException {
        return UnidadesCurriculares.instance().buscaDisciplina(nome);
    }

    /**
     * Retorna disciplinas de um determinado nucleo
     *
     * @param nucleo
     * @return
     */
    public static List<UnidadeCurricular> buscaDisciplinas(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return UnidadesCurriculares.instance().buscaDisciplina(nucleo);
    }

    /**
     * Retorna uma lista de disciplinas para cada nucleo e modulo espeficidados
     *
     * @param nucleo
     * @param modulo
     * @return
     */
    public static List<UnidadeCurricular> buscaDisciplinas(Nucleo nucleo, Integer modulo) throws ClassNotFoundException, SQLException {
        return UnidadesCurriculares.instance().buscaDisciplina(nucleo, modulo);
    }

    /**
     * Retorna uma turma identificada pelo nome
     *
     * @param nome
     * @return
     */
    public static Turma buscaTurma(String nome) throws ClassNotFoundException, SQLException {
        return Turmas.instance().buscaTurma(nome);
    }

    public static Integer getIDTurma() throws ClassNotFoundException, SQLException {
        return Turmas.instance().getIDTurma();
    }

    /**
     * busca turmas a partir de um nucleo especifico
     *
     * @param nucleo
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static List<Turma> buscaTurma(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        return Turmas.instance().buscaTurma(nucleo);
    }

    public static Nucleo buscaNucleo(String nome) throws ClassNotFoundException, SQLException {
        for (Nucleo nc : Nucleos.instance().get()) {
            if (nc.getNome().equals(nome)) {
                return nc;
            }
        }

        return null;
    }

    /**
     * retorna um laboratorio pelo nome
     *
     * @param nome
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Laboratorio buscaLab(String nome) throws ClassNotFoundException, SQLException {
        for (Laboratorio lab : Laboratorios.instance().get()) {
            if (lab.getNome().equals(nome)) {
                return lab;
            }
        }

        return null;
    }

    /* 
     * 
     * API de Utilidades
     * 
     */
    public static int getQuantidadeDiasLetivos() {
        return Main.CALENDARIO.getDiasUteis().size();
    }

    /* 
     * 
     * API de preferencias do Sistema
     * 
     */

    /**
     * retorna o numero de aulas por dia
     *
     * @return
     */
    public static int getAulasDia() {
        return Preferencias.instance().getAulasDia();
    }

    /**
     * set os dias de aula
     *
     * @param aulas
     */
    public static void setAulasDia(int aulas) {
        Preferencias.instance().setAulasDia(aulas);
    }

    /**
     * retorna se ha ou nao alternacia entre horarios
     *
     * @return
     */
    public static int getAlternancia() {
        return Preferencias.instance().getAlternancia();
    }

    /**
     * retorna o dia do inicio do calendario
     *
     * @return
     * @throws ParseException
     */
    public static Date getInicioCalendario() throws ParseException {
        return Preferencias.instance().getInicioCalendario();
    }

    /**
     * seta o primeiro dia do calendario
     *
     * @param inicio
     */
    public static void setInicioCalendario(Date inicio) {
        Preferencias.instance().setInicioCalendario(inicio);
    }

    /**
     * retorna o dia do fim do calendario
     *
     * @return
     * @throws ParseException
     */
    public static Date getFimCalendario() throws ParseException {
        return Preferencias.instance().getFimCalendario();
    }

    /**
     * configura o ultimo dia do calendario
     *
     * @param fim
     */
    public static void setFimCalendario(Date fim) {
        Preferencias.instance().setFimCalendario(fim);
    }

    /**
     * seta a alternancia dos horarios
     *
     * @param alternancia
     */
    public static void setAlternacia(int alternancia) {
        Preferencias.instance().setAlternancia(alternancia);
    }
}
