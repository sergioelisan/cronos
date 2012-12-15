package senai.cronos;

import java.io.*;
import java.net.MalformedURLException;
import java.net.URL;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;
import javax.swing.SwingUtilities;
import org.apache.derby.impl.drda.NetworkServerControlImpl;
import senai.cronos.database.cache.Docentes;
import senai.cronos.database.cache.Feriados;
import senai.cronos.database.cache.Laboratorios;
import senai.cronos.database.cache.Nucleos;
import senai.cronos.database.cache.Turmas;
import senai.cronos.database.cache.UnidadesCurriculares;
import senai.cronos.database.dao.DAO;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.gui.Alerta;
import senai.cronos.gui.CronosFrame;
import senai.cronos.util.CronosFiles;
import senai.util.concurrency.Paralell;
import senai.util.date.Calendario;
import senai.util.date.Feriado;
import static senai.util.debug.Debug.println;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Main {

    /**
     * Versao atual do codigo
     */
    public static final String VERSAO = Versoes.BETA2;
    /**
     * objeto que armazena o CALENDARIO de dias uteis usados pela escola
     */
    public static Calendario CALENDARIO;
    /**
     * janelinha que aparece indicando o carregamento do sistema
     */
    private Splash splash = Splash.getInstance();

    /**
     * Gênesis do sistema... aqui é onde tudo começa.
     */
    public static void main(String[] args) throws InterruptedException {
        new Main().init();
    }

    /**
     * Metodo que carrega o sistema, começando pela base de dados, as
     * preferencias e por fim a UI.
     */
    public void init() {
        try {
            splash.start();
            // updateSystem();
            loadDatabase();
            loadCalendar();
            loadCache();
            loadUI();
            splash.stop();
        } catch (Exception ex) {
            ex.printStackTrace();
            Alerta.jogarAviso(ex.getMessage());
        }
    }

    /**
     * Desliga o banco e a aplicacao
     */
    public static void quit() {
        try {
            println("Encerrando banco de dados e desligando JVM");
            new NetworkServerControlImpl().shutdown();
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.toString());
        } finally {
            System.exit(0);
        }
    }

    /**
     * carrega a base de dados
     */
    private void loadDatabase() throws Exception {
        splash.upBar();

        System.setProperty("derby.system.home", CronosFiles.getCronosDatabase());
        new NetworkServerControlImpl().start(new PrintWriter(System.out));
    }

    /**
     * carrega os dados do banco e os coloca em cache na memoria
     *
     * @throws Exception
     */
    private void loadCache() throws Exception {
        splash.upBar();

        Feriados.start();
        Laboratorios.start();
        Nucleos.start();
        UnidadesCurriculares.start();
        Docentes.start();
        Turmas.start();        
    }

    /**
     * Carrega as configuracoes do sistema
     */
    private void loadCalendar() throws ClassNotFoundException, SQLException, ParseException {
        splash.upBar();

        Date inicio = CronosAPI.getInicioCalendario();
        Date fim = CronosAPI.getFimCalendario();

        DAO<Feriado> dao = DAOFactory.getDao(Feriado.class);
        Main.CALENDARIO = new Calendario(inicio, fim, dao.get());
    }

    /**
     * Carrega e inicia a Interface Gráfica
     */
    private void loadUI() {
        splash.upBar();

        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                CronosFrame frame = new CronosFrame();
                frame.setVisible(true);
            }
        });
    }

    /**
     * Atualiza o sistema
     *
     * @throws IOException
     * @throws NumberFormatException
     */
    private void updateSystem() throws IOException {
        splash.upBar();
        URL url = null;

        String recentVersion = "";
        String stringUrl = "http://code.google.com/p/senai-pe-cronos/downloads/list";
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        InputStreamReader isr = new InputStreamReader(url.openStream());
        BufferedReader br = new BufferedReader(isr);

        String linha;
        while ((linha = br.readLine()) != null) {
            if (linha.contains("/files/updateCronos-0-")) {
                String[] s = linha.split("-");
                String[] v = s[4].split(".exe");
                recentVersion = v[0];
                break;
            }
        }

        stringUrl = "http://senai-pe-cronos.googlecode.com/files/updateCronos-0-" + recentVersion + ".exe";
        UpdateCronos update = new UpdateCronos();
        try {
            url = new URL(stringUrl);
        } catch (MalformedURLException ex) {
            Alerta.jogarAviso(ex.getMessage());
        }

        if (Double.parseDouble(recentVersion) > Double.parseDouble(Main.VERSAO)) {
            File f = update.gravaArquivoDeURL(url, System.getProperty("user.dir"), Main.VERSAO, recentVersion);
            if (update.isS()) {
                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\update.exe");
                System.exit(0);
            }
        }
    }
}
