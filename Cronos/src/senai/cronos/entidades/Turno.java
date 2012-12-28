package senai.cronos.entidades;

import senai.util.Tupla;

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

    public Tupla<String, String> getHorario() {
        if (this.equals(Turno.MANHA)) {
            return new Tupla<>("07h30 - 09h00", "09h15 - 11h30");
        } else if (this.equals(Turno.TARDE)) {
            return new Tupla<>("13h30 - 15h15", "15h30 - 17h30");
        } else if (this.equals(Turno.NOITE)) {
            return new Tupla<>("18h00 - 20h00", "20h15 - 22h00");
        } else {
            return null;
        }
    }

    /**
     * verifica se um turno esta dentro de outros dois turnos
     *
     * @param t
     * @return
     */
    public boolean isInside(Turno t) {
        if (this.equals(Turno.MANHA)
                && (t.equals(Turno.MANHA_TARDE) || t.equals(Turno.MANHA_NOITE) || t.equals(Turno.MANHA))) {
            return true;
        } else if (this.equals(Turno.TARDE)
                && (t.equals(Turno.MANHA_TARDE) || t.equals(Turno.TARDE_NOITE) || t.equals(Turno.TARDE))) {
            return true;
        } else if (this.equals(Turno.NOITE)
                && (t.equals(Turno.TARDE_NOITE) || t.equals(Turno.MANHA_NOITE) || t.equals(Turno.NOITE))) {
            return true;
        } else {
            return false;
        }
    }
}
