/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cronos.util;

import edu.cronos.entidades.UnidadeCurricular;
import edu.cronos.gui.Alerta;

import java.io.*;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

/**
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
