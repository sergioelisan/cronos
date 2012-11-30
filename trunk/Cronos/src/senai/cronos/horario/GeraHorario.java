package senai.cronos.horario;

import java.sql.SQLException;
import java.util.List;
import senai.cronos.Fachada;
import senai.cronos.entidades.*;

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
    public void gerarHorario(Turma turma) throws ClassNotFoundException, SQLException {
        this.turma = turma;
        alocarAulas(turma.getHorario() );
        alocarDocentes(turma.getHorario() );
    }

    /**
     * metodo abstrato que delega a subclasse a forma como as disciplinas serao
     * distribuidas pelos dias uteis do calendario pre-determinado
     * @param horario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract void alocarAulas(Horario horario) throws ClassNotFoundException, SQLException;

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
     * @param horario
     */
    private void alocarDocentes(Horario horario) {

    }

}
