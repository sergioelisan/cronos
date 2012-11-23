package senai.cronos.database.vectors;

import senai.cronos.entidades.*;
import senai.util.date.Feriado;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class RepositoryFactory {

    public static Repository getRepository(Class c) {

        if (c.equals(Docente.class)) {
            return Docentes.instance();

        } else if (c.equals(Laboratorio.class)) {
            return Laboratorios.instance();

        } else if (c.equals(Nucleo.class)) {
            return Nucleos.instance();

        } else if (c.equals(Turma.class)) {
            return Turmas.instance();

        } else if (c.equals(UnidadeCurricular.class)) {
            return Disciplinas.instance();

        } else if (c.equals(Feriado.class)) {
            return Feriados.instance();

        } else
            return null;
    }
}
