package edu.cronos.database.vectors;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Nucleo;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sergio lisan e carlos melo
 */
public final class Nucleos implements Observador, Repository<Nucleo> {

    private static Nucleos instance = new Nucleos();
    private List<Nucleo> nucleos;

    private Nucleos() {
        try {
            DAOFactory.getDao(Nucleo.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Nucleos.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Nucleos instance() {
        return instance;
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
        for (Nucleo nucleo : nucleos)
            if (nucleo.getId().equals(id))
                return nucleo;
        return null;
    }
}
