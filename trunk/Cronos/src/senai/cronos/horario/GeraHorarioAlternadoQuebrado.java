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
import senai.util.date.DateUtil;

public class GeraHorarioAlternadoQuebrado extends GeraHorario {

    @Override
    public void alocarAulas(Horario horarioWrapper) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> horario = horarioWrapper.getHorario();
        List<UnidadeCurricular> disciplinas = getDisciplinas();

        int modo = 0;
        for (UnidadeCurricular uc : disciplinas) {
            Aula aula = getAula(uc);
            int total = getQuantidadeDeDias(uc, GeraHorario.TURNO_METADE);

            for (int i = 0; i < total; i++) {
                OUTER:
                for (Date dia : horario.keySet()) {
                    String diaSemana = DateUtil.getNomeDia(dia);
                    if (modo == 0) {
                        switch (diaSemana) {
                            case DateUtil.SEG:
                            case DateUtil.QUA:
                            case DateUtil.SEX:
                                if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                                    horario.get(dia).setPrimeiro(aula);

                                    break OUTER;
                                }
                        }
                    } else if (modo == 1) {
                        switch (diaSemana) {
                            case DateUtil.SEG:
                            case DateUtil.QUA:
                            case DateUtil.SEX:
                                if (horario.get(dia).getSegundo().equals(Aula.PADRAO)) {
                                    horario.get(dia).setSegundo(aula);

                                    break OUTER;
                                }
                        }
                    } else if (modo == 2) {
                        switch (diaSemana) {
                            case DateUtil.TER:
                            case DateUtil.QUI:
                                if (horario.get(dia).getPrimeiro().equals(Aula.PADRAO)) {
                                    horario.get(dia).setPrimeiro(aula);

                                    break OUTER;
                                }
                        }
                    } else {
                        switch (diaSemana) {
                            case DateUtil.TER:
                            case DateUtil.QUI:
                                if (horario.get(dia).getSegundo().equals(Aula.PADRAO)) {
                                    horario.get(dia).setSegundo(aula);

                                    break OUTER;
                                }

                        }

                    }

                }

            }

            modo = (modo == 3) ? 0 : ++modo;
        }

    }

}