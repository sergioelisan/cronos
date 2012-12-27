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
import javax.swing.*;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.custom.Tile;
import senai.cronos.horario.GeraHorarioFactory;
import senai.cronos.horario.Horario;
import senai.util.Observador;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorariosGerarPanel extends javax.swing.JPanel implements HorariosUIClient, Observador {

    private Turma actualTurma;
    
    private JPanel pnGerar = new JPanel();    
    private JPanel pnTurmas = new JPanel();    
    private JPanel pnLoading = new JPanel();
    
    private JLabel lbLoading = new JLabel();
    
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
        scrollTurmas.setPreferredSize(new Dimension(980, 9000));

        scrollTurmas.getViewport().setMaximumSize(new Dimension(1300, 9000));
        scrollTurmas.getViewport().setMinimumSize(new Dimension(900, 9000));
        scrollTurmas.getViewport().setPreferredSize(new Dimension(9800, 9000));

        scrollTurmas.getViewport().setOpaque(false);

        pnTurmas.setLayout(new FlowLayout(FlowLayout.LEFT));
        pnTurmas.setMinimumSize(new Dimension(900, 9000));
        pnTurmas.setPreferredSize(new Dimension(900, 9000));
        pnTurmas.setMaximumSize(new Dimension(1300, 9000));
        pnTurmas.setOpaque(false);

        pnGerar.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 250));
        pnGerar.setMinimumSize(new Dimension(900, 768));
        pnGerar.setPreferredSize(new Dimension(900, 768));
        pnGerar.setMaximumSize(new Dimension(1300, 1080));
        pnGerar.setOpaque(false);

        Tile todas = new Tile();
        todas.setNome("Todas as turmas");
        todas.setId("");
        todas.setClickEvent(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                gerarTodasTurmas();
            }
        });

        Tile turmas = new Tile();
        turmas.setNome("Escolher turma");
        turmas.setId("");
        turmas.setClickEvent(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                show("TURMAS");
            }
        });

        pnGerar.add(turmas);
        pnGerar.add(todas);

        lbLoading.setPreferredSize(new Dimension(250, 120));
        lbLoading.setFont(new Font("Segoe UI", Font.PLAIN, 36));
        lbLoading.setForeground(new Color(61, 61, 61));

        pnLoading.setLayout(new FlowLayout(FlowLayout.CENTER, 75, 75));
        pnLoading.setOpaque(false);
        pnLoading.add(lbLoading);

        add(pnLoading, "LOADING");
        add(scrollTurmas, "TURMAS");
        add(pnGerar, "GERAR");

        try {
            CronosAPI.subscribe(Turma.class, this);
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        show("GERAR");
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

                    HorariosUI.getInstance().exibir(actualTurma.getId());

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
