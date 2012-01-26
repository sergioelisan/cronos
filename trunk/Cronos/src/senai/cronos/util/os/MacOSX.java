package senai.cronos.util.os;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class MacOSX implements OperatingSystem {

    @Override
    public String readRegistry(String location, String key) {
        throw new UnsupportedOperationException("MacOS X ainda não é suportado!");
    }

    @Override
    public String getName() {
        return OperatingSystem.MacOSX;
    }
    
}
