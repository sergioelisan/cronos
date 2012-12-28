/*
 * CadastroLaboratorios.java
 *
 * Created on 03/01/2012, 03:47:18
 */
package senai.cronos.gui.cadastro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextField;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Laboratorio;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.Dialog;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;

/**
 *
 * @author Sergio Lisan
 */
public class CadastroLaboratorios extends javax.swing.JPanel {

    private List<Laboratorio> laboratorios;

    public CadastroLaboratorios() {
        initComponents();

        btnovo.addMouseListener(new LinkEffectHandler());
        btremove.addMouseListener(new LinkEffectHandler());
        btsave.addMouseListener(new LinkEffectHandler());

        novo();
    }

    private void novo() {
        load();
        lbcodigo.setText("código");
        txtdescricao.setText("descrição");
        txtnome.setText("nome");
    }

    /**
     * salva um novo lab no banco
     */
    private void save() {
        JDialog dialog = Dialog.getDialog("Salvando Lab. Aguarde...");

        Laboratorio lab = new Laboratorio();
        lab.setNome(txtnome.getText().trim());
        lab.setDescricao(txtdescricao.getText().trim());

        String code = lbcodigo.getText();
        try {
            if (code.equals("código")) {
                CronosAPI.add(lab);
            } else {
                lab.setId(Integer.parseInt(code));
                CronosAPI.update(lab);
            }
        } catch (ClassNotFoundException | SQLException ex) {
            Alerta.jogarAviso(ex.getMessage());
        } finally {
            novo();
            dialog.dispose();
        }
    }

    /**
     * remove um lab do banco
     */
    private void remove() {
        JDialog dialog = Dialog.getDialog("Removendo Lab. Aguarde...");
        String code = lbcodigo.getText();
        if (!code.equals("código")) {
            Integer id = Integer.parseInt(code);
            try {
                CronosAPI.remove(Laboratorio.class, id);
            } catch (ClassNotFoundException | SQLException ex) {
                Alerta.jogarAviso(ex.getMessage());
            } finally {
                novo();
                dialog.dispose();
            }
        }

    }

