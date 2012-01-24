package senai.cronos.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.Database;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.enums.Turno;

/**
 *
 * @author Sergio Lisan
 */
public class DAOTurma implements DAO<Turma> {

    public DAOTurma() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }
    
    @Override
    public void add(Turma u) throws SQLException {
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
        
    }

    @Override
    public void remove(Integer id) throws SQLException {
        String query = Database.query("turma.delete");
        
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, id);
            ps.execute();
        }
    }

    @Override
    public void update(Turma u) throws SQLException {
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
    }

    @Override
    public List<Turma> get() throws SQLException {
        List<Turma> turmas = new ArrayList<>();
        String query = Database.query("turma.select");
        
        try(PreparedStatement ps = con.prepareStatement(query) ) {
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {
                Turma t = new Turma();
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                
                DAONucleo daonucleo = new DAONucleo();
                t.setNucleo(daonucleo.get(rs.getInt("nucleo")));
                
                t.setEntrada(rs.getDate("entrada"));
                t.setSaida(rs.getDate("saida"));
                t.setTurno(Turno.getTurno(rs.getInt("turno")));
                t.setHabilitacao(rs.getInt("habilitacao"));
                
                turmas.add(t);                
            }
            
        } catch (ClassNotFoundException e) {
            
        }
        
        return turmas;
    }

    @Override
    public Turma get(Integer id) throws SQLException {
        Turma t = new Turma();
        String query = Database.query("turma.get");
        
        try(PreparedStatement ps = con.prepareStatement(query) ) {
            ps.setInt(1, id);
            ResultSet rs = ps.executeQuery();
            
            while(rs.next()) {                
                t.setId(rs.getInt("id"));
                t.setNome(rs.getString("nome"));
                
                DAONucleo daonucleo = new DAONucleo();
                t.setNucleo(daonucleo.get(rs.getInt("nucleo")));
                
                t.setEntrada(rs.getDate("entrada"));
                t.setSaida(rs.getDate("saida"));
                t.setTurno(Turno.getTurno(rs.getInt("turno")));
                t.setHabilitacao(rs.getInt("habilitacao"));
            }
            
        } catch (ClassNotFoundException e) {
            
        }
        
        return t;
    }
    
    private Connection con;
}
