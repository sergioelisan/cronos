package senai.cronos.gui.horarios;

import java.awt.Color;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorarioTile extends javax.swing.JPanel {

    public HorarioTile() {
        initComponents();
    }
    
    public void setPrimeiroHorario(Color cor) {
        lbhora1.setBackground(cor);
    }
    
    public Color getPrimeiroHorario() {
        return lbhora1.getBackground();
    }
    
    public void setSegundoHorario(Color cor) {
        lbhora2.setBackground(cor);
    }

    public Color getSegundoHorario() {
        return lbhora2.getBackground();
    }
    
    public void setDia(String dia) {
        lbdia.setText(dia);
    }
    
    public String getDia() {
        return lbdia.getText();
    }
    
    public int getDiaSemana() {
        return diaSemana;
    }
    
    public void setDiaSemana(int diaSemana) {
        this.diaSemana = diaSemana;
    }
    
    @SuppressWarnings("unchecked")
    // <editor-fold defaultstate="collapsed" desc="Generated Code">//GEN-BEGIN:initComponents
    private void initComponents() {

        lbhora1 = new javax.swing.JLabel();
        lbhora2 = new javax.swing.JLabel();
        lbdia = new javax.swing.JLabel();

        setMaximumSize(new java.awt.Dimension(60, 80));
        setMinimumSize(new java.awt.Dimension(60, 80));
        setOpaque(false);
        setPreferredSize(new java.awt.Dimension(60, 80));
        setLayout(new java.awt.FlowLayout(java.awt.FlowLayout.CENTER, 0, 0));

        lbhora1.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbhora1.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhora1.setMaximumSize(new java.awt.Dimension(60, 30));
        lbhora1.setMinimumSize(new java.awt.Dimension(60, 30));
        lbhora1.setOpaque(true);
        lbhora1.setPreferredSize(new java.awt.Dimension(60, 30));
        add(lbhora1);

        lbhora2.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbhora2.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbhora2.setMaximumSize(new java.awt.Dimension(60, 30));
        lbhora2.setMinimumSize(new java.awt.Dimension(60, 30));
        lbhora2.setOpaque(true);
        lbhora2.setPreferredSize(new java.awt.Dimension(60, 30));
        add(lbhora2);

        lbdia.setBackground(new java.awt.Color(255, 255, 255));
        lbdia.setFont(new java.awt.Font("Segoe UI", 0, 12)); // NOI18N
        lbdia.setHorizontalAlignment(javax.swing.SwingConstants.CENTER);
        lbdia.setText("dia");
        lbdia.setMaximumSize(new java.awt.Dimension(60, 20));
        lbdia.setMinimumSize(new java.awt.Dimension(60, 20));
        lbdia.setOpaque(true);
        lbdia.setPreferredSize(new java.awt.Dimension(60, 20));
        add(lbdia);
    }// </editor-fold>//GEN-END:initComponents
    // Variables declaration - do not modify//GEN-BEGIN:variables
    private javax.swing.JLabel lbdia;
    private javax.swing.JLabel lbhora1;
    private javax.swing.JLabel lbhora2;
    // End of variables declaration//GEN-END:variables

    private int diaSemana;
}
