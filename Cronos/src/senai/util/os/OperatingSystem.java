package senai.util.os;

/**
 * Interface para operações com o sistema operacional host. Lembrando que essa
 * interface é feita para se manter a interoperabilidade, já que as
 * funcionalidades, os algoritmos concretos serão implementados pelas classes
 * que implementarem essa função, e que serão selecionadas dinamicamente pela
 * máquina virtual do java.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public interface OperatingSystem {

    /**
     * Altera o registro do sistema operacional
     *
     * @param location é a localização do item
     * @param key a ser alterada
     * @return
     */
    String readRegistry(String location, String key);

    /**
     * Retorna o nome do sistema operacional
     */
    String getName();

    String getLookAndFeel();
    // Sistemas operacionais suportados
    final String WINDOWS = "Microsoft Windows";
    final String MacOSX = "Apple MacOS X";
    final String UNIX = "Unix";
    final String LINUX = "Linux";
}
