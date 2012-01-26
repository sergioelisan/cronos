package senai.cronos.util.os;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Linux implements OperatingSystem {

    @Override
    public String readRegistry(String location, String key) {
        throw new UnsupportedOperationException("Linux ainda não é suportado");
    }

    @Override
    public String getName() {
        return OperatingSystem.LINUX;
    }

}
