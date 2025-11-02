package org.emp.gl.core.launcher;

import java.util.Random;
import javax.swing.SwingUtilities;

import org.emp.gl.clients.CompteARebours;
import org.emp.gl.clients.Horloge;
import org.emp.gl.clients.HorlogeGraphique;
import org.emp.gl.time.service.impl.DummyTimeServiceImpl;
import org.emp.gl.timer.service.TimerService;

/**
 * Main Application.
 */
public class App {

    public static void main(String[] args) throws InterruptedException {
        TimerService timerService = new DummyTimeServiceImpl();

        // Horloges console
        new Horloge("Console-1", timerService);
        new Horloge("Console-2", timerService);

        // Horloge graphique (Swing)
        SwingUtilities.invokeLater(() -> new HorlogeGraphique("Horloge Graphique", timerService));

        // Compte à rebours
        new CompteARebours("Cpt5", 5, timerService);

        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int val = 10 + rnd.nextInt(11);
            new CompteARebours("Cpt" + (i + 1), val, timerService);
        }

        // Laisse le programme tourner un moment
        Thread.sleep(40000);
        System.out.println("Fin du programme.");
    }
}
