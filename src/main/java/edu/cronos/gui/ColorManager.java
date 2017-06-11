package edu.cronos.gui;

import java.awt.*;
import java.util.ResourceBundle;

/**
 * Gerenciamento do esquema de cores do sistema
 *
 * @author Sergio Lisan e Carlos Melo
 */
public class ColorManager {

    public static final int FATOR = 40;

    /**
     * Retorna a cor de um componente especifico
     *
     * @param componente
     * @return a a
     */
    public static Color getColor(String componente) {
        ResourceBundle rb = ResourceBundle.getBundle("colorschema");
        return Color.decode(rb.getString(componente));
    }

    /**
     * clareia uma cor em uma FATOR de 20 pontos para R, G e B, se possivel
     *
     * @param actual
     * @return
     */
    public static Color claro(Color actual) {
        int red = actual.getRed() + FATOR;
        int green = actual.getGreen() + FATOR;
        int blue = actual.getBlue() + FATOR;

        return new Color(red > 255 ? 255 : red, green > 255 ? 255 : green, blue > 255 ? 255 : blue);
    }
}
