/*
 * ConfigUI.java
 *
 * Created on 22/12/2011, 09:07:12
 */
package senai.cronos.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.DefaultListModel;
import javax.swing.JList;
import javax.swing.JOptionPane;
import senai.cronos.Fachada;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.events.LinkEffectHandler;
import senai.cronos.util.Feriado;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class ConfigUI extends javax.swing.JPanel {

    private CronosFrame main;

    public ConfigUI(CronosFrame main) {
        try {
            this.main = main;
            initComponents();
            
            // graças aos designers do tio java, tem que se criar um novo list model
            // pq o que ja vem dentro de JList é incompativel com o Default... ¬¬'
            DefaultListModel lm = new DefaultListModel();
            listFeriados.setModel(lm);
            
            panelferiadoadd.setVisible(false);
            
            btaddferiado.addMouseListener(new LinkEffectHandler());
            btremoveferiado.addMouseListener(new LinkEffectHandler());
            btcancelar.addMouseListener(new LinkEffectHandler());
            btsave.addMouseListener(new LinkEffectHandler());

            Date inicio = Fachada.getInicioCalendario();
            Date fim = Fachada.getFimCalendario();

            datepickerFim.setDate(fim);
            datepickerInicio.setDate(inicio);

            loadFeriados();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao carregar as configurações\n" + ex);
            ex.printStackTrace();
        }
    }

    /**
     * atualiza a lista de feriados
     */
    private void loadFeriados() {
        try {
            ((DefaultListModel<Feriado>)listFeriados.getModel() ).clear();
            
            // logica de carregamento de feriados
            List<Feriado> feriados = Fachada.<Feriado>get(Feriado.class);
            DefaultListModel lm = new DefaultListModel();
            Collections.sort(feriados);

            for (Feriado feriado : feriados) {
                lm.addElement(feriado);
            }

            listFeriados.setModel(lm);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao carregar os feriados\n" + ex);
            ex.printStackTrace();
        }
    }

    /**
     * armazena um feriado no banco
     */
    private void saveFeriado() {
        try {
            Date feriado = pickFeriado.getDate();
            String descricao = txtdescricao.getText().trim();
            Feriado f = new Feriado(feriado, descricao);
            Fachada.add(f);

            loadFeriados();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar feriado\n" + ex);
        }
    }

    /**
     * Remove um feriado do banco
     *
     * @param f
     */
    private void removeFeriado(Feriado f) {
        try {
            Fachada.remove(Feriado.class, f.getDia());
            loadFeriados();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao remover feriado\n" + ex);
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);

        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bthome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        jLabel4 = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        datepickerInicio = new org.jdesktop.swingx.JXDatePicker();
        datepickerFim = new org.jdesktop.swingx.JXDatePicker();
        jPanel2 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        listFeriados = new javax.swing.JList();
        btaddferiado = new javax.swing.JLabel();
        btremoveferiado = new javax.swing.JLabel();
        panelferiadoadd = new javax.swing.JPanel();
        pickFeriado = new org.jdesktop.swingx.JXDatePicker();
        jLabel6 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        txtdescricao = new javax.swing.JTextField();
        btcancelar = new javax.swing.JLabel();
        btsave = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
        setPreferredSize(new java.awt.Dimension(1366, 728));

        bthome.setBackground(ColorManager.getColor("button"));
        bthome.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        bthome.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/home.png"))); // NOI18N
        bthome.setMaximumSize(new java.awt.Dimension(35, 35));
        bthome.setMinimumSize(new java.awt.Dimension(35, 35));
        bthome.setOpaque(true);
        bthome.setPreferredSize(new java.awt.Dimension(35, 35));
        bthome.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                bthomeMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("configurações");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(ColorManager.getColor("foreground"));
        jLabel4.setText("calendário");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel3.setText("fim do calendário");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel2.setText("início do calendário");

        datepickerInicio.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datepickerInicioPropertyChange(evt);
            }
        });

        datepickerFim.addPropertyChangeListener(new java.beans.PropertyChangeListener() {
            public void propertyChange(java.beans.PropertyChangeEvent evt) {
                datepickerFimPropertyChange(evt);
            }
        });

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 132, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(datepickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(datepickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2)
                    .addComponent(datepickerInicio, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel3)
                    .addComponent(datepickerFim, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap(13, Short.MAX_VALUE))
        );

        jPanel2.setMaximumSize(new java.awt.Dimension(684, 492));
        jPanel2.setMinimumSize(new java.awt.Dimension(684, 492));
        jPanel2.setOpaque(false);

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(ColorManager.getColor("foreground"));
        jLabel5.setText("feriados");

        listFeriados.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane1.setViewportView(listFeriados);

        btaddferiado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btaddferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btaddferiado.setText("novo");
        btaddferiado.setMaximumSize(new java.awt.Dimension(80, 20));
        btaddferiado.setMinimumSize(new java.awt.Dimension(80, 20));
        btaddferiado.setPreferredSize(new java.awt.Dimension(80, 20));
        btaddferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btaddferiadoMouseClicked(evt);
            }
        });

        btremoveferiado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btremoveferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btremoveferiado.setText("remover");
        btremoveferiado.setMaximumSize(new java.awt.Dimension(80, 20));
        btremoveferiado.setMinimumSize(new java.awt.Dimension(80, 20));
        btremoveferiado.setPreferredSize(new java.awt.Dimension(80, 20));
        btremoveferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btremoveferiadoMouseClicked(evt);
            }
        });

        panelferiadoadd.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel6.setText("dia do feriado");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel9.setText("descrição");

        btcancelar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btcancelar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btcancelar.setText("cancelar");
        btcancelar.setMaximumSize(new java.awt.Dimension(80, 20));
        btcancelar.setMinimumSize(new java.awt.Dimension(80, 20));
        btcancelar.setPreferredSize(new java.awt.Dimension(80, 20));
        btcancelar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btcancelarMouseClicked(evt);
            }
        });

        btsave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btsave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btsave.setText("salvar");
        btsave.setMaximumSize(new java.awt.Dimension(80, 20));
        btsave.setMinimumSize(new java.awt.Dimension(80, 20));
        btsave.setPreferredSize(new java.awt.Dimension(80, 20));
        btsave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsaveMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelferiadoaddLayout = new javax.swing.GroupLayout(panelferiadoadd);
        panelferiadoadd.setLayout(panelferiadoaddLayout);
        panelferiadoaddLayout.setHorizontalGroup(
            panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelferiadoaddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelferiadoaddLayout.createSequentialGroup()
                        .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel9, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(pickFeriado, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(txtdescricao))
                        .addGap(126, 417, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelferiadoaddLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())))
        );
        panelferiadoaddLayout.setVerticalGroup(
            panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelferiadoaddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(pickFeriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 20, Short.MAX_VALUE)
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcancelar, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane1)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5)
                            .addGroup(jPanel2Layout.createSequentialGroup()
                                .addComponent(btaddferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(btremoveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 0, Short.MAX_VALUE))
                    .addComponent(panelferiadoadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 241, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btaddferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremoveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 20, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelferiadoadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(bthome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1))
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap(670, Short.MAX_VALUE))
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bthome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(jPanel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void datepickerInicioPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datepickerInicioPropertyChange
        if (evt.getPropertyName().equals("date")) {
            Fachada.setInicioCalendario(datepickerInicio.getDate());
        }
    }//GEN-LAST:event_datepickerInicioPropertyChange

    private void datepickerFimPropertyChange(java.beans.PropertyChangeEvent evt) {//GEN-FIRST:event_datepickerFimPropertyChange
        if (evt.getPropertyName().equals("date")) {
            Fachada.setFimCalendario(datepickerFim.getDate());
        }
    }//GEN-LAST:event_datepickerFimPropertyChange

    private void btaddferiadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btaddferiadoMouseClicked
        panelferiadoadd.setVisible(true);
    }//GEN-LAST:event_btaddferiadoMouseClicked

    private void btremoveferiadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btremoveferiadoMouseClicked
        int posicao = listFeriados.getSelectedIndex();
        Feriado feriado = ((DefaultListModel<Feriado>) listFeriados.getModel()).get(posicao);
        removeFeriado(feriado);
    }//GEN-LAST:event_btremoveferiadoMouseClicked

    private void btcancelarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btcancelarMouseClicked
        panelferiadoadd.setVisible(false);
    }//GEN-LAST:event_btcancelarMouseClicked

    private void btsaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsaveMouseClicked
        saveFeriado();
    }//GEN-LAST:event_btsaveMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btaddferiado;
    private javax.swing.JLabel btcancelar;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel btremoveferiado;
    private javax.swing.JLabel btsave;
    private org.jdesktop.swingx.JXDatePicker datepickerFim;
    private org.jdesktop.swingx.JXDatePicker datepickerInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JList listFeriados;
    private javax.swing.JPanel panelferiadoadd;
    private org.jdesktop.swingx.JXDatePicker pickFeriado;
    private javax.swing.JTextField txtdescricao;
    // End of variables declaration//GEN-END:variables
}
