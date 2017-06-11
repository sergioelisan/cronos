/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cronos.util;

import java.io.File;

/**
 * @author sergio
 */
public class CronosFiles {

    /**
     * retorna o local da base de dados. Em ambientes Windows, ela fica na pasta
     * "Documentos\banco" e em sistemas Unix "~/.cronos"
     *
     * @return
     */
    public static String getCronosDatabase() {
        String userHome = System.getProperty("user.home");

        String cronosDatabaseDir = "Documents\\banco";
        File databaseDir = new File(userHome + File.separator + cronosDatabaseDir);

        if (!databaseDir.exists()) {
            cronosDatabaseDir = ".cronos";
        }

        return userHome + File.separator + cronosDatabaseDir;
    }

    /**
     * Retorna o path para o diretorio usado para armazenar os xlsx exportados
     * com os horarios das turmas
     *
     * @return
     */
    public static String getCronosExportDir() {
        String userHome = System.getProperty("user.home");

        String desktop = "Desktop";
        File databaseDir = new File(userHome + File.separator + desktop);

        if (!databaseDir.exists()) {
            desktop = "";
        }

        return userHome + File.separator + desktop + File.separator;
    }
}
