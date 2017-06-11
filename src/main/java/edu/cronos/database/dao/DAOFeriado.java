package edu.cronos.database.dao;

import edu.cronos.database.DatabaseUtil;
import edu.cronos.util.date.Feriado;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOFeriado extends DAO<Feriado> {

    private static DAO<Feriado> instance = new DAOFeriado();

    private DAOFeriado() {
    }

    public static DAO<Feriado> getInstance() {
        return instance;
    }

    @Override
    public void add(Feriado u) throws SQLException {
        open();
        String query = DatabaseUtil.query("feriados.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(u.getDia().getTime()));
            ps.setString(2, u.getDescricao());

            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("feriados.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(((Date) id).getTime()));
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void update(Feriado u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Feriado get(Serializable id) throws SQLException {
        open();
        Feriado feriados = new Feriado();
        String query = DatabaseUtil.query("feriados.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date(((Date) id).getTime()));
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                feriados.setDia(rs.getDate("dia"));
                feriados.setDescricao(rs.getString("descricao"));
            }
        }

        close();
        return feriados;
    }

    @Override
    public List<Feriado> get() throws SQLException {
        open();
        List<Feriado> feriados = new ArrayList<>();
        String query = DatabaseUtil.query("feriados.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while (rs.next()) {
                Feriado f = new Feriado();
                f.setDia(rs.getDate("dia"));
                f.setDescricao(rs.getString("descricao"));
                feriados.add(f);
            }
        }

        close();

        return feriados;
    }
}
