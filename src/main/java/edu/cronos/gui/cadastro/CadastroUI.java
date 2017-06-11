/*
 * CadastroUI.java
 *
 * Created on 22/12/2011, 10:27:42
 */
package edu.cronos.gui.cadastro;

import edu.cronos.gui.ColorManager;
import edu.cronos.gui.CronosFrame;
import edu.cronos.gui.custom.ImageLoader;
import edu.cronos.gui.custom.LinkEffectHandler;

import java.awt.*;

/**
 * @author Serginho
 */
public class CadastroUI extends javax.swing.JPanel {

    // End of variables declaration//GEN-END:variables
    public static final String DOCENTE = "docente";
    public static final String TURMAS = "turmas";
    public static final String DISCIPLINAS = "disciplinas";
    public static final String LABS = "laboratórios";
    private CronosFrame main;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lkdisciplinas;
    private javax.swing.JLabel lkdocentes;
    private javax.swing.JLabel lklaboratorio;
    private javax.swing.JLabel lkturmas;
    private javax.swing.JPanel pnContainer;

    public CadastroUI(CronosFrame main) {
        this.main = main;
        initComponents();
        loadPanels();
        Switch(DOCENTE);
        loadEffects();
    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    private void loadPanels() {
        pnContainer.setLayout(new CardLayout());
        pnContainer.add(new CadastroDocente(), DOCENTE);
        pnContainer.add(new CadastroDisciplinas(), DISCIPLINAS);
        pnContainer.add(new CadastroTurmas(), TURMAS);
        pnContainer.add(new CadastroLaboratorios(), LABS);
    }

    private void Switch(String module) {
        ((CardLayout) pnContainer.getLayout()).show(pnContainer, module);
    }

    private void loadEffects() {
        lkdisciplinas.addMouseListener(new LinkEffectHandler());
        lkdocentes.addMouseListener(new LinkEffectHandler());
        lklaboratorio.addMouseListener(new LinkEffectHandler());
        lkturmas.addMouseListener(new LinkEffectHandler());
        lkdisciplinas.setBackground(new Color(50, 50, 200, 20));
        lkdocentes.setBackground(new Color(50, 50, 200, 20));
        lklaboratorio.setBackground(new Color(50, 50, 200, 20));
        lkturmas.setBackground(new Color(50, 50, 200, 20));
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bthome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btconfig = new javax.swing.JLabel();
        lkdocentes = new javax.swing.JLabel();
        lkturmas = new javax.swing.JLabel();
        lkdisciplinas = new javax.swing.JLabel();
        lklaboratorio = new javax.swing.JLabel();
        pnContainer = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
        setPreferredSize(new java.awt.Dimension(1366, 728));

        bthome.setBackground(ColorManager.getColor("button"));
        bthome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bthome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/home.png"))); // NOI18N
        bthome.setMaximumSize(new java.awt.Dimension(35, 35));
        bthome.setMinimumSize(new java.awt.Dimension(35, 35));
        bthome.setOpaque(true);
        bthome.setPreferredSize(new java.awt.Dimension(35, 35));
        bthome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthomeMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("cadastro");

        btconfig.setBackground(ColorManager.getColor("button"));
        btconfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/images/config.png"))); // NOI18N
        btconfig.setMaximumSize(new java.awt.Dimension(35, 35));
        btconfig.setMinimumSize(new java.awt.Dimension(35, 35));
        btconfig.setOpaque(true);
        btconfig.setPreferredSize(new java.awt.Dimension(35, 35));
        btconfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btconfigMouseClicked(evt);
            }
        });

        lkdocentes.setBackground(new java.awt.Color(255, 255, 255));
        lkdocentes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lkdocentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lkdocentes.setText("docentes");
        lkdocentes.setOpaque(true);
        lkdocentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lkdocentesMouseClicked(evt);
            }
        });

        lkturmas.setBackground(new java.awt.Color(255, 255, 255));
        lkturmas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lkturmas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lkturmas.setText("turmas");
        lkturmas.setOpaque(true);
        lkturmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lkturmasMouseClicked(evt);
            }
        });

        lkdisciplinas.setBackground(new java.awt.Color(255, 255, 255));
        lkdisciplinas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lkdisciplinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lkdisciplinas.setText("disciplinas");
        lkdisciplinas.setOpaque(true);
        lkdisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lkdisciplinasMouseClicked(evt);
            }
        });

        lklaboratorio.setBackground(new java.awt.Color(255, 255, 255));
        lklaboratorio.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lklaboratorio.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lklaboratorio.setText("laboratório");
        lklaboratorio.setOpaque(true);
        lklaboratorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lklaboratorioMouseClicked(evt);
            }
        });

        pnContainer.setMaximumSize(new java.awt.Dimension(1342, 591));
        pnContainer.setOpaque(false);
        pnContainer.setPreferredSize(new java.awt.Dimension(1342, 591));
        pnContainer.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bthome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(jLabel1)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btconfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(lkdocentes, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lkturmas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lkdisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                                .addComponent(lklaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 104, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, lkdisciplinas, lkdocentes, lklaboratorio, lkturmas);

        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(btconfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                                .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                                                .addComponent(bthome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(lkdocentes, javax.swing.GroupLayout.PREFERRED_SIZE, 28, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lkturmas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lkdisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(lklaboratorio, javax.swing.GroupLayout.PREFERRED_SIZE, 24, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(pnContainer, javax.swing.GroupLayout.DEFAULT_SIZE, 614, Short.MAX_VALUE)
                                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, lkdisciplinas, lkdocentes, lklaboratorio, lkturmas);

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, bthome, jLabel1);

    }// </editor-fold>//GEN-END:initComponents

    private void lkdocentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lkdocentesMouseClicked
        Switch(DOCENTE);
    }//GEN-LAST:event_lkdocentesMouseClicked

    private void lkturmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lkturmasMouseClicked
        Switch(TURMAS);
    }//GEN-LAST:event_lkturmasMouseClicked

    private void lkdisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lkdisciplinasMouseClicked
        Switch(DISCIPLINAS);
    }//GEN-LAST:event_lkdisciplinasMouseClicked

    private void lklaboratorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lklaboratorioMouseClicked
        Switch(LABS);
    }//GEN-LAST:event_lklaboratorioMouseClicked

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void btconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btconfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btconfigMouseClicked
}
