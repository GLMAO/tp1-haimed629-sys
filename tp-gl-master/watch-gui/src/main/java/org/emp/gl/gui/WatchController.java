package org.emp.gl.gui;

import java.beans.PropertyChangeEvent;

import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.gui.states.WatchState;
import org.emp.gl.gui.states.ModeHeure;

public class WatchController implements TimerChangeListener {

    private final WatchViewer view;
    private WatchState state;

    public WatchController(WatchViewer view) {
        this.view = view;

        // Récupération du TimerService via Lookup (TP2)
        TimerService timer = Lookup.getInstance().getService(TimerService.class);

        if (timer == null) {
            System.err.println("ERREUR: TimerService introuvable dans le Lookup !");
        } else {
            timer.addTimeChangeListener(this);
        }

        // État initial : mode heure
        this.state = new ModeHeure(this);
    }

    public void setState(WatchState newState) {
        this.state = newState;
        view.setModeLetter(newState.getModeLetter());
    }

    public WatchState getState() {
        return state;
    }

    public WatchViewer getView() {
        return view;
    }

    // Bouton MODE
    public void pressMode() {
        if (state != null) {
            state.onMode();
        }
    }

    // Bouton SET
    public void pressSet() {
        if (state != null) {
            state.onSet();
        }
    }

    // Appelé à chaque tick du TimerService
    @Override
    public void propertyChange(PropertyChangeEvent evt) {
        if (state != null) {
            state.onTick(evt);
        }
    }

    @Override
    public String toString() {
        return "WatchController (état = " + state.getModeLetter() + ")";
    }
}
