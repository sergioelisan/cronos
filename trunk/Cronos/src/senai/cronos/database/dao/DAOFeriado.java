package senai.cronos.database.dao;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import senai.cronos.database.Database;

/**
 *
 * @author Sergio Lisan
 */
public class DAOFeriado implements DAO<Date>{

    public DAOFeriado() throws ClassNotFoundException, SQLException {
        con = Database.conexao();
    }

    @Override
    public void add(Date u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void remove(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public void update(Date u) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public Date get(Integer id) throws SQLException {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public List<Date> get() throws SQLException {
        List<Date> feriados = new ArrayList<>();                
        String query = Database.query("feriados.select");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                feriados.add(rs.getDate("dia"));
            }
        }
        
        return feriados;
    }
    
    private Connection con;
}
