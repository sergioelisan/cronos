package senai.cronos.logica.horarios;

import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.logica.validacoes.Validacao; 

/**
 * Classe abstrata que modelo um Gerador de Horario. Ele tem um validador e um
 * metodo que recebe uma turma para retornar um horario.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public abstract class GeraHorario {

    /**
     * numero maximo de tentativas em busca de um professor apto antes de buscar um extra quadro
     */
    protected final int MAX_TENTATIVAS = 30;
    
    /**
     * objeto que validará o docente para saber se ele esta apto a lecionar alguma disciplina
     */
    protected Validacao<Docente> validador;
    
    /**
     * docente padrao, caso nao seja achado nenhum docente apto para lecionar alguma disciplina
     */
    protected final Docente EXTRA_QUADRO = new Docente();

    /**
     * gera um horario para uma turma
     * @param turma
     * @return
     * @throws Exception 
     */
    public abstract Horario generate(Turma turma) throws Exception;
}
