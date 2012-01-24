package senai.cronos.logica;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import senai.cronos.database.dao.DAOTurma;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Turma;

/**
 *
 * @author Carlos Melo e sergio Lisan
 */
public final class Turmas {

    public Turmas() {
    }

    /**
     * Método que retorno o objeto turma tendo como parametro de entrada o nome. 
     * @param nome String com o nome da turma
     * @return objeto Turma
     */
    public Turma buscaTurma(String nome) throws ClassNotFoundException, SQLException {
        for (Turma turma : new DAOTurma().get() ) {
            if (turma.getNome().equals(nome)) {
                return turma;
            }
        }
        
        
        return null;
    }

    /**
     * retorna as turmas de um nucleo
     * @param nucleo
     * @return 
     */
    public List<Turma> buscaTurma(Nucleo nucleo) throws ClassNotFoundException, SQLException{
        List<Turma> turmas = new ArrayList<>();
        for (Turma turma : new DAOTurma().get() ) {
            if (turma.getNucleo().equals(nucleo)) {
                turmas.add(turma);
            }
        }
        return turmas;
    }
}
