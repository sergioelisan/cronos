package edu.cronos.database.dao;

import edu.cronos.database.DatabaseUtil;
import edu.cronos.entidades.Docente;
import edu.cronos.entidades.Proficiencia;
import edu.cronos.entidades.UnidadeCurricular;
import edu.cronos.util.debug.Debug;

import java.io.Serializable;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;

/**
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOProficiencia extends DAO<Proficiencia> {

    private static DAO<Proficiencia> instance = new DAOProficiencia();

    private DAOProficiencia() {
    }

    public static DAO<Proficiencia> getInstance() {
        return instance;
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

    public void addAll(List<Proficiencia> proficiencias) throws SQLException {
        for (Proficiencia p : proficiencias) {
            add(p);
        }
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

    /**
     * Remove as referencias de uma unidade curricular da tabela de
     * proficiencias
     *
     * @param id
     * @throws SQLException
     */
    public void removeUC(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("proficiencia.delete.uc");

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

            DAO<UnidadeCurricular> dao = DAOFactory.getDao(UnidadeCurricular.class);

            while (rs.next()) {
                Proficiencia p = new Proficiencia();
                p.setDocente(doc);
                p.setLecionado(rs.getInt("nivel"));
                p.setScoreTemp(rs.getInt("scoretemp"));
                p.setUnidadecurricular(dao.get(rs.getInt("disciplina")));
                profs.add(p);
            }
        } catch (Exception ex) {
            Debug.println("Problemas ao carregar proficiencias:\n" + ex);
        }

        close();
        return profs;
    }
}
