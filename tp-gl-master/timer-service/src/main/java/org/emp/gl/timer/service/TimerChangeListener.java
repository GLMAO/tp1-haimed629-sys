package org.emp.gl.timer.service;


import java.beans.PropertyChangeListener;


/**
 * TimerChangeListener extends PropertyChangeListener so we can use
 * java.beans.PropertyChangeSupport / PropertyChangeEvent mechanism.
 */
public interface TimerChangeListener extends PropertyChangeListener {


    String DIXEME_DE_SECONDE_PROP = "dixieme";
    String SECONDE_PROP = "seconde";
    String MINUTE_PROP = "minute";
    String HEURE_PROP = "heure";


}




