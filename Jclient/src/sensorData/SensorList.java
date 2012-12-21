/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorData;

import java.util.ArrayList;
import java.util.List;

/**
 *
 * @author Bart
 */
public class SensorList {

    private List<Sensor> sensors = new ArrayList();

    public void init() {
        for (int i = 0; i < 10; i++) {
            Sensor sensor = new Sensor("sensor " + i);
            sensors.add(sensor);
        }
    }

    public String[] getSensorList() {
        String[] arr = new String[sensors.size()];
        for (int i = 0; i < sensors.size(); i++) {
            arr[i] = sensors.get(i).getName();
        }
        return arr;
    }

    public Sensor getSensor(String sensorName) {
        for (int i = 0; i < sensors.size(); i++) {
            if (sensors.get(i).getName().equals(sensorName)) {
                return sensors.get(i);
            }
        }
        return null;
    }
}
