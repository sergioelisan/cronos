/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package senai.cronos.gui.custom;

import java.awt.Color;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import javax.swing.JComponent;
import javax.swing.JLabel;

/**
 *
 * @author sergio lisan e carlos melo
 */
public class BlinkEffectHandler extends MouseAdapter {

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent com = (JComponent) (e.getSource() instanceof JLabel ? ((JComponent) e.getSource()).getParent() : e.getSource());
        Color actual = com.getBackground();

        int red   = actual.getRed() + fator;
        int green = actual.getGreen() + fator;
        int blue  =  actual.getBlue() + fator;

        Color end = new Color(red > 255 ? 255 : red, green > 255 ? 255 : green, blue > 255 ? 255 : blue);
        com.setBackground(end);
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent com = (JComponent) (e.getSource() instanceof JLabel ? ((JComponent) e.getSource()).getParent() : e.getSource());
        Color actual = com.getBackground();

        int red   = actual.getRed() - fator;
        int green = actual.getGreen() - fator;
        int blue  =  actual.getBlue() - fator;

        Color end = new Color(red < 0 ? 0 : red, green < 0 ? 0 : green, blue < 0 ? 0 : blue);
        com.setBackground(end);
    }

    @Override
    public void mouseClicked(MouseEvent e) {
        mouseExited(e);
    }

    public static final int fator = 20;

}
