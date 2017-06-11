package edu.cronos.database.cache;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Nucleo;
import edu.cronos.entidades.UnidadeCurricular;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlos Melo e sergio lisan
 */
public final class UnidadesCurriculares implements Observador, Cache<UnidadeCurricular> {

    private static UnidadesCurriculares instance;
    private List<UnidadeCurricular> disciplinas;

    private UnidadesCurriculares() {
        try {
            DAOFactory.getDao(UnidadeCurricular.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnidadesCurriculares.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UnidadesCurriculares instance() {
        return instance;
    }

    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new UnidadesCurriculares();
    }

    /**
     * retorna uma disciplina identificada pelo seu nome
     *
     * @param nome
     * @return
     */
    public UnidadeCurricular buscaDisciplina(String nome) throws ClassNotFoundException, SQLException {
        for (UnidadeCurricular uc : get()) {
            if (uc.getNome().equals(nome)) {
                return uc;
            }
        }
        return null;
    }

    /**
     * metodo que retorna as disciplinas de uma turma
     *
     * @param t a ser pesquisada
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();
        for (UnidadeCurricular uc : get()) {
            if (uc.getNucleo().equals(nucleo)) {
                unidades.add(uc);
            }
        }
        // Ordena da maior carga horaria para a menor
        Collections.sort(unidades);
        Collections.reverse(unidades);

        return unidades;
    }

    /**
     * metodo que retorna as disciplinas de uma turma
     *
     * @param t a ser pesquisada
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo, int modulo) throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();
        for (UnidadeCurricular uc : get()) {
            if (uc.getNucleo().equals(nucleo) && uc.getModulo().equals(modulo)) {
                unidades.add(uc);
            }
        }
        // Ordena da maior carga horaria para a menor
        Collections.sort(unidades);
        Collections.reverse(unidades);

        return unidades;
    }

    /**
     * retorna as disciplinas de uma turma em um determinado modulo
     *
     * @param t      a ser pesquisada
     * @param modulo a ser pesquisado
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo, Integer modulo) throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();

        for (UnidadeCurricular uc : get()) {
            if (uc.getNucleo().equals(nucleo) && uc.getModulo().equals(modulo)) {
                unidades.add(uc);
            }
        }

        return unidades;
    }

    @Override
    public void update() {
        try {
            disciplinas = DAOFactory.getDao(UnidadeCurricular.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public List<UnidadeCurricular> get() {
        return disciplinas;
    }

    @Override
    public UnidadeCurricular get(Class c, Integer id) {
        for (UnidadeCurricular disciplina : disciplinas) {
            if (disciplina.getId().equals(id)) {
                return disciplina;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        disciplinas.clear();
    }
}
