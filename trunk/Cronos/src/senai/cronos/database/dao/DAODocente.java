package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.*;
import senai.cronos.Fachada;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.*;
import senai.cronos.entidades.enums.Formacao;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Observador;
import senai.cronos.util.debug.Contador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAODocente implements DAO<Docente> {
    
    private static DAO<Docente> instance = new DAODocente();
    
    public static DAO<Docente> getInstance() {
        return instance;
    }
    
    private DAODocente() {        
    }
    
    @Override
    public void add(Docente u) throws SQLException {
        open();
        String query = DatabaseUtil.query("docente.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, u.getMatricula());
            ps.setString(2, u.getNome());
            ps.setInt(3, u.getFormacao().ordinal());
            ps.setDate(4, new java.sql.Date(u.getContratacao().getTime()));
            ps.setInt(5, u.getNucleo().getId());
            ps.setInt(6, u.getScore());
            ps.setInt(7, u.getPrimeiroTurno().ordinal());
            ps.setInt(8, u.getSegundoTurno().ordinal());

            ps.execute();
        }

        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("docente.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            
            DAO daoproficiencia = DAOProficiencia.getInstance();
            daoproficiencia.remove((Integer) id);
            
            ps.execute();
        }
        
        close();
        notifica();
    }

    @Override
    public void update(Docente u) throws SQLException {
        open();
        
        String query = DatabaseUtil.query("docente.update");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getFormacao().ordinal());
            ps.setDate(3, new java.sql.Date(u.getContratacao().getTime()));
            ps.setInt(4, u.getNucleo().getId());
            ps.setInt(5, u.getScore());
            ps.setInt(6, u.getPrimeiroTurno().ordinal());
            ps.setInt(7, u.getSegundoTurno().ordinal());
            ps.setInt(8, u.getMatricula());
            ps.execute();            
        }

        close();
        notifica();
    }

    @Override
    public List<Docente> get() throws SQLException {
        open();
        
        List<Docente> docentes = new LinkedList<>();
        String query = DatabaseUtil.query("docente.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            DAOProficiencia prof = (DAOProficiencia) DAOFactory.getDao(Proficiencia.class);
            
            while (rs.next()) {
                Docente docente = new Docente();
                docente.setMatricula(rs.getInt("matricula"));
                docente.setContratacao(rs.getDate("contratacao"));
                docente.setFormacao(Formacao.getFormacao(rs.getInt("formacao")));
                docente.setNome(rs.getString("nome"));                
                docente.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo")));                
                docente.setPrimeiroTurno(Turno.getTurno(rs.getInt("primeiroturno")));
                docente.setSegundoTurno(Turno.getTurno(rs.getInt("segundoturno")));
                docente.setScore(rs.getInt("score"));
                
                // TODO implementar a logica de captacao dos dados da ocupacao do docente.
                //docente.setOcupacao(ocup.get(docente));
                
                docente.setProficiencias(prof.get(docente));
                docentes.add(docente);
            }
        } catch (ClassNotFoundException ex) {
            // TODO Tratar essa excecao direitinho
        }
        
        close();
        return docentes;
    }

    @Override
    public Docente get(Serializable id) throws SQLException {
        open();
        Docente docente = new Docente();
        String query = DatabaseUtil.query("docente.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();
            
            while (rs.next()) {                
                docente.setMatricula(rs.getInt("matricula"));
                docente.setContratacao(rs.getDate("contratacao"));
                docente.setFormacao(Formacao.getFormacao(rs.getInt("formacao")));
                docente.setNome(rs.getString("nome"));
                docente.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo"))); 
                docente.setPrimeiroTurno(Turno.getTurno(rs.getInt("primeiroturno")));
                docente.setSegundoTurno(Turno.getTurno(rs.getInt("segundoturno")));
                docente.setScore(rs.getInt("score"));                
            }
        } catch (ClassNotFoundException ex) {
            // TODO Tratar essa excecao direitinho
        }
        
        close();
        return docente;
    }
    
    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = DatabaseUtil.conexao();
        Contador.docentes++;
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
