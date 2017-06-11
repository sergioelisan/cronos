package edu.cronos.util.ui;

/**
 * EDU.Cronos
 */

import javax.swing.*;
import java.awt.*;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;

public class PerfectSwitch extends JPanel {
    private JLabel activated;
    private JLabel opt1;
    private JLabel opt2;
    private Color overbackground;
    private Color overforeground;
    private Color inactivebackground;
    private Color inactiveforeground;

    public PerfectSwitch() {
        this.setLayout(new FlowLayout(1, 0, 0));
        this.overbackground = new Color(153, 204, 255);
        this.overforeground = Color.white;
        this.inactivebackground = Color.white;
        this.inactiveforeground = Color.black;
        this.opt1 = new JLabel("ON");
        this.opt1.setHorizontalAlignment(0);
        this.opt1.setVerticalAlignment(0);
        this.opt1.addMouseListener(new PerfectSwitch.SwitchHandler());
        this.opt1.setOpaque(true);
        this.opt2 = new JLabel("OFF");
        this.opt2.setHorizontalAlignment(0);
        this.opt2.setVerticalAlignment(0);
        this.opt2.addMouseListener(new PerfectSwitch.SwitchHandler());
        this.opt2.setBackground(this.inactivebackground);
        this.opt2.setForeground(this.inactiveforeground);
        this.opt2.setOpaque(true);
        this.setPreferredSize(new Dimension(90, 20));
        this.opt1.setPreferredSize(new Dimension(45, 20));
        this.opt2.setPreferredSize(new Dimension(45, 20));
        this.add(this.opt1);
        this.add(this.opt2);
        this.setOpaque(false);
        this.opt1.setBackground(this.overbackground);
        this.opt1.setForeground(this.overforeground);
        this.activated = new JLabel();
    }

    public PerfectSwitch(String opt1, String opt2) {
        this();
        this.opt1.setText(opt1);
        this.opt2.setText(opt2);
    }

    public PerfectSwitch(String o1, String o2, Color ovback, Color ovfront, Color inback, Color infront) {
        this(o1, o2);
        this.overbackground = ovback;
        this.overforeground = ovfront;
        this.inactivebackground = inback;
        this.inactiveforeground = infront;
    }

    public int getSelected() {
        return this.activated.equals(this.opt1) ? 1 : 0;
    }

    public void setSelected(int opcao) {
        if (opcao == 0) {
            this.setActivated(this.opt2);
        } else {
            this.setActivated(this.opt1);
        }

    }

    public String getFirstText() {
        return this.opt1.getText();
    }

    public void setFirstText(String text) {
        this.opt1.setText(text);
    }

    public String getSecondText() {
        return this.opt2.getText();
    }

    public void setSecondText(String text) {
        this.opt2.setText(text);
    }

    public Color getInactivebackground() {
        return this.inactivebackground;
    }

    public void setInactivebackground(Color inactivebackground) {
        if (this.activated.equals(this.opt1)) {
            this.opt2.setBackground(inactivebackground);
        } else {
            this.opt1.setBackground(inactivebackground);
        }

        this.inactivebackground = inactivebackground;
    }

    public Color getInactiveforeground() {
        return this.inactiveforeground;
    }

    public void setInactiveforeground(Color inactiveforeground) {
        if (this.activated.equals(this.opt1)) {
            this.opt2.setForeground(inactiveforeground);
        } else {
            this.opt1.setForeground(inactiveforeground);
        }

        this.inactiveforeground = inactiveforeground;
    }

    public Color getOverbackground() {
        return this.overbackground;
    }

    public void setOverbackground(Color overbackground) {
        if (this.activated.equals(this.opt1)) {
            this.opt1.setForeground(overbackground);
        } else {
            this.opt2.setForeground(overbackground);
        }

        this.overbackground = overbackground;
    }

    public Color getOverforeground() {
        return this.overforeground;
    }

    public void setOverforeground(Color overforeground) {
        if (this.activated.equals(this.opt1)) {
            this.opt1.setForeground(overforeground);
        } else {
            this.opt2.setForeground(overforeground);
        }

        this.overforeground = overforeground;
    }

    public void setClickEvent(MouseListener evt) {
        this.opt1.addMouseListener(evt);
        this.opt2.addMouseListener(evt);
    }

    private void setActivated(JLabel lb) {
        lb.setBackground(this.overbackground);
        lb.setForeground(this.overforeground);
        this.activated.setBackground(this.inactivebackground);
        this.activated.setForeground(this.inactiveforeground);
        this.activated = lb;
    }

    private class SwitchHandler extends MouseAdapter {
        private SwitchHandler() {
        }

        public void mouseClicked(MouseEvent e) {
            JLabel lb = (JLabel) e.getSource();
            PerfectSwitch.this.setActivated(lb);
        }

        public void mouseEntered(MouseEvent e) {
            JLabel lb = (JLabel) e.getSource();
            lb.setBackground(PerfectSwitch.this.overbackground);
            lb.setForeground(PerfectSwitch.this.overforeground);
        }

        public void mouseExited(MouseEvent e) {
            JLabel lb = (JLabel) e.getSource();
            if (!lb.equals(PerfectSwitch.this.activated)) {
                lb.setBackground(PerfectSwitch.this.inactivebackground);
                lb.setForeground(PerfectSwitch.this.inactiveforeground);
            }

        }
    }
}