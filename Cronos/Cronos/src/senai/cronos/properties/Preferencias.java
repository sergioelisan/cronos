package senai.cronos.properties;

import java.io.*;
import java.text.DateFormat;
import java.text.ParseException;
import java.util.Date;
import java.util.Properties;
import javax.swing.JOptionPane;

/**
 * Classe que centraliza e gerencia as preferencias do sistema.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Preferencias {

    /**
     * Construtor privado que carrega as preferencias do sistema logo que inicializa o objeto
     */
    private Preferencias() {
        load();
    }

    /**
     * retorna a opcao de quantas aulas por dia foram selecionadas
     * @return
     */
    public int getAulasDia() {
        return Integer.parseInt(horariosProperties.getProperty("horario.aulaspordia") );
    }

    /**
     * insere uma quantidade de aulas por dia
     * @param aulasDia
     */
    public void setAulasDia(int aulasDia) {
        horariosProperties.setProperty("horario.aulaspordia", String.valueOf(aulasDia));
        save();
    }

    /**
     * retorna se o horario deve ser alternado ou continuo
     * @return
     */
    public int getAlternancia() {
        return Integer.parseInt(horariosProperties.getProperty("horario.alternancia") );
    }

    /**
     * seta a opcao do horario ser continuo ou alternado
     * @param horarioAlternado
     */
    public void setAlternancia(int horarioAlternado) {
        horariosProperties.setProperty("horario.alternancia", String.valueOf(horarioAlternado));
        save();
    }

    /**
     * retorna o fim do calendario
     * @return
     * @throws ParseException
     */
    public Date getFimCalendario() throws ParseException {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).parse(calendarioProperties.getProperty("calendario.fim"));
    }

    /**
     * seta o fim do calendario
     * @param fimCalendario
     */
    public void setFimCalendario(Date fimCalendario) {
        calendarioProperties.setProperty("calendario.fim", DateFormat.getDateInstance(DateFormat.MEDIUM).format(fimCalendario));
        save();
    }

    /**
     * retorna o dia do inicio do calendario
     * @return
     * @throws ParseException
     */
    public Date getInicioCalendario() throws ParseException {
        return DateFormat.getDateInstance(DateFormat.MEDIUM).parse(calendarioProperties.getProperty("calendario.inicio"));
    }

    /**
     * seta o inicio do calendario
     * @param inicioCalendario
     */
    public void setInicioCalendario(Date inicioCalendario) {
        calendarioProperties.setProperty("calendario.inicio", DateFormat.getDateInstance(DateFormat.MEDIUM).format(inicioCalendario));
        save();
    }

    /**
     * retorna uma instancia unica dessa classe
     * @return
     */
    public static Preferencias instance() {
        return instance;
    }

    /**
     * Retorna as propriedades de conexao
     * @return
     */
    public Properties getConexao() {
        return connectionProperties;
    }

    /**
     * metodo que carrega as preferencias
     */
    private void load() {
        try {
            horariosProperties = new Properties();
            horariosProperties.load(getInput(horarioURL));

            calendarioProperties = new Properties();
            calendarioProperties.load(getInput(calendarioURL));

            connectionProperties = new Properties();
            connectionProperties.load(getInput(connectionURL));

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao ler o arquivo de configuracoes:\n" + ex);
        }
    }

    /**
     * metodo que salva as preferencias
     */
    private void save() {
        try {
            horariosProperties.store(getOutput(horarioURL), "horarios");
            calendarioProperties.store(getOutput(calendarioURL), "calendario");

        } catch (IOException ex) {
            JOptionPane.showMessageDialog(null, "Problemas ao ler o arquivo de configuracoes:\n" + ex);
        }
    }

    /**
     * retorna um inputstream para a comunicacao com o arquivo de configuracao
     *
     * @return
     * @throws FileNotFoundException
     */
    private InputStream getInput(String url) throws FileNotFoundException {
        return new FileInputStream(new File(url));
    }

    /**
     * retorna um stream de escrita
     *
     * @return
     * @throws FileNotFoundException
     */
    private OutputStream getOutput(String url) throws FileNotFoundException {
        return new FileOutputStream(new File(url));
    }

    /**
     * instancia unica desta classe
     */
    private static Preferencias instance = new Preferencias();

    /**
     * url e nome do arquivo que armazena as preferencias do calendario
     */
    private String calendarioURL = "./conf/calendario.properties";

    /**
     * url e nome do arquivo que armazena as preferencias de horario
     */
    private String horarioURL = "./conf/horario.properties";

    /**
     * url e nome do arquivo que armazena as preferencias de conexao
     */
    private String connectionURL = "./conf/connection.properties";

    /**
     * objeto que armazena as propriedades de conexao
     */
    private Properties connectionProperties;

    /**
     * objeto que armazena as propriedades de horarios
     */
    private Properties horariosProperties;

    /**
     * objeto que armazena as propriedades de calendarios
     */
    private Properties calendarioProperties;
}
