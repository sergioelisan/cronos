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

    /**
     * verifica se um turno esta dentro de outros dois turnos
     * @param t
     * @return 
     */
    public boolean isInside(Turno t) {
        if (this.equals(Turno.MANHA) 
                && (t.equals(Turno.MANHA_TARDE) || t.equals(Turno.MANHA_NOITE) || t.equals(Turno.MANHA) ) ) {
            return true;
        } else if (this.equals(Turno.TARDE) 
                && (t.equals(Turno.MANHA_TARDE) || t.equals(Turno.TARDE_NOITE) || t.equals(Turno.TARDE) ) ) {
            return true;
        } else if (this.equals(Turno.NOITE) 
                && (t.equals(Turno.TARDE_NOITE) || t.equals(Turno.MANHA_NOITE) || t.equals(Turno.NOITE) )) {
            return true;
        } else
            return false;
    }
}
