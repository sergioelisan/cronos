/*
 * HorariosUI.java
 *
 * Created on 22/12/2011, 09:07:06
 */
package edu.cronos.gui.horarios;

import edu.cronos.CronosAPI;
import edu.cronos.entidades.Turma;
import edu.cronos.gui.ColorManager;
import edu.cronos.gui.CronosFrame;
import edu.cronos.gui.custom.ImageLoader;
import edu.cronos.gui.custom.LinkEffectHandler;
import edu.cronos.gui.custom.Tile;

import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Sergio lisan e carlos melo
 */
public class HorariosUI extends javax.swing.JPanel {

    private static HorariosUI instance = new HorariosUI();
    // End of variables declaration//GEN-END:variables
    private static List<Turma> turmas;
    private CronosFrame main;
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
    private javax.swing.JLabel btexibir;
    private javax.swing.JLabel btgerar;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel btopcoes;
    private javax.swing.JPanel container;

    /*
     * CLASSES INTERNAS ANONIMAS VOLTADAS PARA OFERECER SERVICOS PARA SEUS CLIENTES
     */
    private javax.swing.JLabel lbModulo;

    private HorariosUI() {
        initComponents();

        container.setLayout(new CardLayout());
        container.add(HorariosGerarPanel.getInstance(), "gerar");
        container.add(HorariosExibirPanel.getInstance(), "exibir");
        container.add(new HorariosOpcoesPanel(), "opcoes");

        move("exibir");
        loadEffects();
    }

