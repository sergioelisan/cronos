package edu.cronos.util.date;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.*;

/**
 * @author sergio lisan
 */
public class DateUtil {

    // numero de milissegundos em um dia
    public static final int DAY_FACTOR = (24 * 60 * 60 * 1000);
    // Nomes dos dias da semana
    public static final String DOM = "Domingo";
    public static final String SEG = "Segunda";
    public static final String TER = "Terça";
    public static final String QUA = "Quarta";
    public static final String QUI = "Quinta";
    public static final String SEX = "Sexta";
    public static final String SAB = "Sábado";

    /**
     * Recebe um dia e retorna o nome dele
     *
     * @param dia
     * @return
     */
    public static String getNomeDia(Date dia) {
        String[] dias = {DOM, SEG, TER, QUA, QUI, SEX, SAB};

        Calendar c = Calendar.getInstance();
        c.setTime(dia);

        return dias[c.get(Calendar.DAY_OF_WEEK) - 1];
    }

    /**
     * recebe uma data e retorna um dia do mes referente a ela
     *
     * @param dia
     * @return
     */
    public static int getDia(Date dia) {
        return getDateField(dia, Calendar.DAY_OF_MONTH);
    }

    /**
     * recebe uma data e retorna o mes referente a ela
     *
     * @param dia
     * @return
     */
    public static int getMes(Date dia) {
        return getDateField(dia, Calendar.MONTH) + 1;
    }

    /**
     * recebe uma data e retorna o ano referente a ela
     *
     * @param dia
     * @return
     */
    public static int getAno(Date dia) {
        return getDateField(dia, Calendar.YEAR);
    }

    /**
     * Retorna o dia da semana de um determinado Date
     */
    public static int getDiaSemana(Date dia) {
        return getDateField(dia, Calendar.DAY_OF_WEEK);
    }

    /**
     * recebe uma data e um campo e extrai um dado referente a isso
     *
     * @param dia
     * @param field
     * @return
     */
    private static int getDateField(Date dia, int field) {
        Calendar c = Calendar.getInstance();
        c.setTime(dia);

        return c.get(field);
    }

    /**
     * Retorna o nome do mes
     *
     * @param dia
     * @return
     */
    public static String getNomeMes(Date dia) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        String[] meses = fmt.getDateFormatSymbols().getMonths();

        return meses[getMes(dia)];
    }

    /**
     * Retorna o nome do mes
     *
     * @param dia
     * @return
     */
    public static String getNomeMes(Integer mes) {
        SimpleDateFormat fmt = new SimpleDateFormat();
        String[] meses = fmt.getDateFormatSymbols().getMonths();

        return meses[mes - 1];
    }

    /**
     * Converte uma string para um Date
     *
     * @param data
     * @return
     */
    public static Date string2Date(String data) {
        try {
            return DateFormat.getDateInstance().parse(data);
        } catch (Exception e) {
            System.err.println("Problema ao converter datas");
            System.err.println(e.getMessage());
        }
        return null;
    }

    /**
     * Retorna o numero de semestres a partir de uma Data inicial
     *
     * @param init data inicial
     * @return a quantidade de semestres entre hoje e uma data inicial
     */
    public static int getSemestres(Date init) {
        int dias = DateUtil.diferencaDias(init, new Date());
        return dias / 182 < 0 ? 1 : dias / 182;
    }

    /**
     * Retorna a diferenca entre dois dias distintos
     *
     * @param init
     * @param end
     * @return
     */
    public static int diferencaDias(Date init, Date end) {
        return (int) ((end.getTime() - init.getTime()) / DAY_FACTOR);
    }

    /**
     * Retorna um vetor de objetos Data, de um periodo inicial a um periodo
     * final
     *
     * @param dataInicial
     * @param dataFinal
     * @return
     */
    public static List<Date> getDiasUteis(Date inicio, Date fim) {
        List<Date> diasUteis = new ArrayList<>();

        // Pega a atual instancia do calendario da JVM
        Calendar init = Calendar.getInstance();
        init.setTime(inicio);

        Calendar end = Calendar.getInstance();
        end.setTime(fim);

        // Enquanto o calendario inicial for anterior ao calendario final
        while (init.before(end)) {

            // Se nao for Domingo ou Sabado, dai insere na lista de dias uteis
            int diasemana = init.get(Calendar.DAY_OF_WEEK);
            if ((diasemana != Calendar.SUNDAY && diasemana != Calendar.SATURDAY)) {
                diasUteis.add(new Date(init.getTime().getTime()));
            }

            // incrementa em 1 dia o calendario inicial, ate que ele chegue o final
            init.add(Calendar.DAY_OF_MONTH, 1);
        }

        return diasUteis;
    }

    /**
     * Retorna uma Lista de Data's que representam os dias uteis de um periodo
     *
     * @param inicio do periodo
     * @param fim    do periodo
     * @return lista com Data uteis
     */
    public static List<Date> getDiasUteis(Date inicio, Date fim, List<Date> feriados) {
        List<Date> diasUteis = DateUtil.getDiasUteis(inicio, fim);

        for (Date feriado : feriados) {
            diasUteis.remove(feriado);
        }

        return diasUteis;
    }

    public static Map<Integer, List<Date>> getDiasUteisSeparadosPorMes(Date inicio, Date fim, List<Date> feriados) {
        Map<Integer, List<Date>> meses = new TreeMap<>();
        List<Date> diasUteis = getDiasUteis(inicio, fim, feriados);

        for (Integer mes = DateUtil.getMes(diasUteis.get(0));
             mes <= DateUtil.getMes(diasUteis.get(diasUteis.size() - 1)); mes++) {
            meses.put(mes, new ArrayList<Date>());
        }

        for (Date dia : diasUteis) {
            meses.get(DateUtil.getMes(dia)).add(dia);
        }

        return meses;
    }
}
