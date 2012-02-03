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
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Observador;
import senai.cronos.util.Tupla;
import senai.cronos.util.debug.Contador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DAOOcupacao implements DAO<Ocupacao> {

    private static DAO<Ocupacao> instance = new DAOOcupacao();

    public static DAO<Ocupacao> getInstance() {
        return instance;
    }

    private DAOOcupacao() {
    }

    @Override
    public void add(Ocupacao u) throws SQLException {
        open();
        String query = DatabaseUtil.query("ocupacao.insert");

        for (Turno turno : u.getOcupacao().keySet()) {
            if (turno == null)
                break;
            
            Map<Date, Tupla<Aula, Aula>> ocupacao = u.getOcupacao().get(turno);
            for (Date dia : ocupacao.keySet()) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    ps.setInt(1, u.getDocente().getMatricula());
                    ps.setInt(2, turno.ordinal());
                    ps.setDate(3, new java.sql.Date(dia.getTime()));

                    Tupla<Aula, Aula> tupla = ocupacao.get(dia);
                    Aula a1 = tupla.getPrimeiro();
                    Aula a2 = tupla.getSegundo();

                    UnidadeCurricular uc = a1.getDisciplina();
                    ps.setInt(4, uc == null ? null : uc.getId());
                    
                    Docente dc = a1.getDocente();
                    ps.setInt(5, dc == null ? null : dc.getMatricula());
                    
                    Laboratorio lb = a1.getLab();
                    ps.setInt(6, lb == null ? null : lb.getId());
                    
                    uc = a2.getDisciplina();
                    ps.setInt(7, uc == null ? null : uc.getId());
                    
                    dc = a2.getDocente();
                    ps.setInt(8, dc == null ? null : dc.getMatricula());
                    
                    lb = a2.getLab();
                    ps.setInt(9, lb == null ? null : lb.getId());

                    ps.execute();
                }
            }
        }
        close();
    }

    @Override
    public void remove(Serializable id) throws SQLException {
        open();
        String query = DatabaseUtil.query("ocupacao.delete");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, (Integer) id);
            ps.execute();
        }

        close();
    }

    @Override
    public void update(Ocupacao u) throws SQLException {
        open();
        String query = DatabaseUtil.query("ocupacao.update");

        for (Turno turno : u.getOcupacao().keySet()) {
            Map<Date, Tupla<Aula, Aula>> ocupacao = u.getOcupacao().get(turno);
            for (Date dia : ocupacao.keySet()) {
                try (PreparedStatement ps = con.prepareStatement(query)) {
                    // update
                    ps.setDate(1, new java.sql.Date(dia.getTime()));

                    Tupla<Aula, Aula> tupla = ocupacao.get(dia);
                    Aula a1 = tupla.getPrimeiro();
                    Aula a2 = tupla.getSegundo();

                    UnidadeCurricular uc = a1.getDisciplina();
                    ps.setInt(2, uc == null ? null : uc.getId());
                    
                    Docente dc = a1.getDocente();
                    ps.setInt(3, dc == null ? null : dc.getMatricula());
                    
                    Laboratorio lb = a1.getLab();
                    ps.setInt(4, lb == null ? null : lb.getId());
                    
                    uc = a2.getDisciplina();
                    ps.setInt(5, uc == null ? null : uc.getId());
                    
                    dc = a2.getDocente();
                    ps.setInt(6, dc == null ? null : dc.getMatricula());
                    
                    lb = a2.getLab();
                    ps.setInt(7, lb == null ? null : lb.getId());

                    // where
                    ps.setInt(8, u.getDocente().getMatricula());
                    ps.setInt(9, turno.ordinal());

                    ps.execute();
                }
            }
        }
        close();
    }

    @Override
    public Ocupacao get(Serializable id) throws SQLException {
        return null;
    }

    /**
     * Retorna a ocupacao de um determinado docente
     * @param docente
     * @return
     * @throws SQLException 
     */
    public Ocupacao get(Docente docente) throws SQLException {
        
        Ocupacao ocup = new Ocupacao(docente);
        Map<Turno, Map<Date, Tupla<Aula, Aula>>> ocupacao = ocup.getOcupacao();

        Turno t1 = docente.getPrimeiroTurno();
        Turno t2 = docente.getSegundoTurno();
        
        getHorarioTurno(ocupacao.get(t1), docente.getMatricula(), t1);
        getHorarioTurno(ocupacao.get(t2), docente.getMatricula(), t2);

        return ocup;
    }

    /**
     * Retorna a ocupacao de um docente em um turno
     *
     * @param matricula
     * @param t
     * @return
     */
    private Map<Date, Tupla<Aula, Aula>> getHorarioTurno(Map<Date, Tupla<Aula, Aula>> horario, Integer matricula, Turno t) throws SQLException {
        open();
        String query = DatabaseUtil.query("ocupacao.get");

        try (PreparedStatement ps = con.prepareStatement(query)) {
            ps.setInt(1, matricula);
            ps.setInt(2, t.ordinal());

            ResultSet rs = ps.executeQuery();

            while (rs.next()) {
                Date dia = rs.getDate("dia");
                Aula a1 = new Aula();

                a1.setDocente(Fachada.<Docente>get(Docente.class, rs.getInt("docente1")));
                a1.setDisciplina(Fachada.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina1")));
                a1.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio1")));

                Aula a2 = new Aula();
                a2.setDocente(Fachada.<Docente>get(Docente.class, rs.getInt("docente2")));
                a2.setDisciplina(Fachada.<UnidadeCurricular>get(UnidadeCurricular.class, rs.getInt("disciplina2")));
                a2.setLab(Fachada.<Laboratorio>get(Laboratorio.class, rs.getInt("laboratorio2")));

                horario.get(dia).setPrimeiro(a1);
                horario.get(dia).setPrimeiro(a2);
            }
        } catch (Exception e) {
        }

        close();
        return horario;
    }

    @Override
    public List<Ocupacao> get() throws SQLException {
        open();
        close();
        return null;
    }

    @Override
    public void close() throws SQLException {
        con.close();
    }

    @Override
    public void open() throws SQLException {
        con = DatabaseUtil.conexao();
        Contador.ocupacoes++;
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
        for (Observador o : observadores) {
            o.update();
        }
    }
    private List<Observador> observadores = new ArrayList<>();
    private Connection con;
}
