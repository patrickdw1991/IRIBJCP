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

    public String[] getSensorNames(int list) {
        List<Sensor> temp = null;
        switch (list) {
            case 0:
                temp = analoog;
                break;
            case 1:
                temp = digitaal;
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
            case 0:
                for (int i = 0; i < analoog.size(); i++) {
                    if (analoog.get(i).getName().equals(sensorName)) {
                        return analoog.get(i);
                    }
                }
                break;
            case 1:
                for (int i = 0; i < digitaal.size(); i++) {
                    if (digitaal.get(i).getName().equals(sensorName)) {
                        return digitaal.get(i);
                    }
                }
                break;

        }
        return null;
    }

    public List<Sensor> getAnalogSensorlist(){
        return analoog;
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
    public void updateAnalogSensors(boolean save, String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm) {
        boolean update = false;
        for (int i = 0; i < analoog.size(); i++) {
            if (analoog.get(i).getName().equals(sensorName)) {
                //Sensor does exist
                Sensor sensor = analoog.get(i);
                sensor.update(save, sensorName, value, unit, timestamp, alarm, low, high, lowAlarm, highAlarm);
                update = true;
                break;
            }
        }
        if (!update) {
            //sensor not found
            AnalogSensor sensor = new AnalogSensor(save, sensorName, value, unit, timestamp, alarm, low, high, lowAlarm, highAlarm);
            sensor.setValue(value);
            analoog.add(sensor);
        }
    }

    public void updateBinarySensors(boolean save, String sensorName, int value, String unit, String timestamp, String alarm) {
        boolean update = false;
        for (int i = 0; i < digitaal.size(); i++) {
            if (digitaal.get(i).getName().equals(sensorName)) {
                //Sensor does exist
                Sensor sensor = digitaal.get(i);
                sensor.update(save, sensorName, value, unit, timestamp, alarm, 0, 0, null, null);
                update = true;
                break;
            }
        }
        if (!update) {
            //sensor not found
            DigitalSensor sensor = new DigitalSensor(save, sensorName, value, unit, timestamp, alarm, 0, 0, null, null);
            sensor.setValue(value);
            digitaal.add(sensor);
        }
    }

}
