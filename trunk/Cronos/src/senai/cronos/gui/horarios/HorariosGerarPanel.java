/*
 * HorariosGerarPanel.java
 *
 * Created on 22/12/2011, 11:00:15
 */
package senai.cronos.gui.horarios;

import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import javax.swing.*;
import senai.cronos.CronosAPI;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.database.dao.DAOTurma;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.custom.LinkEffectHandler;
import senai.cronos.gui.custom.Tile;
import senai.cronos.horario.GeraHorarioFactory;
import senai.cronos.horario.Horario;
import senai.cronos.util.Export;
import senai.util.Observador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorariosGerarPanel extends javax.swing.JPanel implements HorariosUIClient, Observador {

    private List<HorarioUI> calendarios = new ArrayList<>();
    private Turma actualTurma;
    private JPanel pnGerar = new JPanel();
    private JPanel pnTurmas = new JPanel();
    private JPanel pnCalendarios = new JPanel();
    private JPanel pnHorarios = new JPanel();
    private JPanel pnLegendas = new JPanel();
    private JPanel pnLoading = new JPanel();
    private JLabel lbLoading = new JLabel();
    private JLabel setaDireita = new JLabel(">");
    private JLabel setaEsquerda = new JLabel("<");
    private JLabel lbVoltar = new JLabel("voltar");
    private JLabel lbSave = new JLabel("salvar");
    private JLabel lbPrint = new JLabel("imprimir");
    private Timer animacao;
    private final int DELAY = 500;
    private static HorariosGerarPanel instance = new HorariosGerarPanel();

    /**
     * retorna uma instancia unica desde painel
     *
     * @return
     */
    public static HorariosGerarPanel getInstance() {
        return instance;
    }

    private HorariosGerarPanel() {
        initComponents();
        setLayout(new CardLayout());

        JScrollPane scrollTurmas = new JScrollPane(pnTurmas);
        scrollTurmas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        scrollTurmas.setHorizontalScrollBarPolicy(JScrollPane.HORIZONTAL_SCROLLBAR_NEVER);
        scrollTurmas.setOpaque(false);
        scrollTurmas.setBorder(null);
        scrollTurmas.setMaximumSize(new Dimension(1300, 9000));
        scrollTurmas.setMinimumSize(new Dimension(900, 9000));
        scrollTurmas.setPreferredSize(new Dimension(1300, 9000));

        scrollTurmas.getViewport().setMaximumSize(new Dimension(1300, 9000));
        scrollTurmas.getViewport().setMinimumSize(new Dimension(900, 9000));
        scrollTurmas.getViewport().setPreferredSize(new Dimension(1300, 9000));

        scrollTurmas.getViewport().setOpaque(false);

        pnTurmas.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTurmas.setMinimumSize(new Dimension(900, 9000));
        pnTurmas.setPreferredSize(new Dimension(1300, 9000));
        pnTurmas.setMaximumSize(new Dimension(1300, 9000));
        pnTurmas.setOpaque(false);

        pnGerar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 250));
        pnGerar.setMinimumSize(new Dimension(900, 768));
        pnGerar.setPreferredSize(new Dimension(1300, 768));
        pnGerar.setMaximumSize(new Dimension(1300, 1080));
        pnGerar.setOpaque(false);

        Tile todas = new Tile();
        todas.setNome("Todas as turmas");
        todas.setId("");
        todas.setClickEvent(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                gerarTodasTurmas();
            }
        });

        Tile turmas = new Tile();
        turmas.setNome("Escolher turma");
        turmas.setId("");
        turmas.setClickEvent(new MouseAdapter() {
            public void mouseClicked(MouseEvent e) {
                show("TURMAS");
            }
        });

        pnGerar.add(turmas);
        pnGerar.add(todas);

        createCalendarComponents();

        lbLoading.setPreferredSize(new Dimension(250, 120));
        lbLoading.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        lbLoading.setForeground(new Color(61, 61, 61));

        pnLoading.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 75));
        pnLoading.setOpaque(false);
        pnLoading.add(lbLoading);

        add(pnLoading, "LOADING");
        add(scrollTurmas, "TURMAS");
        add(pnGerar, "GERAR");

        try { CronosAPI.subscribe(Turma.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        show("GERAR");
    }

    /**
     * inicializa os components voltados para calendarios
     */
    private void createCalendarComponents() {
        lbVoltar.setPreferredSize(new Dimension(100, 25));
        lbVoltar.setOpaque(true);
        lbVoltar.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbVoltar.setHorizontalAlignment(JLabel.CENTER);
        lbVoltar.setForeground(Color.white);
        lbVoltar.setBackground(ColorManager.getColor("button"));
        lbVoltar.addMouseListener(new HorariosUI.LinkHandler());
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
        lbPrint.addMouseListener(new HorariosUI.LinkHandler());
        lbPrint.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                print();
            }
        });

        lbSave.setPreferredSize(new Dimension(100, 25));
        lbSave.setOpaque(true);
        lbSave.setFont(new Font("Segoe UI", Font.PLAIN, 14));
        lbSave.setHorizontalAlignment(JLabel.CENTER);
        lbSave.setForeground(Color.white);
        lbSave.setBackground(ColorManager.getColor("button"));
        lbSave.addMouseListener(new HorariosUI.LinkHandler());
        lbSave.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                saveHorario();
            }
        });

        JPanel toolbox = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 0));
        toolbox.setPreferredSize(new Dimension(1366, 40));
        toolbox.setOpaque(false);
        toolbox.add(lbVoltar);
        toolbox.add(lbSave);
        toolbox.add(lbPrint);

        pnHorarios.setLayout(new CardLayout());

        Dimension setaDIM = new Dimension(40, 150);

        setaDireita.setHorizontalAlignment(JLabel.CENTER);
        setaDireita.setOpaque(true);
        setaDireita.setBackground(Color.white);
        setaDireita.setFont(new Font("Segoe UI", Font.BOLD, 26));
        setaDireita.setPreferredSize(setaDIM);
        setaDireita.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                next();
            }
            
             @Override
            public void mouseEntered(MouseEvent evt) {
                JLabel lb = (JLabel) evt.getSource();
                lb.setForeground(Color.WHITE);
                lb.setBackground(Color.BLUE);
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                JLabel lb = (JLabel) evt.getSource();
                lb.setForeground(Color.BLACK);
                lb.setBackground(Color.WHITE);
            }
        });

        setaEsquerda.setHorizontalAlignment(JLabel.CENTER);
        setaEsquerda.setOpaque(true);
        setaEsquerda.setBackground(Color.white);
        setaEsquerda.setFont(new Font("Segoe UI", Font.BOLD, 26));
        setaEsquerda.setPreferredSize(setaDIM);
        setaEsquerda.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent evt) {
                previous();
            }
            
            @Override
            public void mouseEntered(MouseEvent evt) {
                JLabel lb = (JLabel) evt.getSource();
                lb.setForeground(Color.WHITE);
                lb.setBackground(Color.BLUE);
            }
            
            @Override
            public void mouseExited(MouseEvent evt) {
                JLabel lb = (JLabel) evt.getSource();
                lb.setForeground(Color.BLACK);
                lb.setBackground(Color.WHITE);
            }
        });

        JScrollPane scrollLegendas = new JScrollPane(pnLegendas);
        scrollLegendas.setBorder(null);
        scrollLegendas.setMaximumSize(new Dimension(810, 200));
        scrollLegendas.setPreferredSize(new Dimension(810, 200));
        scrollLegendas.setMinimumSize(new Dimension(810, 200));
        scrollLegendas.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        pnLegendas.setPreferredSize(new Dimension(810, 800));
        pnLegendas.setBackground(Color.WHITE);
        pnLegendas.setOpaque(true);

        pnCalendarios.setLayout(new BorderLayout());
        pnCalendarios.setOpaque(false);

        pnCalendarios.add(toolbox, BorderLayout.NORTH);
        pnCalendarios.add(setaDireita, BorderLayout.EAST);
        pnCalendarios.add(setaEsquerda, BorderLayout.WEST);
        pnCalendarios.add(pnHorarios, BorderLayout.CENTER);
        pnCalendarios.add(scrollLegendas, BorderLayout.SOUTH);
    }

    /**
     * carrega as turmas para forma de tiles
     *
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private void loadTurmas() {
        new Thread(new HorariosUI.LoadTurmas(pnTurmas, this)).start();
    }

    /**
     * Inicia a animacao de carregamento
     */
    private void startLoading() {
        show("LOADING");
        animacao = new Timer(DELAY, new HorariosUI.LoadingEffect(lbLoading));
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
    public void show(String panel) {
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
     * Salva o horario no banco de dados
     */
    private void saveHorario() {
        try {
            DAOTurma dao = (DAOTurma) DAOFactory.getDao(Turma.class);
            dao.addHorario(actualTurma);

            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "FAIL! Erro ao Salvar Horario:\n" + e);
            show("TURMAS");
        }
    }

    /**
     * Imprime o horario em PDF
     */
    private void print() {
        try {
            new Export(actualTurma).generate();
            JOptionPane.showMessageDialog(null, "Arquivo enviado para a Área de Trabalho");
        } catch (Exception ex) {
            Alerta.jogarAviso("Nao foi possivel gerar o arquivo Excel:\n" + ex);
        }
    }

    /**
     * Gera o horario para todas as turmas
     */
    private void gerarTodasTurmas() {
        new Thread(new Runnable() {
            public void run() {
                try {
                    for (Turma t : CronosAPI.<Turma>get(Turma.class)) {
                        gerarHorario(t);
                    }
                    stopLoading();
                    HorariosUI.getInstance().move("exibir");
                } catch (Exception e) {
                    Alerta.jogarAviso("Problemas ao gerar horario para todas as turmas:" + e);
                }
            }
        }).start();

        startLoading();
    }

    /**
     * Gera um horario a partir de um ID de uma turma, e exibe
     *
     * @param id
     */
    @Override
    public void action(final Integer id) {
        Thread thread = new Thread(new Runnable() {
            @Override
            public void run() {
                try {
                    actualTurma = CronosAPI.<Turma>get(Turma.class, id);

                    if (!actualTurma.getHorario().isVazio()) {
                        if (JOptionPane
                                .showConfirmDialog(null,
                                "Deseja sobrescrever o Horário já existente?",
                                "Sobrescrita de Horário",
                                JOptionPane.YES_NO_OPTION,
                                JOptionPane.WARNING_MESSAGE)
                                == JOptionPane.YES_OPTION) {

                            actualTurma.setHorario(new Horario());
                        }

                    }

                    // Gera o horario
                    gerarHorario(actualTurma);

                    stopLoading();
                    show("CALENDARIOS");
                    
                    HorariosUI.getInstance().exibir(actualTurma.getId() );

                } catch (Exception e) {
                    stopLoading();
                    JOptionPane.showMessageDialog(null, "FAIL! Erro ao Gerar Horario:\n" + e);
                    e.printStackTrace(System.err);
                    show("TURMAS");
                }
            }
        });

        thread.start();
        startLoading();
    }

    /**
     * Gera o horario de uma turma
     */
    private void gerarHorario(Turma turma) throws Exception {
        GeraHorarioFactory.getGerador().gerarHorario(turma);
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(1360, 600));
        setMinimumSize(new java.awt.Dimension(1015, 600));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(1360, 600));
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    // End of variables declaration//GEN-END:variables

    @Override
    public void update() {
        loadTurmas();
    }
}
