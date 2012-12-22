package senai.cronos.horario;

import java.sql.SQLException;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Main;
import senai.cronos.CronosAPI;
import senai.cronos.entidades.*;
import senai.util.Tupla;
import senai.util.date.DateUtil;

/**
 * Classe abstrata que armazena a ordem e algumas partes do algoritmo de geração
 * de horarios
 *
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
     * Turma em que uma instancia dessa classe será encarregada de gerar o
     * horario
     */
    private Turma turma;

    /**
     * Gera o horario de uma turma
     *
     * @param turma
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public void gerarHorario(Turma turma) throws Exception {
        this.turma = turma;
        alocarAulas(turma.getHorario().getHorario());

        alocarDocentes(turma.getHorario().getHorario());

    }

    /**
     * metodo abstrato que delega a subclasse a forma como as disciplinas serao
     * distribuidas pelos dias uteis do calendario pre-determinado
     *
     * @param horario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public abstract void alocarAulas(Map<Date, Tupla<Aula, Aula>> horario) throws ClassNotFoundException, SQLException;

    /**
     * Aloca os docentes no horario ja determinado
     *
     * @param horarioWrapper
     */
    public abstract void alocarDocentes(Map<Date, Tupla<Aula, Aula>> horario) throws Exception;

    /**
     * Retorna as disciplinas de um curso
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<UnidadeCurricular> getDisciplinas(int modulo) throws ClassNotFoundException, SQLException {
        int dias = DateUtil.diferencaDias(turma.getEntrada(), Main.CALENDARIO.getDiasUteis().get(0));
        //int modulo = (dias / 180) + 1;
        List<UnidadeCurricular> disciplinas = CronosAPI.buscaDisciplinas(Nucleo.COMUM, modulo);

        disciplinas.addAll(CronosAPI.buscaDisciplinas(turma.getNucleo(), modulo));

        return disciplinas;
    }
    
    /**
     * Retorna as disciplinas de um curso
     *
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    public List<UnidadeCurricular> getDisciplinas() throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> disciplinas = CronosAPI.buscaDisciplinas(Nucleo.COMUM);
        disciplinas.addAll(CronosAPI.buscaDisciplinas(turma.getNucleo()));
        return disciplinas;
    }

    /**
     *      */
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
     *      */
    public Turma getTurma() {
        return turma;
    }
}