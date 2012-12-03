package senai.cronos.horario;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;

public class GeraHorarioContinuoQuebrado extends GeraHorario {

    @Override
    public void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException {
        int modo = Tupla.PRIMEIRA;
        for (UnidadeCurricular uc : getDisciplinas()) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_METADE);

            for (int i = 0; i < total; i++)
                OUTER:
                for (Date dia : horario.keySet())
                    if (modo == Tupla.PRIMEIRA)
                        if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                            horario.get(dia).setPrimeiro(aula);

                            break OUTER;
                        }
                    else
                        if (horario.get(dia).getSegundo().equals(Aula.PADRAO)) {
                            horario.get(dia).setSegundo(aula);

                            break OUTER;
                        }

            modo = (modo == Tupla.PRIMEIRA) ? Tupla.SEGUNDA : Tupla.PRIMEIRA;
        }

    }

}
