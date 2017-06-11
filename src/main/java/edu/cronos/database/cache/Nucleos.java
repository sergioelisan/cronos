package edu.cronos.database.cache;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Nucleo;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.List;

/**
 * @author sergio lisan e carlos melo
 */
public class Nucleos implements Observador, Cache<Nucleo> {

    private static Nucleos instance;
    private List<Nucleo> nucleos;

    private Nucleos() {
        try {
            DAOFactory.getDao(Nucleo.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Nucleos instance() {
        return instance;
    }

    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Nucleos();
    }

    @Override
    public void update() {
        try {
            nucleos = DAOFactory.getDao(Nucleo.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public List<Nucleo> get() {
        return nucleos;
    }

    @Override
    public Nucleo get(Class c, Integer id) {
        for (Nucleo nucleo : nucleos) {
            if (nucleo.getId().equals(id)) {
                return nucleo;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        nucleos.clear();
    }
}
