/*
 * TurmaUI.java
 *
 * Created on 22/12/2011, 09:06:38
 */
package senai.cronos.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.util.List;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import senai.cronos.Fachada;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.events.LinkEffectHandler;
import senai.cronos.gui.horarios.HorariosExibirPanel;
import senai.cronos.gui.horarios.HorariosGerarPanel;
import senai.cronos.gui.horarios.HorariosUI;

/**
 *
 * @author Serginho
 */
public class TurmaUI extends javax.swing.JPanel {

    private CronosFrame main;

    public TurmaUI(CronosFrame main) {
        this.main = main;
        initComponents();
        loadTurmas();

        btgerar.addMouseListener(new LinkEffectHandler());
        btver.addMouseListener(new LinkEffectHandler());
        btgerar.setVisible(false);
        btver.setVisible(false);
    }

    @Override
    public void paintComponent(Graphics g) {
        super.paintComponents(g);
        
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
                    List<Turma> turmas = Fachada.<Turma>get(Turma.class);
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
                    Turma turma = Fachada.buscaTurma(nome);
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
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao exibir informacoes da turma:\n" + ex);
                }
            }
        });
        t.start();
        btgerar.setVisible(false);
        btver.setVisible(false);
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
        lbhabilitacao = new javax.swing.JLabel();
        lbsaida = new javax.swing.JLabel();
        lbturno = new javax.swing.JLabel();
        lbentrada = new javax.swing.JLabel();
        lbnucleo = new javax.swing.JLabel();
        lbnome = new javax.swing.JLabel();
        btgerar = new javax.swing.JLabel();
        btver = new javax.swing.JLabel();
        lbid = new javax.swing.JLabel();
        scroll = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        btconfig = new javax.swing.JLabel();

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

        btgerar.setBackground(new java.awt.Color(255, 255, 255));
        btgerar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btgerar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btgerar.setText("gerar horário");
        btgerar.setBorder(javax.swing.BorderFactory.createLineBorder(ColorManager.getColor("border")));
        btgerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btgerarMouseClicked(evt);
            }
        });

        btver.setBackground(new java.awt.Color(255, 255, 255));
        btver.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btver.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btver.setText("ver horário");
        btver.setBorder(javax.swing.BorderFactory.createLineBorder(ColorManager.getColor("border")));
        btver.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btverMouseClicked(evt);
            }
        });

        lbid.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbid.setText("id");

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addComponent(lbnucleo, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbentrada, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbsaida, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbturno, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbhabilitacao, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(lbid, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                        .addGap(129, 129, 129))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(lbnome, javax.swing.GroupLayout.PREFERRED_SIZE, 355, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(btver, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(0, 0, Short.MAX_VALUE))))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbnome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbid)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbnucleo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbentrada)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbsaida)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbturno)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbhabilitacao)
                .addGap(18, 18, 18)
                .addComponent(btver, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, 29, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        scroll.setActiveWheelGesture(true);

        pnShow.setMaximumSize(new java.awt.Dimension(835, 1080));
        pnShow.setMinimumSize(new java.awt.Dimension(835, 1080));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(835, 1080));
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
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(scroll, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGap(128, 128, 128)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
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
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 402, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(301, 301, 301))
        );
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
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
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
    private br.ufrpe.bcc.continuous.components.MagicScroll scroll;
    // End of variables declaration//GEN-END:variables
}
