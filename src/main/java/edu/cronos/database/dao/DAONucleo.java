package edu.cronos.database.dao;

import edu.cronos.database.DatabaseUtil;
import edu.cronos.entidades.Nucleo;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Lisan
 */
public class DAONucleo extends DAO<Nucleo> {

    private static DAO<Nucleo> instance = new DAONucleo();

    private DAONucleo() {
    }

    public static DAO<Nucleo> getInstance() {
        return instance;
    }

    @Override
    public void add(Nucleo u) throws SQLException {
        open();
        String query = DatabaseUtil.query("nucleo.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("nucleo.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void update(Nucleo u) throws SQLException {
        open();
        String query = DatabaseUtil.query("nucleo.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(3, u.getId());
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public List<Nucleo> get() throws SQLException {
        open();
        List<Nucleo> laboratorios = new ArrayList<>();
        String query = DatabaseUtil.query("nucleo.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Nucleo lb = new Nucleo();
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));

                laboratorios.add(lb);
            }
        }

        close();
        return laboratorios;
    }

    @Override
    public Nucleo get(Serializable id) throws SQLException {
        open();
        Nucleo lb = new Nucleo();

        String query = DatabaseUtil.query("nucleo.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));

            }
        }

        close();

        return lb;
    }
}
