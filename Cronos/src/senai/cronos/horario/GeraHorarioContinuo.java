package senai.cronos.horario;

import java.sql.SQLException;
import java.util.Date;
import java.util.Map;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;

public class GeraHorarioContinuo extends GeraHorario {

    @Override
    public void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException {
        for (UnidadeCurricular uc : getDisciplinas()) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_INTEIRO);

            for (int i = 0; i < total; i++)

                for (Date dia : horario.keySet())
                    if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                        horario.get(dia).setPrimeiro(aula);
                        horario.get(dia).setSegundo(aula);

                        break;
                    }

        }

    }

}
