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
public class DigitalSensor implements Sensor {

    private String name;
    private List<Integer> values = new ArrayList();
    private String unit;
    private String timestamp;
    private String alarm;
    Random random = new Random();

    public DigitalSensor(String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm) {
        this.name = sensorName;
        values.add(value);
        this.unit = unit;
        this.timestamp = timestamp;
        this.alarm = alarm;
        
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
    public void update(String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm){
        this.name = sensorName;
        values.add(value);
        this.unit = unit;
        this.timestamp = timestamp;
        this.alarm = alarm;  
    }
    
    @Override
    public void setValue(int value){
        values.add(value);
    }
}
