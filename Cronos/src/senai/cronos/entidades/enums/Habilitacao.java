package senai.cronos.entidades.enums;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public enum Habilitacao {
    
    TECNICO,
    QUALIFICACAO;
    
    public static Habilitacao getHabilitacao(int i) {
        return values()[i];
    }
    
}
