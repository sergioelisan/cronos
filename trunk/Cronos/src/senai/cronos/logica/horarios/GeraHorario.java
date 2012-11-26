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

public abstract class GeraHorario {

    private int MAXTENTATIVAS = 30;
    private Validacao validacao;
    protected Turma turma;

    public Horario generate(Turma turma) throws ClassNotFoundException, SQLException {
        this.turma = turma;
        this.validacao = new ValidaAptidao(turma);
        Horario horario = getHorario();
        generate(horario);
        return horario;
    }

    abstract void generate(Horario horario) throws ClassNotFoundException, SQLException;

    protected Horario getHorario() throws ClassNotFoundException, SQLException {
        Turma t = Fachada.<Turma>get(Turma.class, turma.getId());
        Horario h = t.getHorario();
        if (h.getHorario().isEmpty()) {
            h = Horario.create();
        }
        return h;
    }

    protected List<UnidadeCurricular> getDisciplinas() throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> disciplinas = new ArrayList<>();
        Nucleo gestao = Fachada.buscaNucleo("comum");
        for (int i = 0; i <= 3; i++) {
            List<UnidadeCurricular> pormodulo = Fachada.buscaDisciplinas(turma.getNucleo(), i);
            List<UnidadeCurricular> gestoes = Fachada.buscaDisciplinas(gestao, i);
            disciplinas.addAll(pormodulo);
            disciplinas.addAll(gestoes);
        }
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
    private Docente getDocente(UnidadeCurricular disciplina, List<Date> diasdisciplina)
            throws ClassNotFoundException, SQLException {
        Docente doc;
        int tentativas = 0;
        do {
            doc = Fachada.melhorDocente(disciplina);
            tentativas++;
        } while (!validacao.isValid(doc) && isEmChoque(doc, diasdisciplina) && tentativas < MAXTENTATIVAS);

        if (tentativas > MAXTENTATIVAS) {
            doc = new Docente();
        }

        return doc;
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
        Docente doc;
        int tentativas = 0;
        do {
            doc = Fachada.melhorDocente(disciplina);
            tentativas++;
        } while (!validacao.isValid(doc) && isEmChoque(doc, diasdisciplina, metade) && tentativas < MAXTENTATIVAS);

        if (tentativas > MAXTENTATIVAS) {
            doc = Docente.PADRAO;
        }

        return doc;
    }

    /**
     * verifica se há choque
     *
     * @param docente
     * @param diasdisciplina
     * @return
     */
    protected boolean isEmChoque(Docente docente, List<Date> diasdisciplina) {

        // @todo Novo algoritmo para verificar o choque do docente.

        /*Turno tn = turma.getTurno();
         for (Date dia : diasdisciplina) {
         if (!docente.getHorarioDocente().isDisponivel(tn, dia)) {
         return true;
         }
         }
         return false;*/
        return true;
    }

    /**
     * verifica se há choque
     *
     * @param docente
     * @param diasdisciplina
     * @return
     */
    protected boolean isEmChoque(Docente docente, List<Date> diasdisciplina, Integer metade) {
        Turno tn = turma.getTurno();
        for (Date dia : diasdisciplina) {
            if (docente.getHorarioDocente().isDisponivel(dia, tn, metade)) {
                return true;
            }
        }
        return false;
    }

    /**
     * retorna a quantidade de dias que serão lecionados para uma disciplina
     *
     * @param uc
     * @param horasPorDia
     * @return
     */
    protected int getQuantidadeDeDias(UnidadeCurricular uc, int horasPorDia) {
        int dias = uc.getCargaHoraria() / horasPorDia;
        int resto = uc.getCargaHoraria() % horasPorDia;
        return dias + resto;
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
