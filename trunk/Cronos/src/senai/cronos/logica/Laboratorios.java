package senai.cronos.logica;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.dao.DAOLaboratorio;
import senai.cronos.entidades.Laboratorio;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Laboratorios implements Observador, Repository<Laboratorio> {
    
    private List<Laboratorio> laboratorios;

    private static Laboratorios instance = new Laboratorios();
    
    public static Laboratorios instance() {
        return instance;
    }
    
    private Laboratorios() {
        update();
    }

    @Override
    public void update() {
        try {
            laboratorios = new DAOLaboratorio().get();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
