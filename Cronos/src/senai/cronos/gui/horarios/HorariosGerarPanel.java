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
import senai.cronos.Fachada;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.ColorManager;
import senai.cronos.gui.events.LinkEffectHandler;
import senai.cronos.logica.horarios.GeraHorarioFactory;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorariosGerarPanel extends javax.swing.JPanel implements HorariosUIClient {

    private List<HorarioUI> calendarios = new ArrayList<>();
    
    private Horario horario;
    
    private JPanel pnTurmas      = new JPanel();
    private JPanel pnCalendarios = new JPanel();
    private JPanel pnHorarios    = new JPanel();
    private JPanel pnLegendas    = new JPanel();
    private JPanel pnLoading     = new JPanel();
    private JLabel lbLoading     = new JLabel();
    private JLabel setaDireita   = new JLabel(">");
    private JLabel setaEsquerda  = new JLabel("<");
    private JLabel lbVoltar      = new JLabel("voltar");
    private JLabel lbSave        = new JLabel("salvar");
    private JLabel lbPrint       = new JLabel("imprimir");
    
    private Timer animacao;
    private final int DELAY      = 500;

    private static HorariosGerarPanel instance = new HorariosGerarPanel();
    
    /**
     * retorna uma instancia unica desde painel
     * @return 
     */
    public static HorariosGerarPanel getInstance() {
        return instance;
    }
    
    private HorariosGerarPanel() {
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
        Thread t = new Thread(new HorariosUI.LoadTurmas(pnTurmas, this));
        t.start();
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
     * Salva o horario no banco de dados
     */
    private void saveHorario() {
        try {
            Fachada.add(horario);
            
            JOptionPane.showMessageDialog(null, "Salvo com sucesso!");
        } catch (ClassNotFoundException | SQLException e) {
            JOptionPane.showMessageDialog(null, "FAIL! Erro ao Salvar Horario:\n" + e);
            show("TURMAS");
            e.printStackTrace(System.err);
        }
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
    @Override
    public void action(final Integer id) {
        Thread thread = new Thread(new Runnable() {

            @Override
            public void run() {
                try {
                    pnLegendas.removeAll();
                    pnHorarios.removeAll();
                    calendarios.clear();
                    
                    Turma turma = Fachada.<Turma>get(Turma.class, id);
                    
                    horario = Fachada.<Horario>get(Horario.class, id);
                    if (!horario.getHorario().isEmpty())  {
                        int opcao = JOptionPane.showConfirmDialog(null, "Deseja sobrescrever o horário dessa turma?", "Aviso", JOptionPane.YES_NO_OPTION);
                        
                        // se a opcao for nao, retorna a funcao.
                        if (opcao == JOptionPane.NO_OPTION) {
                            stopLoading();
                            show("TURMAS");
                            return;
                        }
                    }
                                            
                    horario = GeraHorarioFactory.getGerador().generate(turma);

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
                    
                } catch (Exception e) {
                    JOptionPane.showMessageDialog(null, "FAIL! Erro ao Gerar Horario:\n" + e);                    
                    e.printStackTrace(System.err);
                    show("TURMAS");
                }
            }
        });

        thread.start();
        startLoading();
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
}
