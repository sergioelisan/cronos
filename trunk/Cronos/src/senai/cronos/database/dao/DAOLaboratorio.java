package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import senai.cronos.entidades.Laboratorio;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.Database;
import senai.cronos.util.Contador;

/**
 *
 * @author Sergio Lisan
 */
public class DAOLaboratorio implements DAO<Laboratorio> {

    @Override
    public void add(Laboratorio u) throws SQLException {
        open();
        String query = Database.query("laboratorio.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDescricao());
            ps.execute();
        }
        close();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = Database.query("laboratorio.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
    }

    @Override
    public void update(Laboratorio u) throws SQLException {
        open();
        String query = Database.query("laboratorio.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setString(2, u.getDescricao());
            ps.setInt(3, u.getId());
            ps.execute();
        }
        close();
    }

    @Override
    public List<Laboratorio> get() throws SQLException {
        open();
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
        
        close();

        return laboratorios;
    }

    @Override
    public Laboratorio get(Serializable id) throws SQLException {
        open();
        Laboratorio lb = new Laboratorio();

        String query = Database.query("laboratorio.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                lb.setId(rs.getInt("id"));
                lb.setNome(rs.getString("nome"));
                lb.setDescricao(rs.getString("descricao"));

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
        Contador.laboratorios++;
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
