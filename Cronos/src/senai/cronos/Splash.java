package senai.cronos;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JWindow;
import javax.swing.Timer;
import senai.cronos.gui.custom.ImageLoader;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class Splash extends JWindow {    
    
    private Timer animator;
    
    private static Splash instance = new Splash();
    
    public static Splash instance() {
        return instance;
    }
    
    private Splash() {        
    }
    
    
    
    @Override
    public void paintComponents(Graphics g) {
        Image wallpaper = ImageLoader.loadSplash();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }
}
