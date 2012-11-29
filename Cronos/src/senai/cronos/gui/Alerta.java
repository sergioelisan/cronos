/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.gui;

import javax.swing.JDialog;

/**
 *
 * @author Eduardo
 */
public class Alerta {

    /** */
    public static void jogarAviso(String exception) {
        Update u = new Update();
        JDialog dia = new JDialog();

        dia.setBounds(400, 300, 450, 250);

        dia.setContentPane(u);
        dia.setVisible(true);
        dia.setLocationRelativeTo(null);
        dia.toFront();
        
        u.getProgresso().setVisible(false);
        u.setTitulo("FALHA!");
        u.setAtualiza(exception);
    }
    
    /** 
     * Imprime uma mensagem em um Dialog
     * @param mensagem a ser impressa
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
