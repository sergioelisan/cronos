/*
 * HorariosExibirPanel.java
 *
 * Created on 22/12/2011, 11:00:30
 */
package senai.cronos.gui.horarios;

import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.List;
import javax.swing.*;
import senai.cronos.Fachada;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.Tile;
import senai.cronos.gui.events.LinkEffectHandler;

/**
 *
 * @author Serginho
 */
public class HorariosExibirPanel extends javax.swing.JPanel {

    private static HorariosExibirPanel instance = new HorariosExibirPanel();
    private List<HorarioUI> calendarios;
    private Horario horario;
    private JPanel pnTurmas = new JPanel();
    private JPanel pnCalendarios = new JPanel();
    private JPanel pnHorarios = new JPanel();
    private JPanel pnLegendas = new JPanel();
    private JPanel pnLoading = new JPanel();
    private JLabel lbLoading = new JLabel();
    private JLabel setaDireita = new JLabel(">");
    private JLabel setaEsquerda = new JLabel("<");
    private JLabel lbVoltar = new JLabel("voltar");
    private JLabel lbPrint = new JLabel("imprimir");
    private Timer animacao;
    private final int DELAY = 500;

    public static HorariosExibirPanel getInstance() {
        return instance;
    }

    private HorariosExibirPanel() {
        initComponents();
        setLayout(new CardLayout());

        pnTurmas.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTurmas.setOpaque(false);

        createCalendarComponents();

        lbLoading.setPreferredSize(new Dimension(250, 120));
        lbLoading.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        lbLoading.setForeground(new Color(61, 61, 61));

        pnLoading.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 75));
        pnLoading.setOpaque(false);
        pnLoading.add(lbLoading);

        add(pnLoading, "LOADING");
        add(pnTurmas, "TURMAS");
        add(pnCalendarios, "CALENDARIOS");

        loadTurmas();
        show("TURMAS");
    }

    /**
     * inicializa os components voltados para calendarios
     */
    private void createCalendarComponents() {
        
        class LinkHandler extends MouseAdapter {
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
        
        lbVoltar.setPreferredSize(new Dimension(100, 25));
        lbVoltar.setOpaque(true);
        lbVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbVoltar.setHorizontalAlignment(JLabel.CENTER);
        lbVoltar.setForeground(Color.white);
        lbVoltar.setBackground(ColorManager.getColor("button"));
        lbVoltar.addMouseListener(new LinkHandler());
        lbVoltar.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                show("TURMAS");
            }
        });

        lbPrint.setPreferredSize(new Dimension(100, 25));
        lbPrint.setOpaque(true);
        lbPrint.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbPrint.setHorizontalAlignment(JLabel.CENTER);
        lbPrint.setForeground(Color.white);
        lbPrint.setBackground(ColorManager.getColor("button"));
        lbPrint.addMouseListener(new LinkHandler());
        lbPrint.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                print();
            }
        });

        JPanel toolbox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        toolbox.setPreferredSize(new Dimension(1366, 40));
        toolbox.setOpaque(false);
        toolbox.add(lbVoltar);
        toolbox.add(lbPrint);

        pnHorarios.setLayout(new CardLayout());

        setaDireita.setHorizontalAlignment(JLabel.CENTER);
        setaDireita.setOpaque(true);
        setaDireita.setBackground(Color.white);
        setaDireita.setFont(new Font("Segoe UI", Font.BOLD, 26));
        setaDireita.setPreferredSize(new Dimension(80, 150));
        setaDireita.addMouseListener(new LinkEffectHandler());
        setaDireita.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                next();
            }
        });

        setaEsquerda.setHorizontalAlignment(JLabel.CENTER);
        setaEsquerda.setOpaque(true);
        setaEsquerda.setBackground(Color.white);
        setaEsquerda.setFont(new Font("Segoe UI", Font.BOLD, 26));
        setaEsquerda.setPreferredSize(new Dimension(80, 150));
        setaEsquerda.addMouseListener(new LinkEffectHandler());
        setaEsquerda.addMouseListener(new MouseAdapter() {

            @Override
            public void mouseClicked(MouseEvent evt) {
                previous();
            }
        });

        pnLegendas.setPreferredSize(new Dimension(810, 200));
        pnLegendas.setBackground(Color.white);
        pnLegendas.setOpaque(true);

        pnCalendarios.setLayout(new BorderLayout());
        pnCalendarios.setOpaque(false);

        pnCalendarios.add(toolbox, BorderLayout.NORTH);
        pnCalendarios.add(setaDireita, BorderLayout.EAST);
        pnCalendarios.add(setaEsquerda, BorderLayout.WEST);
        pnCalendarios.add(pnHorarios, BorderLayout.CENTER);
        pnCalendarios.add(pnHorarios, BorderLayout.CENTER);
        pnCalendarios.add(pnLegendas, BorderLayout.SOUTH);
    }

    /**
     * carrega as turmas para forma de tiles
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadTurmas() {
        Thread t = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    List<Turma> turmas = Fachada.<Turma>get(Turma.class);
                    for (Turma t : turmas) {
                        Tile tile = new Tile();
                        tile.setNome(t.getNome());
                        tile.setId(t.getId() + "");
                        tile.setClickEvent(new TileClickedHandler());
                        pnTurmas.add(tile);
                    }
                } catch (ClassNotFoundException | SQLException ex) {
                    JOptionPane.showMessageDialog(null, "FAIL! Problema ao carregar turmas:\n" + ex);
                }
            }
        });

        t.start();
    }

    /**
     * Inicia a animacao de carregamento
     */
    private void startLoading() {
        show("LOADING");

        animacao = new Timer(DELAY, new ActionListener() {

            private int turn = 0;
            private String[] chars = {" ", ".", "..", "..."};

            @Override
            public void actionPerformed(ActionEvent ae) {
                lbLoading.setText("carregando " + chars[turn]);
                turn++;

                if (turn == chars.length) {
                    turn = 0;
                }
            }
        });

        animacao.start();
    }

    /**
     * pausa a animacao de carregamento
     */
    private void stopLoading() {
        animacao.stop();
        animacao = null;
    }

    /**
     * troca a exibicao dos paines ou para TURMAS ou para CALENDARIOS
     *
     * @param panel
     */
    private void show(String panel) {
        ((CardLayout) getLayout()).show(this, panel);
    }

    /**
     * vai um calendario pra frente
     */
    private void next() {
        ((CardLayout) pnHorarios.getLayout()).next(pnHorarios);
    }

    /**
     * vai um calendario para tras
     */
    private void previous() {
        ((CardLayout) pnHorarios.getLayout()).previous(pnHorarios);
    }

    /**
     * Imprime o horario em PDF
     */
    private void print() {
    }

    /**
     * Gera um horario a partir de um ID de uma turma, e exibe
     *
     * @param id
     */
    public void loadHorario(final Integer id) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pnLegendas.removeAll();
                    pnHorarios.removeAll();

                    Turma turma = Fachada.<Turma>get(Turma.class, id);
                    horario = Fachada.<Horario>get(Horario.class, turma.getId());

                    if (!horario.getHorario().isEmpty()) {

                        HorarioUIFactory factory = new HorarioUIFactory(horario);
                        calendarios = factory.getCalendarios();

                        for (HorarioUI calendario : calendarios) {
                            pnHorarios.add(calendario, calendario.getMes().toLowerCase());
                        }

                        for (JLabel legenda : factory.getLegendas()) {
                            pnLegendas.add(legenda);
                        }

                        stopLoading();
                        show("CALENDARIOS");
                        ((CardLayout) pnHorarios.getLayout()).show(pnHorarios, calendarios.get(calendarios.size() - 1).getMes());
                    } else {
                        JOptionPane.showMessageDialog(null, "Horario inexistente! Tente criar um na opção 'gerar'. ");
                        show("TURMAS");
                    }


                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "FAIL! Erro ao Exibir Horario:\n" + e);
                    show("TURMAS");
                    e.printStackTrace();
                }
            }
        });

        thread.start();
        startLoading();
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

            loadHorario(Integer.parseInt(tile.getId()));
        }
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1360, 600));
        setMinimumSize(new java.awt.Dimension(1015, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1360, 600));

        javax.swing.GroupLayout layout = new javax.swing.GroupLayout(this);
        this.setLayout(layout);
        layout.setHorizontalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 1360, Short.MAX_VALUE)
        );
        layout.setVerticalGroup(
            layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGap(0, 600, Short.MAX_VALUE)
        );
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables
}
