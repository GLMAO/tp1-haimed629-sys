package org.emp.gl.gui;

import javax.swing.*;
import java.awt.*;

public class ButtonViewer extends JFrame {

    public ButtonViewer(WatchViewer watch) {
        super("Buttons");

        JButton setBtn = new JButton("SET");
        JButton modeBtn = new JButton("MODE");

        setBtn.setFont(new Font("Consolas", Font.BOLD, 30));
        modeBtn.setFont(new Font("Consolas", Font.BOLD, 30));

        // ✔ Correction ici : pressSet() et pressMode()
        setBtn.addActionListener(e -> watch.getController().pressSet());
        modeBtn.addActionListener(e -> watch.getController().pressMode());

        setLayout(new GridLayout(1, 2));
        add(setBtn);
        add(modeBtn);

        setSize(300, 100);
        setLocation(500, 100);
        setVisible(true);
    }
}
