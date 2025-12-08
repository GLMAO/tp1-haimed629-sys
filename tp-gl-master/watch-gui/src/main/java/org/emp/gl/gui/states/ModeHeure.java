package org.emp.gl.gui.states;

import java.beans.PropertyChangeEvent;

import org.emp.gl.gui.WatchController;
import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

public class ModeHeure implements WatchState {

    private final WatchController controller;
    private final TimerService timer;
    private boolean sepVisible = true;

    public ModeHeure(WatchController c) {
        this.controller = c;
        this.timer = Lookup.getInstance().getService(TimerService.class);
    }

    @Override
    public void onSet() {
        sepVisible = !sepVisible;
        controller.getView().setTextSeparator(sepVisible ? ":" : " ");
    }

    @Override
    public void onMode() {
        controller.setState(new ModeChronometre(controller));
    }

    @Override
    public void onTick(PropertyChangeEvent evt) {
        if (!TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName())) return;

        controller.getView().setTextSeparator(":");
        controller.getView().setTextPosition1(String.format("%02d", timer.getHeures()));
        controller.getView().setTextPosition2(String.format("%02d", timer.getMinutes()));
    }

    @Override
    public String getModeLetter() {
        return "T";
    }
}
