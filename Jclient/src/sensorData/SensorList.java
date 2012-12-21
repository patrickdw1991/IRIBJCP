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
    private List<Sensor> digitaal = new ArrayList();
    private List<Sensor> analoog = new ArrayList();

    public void init() {
        for (int i = 0; i < 2; i++) {
            Sensor sensor = new Sensor("sensor " + i);
            sensors.add(sensor);
        }
        for (int i = 0; i < 5; i++) {
            Sensor sensor = new Sensor("digitaal " + i);
            digitaal.add(sensor);
        }
        for (int i = 0; i < 5; i++) {
            Sensor sensor = new Sensor("analoog " + i);
            analoog.add(sensor);
        }
    }

    public String[] getSensorList(int list) {
        List<Sensor> temp = null;
        switch (list) {
            case 1:
                temp = analoog;
                break;
            case 2:
                temp = digitaal;
                break;
            case 3:
                temp = sensors;
                break;
        }


        String[] arr = new String[temp.size()];

        for (int i = 0; i < temp.size(); i++) {
            arr[i] = temp.get(i).getName();
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
