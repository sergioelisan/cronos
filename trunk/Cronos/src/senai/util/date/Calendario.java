package senai.util.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

/**
 * Classe que instancias objetos que encapsulam os dias uteis de um periodo
 *
 * @author sergio lisan
 */
public class Calendario {

    /**
     *
     * @param inicio data do inicio do calendario de dias uteis
     * @param fim data do fim do semestre letivo Construtor idealizado para
     * criar um calendário de dias e disponibiliza-lo através dos getters and
     * setters
     */
    public Calendario(Date inicio, Date fim, List<Feriado> feriados) {
        List<Date> diasDeFeriado = new ArrayList<>();
        for (Feriado f : feriados) {
            diasDeFeriado.add(f.getDia());
        }

        diasUteis = DateUtil.getDiasUteis(inicio, fim, diasDeFeriado);
    }

    public void setDiasUteis(List<Date> diasUteis) {
        this.diasUteis = diasUteis;
    }

    public List<Date> getDiasUteis() {
        return diasUteis;
    }

    private List<Date> diasUteis;
}
