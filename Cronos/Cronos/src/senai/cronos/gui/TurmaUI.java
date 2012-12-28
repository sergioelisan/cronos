/*
 * TurmaUI.java
 *
 * Created on 22/12/2011, 09:06:38
 */
package senai.cronos.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.Color;
import java.awt.Dimension;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.ArrayList;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.cronos.gui.custom.ucCheck;
import senai.cronos.gui.horarios.HorariosExibirPanel;
import senai.cronos.gui.horarios.HorariosGerarPanel;
import senai.cronos.gui.horarios.HorariosUI;
import senai.cronos.util.Acumulador;
import senai.util.Observador;

/**
 *
 * @author Serginho
 */
public class TurmaUI extends javax.swing.JPanel implements Observador {

    private CronosFrame main;

    public TurmaUI(CronosFrame main) {
        this.main = main;
        initComponents();

        btgerar.addMouseListener(new LinkEffectHandler());
        btver.addMouseListener(new LinkEffectHandler());
        btdisciplinas.addMouseListener(new LinkEffectHandler());
        btgerar.setVisible(false);
        btver.setVisible(false);
        btdisciplinas.setVisible(false);
        lbid.setVisible(false);

        try {
            CronosAPI.subscribe(Turma.class, this);
            reloadPNShow();
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }
    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    /**
     * carrega as turmas para forma de tiles
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadTurmas() {
        pnShow.removeAll();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Turma> turmas = CronosAPI.<Turma>get(Turma.class);
                    for (Turma t : turmas) {
                        Tile ct = new Tile();
                        ct.setNome(t.getNome());
                        ct.setId(t.getNucleo().getNome());
                        ct.setClickEvent(new TileClickedHandler());
                        pnShow.add(ct);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "FAIL! Problema ao carregar turmas:\n" + ex);
                }
            }
        });
        t.start();
    }

    /**
     * carrega as informacoes do docente
     *
     * @param id
     */
    private void showTurmaInfo(final String nome) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Turma turma = CronosAPI.buscaTurma(nome);
                    lbid.setText(String.valueOf(turma.getId()));
                    lbnome.setText(turma.getNome());
                    lbnucleo.setText("nucleo: " + turma.getNucleo().getNome());
                    DateFormat fmt = DateFormat.getDateInstance(DateFormat.SHORT);
                    lbentrada.setText("entrada: " + fmt.format(turma.getEntrada()));
                    lbsaida.setText("saida: " + (turma.getSaida() != null ? fmt.format(turma.getSaida()) : "não determinada"));
                    lbturno.setText("turno: " + turma.getTurno().name().toLowerCase());
                    lbhabilitacao.setText("habilitacao: " + String.valueOf(turma.getHabilitacao()));
                    btgerar.setVisible(true);
                    btver.setVisible(true);
                    btdisciplinas.setVisible(true);

                    reloadPNShow();

                    pnturmasinfo.updateUI();
                    TurmaUI.this.updateUI();
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao exibir informacoes da turma:\n" + ex);
                }
            }
        });
        t.start();
        btgerar.setVisible(false);
        btver.setVisible(false);
    }

    private void reloadPNShow() throws ClassNotFoundException, SQLException {
        int w = pnShow.getPreferredSize().width;
        int turmas = CronosAPI.<Turma>get(Turma.class).size();
        pnShow.setPreferredSize(new Dimension(w, ((110 * turmas) / 3)));
    }

    @Override
    public void update() {
        loadTurmas();
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

            showTurmaInfo(tile.getNome());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        bthome = new javax.swing.JLabel();
        jPanel2 = new javax.swing.JPanel();
        scroll = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        btconfig = new javax.swing.JLabel();
        pnturmasinfo = new javax.swing.JPanel();
        lbhabilitacao = new javax.swing.JLabel();
        lbsaida = new javax.swing.JLabel();
        lbturno = new javax.swing.JLabel();
        lbentrada = new javax.swing.JLabel();
        lbnucleo = new javax.swing.JLabel();
        lbnome = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();
        btver = new javax.swing.JLabel();
        btgerar = new javax.swing.JLabel();
        btdisciplinas = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
        setPreferredSize(new java.awt.Dimension(1366, 728));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("turmas");

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

        jPanel2.setMaximumSize(new java.awt.Dimension(379, 381));
        jPanel2.setMinimumSize(new java.awt.Dimension(379, 381));
        jPanel2.setOpaque(false);
        jPanel2.setLayout(new org.netbeans.lib.awtextra.AbsoluteLayout());

        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setActiveWheelGesture(true);

        pnShow.setMaximumSize(new java.awt.Dimension(835, 1080));
        pnShow.setMinimumSize(new java.awt.Dimension(600, 1080));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(600, 1080));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scroll.setViewportView(pnShow);

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

        pnturmasinfo.setBackground(new Color(50,50,200,20));

        lbhabilitacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbhabilitacao.setText("habilitação");

        lbsaida.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbsaida.setText("saída");

        lbturno.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbturno.setText("turno");

        lbentrada.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbentrada.setText("entrada");

        lbnucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbnucleo.setText("núcleo");

        lbnome.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        lbnome.setForeground(ColorManager.getColor("foreground"));
        lbnome.setText("turma");

        lbid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbid.setText("id");

        javax.swing.GroupLayout pnturmasinfoLayout = new javax.swing.GroupLayout(pnturmasinfo);
        pnturmasinfo.setLayout(pnturmasinfoLayout);
        pnturmasinfoLayout.setHorizontalGroup(
            pnturmasinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pnturmasinfoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pnturmasinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(pnturmasinfoLayout.createSequentialGroup()
                        .addGroup(pnturmasinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(lbid, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbnucleo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbentrada, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbsaida, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbturno, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbhabilitacao, javax.swing.GroupLayout.PREFERRED_SIZE, 236, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 74, Short.MAX_VALUE)))
                .addContainerGap())
        );
        pnturmasinfoLayout.setVerticalGroup(
            pnturmasinfoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, pnturmasinfoLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbnome, javax.swing.GroupLayout.PREFERRED_SIZE, 41, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbid)
                .addGap(6, 6, 6)
                .addComponent(lbnucleo)
                .addGap(6, 6, 6)
                .addComponent(lbentrada)
                .addGap(6, 6, 6)
                .addComponent(lbsaida)
                .addGap(6, 6, 6)
                .addComponent(lbturno)
                .addGap(6, 6, 6)
                .addComponent(lbhabilitacao)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        btver.setBackground(new java.awt.Color(50, 50, 200,20));
        btver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btver.setText("ver horário");
        btver.setOpaque(true);
        btver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btverMouseClicked(evt);
            }
        });

        btgerar.setBackground(new java.awt.Color(50, 50, 200,20));
        btgerar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btgerar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btgerar.setText("gerar horário");
        btgerar.setOpaque(true);
        btgerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btgerarMouseClicked(evt);
            }
        });

        btdisciplinas.setBackground(new java.awt.Color(50, 50, 200,20));
        btdisciplinas.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btdisciplinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btdisciplinas.setText("disciplinas");
        btdisciplinas.setOpaque(true);
        btdisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btdisciplinasMouseClicked(evt);
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
                        .addComponent(bthome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jLabel1)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1167, Short.MAX_VALUE)
                        .addComponent(btconfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, 992, Short.MAX_VALUE)
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(pnturmasinfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(btver, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btdisciplinas, javax.swing.GroupLayout.PREFERRED_SIZE, 146, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {btdisciplinas, btgerar});

        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btconfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING, false)
                        .addComponent(jLabel1, javax.swing.GroupLayout.Alignment.LEADING, 0, 0, Short.MAX_VALUE)
                        .addComponent(bthome, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(pnturmasinfo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btdisciplinas)
                        .addGap(0, 296, Short.MAX_VALUE))
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {btdisciplinas, btgerar});

    }// </editor-fold>//GEN-END:initComponents

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void btverMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btverMouseClicked
        main.Switch(CronosFrame.HORARIOS);
        HorariosUI.getInstance().move("exibir");
        HorariosExibirPanel.getInstance().action(Integer.parseInt(lbid.getText()));
    }//GEN-LAST:event_btverMouseClicked

    private void btgerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btgerarMouseClicked
        main.Switch(CronosFrame.HORARIOS);
        HorariosUI.getInstance().move("gerar");
        HorariosGerarPanel.getInstance().action(Integer.parseInt(lbid.getText()));
    }//GEN-LAST:event_btgerarMouseClicked

    private void btconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btconfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btconfigMouseClicked

    private void btdisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btdisciplinasMouseClicked
        List<ucCheck> listaCheck = new ArrayList<>();
        Turma t = null;
        Acumulador acc = new Acumulador();

        try {
            t = CronosAPI.buscaTurma(lbnome.getText());
        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TurmaUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TurmaUI.class.getName()).log(Level.SEVERE, null, ex);
        }
        try {
            List<UnidadeCurricular> u = CronosAPI.buscaDisciplinas(t.getNucleo());

            for (UnidadeCurricular uc : u) {

                listaCheck.add(new ucCheck(uc, acc));
            }
            JDialog ucd = new JDialog();
            ucd.setSize(new Dimension(750, 450));
            ucd.setLocationRelativeTo(null);
            CheckDisciplina ckUI = new CheckDisciplina(ucd, listaCheck, CronosAPI.buscaTurma(lbnome.getText()), acc);
            ucd.setContentPane(ckUI);
            ucd.setVisible(true);



        } catch (ClassNotFoundException ex) {
            Logger.getLogger(TurmaUI.class.getName()).log(Level.SEVERE, null, ex);
        } catch (SQLException ex) {
            Logger.getLogger(TurmaUI.class.getName()).log(Level.SEVERE, null, ex);
        }


    }//GEN-LAST:event_btdisciplinasMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
    private javax.swing.JLabel btdisciplinas;
    private javax.swing.JLabel btgerar;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel btver;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JLabel lbentrada;
    private javax.swing.JLabel lbhabilitacao;
    private javax.swing.JLabel lbid;
    private javax.swing.JLabel lbnome;
    private javax.swing.JLabel lbnucleo;
    private javax.swing.JLabel lbsaida;
    private javax.swing.JLabel lbturno;
    private javax.swing.JPanel pnShow;
    private javax.swing.JPanel pnturmasinfo;
    private br.ufrpe.bcc.continuous.components.MagicScroll scroll;
    // End of variables declaration//GEN-END:variables
}
