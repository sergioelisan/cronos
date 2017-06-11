package edu.cronos.gui.custom;

import javax.swing.*;
import java.awt.*;

/**
 * Utilidades para carregamento de imagens
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class ImageLoader {

    public static Image loadBackground() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon imagem = new ImageIcon(kit.getClass().getResource("/images/wallpaper.jpg"));
        return imagem.getImage();
    }

    public static Image loadSplash() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon imagem = new ImageIcon(kit.getClass().getResource("/images/Splash.png"));
        return imagem.getImage();
    }

    public static Image loadIcon() {
        Toolkit kit = Toolkit.getDefaultToolkit();
        ImageIcon imagem = new ImageIcon(kit.getClass().getResource("/images/icon.png"));
        return imagem.getImage();
    }
}
