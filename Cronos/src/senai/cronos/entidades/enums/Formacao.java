package senai.cronos.entidades.enums;

/**
 * Enumeracao que representa os niveis de formacao de um individuo
 * 
 * @author Sergio Lisan e Carlos Melo
 */
public enum Formacao {
    
    MEDIO,
    TECNICO,
    SUPERIOR_INCOMPLETO,
    SUPERIOR_COMPLETO,
    POSGRADUACAO,
    MESTRADO,
    DOUTORADO,
    PHD;
    
    public static Formacao getFormacao(Integer i){
        return values()[i];
    }
    
    
}
