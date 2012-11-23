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

public class GeraHorarioAlternadoQuebrado extends GeraHorario {

    @Override
    public void generate(Horario horario) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> calendario = horario.getHorario();

        List<UnidadeCurricular> disciplinas = getDisciplinas();
        int diasletivos = calendario.keySet().size() * 2;

        int modo = 0;
        for (UnidadeCurricular uc : disciplinas) {
            int total = getQuantidadeDeDias(uc, 2);

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
                            }
                        } else if (modo == 1) {
                            switch (diaSemana) {
                                case DateUtil.SEG:
                                case DateUtil.QUA:
                                case DateUtil.SEX:
                                    if (calendario.get(dia).getSegundo() == null && !diasdisciplina.contains(dia)) {
                                        diasdisciplina.add(dia);
                                        diasletivos--;
                                        break OUTER;
                                    }
                            }
                        } else if (modo == 2) {
                            switch (diaSemana) {
                                case DateUtil.TER:
                                case DateUtil.QUI:
                                    if (calendario.get(dia).getPrimeiro() == null && !diasdisciplina.contains(dia)) {
                                        diasdisciplina.add(dia);
                                        diasletivos--;
                                        break OUTER;
                                    }
                            }
                        } else {
                            switch (diaSemana) {
                                case DateUtil.TER:
                                case DateUtil.QUI:
                                    if (calendario.get(dia).getSegundo() == null && !diasdisciplina.contains(dia)) {
                                        diasdisciplina.add(dia);
                                        diasletivos--;
                                        break OUTER;
                                    }
                            }
                        }
                    }
                }

                updateCalendario(uc, diasdisciplina, calendario, modo);

                if (modo == 0) {
                    modo = 1;
                } else if (modo == 1) {
                    modo = 2;
                } else if (modo == 2) {
                    modo = 3;
                } else {
                    modo = 0;
                }
            }

        }

    }
}
