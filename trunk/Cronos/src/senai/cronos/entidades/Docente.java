package senai.cronos.entidades;

import java.util.*;
import senai.cronos.entidades.enums.Formacao;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Tupla;

/**
 *
 * @author Carlos Melo e sergio lisan
 */
public class Docente implements Comparable<Docente> {

    public Docente() {
    }

    public Docente(Integer matricula, String nome, Formacao formacao, Date contratacao,
            Nucleo nucleo, Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao, int score,
            Turno primeiroTurno, Turno segundoTurno) {
        this();
        this.matricula = matricula;
        this.nome = nome;
        this.formacao = formacao;
        this.contratacao = contratacao;
        this.nucleo = nucleo;
        this.ocupacao = ocupacao;
        this.score = score;
        this.primeiroTurno = primeiroTurno;
        this.segundoTurno = segundoTurno;
    }

    /*
     * METODOS DE NEGOCIO PARA UM DOCENTE
     */
    /**
     * verifica a disponibilidade do docente em um turno
     *
     * @param dia
     * @return verdadeiro se esta disponivel, false se nao estiver disponivel
     */
    public boolean isDisponivel(Date dia, Turno turno) {
        if (!ocupacao.containsKey(dia)) {
            return true;
        }
        Map<Turno, Tupla<Aula, Aula>> ocupacaoDia = ocupacao.get(dia);
        return ocupacaoDia.get(turno) == null;
    }

    /**
     * verifica a disponibilidade de um docente em uma parte de um turno
     *
     * 1 - primeira metade do turno 2 - segunda metade do turno
     *
     * @param dia
     * @param turno
     * @param metade
     * @return
     */
    public boolean isDisponivel(Date dia, Turno turno, Integer metade) {
        Map<Turno, Tupla<Aula, Aula>> ocupacaoDia = ocupacao.get(dia);
        Tupla<Aula, Aula> ocupacaoTurno = ocupacaoDia.get(turno);

        if (metade.equals(1)) {
            return ocupacaoTurno.getPrimeiro() == null;
        } else {
            return ocupacaoTurno.getSegundo() == null;
        }
    }

    /**
     * Retorna as unidades curriculares das proficiencias deste docente
     *
     * @return
     */
    public List<UnidadeCurricular> getUnidadesCurriculares() {
        List<UnidadeCurricular> disciplinas = new ArrayList<>();

        for (Proficiencia p : this.getProficiencias()) {
            disciplinas.add(p.getDisciplina());
        }

        return disciplinas;
    }

    /**
     * Retorna a percentagem de ocupacao do docente
     *
     * @return a percentagem de ocupacao
     */
    public double percentualOcupacao() {
        double aulas = 0;
        double ocupado = 0;

        for (Map<Turno, Tupla<Aula, Aula>> ocupacaoDia : ocupacao.values()) {
            for (Tupla<Aula, Aula> tupla : ocupacaoDia.values()) {
                aulas += 2;
                if (tupla.getPrimeiro() != null) {
                    ocupado += 1;
                }
                if (tupla.getSegundo() != null) {
                    ocupado += 1;
                }
            }
        }

        return aulas == 0 ? 0 : (ocupado * 100) / aulas;
    }

    /**
     * adiciona uma proficiencia, ou adiciona pontos a proficiencia.
     *
     * @param prof
     */
    public void addProficiencia(Proficiencia prof) {
        if (proficiencias.contains(prof)) {
            int index = proficiencias.indexOf(prof);
            Proficiencia pf = proficiencias.get(index);
            pf.setNivel(pf.getNivel() + 1);
            pf.setLecionado(pf.getLecionado() + 1);
        } else {
            proficiencias.add(prof);
        }
    }

    /**
     * adiciona uma ocupacao em um dia, turno e aulas
     *
     * @param dia
     * @param t
     * @param aulas
     */
    public void addOcupacao(Date dia, Turno t, Aula a1, Aula a2) {
        if (ocupacao.containsKey(dia)) {
            if (!ocupacao.get(dia).containsKey(t)) {
                ocupacao.get(dia).put(t, new Tupla<>(a1, a2));
            } else {
                ocupacao.get(dia).get(t).setPrimeiro(a1);
                ocupacao.get(dia).get(t).setSegundo(a2);
            }
        } else {
            Map<Turno, Tupla<Aula, Aula>> turnos = new HashMap<>();
            turnos.put(t, new Tupla<>(a1, a2));
            ocupacao.put(dia, turnos);
        }

    }

