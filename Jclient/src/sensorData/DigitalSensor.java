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
    private String sensor_unit;
    private int limit_top;
    private int limit_bot;
    private String timestamp;
    Random random = new Random();

    public DigitalSensor(String name, String sensor_unit, int limit_bot, int limit_top) {
        this.name = name;
        this.sensor_unit = sensor_unit;
        this.limit_top = limit_top;
        this.limit_bot = limit_bot;
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
