package senai.cronos.logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.util.Observador;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.util.calendario.DateUtil;

/**
 *
 * @author Carlos Melo e Sergio Lisan
 */
public final class Docentes implements Observador, Repository<Docente> {
    
    private List<Docente> docentes;

    private static Docentes instance = new Docentes();
    
    public static Docentes instance() {
        return instance;
    }
    
    private Docentes() {
        try {
            DAOFactory.getDao(Docente.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Docentes.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * retorna docentes de um determinado nucleo
     * @param nucleo
     * @return 
     */
    public List<Docente> buscaDocentes(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        List<Docente> _docentes = new ArrayList<>();
        for(Docente doc : getDocentes()) {
            if(doc.getNucleo().equals(nucleo))
                _docentes.add(doc);
        }
        return _docentes;
    }

    /**
     * Busca um docente pelo nome
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
     * @param docente
     * @param uc
     * @return 
     */
    public double getScoreFinal(Docente docente, UnidadeCurricular uc) {
        double score = calcScore(docente);
        double scoreTemp = calcScoreTemp(docente, uc);
        double scoreOcupacao = calcScoreOcupacao(docente);

        return score - (scoreTemp + scoreOcupacao / 3);
    }

    /**
     * Calcula o score, atributo, de um docente. Aqui, o score é calculado levando em consideração a sua formação e
     * experiência, e dois pesos:
     * 
     * experiência * 3 + formação * 2
     * 
     * @param nomeDocente a ser calculado o score
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
     * Gera uma pontuacao de acordo com a ocupacao do docente
     * @param docente
     * @return 
     */
    private double calcScoreOcupacao(Docente docente) {
        double ocupacao = docente.percentualOcupacao();

        if (ocupacao >= 1 && ocupacao <= 10) {
            return 1;
        } else if (ocupacao > 10 && ocupacao <= 20) {
            return 2;
        } else if (ocupacao > 20 && ocupacao <= 30) {
            return 3;
        } else if (ocupacao > 30 && ocupacao <= 40) {
            return 4;
        } else if (ocupacao > 40 && ocupacao <= 50) {
            return 5;
        } else if (ocupacao > 50 && ocupacao <= 66) {
            return 6;
        }

        return 0;
    }

    /**
     * Retorna o scoreTemp de um docente em uma determinada disciplina
     * @param nomeDocente a ser pesquisado
     * @param nomeDisciplina a ser pesquisado
     * @return scoreTempo de um docente em uma disciplina
     */
    private double calcScoreTemp(Docente dc, UnidadeCurricular disciplina) {
        Integer scoreTemp = null;
        List<Proficiencia> proficiencias = dc.getProficiencias();
        for (Proficiencia prof : proficiencias) {
            if (disciplina.equals(prof.getDisciplina())) {
                scoreTemp = prof.getNivel();
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
        for(Docente docente : docentes)
            if(docente.getMatricula().equals(id))
                return docente;
        return null;
    }

    
}
