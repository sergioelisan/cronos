/*
 * Home.java
 *
 * Created on 22/12/2011, 09:06:07
 */
package senai.cronos.gui;

import java.awt.Graphics;
import java.awt.Image;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.DateFormat;
import java.util.Date;
import javax.swing.Timer;
import senai.cronos.Main;
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.custom.BlinkEffectHandler;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Home extends javax.swing.JPanel {

    private CronosFrame main;

    public Home(CronosFrame main) {
        this.main = main;
        initComponents();
        rotinas();
        initEffects();

    }

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }

    /**
     * insere o uma instacia do objeto que faz os efeitos de iluminacao quando o
     * mouse passa por cima de cada um desses objetos
     */
    private void initEffects() {
        horariotile.addMouseListener(new BlinkEffectHandler());
        docentestile.addMouseListener(new BlinkEffectHandler());
        cadastrotile.addMouseListener(new BlinkEffectHandler());
        reportstile.addMouseListener(new BlinkEffectHandler());
        turmastile.addMouseListener(new BlinkEffectHandler());
        disciplinastile.addMouseListener(new BlinkEffectHandler());
        helptile.addMouseListener(new BlinkEffectHandler());
        lbajuda.addMouseListener(new BlinkEffectHandler());
        lbajudaicon.addMouseListener(new BlinkEffectHandler());
        lbcadastro.addMouseListener(new BlinkEffectHandler());
        lbdisciplinas.addMouseListener(new BlinkEffectHandler());
        lbdocentes.addMouseListener(new BlinkEffectHandler());
        lbhorarios.addMouseListener(new BlinkEffectHandler());
        lbturmas.addMouseListener(new BlinkEffectHandler());
    }

    private void rotinas() {
        Timer data = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DateFormat fmt = DateFormat.getDateInstance(DateFormat.LONG);
                lbdata.setText(fmt.format(new Date()));
            }
        });

        Timer clock = new Timer(1000, new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent ae) {
                DateFormat fmt = DateFormat.getTimeInstance(DateFormat.SHORT);
                lbhora.setText(fmt.format(new Date()));
            }
        });

        clock.start();
        data.start();
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbtitle = new javax.swing.JLabel();
        lbhora = new javax.swing.JLabel();
        lbdata = new javax.swing.JLabel();
        btConfig = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnApps = new javax.swing.JPanel();
        horariotile = new javax.swing.JPanel();
        lbhorarios = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        docentestile = new javax.swing.JPanel();
        lbdocentes = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();
        turmastile = new javax.swing.JPanel();
        lbturmas = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        cadastrotile = new javax.swing.JPanel();
        lbcadastro = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        disciplinastile = new javax.swing.JPanel();
        lbdisciplinas = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        reportstile = new javax.swing.JPanel();
        lbrelatorios = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        helptile = new javax.swing.JPanel();
        lbajuda = new javax.swing.JLabel();
        lbajudaicon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 700));
        setMinimumSize(new java.awt.Dimension(1024, 700));
        setPreferredSize(new java.awt.Dimension(1366, 700));

        lbtitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbtitle.setForeground(ColorManager.getColor("foreground"));
        lbtitle.setText("página inicial");

        lbhora.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbhora.setForeground(ColorManager.getColor("foreground"));
        lbhora.setText("carregando...");

        lbdata.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbdata.setText("carregando...");

        btConfig.setBackground(ColorManager.getColor("button"));
        btConfig.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btConfig.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/config.png"))); // NOI18N
        btConfig.setMaximumSize(new java.awt.Dimension(35, 35));
        btConfig.setMinimumSize(new java.awt.Dimension(35, 35));
        btConfig.setOpaque(true);
        btConfig.setPreferredSize(new java.awt.Dimension(35, 35));
        btConfig.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btConfigMouseClicked(evt);
            }
        });

        jPanel1.setOpaque(false);
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 2, 75));

        magicScroll1.setActiveWheelGesture(true);
        magicScroll1.setMaximumSize(new java.awt.Dimension(1200, 400));
        magicScroll1.setMinimumSize(new java.awt.Dimension(800, 400));
        magicScroll1.setPreferredSize(new java.awt.Dimension(1000, 400));

        pnApps.setMaximumSize(new java.awt.Dimension(1200, 600));
        pnApps.setMinimumSize(new java.awt.Dimension(800, 600));
        pnApps.setOpaque(false);
        pnApps.setPreferredSize(new java.awt.Dimension(1000, 600));
        pnApps.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 3, 3));

        horariotile.setBackground(ColorManager.getColor("home.horarios"));
        horariotile.setMaximumSize(new java.awt.Dimension(320, 165));
        horariotile.setPreferredSize(new java.awt.Dimension(360, 165));
        horariotile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                horariotileMouseClicked(evt);
            }
        });

        lbhorarios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhorarios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/clock_icon.png"))); // NOI18N
        lbhorarios.setToolTipText("");
        lbhorarios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbhorariosMouseClicked(evt);
            }
        });

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel1.setForeground(new java.awt.Color(255, 255, 255));
        jLabel1.setText("horário");

        javax.swing.GroupLayout horariotileLayout = new javax.swing.GroupLayout(horariotile);
        horariotile.setLayout(horariotileLayout);
        horariotileLayout.setHorizontalGroup(
            horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(horariotileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, horariotileLayout.createSequentialGroup()
                        .addGap(0, 243, Short.MAX_VALUE)
                        .addComponent(lbhorarios)))
                .addContainerGap())
        );
        horariotileLayout.setVerticalGroup(
            horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, horariotileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 27, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addGap(18, 18, 18)
                .addComponent(lbhorarios, javax.swing.GroupLayout.PREFERRED_SIZE, 98, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );

        pnApps.add(horariotile);

        docentestile.setBackground(ColorManager.getColor("home.docentes"));
        docentestile.setPreferredSize(new java.awt.Dimension(290, 165));
        docentestile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                docentestileMouseClicked(evt);
            }
        });

        lbdocentes.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbdocentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdocentes.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/capelo_icon.png"))); // NOI18N
        lbdocentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdocentesMouseClicked(evt);
            }
        });

        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setText("docentes");

        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setText("configurar proficiência");

        javax.swing.GroupLayout docentestileLayout = new javax.swing.GroupLayout(docentestile);
        docentestile.setLayout(docentestileLayout);
        docentestileLayout.setHorizontalGroup(
            docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docentestileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel2, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(docentestileLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 22, Short.MAX_VALUE)
                        .addComponent(lbdocentes)))
                .addContainerGap())
        );
        docentestileLayout.setVerticalGroup(
            docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, docentestileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel2)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdocentes, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addGroup(docentestileLayout.createSequentialGroup()
                        .addComponent(jLabel8)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnApps.add(docentestile);

        turmastile.setBackground(ColorManager.getColor("home.turmas"));
        turmastile.setMaximumSize(new java.awt.Dimension(330, 165));
        turmastile.setPreferredSize(new java.awt.Dimension(330, 165));
        turmastile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                turmastileMouseClicked(evt);
            }
        });

        lbturmas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbturmas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/turmas_icon.png"))); // NOI18N
        lbturmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbturmasMouseClicked(evt);
            }
        });

        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setText("turmas");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("cadastrar disciplinas");

        javax.swing.GroupLayout turmastileLayout = new javax.swing.GroupLayout(turmastile);
        turmastile.setLayout(turmastileLayout);
        turmastileLayout.setHorizontalGroup(
            turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(turmastileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(turmastileLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 99, Short.MAX_VALUE)
                        .addComponent(lbturmas)))
                .addContainerGap())
        );
        turmastileLayout.setVerticalGroup(
            turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, turmastileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel3)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addGroup(turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbturmas, javax.swing.GroupLayout.DEFAULT_SIZE, 105, Short.MAX_VALUE)
                    .addGroup(turmastileLayout.createSequentialGroup()
                        .addComponent(jLabel7)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnApps.add(turmastile);

        cadastrotile.setBackground(ColorManager.getColor("home.cadastro"));
        cadastrotile.setMaximumSize(new java.awt.Dimension(226, 165));
        cadastrotile.setPreferredSize(new java.awt.Dimension(226, 165));
        cadastrotile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrotileMouseClicked(evt);
            }
        });

        lbcadastro.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbcadastro.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/cadastro_icon.png"))); // NOI18N
        lbcadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbcadastroMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("cadastro");

        javax.swing.GroupLayout cadastrotileLayout = new javax.swing.GroupLayout(cadastrotile);
        cadastrotile.setLayout(cadastrotileLayout);
        cadastrotileLayout.setHorizontalGroup(
            cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastrotileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, 206, Short.MAX_VALUE)
                    .addGroup(cadastrotileLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbcadastro)))
                .addContainerGap())
        );
        cadastrotileLayout.setVerticalGroup(
            cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastrotileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel4)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
                .addComponent(lbcadastro)
                .addContainerGap())
        );

        pnApps.add(cadastrotile);

        disciplinastile.setBackground(ColorManager.getColor("home.disciplinas"));
        disciplinastile.setPreferredSize(new java.awt.Dimension(215, 165));
        disciplinastile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disciplinastileMouseClicked(evt);
            }
        });

        lbdisciplinas.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdisciplinas.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/disciplina_icon.png"))); // NOI18N
        lbdisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdisciplinasMouseClicked(evt);
            }
        });

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("disciplina");

        javax.swing.GroupLayout disciplinastileLayout = new javax.swing.GroupLayout(disciplinastile);
        disciplinastile.setLayout(disciplinastileLayout);
        disciplinastileLayout.setHorizontalGroup(
            disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(disciplinastileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addGroup(disciplinastileLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbdisciplinas)))
                .addContainerGap())
        );
        disciplinastileLayout.setVerticalGroup(
            disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, disciplinastileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel5)
                .addGap(18, 18, 18)
                .addComponent(lbdisciplinas, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnApps.add(disciplinastile);

        reportstile.setBackground(ColorManager.getColor("home.relatorios"));
        reportstile.setPreferredSize(new java.awt.Dimension(320, 165));
        reportstile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportstileMouseClicked(evt);
            }
        });

        lbrelatorios.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbrelatorios.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/tiles/relatorios_icon.png"))); // NOI18N
        lbrelatorios.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbrelatoriosMouseClicked(evt);
            }
        });

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("relatórios");

        javax.swing.GroupLayout reportstileLayout = new javax.swing.GroupLayout(reportstile);
        reportstile.setLayout(reportstileLayout);
        reportstileLayout.setHorizontalGroup(
            reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportstileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jLabel6, javax.swing.GroupLayout.DEFAULT_SIZE, 300, Short.MAX_VALUE)
                    .addGroup(reportstileLayout.createSequentialGroup()
                        .addGap(0, 0, Short.MAX_VALUE)
                        .addComponent(lbrelatorios)))
                .addContainerGap())
        );
        reportstileLayout.setVerticalGroup(
            reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, reportstileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel6)
                .addGap(18, 18, 18)
                .addComponent(lbrelatorios, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnApps.add(reportstile);

        helptile.setBackground(ColorManager.getColor("home.ajuda"));
        helptile.setPreferredSize(new java.awt.Dimension(216, 165));
        helptile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helptileMouseClicked(evt);
            }
        });

        lbajuda.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbajuda.setForeground(new java.awt.Color(255, 255, 255));
        lbajuda.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbajuda.setText("ajuda");
        lbajuda.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbajudaMouseClicked(evt);
            }
        });

        lbajudaicon.setFont(new java.awt.Font("Segoe UI", 0, 96)); // NOI18N
        lbajudaicon.setForeground(new java.awt.Color(255, 255, 255));
        lbajudaicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbajudaicon.setText("?");
        lbajudaicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbajudaiconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout helptileLayout = new javax.swing.GroupLayout(helptile);
        helptile.setLayout(helptileLayout);
        helptileLayout.setHorizontalGroup(
            helptileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(helptileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(helptileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbajuda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE)
                    .addComponent(lbajudaicon, javax.swing.GroupLayout.DEFAULT_SIZE, 196, Short.MAX_VALUE))
                .addContainerGap())
        );
        helptileLayout.setVerticalGroup(
            helptileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helptileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbajudaicon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addComponent(lbajuda)
                .addContainerGap())
        );

        pnApps.add(helptile);

        magicScroll1.setViewportView(pnApps);

        jPanel1.add(magicScroll1);

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 1346, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbtitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1167, Short.MAX_VALUE)
                        .addComponent(btConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbhora)
                        .addGap(18, 18, 18)
                        .addComponent(lbdata)
                        .addGap(0, 0, Short.MAX_VALUE)))
                .addContainerGap())
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(btConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbtitle))
                .addGap(18, 18, 18)
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 555, Short.MAX_VALUE)
                .addGap(48, 48, 48)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdata)
                    .addComponent(lbhora))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void horariotileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_horariotileMouseClicked
        main.Switch(CronosFrame.HORARIOS);
    }//GEN-LAST:event_horariotileMouseClicked

    private void docentestileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_docentestileMouseClicked
        main.Switch(CronosFrame.DOCENTES);
        
    }//GEN-LAST:event_docentestileMouseClicked

    private void turmastileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_turmastileMouseClicked
        main.Switch(CronosFrame.TURMAS);
    }//GEN-LAST:event_turmastileMouseClicked

    private void cadastrotileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrotileMouseClicked
        main.Switch(CronosFrame.CADASTROS);
    }//GEN-LAST:event_cadastrotileMouseClicked

    private void reportstileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportstileMouseClicked
        main.Switch(CronosFrame.RELATORIOS);
    }//GEN-LAST:event_reportstileMouseClicked

    private void disciplinastileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_disciplinastileMouseClicked
        main.Switch(CronosFrame.DISCIPLINAS);
    }//GEN-LAST:event_disciplinastileMouseClicked

    private void lbajudaiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbajudaiconMouseClicked
        main.Switch(CronosFrame.AJUDA);
    }//GEN-LAST:event_lbajudaiconMouseClicked

    private void lbajudaMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbajudaMouseClicked
        main.Switch(CronosFrame.AJUDA);
    }//GEN-LAST:event_lbajudaMouseClicked

    private void helptileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_helptileMouseClicked
        main.Switch(CronosFrame.AJUDA);
    }//GEN-LAST:event_helptileMouseClicked

    private void btConfigMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_btConfigMouseClicked
        main.Switch(CronosFrame.CONFIG);
    }//GEN-LAST:event_btConfigMouseClicked

    private void lbhorariosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbhorariosMouseClicked
        horariotileMouseClicked(evt);
    }//GEN-LAST:event_lbhorariosMouseClicked

    private void lbdocentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdocentesMouseClicked
        docentestileMouseClicked(evt);
    }//GEN-LAST:event_lbdocentesMouseClicked

    private void lbturmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbturmasMouseClicked
        turmastileMouseClicked(evt);
    }//GEN-LAST:event_lbturmasMouseClicked

    private void lbcadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbcadastroMouseClicked
        cadastrotileMouseClicked(evt);
    }//GEN-LAST:event_lbcadastroMouseClicked

    private void lbdisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdisciplinasMouseClicked
        disciplinastileMouseClicked(evt);
    }//GEN-LAST:event_lbdisciplinasMouseClicked

    private void lbrelatoriosMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbrelatoriosMouseClicked
        reportstileMouseClicked(evt);
    }//GEN-LAST:event_lbrelatoriosMouseClicked
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btConfig;
    private javax.swing.JPanel cadastrotile;
    private javax.swing.JPanel disciplinastile;
    private javax.swing.JPanel docentestile;
    private javax.swing.JPanel helptile;
    private javax.swing.JPanel horariotile;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbajuda;
    private javax.swing.JLabel lbajudaicon;
    private javax.swing.JLabel lbcadastro;
    private javax.swing.JLabel lbdata;
    private javax.swing.JLabel lbdisciplinas;
    private javax.swing.JLabel lbdocentes;
    private javax.swing.JLabel lbhora;
    private javax.swing.JLabel lbhorarios;
    private javax.swing.JLabel lbrelatorios;
    private javax.swing.JLabel lbtitle;
    private javax.swing.JLabel lbturmas;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnApps;
    private javax.swing.JPanel reportstile;
    private javax.swing.JPanel turmastile;
    // End of variables declaration//GEN-END:variables
}
