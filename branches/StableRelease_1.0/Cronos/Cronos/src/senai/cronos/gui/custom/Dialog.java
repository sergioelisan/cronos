/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.gui.custom;

import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JPanel;

import senai.util.concurrency.Paralell;

/**
 *
 * @author Sergio
 */
public class Dialog {

    /**
     * Retorna uma dialog com uma mensagem
     *
     * @param message
     * @return
     */
    public static JDialog getDialog(final String message) {
        final JDialog dialog = new JDialog();
        final JPanel panel = new JPanel(new BorderLayout());
        
        Paralell.start(new Runnable() {
            @Override
            public void run() {
                dialog.setSize(new Dimension(400, 200));
                dialog.setLocationRelativeTo(null);

                
                panel.setMinimumSize(new Dimension(380, 180));
                panel.setMaximumSize(new Dimension(380, 180));
               
                panel.add(new JLabel(message), BorderLayout.CENTER);
                
                dialog.setContentPane(panel);
                dialog.setVisible(true);
            }
        });

        return dialog;
    }
}
