package senai.cronos.logica.horarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.util.Tupla;
import senai.cronos.util.debug.Debug;

public class GeraHorarioContinuoQuebrado extends GeraHorario {

    @Override
    public void generate(Horario horario) throws ClassNotFoundException, SQLException {
        Map<Date, Tupla<Aula, Aula>> calendario = horario.getHorario();
        List<UnidadeCurricular> disciplinas = getDisciplinas();
        int diasletivos = calendario.keySet().size() * 2;

        int modo = 0;
        for (UnidadeCurricular uc : disciplinas) {
            int total = getQuantidadeDeDias(uc, 2);
                        
            if (total <= diasletivos ) {
                List<Date> diasdisciplina = new ArrayList<>();

                for (int i = 0; i < total; i++) {
                    OUTER:
                    for (Date dia : calendario.keySet()) {
                        if (modo == 0) {
                            if (calendario.get(dia).getPrimeiro() == null && !diasdisciplina.contains(dia)) {
                                diasdisciplina.add(dia);
                                diasletivos--;
                                break OUTER;
                            }
                        } else {
                            if (calendario.get(dia).getSegundo() == null && !diasdisciplina.contains(dia)) {
                                diasdisciplina.add(dia);
                                diasletivos--;
                                break OUTER;
                            }
                        }

                    }
                }

                updateCalendario(uc, diasdisciplina, calendario, modo);

                if (modo == 0) {
                    modo = 1;
                } else {
                    modo = 0;
                }
            }

        }

    }
}
