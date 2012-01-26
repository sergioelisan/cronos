package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import senai.cronos.util.Observador;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.util.calendario.Feriado;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOFeriado implements DAO<Feriado>{

    private static DAO<Feriado> instance = new DAOFeriado();
    
    public static DAO<Feriado> getInstance() {
        return instance;
    }
    
    private DAOFeriado() {        
    }
    
    @Override
    public void add(Feriado u) throws SQLException {
        open();
        String query = DatabaseUtil.query("feriados.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( u.getDia().getTime() ) );
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
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( ((Date)id).getTime() ) );
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void update(Feriado u) throws SQLException {
        //open();
        throw new UnsupportedOperationException("Not supported yet.");
        //close();
    }

    @Override
    public Feriado get(Serializable id) throws SQLException {
        open();
        Feriado feriados = new Feriado();                
        String query = DatabaseUtil.query("feriados.get");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setDate(1, new java.sql.Date( ((Date)id).getTime() ) );
            ResultSet rs = ps.executeQuery();
            while(rs.next()){                
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
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();
            while(rs.next()){
                Feriado f = new Feriado();
                f.setDia(rs.getDate("dia"));
                f.setDescricao(rs.getString("descricao"));
                feriados.add(f);
            }
        }
        
        close();
        
        return feriados;
    }
    
    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = DatabaseUtil.conexao();
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
