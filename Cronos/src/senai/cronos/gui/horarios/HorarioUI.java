package senai.cronos.gui.horarios;

import java.util.ArrayList;
import java.util.List;
import javax.swing.JPanel;
import senai.cronos.entidades.Turma;
import senai.cronos.gui.ColorManager;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorarioUI extends javax.swing.JPanel {

    private String mes;
    private Turma turma;
    private List<HorarioTile> tiles;

    public HorarioUI(String mes, Turma turma, List<HorarioTile> tiles) {
        initComponents();
        this.mes = mes;
        this.turma = turma;
        this.tiles = tiles;
        init();
    }

    public String getMes() {
        return mes;
    }

    /**
     * carrega os dados das tiles de dias uteis
     */
    private void init() {
        lbmes.setText(mes);
        lbturma.setText(turma.getNome());

        int inicial = tiles.get(0).getDiaSemana() - 3;

        for (HorarioTile ct : tiles) {
            getSlots().get(++inicial).add(ct);
        }
    }

    /**
     * retorna um slot ideintificado por sua id
     */
    private List<JPanel> getSlots() {
        List<JPanel> panels = new ArrayList<>();

        panels.add(slot2);
        panels.add(slot3);
        panels.add(slot4);
        panels.add(slot5);
        panels.add(slot6);

        panels.add(slot9);
        panels.add(slot10);
        panels.add(slot11);
        panels.add(slot12);
        panels.add(slot13);

        panels.add(slot16);
        panels.add(slot17);
        panels.add(slot18);
        panels.add(slot19);
        panels.add(slot20);

        panels.add(slot23);
        panels.add(slot24);
        panels.add(slot25);
        panels.add(slot26);
        panels.add(slot27);

        panels.add(slot30);
        panels.add(slot31);
        panels.add(slot32);
        panels.add(slot33);
        panels.add(slot34);

        panels.add(slot37);
        panels.add(slot38);
        panels.add(slot39);
        panels.add(slot40);

        return panels;
    }

    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        jPanel1 = new javax.swing.JPanel();
        slot36 = new javax.swing.JPanel();
        slot9 = new javax.swing.JPanel();
        slot30 = new javax.swing.JPanel();
        slot8 = new javax.swing.JPanel();
        slot16 = new javax.swing.JPanel();
        slot22 = new javax.swing.JPanel();
        slot2 = new javax.swing.JPanel();
        slot15 = new javax.swing.JPanel();
        slot29 = new javax.swing.JPanel();
        slot23 = new javax.swing.JPanel();
        slot10 = new javax.swing.JPanel();
        slot24 = new javax.swing.JPanel();
        lbturma = new javax.swing.JLabel();
        slot37 = new javax.swing.JPanel();
        slot18 = new javax.swing.JPanel();
        slot38 = new javax.swing.JPanel();
        slot31 = new javax.swing.JPanel();
        slot11 = new javax.swing.JPanel();
        slot4 = new javax.swing.JPanel();
        slot3 = new javax.swing.JPanel();
        slot17 = new javax.swing.JPanel();
        slot41 = new javax.swing.JPanel();
        slot42 = new javax.swing.JPanel();
        slot25 = new javax.swing.JPanel();
        jLabel2 = new javax.swing.JLabel();
        slot12 = new javax.swing.JPanel();
        jLabel5 = new javax.swing.JLabel();
        jLabel6 = new javax.swing.JLabel();
        slot39 = new javax.swing.JPanel();
        jLabel3 = new javax.swing.JLabel();
        jLabel4 = new javax.swing.JLabel();
        slot40 = new javax.swing.JPanel();
        slot6 = new javax.swing.JPanel();
        slot26 = new javax.swing.JPanel();
        slot33 = new javax.swing.JPanel();
        slot32 = new javax.swing.JPanel();
        slot19 = new javax.swing.JPanel();
        slot5 = new javax.swing.JPanel();
        jLabel10 = new javax.swing.JLabel();
        slot13 = new javax.swing.JPanel();
        jLabel11 = new javax.swing.JLabel();
        jLabel12 = new javax.swing.JLabel();
        slot27 = new javax.swing.JPanel();
        jLabel13 = new javax.swing.JLabel();
        jLabel14 = new javax.swing.JLabel();
        slot14 = new javax.swing.JPanel();
        jLabel15 = new javax.swing.JLabel();
        slot1 = new javax.swing.JPanel();
        slot28 = new javax.swing.JPanel();
        lbmes = new javax.swing.JLabel();
        slot21 = new javax.swing.JPanel();
        slot35 = new javax.swing.JPanel();
        slot20 = new javax.swing.JPanel();
        slot34 = new javax.swing.JPanel();
        slot7 = new javax.swing.JPanel();
        jLabel7 = new javax.swing.JLabel();
        jLabel9 = new javax.swing.JLabel();
        jLabel8 = new javax.swing.JLabel();

        setBackground(new java.awt.Color(255, 255, 255));
        setMaximumSize(new java.awt.Dimension(950, 350));
        setMinimumSize(new java.awt.Dimension(950, 350));
        setPreferredSize(new java.awt.Dimension(950, 350));

        jPanel1.setBackground(new java.awt.Color(255, 255, 255));

        slot36.setBackground(new java.awt.Color(255, 255, 255));
        slot36.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot36.setMaximumSize(new java.awt.Dimension(60, 80));
        slot36.setMinimumSize(new java.awt.Dimension(60, 80));
        slot36.setName("slot36");
        slot36.setPreferredSize(new java.awt.Dimension(60, 80));
        slot36.setLayout(new java.awt.BorderLayout());

        slot9.setBackground(new java.awt.Color(255, 255, 255));
        slot9.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot9.setMaximumSize(new java.awt.Dimension(60, 80));
        slot9.setMinimumSize(new java.awt.Dimension(60, 80));
        slot9.setName("slot9");
        slot9.setPreferredSize(new java.awt.Dimension(60, 80));
        slot9.setLayout(new java.awt.BorderLayout());

        slot30.setBackground(new java.awt.Color(255, 255, 255));
        slot30.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot30.setMaximumSize(new java.awt.Dimension(60, 80));
        slot30.setMinimumSize(new java.awt.Dimension(60, 80));
        slot30.setName("slot30");
        slot30.setPreferredSize(new java.awt.Dimension(60, 80));
        slot30.setLayout(new java.awt.BorderLayout());

        slot8.setBackground(new java.awt.Color(255, 255, 255));
        slot8.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot8.setMaximumSize(new java.awt.Dimension(60, 80));
        slot8.setMinimumSize(new java.awt.Dimension(60, 80));
        slot8.setName("slot8");
        slot8.setPreferredSize(new java.awt.Dimension(60, 80));
        slot8.setLayout(new java.awt.BorderLayout());

        slot16.setBackground(new java.awt.Color(255, 255, 255));
        slot16.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot16.setMaximumSize(new java.awt.Dimension(60, 80));
        slot16.setMinimumSize(new java.awt.Dimension(60, 80));
        slot16.setName("slot16");
        slot16.setPreferredSize(new java.awt.Dimension(60, 80));
        slot16.setLayout(new java.awt.BorderLayout());

        slot22.setBackground(new java.awt.Color(255, 255, 255));
        slot22.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot22.setMaximumSize(new java.awt.Dimension(60, 80));
        slot22.setMinimumSize(new java.awt.Dimension(60, 80));
        slot22.setName("slot22");
        slot22.setPreferredSize(new java.awt.Dimension(60, 80));
        slot22.setLayout(new java.awt.BorderLayout());

        slot2.setBackground(new java.awt.Color(255, 255, 255));
        slot2.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot2.setMaximumSize(new java.awt.Dimension(60, 80));
        slot2.setMinimumSize(new java.awt.Dimension(60, 80));
        slot2.setName("slot2");
        slot2.setPreferredSize(new java.awt.Dimension(60, 80));
        slot2.setLayout(new java.awt.BorderLayout());

        slot15.setBackground(new java.awt.Color(255, 255, 255));
        slot15.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot15.setMaximumSize(new java.awt.Dimension(60, 80));
        slot15.setMinimumSize(new java.awt.Dimension(60, 80));
        slot15.setName("slot15");
        slot15.setPreferredSize(new java.awt.Dimension(60, 80));
        slot15.setLayout(new java.awt.BorderLayout());

        slot29.setBackground(new java.awt.Color(255, 255, 255));
        slot29.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot29.setMaximumSize(new java.awt.Dimension(60, 80));
        slot29.setMinimumSize(new java.awt.Dimension(60, 80));
        slot29.setName("slot29");
        slot29.setPreferredSize(new java.awt.Dimension(60, 80));
        slot29.setLayout(new java.awt.BorderLayout());

        slot23.setBackground(new java.awt.Color(255, 255, 255));
        slot23.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot23.setMaximumSize(new java.awt.Dimension(60, 80));
        slot23.setMinimumSize(new java.awt.Dimension(60, 80));
        slot23.setName("slot23");
        slot23.setPreferredSize(new java.awt.Dimension(60, 80));
        slot23.setLayout(new java.awt.BorderLayout());

        slot10.setBackground(new java.awt.Color(255, 255, 255));
        slot10.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot10.setMaximumSize(new java.awt.Dimension(60, 80));
        slot10.setMinimumSize(new java.awt.Dimension(60, 80));
        slot10.setName("slot10");
        slot10.setPreferredSize(new java.awt.Dimension(60, 80));
        slot10.setLayout(new java.awt.BorderLayout());

        slot24.setBackground(new java.awt.Color(255, 255, 255));
        slot24.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot24.setMaximumSize(new java.awt.Dimension(60, 80));
        slot24.setMinimumSize(new java.awt.Dimension(60, 80));
        slot24.setName("slot24");
        slot24.setPreferredSize(new java.awt.Dimension(60, 80));
        slot24.setLayout(new java.awt.BorderLayout());

        lbturma.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbturma.setHorizontalAlignment(javax.swing.SwingConstants.RIGHT);
        lbturma.setText("turma");
        lbturma.setMaximumSize(new java.awt.Dimension(60, 16));
        lbturma.setMinimumSize(new java.awt.Dimension(60, 16));
        lbturma.setPreferredSize(new java.awt.Dimension(60, 16));

        slot37.setBackground(new java.awt.Color(255, 255, 255));
        slot37.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot37.setMaximumSize(new java.awt.Dimension(60, 80));
        slot37.setMinimumSize(new java.awt.Dimension(60, 80));
        slot37.setName("slot37");
        slot37.setPreferredSize(new java.awt.Dimension(60, 80));
        slot37.setLayout(new java.awt.BorderLayout());

        slot18.setBackground(new java.awt.Color(255, 255, 255));
        slot18.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot18.setMaximumSize(new java.awt.Dimension(60, 80));
        slot18.setMinimumSize(new java.awt.Dimension(60, 80));
        slot18.setName("slot18");
        slot18.setPreferredSize(new java.awt.Dimension(60, 80));
        slot18.setLayout(new java.awt.BorderLayout());

        slot38.setBackground(new java.awt.Color(255, 255, 255));
        slot38.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot38.setMaximumSize(new java.awt.Dimension(60, 80));
        slot38.setMinimumSize(new java.awt.Dimension(60, 80));
        slot38.setName("slot38");
        slot38.setPreferredSize(new java.awt.Dimension(60, 80));
        slot38.setLayout(new java.awt.BorderLayout());

        slot31.setBackground(new java.awt.Color(255, 255, 255));
        slot31.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot31.setMaximumSize(new java.awt.Dimension(60, 80));
        slot31.setMinimumSize(new java.awt.Dimension(60, 80));
        slot31.setName("slot31");
        slot31.setPreferredSize(new java.awt.Dimension(60, 80));
        slot31.setLayout(new java.awt.BorderLayout());

        slot11.setBackground(new java.awt.Color(255, 255, 255));
        slot11.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot11.setMaximumSize(new java.awt.Dimension(60, 80));
        slot11.setMinimumSize(new java.awt.Dimension(60, 80));
        slot11.setName("slot11");
        slot11.setPreferredSize(new java.awt.Dimension(60, 80));
        slot11.setLayout(new java.awt.BorderLayout());

        slot4.setBackground(new java.awt.Color(255, 255, 255));
        slot4.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot4.setMaximumSize(new java.awt.Dimension(60, 80));
        slot4.setMinimumSize(new java.awt.Dimension(60, 80));
        slot4.setName("slot4");
        slot4.setPreferredSize(new java.awt.Dimension(60, 80));
        slot4.setLayout(new java.awt.BorderLayout());

        slot3.setBackground(new java.awt.Color(255, 255, 255));
        slot3.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot3.setMaximumSize(new java.awt.Dimension(60, 80));
        slot3.setMinimumSize(new java.awt.Dimension(60, 80));
        slot3.setName("slot3");
        slot3.setPreferredSize(new java.awt.Dimension(60, 80));
        slot3.setLayout(new java.awt.BorderLayout());

        slot17.setBackground(new java.awt.Color(255, 255, 255));
        slot17.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot17.setMaximumSize(new java.awt.Dimension(60, 80));
        slot17.setMinimumSize(new java.awt.Dimension(60, 80));
        slot17.setName("slot17");
        slot17.setPreferredSize(new java.awt.Dimension(60, 80));
        slot17.setLayout(new java.awt.BorderLayout());

        slot41.setBackground(new java.awt.Color(255, 255, 255));
        slot41.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot41.setMaximumSize(new java.awt.Dimension(60, 80));
        slot41.setMinimumSize(new java.awt.Dimension(60, 80));
        slot41.setName("slot28");
        slot41.setPreferredSize(new java.awt.Dimension(60, 80));
        slot41.setLayout(new java.awt.BorderLayout());

        slot42.setBackground(new java.awt.Color(255, 255, 255));
        slot42.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot42.setMaximumSize(new java.awt.Dimension(60, 80));
        slot42.setMinimumSize(new java.awt.Dimension(60, 80));
        slot42.setName("slot27");
        slot42.setPreferredSize(new java.awt.Dimension(60, 80));
        slot42.setLayout(new java.awt.BorderLayout());

        slot25.setBackground(new java.awt.Color(255, 255, 255));
        slot25.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot25.setMaximumSize(new java.awt.Dimension(60, 80));
        slot25.setMinimumSize(new java.awt.Dimension(60, 80));
        slot25.setName("slot25");
        slot25.setPreferredSize(new java.awt.Dimension(60, 80));
        slot25.setLayout(new java.awt.BorderLayout());

        jLabel2.setBackground(ColorManager.getColor("button"));
        jLabel2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel2.setForeground(new java.awt.Color(255, 255, 255));
        jLabel2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel2.setText("dom");
        jLabel2.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel2.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel2.setOpaque(true);
        jLabel2.setPreferredSize(new java.awt.Dimension(60, 16));

        slot12.setBackground(new java.awt.Color(255, 255, 255));
        slot12.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot12.setMaximumSize(new java.awt.Dimension(60, 80));
        slot12.setMinimumSize(new java.awt.Dimension(60, 80));
        slot12.setName("slot12");
        slot12.setPreferredSize(new java.awt.Dimension(60, 80));
        slot12.setLayout(new java.awt.BorderLayout());

        jLabel5.setBackground(ColorManager.getColor("tile"));
        jLabel5.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel5.setForeground(new java.awt.Color(255, 255, 255));
        jLabel5.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel5.setText("qua");
        jLabel5.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel5.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel5.setOpaque(true);
        jLabel5.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel6.setBackground(ColorManager.getColor("tile"));
        jLabel6.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel6.setForeground(new java.awt.Color(255, 255, 255));
        jLabel6.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel6.setText("qui");
        jLabel6.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel6.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel6.setOpaque(true);
        jLabel6.setPreferredSize(new java.awt.Dimension(60, 16));

        slot39.setBackground(new java.awt.Color(255, 255, 255));
        slot39.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot39.setMaximumSize(new java.awt.Dimension(60, 80));
        slot39.setMinimumSize(new java.awt.Dimension(60, 80));
        slot39.setName("slot39");
        slot39.setPreferredSize(new java.awt.Dimension(60, 80));
        slot39.setLayout(new java.awt.BorderLayout());

        jLabel3.setBackground(ColorManager.getColor("tile"));
        jLabel3.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel3.setForeground(new java.awt.Color(255, 255, 255));
        jLabel3.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel3.setText("seg");
        jLabel3.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel3.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel3.setOpaque(true);
        jLabel3.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel4.setBackground(ColorManager.getColor("tile"));
        jLabel4.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel4.setForeground(new java.awt.Color(255, 255, 255));
        jLabel4.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel4.setText("ter");
        jLabel4.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel4.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel4.setOpaque(true);
        jLabel4.setPreferredSize(new java.awt.Dimension(60, 16));

        slot40.setBackground(new java.awt.Color(255, 255, 255));
        slot40.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot40.setMaximumSize(new java.awt.Dimension(60, 80));
        slot40.setMinimumSize(new java.awt.Dimension(60, 80));
        slot40.setName("slot40");
        slot40.setPreferredSize(new java.awt.Dimension(60, 80));
        slot40.setLayout(new java.awt.BorderLayout());

        slot6.setBackground(new java.awt.Color(255, 255, 255));
        slot6.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot6.setMaximumSize(new java.awt.Dimension(60, 80));
        slot6.setMinimumSize(new java.awt.Dimension(60, 80));
        slot6.setName("slot6");
        slot6.setPreferredSize(new java.awt.Dimension(60, 80));
        slot6.setLayout(new java.awt.BorderLayout());

        slot26.setBackground(new java.awt.Color(255, 255, 255));
        slot26.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot26.setMaximumSize(new java.awt.Dimension(60, 80));
        slot26.setMinimumSize(new java.awt.Dimension(60, 80));
        slot26.setName("slot26");
        slot26.setPreferredSize(new java.awt.Dimension(60, 80));
        slot26.setLayout(new java.awt.BorderLayout());

        slot33.setBackground(new java.awt.Color(255, 255, 255));
        slot33.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot33.setMaximumSize(new java.awt.Dimension(60, 80));
        slot33.setMinimumSize(new java.awt.Dimension(60, 80));
        slot33.setName("slot33");
        slot33.setPreferredSize(new java.awt.Dimension(60, 80));
        slot33.setLayout(new java.awt.BorderLayout());

        slot32.setBackground(new java.awt.Color(255, 255, 255));
        slot32.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot32.setMaximumSize(new java.awt.Dimension(60, 80));
        slot32.setMinimumSize(new java.awt.Dimension(60, 80));
        slot32.setName("slot32");
        slot32.setPreferredSize(new java.awt.Dimension(60, 80));
        slot32.setLayout(new java.awt.BorderLayout());

        slot19.setBackground(new java.awt.Color(255, 255, 255));
        slot19.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot19.setMaximumSize(new java.awt.Dimension(60, 80));
        slot19.setMinimumSize(new java.awt.Dimension(60, 80));
        slot19.setName("slot19");
        slot19.setPreferredSize(new java.awt.Dimension(60, 80));
        slot19.setLayout(new java.awt.BorderLayout());

        slot5.setBackground(new java.awt.Color(255, 255, 255));
        slot5.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot5.setMaximumSize(new java.awt.Dimension(60, 80));
        slot5.setMinimumSize(new java.awt.Dimension(60, 80));
        slot5.setName("slot5");
        slot5.setPreferredSize(new java.awt.Dimension(60, 80));
        slot5.setLayout(new java.awt.BorderLayout());

        jLabel10.setBackground(ColorManager.getColor("tile"));
        jLabel10.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel10.setForeground(new java.awt.Color(255, 255, 255));
        jLabel10.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel10.setText("qui");
        jLabel10.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel10.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel10.setOpaque(true);
        jLabel10.setPreferredSize(new java.awt.Dimension(60, 16));

        slot13.setBackground(new java.awt.Color(255, 255, 255));
        slot13.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot13.setMaximumSize(new java.awt.Dimension(60, 80));
        slot13.setMinimumSize(new java.awt.Dimension(60, 80));
        slot13.setName("slot13");
        slot13.setPreferredSize(new java.awt.Dimension(60, 80));
        slot13.setLayout(new java.awt.BorderLayout());

        jLabel11.setBackground(ColorManager.getColor("tile"));
        jLabel11.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel11.setForeground(new java.awt.Color(255, 255, 255));
        jLabel11.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel11.setText("sex");
        jLabel11.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel11.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel11.setOpaque(true);
        jLabel11.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel12.setBackground(ColorManager.getColor("tile"));
        jLabel12.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel12.setForeground(new java.awt.Color(255, 255, 255));
        jLabel12.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel12.setText("seg");
        jLabel12.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel12.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel12.setOpaque(true);
        jLabel12.setPreferredSize(new java.awt.Dimension(60, 16));

        slot27.setBackground(new java.awt.Color(255, 255, 255));
        slot27.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot27.setMaximumSize(new java.awt.Dimension(60, 80));
        slot27.setMinimumSize(new java.awt.Dimension(60, 80));
        slot27.setName("slot27");
        slot27.setPreferredSize(new java.awt.Dimension(60, 80));
        slot27.setLayout(new java.awt.BorderLayout());

        jLabel13.setBackground(ColorManager.getColor("tile"));
        jLabel13.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel13.setForeground(new java.awt.Color(255, 255, 255));
        jLabel13.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel13.setText("ter");
        jLabel13.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel13.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel13.setOpaque(true);
        jLabel13.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel14.setBackground(ColorManager.getColor("button"));
        jLabel14.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel14.setForeground(new java.awt.Color(255, 255, 255));
        jLabel14.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel14.setText("dom");
        jLabel14.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel14.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel14.setOpaque(true);
        jLabel14.setPreferredSize(new java.awt.Dimension(60, 16));

        slot14.setBackground(new java.awt.Color(255, 255, 255));
        slot14.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot14.setMaximumSize(new java.awt.Dimension(60, 80));
        slot14.setMinimumSize(new java.awt.Dimension(60, 80));
        slot14.setName("slot14");
        slot14.setPreferredSize(new java.awt.Dimension(60, 80));
        slot14.setLayout(new java.awt.BorderLayout());

        jLabel15.setBackground(ColorManager.getColor("tile"));
        jLabel15.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel15.setForeground(new java.awt.Color(255, 255, 255));
        jLabel15.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel15.setText("qua");
        jLabel15.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel15.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel15.setOpaque(true);
        jLabel15.setPreferredSize(new java.awt.Dimension(60, 16));

        slot1.setBackground(new java.awt.Color(255, 255, 255));
        slot1.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot1.setMaximumSize(new java.awt.Dimension(60, 80));
        slot1.setMinimumSize(new java.awt.Dimension(60, 80));
        slot1.setName("slot1");
        slot1.setPreferredSize(new java.awt.Dimension(60, 80));
        slot1.setLayout(new java.awt.BorderLayout());

        slot28.setBackground(new java.awt.Color(255, 255, 255));
        slot28.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot28.setMaximumSize(new java.awt.Dimension(60, 80));
        slot28.setMinimumSize(new java.awt.Dimension(60, 80));
        slot28.setName("slot28");
        slot28.setPreferredSize(new java.awt.Dimension(60, 80));
        slot28.setLayout(new java.awt.BorderLayout());

        lbmes.setFont(new java.awt.Font("Segoe UI", 0, 14)); // NOI18N
        lbmes.setText("mês");
        lbmes.setMaximumSize(new java.awt.Dimension(60, 16));
        lbmes.setMinimumSize(new java.awt.Dimension(60, 16));
        lbmes.setPreferredSize(new java.awt.Dimension(60, 16));

        slot21.setBackground(new java.awt.Color(255, 255, 255));
        slot21.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot21.setMaximumSize(new java.awt.Dimension(60, 80));
        slot21.setMinimumSize(new java.awt.Dimension(60, 80));
        slot21.setName("slot21");
        slot21.setPreferredSize(new java.awt.Dimension(60, 80));
        slot21.setLayout(new java.awt.BorderLayout());

        slot35.setBackground(new java.awt.Color(255, 255, 255));
        slot35.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot35.setMaximumSize(new java.awt.Dimension(60, 80));
        slot35.setMinimumSize(new java.awt.Dimension(60, 80));
        slot35.setName("slot35");
        slot35.setPreferredSize(new java.awt.Dimension(60, 80));
        slot35.setLayout(new java.awt.BorderLayout());

        slot20.setBackground(new java.awt.Color(255, 255, 255));
        slot20.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot20.setMaximumSize(new java.awt.Dimension(60, 80));
        slot20.setMinimumSize(new java.awt.Dimension(60, 80));
        slot20.setName("slot20");
        slot20.setPreferredSize(new java.awt.Dimension(60, 80));
        slot20.setLayout(new java.awt.BorderLayout());

        slot34.setBackground(new java.awt.Color(255, 255, 255));
        slot34.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot34.setMaximumSize(new java.awt.Dimension(60, 80));
        slot34.setMinimumSize(new java.awt.Dimension(60, 80));
        slot34.setName("slot34");
        slot34.setPreferredSize(new java.awt.Dimension(60, 80));
        slot34.setLayout(new java.awt.BorderLayout());

        slot7.setBackground(new java.awt.Color(255, 255, 255));
        slot7.setBorder(javax.swing.BorderFactory.createLineBorder(new java.awt.Color(215, 215, 215)));
        slot7.setMaximumSize(new java.awt.Dimension(60, 80));
        slot7.setMinimumSize(new java.awt.Dimension(60, 80));
        slot7.setName("slot7");
        slot7.setPreferredSize(new java.awt.Dimension(60, 80));
        slot7.setLayout(new java.awt.BorderLayout());

        jLabel7.setBackground(ColorManager.getColor("tile"));
        jLabel7.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel7.setForeground(new java.awt.Color(255, 255, 255));
        jLabel7.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel7.setText("sex");
        jLabel7.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel7.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel7.setOpaque(true);
        jLabel7.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel9.setBackground(ColorManager.getColor("button"));
        jLabel9.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel9.setForeground(new java.awt.Color(255, 255, 255));
        jLabel9.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel9.setText("sab");
        jLabel9.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel9.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel9.setOpaque(true);
        jLabel9.setPreferredSize(new java.awt.Dimension(60, 16));

        jLabel8.setBackground(ColorManager.getColor("button"));
        jLabel8.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        jLabel8.setForeground(new java.awt.Color(255, 255, 255));
        jLabel8.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        jLabel8.setText("sab");
        jLabel8.setMaximumSize(new java.awt.Dimension(60, 16));
        jLabel8.setMinimumSize(new java.awt.Dimension(60, 16));
        jLabel8.setOpaque(true);
        jLabel8.setPreferredSize(new java.awt.Dimension(60, 16));

        javax.swing.GroupLayout jPanel1Layout = new javax.swing.GroupLayout(jPanel1);
        jPanel1.setLayout(jPanel1Layout);
        jPanel1Layout.setHorizontalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbmes, javax.swing.GroupLayout.PREFERRED_SIZE, 258, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, 60, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addComponent(lbturma, javax.swing.GroupLayout.PREFERRED_SIZE, 192, javax.swing.GroupLayout.PREFERRED_SIZE)))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(slot27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(slot13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                                    .addComponent(slot28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                    .addComponent(slot14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(slot42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED)
                                .addComponent(slot41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );
        jPanel1Layout.setVerticalGroup(
            jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
            .addGroup(jPanel1Layout.createSequentialGroup()
                .addContainerGap()
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(lbmes, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(lbturma, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.BASELINE)
                    .addComponent(jLabel2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                    .addComponent(jLabel9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING, false)
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot1, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot15, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot29, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot2, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot16, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot30, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot3, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot17, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot31, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot4, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot18, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot32, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot5, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot19, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot33, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot6, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot20, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot34, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot7, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot21, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot35, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot8, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot22, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot36, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot9, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot23, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot37, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot10, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot24, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot38, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot11, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot25, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot39, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addComponent(slot12, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot26, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                        .addComponent(slot40, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                    .addGroup(jPanel1Layout.createSequentialGroup()
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(slot13, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(slot27, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))
                            .addGroup(jPanel1Layout.createSequentialGroup()
                                .addComponent(slot14, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                                .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.UNRELATED)
                                .addComponent(slot28, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)))
                        .addPreferredGap(javax.swing.LayoutStyle.ComponentPlacement.RELATED, javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE)
                        .addGroup(jPanel1Layout.createParallelGroup(javax.swing.GroupLayout.Alignment.LEADING)
                            .addComponent(slot42, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE)
                            .addComponent(slot41, javax.swing.GroupLayout.PREFERRED_SIZE, javax.swing.GroupLayout.DEFAULT_SIZE, javax.swing.GroupLayout.PREFERRED_SIZE))))
                .addContainerGap(javax.swing.GroupLayout.DEFAULT_SIZE, Short.MAX_VALUE))
        );

        add(jPanel1);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel jLabel10;
    private javax.swing.JLabel jLabel11;
    private javax.swing.JLabel jLabel12;
    private javax.swing.JLabel jLabel13;
    private javax.swing.JLabel jLabel14;
    private javax.swing.JLabel jLabel15;
    private javax.swing.JLabel jLabel2;
    private javax.swing.JLabel jLabel3;
    private javax.swing.JLabel jLabel4;
    private javax.swing.JLabel jLabel5;
    private javax.swing.JLabel jLabel6;
    private javax.swing.JLabel jLabel7;
    private javax.swing.JLabel jLabel8;
    private javax.swing.JLabel jLabel9;
    private javax.swing.JPanel jPanel1;
    private javax.swing.JLabel lbmes;
    private javax.swing.JLabel lbturma;
    private javax.swing.JPanel slot1;
    private javax.swing.JPanel slot10;
    private javax.swing.JPanel slot11;
    private javax.swing.JPanel slot12;
    private javax.swing.JPanel slot13;
    private javax.swing.JPanel slot14;
    private javax.swing.JPanel slot15;
    private javax.swing.JPanel slot16;
    private javax.swing.JPanel slot17;
    private javax.swing.JPanel slot18;
    private javax.swing.JPanel slot19;
    private javax.swing.JPanel slot2;
    private javax.swing.JPanel slot20;
    private javax.swing.JPanel slot21;
    private javax.swing.JPanel slot22;
    private javax.swing.JPanel slot23;
    private javax.swing.JPanel slot24;
    private javax.swing.JPanel slot25;
    private javax.swing.JPanel slot26;
    private javax.swing.JPanel slot27;
    private javax.swing.JPanel slot28;
    private javax.swing.JPanel slot29;
    private javax.swing.JPanel slot3;
    private javax.swing.JPanel slot30;
    private javax.swing.JPanel slot31;
    private javax.swing.JPanel slot32;
    private javax.swing.JPanel slot33;
    private javax.swing.JPanel slot34;
    private javax.swing.JPanel slot35;
    private javax.swing.JPanel slot36;
    private javax.swing.JPanel slot37;
    private javax.swing.JPanel slot38;
    private javax.swing.JPanel slot39;
    private javax.swing.JPanel slot4;
    private javax.swing.JPanel slot40;
    private javax.swing.JPanel slot41;
    private javax.swing.JPanel slot42;
    private javax.swing.JPanel slot5;
    private javax.swing.JPanel slot6;
    private javax.swing.JPanel slot7;
    private javax.swing.JPanel slot8;
    private javax.swing.JPanel slot9;
    // End of variables declaration//GEN-END:variables
}
