/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.gui.Alerta;

/**
 *
 * @author Carlos Melo
 */
public class TurmasConfig {

    public TurmasConfig() {
    }

    /**
     * importa as configuracoes de turma
     *
     * @param nomeTurma
     * @return
     */
    public List<UnidadeCurricular> importar(String nomeTurma) {
        List<UnidadeCurricular> unidadesCurriculares = new ArrayList<>();

        File file = new File(nomeTurma + ".tur");
        if (file.exists()) {
            try {
                ObjectInputStream ob = new ObjectInputStream(new FileInputStream(file));
                unidadesCurriculares = (List<UnidadeCurricular>) ob.readObject();
            } catch (IOException | ClassNotFoundException ex) {
                Alerta.jogarAviso(ex.getMessage());
            }
        }

        Collections.sort(unidadesCurriculares);
        return unidadesCurriculares;
    }

    /**
     * exporta a configuracao de uma turma para um arquivo
     *
     * @param nome
     * @param uc
     */
    public void exportar(String nome, List<UnidadeCurricular> unidadesCurriculares) {
        ObjectOutputStream ob;
        Collections.sort(unidadesCurriculares);

        try {
            ob = new ObjectOutputStream(new FileOutputStream(nome + ".tur"));
            ob.writeObject(unidadesCurriculares);
            ob.close();
        } catch (IOException ex) {
            Alerta.jogarAviso(ex.getMessage());
        }
    }
}
