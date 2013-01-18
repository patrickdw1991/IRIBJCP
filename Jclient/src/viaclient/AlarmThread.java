/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import java.awt.Toolkit;
import java.util.ArrayList;
import java.util.List;
import sensorData.Sensor;
import sensorData.SensorList;
import userInterface.GraphScreen;

/**
 *
 * @author Bart
 */
public class AlarmThread extends Thread {

    private static final int ANALOG = 0;
    private static final int BINARY = 1;
    SensorList list;
    GraphScreen screen;

    AlarmThread(SensorList list, GraphScreen screen) {
        this.list = list;
        this.screen = screen;
    }

    @Override
    public void run() {
        while (true) {

            List<String> alarmList = new ArrayList();
            String[] sensorNames = list.getSensorNames(ANALOG);
            for (String name : sensorNames) {
                Sensor sensor = list.getSensor(name, ANALOG);
                if (!sensor.getAlarm().equalsIgnoreCase("no alarm")) {
                    alarmList.add("["+sensor.getTimestamp()+"] "+sensor.getAlarm());
                    Toolkit.getDefaultToolkit().beep();
                }
            }

            sensorNames = list.getSensorNames(BINARY);
            for (String name : sensorNames) {
                Sensor sensor = list.getSensor(name, BINARY);
                if (!sensor.getAlarm().equalsIgnoreCase("no alarm")) {
                    alarmList.add("["+sensor.getTimestamp()+"] "+sensor.getAlarm());
                    Toolkit.getDefaultToolkit().beep();
                }
            }


            screen.updateAlarmList(alarmList.toArray(new String[alarmList.size()]));

            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
