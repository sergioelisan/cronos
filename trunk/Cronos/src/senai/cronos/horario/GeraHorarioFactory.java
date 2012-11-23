package senai.cronos.horario;

import senai.cronos.Fachada;
import senai.cronos.logica.horarios.GeraHorario;
import senai.cronos.logica.horarios.GeraHorarioAlternado;
import senai.cronos.logica.horarios.GeraHorarioAlternadoQuebrado;
import senai.cronos.logica.horarios.GeraHorarioContinuo;
import senai.cronos.logica.horarios.GeraHorarioContinuoQuebrado;

/**
 * Fabrica o gerador de horario, de acordo com as opcoes do sistema.
 *
 * @author sergio lisan e carlos melo
 */
public class GeraHorarioFactory {

    public static GeraHorario getGerador() {
        int alternancia = Fachada.getAlternancia();
        int aulasPorDia = Fachada.getAulasDia();

        if (alternancia == ALTERNADO && aulasPorDia == 0) {
            return new GeraHorarioAlternado();

        } else if (alternancia == CONTINUO && aulasPorDia == 0) {
            return new GeraHorarioContinuo();

        } else if (alternancia == ALTERNADO && aulasPorDia == 1) {
            return new GeraHorarioAlternadoQuebrado();

        } else if (alternancia == CONTINUO && aulasPorDia == 1) {
            return new GeraHorarioContinuoQuebrado();
        } else {
            throw new IllegalArgumentException("FAIL! Opcoes corrompidas! -> 'horario' ");
        }
    }
    public static final int CONTINUO = 0;
    public static final int ALTERNADO = 1;
}
