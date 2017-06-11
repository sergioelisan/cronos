package edu.cronos.util.ui;

/**
 * EDU.Cronos
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseWheelEvent;

public class MagicScroll extends JScrollPane {
    private int sensibility;
    private boolean activemotiongesture;
    private boolean activewheelgesture;
    private MagicScroll.MouseGesture mousegesture;
    private MagicScroll.MouseWheelGesture wheelgesture;

    public MagicScroll() {
        this.sensibility = 45;
        this.mousegesture = new MagicScroll.MouseGesture();
        this.wheelgesture = new MagicScroll.MouseWheelGesture();
        this.setOpaque(false);
        this.getViewport().setOpaque(false);
        this.setBorder(null);
        this.setHorizontalScrollBarPolicy(31);
        this.setVerticalScrollBarPolicy(21);
    }

    public MagicScroll(Boolean activeMotionGesture, Boolean activeWheelGesture) {
        this();
        this.setActiveMotionGesture(activeMotionGesture.booleanValue());
        this.setActiveWheelGesture(activeWheelGesture.booleanValue());
    }

    public MagicScroll(Component comp, Integer sensibility, Boolean activeMouseGesture, Boolean activeWheelGesture) {
        this(activeMouseGesture, activeWheelGesture);
        this.setViewportView(comp);
        this.sensibility = sensibility.intValue();
    }

    public void detectMotion(int x, int y) {
        int previousHorizontalLocation = (int) ((double) this.getSize().width * 0.08D);
        int nextHorizontalLocation = (int) ((double) this.getSize().width * 0.92D);
        int previousVerticalLocation = (int) ((double) this.getSize().height * 0.08D);
        int nextVerticalLocation = (int) ((double) this.getSize().height * 0.92D);
        if (x <= previousHorizontalLocation) {
            this.previousHorizontal();
        } else if (x >= nextHorizontalLocation) {
            this.nextHorizontal();
        }

        if (y <= previousVerticalLocation) {
            this.previousVertical();
        } else if (y >= nextVerticalLocation) {
            this.nextVertical();
        }

    }

    public void detectWheel(int pos) {
        if (pos < 0) {
            this.previousVertical();
        } else if (pos > 0) {
            this.nextVertical();
        }

    }

    public void previousVertical() {
        JScrollBar barra = this.getVerticalScrollBar();
        barra.setValue(barra.getValue() - this.sensibility);
    }

    public void nextVertical() {
        JScrollBar barra = this.getVerticalScrollBar();
        barra.setValue(barra.getValue() + this.sensibility);
    }

    public void previousHorizontal() {
        JScrollBar barra = this.getHorizontalScrollBar();
        barra.setValue(barra.getValue() - this.sensibility);
    }

    public void nextHorizontal() {
        JScrollBar barra = this.getHorizontalScrollBar();
        barra.setValue(barra.getValue() + this.sensibility);
    }

    public boolean isActiveMotionGesture() {
        return this.activemotiongesture;
    }

    public void setActiveMotionGesture(boolean activeMotionGesture) {
        this.activemotiongesture = activeMotionGesture;
        if (activeMotionGesture) {
            this.addMouseMotionListener(this.mousegesture);
        } else {
            this.removeMouseMotionListener(this.mousegesture);
        }

    }

    public boolean isActiveWheelGesture() {
        return this.activewheelgesture;
    }

    public void setActiveWheelGesture(boolean activeWheelGesture) {
        this.activewheelgesture = activeWheelGesture;
        if (activeWheelGesture) {
            this.addMouseWheelListener(this.wheelgesture);
        } else {
            this.removeMouseWheelListener(this.wheelgesture);
        }

    }

    public int getSensibility() {
        return this.sensibility;
    }

    public void setSensibility(int sensibility) {
        this.sensibility = sensibility;
    }

    public class MouseWheelGesture extends MouseAdapter {
        public MouseWheelGesture() {
        }

        public void mouseWheelMoved(MouseWheelEvent e) {
            MagicScroll.this.detectWheel(e.getWheelRotation());
        }
    }

    public class MouseGesture extends MouseAdapter {
        public MouseGesture() {
        }

        public void mouseDragged(MouseEvent e) {
        }

        public void mouseMoved(MouseEvent e) {
            MagicScroll.this.detectMotion(e.getX(), e.getY());
        }
    }
}