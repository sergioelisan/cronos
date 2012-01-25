package senai.cronos.logica;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.dao.DAONucleo;
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
        update();
    }

    @Override
    public void update() {
        try {
            nucleos = new DAONucleo().get();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
