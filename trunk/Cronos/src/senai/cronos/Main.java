package senai.cronos;

import javax.swing.Timer;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import javax.swing.JDialog;
import java.io.File;
import java.io.PrintWriter;
import java.sql.SQLException;
import java.text.ParseException;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;

import senai.cronos.gui.CronosFrame;
import senai.cronos.gui.Update;
import senai.cronos.util.calendario.Calendario;
import senai.cronos.util.calendario.Feriado;

import org.apache.derby.impl.drda.NetworkServerControlImpl;

import senai.cronos.util.debug.Debug;
import senai.cronos.util.os.OSFactory;
import senai.cronos.util.os.OperatingSystem;
import senai.cronos.util.os.UpdateCronos;
import static senai.cronos.util.debug.Debug.*;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Main {

 
 
    /**
     * Gênesis do sistema... aqui é onde tudo começa.
     */
    public static void main(String[] args) throws InterruptedException {
        final Main m = new Main();
    
        m.init(); 
        
         
    }

 

    /**
     * Metodo que carrega o sistema, começando pela base de dados, as
     * preferencias e por fim a UI.
     */
    public void init() {
        try {
            loadDatabase();
            loadPreferences();
            loadUI();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(null, "FAIL! Problemas ao iniciar o sistema:\n\n" + ex.getMessage());
        }
    }
    
    /**
     * Desliga o banco e a aplicacao
     */
    public static void quit() {
        try {
            println("Encerrando banco de dados e desligando JVM");
            NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
            networkServer.shutdown();
            // metodo usado para debugar o numero de conexoes
            Debug.countConnections();
            System.exit(0);

        } catch (Exception ex) {
            ex.printStackTrace(System.err);
        }
    }

    /**
     * carrega a base de dados
     */
    private void loadDatabase() throws Exception {
        OperatingSystem os = OSFactory.getOperatingSystem();
        UpdateCronos update=new UpdateCronos();
        
       
        String path = "";
        String key  = "";
        String dir  = "";
        switch (os.getName()) {
            case OperatingSystem.WINDOWS:
                path = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders";
                key  = "Personal";
                dir  = "\\banco";
                break;
                
            // caso algum outro sistema operacional seja implementado
            // é só adicionar um case com o path devido
                
            default:
                throw new IllegalArgumentException("Sistema Operacional ainda não suportado!");
        }
        
        String location = os.readRegistry(path, key) + dir;
         println("Local do Banco: " + location );
          
        println(System.getProperty("user.dir"));
          File f=update.gravaArquivoDeURL("http://senai-pe-cronos.googlecode.com/files/updateCronos-0-11.exe",System.getProperty("user.dir"));  
       
        
          System.setProperty("derby.system.home", location);
        NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
        networkServer.start(new PrintWriter(System.out));
        
        println("Sistema Operacional: " + os.getName() );
        
        println("Conectado ao banco de dados.");
    }

    /**
     * Carrega as configuracoes do sistema
     */
    private void loadPreferences() throws ClassNotFoundException, SQLException, ParseException {
        Date inicio = Fachada.getInicioCalendario();
        Date fim = Fachada.getFimCalendario();

        List<Feriado> feriados = Fachada.<Feriado>get(Feriado.class);

        List<Date> diasDeFeriado = new ArrayList<>();
        for (Feriado f : feriados) {
            diasDeFeriado.add(f.getDia());
        }

        calendario = new Calendario(inicio, fim, diasDeFeriado);
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
}
