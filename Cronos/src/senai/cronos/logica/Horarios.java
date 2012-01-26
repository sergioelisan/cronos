/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.logica;

import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.util.Observador;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Horarios implements Observador, Repository<Horario> {
  
    private List<Horario> horarios;

    private static Horarios instance = new Horarios();
    
    public static Horarios instance() {
        return instance;
    }
    
    private Horarios() {
        try {
            DAOFactory.getDao(Horario.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Horarios.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    @Override
    public void update() {
        try {
            horarios = DAOFactory.getDao(Horario.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * @return the laboratorios
     */
    public List<Horario> getNucleos() {
        return horarios;
    }

    @Override
    public List<Horario> get() {
        return horarios;
    }

    @Override
    public Horario get(Class c, Integer id) {
        Turma turma = Turmas.instance().get(Turma.class, id);
        for(Horario horario : horarios)
            if(horario.getTurma().equals(turma))
                return horario;
        return null;
    }
    
}
