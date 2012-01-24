package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.Database;
import senai.cronos.entidades.Nucleo;

/**
 *
 * @author Sergio Lisan
 */
public class DAONucleo implements DAO<Nucleo> {

    public DAONucleo() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(Nucleo u) throws SQLException {
        String query = Database.query("nucleo.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDesc());
            ps.execute();
        }
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        String query = Database.query("nucleo.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
    }

    @Override
    public void update(Nucleo u) throws SQLException {
        String query = Database.query("nucleo.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDesc());
            ps.setInt(3, u.getId());
            ps.execute();
        }
    }

    @Override
    public List<Nucleo> get() throws SQLException {
        List<Nucleo> laboratorios = new ArrayList<>();
        String query = Database.query("nucleo.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nucleo lb = new Nucleo();
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));
                lb.setDesc(rs.getString("descricao"));

                laboratorios.add(lb);
            }
        }

        return laboratorios;
    }

    @Override
    public Nucleo get(Serializable id) throws SQLException {
        Nucleo lb = new Nucleo();

        String query = Database.query("nucleo.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));
                lb.setDesc(rs.getString("descricao"));

            }
        }

        return lb;
    }
    private Connection con;
}
