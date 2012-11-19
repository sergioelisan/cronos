package senai.cronos.gui.horarios;

import java.awt.Color;
import java.awt.Dimension;
import java.awt.Font;
import java.util.*;
import javax.swing.JLabel;
import senai.cronos.entidades.Aula;
import senai.cronos.entidades.Docente;
import senai.cronos.entidades.Horario;
import senai.cronos.entidades.Turma;
import senai.cronos.entidades.UnidadeCurricular;
import senai.cronos.util.calendario.DateUtil;
import senai.cronos.util.Tupla;

/**
 *
 * Classe que fabrica e retorna os calendarios de cada mes do periodo de aulas,
 * em forma gráfica
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class HorarioUIFactory {

    /**
     * construtor, recebe um horario, gera os calendarios e deixa disponivel os
     * CalendarUI's de cada horario de cada mes e as legendas.
     *
     * @param horario
     */
    public HorarioUIFactory(Horario horario) {
        this.horario = horario;
        loadCalendars();
    }

    /**
     * retorna as legendas dos calendarios
     *
     * @return
     */
    public List<JLabel> getLegendas() {
        return legendas;
    }

    /**
     * Retorna uma lista de calendarios, contendo o horario de cada mes
     *
     * @return
     */
    public List<HorarioUI> getCalendarios() {
        return calendarios;
    }

    /**
     * recebe um horario e retorna uma lista de objetos que possuem a
     * representacao grafica dele, e cada objeto calendario representa um mes.
     *
     * @return
     */
    private void loadCalendars() {
        // haverá um calendário por mês
        calendarios = new ArrayList<>();

        // divide o horario geral para um sub horario pra cada mes
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horariosMes = divideHorario();
        // atribui cores para cada uma das disciplinas
        Map<UnidadeCurricular, Color> dicionarioCores = loadCoresELegendas();

        // Para cada mes
        for (Integer mes : horariosMes.keySet()) {
            // Cria uma lista de peças para serem adicionadas no calendario
            List<HorarioTile> tiles = new ArrayList<>();

            // Para cada dia do mes atual
            for (Date dia : horariosMes.get(mes).keySet()) {
                // Cria uma peça
                HorarioTile tile = new HorarioTile();
                // Pega a tupla de aulas do dia
                Tupla<Aula, Aula> aulas = horariosMes.get(mes).get(dia);

                // Coloca a primeira aula na parte de cima da peça do calendario
                tile.setPrimeiroHorario(dicionarioCores.get(aulas.getPrimeiro().getDisciplina()));
                // Coloca a segunda aula na parte do meio da peça
                tile.setSegundoHorario(dicionarioCores.get(aulas.getSegundo().getDisciplina()));

                // Coloca o numero do dia na parte de baixo da peça
                int day = DateUtil.getDia(dia);
                tile.setDia(String.valueOf(day));

                // Coloca o dia da semana na peça, que será util na organizacao desta no calendario
                int diaSemana = DateUtil.getDiaSemana(dia);
                tile.setDiaSemana(diaSemana);

                // Adiciona a nova peça a lista de peças
                tiles.add(tile);
            }

            // cria uma calendario do mes, com as peças geradas para cada dia deste
            HorarioUI calendar = new HorarioUI(DateUtil.getNomeMes(mes), new Turma(), tiles);
            calendarios.add(calendar);
        }

    }

    /**
     * filtra as disciplinas de um horario e da cores para representalas
     *
     * @param horario
     * @return
     */
    public Map<UnidadeCurricular, Color> loadCoresELegendas() {
        // cria uma disciplina nula que vai ocupar o espaco vazio
        Map<UnidadeCurricular, Docente> docs = new HashMap<>();
        Set<UnidadeCurricular> disciplinas = new HashSet<>();

        for (Date dia : horario.getHorario().keySet()) {
            Tupla<Aula, Aula> aulas = horario.getHorario().get(dia);
            if (aulas.getPrimeiro() == null) {
                aulas.setPrimeiro(Aula.create());
            }

            if (aulas.getSegundo() == null) {
                aulas.setSegundo(Aula.create());
            }

            disciplinas.add(aulas.getPrimeiro().getDisciplina());
            docs.put(aulas.getPrimeiro().getDisciplina(), aulas.getPrimeiro().getDocente());

            disciplinas.add(aulas.getSegundo().getDisciplina());
            docs.put(aulas.getSegundo().getDisciplina(), aulas.getSegundo().getDocente());

        }

        int i = 0;
        Map<UnidadeCurricular, Color> dicionarioCores = new HashMap<>();
        for (UnidadeCurricular disciplina : disciplinas) {
            dicionarioCores.put(disciplina, CORES.get(i));
            i++;
        }
        dicionarioCores.put(new UnidadeCurricular(), Color.white);


        /*
         * Cria as legendas, usando o dicionario de cores
         */
        for (UnidadeCurricular uc : docs.keySet()) {
            if (uc != null && !uc.getNome().isEmpty()) {
                JLabel label = new JLabel();
                label.setOpaque(true);
                label.setPreferredSize(new Dimension(450, 25));
                label.setMinimumSize(new Dimension(450, 25));
                label.setMaximumSize(new Dimension(450, 25));
                label.setFont(new Font("Segoe UI", Font.PLAIN, 14));
                label.setForeground(Color.white);
                label.setBackground(dicionarioCores.get(uc));

                String text = " [" + uc.getCargaHoraria() + "h] sala: " + uc.getLab().getNome() + ", " + uc.getNome() + " - " + docs.get(uc).getNome();
                label.setText(text);

                legendas.add(label);
            }

        }

        return dicionarioCores;
    }

    /**
     * separa o horario integral em varios sub-horarios divididos pelo seu mes
     *
     * @param horario
     * @return
     */
    public Map<Integer, Map<Date, Tupla<Aula, Aula>>> divideHorario() {
        Map<Integer, Map<Date, Tupla<Aula, Aula>>> horarios = new TreeMap<>();

        Integer mesAtual = 0;
        for (Date dia : horario.getHorario().keySet()) {
            Integer mes = DateUtil.getMes(dia);

            // se for o mes corrente
            if (mesAtual.equals(mes)) {
                horarios.get(mes).put(dia, horario.getHorario().get(dia));
            } // quando troca o mes
            else if (!mesAtual.equals(mes)) {
                Map<Date, Tupla<Aula, Aula>> mapa = new TreeMap<>();
                mapa.put(dia, horario.getHorario().get(dia));

                horarios.put(mes, mapa);
                mesAtual = mes;
            }
        }

        return horarios;
    }

    /**
     * carrega as cores definidas no colorschema.properties
     *
     * @return
     */
    private List<Color> readSystemColors() {
        ResourceBundle rb = ResourceBundle.getBundle("senai/cronos/properties/colorschema");

        List<Color> cores = new ArrayList<>();
        for (int i = 1; i <= 15; i++) {
            cores.add(Color.decode(rb.getString("disciplina." + (i))));
        }

        return cores;
    }
    /**
     * Lista de calendarios
     */
    private List<HorarioUI> calendarios;
    /**
     * lista de cores determinadas pelo sistema
     */
    private List<Color> CORES = readSystemColors();
    /**
     * legendas de cada aula (disciplina, docente, laboratorio)
     */
    private List<JLabel> legendas = new ArrayList<>();
    /**
     * horario em que o algoritmo de fabricacao de calendarios vai trabalhar em
     * cima
     */
    private Horario horario;
}
