package org.emp.gl.gui.time.service.impl;

import java.beans.PropertyChangeSupport;
import java.time.LocalTime;
import java.util.Timer;
import java.util.TimerTask;

import org.emp.gl.timer.service.TimerChangeListener;
import org.emp.gl.timer.service.TimerService;

/**
 * Implémentation du service de temps (simulateur).
 * Utilise PropertyChangeSupport pour notifier les observateurs.
 */
public class DummyTimeServiceImpl implements TimerService {

    private volatile int dixiemeDeSeconde;
    private volatile int minutes;
    private volatile int secondes;
    private volatile int heures;

    private final PropertyChangeSupport pcs = new PropertyChangeSupport(this);

    public DummyTimeServiceImpl() {
        setTimeValues();
        // Planification : 1 tick = 100 ms
        Timer timer = new Timer(true);
        TimerTask task = new TimerTask() {
            @Override
            public void run() {
                timeChanged();
            }
        };
        timer.scheduleAtFixedRate(task, 100, 100);
    }

    private void setTimeValues() {
        LocalTime localTime = LocalTime.now();
        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
        setDixiemeDeSeconde(localTime.getNano() / 100_000_000);
    }

    @Override
    public void addTimeChangeListener(TimerChangeListener pl) {
        pcs.addPropertyChangeListener(pl);
    }

    @Override
    public void removeTimeChangeListener(TimerChangeListener pl) {
        pcs.removePropertyChangeListener(pl);
    }

    private void timeChanged() {
        LocalTime localTime = LocalTime.now();
        setDixiemeDeSeconde(localTime.getNano() / 100_000_000);
        setSecondes(localTime.getSecond());
        setMinutes(localTime.getMinute());
        setHeures(localTime.getHour());
    }

    public void setDixiemeDeSeconde(int newVal) {
        int oldVal = this.dixiemeDeSeconde;
        if (oldVal == newVal) return;
        this.dixiemeDeSeconde = newVal;
        pcs.firePropertyChange(TimerChangeListener.DIXEME_DE_SECONDE_PROP, oldVal, newVal);
    }

    public void setSecondes(int newVal) {
        int oldVal = this.secondes;
        if (oldVal == newVal) return;
        this.secondes = newVal;
        pcs.firePropertyChange(TimerChangeListener.SECONDE_PROP, oldVal, newVal);
    }

    public void setMinutes(int newVal) {
        int oldVal = this.minutes;
        if (oldVal == newVal) return;
        this.minutes = newVal;
        pcs.firePropertyChange(TimerChangeListener.MINUTE_PROP, oldVal, newVal);
    }

    public void setHeures(int newVal) {
        int oldVal = this.heures;
        if (oldVal == newVal) return;
        this.heures = newVal;
        pcs.firePropertyChange(TimerChangeListener.HEURE_PROP, oldVal, newVal);
    }

    @Override
    public int getDixiemeDeSeconde() {
        return dixiemeDeSeconde;
    }

    @Override
    public int getHeures() {
        return heures;
    }

    @Override
    public int getMinutes() {
        return minutes;
    }

    @Override
    public int getSecondes() {
        return secondes;
    }
}
