/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.logica;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.dao.DAOHorario;
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
        update();
    }

    @Override
    public void update() {
        try {
            horarios = new DAOHorario().get();
        } catch (SQLException ex) {
            ex.printStackTrace();
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
