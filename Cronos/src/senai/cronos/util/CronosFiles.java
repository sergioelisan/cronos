/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.File;

/**
 *
 * @author sergio
 */
public class CronosFiles {

    /**
     * retorna o local da base de dados. Em ambientes Windows, ela fica na pasta
     * "Documentos\banco" e em sistemas Unix "~/.cronos"
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
    

}
