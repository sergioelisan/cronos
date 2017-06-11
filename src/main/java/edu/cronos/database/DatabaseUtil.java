package edu.cronos.database;

import java.sql.Connection;
import java.sql.SQLException;
import java.util.ResourceBundle;

/**
 * @author Sergio Lisan
 */
public class DatabaseUtil {

    public static String query(String key) {
        ResourceBundle rb = ResourceBundle.getBundle("queries");
        return rb.getString(key);
    }

    /**
     * encapsula a chamada de metodo para obtencao de uma conexao
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public static Connection conexao() throws SQLException {
        return new ConnectionFactory().getConnection();
    }
}