    /*
     *
     * METODOS ACESSORES
     *
     */
    @Override
    public int compareTo(Docente o) {
        return score > o.score ? 1 : score == o.score ? 0 : -1;
    }

    public Date getContratacao() {
        return contratacao;
    }

    public void setContratacao(Date contratacao) {
        this.contratacao = contratacao;
    }

    public Formacao getFormacao() {
        return formacao;
    }

    public void setFormacao(Formacao formacao) {
        this.formacao = formacao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public String getNome() {
        return nome;
    }

    public void setNome(String nome) {
        this.nome = nome;
    }

    public Nucleo getNucleo() {
        return nucleo;
    }

    public void setNucleo(Nucleo nucleo) {
        this.nucleo = nucleo;
    }

    public Map<Date, Map<Turno, Tupla<Aula, Aula>>> getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao) {
        this.ocupacao = ocupacao;
    }

    public Turno getPrimeiroTurno() {
        return primeiroTurno;
    }

    public void setPrimeiroTurno(Turno primeiroTurno) {
        this.primeiroTurno = primeiroTurno;
    }

    public List<Proficiencia> getProficiencias() {
        return proficiencias;
    }

    public void setProficiencias(List<Proficiencia> proficiencias) {
        this.proficiencias = proficiencias;
    }

    public int getScore() {
        return score;
    }

    public void setScore(int score) {
        this.score = score;
    }

    public Turno getSegundoTurno() {
        return segundoTurno;
    }

    public void setSegundoTurno(Turno segundoTurno) {
        this.segundoTurno = segundoTurno;
    }

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Docente other = (Docente) obj;
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        if (!Objects.equals(this.nome, other.nome)) {
            return false;
        }
        if (this.formacao != other.formacao) {
            return false;
        }
        if (!Objects.equals(this.contratacao, other.contratacao)) {
            return false;
        }
        if (!Objects.equals(this.nucleo, other.nucleo)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 5;
        hash = 79 * hash + Objects.hashCode(this.matricula);
        hash = 79 * hash + Objects.hashCode(this.nome);
        hash = 79 * hash + (this.formacao != null ? this.formacao.hashCode() : 0);
        hash = 79 * hash + Objects.hashCode(this.contratacao);
        hash = 79 * hash + Objects.hashCode(this.nucleo);
        hash = 79 * hash + Objects.hashCode(this.proficiencias);
        hash = 79 * hash + Objects.hashCode(this.ocupacao);
        hash = 79 * hash + this.score;
        hash = 79 * hash + (this.primeiroTurno != null ? this.primeiroTurno.hashCode() : 0);
        hash = 79 * hash + (this.segundoTurno != null ? this.segundoTurno.hashCode() : 0);
        return hash;
    }

    @Override
    public String toString() {
        return "Docente { "
                + "matricula = " + matricula
                + ", nome = " + nome
                + ", formacao = " + formacao
                + ", contratacao = " + contratacao
                + ", nucleo = " + nucleo
                + ", proficiencias = " + proficiencias
                + ", ocupacao = " + ocupacao
                + ", score = " + score
                + ", primeiroTurno = " + primeiroTurno
                + ", segundoTurno = " + segundoTurno + '}';
    }
    /**
     * matricula que serve como identificador unico do docente
     */
    private Integer matricula = 0;
    /**
     * nome do docente
     */
    private String nome = "Extra Quadro";
    /**
     * Inteiro que contem um numero arbitrario sobre a formacao do docente
     */
    private Formacao formacao = Formacao.MEDIO;
    /**
     * data de contratacao do docente
     */
    private Date contratacao = new Date();
    /**
     * nucleo em que o docente preferencialmente trabalha
     */
    private Nucleo nucleo = new Nucleo();
    /**
     * lista de proficiencias do docente
     */
    private List<Proficiencia> proficiencias = new ArrayList<>();
    /**
     * Como o horario do docente esta sendo preenchido
     */
    private Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao = new HashMap();
    /**
     * pontuacao do docente. esse atributo é relacionado com a formacao e o
     * tempo de casa do docente
     */
    private int score = 0;
    /**
     * Turno de operacao do docente
     */
    private Turno primeiroTurno = Turno.MANHA;
    /**
     * docentes geralmente trabalham em dois turnos, nao necessariamento
     * seguidos
     */
    private Turno segundoTurno = Turno.TARDE;
}
