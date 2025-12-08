package org.emp.gl.gui.clients;

import java.beans.PropertyChangeEvent;
import java.util.concurrent.atomic.AtomicInteger;

import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * A countdown that decrements every second.
 * It unregisters itself automatically when it reaches 0.
 */
public class CompteARebours implements TimerChangeListener {

    private final String name;
    private final AtomicInteger remaining;

    private final TimerService timerService;

    public CompteARebours(String name, int startValue) {
        this.name = name;
        this.remaining = new AtomicInteger(startValue);

        // Récupération du TimerService depuis l'annuaire (TP2)
        this.timerService = Lookup.getInstance().getService(TimerService.class);

        if (this.timerService != null) {
            this.timerService.addTimeChangeListener(this);
        }

        System.out.println("CompteARebours " + name + " démarré à " + startValue);
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {

        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {

            int newVal = remaining.decrementAndGet();

            if (newVal >= 0) {
                System.out.println(name + " -> " + newVal);
            }

            // lorsque le compte à rebours atteint 0
            if (newVal <= 0) {
                timerService.removeTimeChangeListener(this);
                System.out.println(name + " terminé et désinscrit.");
            }
        }
    }
}
