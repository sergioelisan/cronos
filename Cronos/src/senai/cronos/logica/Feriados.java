/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.logica;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.dao.DAOFeriado;
import senai.cronos.util.Feriado;

/**
 *
 * @author user
 */
public class Feriados implements Observador, Repository<Feriado> {
    
    private List<Feriado> feriados;

    private static Feriados instance = new Feriados();
    
    public static Feriados instance() {
        return instance;
    }
    
    private Feriados() {
        update();
    }

    @Override
    public void update() {
        try {
            feriados = new DAOFeriado().get();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
