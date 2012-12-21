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
    private String is_alarm;
    private String timestamp;
    Random random = new Random();

    public AnalogSensor(String name, String is_alarm) {
        this.name = name;
        this.is_alarm = is_alarm;
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        values.add(random.nextInt(10));
        
    }

    @Override
    public String getName() {
        return name;
    }

    @Override
    public List getValues() {
        return values;
    }
}
