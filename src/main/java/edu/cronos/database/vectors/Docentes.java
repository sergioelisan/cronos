package edu.cronos.database.vectors;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Docente;
import edu.cronos.entidades.Nucleo;
import edu.cronos.entidades.Proficiencia;
import edu.cronos.entidades.UnidadeCurricular;
import edu.cronos.util.Observador;
import edu.cronos.util.date.DateUtil;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlos Melo e Sergio Lisan
 */
public final class Docentes implements Observador, Repository<Docente> {

    private static Docentes instance = new Docentes();
    private List<Docente> docentes;

    private Docentes() {
        try {
            DAOFactory.getDao(Docente.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Docentes instance() {
        return instance;
    }

    /**
     * retorna docentes de um determinado nucleo
     *
     * @param nucleo
     * @return
     */
    public List<Docente> buscaDocentes(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        List<Docente> _docentes = new ArrayList<>();
        for (Docente doc : getDocentes()) {
            if (doc.getNucleo().equals(nucleo)) {
                _docentes.add(doc);
            }
        }
        return _docentes;
    }

    /**
     * Busca um docente pelo nome
     *
     * @param nome
     * @return
     */
    public Docente buscaDocente(String nome) throws ClassNotFoundException, SQLException {
        for (Docente dc : getDocentes()) {
            if (dc.getNome().equals(nome)) {
                return dc;
            }
        }
        return null;
    }

    /**
     * Calcula o Score final de um docente de acordo com uma Disciplina
     *
     * @param docente
     * @param uc
     * @return
     */
    public double getScoreFinal(Docente docente, UnidadeCurricular uc) {
        double score = calcScore(docente);
        double scoreTemp = calcScoreTemp(docente, uc);
        double scoreOcupacao = 0;

        return score - (scoreTemp + scoreOcupacao / 3);
    }

    /**
     * Calcula o score, atributo, de um docente. Aqui, o score é calculado
     * levando em consideração a sua formação e experiência, e dois pesos:
     * <p>
     * experiência * 3 + formação * 2
     *
     * @return valor do score do docente
     */
    private double calcScore(Docente docente) {
        double score;

        int semestres = DateUtil.getSemestres(docente.getContratacao());
        int formacao = docente.getFormacao().ordinal();

        score = semestres * 3 + formacao * 2;
        docente.setScore((int) score);

        return score;
    }

    /**
     * Busca docente pela matricula
     *
     * @return existe
     * @Param matricula
     */
    public boolean existeDocente(String matricula) throws ClassNotFoundException, SQLException {
        for (Docente dc : getDocentes()) {
            if (dc.getMatricula().equals(Integer.parseInt(matricula))) {
                return true;
            }
        }
        return false;
    }

    /**
     * Retorna o scoreTemp de um docente em uma determinada disciplina
     *
     * @return scoreTempo de um docente em uma disciplina
     */
    private double calcScoreTemp(Docente dc, UnidadeCurricular disciplina) {
        Integer scoreTemp = null;
        List<Proficiencia> proficiencias = dc.getProficiencias();
        for (Proficiencia prof : proficiencias) {
            if (disciplina.equals(prof.getDisciplina())) {
                scoreTemp = prof.getScoreTemp();
            }
        }
        return Double.valueOf(scoreTemp);
    }

    @Override
    public void update() {
        try {
            docentes = DAOFactory.getDao(Docente.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return the docentes
     */
    public List<Docente> getDocentes() {
        return docentes;
    }

    @Override
    public List<Docente> get() {
        return getDocentes();
    }

    @Override
    public Docente get(Class c, Integer id) {
        for (Docente docente : docentes) {
            if (docente.getMatricula().equals(id)) {
                return docente;
            }
        }
        return null;
    }
}
