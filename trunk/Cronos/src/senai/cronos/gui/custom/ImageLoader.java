package senai.cronos.gui.custom;

import java.awt.Image;
import java.awt.Toolkit;
import javax.swing.ImageIcon;

/**
 *
 * Utilidades para carregamento de imagens
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class ImageLoader {

    public static Image loadBackground() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon imagem = new ImageIcon(kit.getClass().getResource("/senai/cronos/gui/images/wallpaper.jpg"));
        return imagem.getImage();
    }

    public static Image loadSplash() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon imagem = new ImageIcon(kit.getClass().getResource("/senai/cronos/gui/images/Splash.png"));
        return imagem.getImage();
    }
}
