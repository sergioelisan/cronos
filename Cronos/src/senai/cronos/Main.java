package senai.cronos;

import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import senai.cronos.gui.CronosFrame;
import senai.cronos.util.Calendario;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Main {

    public static void main(String[] args) {
        Main m = new Main();

        try {
            m.init();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(null, "FAIL!\n" + e);
        }
    }

    /**
     * Construtor
     */
    public Main() {
    }

    /**
     * Metodo que carrega o sistema
     */
    public void init() {
        try {
            loadPreferences();
        } catch (ClassNotFoundException | SQLException | ParseException ex) {
            JOptionPane.showMessageDialog(null, "FAIL! Problemas ao iniciar o sistema:\n\n" + ex.getMessage());
        }

        loadUI();
    }

    /**
     * Carrega as configuracoes do sistema
     */
    private void loadPreferences() throws ClassNotFoundException, SQLException, ParseException {
        Date inicio = Fachada.getInicioCalendario();
        Date fim = Fachada.getFimCalendario();

        List<Date> feriados = Fachada.<Date>get(Date.class);
        calendario = new Calendario(inicio, fim, feriados);
    }

    /**
     * Carrega e inicia a Interface Gráfica
     */
    private void loadUI() {
        SwingUtilities.invokeLater(new Runnable() {

            @Override
            public void run() {
                CronosFrame frame = new CronosFrame();
                frame.setVisible(true);
            }
        });
    }
    
    /**
     * objeto que armazena o calendario de dias uteis usados pela escola
     */
    public static Calendario calendario;
    /**
     * endereco do arquivo de configuracoes
     */
    public static final String config = "senai/cronos/properties/calendario";
}
