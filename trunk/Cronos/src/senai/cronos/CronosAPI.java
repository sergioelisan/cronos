package senai.cronos;

import java.io.Serializable;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import senai.cronos.database.dao.DAO;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.database.cache.UnidadesCurriculares;
import senai.cronos.database.cache.Docentes;
import senai.cronos.database.cache.Laboratorios;
import senai.cronos.database.cache.Nucleos;
import senai.cronos.database.cache.Cache;
import senai.cronos.database.cache.CacheFactory;
import senai.cronos.database.cache.Turmas;
import senai.cronos.entidades.*;
import senai.cronos.properties.Preferencias;
import senai.util.Observador;

/**
 *
 * Interface Pública do SENAI Cronos. Métodos que acessam os subsistemas da
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
     *
     * @param <T>
     * @param u
     * @param id
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
     * retorna uma lista de objetos de uma determinada classe
     *
     * @param <T>
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
     * @param <T>
     * @param c
     * @param id
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static <T> T get(Class c, Integer id) throws ClassNotFoundException, SQLException {
        Cache<T> repositorio = CacheFactory.getRepository(c);
        return repositorio.get(c, id);
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
     * @param matricula
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    public static Docente buscaDocenteMatricula(String matricula) throws ClassNotFoundException, SQLException {
        return Docentes.instance().buscaDocenteMatricula(matricula);
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
     * retorna o dia do fim do calendario
     *
     * @return
     * @throws ParseException
     */
    public static Date getFimCalendario() throws ParseException {
        return Preferencias.instance().getFimCalendario();
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
     * seta a alternancia dos horarios
     *
     * @param alternancia
     */
    public static void setAlternacia(int alternancia) {
        Preferencias.instance().setAlternancia(alternancia);
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
     * configura o ultimo dia do calendario
     *
     * @param fim
     */
    public static void setFimCalendario(Date fim) {
        Preferencias.instance().setFimCalendario(fim);
    }
}
