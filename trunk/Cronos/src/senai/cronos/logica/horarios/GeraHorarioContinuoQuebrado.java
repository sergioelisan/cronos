package senai.cronos.logica.horarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.entidades.*;
import senai.cronos.logica.validacoes.ValidaAptidao;
import senai.cronos.util.Tupla;

/**
 * Gera um horario com dias continuos, porem com duas aulas por dia. 
 *
 * @author sergio lisan e carlos melo
 */
public class GeraHorarioContinuoQuebrado extends GeraHorario {

    @Override
    public Horario generate(Turma turma) throws ClassNotFoundException, SQLException {
        validador = new ValidaAptidao(turma);

        // pega o horario ja existente no banco de dados
        Horario horario = Fachada.get(Horario.class, turma.getId());
        if(horario.getHorario().keySet().isEmpty()) {
            // se nao tiver um horario existente, coloca um novo
            horario = new Horario(turma);
        }       
        
        Map<Date, Tupla<Aula, Aula>> calendario = horario.getHorario();

        // pega uma lista de disciplinas de um nucleo em um modulo
        List<UnidadeCurricular> disciplinas = new ArrayList<>();
        Nucleo gestao = Fachada.buscaNucleo("comum");
        for(int i = 0; i < 3; i++) {
            List<UnidadeCurricular> disciplinasPorModulo = Fachada.buscaDisciplinas(turma.getNucleo(), i);
            List<UnidadeCurricular> gestoes = Fachada.buscaDisciplinas(gestao, i);
            disciplinas.addAll(disciplinasPorModulo);
            disciplinas.addAll(gestoes);
        }

        int modo = 0;

        // para cada unidade curricular
        for (UnidadeCurricular disciplina : disciplinas) {
            // procura por um docente bom e apto.
            Docente docente;
            int tentativas = 0;
            do {
                docente = Fachada.melhorDocente(disciplina);
                tentativas++;
            } while (!validador.isValid(docente) && tentativas < MAX_TENTATIVAS);

            // se extourar o numero de tentativas, seleciona um extra-quadro
            if (tentativas >= MAX_TENTATIVAS) {
                docente = EXTRA_QUADRO;
            }

            Aula aula = new Aula(disciplina, docente, disciplina.getLab());

            int dias = disciplina.getCargaHoraria() / 2; // carga horaria dividida por 4h diarias
            int resto = disciplina.getCargaHoraria() % 2; // carga que sobra q
            int totaldias = dias + resto; // quantidade total de dias que a cadeira é lecionada

            for (int i = 0; i < totaldias; i++) {
                OUTER:
                for (Date data : calendario.keySet()) {

                    // modo 0, aulas no primeiro horario
                    if (modo == 0) {
                        if (calendario.get(data).getPrimeiro() == null) {
                            calendario.get(data).setPrimeiro(aula);
                            break OUTER;
                        }

                    } // modo 1, aulas no segundo horario
                    else if (modo == 1) {
                        if (calendario.get(data).getSegundo() == null) {
                            calendario.get(data).setSegundo(aula);
                            break OUTER;

                        }

                    }

                }

            }

            // troca o modo
            if (modo == 0) {
                modo = 1;
            } else if (modo == 1) {
                modo = 0;
            }
        }

        // retorna o horario de uma turma em um determinado dia
        return horario;
    }
}
