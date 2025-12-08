package org.emp.gl.gui.states;

import java.beans.PropertyChangeEvent;

import org.emp.gl.gui.WatchController;
import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

public class ModeChronometre implements WatchState {

    private final WatchController controller;
    private final TimerService timer;

    private int dixieme = 0;
    private int secondes = 0;

    public ModeChronometre(WatchController c) {
        this.controller = c;
        this.timer = Lookup.getInstance().getService(TimerService.class);
    }

    @Override
    public void onSet() {
        // reset chronomètre
        dixieme = 0;
        secondes = 0;
        controller.getView().setTextPosition1("00");
        controller.getView().setTextSeparator(":");
        controller.getView().setTextPosition2("00");
    }

    @Override
    public void onMode() {
        controller.setState(new ModeSettings(controller));
    }

    @Override
    public void onTick(PropertyChangeEvent evt) {

        if (!TimerChangeListener.DIXEME_DE_SECONDE_PROP.equals(evt.getPropertyName())) {
            return;
        }

        dixieme++;

        if (dixieme == 10) {
            dixieme = 0;
            secondes = (secondes + 1) % 60;
        }

        controller.getView().setTextPosition1(String.format("%02d", secondes));
        controller.getView().setTextSeparator(".");
        controller.getView().setTextPosition2(Integer.toString(dixieme));
    }

    @Override
    public String getModeLetter() {
        return "C";
    }
}
