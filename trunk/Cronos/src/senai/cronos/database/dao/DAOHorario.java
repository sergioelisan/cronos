package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import java.util.TreeMap;
import senai.cronos.database.Database;
import senai.cronos.entidades.*;
import senai.cronos.util.Tupla;

/**
 *
 * @author Sergio Lisan
 */
public class DAOHorario implements DAO<Horario> {

    public DAOHorario() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(Horario u) throws SQLException {
        String query = Database.query("horario.insert");

        for (Date dia : u.getHorario().keySet()) {
            try (PreparedStatement ps = con.prepareStatement(query)) {
                ps.setInt(1, u.getTurma().getId());
                ps.setDate(2, new java.sql.Date(dia.getTime()));
                ps.setInt(3, u.getHorario().get(dia).getPrimeiro().getDisciplina().getId());
                ps.setInt(4, u.getHorario().get(dia).getPrimeiro().getDocente().getMatricula());
                ps.setInt(5, u.getHorario().get(dia).getPrimeiro().getLab().getId());
                ps.setInt(6, u.getHorario().get(dia).getSegundo().getDisciplina().getId());
                ps.setInt(7, u.getHorario().get(dia).getSegundo().getDocente().getMatricula());
                ps.setInt(8, u.getHorario().get(dia).getSegundo().getLab().getId());

                ps.execute();
            }
        }
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        String query = Database.query("horario.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
    }

    @Override
    public void update(Horario u) throws SQLException {
        String query = Database.query("horario.update");

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
    }

    @Override
    public List<Horario> get() throws SQLException {
        throw new UnsupportedOperationException("Logica sem necessidade de implementacao");
    }

    @Override
    public Horario get(Serializable id) throws SQLException {
        Horario h = new Horario();
        String query = Database.query("horario.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();

            Map<Date, Tupla<Aula, Aula>> horario = new TreeMap<>();

            DAOTurma daoturma = new DAOTurma();
            
            DAODocente daodocente = new DAODocente();
            List<Docente> docentes = daodocente.get();
            
            DAOUnidadeCurricular daodisciplina = new DAOUnidadeCurricular();
            List<UnidadeCurricular> disciplinas = daodisciplina.get();
            
            DAOLaboratorio daoLab = new DAOLaboratorio();
            List<Laboratorio> labs = daoLab.get();
            
            Turma t = daoturma.get((Integer) id);

            while (rs.next()) {
                Date dia = rs.getDate("dia");

                Aula a1 = new Aula();

                a1.setDocente(getDocente(rs.getInt("docente1"), docentes) );
                a1.setDisciplina(getUnidadeCurricular(rs.getInt("disciplina1"), disciplinas));
                a1.setLab(getLaboratorio(rs.getInt("laboratorio1"), labs));

                Aula a2 = new Aula();
                a2.setDocente(getDocente(rs.getInt("docente2"), docentes) );
                a2.setDisciplina(getUnidadeCurricular(rs.getInt("disciplina2"), disciplinas));
                a2.setLab(getLaboratorio(rs.getInt("laboratorio1"), labs));

                horario.put(dia, new Tupla<>(a1, a2));
            }

            h.setTurma(t);
            h.setHorario(horario);

        } catch (ClassNotFoundException e) {
        }

        return h;
    }
    
    /**
     * Metodo usado para acelerar a operacao de carregamento de horarios
     * @param id
     * @param docentes
     * @return 
     */
    private Docente getDocente(Integer id, List<Docente> docentes) {
        for(Docente dc : docentes)
            if(dc.getMatricula().equals(id))
                return dc;
        return new Docente();
    }
    
    /**
     * Metodo usado para acelerar a operacao de carregamento de horarios
     * @param id
     * @param disciplinas
     * @return 
     */
    private UnidadeCurricular getUnidadeCurricular(Integer id, List<UnidadeCurricular> disciplinas) {
        for(UnidadeCurricular disciplina : disciplinas)
            if(disciplina.getId().equals(id))
                return disciplina;
        return new UnidadeCurricular();
    }
    
    /**
     * Metodo usado para acelerar a operacao de carregamento de horarios
     * @param id
     * @param labs
     * @return 
     */
    private Laboratorio getLaboratorio(Integer id, List<Laboratorio> labs) {
        for(Laboratorio lab : labs)
            if(lab.getId().equals(id))
                return lab;
        return new Laboratorio();
    }
    
    
    private Connection con;
}
