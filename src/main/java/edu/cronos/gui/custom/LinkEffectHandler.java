package edu.cronos.gui.custom;

import edu.cronos.gui.ColorManager;

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

/**
 * @author sergio lisan e carlos melo
 */
public class LinkEffectHandler extends MouseAdapter {

    public static final Color overbackgroundcolor = ColorManager.getColor("background");
    public static final Color overforegroundcolor = Color.white;
    public static final Color outbackgroundcolor = Color.white;
    public static final Color outforegroundcolor = Color.black;

    @Override
    public void mouseEntered(MouseEvent e) {
        JComponent com = (JComponent) e.getSource();
        com.setOpaque(true);
        com.setBackground(overbackgroundcolor);
        com.setForeground(overforegroundcolor);
        com.updateUI();
        ((JComponent) com.getParent()).updateUI();
    }

    @Override
    public void mouseExited(MouseEvent e) {
        JComponent com = (JComponent) e.getSource();

        com.setBackground(new Color(50, 50, 200, 20));
        com.setOpaque(true);

        com.setForeground(outforegroundcolor);
        com.updateUI();
        ((JComponent) com.getParent()).updateUI();
    }
}
