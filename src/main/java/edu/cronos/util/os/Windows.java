package edu.cronos.util.os;

import java.io.IOException;
import java.io.InputStream;

/**
 * @author Sergio Lisan e Carlos Melo
 */
public class Windows implements OperatingSystem {

    @Override
    public String readRegistry(String location, String key) {
        try {

            Process process = Runtime.getRuntime().exec("reg query "
                    + '"' + location + "\" /v " + key);

            InputStream is = process.getInputStream();
            StringBuilder sw = new StringBuilder();

            try {
                int c;
                while ((c = is.read()) != -1) {
                    sw.append((char) c);
                }
            } catch (IOException e) {
            }

            String output = sw.toString();


            int i = output.indexOf("REG_SZ");
            if (i == -1) {
                return null;
            }

            sw = new StringBuilder();
            i += 6;
            for (; ; ) {
                if (i > output.length()) {
                    break;
                }
                char c = output.charAt(i);
                if (c != ' ' && c != '\t') {
                    break;
                }
                ++i;
            }


            for (; ; ) {
                if (i > output.length()) {
                    break;
                }
                char c = output.charAt(i);
                if (c == '\r' || c == '\n') {
                    break;
                }
                sw.append(c);
                ++i;
            }

            return sw.toString();


        } catch (Exception e) {
            e.printStackTrace(System.err);
            return null;
        }
    }

    @Override
    public String getName() {
        return OperatingSystem.WINDOWS;
    }

    @Override
    public String getLookAndFeel() {
        return "com.sun.java.swing.plaf.windows.WindowsLookAndFeel";
    }
}
