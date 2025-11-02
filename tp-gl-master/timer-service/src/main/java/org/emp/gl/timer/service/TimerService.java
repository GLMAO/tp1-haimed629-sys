// File: src/main/java/org/emp/gl/timer/service/TimerService.java
package org.emp.gl.timer.service;


public interface TimerService extends TimeChangeProvider {


    int getMinutes();


    int getHeures();


    int getSecondes();


    int getDixiemeDeSeconde();
}