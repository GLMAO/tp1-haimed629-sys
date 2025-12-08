package org.emp.gl.gui.states;

import java.beans.PropertyChangeEvent;

public interface WatchState {
    void onSet();
    void onMode();
    void onTick(PropertyChangeEvent evt);
    String getModeLetter();
}
