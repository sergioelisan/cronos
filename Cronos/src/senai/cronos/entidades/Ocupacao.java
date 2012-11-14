package senai.cronos.entidades;

import java.util.*;
import senai.cronos.Main;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Tupla;
import senai.cronos.util.calendario.Calendario;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Ocupacao {

    public Ocupacao(Docente docente) {
        this.docente = docente;
        Calendario calendario = Main.calendario;

        Map<Date, Tupla<Aula, Aula>> horario1 = new TreeMap<>();
        Map<Date, Tupla<Aula, Aula>> horario2 = new TreeMap<>();

        for (Date dia : calendario.getDiasUteis()) {
            horario1.put(dia, new Tupla<>(Aula.NULA, Aula.NULA));
            horario2.put((Date) dia.clone(), new Tupla<>(Aula.NULA, Aula.NULA));
        }

        ocupacao.put(docente.getPrimeiroTurno(), horario1);
        ocupacao.put(docente.getSegundoTurno(), horario2);
    }

    public Docente getDocente() {
        return docente;
    }

    public void setDocente(Docente docente) {
        this.docente = docente;
    }

    public Map<Turno, Map<Date, Tupla<Aula, Aula>>> getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Map<Turno, Map<Date, Tupla<Aula, Aula>>> ocupacao) {
        this.ocupacao = ocupacao;
    }

    /**
     * verifica a disponibilidade de um docente.
     *
     * @param turno
     * @param dia
     * @param metade
     * @return
     */
    public boolean isDisponivel(Turno turno, Date dia, Integer metade) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);

        if (metade == 0) {
            return horario.get(dia).getPrimeiro() == null;
        } else {
            return horario.get(dia).getSegundo() == null;
        }
    }

    /**
     * verifica a disponibilidade de um docente.
     *
     * @param turno
     * @param dia
     * @return
     */
    public boolean isDisponivel(Turno turno, Date dia) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        return horario.get(dia).getPrimeiro().equals(Aula.NULA) && horario.get(dia).getSegundo().equals(Aula.NULA);
    }

    /**
     * adiciona uma aula em uma metade de um turno
     *
     * @param turno
     * @param dia
     * @param aula
     * @param metade
     */
    public void add(Turno turno, Date dia, Aula aula, Integer metade) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        if (metade == 0) {
            horario.get(dia).setPrimeiro(aula);
        } else {
            horario.get(dia).setSegundo(aula);
        }
    }

    /**
     * adiciona uma tupla de aulas em um dia em um turno
     *
     * @param turno
     * @param dia
     * @param aulas
     */
    public void add(Turno turno, Date dia, Tupla<Aula, Aula> aulas) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        horario.put(dia, aulas);
    }

    /**
     * remove a ocupacao de um determinado dia
     *
     * @param turno
     * @param dia a
     */
    public void remove(Turno turno, Date dia) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        horario.put(dia, new Tupla<Aula, Aula>(null, null));
    }

    /**
     * remove a ocupacao de um determinado dia, em um turno, em uma metade
     *
     * @param turno
     * @param dia
     * @param metade mov
     */
    public void remove(Turno turno, Date dia, Integer metade) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        if (metade == 0) {
            horario.get(dia).setPrimeiro(null);
        } else {
            horario.get(dia).setSegundo(null);
        }

    }

    /**
     * retorna uma tupla
     *
     * @param turno
     * @param dia
     * @return
     */
    public Tupla<Aula, Aula> get(Turno turno, Date dia) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        return horario.get(dia);
    }

    /**
     * retorna uma aula
     *
     * @param turno
     * @param dia
     * @param metade
     * @return
     */
    public Aula get(Turno turno, Date dia, Integer metade) {
        Map<Date, Tupla<Aula, Aula>> horario = ocupacao.get(turno);
        if (metade == 0) {
            return horario.get(dia).getPrimeiro();
        } else {
            return horario.get(dia).getSegundo();
        }
    }

    /**
     * retorna o percetual de ocupacao
     *
     * @return
     */
    public double percentualOcupacao() {
        double aulas = 0;
        double ocupado = 0;

        for (Map<Date, Tupla<Aula, Aula>> ocupacaoDia : ocupacao.values()) {
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

    @Override
    public boolean equals(Object obj) {
        if (obj == null) {
            return false;
        }
        if (getClass() != obj.getClass()) {
            return false;
        }
        final Ocupacao other = (Ocupacao) obj;
        if (!Objects.equals(this.docente, other.docente)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.docente);
        return hash;
    }

    @Override
    public String toString() {
        return "Ocupacao{" + "matricula=" + docente + ", ocupacao=" + ocupacao + '}';
    }
    private Docente docente;
    private Map<Turno, Map<Date, Tupla<Aula, Aula>>> ocupacao = new HashMap<>();
}
