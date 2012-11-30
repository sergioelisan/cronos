package senai.cronos.horario;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;

public class GeraHorarioContinuoQuebrado extends GeraHorario {

    @Override
    public void alocarAulas(Horario horarioWrapper) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> horario = horarioWrapper.getHorario();
        List<UnidadeCurricular> disciplinas = getDisciplinas();

        int modo = Tupla.PRIMEIRA;
        for (UnidadeCurricular uc : disciplinas) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_METADE);

            for (int i = 0; i < total; i++) {
                OUTER:
                for (Date dia : horario.keySet()) {
                    if (modo == Tupla.PRIMEIRA) {
                        if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                            horario.get(dia).setPrimeiro(aula);

                            break OUTER;
                        }
                    } else {
                        if (horario.get(dia).getSegundo().equals(Aula.PADRAO)) {
                            horario.get(dia).setSegundo(aula);

                            break OUTER;
                        }
                    }

                }
            }

            modo = (modo == Tupla.PRIMEIRA) ? Tupla.SEGUNDA : Tupla.PRIMEIRA;
        }

    }
}
