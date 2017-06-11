package edu.cronos;

import edu.cronos.database.cache.*;
import edu.cronos.database.dao.DAO;
import edu.cronos.database.dao.DAOFactory;
import edu.cronos.gui.Alerta;
import edu.cronos.gui.CronosFrame;
import edu.cronos.util.Observador;
import edu.cronos.util.date.Calendario;
import edu.cronos.util.date.Feriado;

import javax.swing.*;
import java.io.IOException;
import java.sql.SQLException;
import java.text.ParseException;
import java.util.Date;

import static edu.cronos.util.debug.Debug.println;

/**
 * @author sergio lisan e carlos melo
 */
public class Main implements Observador {

    /**
     * Versao atual do codigo
     */
    public static final String VERSAO = "1.0.1.104";
    /**
     * objeto que armazena o CALENDARIO de dias uteis usados pela escola
     */
    public static Calendario CALENDARIO;
    /**
     * janelinha que aparece indicando o carregamento do sistema
     */
    private Splash splash = Splash.getInstance();

    public Main() {
    }

    /**
     * Gênesis do sistema... aqui é onde tudo começa.
     */
    public static void main(String[] args) throws InterruptedException {
        new Main().init();
    }

    /**
     * Desliga o banco e a aplicacao
     */
    public static void quit() {
        try {
            println("Encerrando banco de dados e desligando JVM");
//            new NetworkServerControlImpl().shutdown();
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.toString());
        } finally {
            System.exit(0);
        }
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
            splash.upBar();
            loadCalendar();
            loadCache();
            loadUI();
            splash.stop();

            CronosAPI.subscribe(Feriado.class, this);

        } catch (Exception ex) {
            ex.printStackTrace();
            Alerta.jogarAviso(ex.getMessage());
        }
    }

    /**
     * carrega a base de dados
     */
    private void loadDatabase() throws Exception {
        splash.upBar();

//        System.setProperty("derby.system.home", CronosFiles.getCronosDatabase());
//        new NetworkServerControlImpl().start(new PrintWriter(System.out));
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

        SwingUtilities.invokeLater(() -> {
            CronosFrame frame = new CronosFrame();
            frame.setVisible(true);
        });
    }

    /**
     * Atualiza o sistema
     *
     * @Deprecated
     */
    private void updateSystem() throws IOException {
//        splash.upBar();
//        URL url = null;
//
//        String recentVersion = "";
//        String stringUrl = "http://code.google.com/p/EDU-pe-cronos/downloads/list";
//        try {
//            url = new URL(stringUrl);
//        } catch (MalformedURLException ex) {
//            Alerta.jogarAviso(ex.getMessage());
//        }
//
//        InputStreamReader isr = new InputStreamReader(url.openStream());
//        BufferedReader br = new BufferedReader(isr);
//
//        String linha;
//        while ((linha = br.readLine()) != null) {
//            if (linha.contains("/files/updateCronos-0-")) {
//                String[] s = linha.split("-");
//                String[] v = s[4].split(".exe");
//                recentVersion = v[0];
//                break;
//            }
//        }
//
//        stringUrl = "http://EDU-pe-cronos.googlecode.com/files/updateCronos-0-" + recentVersion + ".exe";
//        UpdateCronos update = new UpdateCronos();
//        try {
//            url = new URL(stringUrl);
//        } catch (MalformedURLException ex) {
//            Alerta.jogarAviso(ex.getMessage());
//        }
//
//        if (Double.parseDouble(recentVersion) > Double.parseDouble(Main.VERSAO)) {
//            File f = update.gravaArquivoDeURL(url, System.getProperty("user.dir"), Main.VERSAO, recentVersion);
//            if (update.isS()) {
//                Runtime.getRuntime().exec(System.getProperty("user.dir") + "\\update.exe");
//                System.exit(0);
//            }
//        }
    }

    @Override
    public void update() {
        try {
            loadCalendar();
        } catch (Exception ex) {
            Alerta.jogarAviso(ex.getMessage());
        }
    }
}
