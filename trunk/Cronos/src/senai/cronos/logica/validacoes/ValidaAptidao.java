package senai.cronos.logica.validacoes;

import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Turma;

/**
 * Avalia se o docente está apto a lecionar uma disciplina
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class ValidaAptidao implements Validacao<Docente> {

    private Turma turma;

    public ValidaAptidao(Turma turma) {
        this.turma = turma;
    }

    @Override
    public boolean isValid(Docente u) {
        // se receber uma referencia nula
        if (u == null) {
            return false;
        }

        // se o percentual de ocupacao estiver estourado
        if (u.getHorarioDocente().getPercentualOcupacao() > .66) {
            return false;
        }
                
        // se a turma estiver em um turno diferente do turno que o professor leciona
        if (!u.getPrimeiroTurno().equals(turma.getTurno()) && !u.getSegundoTurno().equals(turma.getTurno())) {
            return false;
        }
        
        return true;
    }
}
