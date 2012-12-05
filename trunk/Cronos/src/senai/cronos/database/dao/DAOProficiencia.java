package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.CronosAPI;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.debug.Debug;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOProficiencia extends DAO<Proficiencia> {

    private static DAO<Proficiencia> instance = new DAOProficiencia();

    public static DAO<Proficiencia> getInstance() {
        return instance;
    }

    private DAOProficiencia() {
    }

    @Override
    public void add(Proficiencia p) throws SQLException {
        open();
        String query = DatabaseUtil.query("proficiencia.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, p.getDocente().getMatricula());
            ps.setInt(2, p.getDisciplina().getId());
            ps.setInt(3, p.getLecionado());
            ps.setInt(4, p.getScoreTemp());

            ps.execute();
        }
        close();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("proficiencia.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }
        close();
    }

    @Override
    public void update(Proficiencia p) throws SQLException {
        open();
        String query = DatabaseUtil.query("proficiencia.update");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            // update
            ps.setInt(1, p.getLecionado());
            ps.setInt(2, p.getScoreTemp());

            // where
            ps.setInt(3, p.getDocente().getMatricula());
            ps.setInt(4, p.getDisciplina().getId());

            ps.execute();
        }
        close();
    }

    @Override
    public Proficiencia get(Serializable id) throws SQLException {
        throw new UnsupportedOperationException("Operacao nao suportada");
    }

    @Override
    public List<Proficiencia> get() throws SQLException {
        throw new UnsupportedOperationException("Operacao nao suportada");
    }

    public List<Proficiencia> get(Docente doc) throws SQLException {
        open();
        List<Proficiencia> profs = new ArrayList<>();
        String query = DatabaseUtil.query("proficiencia.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, doc.getMatricula());
            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Proficiencia p = new Proficiencia();
                p.setDocente(doc);
                p.setLecionado(rs.getInt("nivel"));
                p.setScoreTemp(rs.getInt("scoretemp"));
                p.setUnidadecurricular(CronosAPI.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina")));
                profs.add(p);
            }
        } catch (Exception ex) {
            Debug.println("Problemas ao carregar proficiencias:\n" + ex);
        }

        close();
        return profs;
    }

}
