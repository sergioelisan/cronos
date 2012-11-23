package senai.cronos.database.vectors;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.util.Observador;
import senai.cronos.entidades.Nucleo;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Nucleos implements Observador, Repository<Nucleo> {

    private List<Nucleo> nucleos;

    private static Nucleos instance = new Nucleos();

    public static Nucleos instance() {
        return instance;
    }

    private Nucleos() {
        try {
            DAOFactory.getDao(Nucleo.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Nucleos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        try {
            nucleos = DAOFactory.getDao(Nucleo.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return the laboratorios
     */
    public List<Nucleo> getNucleos() {
        return nucleos;
    }

    @Override
    public List<Nucleo> get() {
        return nucleos;
    }

    @Override
    public Nucleo get(Class c, Integer id) {
        for(Nucleo nucleo : nucleos)
            if(nucleo.getId().equals(id))
                return nucleo;
        return null;
    }
}
