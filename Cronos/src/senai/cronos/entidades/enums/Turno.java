package senai.cronos.entidades.enums;

/**
 *
 * @author sergio lisan
 */
public enum Turno {

    MANHA,
    TARDE,
    NOITE;
    
    public static Turno getTurno(Integer i) {
        return values()[i];
    }
}
