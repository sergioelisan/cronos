/*
 * ConfigUI.java
 *
 * Created on 22/12/2011, 09:07:12
 */
package senai.cronos.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.sql.SQLException;
import java.util.Collections;
import java.util.Date;
import java.util.List;
import javax.swing.DefaultListModel;
import javax.swing.JOptionPane;
import senai.cronos.Fachada;
import senai.cronos.entidades.Nucleo;
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
            DefaultListModel<Feriado> lmFeriados = new DefaultListModel<>();
            listFeriados.setModel(lmFeriados); 
            
            DefaultListModel<Nucleo> lmNucleos = new DefaultListModel<>();
            listaNucleos.setModel(lmNucleos);                    
            
            panelferiadoadd.setVisible(false);
            panelAddNucleo.setVisible(false);
            
            btaddferiado.addMouseListener(new LinkEffectHandler());
            btremoveferiado.addMouseListener(new LinkEffectHandler());
            btcancelarferiado.addMouseListener(new LinkEffectHandler());
            btsaveferiado.addMouseListener(new LinkEffectHandler());
            
            btaddnucleo.addMouseListener(new LinkEffectHandler());
            btremovenucleo.addMouseListener(new LinkEffectHandler());
            btcancelarnucleo.addMouseListener(new LinkEffectHandler());
            btsavenucleo.addMouseListener(new LinkEffectHandler());

            Date inicio = Fachada.getInicioCalendario();
            Date fim = Fachada.getFimCalendario();

            datepickerFim.setDate(fim);
            datepickerInicio.setDate(inicio);

            loadFeriados();
            loadNucleos();

        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao carregar as configurações\n" + ex);
            ex.printStackTrace(System.err);
        }
    }

    /**
     * atualiza a lista de feriados
     */
    private void loadFeriados() {
        try {
            DefaultListModel lm = (DefaultListModel<Feriado>)listFeriados.getModel();
            lm.clear();
            
            // logica de carregamento de feriados
            List<Feriado> feriados = Fachada.<Feriado>get(Feriado.class);
            Collections.sort(feriados);

            for (Feriado feriado : feriados) {
                lm.addElement(feriado);
            }

            listFeriados.setModel(lm);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao carregar os feriados\n" + ex);
            ex.printStackTrace(System.err);
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
    
    /**
     * atualiza a lista de feriados
     */
    private void loadNucleos() {
        try {
            DefaultListModel lm = (DefaultListModel<Nucleo>)listaNucleos.getModel();
            lm.clear();
            
            // logica de carregamento de feriados
            List<Nucleo> nucleos = Fachada.<Nucleo>get(Nucleo.class);
            Collections.sort(nucleos);

            for (Nucleo nucleo : nucleos) {
                lm.addElement(nucleo);
            }

            listaNucleos.setModel(lm);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao carregar os nucleos nas configuracoes\n" + ex);
            ex.printStackTrace(System.err);
        }
    }

    /**
     * armazena um feriado no banco
     */
    private void saveNucleo() {
        try {
            String nome = txtnomenucleo.getText().trim();
            String descricao = txtdescricao.getText().trim();
            Nucleo nucleo = new Nucleo(nome, descricao);
            
            Fachada.add(nucleo);
            loadNucleos();
            panelAddNucleo.setVisible(false);
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao salvar novo nucleo\n" + ex);
        }
    }

    /**
     * Remove um feriado do banco
     *
     * @param f
     */
    private void removeNucleo(Nucleo n) {
        try {
            Fachada.remove(Nucleo.class, n.getId());
            loadNucleos();
        } catch (ClassNotFoundException | SQLException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao remover núcleo\n" + ex);
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
        btcancelarferiado = new javax.swing.JLabel();
        btsaveferiado = new javax.swing.JLabel();
        jPanel3 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jScrollPane2 = new javax.swing.JScrollPane();
        listaNucleos = new javax.swing.JList();
        btaddnucleo = new javax.swing.JLabel();
        btremovenucleo = new javax.swing.JLabel();
        panelAddNucleo = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        jLabel11 = new javax.swing.JLabel();
        txtnomenucleo = new javax.swing.JTextField();
        txtdescricaonucleo = new javax.swing.JTextField();
        btsavenucleo = new javax.swing.JLabel();
        btcancelarnucleo = new javax.swing.JLabel();

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

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 28));
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("configurações");

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));
        jPanel1.setOpaque(false);

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel4.setForeground(ColorManager.getColor("foreground"));
        jLabel4.setText("calendário");

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel3.setText("fim do calendário");

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 14));
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
                .addContainerGap(286, Short.MAX_VALUE))
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
        jPanel2.setMinimumSize(new java.awt.Dimension(550, 300));
        jPanel2.setOpaque(false);
        jPanel2.setPreferredSize(new java.awt.Dimension(550, 300));

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel5.setForeground(ColorManager.getColor("foreground"));
        jLabel5.setText("feriados");

        listFeriados.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jScrollPane1.setViewportView(listFeriados);

        btaddferiado.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btaddferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btaddferiado.setText("novo");
        btaddferiado.setMaximumSize(new java.awt.Dimension(80, 22));
        btaddferiado.setMinimumSize(new java.awt.Dimension(80, 22));
        btaddferiado.setPreferredSize(new java.awt.Dimension(80, 22));
        btaddferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btaddferiadoMouseClicked(evt);
            }
        });

        btremoveferiado.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btremoveferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btremoveferiado.setText("remover");
        btremoveferiado.setMaximumSize(new java.awt.Dimension(80, 22));
        btremoveferiado.setMinimumSize(new java.awt.Dimension(80, 22));
        btremoveferiado.setPreferredSize(new java.awt.Dimension(80, 22));
        btremoveferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btremoveferiadoMouseClicked(evt);
            }
        });

        panelferiadoadd.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(0, 0, 0)));
        panelferiadoadd.setOpaque(false);

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel6.setText("dia do feriado");

        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel9.setText("descrição");

        btcancelarferiado.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btcancelarferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btcancelarferiado.setText("cancelar");
        btcancelarferiado.setMaximumSize(new java.awt.Dimension(80, 22));
        btcancelarferiado.setMinimumSize(new java.awt.Dimension(80, 22));
        btcancelarferiado.setPreferredSize(new java.awt.Dimension(80, 22));
        btcancelarferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btcancelarferiadoMouseClicked(evt);
            }
        });

        btsaveferiado.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btsaveferiado.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btsaveferiado.setText("salvar");
        btsaveferiado.setMaximumSize(new java.awt.Dimension(80, 22));
        btsaveferiado.setMinimumSize(new java.awt.Dimension(80, 22));
        btsaveferiado.setPreferredSize(new java.awt.Dimension(80, 22));
        btsaveferiado.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsaveferiadoMouseClicked(evt);
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
                        .addGap(0, 330, Short.MAX_VALUE)
                        .addComponent(btcancelarferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btsaveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap())
                    .addGroup(panelferiadoaddLayout.createSequentialGroup()
                        .addComponent(jLabel6)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(pickFeriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelferiadoaddLayout.createSequentialGroup()
                        .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, 224, javax.swing.GroupLayout.PREFERRED_SIZE))))
        );

        panelferiadoaddLayout.linkSize(javax.swing.SwingConstants.HORIZONTAL, new java.awt.Component[] {jLabel6, jLabel9});

        panelferiadoaddLayout.setVerticalGroup(
            panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelferiadoaddLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel9)
                    .addComponent(txtdescricao, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel6)
                    .addComponent(pickFeriado, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGroup(panelferiadoaddLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsaveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcancelarferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        panelferiadoaddLayout.linkSize(javax.swing.SwingConstants.VERTICAL, new java.awt.Component[] {jLabel6, jLabel9});

        javax.swing.GroupLayout jPanel2Layout = new javax.swing.GroupLayout(jPanel2);
        jPanel2.setLayout(jPanel2Layout);
        jPanel2Layout.setHorizontalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(jLabel5)
                        .addContainerGap(455, Short.MAX_VALUE))
                    .addGroup(jPanel2Layout.createSequentialGroup()
                        .addComponent(btaddferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(btremoveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addContainerGap(370, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 530, Short.MAX_VALUE)
                        .addContainerGap())
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, jPanel2Layout.createSequentialGroup()
                        .addComponent(panelferiadoadd, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addContainerGap())))
        );
        jPanel2Layout.setVerticalGroup(
            jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel2Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(jScrollPane1, javax.swing.GroupLayout.PREFERRED_SIZE, 155, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addGroup(jPanel2Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btaddferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremoveferiado, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addComponent(panelferiadoadd, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        jPanel3.setOpaque(false);

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 24));
        jLabel7.setForeground(ColorManager.getColor("foreground"));
        jLabel7.setText("núcleos");

        listaNucleos.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jScrollPane2.setViewportView(listaNucleos);

        btaddnucleo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btaddnucleo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btaddnucleo.setText("novo");
        btaddnucleo.setMaximumSize(new java.awt.Dimension(80, 22));
        btaddnucleo.setMinimumSize(new java.awt.Dimension(80, 22));
        btaddnucleo.setPreferredSize(new java.awt.Dimension(80, 22));
        btaddnucleo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btaddnucleoMouseClicked(evt);
            }
        });

        btremovenucleo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btremovenucleo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btremovenucleo.setText("remover");
        btremovenucleo.setMaximumSize(new java.awt.Dimension(80, 22));
        btremovenucleo.setMinimumSize(new java.awt.Dimension(80, 22));
        btremovenucleo.setPreferredSize(new java.awt.Dimension(80, 22));
        btremovenucleo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btremovenucleoMouseClicked(evt);
            }
        });

        panelAddNucleo.setOpaque(false);

        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel10.setText("nome");

        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 14));
        jLabel11.setText("descrição");

        txtnomenucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        txtdescricaonucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N

        btsavenucleo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btsavenucleo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btsavenucleo.setText("salvar");
        btsavenucleo.setMaximumSize(new java.awt.Dimension(80, 22));
        btsavenucleo.setMinimumSize(new java.awt.Dimension(80, 22));
        btsavenucleo.setPreferredSize(new java.awt.Dimension(80, 22));
        btsavenucleo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btsavenucleoMouseClicked(evt);
            }
        });

        btcancelarnucleo.setFont(new java.awt.Font("Segoe UI", 0, 14));
        btcancelarnucleo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btcancelarnucleo.setText("cancelar");
        btcancelarnucleo.setMaximumSize(new java.awt.Dimension(80, 22));
        btcancelarnucleo.setMinimumSize(new java.awt.Dimension(80, 22));
        btcancelarnucleo.setPreferredSize(new java.awt.Dimension(80, 22));
        btcancelarnucleo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btcancelarnucleoMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout panelAddNucleoLayout = new javax.swing.GroupLayout(panelAddNucleo);
        panelAddNucleo.setLayout(panelAddNucleoLayout);
        panelAddNucleoLayout.setHorizontalGroup(
            panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddNucleoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(panelAddNucleoLayout.createSequentialGroup()
                        .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtnomenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(panelAddNucleoLayout.createSequentialGroup()
                        .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 62, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(txtdescricaonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 149, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, panelAddNucleoLayout.createSequentialGroup()
                .addContainerGap(61, Short.MAX_VALUE)
                .addComponent(btcancelarnucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(btsavenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        panelAddNucleoLayout.setVerticalGroup(
            panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(panelAddNucleoLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel10)
                    .addComponent(txtnomenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel11)
                    .addComponent(txtdescricaonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 50, Short.MAX_VALUE)
                .addGroup(panelAddNucleoLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsavenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btcancelarnucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addContainerGap())
        );

        javax.swing.GroupLayout jPanel3Layout = new javax.swing.GroupLayout(jPanel3);
        jPanel3.setLayout(jPanel3Layout);
        jPanel3Layout.setHorizontalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(panelAddNucleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(28, 28, 28)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 228, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(btaddnucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(btremovenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 80, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        jPanel3Layout.setVerticalGroup(
            jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel3Layout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel7)
                .addGap(18, 18, 18)
                .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(panelAddNucleo, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(jPanel3Layout.createSequentialGroup()
                        .addComponent(jScrollPane2, javax.swing.GroupLayout.PREFERRED_SIZE, 218, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(jPanel3Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(btaddnucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(btremovenucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 22, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
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
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 271, Short.MAX_VALUE)
                .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addComponent(bthome, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 0, Short.MAX_VALUE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(jPanel1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(jPanel2, javax.swing.GroupLayout.PREFERRED_SIZE, 388, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap(120, Short.MAX_VALUE))
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

    private void btcancelarferiadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btcancelarferiadoMouseClicked
        panelferiadoadd.setVisible(false);
    }//GEN-LAST:event_btcancelarferiadoMouseClicked

    private void btsaveferiadoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsaveferiadoMouseClicked
        saveFeriado();
    }//GEN-LAST:event_btsaveferiadoMouseClicked

    private void btaddnucleoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btaddnucleoMouseClicked
        panelAddNucleo.setVisible(true);
    }//GEN-LAST:event_btaddnucleoMouseClicked

    private void btremovenucleoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btremovenucleoMouseClicked
        int posicao = listaNucleos.getSelectedIndex();
        Nucleo nucleo = ((DefaultListModel<Nucleo>) listaNucleos.getModel()).get(posicao);
        removeNucleo(nucleo);
    }//GEN-LAST:event_btremovenucleoMouseClicked

    private void btsavenucleoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btsavenucleoMouseClicked
        saveNucleo();
    }//GEN-LAST:event_btsavenucleoMouseClicked

    private void btcancelarnucleoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btcancelarnucleoMouseClicked
        txtnomenucleo.setText(null);
        txtdescricaonucleo.setText(null);
        panelAddNucleo.setVisible(false);
    }//GEN-LAST:event_btcancelarnucleoMouseClicked

    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btaddferiado;
    private javax.swing.JLabel btaddnucleo;
    private javax.swing.JLabel btcancelarferiado;
    private javax.swing.JLabel btcancelarnucleo;
    private javax.swing.JLabel bthome;
    private javax.swing.JLabel btremoveferiado;
    private javax.swing.JLabel btremovenucleo;
    private javax.swing.JLabel btsaveferiado;
    private javax.swing.JLabel btsavenucleo;
    private org.jdesktop.swingx.JXDatePicker datepickerFim;
    private org.jdesktop.swingx.JXDatePicker datepickerInicio;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JPanel jPanel2;
    private javax.swing.JPanel jPanel3;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JScrollPane jScrollPane2;
    private javax.swing.JList listFeriados;
    private javax.swing.JList listaNucleos;
    private javax.swing.JPanel panelAddNucleo;
    private javax.swing.JPanel panelferiadoadd;
    private org.jdesktop.swingx.JXDatePicker pickFeriado;
    private javax.swing.JTextField txtdescricao;
    private javax.swing.JTextField txtdescricaonucleo;
    private javax.swing.JTextField txtnomenucleo;
    // End of variables declaration//GEN-END:variables
}
