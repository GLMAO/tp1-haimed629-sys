package org.emp.gl.gui;

public class Chronometre {

    private int seconds = 0;
    private int tenths = 0;
    private boolean running = false;

    public void start() { running = true; }

    public void stop() { running = false; }

    public boolean isRunning() { return running; }

    public void reset() {
        seconds = 0;
        tenths = 0;
    }

    public void tic() {
        if (!running) return;

        tenths++;
        if (tenths == 10) {
            tenths = 0;
            seconds++;
        }
    }

    public int getSeconds() { return seconds; }
    public int getTenths() { return tenths; }
}
