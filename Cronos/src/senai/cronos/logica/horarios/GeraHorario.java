package senai.cronos.logica.horarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.entidades.*;
import senai.cronos.entidades.Turno;
import senai.cronos.logica.validacoes.ValidaAptidao;
import senai.cronos.logica.validacoes.Validacao;
import senai.util.Tupla;

/**
 * Classe abstrata que armazena a ordem e algumas partes do algoritmo de geração
 * de horarios
 * @author Sergio
 */
public abstract class GeraHorario {

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
        fazerHorario(turma.getHorario() );
    }

    /**
     * metodo abstrato que delega a subclasse a forma como as disciplinas serao
     * distribuidas pelos dias uteis do calendario pre-determinado
     * @param horario
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    protected abstract void fazerHorario(Horario horario) throws ClassNotFoundException, SQLException;

    /**
     * Retorna as disciplinas de um curso
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    protected List<UnidadeCurricular> getDisciplinas() throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> disciplinas = new ArrayList<>();
        
        // TODO implementar
        
        return disciplinas;
    }

    /**
     * retorna o melhor docente
     *
     * @param disciplina
     * @param diasdisciplina
     * @return
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    private Docente getDocente(UnidadeCurricular disciplina, List<Date> diasdisciplina, Integer metade)
            throws ClassNotFoundException, SQLException {
        Docente doc = Docente.PADRAO;
        
        // TODO implementar

        return doc;
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
     * atualiza o horario
     *
     * @param uc
     * @param diasdisciplina
     * @param calendario
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    protected void updateCalendario(UnidadeCurricular uc, List<Date> diasdisciplina,
            Map<Date, Tupla<Aula, Aula>> calendario)
            throws ClassNotFoundException, SQLException {
        Docente doc = getDocente(uc, diasdisciplina);
        Laboratorio lab = uc.getLab();
        Aula au = Aula.create();
        au.setDisciplina(uc);
        au.setDocente(doc);
        au.setLab(lab);

        doc.addProficiencia(uc);

        for (Date dia : diasdisciplina) {
            Tupla<Aula, Aula> aulas = new Tupla<>(au, au);
            calendario.put(dia, aulas);
            doc.getHorarioDocente().add(dia, turma.getTurno(), aulas.getPrimeiro(), Tupla.PRIMERA);
            doc.getHorarioDocente().add(dia, turma.getTurno(), aulas.getSegundo(), Tupla.SEGUNDA);
        }
    }

    /**
     * atualiza o horario
     *
     * @param uc
     * @param diasdisciplina
     * @param calendario
     * @param modo
     * @throws ClassNotFoundException
     * @throws SQLException
     */
    protected void updateCalendario(UnidadeCurricular uc, List<Date> diasdisciplina,
            Map<Date, Tupla<Aula, Aula>> calendario, Integer modo)
            throws ClassNotFoundException, SQLException {

        Docente doc = getDocente(uc, diasdisciplina, modo);
        Laboratorio lab = uc.getLab();
        
        Aula au = Aula.create();
        au.setDisciplina(uc);
        au.setDocente(doc);
        au.setLab(lab);

        for (Date dia : diasdisciplina) {
            if (modo == 0 || modo == 2) {
                calendario.get(dia).setPrimeiro(au);
            } else {
                calendario.get(dia).setSegundo(au);
            }

            doc.getHorarioDocente().add(dia, turma.getTurno(), au, modo);
        }
    }
}
