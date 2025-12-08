package org.emp.gl.gui.states;

import java.beans.PropertyChangeEvent;

import org.emp.gl.gui.WatchController;
import org.emp.gl.lookup.Lookup;
import org.emp.gl.timer.service.TimerService;
import org.emp.gl.timer.service.TimerChangeListener;

public class ModeSettings implements WatchState {

    private final WatchController controller;
    private final TimerService timer;

    private int step = 0; // 0 = heures, 1 = minutes

    public ModeSettings(WatchController c) {
        this.controller = c;
        this.timer = Lookup.getInstance().getService(TimerService.class);
    }

    @Override
    public void onSet() {
        if (step == 0) {
            int h = timer.getHeures();
            h = (h + 1) % 24;
            controller.getView().setTextPosition1(String.format("%02d", h));
        } else {
            int m = timer.getMinutes();
            m = (m + 1) % 60;
            controller.getView().setTextPosition2(String.format("%02d", m));
        }
    }

    @Override
    public void onMode() {
        step = (step + 1) % 2;
    }

    @Override
    public void onTick(PropertyChangeEvent evt) {
        if (!TimerChangeListener.SECONDE_PROP.equals(evt.getPropertyName()))
            return;

        controller.getView().setTextPosition1(String.format("%02d", timer.getHeures()));
        controller.getView().setTextPosition2(String.format("%02d", timer.getMinutes()));
        controller.getView().setTextSeparator(":");
    }

    @Override
    public String getModeLetter() {
        return "S";
    }
}
