package senai.cronos.horario;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;

public class GeraHorarioContinuoQuebrado extends GeraHorario {

    @Override
    public void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException {
        int modo = Tupla.PRIMEIRA;
        for (UnidadeCurricular uc : getDisciplinas()) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_METADE);

            for (int i = 0; i < total; i++) {
                OUTER:
                for (Date dia : horario.keySet()) {
                    if (modo == Tupla.PRIMEIRA) {
                        if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                            horario.get(dia).setPrimeiro(aula);

                            break OUTER;
                        } else if (horario.get(dia).getSegundo().equals(Aula.PADRAO)) {
                            horario.get(dia).setSegundo(aula);

                            break OUTER;
                        }
                    }
                }
            }

            modo = (modo == Tupla.PRIMEIRA) ? Tupla.SEGUNDA : Tupla.PRIMEIRA;
        }

    }
    
    private Docente lastDocente = Docente.PADRAO;

    @Override
    public void alocarDocentes(Map<Date, Tupla<Aula, Aula>> horario) throws Exception {
        Horario wrapper = new Horario(horario);

        for (Aula aula : wrapper.getAulas() ) {
            Map<Date, Tupla<Boolean, Boolean>> dias = wrapper.getDiasLecionados(aula);

            for (Docente docente : CronosAPI.bestDocentes(aula.getDisciplina() ) ) {
                boolean disponivel = true;

                for (Date dia : dias.keySet()) {
                    if (!docente.getHorarioDocente().isDisponivel(dia, getTurma().getTurno() ) ) {
                        disponivel = false;
                        break;
                    }
                }

                if ((disponivel && !lastDocente.equals(docente)) 
                        && getTurma().getTurno().isInside(docente.getTurno() )) {
                    aula.setDocente(docente);
                    lastDocente = docente;
                    break;
                }
            }

        }

    }
}
