package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.Database;
import senai.cronos.entidades.Nucleo;

/**
 *
 * @author Sergio Lisan
 */
public class DAONucleo implements DAO<Nucleo> {

    @Override
    public void add(Nucleo u) throws SQLException {
        open();
        String query = Database.query("nucleo.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDesc());
            ps.execute();
        }
        close();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = Database.query("nucleo.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
    }

    @Override
    public void update(Nucleo u) throws SQLException {
        open();
        String query = Database.query("nucleo.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDesc());
            ps.setInt(3, u.getId());
            ps.execute();
        }
        close();
    }

    @Override
    public List<Nucleo> get() throws SQLException {
        open();
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

        close();
        
        return laboratorios;
    }

    @Override
    public Nucleo get(Serializable id) throws SQLException {
        open();
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

        close();
        
        return lb;
    }
    
    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = Database.conexao();
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
        for(Observador o : observadores)
            o.update();
    }
    
    private List<Observador> observadores = new ArrayList<>();
    
    private Connection con;
}
