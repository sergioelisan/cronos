package senai.cronos.entidades;

import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;
import senai.cronos.entidades.enums.Turno;
import senai.cronos.util.Tupla;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Ocupacao {
    
    public Ocupacao() {
        
    }

    public Ocupacao(Integer matricula, Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao) {
        this.matricula = matricula;
        this.ocupacao = ocupacao;
    }

    public Integer getMatricula() {
        return matricula;
    }

    public void setMatricula(Integer matricula) {
        this.matricula = matricula;
    }

    public Map<Date, Map<Turno, Tupla<Aula, Aula>>> getOcupacao() {
        return ocupacao;
    }

    public void setOcupacao(Map<Date, Map<Turno, Tupla<Aula, Aula>>> ocupacao) {
        this.ocupacao = ocupacao;
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
        if (!Objects.equals(this.matricula, other.matricula)) {
            return false;
        }
        return true;
    }

    @Override
    public int hashCode() {
        int hash = 3;
        hash = 31 * hash + Objects.hashCode(this.matricula);
        return hash;
    }

    @Override
    public String toString() {
        return "Ocupacao{" + "matricula=" + matricula + ", ocupacao=" + ocupacao + '}';
    }
    
    private Integer matricula = 0;
    private Map<Date, Map<Turno, Tupla<Aula,Aula>>> ocupacao = new HashMap<>();
}
