package org.emp.gl.gui.clients;

import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.Font;
import java.beans.PropertyChangeEvent;

import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.SwingUtilities;

import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Version graphique de l'horloge pour le TP2 (Lookup).
 */
public class HorlogeGraphique extends JFrame implements TimerChangeListener {

    private final TimerService timerService;
    private final JLabel labelHeure;

    public HorlogeGraphique(String titre) {
        super(titre);

        // Récupération du service via LOOKUP (TP2)
        this.timerService = Lookup.getInstance().getService(TimerService.class);

        // Interface graphique
        labelHeure = new JLabel("--:--:--", JLabel.CENTER);
        labelHeure.setFont(new Font("Consolas", Font.BOLD, 60));
        labelHeure.setForeground(Color.CYAN);

        JPanel panel = new JPanel(new BorderLayout());
        panel.setBackground(Color.BLACK);
        panel.add(labelHeure, BorderLayout.CENTER);

        add(panel);
        setSize(400, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setVisible(true);

        // Abonnement au TimerService
        if (this.timerService != null) {
            this.timerService.addTimeChangeListener(this);
        }
    }

    /** Met à jour l'affichage de l'heure */
    private void afficherHeure() {
        if (timerService != null) {
            String heure = String.format("%02d:%02d:%02d",
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
            labelHeure.setText(heure);
        }
    }

    /** Observer */
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            SwingUtilities.invokeLater(this::afficherHeure);
        }
    }
}
