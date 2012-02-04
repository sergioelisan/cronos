package senai.cronos;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JDialog;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import org.apache.derby.impl.drda.NetworkServerControlImpl;
import senai.cronos.gui.Update;
import senai.cronos.gui.Alertas;
import senai.cronos.gui.CronosFrame;
import senai.cronos.util.UpdateCronos;
import senai.cronos.util.calendario.Calendario;
import senai.cronos.util.calendario.Feriado;
import senai.cronos.util.debug.Debug;
import static senai.cronos.util.debug.Debug.println;
import senai.cronos.util.os.OSFactory;
import senai.cronos.util.os.OperatingSystem;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Main {
private String location;
    public static int version = 40;
    String versao=null;
        URL url = null;
        Alertas alerta=new Alertas();

    public static int getVersion() {
        return version;
    }

    public static void setVersion(int version) {
        Main.version = version;
    }


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
            Splash s = Splash.getInstance();
            s.start();

            s.upBar();
            updateSystem();

            s.upBar();
            loadDatabase();

            s.upBar();
            loadPreferences();

            s.upBar();
            loadUI();

            s.stop();
        } catch (Exception ex) {
           alerta.jogarAviso(ex.toString());
            
            //JOptionPane.showMessageDialog(null, "FAIL! Problemas na inicializacao:\n\n" + ex.getMessage());
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

        String path = "";
        String key = "";
        String dir = "";
        switch (os.getName()) {
            // windows 7, windows vista (com aero ligado) e windows xp ok!
            case OperatingSystem.WINDOWS:
                path = "HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\Explorer\\Shell Folders";
                key = "Personal";
                dir = "\\banco";
                break;

            // testado no Ubuntu 11.10 32 bits
            case OperatingSystem.LINUX:
                path = "/home";
                key = "user.dir";
                dir = ".cronos-database";
                break;

            // caso algum outro sistema operacional seja implementado
            // é só adicionar um case com o path devido

            default:
                throw new IllegalArgumentException("Sistema Operacional ainda não suportado!");
        }

         location= os.readRegistry(path, key) + dir;
        
        //location="wwer";
        println(location);
        System.setProperty("derby.system.home", location);
        NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
        networkServer.start(new PrintWriter(System.out));
        //Checando a versão do repositorio
      
    }
      

    /**
     * Atualiza o sistema
     *
     * @throws IOException
     * @throws NumberFormatException
     */
    private void updateSystem() throws IOException {
          String stringUrl="http://code.google.com/p/senai-pe-cronos/downloads/list";
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }
        InputStream is = url.openStream();   
InputStreamReader isr = new InputStreamReader(is);   
BufferedReader br = new BufferedReader(isr);   
  
String linha = br.readLine();  
  
while (linha != null) { 
    linha = br.readLine();  
   
   if(linha.contains("/files/updateCronos-0-")){
   String[] s=linha.split("-");
   String[] v=s[4].split(".exe");
   versao=v[0];
   println("----"+versao);
  
  break;
}   

}
      
stringUrl="http://senai-pe-cronos.googlecode.com/files/updateCronos-0-"+versao+".exe";
UpdateCronos update=new UpdateCronos();
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException ex) {
            ex.printStackTrace();
        }       
System.out.println("versão:"+versao);
         if(Integer.parseInt(versao)>version){
              
             File f = update.gravaArquivoDeURL(url,System.getProperty("user.dir"),String.valueOf(version),versao);
            
             
            
             if(update.isS()) {
                 Runtime.getRuntime().exec(location+"\\update.exe");
                 System.exit(0);
         }
         }

          
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
