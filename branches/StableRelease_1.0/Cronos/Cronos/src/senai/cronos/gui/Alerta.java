/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.gui;

import java.awt.Dimension;
import javax.swing.JDialog;

/**
 *
 * @author Eduardo
 */
public class Alerta {

    /**
     * exibe um alerta com mensagem de erro
     * @param Exception
     */
    public static void jogarAviso(String exception) {
        ExceptionUI exui = new ExceptionUI(exception);

        JDialog dia = new JDialog();
        dia.setSize(new Dimension(600, 300));
        dia.setContentPane(exui);
        dia.setVisible(true);
        dia.setLocationRelativeTo(null);
        dia.toFront();
    }

    /**
     * exibe um alerta com mensagens
     * @param titulo
     * @param mensagem
     */
    public static void showMensagem(String titulo, String mensagem) {
        Update u = new Update();
        JDialog dia = new JDialog();

        dia.setBounds(400, 300, 450, 250);

        dia.setContentPane(u);
        dia.setVisible(true);
        dia.setLocationRelativeTo(null);
        dia.toFront();
        u.getProgresso().setVisible(false);
        u.setTitulo(titulo);
        u.setAtualiza(mensagem);
    }

}
