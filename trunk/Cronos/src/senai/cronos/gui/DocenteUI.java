/*
 * DocenteUI.java
 *
 * Created on 22/12/2011, 09:06:44
 */
package senai.cronos.gui;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import senai.cronos.CronosAPI;
import senai.cronos.database.dao.DAO;
import senai.cronos.database.dao.DAODocente;
import senai.cronos.database.dao.DAOProficiencia;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.custom.ProficienciaSlider;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.cronos.horario.HorarioDocente;
import senai.util.Observador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class DocenteUI extends javax.swing.JPanel implements Observador {

    private CronosFrame main;
    private List<Nucleo> nucleos;
    private int posicao = -1;

    public DocenteUI(CronosFrame main) {
        this.main = main;
        initComponents();
        lbproximo.addMouseListener(new LinkEffectHandler());
        lbanterior.addMouseListener(new LinkEffectHandler());
        lbProficiencias.addMouseListener(new LinkEffectHandler());
        lbProficiencias.setVisible(false);

        try {
            CronosAPI.subscribe(Docente.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        update();

    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    /**
     * Inicializa os dados
     */
    private void initData() {
        // thread usada para criar uma linha paralela de processamento e impedir o congelamento da interface grafica
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

        // carrega os docentes
        loadDocentes();
    }

    /**
     * carrega os docentes de um determinado nucleo e imprime na ui
     *
     * @param nucleo
     */
    private void loadDocentes() {
        pnShow.removeAll();
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                List<Docente> docentes;
                try {
                    if (posicao == -1) {
                        docentes = CronosAPI.<Docente>get(Docente.class);
                        lbnucleoatual.setText("todos");
                    } else {
                        Nucleo nucleo = nucleos.get(posicao);
                        docentes = CronosAPI.buscaDocentes(nucleo);
                        lbnucleoatual.setText(nucleo.getNome().toLowerCase());
                    }

                    for (Docente d : docentes) {
                        Tile ct = new Tile();
                        ct.setNome(d.getNome());
                        ct.setId(String.valueOf(d.getMatricula()));
                        ct.setClickEvent(new TileClickedHandler());
                        pnShow.add(ct);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao carregas os docentes:\n" + ex);
                }
            }
        });
        t.start();
        pnShow.repaint();
    }

    /**
     * carrega as informacoes do docente
     *
     * @param id
     */
    private void showDocenteInfo(final String id) {
        Thread t = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Integer matricula = Integer.parseInt(id);
                    Docente doc = CronosAPI.<Docente>get(Docente.class, matricula);

                    lbformacao.setText("formação: " + doc.getFormacao().name());
                    lbmatricula.setText("matricula: " + doc.getMatricula().toString());
                    lbnome.setText(doc.getNome());
                    lbnucleo.setText("núcleo: " + doc.getNucleo().getNome());
                    lbscore.setText("score: " + String.valueOf(doc.getScore()));
                    lbturnos.setText("turnos: " + doc.getPrimeiroTurno().name().toLowerCase() + " e " + doc.getSegundoTurno().name().toLowerCase());

                    HorarioDocente hd = doc.getHorarioDocente();
                    lbocupacao.setText("ocupação: " + String.valueOf(hd.getPercentualOcupacao()) + "%");
                    
                    pndocentedata.updateUI();
                    DocenteUI.this.updateUI();
                    
                    lbProficiencias.setVisible(true);
                    
                } catch (Exception ex) {
                    ex.printStackTrace();
                    JOptionPane.showMessageDialog(null, "Problemas ao exibir informacoes do docente:\n" + ex);
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

        loadDocentes();
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

        loadDocentes();
    }

    @Override
    public void update() {
        initData();
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

            showDocenteInfo(tile.getId());
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        bthome = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        btconfig = new javax.swing.JLabel();
        pndocentedata = new javax.swing.JPanel();
        lbnome = new javax.swing.JLabel();
        lbmatricula = new javax.swing.JLabel();
        lbformacao = new javax.swing.JLabel();
        lbnucleo = new javax.swing.JLabel();
        lbscore = new javax.swing.JLabel();
        lbturnos = new javax.swing.JLabel();
        lbocupacao = new javax.swing.JLabel();
        lbproximo = new javax.swing.JLabel();
        lbanterior = new javax.swing.JLabel();
        scroll = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        lbnucleoatual = new javax.swing.JLabel();
        lbProficiencias = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 768));
        setMinimumSize(new java.awt.Dimension(1024, 768));
        setPreferredSize(new java.awt.Dimension(1366, 768));

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
        jLabel1.setText("docentes");

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

        pndocentedata.setBackground(new Color(50,50,200,20));
        pndocentedata.setMaximumSize(new java.awt.Dimension(455, 244));
        pndocentedata.setMinimumSize(new java.awt.Dimension(455, 244));
        pndocentedata.setPreferredSize(new java.awt.Dimension(455, 245));

        lbnome.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        lbnome.setForeground(ColorManager.getColor("foreground"));
        lbnome.setText("docente");

        lbmatricula.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbmatricula.setText("matrícula");

        lbformacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbformacao.setText("formação");

        lbnucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbnucleo.setText("núcleo");

        lbscore.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbscore.setText("score");

        lbturnos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbturnos.setText("turnos");

        lbocupacao.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbocupacao.setText("ocupação");

        javax.swing.GroupLayout pndocentedataLayout = new javax.swing.GroupLayout(pndocentedata);
        pndocentedata.setLayout(pndocentedataLayout);
        pndocentedataLayout.setHorizontalGroup(
            pndocentedataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndocentedataLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(pndocentedataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbscore, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbnome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbmatricula, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbformacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbnucleo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbturnos, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(lbocupacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
        pndocentedataLayout.setVerticalGroup(
            pndocentedataLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(pndocentedataLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbnome)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbmatricula)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbformacao)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbnucleo)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbscore)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbturnos)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(lbocupacao)
                .addContainerGap(12, Short.MAX_VALUE))
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

        scroll.setVerticalScrollBarPolicy(javax.swing.ScrollPaneConstants.VERTICAL_SCROLLBAR_AS_NEEDED);
        scroll.setActiveWheelGesture(true);

        pnShow.setMaximumSize(new java.awt.Dimension(835, 6000));
        pnShow.setMinimumSize(new java.awt.Dimension(835, 1080));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(835, 6000));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT));
        scroll.setViewportView(pnShow);

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

        lbProficiencias.setBackground(new java.awt.Color(255, 255, 255));
        lbProficiencias.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbProficiencias.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbProficiencias.setText("ajustar proficiências");
        lbProficiencias.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbProficiencias.setMaximumSize(new java.awt.Dimension(100, 25));
        lbProficiencias.setMinimumSize(new java.awt.Dimension(100, 25));
        lbProficiencias.setPreferredSize(new java.awt.Dimension(100, 25));
        lbProficiencias.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbProficienciasMouseClicked(evt);
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
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(427, 427, 427)
                                .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 56, Short.MAX_VALUE)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(lbProficiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 166, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(289, 289, 289))
                            .addComponent(pndocentedata, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))))
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(pndocentedata, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(lbProficiencias, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(0, 369, Short.MAX_VALUE))
                    .addComponent(scroll, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void bthomeMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_bthomeMouseClicked
        main.Switch(CronosFrame.HOME);
    }//GEN-LAST:event_bthomeMouseClicked

    private void lbproximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbproximoMouseClicked
        nextNucleo();
    }//GEN-LAST:event_lbproximoMouseClicked

    private void lbanteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbanteriorMouseClicked
        previousNucleo();
    }//GEN-LAST:event_lbanteriorMouseClicked

    private void btconfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btconfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btconfigMouseClicked

    private void lbProficienciasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbProficienciasMouseClicked
        try {
            Docente doc = CronosAPI.buscaDocenteMatricula(lbmatricula.getText().replace("matricula: ", "") );
            List<ProficienciaSlider> sliders = new ArrayList<>();
            
            for (Proficiencia p : doc.getProficiencias()) {
                sliders.add(new ProficienciaSlider(p));
            }
                
            JDialog profcDialog = new JDialog();
            profcDialog.setSize(new Dimension(750, 450) );
            profcDialog.setLocationRelativeTo(null);
            
            DocenteProficienciaUI dpUI = new DocenteProficienciaUI(profcDialog, doc, sliders);
            profcDialog.setContentPane(dpUI);
            
            profcDialog.setVisible(true);
        } catch (ClassNotFoundException | SQLException ex) { 
            System.out.println(ex);
            Alerta.jogarAviso(ex.getMessage() ); 
        
        }
    }//GEN-LAST:event_lbProficienciasMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btconfig;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbProficiencias;
    private javax.swing.JLabel lbanterior;
    private javax.swing.JLabel lbformacao;
    private javax.swing.JLabel lbmatricula;
    private javax.swing.JLabel lbnome;
    private javax.swing.JLabel lbnucleo;
    private javax.swing.JLabel lbnucleoatual;
    private javax.swing.JLabel lbocupacao;
    private javax.swing.JLabel lbproximo;
    private javax.swing.JLabel lbscore;
    private javax.swing.JLabel lbturnos;
    private javax.swing.JPanel pnShow;
    private javax.swing.JPanel pndocentedata;
    private br.ufrpe.bcc.continuous.components.MagicScroll scroll;
    // End of variables declaration//GEN-END:variables
}
