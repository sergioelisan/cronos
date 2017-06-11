package edu.cronos.util.ui;

/**
 * EDU.Cronos
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;

public class Tile extends JPanel implements PerfectContinuousConstants {
    public static final int Tile_Width = 300;
    public static final int Tile_Height = 150;
    private Color entered;
    private Color exited;

    public Tile() {
        this.setPreferredSize(new Dimension(300, 150));
        this.setMinimumSize(new Dimension(300, 150));
        this.setMaximumSize(new Dimension(300, 150));
        this.setBackground(PerfectContinuousConstants.bright_bcc);
        this.addMouseListener(new Tile.TileHandler());
    }

    public void instantiateColors() {
        this.exited = this.getBackground();
        if (!this.exited.equals(Color.white)) {
            this.entered = this.exited.brighter();
        } else {
            this.entered = this.exited.darker();
        }

    }

    public void over() {
        this.setBackground(this.entered);
    }

    public void exit() {
        this.setBackground(this.exited);
    }

    public Color getEntered() {
        return this.entered;
    }

    public void setEntered(Color entered) {
        this.entered = entered;
    }

    public Color getExited() {
        return this.exited;
    }

    public void setExited(Color exited) {
        this.exited = exited;
    }

    private class TileHandler extends MouseAdapter {
        private TileHandler() {
        }

        public void mouseExited(MouseEvent e) {
            Tile.this.exit();
        }

        public void mouseEntered(MouseEvent e) {
            Tile.this.over();
        }
    }
}