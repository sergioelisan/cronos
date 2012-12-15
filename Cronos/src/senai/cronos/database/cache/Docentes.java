package senai.cronos.database.cache;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.util.Observador;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.UnidadeCurricular;

/**
 *
 * @author Carlos Melo e Sergio Lisan
 */
public final class Docentes implements Observador, Cache<Docente> {

    private List<Docente> docentes;
    private static Docentes instance;

    public static Docentes instance() {
        return instance;
    }

    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Docentes();
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
    public Docente buscaDocenteNome(String nome) throws ClassNotFoundException, SQLException {
        for (Docente dc : getDocentes()) {
            if (dc.getNome().equals(nome)) {
                return dc;
            }
        }
        return null;
    }

    /**
     * procura um docente por sua matricula
     *
     * @param matricula
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public Docente buscaDocenteMatricula(String matricula) throws ClassNotFoundException, SQLException {
        for (Docente dc : getDocentes()) {
            if (dc.getMatricula().equals(Integer.parseInt(matricula))) {
                return dc;
            }
        }
        return null;
    }

    public boolean existeDocente(String matricula) throws ClassNotFoundException, SQLException {
        for (Docente dc : getDocentes()) {
            if (dc.getMatricula().equals(Integer.parseInt(matricula) ) ) {
                return true;
            }
        }
        return false;
    }
    
    /**
     * Método que retorna os melhores docentes para uma determinada unidade curricular
     * @param uc
     * @return 
     */
    public List<Docente> bestDocentes(UnidadeCurricular uc) throws Exception {
        List<Docente> bestDocentes   = new ArrayList<>();
        
        for (Docente doc : buscaDocentes(uc.getNucleo() ) ) {
            if (doc.getProficiencia(uc) != null && doc.getProficiencia(uc).getLecionado() > 5) {
                bestDocentes.add(doc);
            }
        }
        
        return bestDocentes;
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
