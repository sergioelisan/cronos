/*
 * Tile.java
 *
 * Created on 02/01/2012, 04:06:05
 */
package edu.cronos.gui.custom;

import edu.cronos.gui.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author Sergio Lisan
 */
public class Tile extends JPanel {

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbid;
    private javax.swing.JLabel lbnome;
    // End of variables declaration//GEN-END:variables
    private Color standard = ColorManager.getColor("tile");
    private Color over = ColorManager.claro(ColorManager.getColor("tile"));

    public Tile() {
        initComponents();
        addMouseListener(new MouseHandler());
        lbnome.addMouseListener(new MouseHandler());
        lbid.addMouseListener(new MouseHandler());
    }

    public String getNome() {
        return lbnome.getText();
    }

    public void setNome(String nome) {
        lbnome.setText(nome);
    }

    public String getId() {
        return lbid.getText();
    }

    public void setId(String id) {
        lbid.setText(id);
    }

    public void setClickEvent(MouseAdapter eventhandler) {
        addMouseListener(eventhandler);
        lbnome.addMouseListener(eventhandler);
        lbid.addMouseListener(eventhandler);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbnome = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();

        setBackground(ColorManager.getColor("tile"));
        setMaximumSize(new java.awt.Dimension(192, 192));
        setMinimumSize(new java.awt.Dimension(192, 96));
        setPreferredSize(new java.awt.Dimension(192, 96));

        lbnome.setFont(new java.awt.Font("Segoe UI", 0, 20)); // NOI18N
        lbnome.setForeground(new java.awt.Color(255, 255, 255));
        lbnome.setText("nome");
        lbnome.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbnome.setMaximumSize(new java.awt.Dimension(168, 24));
        lbnome.setMinimumSize(new java.awt.Dimension(168, 24));
        lbnome.setPreferredSize(new java.awt.Dimension(168, 24));

        lbid.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbid.setForeground(new java.awt.Color(255, 255, 255));
        lbid.setText("id");
        lbid.setVerticalAlignment(javax.swing.SwingConstants.TOP);
        lbid.setMaximumSize(new java.awt.Dimension(168, 19));
        lbid.setMinimumSize(new java.awt.Dimension(168, 19));
        lbid.setPreferredSize(new java.awt.Dimension(168, 19));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(lbid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addComponent(lbnome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 29, Short.MAX_VALUE)
                                .addComponent(lbid, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private class MouseHandler extends MouseAdapter {

        public void mouseEntered(MouseEvent e) {
            setBackground(over);
        }

        public void mouseExited(MouseEvent e) {
            setBackground(standard);
        }
    }
}
