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
import senai.cronos.gui.custom.ImageLoader;
import senai.cronos.gui.events.BlinkEffectHandler;

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
        super.paintComponents(g);
        
        Image wallpaper = ImageLoader.loadBackground();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }
    
    /**
     * insere o uma instacia do objeto que faz os efeitos de iluminacao quando o mouse passa por cima
     * de cada um desses objetos
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
        lbdocentesicon.addMouseListener(new BlinkEffectHandler());
        lbhorario.addMouseListener(new BlinkEffectHandler());
        lbhorarioicon.addMouseListener(new BlinkEffectHandler());
        lbrelatorio.addMouseListener(new BlinkEffectHandler());
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
        lbuser = new javax.swing.JLabel();
        btConfig = new javax.swing.JLabel();
        jPanel1 = new javax.swing.JPanel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnApps = new javax.swing.JPanel();
        horariotile = new javax.swing.JPanel();
        lbhorarioicon = new javax.swing.JLabel();
        lbhorario = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        jLabel7 = new javax.swing.JLabel();
        docentestile = new javax.swing.JPanel();
        lbdocentes = new javax.swing.JLabel();
        lbdocentesicon = new javax.swing.JLabel();
        turmastile = new javax.swing.JPanel();
        lbturmas = new javax.swing.JLabel();
        cadastrotile = new javax.swing.JPanel();
        lbcadastro = new javax.swing.JLabel();
        jLabel1 = new javax.swing.JLabel();
        disciplinastile = new javax.swing.JPanel();
        lbdisciplinas = new javax.swing.JLabel();
        jLabel2 = new javax.swing.JLabel();
        reportstile = new javax.swing.JPanel();
        lbrelatorio = new javax.swing.JLabel();
        jLabel3 = new javax.swing.JLabel();
        helptile = new javax.swing.JPanel();
        lbajuda = new javax.swing.JLabel();
        lbajudaicon = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1366, 728));
        setMinimumSize(new java.awt.Dimension(1024, 728));
        setPreferredSize(new java.awt.Dimension(1366, 728));

        lbtitle.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbtitle.setForeground(ColorManager.getColor("foreground"));
        lbtitle.setText("Home");

        lbhora.setFont(new java.awt.Font("Segoe UI", 1, 24)); // NOI18N
        lbhora.setForeground(ColorManager.getColor("foreground"));
        lbhora.setText("carregando...");

        lbdata.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbdata.setText("carregando...");

        lbuser.setFont(new java.awt.Font("Segoe UI", 0, 16)); // NOI18N
        lbuser.setText("usuário");

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
        jPanel1.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 5, 75));

        magicScroll1.setActiveWheelGesture(true);
        magicScroll1.setMaximumSize(new java.awt.Dimension(1200, 400));
        magicScroll1.setMinimumSize(new java.awt.Dimension(1000, 400));
        magicScroll1.setPreferredSize(new java.awt.Dimension(1200, 400));

        pnApps.setMaximumSize(new java.awt.Dimension(1200, 3000));
        pnApps.setMinimumSize(new java.awt.Dimension(1000, 3000));
        pnApps.setOpaque(false);
        pnApps.setPreferredSize(new java.awt.Dimension(1200, 3000));
        pnApps.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 6, 6));

        horariotile.setBackground(ColorManager.getColor("home.horarios"));
        horariotile.setPreferredSize(new java.awt.Dimension(365, 165));
        horariotile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                horariotileMouseClicked(evt);
            }
        });

        lbhorarioicon.setFont(new java.awt.Font("Segoe UI", 0, 36)); // NOI18N
        lbhorarioicon.setForeground(new java.awt.Color(255, 255, 255));
        lbhorarioicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhorarioicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/clock_2.png"))); // NOI18N
        lbhorarioicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbhorarioiconMouseClicked(evt);
            }
        });

        lbhorario.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbhorario.setForeground(new java.awt.Color(255, 255, 255));
        lbhorario.setText("horários");
        lbhorario.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbhorarioMouseClicked(evt);
            }
        });

        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setText("- exibir horário");

        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setText("- gerar horário");

        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setText("- imprimir horário");

        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setText("- salvar horário");

        javax.swing.GroupLayout horariotileLayout = new javax.swing.GroupLayout(horariotile);
        horariotile.setLayout(horariotileLayout);
        horariotileLayout.setHorizontalGroup(
            horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(horariotileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(horariotileLayout.createSequentialGroup()
                        .addComponent(lbhorario)
                        .addGap(111, 111, 111))
                    .addGroup(horariotileLayout.createSequentialGroup()
                        .addGap(10, 10, 10)
                        .addGroup(horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel5, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel4, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel7, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                            .addComponent(jLabel6))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)))
                .addComponent(lbhorarioicon, javax.swing.GroupLayout.PREFERRED_SIZE, 148, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addContainerGap())
        );
        horariotileLayout.setVerticalGroup(
            horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(horariotileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(horariotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbhorarioicon)
                    .addGroup(horariotileLayout.createSequentialGroup()
                        .addComponent(lbhorario)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel4)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel5)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6)))
                .addContainerGap(25, Short.MAX_VALUE))
        );

        pnApps.add(horariotile);

        docentestile.setBackground(ColorManager.getColor("home.docentes"));
        docentestile.setPreferredSize(new java.awt.Dimension(290, 165));
        docentestile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                docentestileMouseClicked(evt);
            }
        });

        lbdocentes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbdocentes.setForeground(new java.awt.Color(255, 255, 255));
        lbdocentes.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdocentes.setText("docentes");
        lbdocentes.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdocentesMouseClicked(evt);
            }
        });

        lbdocentesicon.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbdocentesicon.setForeground(new java.awt.Color(255, 255, 255));
        lbdocentesicon.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdocentesicon.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/docente.png"))); // NOI18N
        lbdocentesicon.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdocentesiconMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout docentestileLayout = new javax.swing.GroupLayout(docentestile);
        docentestile.setLayout(docentestileLayout);
        docentestileLayout.setHorizontalGroup(
            docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docentestileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdocentes, javax.swing.GroupLayout.DEFAULT_SIZE, 270, Short.MAX_VALUE)
                    .addComponent(lbdocentesicon, javax.swing.GroupLayout.DEFAULT_SIZE, 266, Short.MAX_VALUE))
                .addContainerGap())
        );
        docentestileLayout.setVerticalGroup(
            docentestileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(docentestileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbdocentesicon, javax.swing.GroupLayout.PREFERRED_SIZE, 117, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbdocentes, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnApps.add(docentestile);

        turmastile.setBackground(ColorManager.getColor("home.turmas"));
        turmastile.setPreferredSize(new java.awt.Dimension(365, 165));
        turmastile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                turmastileMouseClicked(evt);
            }
        });

        lbturmas.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbturmas.setForeground(new java.awt.Color(255, 255, 255));
        lbturmas.setText("turmas");
        lbturmas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbturmasMouseClicked(evt);
            }
        });

        javax.swing.GroupLayout turmastileLayout = new javax.swing.GroupLayout(turmastile);
        turmastile.setLayout(turmastileLayout);
        turmastileLayout.setHorizontalGroup(
            turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, turmastileLayout.createSequentialGroup()
                .addContainerGap(280, Short.MAX_VALUE)
                .addComponent(lbturmas)
                .addContainerGap())
        );
        turmastileLayout.setVerticalGroup(
            turmastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(turmastileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbturmas)
                .addContainerGap(121, Short.MAX_VALUE))
        );

        pnApps.add(turmastile);

        cadastrotile.setBackground(ColorManager.getColor("home.cadastro"));
        cadastrotile.setPreferredSize(new java.awt.Dimension(215, 165));
        cadastrotile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                cadastrotileMouseClicked(evt);
            }
        });

        lbcadastro.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbcadastro.setForeground(new java.awt.Color(255, 255, 255));
        lbcadastro.setText("cadastro");
        lbcadastro.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbcadastroMouseClicked(evt);
            }
        });

        jLabel1.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/people.png"))); // NOI18N
        jLabel1.setText("jLabel1");

        javax.swing.GroupLayout cadastrotileLayout = new javax.swing.GroupLayout(cadastrotile);
        cadastrotile.setLayout(cadastrotileLayout);
        cadastrotileLayout.setHorizontalGroup(
            cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(cadastrotileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastrotileLayout.createSequentialGroup()
                        .addGap(0, 62, Short.MAX_VALUE)
                        .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 129, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(cadastrotileLayout.createSequentialGroup()
                        .addComponent(lbcadastro)
                        .addGap(0, 125, Short.MAX_VALUE)))
                .addContainerGap())
        );
        cadastrotileLayout.setVerticalGroup(
            cadastrotileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, cadastrotileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(jLabel1, javax.swing.GroupLayout.PREFERRED_SIZE, 116, Short.MAX_VALUE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                .addComponent(lbcadastro)
                .addGap(12, 12, 12))
        );

        pnApps.add(cadastrotile);

        disciplinastile.setBackground(ColorManager.getColor("home.disciplinas"));
        disciplinastile.setPreferredSize(new java.awt.Dimension(215, 165));
        disciplinastile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                disciplinastileMouseClicked(evt);
            }
        });

        lbdisciplinas.setFont(new java.awt.Font("Segoe UI", 0, 18)); // NOI18N
        lbdisciplinas.setForeground(new java.awt.Color(255, 255, 255));
        lbdisciplinas.setText("disciplinas");
        lbdisciplinas.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbdisciplinasMouseClicked(evt);
            }
        });

        jLabel2.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/book1.png"))); // NOI18N

        javax.swing.GroupLayout disciplinastileLayout = new javax.swing.GroupLayout(disciplinastile);
        disciplinastile.setLayout(disciplinastileLayout);
        disciplinastileLayout.setHorizontalGroup(
            disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(disciplinastileLayout.createSequentialGroup()
                .addContainerGap()
                .addGroup(disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addComponent(lbdisciplinas, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, disciplinastileLayout.createSequentialGroup()
                        .addGap(0, 86, Short.MAX_VALUE)
                        .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 105, javax.swing.GroupLayout.PREFERRED_SIZE)))
                .addContainerGap())
        );
        disciplinastileLayout.setVerticalGroup(
            disciplinastileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(disciplinastileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbdisciplinas)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, 104, Short.MAX_VALUE)
                .addContainerGap())
        );

        pnApps.add(disciplinastile);

        reportstile.setBackground(ColorManager.getColor("home.relatorios"));
        reportstile.setPreferredSize(new java.awt.Dimension(365, 165));
        reportstile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                reportstileMouseClicked(evt);
            }
        });

        lbrelatorio.setFont(new java.awt.Font("Segoe UI", 0, 24)); // NOI18N
        lbrelatorio.setForeground(new java.awt.Color(255, 255, 255));
        lbrelatorio.setText("relatórios");
        lbrelatorio.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbrelatorioMouseClicked(evt);
            }
        });

        jLabel3.setIcon(new javax.swing.ImageIcon(getClass().getResource("/senai/cronos/gui/images/relatorios (1).png"))); // NOI18N

        javax.swing.GroupLayout reportstileLayout = new javax.swing.GroupLayout(reportstile);
        reportstile.setLayout(reportstileLayout);
        reportstileLayout.setHorizontalGroup(
            reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportstileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbrelatorio)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 114, Short.MAX_VALUE)
                .addComponent(jLabel3)
                .addContainerGap())
        );
        reportstileLayout.setVerticalGroup(
            reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(reportstileLayout.createSequentialGroup()
                .addGroup(reportstileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(reportstileLayout.createSequentialGroup()
                        .addContainerGap()
                        .addComponent(lbrelatorio))
                    .addGroup(reportstileLayout.createSequentialGroup()
                        .addGap(29, 29, 29)
                        .addComponent(jLabel3, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
        );

        pnApps.add(reportstile);

        helptile.setBackground(ColorManager.getColor("home.ajuda"));
        helptile.setPreferredSize(new java.awt.Dimension(215, 165));
        helptile.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                helptileMouseClicked(evt);
            }
        });

        lbajuda.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
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
                    .addComponent(lbajuda, javax.swing.GroupLayout.Alignment.TRAILING, javax.swing.GroupLayout.DEFAULT_SIZE, 195, Short.MAX_VALUE)
                    .addComponent(lbajudaicon, javax.swing.GroupLayout.DEFAULT_SIZE, 191, Short.MAX_VALUE))
                .addContainerGap())
        );
        helptileLayout.setVerticalGroup(
            helptileLayout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, helptileLayout.createSequentialGroup()
                .addContainerGap()
                .addComponent(lbajudaicon, javax.swing.GroupLayout.PREFERRED_SIZE, 108, javax.swing.GroupLayout.PREFERRED_SIZE)
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 13, Short.MAX_VALUE)
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
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.TRAILING)
                    .addGroup(javax.swing.GroupLayout.Alignment.LEADING, layout.createSequentialGroup()
                        .addComponent(lbtitle)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1246, Short.MAX_VALUE)
                        .addComponent(btConfig, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(jPanel1, javax.swing.GroupLayout.Alignment.LEADING, javax.swing.GroupLayout.DEFAULT_SIZE, 1342, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbhora)
                        .addGap(18, 18, 18)
                        .addComponent(lbdata)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 1036, Short.MAX_VALUE)
                        .addComponent(lbuser)))
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
                .addComponent(jPanel1, javax.swing.GroupLayout.DEFAULT_SIZE, 588, Short.MAX_VALUE)
                .addGap(31, 31, 31)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbdata)
                    .addComponent(lbuser)
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

    private void lbdocentesiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdocentesiconMouseClicked
        main.Switch(CronosFrame.DOCENTES);
    }//GEN-LAST:event_lbdocentesiconMouseClicked

    private void lbdocentesMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdocentesMouseClicked
        main.Switch(CronosFrame.DOCENTES);
    }//GEN-LAST:event_lbdocentesMouseClicked

    private void lbhorarioiconMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbhorarioiconMouseClicked
        main.Switch(CronosFrame.HORARIOS);
    }//GEN-LAST:event_lbhorarioiconMouseClicked

    private void lbhorarioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbhorarioMouseClicked
        main.Switch(CronosFrame.HORARIOS);
    }//GEN-LAST:event_lbhorarioMouseClicked

    private void lbturmasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbturmasMouseClicked
        main.Switch(CronosFrame.TURMAS);
    }//GEN-LAST:event_lbturmasMouseClicked

    private void turmastileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_turmastileMouseClicked
        main.Switch(CronosFrame.TURMAS);
    }//GEN-LAST:event_turmastileMouseClicked

    private void lbcadastroMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbcadastroMouseClicked
        main.Switch(CronosFrame.CADASTROS);
    }//GEN-LAST:event_lbcadastroMouseClicked

    private void cadastrotileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_cadastrotileMouseClicked
        main.Switch(CronosFrame.CADASTROS);
    }//GEN-LAST:event_cadastrotileMouseClicked

    private void lbrelatorioMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbrelatorioMouseClicked
        main.Switch(CronosFrame.RELATORIOS);
    }//GEN-LAST:event_lbrelatorioMouseClicked

    private void reportstileMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_reportstileMouseClicked
        main.Switch(CronosFrame.RELATORIOS);
    }//GEN-LAST:event_reportstileMouseClicked

    private void lbdisciplinasMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbdisciplinasMouseClicked
        main.Switch(CronosFrame.DISCIPLINAS);
    }//GEN-LAST:event_lbdisciplinasMouseClicked

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
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbajuda;
    private javax.swing.JLabel lbajudaicon;
    private javax.swing.JLabel lbcadastro;
    private javax.swing.JLabel lbdata;
    private javax.swing.JLabel lbdisciplinas;
    private javax.swing.JLabel lbdocentes;
    private javax.swing.JLabel lbdocentesicon;
    private javax.swing.JLabel lbhora;
    private javax.swing.JLabel lbhorario;
    private javax.swing.JLabel lbhorarioicon;
    private javax.swing.JLabel lbrelatorio;
    private javax.swing.JLabel lbtitle;
    private javax.swing.JLabel lbturmas;
    private javax.swing.JLabel lbuser;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnApps;
    private javax.swing.JPanel reportstile;
    private javax.swing.JPanel turmastile;
    // End of variables declaration//GEN-END:variables
}
