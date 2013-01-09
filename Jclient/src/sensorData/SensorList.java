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
            AnalogSensor sensor = new AnalogSensor("floep " + i, "no");
            sensors.add(sensor);
        }
        for (int i = 0; i < 5; i++) {
            DigitalSensor sensor = new DigitalSensor("digitaal " + i, "unit 1", 10, 100);
            digitaal.add(sensor);
        }
        for (int i = 0; i < 5; i++) {
            AnalogSensor sensor = new AnalogSensor("analoog " + i, "no");
            analoog.add(sensor);
        }
    }

    public String[] getSensorNames(int list) {
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

    public Sensor getSensor(String sensorName, int sensorList) {
        switch (sensorList) {
            case 1:
                for (int i = 0; i < analoog.size(); i++) {
                    if (analoog.get(i).getName().equals(sensorName)) {
                        return analoog.get(i);
                    }
                }
                break;
            case 2:
                for (int i = 0; i < digitaal.size(); i++) {
                    if (digitaal.get(i).getName().equals(sensorName)) {
                        return digitaal.get(i);
                    }
                }
                break;
            case 3:
                for (int i = 0; i < sensors.size(); i++) {
                    if (sensors.get(i).getName().equals(sensorName)) {
                        return sensors.get(i);
                    }
                }
                break;
        }
        return null;
    }

    /**
     * Function which updates an existing sensor. If the sensor does not exist
     * it will create a new sensor with these values.
     *
     * @param sensorName String the name of the sensor which need to be updated
     * or added
     * @param value int the value of the sensor
     * @param unit String the unit type of the sensor
     *
     * @return void
     */
    public void updateSensors(String sensorName, int value, String unit) {
        boolean update = false;
        for (int i = 0; i < analoog.size(); i++) {
            if (analoog.get(i).getName().equals(sensorName)) {
                //Sensor does exist
                Sensor sensor = analoog.get(i);
                sensor.update(sensorName, value);
                update = true;
                break;
            }
        }
        if (!update) {
            //sensor not found
            AnalogSensor sensor = new AnalogSensor(sensorName, "no");
            sensor.setValue(value);
            analoog.add(sensor);
        }
    }
}
