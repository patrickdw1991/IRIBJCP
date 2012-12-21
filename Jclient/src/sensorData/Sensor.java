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
 * @author Bart
 */
public class Sensor {

    private String name;
    private List<Integer> values = new ArrayList();
    Random random = new Random();

    public Sensor(String name) {
        this.name = name;
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

    public String getName() {
        return name;
    }

    public List<Integer> getValues() {
        return values;
    }
}
