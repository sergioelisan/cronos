/*
 * CadastroTurmas.java
 *
 * Created on 03/01/2012, 03:46:49
 */
package senai.cronos.gui.cadastro;

import java.awt.HeadlessException;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.Habilitacao;
import senai.cronos.entidades.Turno;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.Dialog;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.util.Observador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class CadastroTurmas extends javax.swing.JPanel implements Observador {

    private List<Nucleo> nucleos;
    private int posicao = -1;

    public CadastroTurmas() {
        initComponents();

        lbproximo.addMouseListener(new LinkEffectHandler());
        lbanterior.addMouseListener(new LinkEffectHandler());
        btnovo.addMouseListener(new LinkEffectHandler());
        btremove.addMouseListener(new LinkEffectHandler());
        btsave.addMouseListener(new LinkEffectHandler());

        for (Habilitacao hab : Habilitacao.values()) {
            combohabilitacao.addItem(hab.name().toLowerCase());
        }

        comboturno.addItem("manhã");
        comboturno.addItem("tarde");
        comboturno.addItem("noite");

        // recebe atualizacoes do DAONucleo assim que alguma coisa for alterada
        try {
            CronosAPI.subscribe(Nucleo.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
            ex.printStackTrace(System.out);
        }

        update();
    }

    /**
     * carrega os nucleos para o combobox
     */
    private void loadNucleosCombobox() {
        combonucleo.removeAllItems();
        combonucleo.addItem("-- núcleos --");
        for (Nucleo nc : nucleos) {
            combonucleo.addItem(nc.getNome());
        }

        load();
    }

    /**
     * retorna o núcleo
     *
     * @param nome
     * @return
     */
    private Nucleo getNucleo(String nome) {
        for (Nucleo nc : nucleos) {

            if (nc.getNome().equals(nome)) {
                return nc;
            }
        }
        return null;
    }

    /**
     * salva uma nova turma no banco
     */
    private void save() {
        JDialog dialog = Dialog.getDialog("Salvando Turma. Aguarde...");
        try {
            Turma turma = new Turma();
            turma.setNome(txtnome.getText().trim());
            turma.setEntrada(dateentrada.getDate());
            turma.setHabilitacao(combohabilitacao.getSelectedIndex() - 1);
            turma.setTurno(Turno.getTurno(comboturno.getSelectedIndex() - 1));
            turma.setNucleo(getNucleo(combonucleo.getSelectedItem().toString()));

            if (lbcodigo.getText().equals("código")) {
                CronosAPI.add(turma);
            } else {
                turma.setId(Integer.parseInt(lbcodigo.getText()));
                CronosAPI.update(turma);
            }

        } catch (ClassNotFoundException | SQLException | HeadlessException | NumberFormatException e) {
            Alerta.jogarAviso(e.getMessage());
            e.printStackTrace(System.out);
        } finally {
            novo();
            dialog.dispose();
        }
        load();
    }

    /**
     * remove uma turma do banco
     */
    private void remove() {
        JDialog dialog = Dialog.getDialog("Removendo Turma. Aguarde...");
        
        String code = lbcodigo.getText();
        if (!code.equals("código")) {
            try {
                Integer id = Integer.parseInt(code);
                CronosAPI.remove(Turma.class, id);
            } catch (ClassNotFoundException | SQLException e) {
                Alerta.jogarAviso(e.getMessage());
                e.printStackTrace(System.out);
            } finally {
                novo();
                dialog.dispose();
            }
        }
        load();
    }

    /**
     * limpa os campos pra inserção de novos dados
     */
    private void novo() {
        load();
        lbcodigo.setText("código");
        txtnome.setText("nome");
        dateentrada.setDate(null);
        combohabilitacao.setSelectedIndex(0);
        combonucleo.setSelectedIndex(0);
        comboturno.setSelectedIndex(0);
    }

    /**
     * carrega uma turma selecionada
     */
    private void load() {
        pnShow.removeAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Turma> turmas;


                    if (posicao == -1) {
                        turmas = CronosAPI.<Turma>get(Turma.class);
                        lbnucleoatual.setText(
                                "todos");
                    } else {
                        Nucleo nucleo = nucleos.get(posicao);
                        turmas = CronosAPI.buscaTurma(nucleo);
                        lbnucleoatual.setText(nucleo.getNome().toLowerCase());
                    }

                    for (Turma turma : turmas) {
                        Tile ct = new Tile();
                        ct.setNome(turma.getNome());
                        ct.setId(turma.getNucleo().getNome() + "");
                        ct.setClickEvent(new TileClickedHandler());
                        pnShow.add(ct);
                    }

                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
                pnShow.repaint();
            }
        }).start();        
    }

    /**
     * Exibe os dados de uma turma selecionada
     *
     * @param nome
     */
    private void show(final String nome) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Turma t = CronosAPI.buscaTurma(nome);
                    lbcodigo.setText(String.valueOf(t.getId()));
                    dateentrada.setDate(t.getEntrada());
                    txtnome.setText(t.getNome());
                    combohabilitacao.setSelectedIndex(t.getHabilitacao() + 1);
                    combonucleo.setSelectedItem(t.getNucleo().getNome());
                    comboturno.setSelectedIndex(t.getTurno().ordinal() + 1);

                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                    ex.printStackTrace(System.out);
                }
            }
        }).start();
    }

    @Override
    /**
     * Assim que a base de dados é atualizada, esse metodo atualiza os dados da
     * GUI de cadastro
     */
    public void update() {
        try {
            nucleos = CronosAPI.<Nucleo>get(Nucleo.class);
            loadNucleosCombobox();
        } catch (ClassNotFoundException | SQLException ex) {
            Alerta.jogarAviso(ex.getMessage());
            ex.printStackTrace(System.out);
        }
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

            show(tile.getNome());
        }
    }

    /**
     * passa para o proximo nucleo
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void nextNucleo() {
        if (++posicao == nucleos.size()) {
            posicao = -1;
        }
        load();
    }

    /**
     * volta para o nucleo anterior
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void previousNucleo() {
        if (--posicao == -2) {
            posicao = nucleos.size() - 1;
        }
        load();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        btsave = new javax.swing.JLabel();
        btremove = new javax.swing.JLabel();
        btnovo = new javax.swing.JLabel();
        combonucleo = new javax.swing.JComboBox();
        lbproximo = new javax.swing.JLabel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        txtnome = new javax.swing.JTextField();
        lbcodigo = new javax.swing.JLabel();
        lbnucleoatual = new javax.swing.JLabel();
        lbanterior = new javax.swing.JLabel();
        comboturno = new javax.swing.JComboBox();
        combohabilitacao = new javax.swing.JComboBox();
        dateentrada = new org.jdesktop.swingx.JXDatePicker();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1342, 591));
        setMinimumSize(new java.awt.Dimension(1024, 591));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1342, 591));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("turmas");

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

        combonucleo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        combonucleo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- núcleo --" }));

        lbproximo.setBackground(new java.awt.Color(255, 255, 255));
        lbproximo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbproximo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbproximo.setText("próximo núcleo");
        lbproximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbproximo.setMaximumSize(new java.awt.Dimension(100, 25));
        lbproximo.setMinimumSize(new java.awt.Dimension(100, 25));
        lbproximo.setPreferredSize(new java.awt.Dimension(100, 25));
        lbproximo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbproximoMouseClicked(evt);
            }
        });

        magicScroll1.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        magicScroll1.setActiveWheelGesture(true);
        magicScroll1.setMaximumSize(new java.awt.Dimension(1900, 310));
        magicScroll1.setMinimumSize(new java.awt.Dimension(990, 310));
        magicScroll1.setPreferredSize(new java.awt.Dimension(1900, 310));

        pnShow.setMaximumSize(new java.awt.Dimension(1340, 3000));
        pnShow.setMinimumSize(new java.awt.Dimension(990, 3000));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(1340, 3000));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        magicScroll1.setViewportView(pnShow);

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

        lbcodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        lbcodigo.setForeground(new java.awt.Color(130, 130, 130));
        lbcodigo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcodigo.setText("código");
        lbcodigo.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(190, 190, 190)));

        lbnucleoatual.setBackground(new java.awt.Color(255, 255, 255));
        lbnucleoatual.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbnucleoatual.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbnucleoatual.setText("núcleo");
        lbnucleoatual.setBorder(javax.swing.BorderFactory.createLineBorder(ColorManager.getColor("border")));
        lbnucleoatual.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbnucleoatual.setMaximumSize(new java.awt.Dimension(100, 25));
        lbnucleoatual.setMinimumSize(new java.awt.Dimension(100, 25));
        lbnucleoatual.setOpaque(true);
        lbnucleoatual.setPreferredSize(new java.awt.Dimension(100, 25));

        lbanterior.setBackground(new java.awt.Color(255, 255, 255));
        lbanterior.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbanterior.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbanterior.setText("núcleo anterior");
        lbanterior.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbanterior.setMaximumSize(new java.awt.Dimension(100, 25));
        lbanterior.setMinimumSize(new java.awt.Dimension(100, 25));
        lbanterior.setPreferredSize(new java.awt.Dimension(100, 25));
        lbanterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbanteriorMouseClicked(evt);
            }
        });

        comboturno.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboturno.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- turno --" }));

        combohabilitacao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        combohabilitacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- habilitação --" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                                    .addComponent(lbcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                    .addComponent(btnovo, javax.swing.GroupLayout.DEFAULT_SIZE, 109, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(dateentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                    .addGroup(layout.createSequentialGroup()
                                        .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(combohabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addGap(18, 18, 18)
                                        .addComponent(comboturno, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                        .addGap(0, 499, Short.MAX_VALUE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 910, Short.MAX_VALUE)
                        .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(magicScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 310, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(24, 24, 24)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(dateentrada, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboturno, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combohabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(18, 18, 18)
                        .addComponent(lbcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
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

    private void lbanteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbanteriorMouseClicked
        previousNucleo();
    }//GEN-LAST:event_lbanteriorMouseClicked

    private void lbproximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbproximoMouseClicked
        nextNucleo();
    }//GEN-LAST:event_lbproximoMouseClicked

    private void txtnomeFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomeFocusGained
        if (txtnome.getText().equals("nome")) {
            txtnome.setText(null);
        }
    }//GEN-LAST:event_txtnomeFocusGained

    private void txtnomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomeFocusLost
        if (txtnome.getText().equals("")) {
            txtnome.setText("nome");
        }
    }//GEN-LAST:event_txtnomeFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnovo;
    private javax.swing.JLabel btremove;
    private javax.swing.JLabel btsave;
    private javax.swing.JComboBox combohabilitacao;
    private javax.swing.JComboBox combonucleo;
    private javax.swing.JComboBox comboturno;
    private org.jdesktop.swingx.JXDatePicker dateentrada;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbanterior;
    private javax.swing.JLabel lbcodigo;
    private javax.swing.JLabel lbnucleoatual;
    private javax.swing.JLabel lbproximo;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnShow;
    private javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
