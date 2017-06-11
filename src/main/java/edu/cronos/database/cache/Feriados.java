/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cronos.database.cache;

import edu.cronos.database.dao.DAOFactory;
import edu.cronos.util.Observador;
import edu.cronos.util.date.Feriado;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author user
 */
public class Feriados implements Observador, Cache<Feriado> {

    private static Feriados instance;
    private List<Feriado> feriados;

    private Feriados() {
        try {
            DAOFactory.getDao(Feriado.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Feriados.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static Feriados instance() {
        return instance;
    }

    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Feriados();
    }

    @Override
    public void update() {
        try {
            feriados = DAOFactory.getDao(Feriado.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return the laboratorios
     */
    public List<Feriado> getFeriados() {
        return feriados;
    }

    @Override
    public List<Feriado> get() {
        return feriados;
    }

    @Override
    public Feriado get(Class c, Integer id) {
        return null;
    }

    @Override
    public void clear() {
        feriados.clear();
    }
}
