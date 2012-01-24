package senai.cronos.util;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;

/**
 *
 * @author Sergio Lisan
 */
public class Repositorio<T> {

    public Repositorio(String arquivo) {
        repositorio = new File(arquivo);
    }

    /**
     * Serializa um objeto em um arquivo
     * @param objeto a ser persistido
     */
    public void save(T objeto) {
        try (ObjectOutputStream out = new ObjectOutputStream(new FileOutputStream(repositorio))) {
            out.writeObject(objeto);
            out.flush();
        } catch(Exception e) {
            System.out.println("Problemas ao salvar o objeto no repositorio!");
        }
    }

    /**
     * Le um arquivo e retorna um objeto do repositorio
     * @return objeto serializado
     */
    public T load() {
        try(ObjectInputStream in = new ObjectInputStream(new FileInputStream(repositorio) ) ) {
            T objeto = (T) in.readObject();
            return objeto;
        } catch(Exception e) {
            System.out.println("Problemas ao carregar o objeto do repositorio"); 
            return null;
        }
    }
    
    private File repositorio;
}
