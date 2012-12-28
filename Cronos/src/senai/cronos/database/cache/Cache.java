package senai.cronos.database.cache;

import java.util.List;

/**
 *
 * interface que viabiliza com que repositorios retornem uma lista de determinado
 * objeto
 * 
 * @author sergio lisan e carlos melo
 */
public interface Cache<T> {
    
    List<T> get();
    
    T get(Class c, Integer id);
    
    void clear();
    
}
