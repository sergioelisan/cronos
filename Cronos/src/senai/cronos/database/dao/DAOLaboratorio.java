package senai.cronos.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import senai.cronos.entidades.Laboratorio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.Database;

/**
 *
 * @author Sergio Lisan
 */
public class DAOLaboratorio implements DAO<Laboratorio> {

    public DAOLaboratorio() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(Laboratorio u) throws SQLException {
        String query = Database.query("laboratorio.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDescricao());
            ps.execute();
        }
    }

    @Override
    public void remove(Integer id) throws SQLException {
        String query = Database.query("laboratorio.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    @Override
    public void update(Laboratorio u) throws SQLException {
        String query = Database.query("laboratorio.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDescricao());
            ps.setInt(3, u.getId());
            ps.execute();
        }
    }

    @Override
    public List<Laboratorio> get() throws SQLException {
        List<Laboratorio> laboratorios = new ArrayList<>();
        String query = Database.query("laboratorio.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Laboratorio lb = new Laboratorio();
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));
                lb.setDescricao(rs.getString("descricao"));

                laboratorios.add(lb);
            }
        }

        return laboratorios;
    }

    @Override
    public Laboratorio get(Integer id) throws SQLException {
        Laboratorio lb = new Laboratorio();

        String query = Database.query("laboratorio.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));
                lb.setDescricao(rs.getString("descricao"));

            }
        }

        return lb;
    }
    
    
    private Connection con;
}
