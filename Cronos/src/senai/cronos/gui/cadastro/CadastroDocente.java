/*
 * CadastroDocente.java
 *
 * Created on 03/01/2012, 03:46:29
 */
package senai.cronos.gui.cadastro;

import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import senai.cronos.CronosAPI;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.entidades.Formacao;
import senai.cronos.entidades.Turno;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.util.Aleatorio;
import senai.util.Observador;

/**
 *
 * interface grafica para o cadastro de docentes no banco de dados. Tem rotinas
 * para interagir com a fachada do sistema, para realizar operacoes de adicao,
 * alteracao, remocao e listagem de Docentes do sistema.
 *
 * @author Carlos Melo eSergio Lisan
 */
public class CadastroDocente extends javax.swing.JPanel implements Observador {

    /**
     * Lista de nucleos que agrupam os docentes
     */
    private List<Nucleo> nucleos;
    /**
     * posicao atual da lista usada para armazenar os nucleos
     */
    private int posicao = -1;

    /**
     * Construtor, inicia os componentes e seus dados.
     */
    public CadastroDocente() {
        initComponents();

        lbproximo.addMouseListener(new LinkEffectHandler());
        lbanterior.addMouseListener(new LinkEffectHandler());
        btnovo.addMouseListener(new LinkEffectHandler());
        btremove.addMouseListener(new LinkEffectHandler());
        btsave.addMouseListener(new LinkEffectHandler());

        for (Formacao f : Formacao.values()) {
            comboformacao.addItem(f.name().toLowerCase());
        }

        comboturnos.addItem("manhã");
        comboturnos.addItem("tarde");
        comboturnos.addItem("noite");
        comboturnos.addItem("manhã e tarde");
        comboturnos.addItem("manhã e noite");
        comboturnos.addItem("tarde e noite");

        // recebe atualizacoes do DAONucleo assim que alguma coisa for alterada
        try {
            CronosAPI.subscribe(Nucleo.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        update();
    }

    /**
     * inicializa os dados de disciplinas
     */
    private void loadNucleosCombobox() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                combonucleo.removeAllItems();
                combonucleo.addItem("-- núcleos --");
                for (Nucleo nc : nucleos) {
                    combonucleo.addItem(nc.getNome());
                }

            }
        }).start();
        load();
    }

    /**
     * Cria um objeto Docente com os dados inseridos nos elementos da Interface
     * grafica e manda-o para a fachada salva-lo
     */
    private void save() {
        try {
            Docente docente = new Docente();
            docente.setMatricula(Integer.parseInt(txtmatricula.getText()));
            docente.setContratacao(datecontratacao.getDate());
            docente.setFormacao(Formacao.valueOf(((String) comboformacao.getSelectedItem()).toUpperCase()));
            docente.setNome(txtnome.getText().trim());
            docente.setTurno(Turno.getTurno(comboturnos.getSelectedIndex() - 1));
            docente.setNucleo(nucleos.get(combonucleo.getSelectedIndex() - 1));
            docente.setScore(1);

            if (CronosAPI.buscaDocenteMatricula(txtmatricula.getText()) == null) {
                CronosAPI.add(docente);
            } else {
                CronosAPI.update(docente);
            }

            // Adiciona proficiencias-padrao para o novo docente
            for (UnidadeCurricular uc : CronosAPI.buscaDisciplinas(docente.getNucleo())) {
                Proficiencia proficiencia = new Proficiencia(docente, uc, 1, 1);
                docente.getProficiencias().add(proficiencia);
                CronosAPI.add(proficiencia);
            }
        } catch (ClassNotFoundException | SQLException e) {
            Alerta.jogarAviso(e.getMessage());
        } finally {
            novo();
        }

    }

    /**
     * Trata com a fachada a remocao de um objeto Docente, por sua matricula
     */
    private void remove() {
        new Thread(new Runnable() {
            @Override
            public void run() {
                String code = txtmatricula.getText();
                if (!code.equals("matrícula")) {
                    Integer id = Integer.parseInt(code);
                    try {
                        CronosAPI.remove(Docente.class, id);
                    } catch (ClassNotFoundException | SQLException e) {
                        Alerta.jogarAviso(e.getMessage());
                    } finally {
                        novo();
                    }
                }
            }
        }).start();
    }

    /**
     * inicializa os elementos usados para inserir dados dos docentes.
     */
    private void novo() {
        load();
        txtmatricula.setText("matrícula");
        txtnome.setText("nome");
        datecontratacao.setDate(null);
        comboformacao.setSelectedIndex(0);
        combonucleo.setSelectedIndex(0);
        comboturnos.setSelectedIndex(0);
    }

    /**
     * carrega os docentes, agrupados por nucleo, para serem exibidos no painel
     * de exibicao de docentes.
     */
    private void load() {
        pnShow.removeAll();
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    List<Docente> docentes;
                    if (posicao == -1) {
                        docentes = CronosAPI.<Docente>get(Docente.class);
                        lbnucleoatual.setText("todos");
                    } else {
                        Nucleo nucleo = nucleos.get(posicao);
                        docentes = CronosAPI.buscaDocentes(nucleo);
                        lbnucleoatual.setText(nucleo.getNome().toLowerCase());
                    }

                    for (Docente doc : docentes) {
                        if (!doc.getNome().equals("docente")) {
                            Tile ct = new Tile();
                            ct.setNome(doc.getNome());
                            ct.setId(String.valueOf(doc.getMatricula()) + "");
                            ct.setClickEvent(new TileClickedHandler());
                            pnShow.add(ct);
                        }
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "Problemas ao carregas o docente:\n" + ex);

                }

            }
        }).start();
        pnShow.repaint();
    }

    /**
     * Exibe os dados de um docente selecionado do painel de exibicao, nos
     * componentes usados para manipulacao de dados.
     *
     * @param matricula
     */
    private void show(final String matricula) {
        new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    Docente dc = CronosAPI.<Docente>get(Docente.class, Integer.parseInt(matricula));
                    txtmatricula.setText(String.valueOf(dc.getMatricula()));
                    datecontratacao.setDate(dc.getContratacao());
                    txtnome.setText(dc.getNome());
                    comboformacao.setSelectedIndex(dc.getFormacao().ordinal() + 1);
                    combonucleo.setSelectedItem(dc.getNucleo().getNome());
                    comboturnos.setSelectedIndex(dc.getTurno().ordinal() + 1);

                } catch (ClassNotFoundException | SQLException ex) {
                    Alerta.jogarAviso(ex.getMessage());
                }
            }
        }).start();
    }

    @Override
    public void update() {
        try {
            nucleos = CronosAPI.<Nucleo>get(Nucleo.class);
            loadNucleosCombobox();
        } catch (ClassNotFoundException | SQLException ex) {
            Alerta.jogarAviso(ex.getMessage());
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

            show(tile.getId());
        }
    }

    /**
     * passa para o proximo nucleo
     */
    private void nextNucleo() {
        if (++posicao == nucleos.size()) {
            posicao = -1;
        }
        load();
    }

    /**
     * volta para o nucleo anterior
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
        comboturnos = new javax.swing.JComboBox();
        lbnucleoatual = new javax.swing.JLabel();
        btnovo = new javax.swing.JLabel();
        lbanterior = new javax.swing.JLabel();
        combonucleo = new javax.swing.JComboBox();
        lbproximo = new javax.swing.JLabel();
        magicScroll1 = new br.ufrpe.bcc.continuous.components.MagicScroll();
        pnShow = new javax.swing.JPanel();
        txtnome = new javax.swing.JTextField();
        btsave = new javax.swing.JLabel();
        btremove = new javax.swing.JLabel();
        txtmatricula = new javax.swing.JTextField();
        comboformacao = new javax.swing.JComboBox();
        datecontratacao = new org.jdesktop.swingx.JXDatePicker();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1342, 591));
        setMinimumSize(new java.awt.Dimension(1024, 591));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1342, 591));

        jLabel1.setFont(new java.awt.Font("Segoe UI", 0, 30)); // NOI18N
        jLabel1.setForeground(ColorManager.getColor("foreground"));
        jLabel1.setText("docentes");

        comboturnos.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboturnos.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- turnos --" }));

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
        txtnome.setText("nome");
        txtnome.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtnomeFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtnomeFocusLost(evt);
            }
        });

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

        txtmatricula.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        txtmatricula.setText("matrícula");
        txtmatricula.addFocusListener(new java.awt.event.FocusAdapter() {
            public void focusGained(java.awt.event.FocusEvent evt) {
                txtmatriculaFocusGained(evt);
            }
            public void focusLost(java.awt.event.FocusEvent evt) {
                txtmatriculaFocusLost(evt);
            }
        });

        comboformacao.setFont(new java.awt.Font("SansSerif", 0, 14)); // NOI18N
        comboformacao.setModel(new javax.swing.DefaultComboBoxModel(new String[] { "-- formação --" }));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(lbnucleoatual, javax.swing.GroupLayout.PREFERRED_SIZE, 100, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, 910, Short.MAX_VALUE)
                        .addComponent(lbanterior, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(lbproximo, javax.swing.GroupLayout.PREFERRED_SIZE, 151, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, 1322, Short.MAX_VALUE)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(jLabel1)
                            .addGroup(layout.createSequentialGroup()
                                .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 109, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addGap(18, 18, 18)
                                .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 120, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(layout.createSequentialGroup()
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                                    .addComponent(comboformacao, 0, 220, Short.MAX_VALUE)
                                    .addComponent(datecontratacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
                                .addGap(18, 18, 18)
                                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(comboturnos, javax.swing.GroupLayout.PREFERRED_SIZE, 220, javax.swing.GroupLayout.PREFERRED_SIZE))))
                        .addGap(0, 626, Short.MAX_VALUE)))
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
                .addComponent(magicScroll1, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(btsave, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btremove, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(btnovo, javax.swing.GroupLayout.PREFERRED_SIZE, 25, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addGap(18, 18, 18)
                .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(layout.createSequentialGroup()
                        .addGroup(layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                            .addComponent(txtmatricula, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(comboformacao, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addGap(18, 18, 18)
                        .addComponent(txtnome, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addComponent(combonucleo, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addGap(18, 18, 18)
                        .addComponent(comboturnos, javax.swing.GroupLayout.PREFERRED_SIZE, 40, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(layout.createSequentialGroup()
                        .addGap(59, 59, 59)
                        .addComponent(datecontratacao, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)))
                .addContainerGap())
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

    private void txtmatriculaFocusGained(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmatriculaFocusGained
        if (txtmatricula.getText().equals("matrícula")) {
            txtmatricula.setText(null);
        }
    }//GEN-LAST:event_txtmatriculaFocusGained

    private void txtmatriculaFocusLost(java.awt.event.FocusEvent evt) {//GEN-FIRST:event_txtmatriculaFocusLost
        if (txtmatricula.getText().equals("")) {
            txtmatricula.setText("matrícula");
        }
    }//GEN-LAST:event_txtmatriculaFocusLost

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
    private javax.swing.JComboBox comboformacao;
    private javax.swing.JComboBox combonucleo;
    private javax.swing.JComboBox comboturnos;
    private org.jdesktop.swingx.JXDatePicker datecontratacao;
    private javax.swing.JLabel jLabel1;
    private javax.swing.JLabel lbanterior;
    private javax.swing.JLabel lbnucleoatual;
    private javax.swing.JLabel lbproximo;
    private br.ufrpe.bcc.continuous.components.MagicScroll magicScroll1;
    private javax.swing.JPanel pnShow;
    private javax.swing.JTextField txtmatricula;
    private javax.swing.JTextField txtnome;
    // End of variables declaration//GEN-END:variables
}
