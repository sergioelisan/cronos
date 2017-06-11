package edu.cronos.database.vectors;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Laboratorio;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author sergio lisan e carlos melo
 */
public final class Laboratorios implements Observador, Repository<Laboratorio> {

    private static Laboratorios instance = new Laboratorios();
    private List<Laboratorio> laboratorios;

    private Laboratorios() {
        try {
            DAOFactory.getDao(Laboratorio.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Laboratorios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Laboratorios instance() {
        return instance;
    }

    @Override
    public void update() {
        try {
            laboratorios = DAOFactory.getDao(Laboratorio.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return the laboratorios
     */
    public List<Laboratorio> getLaboratorios() {
        return laboratorios;
    }

    @Override
    public List<Laboratorio> get() {
        return laboratorios;
    }

    @Override
    public Laboratorio get(Class c, Integer id) {
        for (Laboratorio lab : laboratorios)
            if (lab.getId().equals(id))
                return lab;
        return null;
    }
}
