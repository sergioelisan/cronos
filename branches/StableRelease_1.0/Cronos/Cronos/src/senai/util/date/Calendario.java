package senai.util.date;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;

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

        this.feriados = diasDeFeriado;
        this.inicio = inicio;
        this.fim = fim;
        diasUteis = DateUtil.getDiasUteis(inicio, fim, diasDeFeriado);
    }

    public void setDiasUteis(List<Date> diasUteis) {
        this.diasUteis = diasUteis;
    }

    public List<Date> getDiasUteis() {
        return diasUteis;
    }
    
    public Map<Integer, List<Date> > getDiasUteisSeparadosEmMeses() {
        return DateUtil.getDiasUteisSeparadosPorMes(inicio, fim, feriados);
    }

    private List<Date> diasUteis;
    
    private Date inicio;
    private Date fim;
    private List<Date> feriados;
}
