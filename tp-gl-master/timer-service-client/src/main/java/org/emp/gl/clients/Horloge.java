

// File: src/main/java/org/emp/gl/clients/Horloge.java
package org.emp.gl.clients;


import java.beans.PropertyChangeEvent;


import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;


/**
 * Horloge listens to the TimerService and prints the time each second.
 * It depends only on the TimerService abstraction.
 */
public class Horloge implements TimerChangeListener {


    private final String name;
    private final TimerService timerService;


    public Horloge(String name, TimerService timerService) {
        this.name = name;
        this.timerService = timerService;
// register as listener
        if (this.timerService != null) {
            this.timerService.addTimeChangeListener(this);
            System.out.println("Horloge " + name + " initialized and registered.");
        }
    }


    public void afficherHeure() {
        if (timerService != null) {
            System.out.printf("%s affiche %02d:%02d:%02d\n", name,
                    timerService.getHeures(),
                    timerService.getMinutes(),
                    timerService.getSecondes());
        }
    }


    @Override
    public void propertyChange(PropertyChangeEvent evt) {
// We only care about seconde changes to print once per second
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }
}