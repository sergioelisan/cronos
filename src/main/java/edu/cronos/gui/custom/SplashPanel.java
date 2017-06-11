package edu.cronos.gui.custom;

import javax.swing.*;
import java.awt.*;

/**
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
