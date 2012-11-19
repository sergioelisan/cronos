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
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.Laboratorio;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.UnidadeCurricular;

/**
 *
 * @author Sergio Lisan
 */
public class DAOUnidadeCurricular implements DAO<UnidadeCurricular> {

    private static DAO<UnidadeCurricular> instance = new DAOUnidadeCurricular();
    
    public static DAO<UnidadeCurricular> getInstance() {
        return instance;
    }
    
    private DAOUnidadeCurricular() {        
    }
    
    @Override
    public void add(UnidadeCurricular u) throws SQLException {
        open();
        String query = DatabaseUtil.query("disciplina.insert");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setInt(3, u.getModulo());
            ps.setString(4, u.getConteudoProgramatico());
            ps.setInt(5, u.getLab().getId());
            ps.setInt(6, u.getCargaHoraria());
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("disciplina.delete");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public void update(UnidadeCurricular u) throws SQLException {
        open();
        String query = DatabaseUtil.query("disciplina.update");
        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setString(1, u.getNome());
            ps.setInt(2, u.getNucleo().getId());
            ps.setInt(3, u.getModulo());
            ps.setString(4, u.getConteudoProgramatico());
            ps.setInt(5, u.getLab().getId());
            ps.setInt(6, u.getCargaHoraria());
            ps.setInt(7, u.getId());

            ps.execute();
        }
        close();
        notifica();
    }

    @Override
    public List<UnidadeCurricular> get() throws SQLException {
        open();
        List<UnidadeCurricular> disciplinas = new ArrayList<>();
        String query = DatabaseUtil.query("disciplina.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                UnidadeCurricular uc = new UnidadeCurricular();
                uc.setId(rs.getInt("id"));
                uc.setNome(rs.getString("nome"));
                uc.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo")));
                uc.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio")));                
                uc.setCargaHoraria(rs.getInt("carga"));
                uc.setModulo(rs.getInt("modulo"));
                uc.setConteudoProgramatico(rs.getString("ementa"));

                disciplinas.add(uc);
            }
        } catch (Exception e) {
        }

        close();
        
        return disciplinas;
    }

    @Override
    public UnidadeCurricular get(Serializable id) throws SQLException {
        open();
        UnidadeCurricular uc = new UnidadeCurricular();
        String query = DatabaseUtil.query("disciplina.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                uc.setId(rs.getInt("id"));
                uc.setNome(rs.getString("nome"));
                uc.setNucleo(Fachada.<Nucleo>get(Nucleo.class, rs.getInt("nucleo")));
                uc.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio")));                
                uc.setCargaHoraria(rs.getInt("carga"));
                uc.setModulo(rs.getInt("modulo"));
                uc.setConteudoProgramatico(rs.getString("ementa"));
            }
        } catch (Exception e) {
        }

        close();
        
        return uc;
    }
    
    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = DatabaseUtil.conexao();
    }
    
    private Connection con;

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
}
