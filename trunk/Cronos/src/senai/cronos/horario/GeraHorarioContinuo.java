package senai.cronos.horario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;

public class GeraHorarioContinuo extends GeraHorario {

    @Override
    public void alocarAulas(Horario horarioWrapper) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> horario = horarioWrapper.getHorario();
        List<UnidadeCurricular> disciplinas = getDisciplinas();

        for (UnidadeCurricular uc : disciplinas) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_INTEIRO);

            for (int i = 0; i < total; i++) {

                for (Date dia : horario.keySet()) {
                    if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                        horario.get(dia).setPrimeiro(aula);
                        horario.get(dia).setSegundo(aula);

                        /* esse break eh usado pra evitar percorrer todo o loop
                         e gastar processamento a toa; */
                        break;

                    }

                }

            }

        }

    }

}
