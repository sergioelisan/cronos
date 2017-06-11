/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package edu.cronos.gui.custom;

import edu.cronos.util.concurrency.Paralell;

import javax.swing.*;
import java.awt.*;

/**
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
