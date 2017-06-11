package edu.cronos.database.cache;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Laboratorio;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.List;

/**
 * @author sergio lisan e carlos melo
 */
public class Laboratorios implements Observador, Cache<Laboratorio> {

    private static Laboratorios instance;
    private List<Laboratorio> laboratorios;

    private Laboratorios() {
        try {
            DAOFactory.getDao(Laboratorio.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage());
        }
    }

    public static Laboratorios instance() {
        return instance;
    }

    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Laboratorios();
    }

    @Override
    public void update() {
        try {
            laboratorios = DAOFactory.getDao(Laboratorio.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public List<Laboratorio> get() {
        return laboratorios;
    }

    @Override
    public Laboratorio get(Class c, Integer id) {
        for (Laboratorio lab : laboratorios) {
            if (lab.getId().equals(id)) {
                return lab;
            }
        }
        return null;
    }

    @Override
    public void clear() {
        laboratorios.clear();
    }
}
