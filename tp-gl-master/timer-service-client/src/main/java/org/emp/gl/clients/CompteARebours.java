

// File: src/main/java/org/emp/gl/clients/CompteARebours.java
package org.emp.gl.clients;


import java.beans.PropertyChangeEvent;
import java.util.concurrent.atomic.AtomicInteger;


import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


/**
 * A countdown that decrements on each second. It unregisters itself when it reaches 0.
 */
public class CompteARebours implements TimerChangeListener {


    private final String name;
    private final TimerService timerService;
    private final AtomicInteger remaining;


    public CompteARebours(String name, int startValue, TimerService timerService) {
        this.name = name;
        this.remaining = new AtomicInteger(startValue);
        this.timerService = timerService;
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
            if (newVal <= 0) {
// unregister when finished
                timerService.removeTimeChangeListener(this);
                System.out.println(name + " terminé et désinscrit.");
            }
        }
    }
}

