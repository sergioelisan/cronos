package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import senai.cronos.database.Database;
import senai.cronos.util.Feriado;

/**
 *
 * @author Sergio Lisan
 */
public class DAOFeriado implements DAO<Feriado>{

    public DAOFeriado() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(Feriado u) throws SQLException {
        String query = Database.query("feriados.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( u.getDia().getTime() ) );
            ps.setString(2, u.getDescricao());

            ps.execute();
        }
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        String query = Database.query("feriados.delete");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( ((Date)id).getTime() ) );
            ps.execute();
        }
    }

    @Override
    public void update(Feriado u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Feriado get(Serializable id) throws SQLException {
        Feriado feriados = new Feriado();                
        String query = Database.query("feriados.get");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( ((Date)id).getTime() ) );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){                
                feriados.setDia(rs.getDate("dia"));
                feriados.setDescricao(rs.getString("descricao"));
            }
        }
        
        return feriados;
    }

    @Override
    public List<Feriado> get() throws SQLException {
        List<Feriado> feriados = new ArrayList<>();                
        String query = Database.query("feriados.select");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feriado f = new Feriado();
                f.setDia(rs.getDate("dia"));
                f.setDescricao(rs.getString("descricao"));
                feriados.add(f);
            }
        }
        
        return feriados;
    }
    
    private Connection con;    
}
