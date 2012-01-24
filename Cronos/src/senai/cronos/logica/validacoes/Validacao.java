package senai.cronos.logica.validacoes;

/**
 * interface que serve de contrato para que subclasses possam implementar a logica
 * de validacao de certo objeto para o sistema
 * 
 * @author Sergio Lisan e Carlos Melo
 */
public interface Validacao<T> {
    
    boolean isValid(T u);
    
}
