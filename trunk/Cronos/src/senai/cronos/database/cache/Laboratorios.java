package senai.cronos.database.cache;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.database.dao.DAOFactory;
import senai.util.Observador;
import senai.cronos.entidades.Laboratorio;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Laboratorios implements Observador, Cache<Laboratorio> {

    private List<Laboratorio> laboratorios;

    private static Laboratorios instance;

    public static Laboratorios instance() {
        return instance;
    }
    
    /**
     * Inicia o cache
     */
    public static void start() {
        instance = new Laboratorios();
    }

    private Laboratorios() {
        try {
            DAOFactory.getDao(Laboratorio.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            System.out.println(ex.getMessage() );
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

    @Override
    public void clear() {
        laboratorios.clear();
    }
}
