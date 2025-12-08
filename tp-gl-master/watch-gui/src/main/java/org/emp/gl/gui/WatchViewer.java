package org.emp.gl.gui;

import javax.swing.*;
import java.awt.*;

public class WatchViewer extends JFrame {

    private final JLabel hh = new JLabel("HH", SwingConstants.CENTER);
    private final JLabel sep = new JLabel(":", SwingConstants.CENTER);
    private final JLabel mm = new JLabel("MM", SwingConstants.CENTER);
    private final JLabel mode = new JLabel("T", SwingConstants.CENTER);

    private WatchController controller;

    public WatchViewer() {
        super("Watch Viewer");

        controller = new WatchController(this);

        setLayout(new GridBagLayout());
        Font big = new Font("Consolas", Font.BOLD, 60);
        Font small = new Font("Consolas", Font.BOLD, 30);

        hh.setFont(big);
        sep.setFont(big);
        mm.setFont(big);
        mode.setFont(small);

        hh.setForeground(Color.CYAN);
        sep.setForeground(Color.CYAN);
        mm.setForeground(Color.CYAN);
        mode.setForeground(Color.GREEN);

        JPanel p = new JPanel(new FlowLayout());
        p.setBackground(Color.BLACK);
        p.add(hh);
        p.add(sep);
        p.add(mm);
        p.add(mode);

        add(p);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setVisible(true);
    }


    public void setTextPosition1(String t) { hh.setText(t); }
    public void setTextSeparator(String t) { sep.setText(t); }
    public void setTextPosition2(String t) { mm.setText(t); }
    public void setModeLetter(String t) { mode.setText(t); }
    public String getCurrentSeparator() { return sep.getText(); }

    public WatchController getController() { return controller; }
}
