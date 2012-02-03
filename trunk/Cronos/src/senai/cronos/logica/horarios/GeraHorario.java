package senai.cronos.logica.horarios;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
import java.util.Map;
import senai.cronos.Fachada;
import senai.cronos.entidades.*;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.logica.validacoes.ValidaAptidao;
import senai.cronos.logica.validacoes.Validacao;
import senai.cronos.util.Tupla;
import senai.cronos.util.debug.Debug;

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
        Horario horario = Fachada.<Horario>get(Horario.class, turma.getId());
        if (horario.getHorario().keySet().isEmpty()) {
            horario = new Horario(turma);
        }
        return horario;
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

        System.out.println(doc.getNome() );
        System.out.println(isEmChoque(doc, diasdisciplina) );
        
        return doc;
    }
    
    /**
     * retorna o melhor docente
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
        } while ( !validacao.isValid(doc) && isEmChoque(doc, diasdisciplina, metade) && tentativas < MAXTENTATIVAS);

        if (tentativas > MAXTENTATIVAS) {
            doc = new Docente();
        }

        return doc;
    }

    /**
     * verifica se há choque
     * @param docente
     * @param diasdisciplina
     * @return 
     */
    protected boolean isEmChoque(Docente docente, List<Date> diasdisciplina) {
        Turno tn = turma.getTurno();
        for (Date dia : diasdisciplina) {
            if (!docente.getOcupacao().isDisponivel(tn, dia) )
                return true;
        }
        return false;        
    }
    
    /**
     * verifica se há choque
     * @param docente
     * @param diasdisciplina
     * @return
     */
    protected boolean isEmChoque(Docente docente, List<Date> diasdisciplina, Integer metade) {
        Turno tn = turma.getTurno();
        for (Date dia : diasdisciplina) {
            if (!docente.getOcupacao().isDisponivel(tn, dia, metade) )
                return true;
        }
        return false;        
    }

    /**
     * retorna a quantidade de dias que serão lecionados para uma disciplina
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
     * @param uc
     * @param diasdisciplina
     * @param calendario
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    protected void updateCalendario(UnidadeCurricular uc, List<Date> diasdisciplina, Map<Date, Tupla<Aula, Aula>> calendario)
            throws ClassNotFoundException, SQLException {
        Docente doc = getDocente(uc, diasdisciplina);
        Laboratorio lab = uc.getLab();
        Aula au = new Aula(uc, doc, lab);
        
        doc.addProficiencia(uc);
        
        for (Date dia : diasdisciplina) {
            Tupla<Aula, Aula> aulas = new Tupla<>(au, au);
            calendario.put(dia, aulas);
            doc.getOcupacao().add(turma.getTurno(), dia, aulas);
        }
    }

    /**
     * atualiza o horario
     * @param uc
     * @param diasdisciplina
     * @param calendario
     * @param modo
     * @throws ClassNotFoundException
     * @throws SQLException 
     */
    protected void updateCalendario(UnidadeCurricular uc, List<Date> diasdisciplina, Map<Date, Tupla<Aula, Aula>> calendario, Integer modo)
            throws ClassNotFoundException, SQLException {
        
        Docente doc = getDocente(uc, diasdisciplina, modo);
        Laboratorio lab = uc.getLab();
        Aula au = new Aula(uc, doc, lab);
        
        for (Date dia : diasdisciplina) {
            if (modo == 0 || modo == 2) {
                calendario.get(dia).setPrimeiro(au);
            } else {
                calendario.get(dia).setSegundo(au);
            }
            
            doc.getOcupacao().add(turma.getTurno(), dia, au, modo);
        }
    }
}
