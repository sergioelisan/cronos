package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.Database;
import senai.cronos.entidades.UnidadeCurricular;

/**
 *
 * @author Sergio Lisan
 */
public class DAOUnidadeCurricular implements DAO<UnidadeCurricular> {

    public DAOUnidadeCurricular() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(UnidadeCurricular u) throws SQLException {
        String query = Database.query("disciplina.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setInt(3, u.getModulo());
            ps.setString(4, u.getConteudoProgramatico());
            ps.setInt(5, u.getLab().getId());
            ps.setInt(6, u.getCargaHoraria());
            ps.execute();
        }
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        String query = Database.query("disciplina.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
    }

    @Override
    public void update(UnidadeCurricular u) throws SQLException {
        String query = Database.query("disciplina.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setInt(3, u.getModulo());
            ps.setString(4, u.getConteudoProgramatico());
            ps.setInt(5, u.getLab().getId());
            ps.setInt(6, u.getCargaHoraria());
            ps.setInt(7, u.getId());

            ps.execute();
        }
    }

    @Override
    public List<UnidadeCurricular> get() throws SQLException {
        List<UnidadeCurricular> disciplinas = new ArrayList<>();
        String query = Database.query("disciplina.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UnidadeCurricular uc = new UnidadeCurricular();
                uc.setId(rs.getInt("id"));
                uc.setNome(rs.getString("nome"));

                DAONucleo daonucleo = new DAONucleo();
                uc.setNucleo(daonucleo.get(rs.getInt("nucleo")));

                DAOLaboratorio daolab = new DAOLaboratorio();
                uc.setLab(daolab.get(rs.getInt("laboratorio")));
                
                uc.setCargaHoraria(rs.getInt("carga"));
                uc.setModulo(rs.getInt("modulo"));
                uc.setConteudoProgramatico(rs.getString("ementa"));

                disciplinas.add(uc);
            }
        } catch (ClassNotFoundException e) {
        }

        return disciplinas;
    }

    @Override
    public UnidadeCurricular get(Serializable id) throws SQLException {
        UnidadeCurricular uc = new UnidadeCurricular();
        String query = Database.query("disciplina.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uc.setId(rs.getInt("id"));
                uc.setNome(rs.getString("nome"));

                DAONucleo daonucleo = new DAONucleo();
                uc.setNucleo(daonucleo.get(rs.getInt("nucleo")));

                DAOLaboratorio daolab = new DAOLaboratorio();
                uc.setLab(daolab.get(rs.getInt("laboratorio")));
                
                uc.setCargaHoraria(rs.getInt("carga"));
                uc.setModulo(rs.getInt("modulo"));
                uc.setConteudoProgramatico(rs.getString("ementa"));
            }
        } catch (ClassNotFoundException e) {
        }

        return uc;
    }
    private Connection con;
}
