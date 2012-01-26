package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.LinkedList;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.util.Observador;
import senai.cronos.database.DatabaseUtil;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.entidades.enums.Formacao;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.debug.Contador;
import senai.cronos.util.Tupla;

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

        this.addProficiencia(u);
        this.addOcupacao(u);
        
        close();
        notifica();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("docente.delete");

        removeOcupacao((Integer) id);
        removeProficiencia((Integer) id);

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
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

        this.updateOcupacao(u);
        this.updateProficiencia(u);
        
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
                docente.setOcupacao(this.listOcupacao(docente));
                docente.setProficiencias(this.listProficiencia(docente));
                
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
                docente.setOcupacao(this.listOcupacao(docente));
                docente.setProficiencias(this.listProficiencia(docente));
            }
        } catch (ClassNotFoundException ex) {
            // TODO Tratar essa excecao direitinho
        }
        
        close();
        return docente;
    }

    /**
     * Adiciona as ocupacoes padrao de um docente
     * @param u 
     */
    private void addOcupacao(Docente u) throws SQLException {
        String query = DatabaseUtil.query("docente.ocupacao.insert");

        for (Date dia : u.getOcupacao().keySet()) {
            Map<Turno, Tupla<Aula, Aula>> ocupacao = u.getOcupacao().get(dia);
            for (Turno t : ocupacao.keySet()) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setInt(1, u.getMatricula());
                    ps.setDate(2, new java.sql.Date(dia.getTime()));
                    ps.setInt(3, t.ordinal());

                    Tupla<Aula, Aula> tupla = ocupacao.get(t);
                    Aula a1 = tupla.getPrimeiro();
                    Aula a2 = tupla.getSegundo();

                    ps.setInt(4, a1.getId());
                    ps.setInt(5, a2.getId());

                    ps.execute();
                }
            }
        }
    }

    /**
     * Atualiza a ocupacao de um docente
     * @param u
     * @throws SQLException 
     */
    private void updateOcupacao(Docente u) throws SQLException {
        String query = DatabaseUtil.query("docente.ocupacao.update");

        for (Date dia : u.getOcupacao().keySet()) {
            Map<Turno, Tupla<Aula, Aula>> ocupacao = u.getOcupacao().get(dia);
            for (Turno t : ocupacao.keySet()) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    // update
                    ps.setInt(1, t.ordinal());

                    Tupla<Aula, Aula> tupla = ocupacao.get(t);
                    Aula a1 = tupla.getPrimeiro();
                    Aula a2 = tupla.getSegundo();

                    ps.setInt(2, a1.getId());
                    ps.setInt(3, a2.getId());

                    // where
                    ps.setInt(4, u.getMatricula());
                    ps.setDate(5, new java.sql.Date(dia.getTime()));

                    ps.execute();
                }
            }
        }
    }

    /**
     * Lista todas as ocupacoes de um determinado docente
     * @param matricula
     * @return 
     */
    private Map<Date, Map<Turno, Tupla<Aula, Aula>>> listOcupacao(Docente dc)
            throws SQLException, ClassNotFoundException {
        Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao = new HashMap<>();
        String query = DatabaseUtil.query("docente.ocupacao.select");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, dc.getMatricula());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date dia = rs.getDate("dia");
                Turno t = Turno.getTurno(rs.getInt("turno"));

//                DAOAula dao = new DAOAula();
//                Aula a1 = dao.get(rs.getInt("primeiraparte"));
//                Aula a2 = dao.get(rs.getInt("segundaparte"));
//                
//                if(!ocupacao.containsKey(dia)) {
//                    Map<Turno, Tupla<Aula, Aula> > map = new HashMap<>();
//                    map.put(t, new Tupla<>(a1, a2));
//                    ocupacao.put(dia, map);
//                } else {
//                    ocupacao.get(dia).put(t, new Tupla<>(a1, a2));
//                }                    
            }
        }


        return ocupacao;
    }

    /**
     * Remove todas as ocupacoes de um docente
     * @param matricula
     * @throws SQLException 
     */
    private void removeOcupacao(Integer matricula) throws SQLException {
        String query = DatabaseUtil.query("docente.ocupacao.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, matricula);

            ps.execute();
        }
    }

    /**
     * adiciona proficiencias padrao para um docente
     * @param d 
     */
    private void addProficiencia(Docente d) throws SQLException {
        String query = DatabaseUtil.query("docente.proficiencia.insert");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            for (Proficiencia p : d.getProficiencias()) {
                ps.setInt(1, d.getMatricula());
                ps.setInt(2, p.getDisciplina().getId());
                ps.setInt(3, p.getLecionado());
                ps.setInt(4, p.getNivel());

                ps.execute();
            }
        }
    }

    /**
     * atualiza as proficiencias de um docente
     * @param d 
     */
    private void updateProficiencia(Docente d) throws SQLException {
        String query = DatabaseUtil.query("docente.proficiencia.update");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            for (Proficiencia p : d.getProficiencias()) {
                // update
                ps.setInt(1, p.getLecionado());
                ps.setInt(2, p.getNivel());

                // where
                ps.setInt(3, d.getMatricula());
                ps.setInt(4, p.getDisciplina().getId());

                ps.execute();
            }
        }
    }
    
    /**
     * Lista todas as proficiencias de um docente
     * @param dc
     * @return 
     */
    private List<Proficiencia> listProficiencia(Docente dc) throws SQLException {
        List<Proficiencia> profs = new ArrayList<>();
        String query = DatabaseUtil.query("docente.proficiencia.select");
        
        try(PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, dc.getMatricula() );  
            ResultSet rs = ps.executeQuery();
            
            while(rs.next() ) {
                Proficiencia p = new Proficiencia();
                p.setLecionado(rs.getInt("nivel"));
                p.setNivel(rs.getInt("scoretemp"));
                p.setUnidadecurricular(Fachada.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina")));
                profs.add(p);
            }
        } catch (Exception ex) {
            // TODO tratar essa excessao
        }
        
        return profs;
    }

    /**
     * Remove todas as proficiencias do docente
     * @param matricula
     * @throws SQLException 
     */
    private void removeProficiencia(Integer matricula) throws SQLException {
        String query = DatabaseUtil.query("docente.proficiencia.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, matricula);
            ps.execute();
        }
    }
    
    private Connection con;

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
}
