package senai.cronos.logica.horarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.UnidadeCurricular;
import senai.util.Tupla;
import senai.util.date.DateUtil;
import senai.util.debug.Debug;

public class GeraHorarioAlternado extends GeraHorario {

    @Override
    public void generate(Horario horario) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> calendario = horario.getHorario();

        List<UnidadeCurricular> disciplinas = getDisciplinas();
        int diasletivos = calendario.keySet().size();

        int modo = 0;
        for (UnidadeCurricular uc : disciplinas) {
            int total = getQuantidadeDeDias(uc, 4);

            if (total <= diasletivos) {
                List<Date> diasdisciplina = new ArrayList<>();

                for (int i = 0; i < total; i++) {
                    OUTER:
                    for (Date dia : calendario.keySet()) {
                        String diaSemana = DateUtil.getNomeDia(dia);
                        if (modo == 0) {
                            switch (diaSemana) {
                                case DateUtil.SEG:
                                case DateUtil.QUA:
                                case DateUtil.SEX:
                                    if (calendario.get(dia).getPrimeiro() == null && !diasdisciplina.contains(dia)) {
                                        diasdisciplina.add(dia);
                                        diasletivos--;
                                        break OUTER;
                                    }
                                    break;
                            }
                        } else {
                            switch (diaSemana) {
                                case DateUtil.TER:
                                case DateUtil.QUI:
                                    if (calendario.get(dia).getPrimeiro() == null && !diasdisciplina.contains(dia)) {
                                        diasdisciplina.add(dia);
                                        diasletivos--;
                                        break OUTER;
                                    }
                                    break;
                            }

                        }

                    }

                }

                updateCalendario(uc, diasdisciplina, calendario);

                if (modo == 0) {
                    modo = 1;
                } else {
                    modo = 0;
                }

            }
        }
    }
}
