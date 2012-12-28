package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import senai.cronos.CronosAPI;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.*;
import senai.cronos.entidades.Formacao;
import senai.cronos.entidades.Turno;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAODocente extends DAO<Docente> {

    private static DAO<Docente> instance = new DAODocente();

    public static DAO<Docente> getInstance() {
        return instance;
    }

    private DAODocente() {
    }

    @Override
    public void add(Docente u) throws SQLException {
        open();
        String query = DatabaseUtil.query("docente.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, u.getMatricula());
            ps.setString(2, u.getNome());
            ps.setInt(3, u.getFormacao().ordinal());
            ps.setDate(4, new java.sql.Date(u.getContratacao().getTime()));
            ps.setInt(5, u.getNucleo().getId());
            ps.setInt(6, u.getScore());
            ps.setInt(7, u.getPrimeiroTurno().ordinal());
            ps.setInt(8, u.getSegundoTurno().ordinal());

            ps.execute();
        }

        close();
        
        DAOProficiencia daoprof = (DAOProficiencia) DAOProficiencia.getInstance();
        daoprof.addAll(u.getProficiencias());
        
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query;
        
        query = DatabaseUtil.query("horario.delete.docente1");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        
        query = DatabaseUtil.query("horario.delete.docente2");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        
        query = DatabaseUtil.query("docente.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);

            DAO daoproficiencia = DAOProficiencia.getInstance();
            daoproficiencia.remove((Integer) id);

            ps.execute();
        }

        close();
        notifica();
    }

    @Override
    public void update(Docente u) throws SQLException {
        open();

        String query = DatabaseUtil.query("docente.update");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getFormacao().ordinal());
            ps.setDate(3, new java.sql.Date(u.getContratacao().getTime()));
            ps.setInt(4, u.getNucleo().getId());
            ps.setInt(5, u.getScore());
            ps.setInt(6, u.getPrimeiroTurno().ordinal());
            ps.setInt(7, u.getSegundoTurno().ordinal());
            ps.setInt(8, u.getMatricula());
            ps.execute();
        }

        close();
        
        DAO<Proficiencia> daoprof = DAOProficiencia.getInstance();
        for (Proficiencia p : u.getProficiencias() ) {
            daoprof.update(p);
        }
        
        notifica();
    }

    @Override
    public List<Docente> get() throws SQLException {
        open();

        List<Docente> docentes = new ArrayList<>();
        String query = DatabaseUtil.query("docente.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            DAOProficiencia prof = (DAOProficiencia) DAOFactory.getDao(Proficiencia.class);
            DAO<Nucleo> dao = DAOFactory.getDao(Nucleo.class);

            while (rs.next()) {
                Docente docente = new Docente();
                docente.setMatricula(rs.getInt("matricula"));
                docente.setContratacao(rs.getDate("contratacao"));
                docente.setFormacao(Formacao.getFormacao(rs.getInt("formacao")));
                docente.setNome(rs.getString("nome"));
                docente.setNucleo(dao.get(rs.getInt("nucleo")));
                docente.setPrimeiroTurno(Turno.getTurno(rs.getInt("primeiroturno")));
                docente.setSegundoTurno(Turno.getTurno(rs.getInt("segundoturno")));
                docente.setScore(rs.getInt("score"));
                docente.setProficiencias(prof.get(docente));
                docentes.add(docente);
            }
        } catch (Exception ex) {
            // TODO Tratar essa excecao direitinho
        }

        close();
        return docentes;
    }

    @Override
    public Docente get(Serializable id) throws SQLException {
        open();
        Docente docente = new Docente();
        String query = DatabaseUtil.query("docente.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();

            DAO<Nucleo> dao = DAOFactory.getDao(Nucleo.class);

            while (rs.next()) {
                docente.setMatricula(rs.getInt("matricula"));
                docente.setContratacao(rs.getDate("contratacao"));
                docente.setFormacao(Formacao.getFormacao(rs.getInt("formacao")));
                docente.setNome(rs.getString("nome"));
                docente.setNucleo(dao.get(rs.getInt("nucleo")));
                docente.setPrimeiroTurno(Turno.getTurno(rs.getInt("primeiroturno")));
                docente.setSegundoTurno(Turno.getTurno(rs.getInt("segundoturno")));
                docente.setScore(rs.getInt("score"));
            }
        } catch (Exception ex) {
            // TODO Tratar essa excecao direitinho
        }

        close();
        return docente;
    }
}