    /**
     * carrega os labs do banco
     */
    private void load() {
        pnShow.removeAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    laboratorios = CronosAPI.<Laboratorio>get(Laboratorio.class);
                    for (Laboratorio lab : laboratorios) {
                        Tile ct = new Tile();
                        ct.setId(lab.getId() + "");
                        ct.setNome(lab.getNome());
                        ct.setClickEvent(new TileClickedHandler());
                        pnShow.add(ct);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                }
                pnShow.repaint();
            }
        }).start();
    }

    /**
     * exibe os labs do banco
     *
     * @param code
     */
    private void show(final String code) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Laboratorio lab = CronosAPI.<Laboratorio>get(Laboratorio.class, Integer.parseInt(code));
                    lbcodigo.setText(String.valueOf(lab.getId()));
                    txtnome.setText(lab.getNome());
                    txtdescricao.setText(lab.getDescricao());
                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                }
            }
        }).start();
    }

    /**
     * classe interna que trata dos eventos do mouse
     */
    private class TileClickedHandler extends MouseAdapter {

        @Override
        public void mouseClicked(MouseEvent e) {
            Tile tile;
            if (e.getSource() instanceof JLabel) {
                tile = (Tile) ((JLabel) e.getSource()).getParent();
            } else {
                tile = (Tile) e.getSource();
            }

            show(tile.getId());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        btremove = new javax.swing.JLabel();
        btsave = new javax.swing.JLabel();
        txtdescricao = new javax.swing.JTextField();
        btnovo = new javax.swing.JLabel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        lbcodigo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1342, 591));
        setMinimumSize(new java.awt.Dimension(1024, 591));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1342, 591));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("laboratórios");

        txtnome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnome.setForeground(new java.awt.Color(130, 130, 130));
        txtnome.setText("nome");
        txtnome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnomeFocusLost(evt);
            }
        });

        btremove.setBackground(new java.awt.Color(255, 255, 255));
        btremove.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btremove.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btremove.setText("remover");
        btremove.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btremove.setMaximumSize(new java.awt.Dimension(100, 25));
        btremove.setMinimumSize(new java.awt.Dimension(100, 25));
        btremove.setPreferredSize(new java.awt.Dimension(100, 25));
        btremove.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btremoveMouseClicked(evt);
            }
        });

        btsave.setBackground(new java.awt.Color(255, 255, 255));
        btsave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btsave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btsave.setText("salvar");
        btsave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btsave.setMaximumSize(new java.awt.Dimension(100, 25));
        btsave.setMinimumSize(new java.awt.Dimension(100, 25));
        btsave.setPreferredSize(new java.awt.Dimension(100, 25));
        btsave.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsaveMouseClicked(evt);
            }
        });

        txtdescricao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtdescricao.setForeground(new java.awt.Color(130, 130, 130));
        txtdescricao.setText("descrição");
        txtdescricao.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtdescricaoFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtdescricaoFocusLost(evt);
            }
        });

        btnovo.setBackground(new java.awt.Color(255, 255, 255));
        btnovo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btnovo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btnovo.setText("novo");
        btnovo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btnovo.setMaximumSize(new java.awt.Dimension(100, 25));
        btnovo.setMinimumSize(new java.awt.Dimension(100, 25));
        btnovo.setPreferredSize(new java.awt.Dimension(100, 25));
        btnovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnovoMouseClicked(evt);
            }
        });

        magicScroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        magicScroll1.setMaximumSize(new java.awt.Dimension(1900, 355));
        magicScroll1.setMinimumSize(new java.awt.Dimension(990, 355));
        magicScroll1.setPreferredSize(new java.awt.Dimension(1900, 355));

        pnShow.setMaximumSize(new java.awt.Dimension(1340, 3000));
        pnShow.setMinimumSize(new java.awt.Dimension(900, 3000));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(1340, 3000));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        magicScroll1.setViewportView(pnShow);

        lbcodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbcodigo.setForeground(new java.awt.Color(130, 130, 130));
        lbcodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcodigo.setText("código");
        lbcodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(lbcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {txtdescricao, txtnome});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 358, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(lbcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {txtdescricao, txtnome});

    }// </editor-fold>//GEN-END:initComponents

    private void btnovoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btnovoMouseClicked
        novo();
    }//GEN-LAST:event_btnovoMouseClicked

    private void btsaveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsaveMouseClicked
        save();
    }//GEN-LAST:event_btsaveMouseClicked

    private void btremoveMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btremoveMouseClicked
        remove();
    }//GEN-LAST:event_btremoveMouseClicked

    private void txtnomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomeFocusGained
        if (((JTextField) evt.getSource()).getText().equals("nome")) {
            ((JTextField) evt.getSource()).setText(null);
        }
    }//GEN-LAST:event_txtnomeFocusGained

    private void txtnomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomeFocusLost
        if (((JTextField) evt.getSource()).getText().equals("")) {
            ((JTextField) evt.getSource()).setText("nome");
        }
    }//GEN-LAST:event_txtnomeFocusLost

    private void txtdescricaoFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdescricaoFocusGained
        if (((JTextField) evt.getSource()).getText().equals("descrição")) {
            ((JTextField) evt.getSource()).setText(null);
        }
    }//GEN-LAST:event_txtdescricaoFocusGained

    private void txtdescricaoFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtdescricaoFocusLost
        if (((JTextField) evt.getSource()).getText().equals("")) {
            ((JTextField) evt.getSource()).setText("descrição");
        }
    }//GEN-LAST:event_txtdescricaoFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnovo;
    private javax.swing.JLabel btremove;
    private javax.swing.JLabel btsave;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbcodigo;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnShow;
    private javax.swing.JTextField txtdescricao;
    private javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
