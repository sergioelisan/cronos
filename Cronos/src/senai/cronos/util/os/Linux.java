package senai.cronos.util.os;

/**
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class Linux implements OperatingSystem {

    @Override
    public String readRegistry(String location, String key) {
        String userPath = System.getProperty(key);
        StringBuilder sb = new StringBuilder();
        
        int diretorios = 0;
        int actual = -1;
        while(diretorios < 3) {
            char c = userPath.charAt(++actual);
            sb.append(c);
            
            if (c == '/')
                diretorios++;
        }
        
        return sb.toString();
    }

    @Override
    public String getName() {
        return OperatingSystem.LINUX;
    }

}
