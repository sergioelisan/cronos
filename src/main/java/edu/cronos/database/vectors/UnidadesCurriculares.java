package edu.cronos.database.vectors;

import edu.cronos.CronosAPI;
import edu.cronos.database.dao.DAOFactory;
import edu.cronos.entidades.Docente;
import edu.cronos.entidades.Nucleo;
import edu.cronos.entidades.Proficiencia;
import edu.cronos.entidades.UnidadeCurricular;
import edu.cronos.util.Aleatorio;
import edu.cronos.util.Observador;

import java.sql.SQLException;
import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 * @author Carlos Melo e sergio lisan
 */
public final class UnidadesCurriculares implements Observador, Repository<UnidadeCurricular> {

    private static UnidadesCurriculares instance = new UnidadesCurriculares();
    private List<UnidadeCurricular> disciplinas;

    private UnidadesCurriculares() {
        try {
            DAOFactory.getDao(UnidadeCurricular.class).registra(this);
            update();
        } catch (ClassNotFoundException | SQLException ex) {
            Logger.getLogger(UnidadesCurriculares.class.getName()).log(Level.SEVERE, null, ex);
        }
    }

    public static UnidadesCurriculares instance() {
        return instance;
    }

    /**
     * retorna uma disciplina identificada pelo seu nome
     *
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
     *
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
     *
     * @param modulo a ser pesquisado
     * @return uma lista de unidades curriculares
     */
    public List<UnidadeCurricular> buscaDisciplina(Nucleo nucleo, Integer modulo) throws ClassNotFoundException, SQLException {
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
     *
     * @param uc a ser avaliada
     * @return o docente mais indicado
     */
    public Docente melhorDocente(UnidadeCurricular uc) throws ClassNotFoundException, SQLException {
        List<Proficiencia> proficiencias = new ArrayList<>();

        for (Docente dc : CronosAPI.<Docente>get(Docente.class)) {
            for (Proficiencia p : dc.getProficiencias()) {
                if (p.getDisciplina().equals(uc)) {
                    proficiencias.add(p);
                    break; // break usado para evitar que iteracoes sejam feitas desnecessariamente!
                }
            }
        }

        // Se nao houver nenhuma proficiencia, retorna 'extra-quadro'
        if (proficiencias.isEmpty()) {
            return Docente.PADRAO;
        }

        List<Docente> melhores = new ArrayList<>();
        for (Proficiencia prof : proficiencias) {
            Docente doc = prof.getDocente();
            double scorefinal = Docentes.instance().getScoreFinal(doc, prof.getDisciplina());
            doc.setScore((int) scorefinal);
            melhores.add(doc);
        }

        // pega os quatro melhores
        Collections.sort(melhores);
        Collections.reverse(melhores);

        // TODO Implementar a logica de geracao de aleatorios
        int fator = Aleatorio.alec(0, proficiencias.size() > 4 ? 4 : proficiencias.size());

        // Retorna o Melhor docente
        return melhores.get(fator);
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
        for (UnidadeCurricular disciplina : disciplinas) {
            if (disciplina.getId().equals(id)) {
                return disciplina;
            }
        }
        return null;
    }
}
