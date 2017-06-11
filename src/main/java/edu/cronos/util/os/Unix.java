package edu.cronos.util.os;

/**
 * @author Sergio Lisan e Carlos Melo
 */
public class Unix implements OperatingSystem {

    @Override
    public String readRegistry(String location, String key) {
        throw new UnsupportedOperationException("Not supported yet.");
    }

    @Override
    public String getName() {
        return OperatingSystem.UNIX;
    }

    @Override
    public String getLookAndFeel() {
        return "com.sun.java.swing.plaf.gtk.GTKLookAndFeel";
    }
}
