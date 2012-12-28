/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.database.cache;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.util.Observador;
import senai.util.date.Feriado;

/**
 *
 * @author user
 */
public class Feriados implements Observador, Cache<Feriado> {

    private List<Feriado> feriados;

    private static Feriados instance;

    public static Feriados instance() {
        return instance;
    }
    
    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Feriados();
    }

    private Feriados() {
        try {
            DAOFactory.getDao(Feriado.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Feriados.class.getName()).log(Level.SEVERE, null, ex);
        }
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
}
