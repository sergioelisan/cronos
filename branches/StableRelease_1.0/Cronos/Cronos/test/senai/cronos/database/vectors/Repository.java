package senai.cronos.database.vectors;

import java.util.List;

/**
 *
 * interface que viabiliza com que repositorios retornem uma lista de determinado
 * objeto
 * 
 * @author sergio lisan e carlos melo
 */
public interface Repository<T> {
    
    List<T> get();
    
    T get(Class c, Integer id);
    
}
