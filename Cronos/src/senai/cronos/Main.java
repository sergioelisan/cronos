package senai.cronos;

import java.io.IOException;
import java.io.InputStream;
import java.io.PrintWriter;
import java.io.StringWriter;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import senai.cronos.gui.CronosFrame;
import senai.cronos.util.Calendario;
import senai.cronos.util.Feriado;
import org.apache.derby.impl.drda.NetworkServerControlImpl;
import senai.cronos.util.Contador;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Main {
    
public static final String readRegistry(String location, String key)
{
  try
  {
      // Run reg query, then read output with StreamReader (internal class)
      Process process = Runtime.getRuntime().exec("reg query " + 
              '"'+ location + "\" /v " + key);

      InputStream is = process.getInputStream();
      StringBuilder sw = new StringBuilder();

      try
      {
         int c;
         while ((c = is.read()) != -1)
             sw.append((char)c);
      }
      catch (IOException e)
      { 
      }

      String output = sw.toString();

      // Output has the following format:
      // \n<Version information>\n\n<key>    <registry type>    <value>\r\n\r\n
      int i = output.indexOf("REG_SZ");
      if (i == -1)
      {
          return null;
      }

      sw = new StringBuilder();
      i += 6; // skip REG_SZ

      // skip spaces or tabs
      for (;;)
      {
         if (i > output.length())
             break;
         char c = output.charAt(i);
         if (c != ' ' && c != '\t')
             break;
         ++i;
      }

      // take everything until end of line
      for (;;)
      {
         if (i > output.length())
             break;
         char c = output.charAt(i);
         if (c == '\r' || c == '\n')
             break;
         sw.append(c);
         ++i;
      }

      return sw.toString();
  }
  catch (Exception e)
  {
      return null;
  }
}
    public static void main(String[] args) {
        Main m = new Main();

        try {
            //String location = System.getProperty("user.home")+"\\Documents\\banco";
            
           
             String location = m.readRegistry("HKCU\\Software\\Microsoft\\Windows\\CurrentVersion\\" 
                 + "Explorer\\Shell Folders", "Personal")+"\\banco";
        System.out.println(location);
            System.setProperty("derby.system.home", location);
            NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
            networkServer.start(new PrintWriter(System.out));
            System.out.println("Conectado ao banco de dados.");
            
            m.init();
        } catch (Exception ex) {
            System.out.println("Problemas no sistema:\n\n" + ex);
            ex.printStackTrace(System.err);
        }
    }

    /**
     * desliga o banco e a aplicacao
     */
    public static void quit() {
        try {
            System.out.println("Encerrando banco de dados e desligando JVM");
            NetworkServerControlImpl networkServer = new NetworkServerControlImpl();
            networkServer.shutdown();
            
            System.out.println("Docentes: " + Contador.docentes);
            System.out.println("Discipli: " + Contador.disciplinas);
            System.out.println("Laborato: " + Contador.laboratorios);
            System.out.println("Horarios: " + Contador.horario);
            System.out.println("Turmas  : " + Contador.turmas);
            System.out.println("Nucleos : " + Contador.nucleos);
            
            System.exit(0);
            
        } catch (Exception ex) {
            ex.printStackTrace();
        }
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
