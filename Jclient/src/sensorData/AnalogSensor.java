/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorData;

import java.util.ArrayList;
import java.util.List;
import java.util.Random;

/**
 *
 * @author Jesse
 */
public class AnalogSensor implements Sensor {

    private String name;
    private List<Integer> values = new ArrayList();
    private String unit;
    private String timestamp;
    private String alarm;
    private int low;
    private int high;
    private String lowAlarm;
    private String highAlarm;
    Random random = new Random();

    public AnalogSensor(boolean save, String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm) {
        this.name = sensorName;
        if (save) {
            values.add(value);
        }
        this.unit = unit;
        this.timestamp = timestamp;
        this.alarm = alarm;
        this.low = low;
        this.high = high;
        this.lowAlarm = lowAlarm;
        this.highAlarm = highAlarm;
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List getValues() {
        return values;
    }
    
    @Override
    public String getTimestamp(){
        return timestamp;
    }

    @Override
    public void update(boolean save, String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm) {
        this.name = sensorName;
        if (save) {
            values.add(value);
        }
        this.unit = unit;
        this.timestamp = timestamp;
        this.alarm = alarm;
        this.low = low;
        this.high = high;
        this.lowAlarm = lowAlarm;
        this.highAlarm = highAlarm;
    }

    @Override
    public void setValue(int value) {
        values.add(value);
    }

    /**
     * @return the alarm
     */
    public String getAlarm() {
        return alarm;
    }
}
