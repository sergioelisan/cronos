package senai.cronos.logica;

import java.sql.SQLException;
import java.util.*;
import java.util.logging.Level;
import java.util.logging.Logger;
import senai.cronos.database.dao.DAOFactory;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Nucleo;
import senai.cronos.entidades.Proficiencia;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.util.Aleatorio;
import senai.cronos.util.Observador;

/**
 *
 * @author Carlos Melo e sergio lisan
 */
public final class Disciplinas implements Observador, Repository<UnidadeCurricular> {
    
    private List<UnidadeCurricular> disciplinas;

    private static Disciplinas instance = new Disciplinas();
    
    public static Disciplinas instance() {
        return instance;
    }
    
    private Disciplinas() {
        try {
            DAOFactory.getDao(UnidadeCurricular.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(Disciplinas.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    /**
     * retorna uma disciplina identificada pelo seu nome
     * @param nome
     * @return 
     */
    public UnidadeCurricular buscaDisciplina(String nome) throws ClassNotFoundException, SQLException {
        for (UnidadeCurricular uc : get()) {
            if (uc.getNome().equals(nome)) {
                return uc;
            }
        }
        return null;
    }

    /**
     * metodo que retorna as disciplinas de uma turma
     * @param t a ser pesquisada
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo) throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();
        for (UnidadeCurricular uc : get()) {
            if (uc.getNucleo().equals(nucleo)) {
                unidades.add(uc);
            }
        }
        // Ordena da maior carga horaria para a menor
        Collections.sort(unidades);
        Collections.reverse(unidades);

        return unidades;
    }

    /**
     * retorna as disciplinas de uma turma em um determinado modulo
     * @param t a ser pesquisada
     * @param modulo a ser pesquisado
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo, Integer modulo)  throws ClassNotFoundException, SQLException {
        List<UnidadeCurricular> unidades = new ArrayList<>();
        
        for (UnidadeCurricular uc : get()) {
            if (uc.getNucleo().equals(nucleo) && uc.getModulo().equals(modulo)) {
                unidades.add(uc);
            }
        }
        
        return unidades;
    }

    /**
     * Retorna o melhor docente a partir de uma unidade curricular
     * @param uc a ser avaliada
     * @return o docente mais indicado
     */
    public Docente melhorDocente(UnidadeCurricular uc)  throws ClassNotFoundException, SQLException {
        // TODO Fazer algoritmo sofisticado, com analise probabilistica
        // ALGORITMO PROVISORIO!
        // Cria um dicionario com uma Proficiencia e um Docente correspondente
        Map<Docente, Proficiencia> proficiencias = new HashMap<>();
        List<Docente> docentes = DAOFactory.getDao(Docente.class).get();
        for (Docente dc :  docentes) {
            for (Proficiencia p : dc.getProficiencias()) {
                if (p.getDisciplina().equals(uc)) {
                    proficiencias.put(dc, p);
                    break; // break usado para evitar que iteracoes sejam feitas desnecessariamente!
                }
            }
        }
        // Se nao houver nenhuma proficiencia, retorna nulo
        if (proficiencias.isEmpty()) {
            return null;
        }
        // TODO Implementar a logica de geracao de aleatorios
        int fator = Aleatorio.alec(0, 4);
        // Retorna o Melhor docente
        return selecioneMelhorDocente(proficiencias, fator);
    }

    /**
     * Metodo que aplica o algoritmo matematico para seleciona o melhor docente
     * @param docentes
     * @param fator
     * @return 
     */
    private Docente selecioneMelhorDocente(Map<Docente, Proficiencia> proficiencias, int fator) {
        // Varre a lista de docentes para remover os que tem mais de 66% de ocupacao
        for (Docente doc : proficiencias.keySet()) {
            if (doc.percentualOcupacao() > .66) {
                proficiencias.remove(doc);
            }
        }
        // Armazena os melhores docentes associados a seus Scores Finais
        List<Docente> melhores = new ArrayList<>();
        for (Docente doc : proficiencias.keySet()) {
            double scorefinal = Docentes.instance().getScoreFinal(doc, proficiencias.get(doc).getDisciplina());
            doc.setScore((int) scorefinal);
            melhores.add(doc);
        }
        // pega os quatro melhores
        Collections.sort(melhores);
        Collections.reverse(melhores);
        Docente[] docentes = new Docente[4];
        for (int i = 0; i < 4 && i < melhores.size(); i++) {
            docentes[i] = melhores.get(i);
        }
        // retorna o melhor docente de 1 a 4.
        return docentes[fator];
    }

    @Override
    public void update() {
        try {
            disciplinas = DAOFactory.getDao(UnidadeCurricular.class).get();
        } catch (ClassNotFoundException | SQLException ex) {
            ex.printStackTrace(System.err);
        }
    }

    @Override
    public List<UnidadeCurricular> get() {
        return disciplinas;
    }

    @Override
    public UnidadeCurricular get(Class c, Integer id) {
        for(UnidadeCurricular disciplina : disciplinas)
            if(disciplina.getId().equals(id))
                return disciplina;
        return null;
    }
}
