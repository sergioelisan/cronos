package senai.cronos.database.cache;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.util.Observador;
import senai.cronos.entidades.Laboratorio;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Laboratorios implements Observador, Cache<Laboratorio> {

    private List<Laboratorio> laboratorios;

    private static Laboratorios instance = new Laboratorios();

    public static Laboratorios instance() {
        return instance;
    }

    private Laboratorios() {
        try {
            DAOFactory.getDao(Laboratorio.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Laboratorios.class.getName()).log(Level.SEVERE, null, ex);
        }
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
        for(Laboratorio lab : laboratorios)
            if(lab.getId().equals(id))
                return lab;
        return null;
    }
}
