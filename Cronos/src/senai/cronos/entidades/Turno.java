package senai.cronos.entidades;

/**
 *
 * @author sergio lisan
 */
public enum Turno {

    MANHA,
    TARDE,
    NOITE,
    MANHA_TARDE,
    MANHA_NOITE,
    TARDE_NOITE;

    public static Turno getTurno(Integer i) {
        return values()[i];
    }
}
