package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import senai.cronos.Fachada;
import senai.cronos.util.Observador;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.*;
import senai.cronos.util.debug.Contador;
import senai.cronos.util.Tupla;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOHorario implements DAO<Horario> {

    private static DAO<Horario> instance = new DAOHorario();

    public static DAO<Horario> getInstance() {
        return instance;
    }

    private DAOHorario() {
    }

    @Override
    public void add(Horario u) throws SQLException {
        open();
        String query = DatabaseUtil.query("horario.insert");
        
        // limpa o objeto Horario, mas essa tarefa precisa ser feita em 
        // outro lugar, ou seja:
        // TODO Refatorar!
        
        // Fim da limpeza

        for (Date dia : u.getHorario().keySet()) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, u.getTurma().getId());
                ps.setDate(2, new java.sql.Date(dia.getTime()));
                
                System.out.println(u.getHorario().get(dia).getPrimeiro().getDisciplina().getId());
                Integer disciplinaID = u.getHorario().get(dia).getPrimeiro().getDisciplina().getId();
                ps.setInt(3, disciplinaID);
                ps.setInt(4, u.getHorario().get(dia).getPrimeiro().getDocente().getMatricula());                
                ps.setInt(5, u.getHorario().get(dia).getPrimeiro().getLab().getId());
                
                System.out.println(u.getHorario().get(dia).getSegundo().getDisciplina().getId());
                Integer disciplina2ID = u.getHorario().get(dia).getSegundo().getDisciplina().getId();
                ps.setInt(6, disciplina2ID);
                ps.setInt(7, u.getHorario().get(dia).getSegundo().getDocente().getMatricula());                
                ps.setInt(8, u.getHorario().get(dia).getSegundo().getLab().getId());

                ps.execute();
            }
        }
        
        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("horario.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }

        close();
        notifica();
    }  

    @Override
    public void update(Horario u) throws SQLException {
        open();
        String query = DatabaseUtil.query("horario.update");

        for (Date dia : u.getHorario().keySet()) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                // update
                ps.setInt(1, u.getHorario().get(dia).getPrimeiro().getDisciplina().getId());
                ps.setInt(2, u.getHorario().get(dia).getPrimeiro().getDocente().getMatricula());
                ps.setInt(3, u.getHorario().get(dia).getPrimeiro().getLab().getId());
                ps.setInt(4, u.getHorario().get(dia).getSegundo().getDisciplina().getId());
                ps.setInt(5, u.getHorario().get(dia).getSegundo().getDocente().getMatricula());
                ps.setInt(6, u.getHorario().get(dia).getSegundo().getLab().getId());

                // where
                ps.setInt(7, u.getTurma().getId());
                ps.setDate(8, new java.sql.Date(dia.getTime()));

                ps.execute();
            }
        }
        close();
        notifica();
    }
    
    @Override
    public List<Horario> get() throws SQLException {
        open();
        List<Horario> horarios = new ArrayList<>();

        try {
            for (Turma turma : Fachada.<Turma>get(Turma.class)) {
                Integer id = turma.getId();
                horarios.add(this.get(id));
            }
        } catch (ClassNotFoundException ex) {
            ex.printStackTrace(System.err);
        }

        close();
        return horarios;
    }

    @Override
    public Horario get(Serializable id) throws SQLException {
        open();
        Horario h = new Horario();
        String query = DatabaseUtil.query("horario.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();

            Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();

            Turma t = Fachada.<Turma>get(Turma.class, (Integer) id);

            while (rs.next()) {
                Date dia = rs.getDate("dia");

                Aula a1 = new Aula();

                a1.setDocente(Fachada.<Docente>get(Docente.class, rs.getInt("docente1")));
                a1.setDisciplina(Fachada.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina1")));
                a1.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio1")));

                Aula a2 = new Aula();
                a2.setDocente(Fachada.<Docente>get(Docente.class, rs.getInt("docente2")));
                a2.setDisciplina(Fachada.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina2")));
                a2.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio2")));

                horario.put(dia, new Tupla<>(a1, a2));
            }

            h.setTurma(t);
            h.setHorario(horario);

        } catch (Exception e) {
        }

        close();

        return h;
    }

    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = DatabaseUtil.conexao();
        Contador.horario++;
    }

    @Override
    public void registra(Observador o) {
        observadores.add(o);
    }

    @Override
    public void remove(Observador o) {
        observadores.remove(o);
    }

    @Override
    public void notifica() {
        for (Observador o : observadores) {
            o.update();
        }
    }
    private List<Observador> observadores = new ArrayList<>();
    private Connection con;
}
