package senai.cronos.gui.custom;

import java.awt.Graphics;
import java.awt.Image;
import javax.swing.JPanel;

/**
 *
 * @author sergio
 */
public class SplashPanel extends JPanel {

    @Override
    public void paintComponent(Graphics g) {
        Image wallpaper = ImageLoader.loadSplash();
        if (wallpaper != null) {
            g.drawImage(wallpaper, 0, 0, null);
        }
    }
}
