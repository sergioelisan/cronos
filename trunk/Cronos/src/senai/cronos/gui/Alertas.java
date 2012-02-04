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
public class Alertas {
    
     Update u=new Update();
            JDialog dia = new JDialog();
     public void jogarAviso(String Exception){
        dia.setBounds(400, 300, 450, 250);

        dia.setContentPane(u);
        dia.setVisible(true);
        dia.toFront();
        u.getProgresso().setVisible(false);
        u.setTitulo("Mensagem");
        u.setAtualiza("FALHA! mensagem:"+Exception); 
     }

    public Alertas() {
    }
            
    
}
