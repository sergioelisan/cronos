/*
 * HorariosOpcoesPanel.java
 *
 * Created on 22/12/2011, 11:00:39
 */
package senai.cronos.gui.horarios;

import senai.cronos.CronosAPI;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorariosOpcoesPanel extends javax.swing.JPanel {

    public HorariosOpcoesPanel() {
        initComponents();

        if (CronosAPI.getAulasDia() == 0) {
            checkBoxAulasDia.setSelected(false);
        } else {
            checkBoxAulasDia.setSelected(true);
        }

        if (CronosAPI.getAlternancia() == 0) {
            checkBoxAlternancia.setSelected(false);
        } else {
            checkBoxAlternancia.setSelected(true);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        lbstatus = new javax.swing.JLabel();
        checkBoxAlternancia = new javax.swing.JCheckBox();
        checkBoxAulasDia = new javax.swing.JCheckBox();

        setBackground(new java.awt.Color(255, 255, 255));
        setOpaque(false);

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel1.setText("disciplinas em dias alternados?");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("duas aulas por dia?");

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(61, 61, 61));
        jLabel4.setText("opções de geração de horários");

        lbstatus.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        checkBoxAlternancia.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxAlternancia.setOpaque(false);
        checkBoxAlternancia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxAlternanciaItemStateChanged(evt);
            }
        });

        checkBoxAulasDia.setBackground(new java.awt.Color(255, 255, 255));
        checkBoxAulasDia.setOpaque(false);
        checkBoxAulasDia.addItemListener(new java.awt.event.ItemListener() {
            public void itemStateChanged(java.awt.event.ItemEvent evt) {
                checkBoxAulasDiaItemStateChanged(evt);
            }
        });

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbstatus, javax.swing.GroupLayout.PREFERRED_SIZE, 225, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, 290, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(checkBoxAlternancia)
                            .addComponent(checkBoxAulasDia))
                        .addGap(356, 356, 356))))
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel1, jLabel2});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 37, Short.MAX_VALUE)
                    .addComponent(lbstatus, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel1)
                    .addComponent(checkBoxAlternancia))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(checkBoxAulasDia))
                .addGap(149, 149, 149))
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel1, jLabel2});

    }// </editor-fold>//GEN-END:initComponents

    private void checkBoxAlternanciaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxAlternanciaItemStateChanged
        if (checkBoxAlternancia.isSelected()) {
            CronosAPI.setAlternacia(1);
        } else {
            CronosAPI.setAlternacia(0);
        }
    }//GEN-LAST:event_checkBoxAlternanciaItemStateChanged

    private void checkBoxAulasDiaItemStateChanged(java.awt.event.ItemEvent evt) {//GEN-FIRST:event_checkBoxAulasDiaItemStateChanged
        if (checkBoxAulasDia.isSelected()) {
            CronosAPI.setAulasDia(1);
        } else {
            CronosAPI.setAulasDia(0);
        }
    }//GEN-LAST:event_checkBoxAulasDiaItemStateChanged
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JCheckBox checkBoxAlternancia;
    private javax.swing.JCheckBox checkBoxAulasDia;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel lbstatus;
    // End of variables declaration//GEN-END:variables
}
