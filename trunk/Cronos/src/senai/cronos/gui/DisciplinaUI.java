/*
 * DisciplinaUI.java
 *
 * Created on 22/12/2011, 09:06:51
 */
package senai.cronos.gui;

import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.util.Observador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DisciplinaUI extends javax.swing.JPanel implements Observador {

    private CronosFrame main;
    private List<Nucleo> nucleos;
    private int posicao = -1;

    public DisciplinaUI(CronosFrame main) {
        this.main = main;
        initComponents();
        lbproximo.addMouseListener(new LinkEffectHandler());
        lbanterior.addMouseListener(new LinkEffectHandler());

        try {
            CronosAPI.subscribe(UnidadeCurricular.class, this);
            reloadPNShow();
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

    }

    @Override
    public void update() {
        initData();
    }

    private void reloadPNShow() throws ClassNotFoundException, SQLException {
        int w = pnShow.getPreferredSize().width;
        int turmas = CronosAPI.<UnidadeCurricular>get(UnidadeCurricular.class).size();
        pnShow.setPreferredSize(new Dimension(w, ((110 * turmas) / 3)));
    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    /**
     * inicializa os dados de disciplinas
     */
    private void initData() {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    nucleos = CronosAPI.<Nucleo>get(Nucleo.class);
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao carregas os nucleos:\n" + ex);
                }
            }
        });

        t.start();

        loadDisciplina();
    }

    /**
     * carrega os docentes de um determinado nucleo e imprime na ui
     *
     * @param nucleo
     */
    private void loadDisciplina() {
        pnShow.removeAll();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<UnidadeCurricular> disciplinas;
                    if (posicao == -1) {
                        disciplinas = CronosAPI.<UnidadeCurricular>get(UnidadeCurricular.class);
                        lbnucleoatual.setText("todos");
                    } else {
                        Nucleo nucleo = nucleos.get(posicao);
                        disciplinas = CronosAPI.buscaDisciplinas(nucleo);
                        lbnucleoatual.setText(nucleo.getNome().toLowerCase());
                    }

                    for (UnidadeCurricular d : disciplinas) {
                        Tile ct = new Tile();
                        ct.setNome(d.getNome());
                        ct.setId(String.valueOf(d.getCargaHoraria()) + "h");
                        ct.setClickEvent(new TileClickedHandler());
                        pnShow.add(ct);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao carregas a disciplina:\n" + ex);

                }
            }
        });

        t.start();
        pnShow.repaint();
    }

    /**
     * carrega as informacoes da unidade curricular
     *
     * @param nome
     */
    private void showDisciplinaInfo(final String nome) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UnidadeCurricular uc = CronosAPI.buscaDisciplina(nome);

                    lbnome.setText(uc.getNome());
                    lbnucleo.setText("núcleo: " + uc.getNucleo().getNome());
                    lblab.setText("laboratório: " + uc.getLab().getNome());
                    lbcarga.setText("carga horária: " + String.valueOf(uc.getCargaHoraria()) + "h");
                    lbmodulo.setText("módulo: " + String.valueOf(uc.getModulo()));
                    pnementa.setText(uc.getConteudoProgramatico());

                    reloadPNShow();

                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao exibir informacoes da turma:\n" + ex);
                }
            }
        });

        t.start();
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

        loadDisciplina();
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

        loadDisciplina();
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

            showDisciplinaInfo(tile.getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bthome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btconfig = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        lbmodulo = new javax.swing.JLabel();
        lblab = new javax.swing.JLabel();
        lbcarga = new javax.swing.JLabel();
        lbnucleo = new javax.swing.JLabel();
        lbnome = new javax.swing.JLabel();
        jScrollPane1 = new javax.swing.JScrollPane();
        pnementa = new javax.swing.JTextPane();
        lbproximo = new javax.swing.JLabel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        lbanterior = new javax.swing.JLabel();
        lbnucleoatual = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("disciplinas");

        btconfig.setBackground(ColorManager.getColor("button"));
        btconfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btconfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/config.png"))); // NOI18N
        btconfig.setMaximumSize(new java.awt.Dimension(35, 35));
        btconfig.setMinimumSize(new java.awt.Dimension(35, 35));
        btconfig.setOpaque(true);
        btconfig.setPreferredSize(new java.awt.Dimension(35, 35));
        btconfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btconfigMouseClicked(evt);
            }
        });

        jPanel2.setMaximumSize(new java.awt.Dimension(260, 381));
        jPanel2.setMinimumSize(new java.awt.Dimension(200, 381));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(200, 381));

        lbmodulo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbmodulo.setText("módulo");

        lblab.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lblab.setText("laboratório");

        lbcarga.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbcarga.setText("carga horária");

        lbnucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbnucleo.setText("núcleo");

        lbnome.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbnome.setForeground(ColorManager.getColor("foreground"));
        lbnome.setText("unidade curricular");

        pnementa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        pnementa.setText("conteúdo programático");
        jScrollPane1.setViewportView(pnementa);

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbnucleo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbcarga, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 194, Short.MAX_VALUE)
                            .addComponent(lbmodulo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lblab, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(129, 129, 129))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(jScrollPane1, javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(lbnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbnome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbnucleo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbcarga)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbmodulo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lblab)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 212, Short.MAX_VALUE)
                .addContainerGap())
        );

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

        pnShow.setMaximumSize(new java.awt.Dimension(835, 3080));
        pnShow.setMinimumSize(new java.awt.Dimension(600, 3080));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(600, 3080));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        magicScroll1.setViewportView(pnShow);

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
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 995, Short.MAX_VALUE))
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 333, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(btconfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(bthome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 21, Short.MAX_VALUE)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(magicScroll1, javax.swing.GroupLayout.PREFERRED_SIZE, 614, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void btconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btconfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btconfigMouseClicked

    private void lbanteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbanteriorMouseClicked
        previousNucleo();
    }//GEN-LAST:event_lbanteriorMouseClicked

    private void lbproximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbproximoMouseClicked
        nextNucleo();
    }//GEN-LAST:event_lbproximoMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbanterior;
    private javax.swing.JLabel lbcarga;
    private javax.swing.JLabel lblab;
    private javax.swing.JLabel lbmodulo;
    private javax.swing.JLabel lbnome;
    private javax.swing.JLabel lbnucleo;
    private javax.swing.JLabel lbnucleoatual;
    private javax.swing.JLabel lbproximo;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnShow;
    private javax.swing.JTextPane pnementa;
    // End of variables declaration//GEN-END:variables
}
