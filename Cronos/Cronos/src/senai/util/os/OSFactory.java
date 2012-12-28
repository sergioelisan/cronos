package senai.util.os;

/**
 *
 * Essa classe é uma fabrica de objetos que interagem com o sistema operacional
 * host da Maquina virtual do java. Ela identifica o sistema e instancia o
 * objeto correto para lidar com este.
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class OSFactory {

    public static OperatingSystem getOperatingSystem() {
        String os = getOSName();

        switch (os) {
            case OperatingSystem.WINDOWS:
                return new Windows();

            case OperatingSystem.LINUX:
                return new Linux();

            case OperatingSystem.MacOSX:
                return new MacOSX();

            case OperatingSystem.UNIX:
                return new Unix();

            default:
                throw new IllegalArgumentException("Sistema Operacional não suportado!");
        }
    }

    /**
     * Retorna o nome do sistema operacional host
     *
     * @return
     */
    private static String getOSName() {

        if (System.getProperty("os.name").toLowerCase().indexOf("windows") > -1) {
            return OperatingSystem.WINDOWS;

        } else if (System.getProperty("os.name").toLowerCase().indexOf("linux") > -1) {
            return OperatingSystem.LINUX;

        } else if (System.getProperty("os.name").toLowerCase().indexOf("mac") > -1) {
            return OperatingSystem.MacOSX;

        } else if (System.getProperty("os.name").toLowerCase().indexOf("unix") > -1) {
            return OperatingSystem.UNIX;

        } else {
            throw new IllegalArgumentException("Sistema Operacional não suportado!");
        }
    }
}
