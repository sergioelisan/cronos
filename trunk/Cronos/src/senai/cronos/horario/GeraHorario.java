package senai.cronos.horario;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;
import java.util.Set;
import senai.cronos.Fachada;
import senai.cronos.entidades.*;
import senai.util.Tupla;

/**
 * Classe abstrata que armazena a ordem e algumas partes do algoritmo de geração
 * de horarios
 * @author Sergio
 */
public abstract class GeraHorario {

    /**
     * quantidade de horas que uma aula usa quando ocupa o turno inteiro
     */
    public static final Integer TURNO_INTEIRO = 4;

    /**
     * quantidade de horas que uma aula usa quando ocupa o turno pela metade
     */
    public static final Integer TURNO_METADE = 2;

    /**
     * Turma em que uma instancia dessa classe será encarregada de gerar o horario
     */
    private Turma turma;

    /**
     * Gera o horario de uma turma
     * @param turma
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void gerarHorario(Turma turma) throws Exception {
        this.turma = turma;
        alocarAulas(turma.getHorarioWrapper().getHorario() );
        alocarDocentes(turma.getHorarioWrapper() );
    }

    /**
     * metodo abstrato que delega a subclasse a forma como as disciplinas serao
     * distribuidas pelos dias uteis do calendario pre-determinado
     * @param horario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException;

    /**
     * Retorna as disciplinas de um curso
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<UnidadeCurricular> getDisciplinas() throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> disciplinas = Fachada.buscaDisciplinas(Nucleo.COMUM);
        disciplinas.addAll(Fachada.buscaDisciplinas(turma.getNucleo() ) );

        return disciplinas;
    }

    /** */
    public Aula getAula(UnidadeCurricular uc) {
        Aula aula = Aula.create();
        aula.setDisciplina(uc);
        aula.setLab(uc.getLab());
        aula.setDocente(Docente.PADRAO);

        return aula;
    }

    /**
     * retorna a quantidade de dias que serão lecionados para uma disciplina
     *
     * @param uc
     * @param horasPorDia
     * @return
     */
    public int getQuantidadeDeDias(UnidadeCurricular uc, int horasPorDia) {
        return (uc.getCargaHoraria() / horasPorDia) + (uc.getCargaHoraria() % horasPorDia);
    }

    /**
     * Aloca os docentes no horario ja determinado
     * @param horarioWrapper
     */
    private void alocarDocentes(Horario horarioWrapper) throws Exception {
        Map<Aula, Map<Integer, List<Date> > > diasDeAula = getDiasAula(horarioWrapper);

        for (Aula a : diasDeAula.keySet() ) {
            List<Docente> docentesCandidatos = Fachada.buscaDocente(turma.getNucleo());

        }

    }

    /**
     * Coloca em um dicionario os dias que cada UnidadeCurricular será lecionada
     * @param horarioWrapper
     * @return
     */
    private Map<Aula, Map<Integer, List<Date> > > getDiasAula(Horario horarioWrapper) {
        Set<Aula> aulas = new HashSet<>();
        Map<Aula, Map<Integer, List<Date> > > diasDeAula = new HashMap<>();

        // adiciona instancias unicas de cada Aula em um conjunto
        for(Date dia : horarioWrapper.getHorario().keySet()) {
            aulas.add(horarioWrapper.getHorario().get(dia).getPrimeiro() );
            aulas.add(horarioWrapper.getHorario().get(dia).getSegundo() );
        }

        // cria um dicionario usando as instancias unicas de Aulas, que serao
        // associadas aos dias que elas sao lecionadas
        for(Aula aula : aulas) {
            Map<Integer, List<Date> > ocorrencia = new HashMap<>();
            ocorrencia.put(Tupla.PRIMEIRA, new ArrayList<Date>());
            ocorrencia.put(Tupla.SEGUNDA, new ArrayList<Date>());
            diasDeAula.put(aula, ocorrencia);
        }

        // le os dias que cada Aula será lecionada e joga no conjunto correspondente
        for(Date dia : horarioWrapper.getHorario().keySet() ) {
            Aula a1 = horarioWrapper.getHorario().get(dia).getPrimeiro();
            diasDeAula.get(a1).get(Tupla.PRIMEIRA).add(dia);

            Aula a2 = horarioWrapper.getHorario().get(dia).getSegundo();
            diasDeAula.get(a2).get(Tupla.SEGUNDA).add(dia);
        }

        return diasDeAula;
    }

}