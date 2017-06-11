package edu.cronos.util.ui;

import javax.swing.*;

public class GuiUtil {
    public GuiUtil() {
    }

    public ImageIcon getImage(String endereco) {
        return new ImageIcon(this.getClass().getResource(endereco));
    }
}