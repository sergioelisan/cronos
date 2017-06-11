package edu.cronos.database;

import edu.cronos.properties.Preferencias;

import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Properties;

/**
 * Classe que fabrica uma conexao lendo as configuracoes do arquivo
 * connection.properties
 *
 * @author Sergio Lisan
 */
public class ConnectionFactory {

    private String user;
    private String passwd;
    private String url;
    private String driver;

    ConnectionFactory() {
        Properties pp = Preferencias.instance().getConexao();
        user = pp.getProperty("user");
        passwd = pp.getProperty("passwd");
        url = pp.getProperty("url");
        driver = pp.getProperty("driver");
    }

    /**
     * retorna uma conexao com o banco de dados
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    Connection getConnection() throws SQLException {
        try {
            Class.forName(driver);
        } catch (Exception ex) {
        }
        return DriverManager.getConnection(url, user, passwd);
    }
}
