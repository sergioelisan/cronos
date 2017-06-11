package edu.cronos.database.vectors;

/**
 * @author sergio lisan e carlos melo
 */
public class RepositoryFactory {

    public static Repository getRepository(Class c) {

        switch (c.getName()) {
            case "edu.cronos.entidades.Docente":
                return Docentes.instance();

            case "edu.cronos.entidades.Laboratorio":
                return Laboratorios.instance();

            case "edu.cronos.entidades.Nucleo":
                return Nucleos.instance();

            case "edu.cronos.entidades.Turma":
                return Turmas.instance();

            case "edu.cronos.entidades.UnidadeCurricular":
                return UnidadesCurriculares.instance();

            case "edu.cronos.util.date.Feriado":
                return Feriados.instance();

            default:
                throw new IllegalArgumentException(c.getName() + " não possui Repository");
        }

    }

}
