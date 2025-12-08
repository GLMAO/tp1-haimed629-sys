package org.emp.gl.gui.clients;

import java.beans.PropertyChangeEvent;
import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class Horloge implements TimerChangeListener {

    private final String name;
    private final TimerService timerService;

    public Horloge(String name) {
        this.name = name;

        // RÉCUPÉRATION VIA LOOKUP
        this.timerService = Lookup.getInstance().getService(TimerService.class);

        if (timerService != null) {
            timerService.addTimeChangeListener(this);
            System.out.println("Horloge " + name + " récupère TimerService via Lookup !");
        }
    }

    public void afficherHeure() {
        System.out.printf("%s affiche %02d:%02d:%02d\n", name,
                timerService.getHeures(),
                timerService.getMinutes(),
                timerService.getSecondes());
    }

    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) {
            afficherHeure();
        }
    }
}
