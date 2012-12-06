package senai.cronos.database.dao;

import java.io.Serializable;
import java.sql.Connection;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.DatabaseUtil;
import senai.util.Observado;
import senai.util.Observador;

/**
 *
 * Interface de um objeto de acesso a dados, com metodo de manipulacao de uma tabela
 * de um banco.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public abstract class DAO<T> implements Observado {

    /**
     * adiciona um elemento ao banco de dados
     * @param u
     */
    public abstract void add(T u) throws SQLException;

    /**
     * remove um elemento do banco de dados
     * @param id
     */
    public abstract void remove(Serializable id) throws SQLException;

    /**
     * altera um elemento do banco de dados
     * @param u
     */
    public abstract void update(T u) throws SQLException;

    /**
     * retorna um objeto idenfiticado por sua id
     * @param id
     * @return
     * @throws Exception
     */
    public abstract T get(Serializable id) throws SQLException;

    /**
     * lista elementos de uma tabela do banco de dados
     * @return
     */
    public abstract List<T> get() throws SQLException;

    /** fecha uma conexao com o banco */
    public void close() throws SQLException {
        con.close();
    }

    /** abre uma conexao com o banco */
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
        for (Observador o : observadores) {
            o.update();
        }
    }
    
    /** lista de observadores */
    protected List<Observador> observadores = new ArrayList<>();
    
    /** conexao com o banco */
    protected Connection con;

}
