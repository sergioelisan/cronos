package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.Fachada;
import senai.cronos.util.Observador;
import senai.cronos.database.Database;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.enums.Turno;

/**
 *
 * @author Sergio Lisan
 */
public class DAOTurma implements DAO<Turma> {

    @Override
    public void add(Turma u) throws SQLException {
        open();
        String query = Database.query("turma.insert");
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setDate(3, new java.sql.Date(u.getEntrada().getTime()) );
            ps.setDate(4, new java.sql.Date(u.getSaida().getTime()) );
            ps.setInt(5, u.getTurno().ordinal());
            ps.setInt(6, u.getHabilitacao());
            ps.execute();
        }
        close();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = Database.query("turma.delete");
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
    }

    @Override
    public void update(Turma u) throws SQLException {
        open();
        String query = Database.query("turma.update");
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setDate(3, new java.sql.Date(u.getEntrada().getTime()) );
            
            java.sql.Date saida = u.getSaida() == null ? null : new java.sql.Date(u.getSaida().getTime()); 
            ps.setDate(4, saida);
            
            ps.setInt(5, u.getTurno().ordinal());
            ps.setInt(6, u.getHabilitacao());
            ps.setInt(7, u.getId());
            ps.execute();
        }
        close();
    }

    @Override
    public List<Turma> get() throws SQLException {
        open();
        List<Turma> turmas = new ArrayList<>();
        String query = Database.query("turma.select");
        
        try(PreparedStatement ps = con.prepareStatement(query) ) {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Turma t = new Turma();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));                
                t.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo")));                
                t.setEntrada(rs.getDate("entrada"));
                t.setSaida(rs.getDate("saida"));
                t.setTurno(Turno.getTurno(rs.getInt("turno")));
                t.setHabilitacao(rs.getInt("habilitacao"));
                
                turmas.add(t);                
            }
            
        } catch (Exception e) {
            
        }
        close();
        
        return turmas;
    }

    @Override
    public Turma get(Serializable id) throws SQLException {
        open();
        Turma t = new Turma();
        String query = Database.query("turma.get");
        
        try(PreparedStatement ps = con.prepareStatement(query) ) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {                
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                t.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo")));                
                t.setEntrada(rs.getDate("entrada"));
                t.setSaida(rs.getDate("saida"));
                t.setTurno(Turno.getTurno(rs.getInt("turno")));
                t.setHabilitacao(rs.getInt("habilitacao"));
            }
            
        } catch (Exception e) {
            
        }
        
        close();
        return t;
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
