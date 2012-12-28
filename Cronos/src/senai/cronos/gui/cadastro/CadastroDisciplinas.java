/*
 * CadastroDisciplinas.java
 *
 * Created on 03/01/2012, 03:47:04
 */
package senai.cronos.gui.cadastro;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JLabel;
import javax.swing.JTextArea;
import javax.swing.JTextField;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Laboratorio;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.Dialog;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.util.Observador;

/**
 *
 * Interface grafica para cadastro de docentes. Nessa classe, temos rotinas que
 * interagem com a fachada para a adição, alteração, remoção e listagem de
 * Unidades Curriculares no banco de dados.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class CadastroDisciplinas extends javax.swing.JPanel implements Observador {

    /**
     * Lista de nucleos que agrupam as unidades curriculares
     */
    private List<Nucleo> nucleos;
    /**
     * Lista de laboratorios
     */
    private List<Laboratorio> labs;
    /**
     * Contador usado para exibir um determinado elemento da lista de nucleos
     */
    private int posicao = -1;

    /**
     * Construtor. Chama o método de criação de componentes e adiciona efeitos a
     * certos elementos da interface. Por fim, inicia os dados a serem exibidos
     * no painél usado para mostrar as Unidades Curriculares.
     */
    public CadastroDisciplinas() {
        initComponents();
        lbproximo.addMouseListener(new LinkEffectHandler());
        lbanterior.addMouseListener(new LinkEffectHandler());
        btnovo.addMouseListener(new LinkEffectHandler());
        btremove.addMouseListener(new LinkEffectHandler());
        btsave.addMouseListener(new LinkEffectHandler());
         lbproximo.setBackground(new Color(50,50,200,20));
        lbanterior.setBackground(new Color(50,50,200,20));
        btnovo.setBackground(new Color(50,50,200,20));
        btremove.setBackground(new Color(50,50,200,20));
        btsave.setBackground(new Color(50,50,200,20));

        try {
            CronosAPI.subscribe(Nucleo.class, this);
            CronosAPI.subscribe(Laboratorio.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        update();
    }

    /**
     * inicializa os dados de disciplinas
     */
    private void loadComboboxes() {
        combonucleo.removeAllItems();
        combonucleo.addItem("-- núcleos --");
        for (Nucleo nc : nucleos) {
            combonucleo.addItem(nc.getNome());
        }

        combolab.removeAllItems();
        combolab.addItem("-- laboratórios --");
        for (Laboratorio lb : labs) {
            combolab.addItem(lb.getNome());
        }

        load();
    }

    /**
     * carrega os docentes de um determinado nucleo e imprime na ui
     *
     * @param nucleo
     */
    private void load() {
        pnShow.removeAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<UnidadeCurricular> disciplinas;
                    if (posicao == -1) {
                        disciplinas = CronosAPI.<UnidadeCurricular>get(UnidadeCurricular.class);
                        lbnucleoatual.setText("todos");
                    } else {
                        Nucleo nu = nucleos.get(posicao);
                        disciplinas = CronosAPI.buscaDisciplinas(nu);
                        lbnucleoatual.setText(nu.getNome().toLowerCase());
                    }

                    for (UnidadeCurricular d : disciplinas) {
                        Tile ct = new Tile();
                        ct.setNome(d.getNome());
                        ct.setId(String.valueOf(d.getCargaHoraria()) + "h");
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
     * carrega as informacoes da unidade curricular
     *
     * @param nome
     */
    private void show(final String nome) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    UnidadeCurricular uc = CronosAPI.buscaDisciplina(nome);
                    lbcodigo.setText(String.valueOf(uc.getId()));
                    txtnome.setText(uc.getNome());
                    combonucleo.setSelectedItem(uc.getNucleo().getNome());
                    combolab.setSelectedItem(uc.getLab().getNome());
                    txtcarga.setText(String.valueOf(uc.getCargaHoraria()));
                    txtmodulo.setText(String.valueOf(uc.getModulo()));
                    txtementa.setText(uc.getConteudoProgramatico());
                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                }
            }
        }).start();
    }

    /**
     * limpa os campos para adicionarem informacoes neles
     */
    private void novo() {
        load();
        lbcodigo.setText("código");
        txtcarga.setText("carga horária");
        txtementa.setText("conteúdo programático");
        txtmodulo.setText("módulo");
        txtnome.setText("nome");
        combolab.setSelectedIndex(0);
        combonucleo.setSelectedIndex(0);
    }

    /**
     * Remove um objeto do banco de dados
     */
    private void remove() {
        JDialog dialog = Dialog.getDialog("Removendo Disciplina. Aguarde...");

        String code = lbcodigo.getText();
        if (!code.equals("código")) {
            Integer id = Integer.parseInt(code);
            try {
                CronosAPI.remove(UnidadeCurricular.class, id);
            } catch (ClassNotFoundException | SQLException e) {
                Alerta.jogarAviso(e.getMessage());
            } finally {
                novo();
                dialog.dispose();
            }
        }
    }

    /**
     * salva um objeto no banco de dados
     */
    private void save() {
        JDialog dialog = Dialog.getDialog("Salvando Disciplina. Aguarde...");

        try {
            UnidadeCurricular uc = new UnidadeCurricular();
            uc.setCargaHoraria(Integer.parseInt(txtcarga.getText().trim()));

            String ementa = txtementa.getText().trim().equals("conteúdo programático") ? "" : txtementa.getText();
            uc.setConteudoProgramatico(ementa);

            uc.setNucleo(CronosAPI.buscaNucleo((String) combonucleo.getSelectedItem()));
            uc.setLab(labs.get(combolab.getSelectedIndex() - 1));
            uc.setModulo(Integer.parseInt(txtmodulo.getText().trim()));
            uc.setNome(txtnome.getText().trim());

            String code = lbcodigo.getText();

            if (code.equals("código")) {
                CronosAPI.add(uc);
            } else {

                uc.setId(Integer.parseInt(code));
                CronosAPI.update(uc);
            }

        } catch (ClassNotFoundException | SQLException e) {
            Alerta.jogarAviso(e.getMessage());
        } finally {
            novo();
            dialog.dispose();
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

    @Override
    public void update() {
        try {
            labs = CronosAPI.<Laboratorio>get(Laboratorio.class);
            nucleos = CronosAPI.<Nucleo>get(Nucleo.class);
            loadComboboxes();
        } catch (Exception e) {
            Alerta.jogarAviso(e.getMessage());
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

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jLabel1 = new javax.swing.JLabel();
        txtnome = new javax.swing.JTextField();
        combonucleo = new javax.swing.JComboBox();
        txtcarga = new javax.swing.JTextField();
        txtmodulo = new javax.swing.JTextField();
        jScrollPane1 = new javax.swing.JScrollPane();
        txtementa = new javax.swing.JTextArea();
        combolab = new javax.swing.JComboBox();
        lbcodigo = new javax.swing.JLabel();
        lbnucleoatual = new javax.swing.JLabel();
        lbanterior = new javax.swing.JLabel();
        lbproximo = new javax.swing.JLabel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        btsave = new javax.swing.JLabel();
        btremove = new javax.swing.JLabel();
        btnovo = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1342, 591));
        setMinimumSize(new java.awt.Dimension(990, 591));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1342, 591));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("disciplinas");

        txtnome.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtnome.setText("nome");
        txtnome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnomeFocusLost(evt);
            }
        });

        combonucleo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        combonucleo.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- núcleo --" }));

        txtcarga.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtcarga.setText("carga horária");
        txtcarga.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtcargaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtcargaFocusLost(evt);
            }
        });

        txtmodulo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtmodulo.setText("módulo");
        txtmodulo.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmoduloFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmoduloFocusLost(evt);
            }
        });

        txtementa.setColumns(20);
        txtementa.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtementa.setLineWrap(true);
        txtementa.setRows(5);
        txtementa.setText("conteúdo programático");
        txtementa.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtementaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtementaFocusLost(evt);
            }
        });
        jScrollPane1.setViewportView(txtementa);

        combolab.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        combolab.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- laboratório --" }));

        lbcodigo.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
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
        lbanterior.setOpaque(true);
        lbanterior.setPreferredSize(new java.awt.Dimension(100, 25));
        lbanterior.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                lbanteriorMouseClicked(evt);
            }
        });

        lbproximo.setBackground(new java.awt.Color(255, 255, 255));
        lbproximo.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbproximo.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbproximo.setText("próximo núcleo");
        lbproximo.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        lbproximo.setMaximumSize(new java.awt.Dimension(100, 25));
        lbproximo.setMinimumSize(new java.awt.Dimension(100, 25));
        lbproximo.setOpaque(true);
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

        pnShow.setMaximumSize(new java.awt.Dimension(1340, 9000));
        pnShow.setMinimumSize(new java.awt.Dimension(900, 3000));
        pnShow.setOpaque(false);
        pnShow.setPreferredSize(new java.awt.Dimension(1340, 9000));
        pnShow.setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.LEFT, 10, 10));
        magicScroll1.setViewportView(pnShow);

        btsave.setBackground(new java.awt.Color(255, 255, 255));
        btsave.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        btsave.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        btsave.setText("salvar");
        btsave.setHorizontalTextPosition(javax.swing.SwingConstants.CENTER);
        btsave.setMaximumSize(new java.awt.Dimension(100, 25));
        btsave.setMinimumSize(new java.awt.Dimension(100, 25));
        btsave.setOpaque(true);
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
        btremove.setOpaque(true);
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
        btnovo.setOpaque(true);
        btnovo.setPreferredSize(new java.awt.Dimension(100, 25));
        btnovo.addMouseListener(new java.awt.event.MouseAdapter() {
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                btnovoMouseClicked(evt);
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
                        .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1318, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addGap(0, 944, Short.MAX_VALUE))
                    .addGroup(javax.swing.GroupLayout.Alignment.TRAILING, layout.createSequentialGroup()
                        .addComponent(lbcodigo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtnome)
                            .addComponent(txtmodulo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(combolab, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(txtcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 489, Short.MAX_VALUE)))
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
                .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 313, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(combolab, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addComponent(txtmodulo)
                            .addComponent(txtcarga, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addComponent(jScrollPane1, javax.swing.GroupLayout.DEFAULT_SIZE, 93, Short.MAX_VALUE)
                    .addComponent(lbcodigo, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                .addContainerGap())
        );
    }// </editor-fold>//GEN-END:initComponents

    private void lbanteriorMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbanteriorMouseClicked
        previousNucleo();
    }//GEN-LAST:event_lbanteriorMouseClicked

    private void lbproximoMouseClicked(java.awt.event.MouseEvent evt) {//GEN-FIRST:event_lbproximoMouseClicked
        nextNucleo();
    }//GEN-LAST:event_lbproximoMouseClicked

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
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().equals("nome")) {
            txt.setText(null);
        }
    }//GEN-LAST:event_txtnomeFocusGained

    private void txtnomeFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtnomeFocusLost
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().isEmpty()) {
            txt.setText("nome");
        }
    }//GEN-LAST:event_txtnomeFocusLost

    private void txtcargaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcargaFocusGained
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().equals("carga horária")) {
            txt.setText(null);
        }
    }//GEN-LAST:event_txtcargaFocusGained

    private void txtcargaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtcargaFocusLost
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().isEmpty()) {
            txt.setText("carga horária");
        }
    }//GEN-LAST:event_txtcargaFocusLost

    private void txtmoduloFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmoduloFocusGained
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().equals("módulo")) {
            txt.setText(null);
        }
    }//GEN-LAST:event_txtmoduloFocusGained

    private void txtmoduloFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmoduloFocusLost
        JTextField txt = (JTextField) evt.getSource();
        if (txt.getText().trim().isEmpty()) {
            txt.setText("módulo");
        }
    }//GEN-LAST:event_txtmoduloFocusLost

    private void txtementaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtementaFocusGained
        JTextArea txt = (JTextArea) evt.getSource();
        if (txt.getText().trim().equals("conteúdo programático")) {
            txt.setText(null);
        }
    }//GEN-LAST:event_txtementaFocusGained

    private void txtementaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtementaFocusLost
        JTextArea txt = (JTextArea) evt.getSource();
        if (txt.getText().trim().isEmpty()) {
            txt.setText("conteúdo programático");
        }
    }//GEN-LAST:event_txtementaFocusLost
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel btnovo;
    private javax.swing.JLabel btremove;
    private javax.swing.JLabel btsave;
    private javax.swing.JComboBox combolab;
    private javax.swing.JComboBox combonucleo;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JScrollPane jScrollPane1;
    private javax.swing.JLabel lbanterior;
    private javax.swing.JLabel lbcodigo;
    private javax.swing.JLabel lbnucleoatual;
    private javax.swing.JLabel lbproximo;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnShow;
    private javax.swing.JTextField txtcarga;
    private javax.swing.JTextArea txtementa;
    private javax.swing.JTextField txtmodulo;
    private javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