    public static HorariosUI getInstance() {
        return instance;
    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    /**
     * insere uma referencia para o main
     *
     * @param main
     */
    public void setMain(CronosFrame main) {
        this.main = main;
    }

    private void loadEffects() {
        btexibir.addMouseListener(new LinkEffectHandler());
        btexibir.setBackground(new Color(50, 50, 200, 20));
        btgerar.addMouseListener(new LinkEffectHandler());
        btgerar.setBackground(new Color(50, 50, 200, 20));
        btopcoes.addMouseListener(new LinkEffectHandler());
        btopcoes.setBackground(new Color(50, 50, 200, 20));
    }

    /**
     * Move um componente para o topo
     *
     * @param c
     */
    public void move(String modulo) {
        ((CardLayout) container.getLayout()).show(container, modulo);
        lbModulo.setText(modulo);
    }

    public void exibir(Integer turmaID) {
        move("exibir");
        HorariosExibirPanel.getInstance().action(turmaID);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbModulo = new javax.swing.JLabel();
        btconfig = new javax.swing.JLabel();
        bthome = new javax.swing.JLabel();
        btgerar = new javax.swing.JLabel();
        btexibir = new javax.swing.JLabel();
        btopcoes = new javax.swing.JLabel();
        container = new javax.swing.JPanel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
        setPreferredSize(new java.awt.Dimension(1366, 728));

        lbModulo.setFont(new java.awt.Font("Segoe UI", 0, 28)); // NOI18N
        lbModulo.setForeground(ColorManager.getColor("foreground"));
        lbModulo.setText("horários");

        btconfig.setBackground(ColorManager.getColor("button"));
        btconfig.setForeground(ColorManager.getColor("button"));
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

        bthome.setBackground(ColorManager.getColor("button"));
        bthome.setForeground(ColorManager.getColor("button"));
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

        btgerar.setBackground(ColorManager.getColor("button"));
        btgerar.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btgerar.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btgerar.setText("gerar");
        btgerar.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btgerar.setMaximumSize(new java.awt.Dimension(100, 25));
        btgerar.setMinimumSize(new java.awt.Dimension(100, 25));
        btgerar.setOpaque(true);
        btgerar.setPreferredSize(new java.awt.Dimension(100, 25));
        btgerar.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btgerarMouseClicked(evt);
            }
        });

        btexibir.setBackground(ColorManager.getColor("button"));
        btexibir.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btexibir.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btexibir.setText("exibir");
        btexibir.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btexibir.setMaximumSize(new java.awt.Dimension(100, 25));
        btexibir.setMinimumSize(new java.awt.Dimension(100, 25));
        btexibir.setOpaque(true);
        btexibir.setPreferredSize(new java.awt.Dimension(100, 25));
        btexibir.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btexibirMouseClicked(evt);
            }
        });

        btopcoes.setBackground(ColorManager.getColor("button"));
        btopcoes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btopcoes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btopcoes.setText("opções");
        btopcoes.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btopcoes.setMaximumSize(new java.awt.Dimension(100, 25));
        btopcoes.setMinimumSize(new java.awt.Dimension(100, 25));
        btopcoes.setOpaque(true);
        btopcoes.setPreferredSize(new java.awt.Dimension(100, 25));
        btopcoes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btopcoesMouseClicked(evt);
            }
        });

        container.setBackground(new Color(20, 20, 200, 0));
        container.setMaximumSize(new java.awt.Dimension(1920, 1080));
        container.setMinimumSize(new java.awt.Dimension(900, 720));
        container.setOpaque(false);
        container.setPreferredSize(new java.awt.Dimension(900, 720));
        container.setLayout(new java.awt.BorderLayout());

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                        .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(bthome, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(18, 18, 18)
                                                .addComponent(lbModulo)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                                .addComponent(btconfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                        .addGroup(layout.createSequentialGroup()
                                                .addComponent(btexibir, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                                .addComponent(btopcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                                .addGap(0, 0, Short.MAX_VALUE)))
                                .addContainerGap())
        );
        layout.setVerticalGroup(
                layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                        .addGroup(layout.createSequentialGroup()
                                .addContainerGap()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                        .addComponent(bthome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(btconfig, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                        .addComponent(lbModulo, javax.swing.GroupLayout.PREFERRED_SIZE, 0, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                                        .addComponent(btopcoes, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btexibir, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                                        .addComponent(btgerar, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(container, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                                .addContainerGap())
        );

        layout.linkSize(javax.swing.SwingConstants.VERTICAL, btconfig, bthome, lbModulo);

    }// </editor-fold>//GEN-END:initComponents

    private void btgerarMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btgerarMouseClicked
        move("gerar");
        HorariosGerarPanel.getInstance().show("GERAR");
    }//GEN-LAST:event_btgerarMouseClicked

    private void btexibirMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btexibirMouseClicked
        move("exibir");
    }//GEN-LAST:event_btexibirMouseClicked

    private void btopcoesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btopcoesMouseClicked
        move("opcoes");
    }//GEN-LAST:event_btopcoesMouseClicked

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void btconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btconfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btconfigMouseClicked

    /**
     * Classe interna que gera o efeito de carregamento no Loading
     */
    static class LoadingEffect implements ActionListener {

        private JLabel lbLoading;
        private int turn = 0;
        private String[] chars = {" ", ".", "..", "..."};

        LoadingEffect(JLabel lb) {
            lbLoading = lb;
        }

        @Override
        public void actionPerformed(ActionEvent ae) {
            lbLoading.setText("carregando " + chars[turn]);
            turn++;

            if (turn == chars.length) {
                turn = 0;
            }
        }
    }

    /**
     * Classe interna que prove o Serviço de carregar turmas do banco de dados e
     * retornar em forma de "TILES", peças da cor do sistema.
     * <p>
     * É uma maneira de centralizar essa funcionalidade em um unico lugar e
     * oferecer a todos os modulos instalados em HorariosUI
     */
    static class LoadTurmas implements Runnable {

        private JPanel panel;
        private HorariosUIClient client;

        LoadTurmas(JPanel panel, HorariosUIClient client) {
            this.panel = panel;
            this.client = client;
        }

        @Override
        public void run() {
            try {
                panel.removeAll();
                List<Turma> turmas = CronosAPI.get(Turma.class);
                for (Turma t : turmas) {
                    Tile tile = new Tile();
                    tile.setNome(t.getNome());
                    tile.setId(t.getNucleo().getNome() + "");
                    tile.setClickEvent(new TileClickedHandler(client));
                    panel.add(tile);
                }
            } catch (ClassNotFoundException | SQLException ex) {
                JOptionPane.showMessageDialog(null, "FAIL! Problema ao carregar turmas:\n" + ex);
            }
        }
    }

    /**
     * Classe que gerará objetos HANDLER para tratar os efeitos graficos, quando
     * o mouse passa por cima dos elementos que o implementam
     */
    static class LinkHandler extends MouseAdapter {

        @Override
        public void mouseEntered(MouseEvent e) {
            JComponent com = (JComponent) e.getSource();
            com.setBackground(ColorManager.claro(ColorManager.getColor("button")));
        }

        @Override
        public void mouseExited(MouseEvent e) {
            JComponent com = (JComponent) e.getSource();
            com.setBackground(ColorManager.getColor("button"));
        }
    }

    /**
     * Classe que prove o servico de tratamento do evento de clicar em cima da
     * tile
     */
    static class TileClickedHandler extends MouseAdapter {

        private HorariosUIClient action;

        TileClickedHandler(HorariosUIClient action) {
            this.action = action;
        }

        @Override
        public void mouseClicked(MouseEvent e) {
            Tile tile;
            if (e.getSource() instanceof JLabel) {
                tile = (Tile) ((JLabel) e.getSource()).getParent();
            } else {
                tile = (Tile) e.getSource();
            }
            try {
                for (Turma t : CronosAPI.<Turma>get(Turma.class)) {
                    if (t.getNome().equals(tile.getNome())) {
                        action.action(t.getId());
                    }
                }
            } catch (ClassNotFoundException ex) {
                Logger.getLogger(HorariosUI.class.getName()).log(Level.SEVERE, null, ex);
            } catch (SQLException ex) {
                Logger.getLogger(HorariosUI.class.getName()).log(Level.SEVERE, null, ex);
            }
        }
    }
}
