package org.emp.gl.gui.core.launcher;

import java.util.Random;
import javax.swing.SwingUtilities;

// ==== TP1 Clients ====
import org.emp.gl.gui.clients.CompteARebours;
import org.emp.gl.gui.clients.Horloge;
import org.emp.gl.gui.clients.HorlogeGraphique;

// ==== TP2 Lookup ====
import org.emp.gl.lookup.Lookup;

// ==== TP1/TP2 Timer ====
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.gui.time.service.impl.DummyTimeServiceImpl;

// ==== TP3 Montre ====
import org.emp.gl.gui.WatchViewer;
import org.emp.gl.gui.ButtonViewer;

/**
 * Lance l’ensemble des 3 TP :
 *  - TP1 : horloges + compte à rebours
 *  - TP2 : Lookup + horloge graphique
 *  - TP3 : montre SET / MODE
 */
public class App {

    public static void main(String[] args) throws InterruptedException {

        // ============================
        //   Création du TimerService
        // ============================
        TimerService timerService = new DummyTimeServiceImpl();

        // ======================================
        //   Enregistrement du Timer dans Lookup
        // ======================================
        Lookup.getInstance().subscribeService(TimerService.class, timerService);

        // ====================
        //   TP1 : Horloges
        // ====================
        new Horloge("Console-1");
        new Horloge("Console-2");

        // ==============================
        //   TP1 : Comptes à rebours
        // ==============================
        Random rnd = new Random();
        for (int i = 0; i < 5; i++) {
            int valeurInitiale = 10 + rnd.nextInt(11);   // entre 10 et 20
            new CompteARebours("Cpt" + (i + 1), valeurInitiale);
        }

        // ====================================
        //   TP2 : Horloge graphique Swing
        // ====================================
        SwingUtilities.invokeLater(() -> {
            new HorlogeGraphique("Horloge Graphique");
        });

        // ============================
        //   TP3 : Montre (Watch)
        // ============================
        SwingUtilities.invokeLater(() -> {
            WatchViewer viewer = new WatchViewer();  // afficheur digital
            new ButtonViewer(viewer);               // boutons SET / MODE
        });

        // Le timer continue à tourner
        Thread.sleep(40000);

        System.out.println("Fin du programme.");
    }
}
