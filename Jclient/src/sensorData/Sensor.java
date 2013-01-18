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
public interface Sensor {

    public String getName();

    public List<Integer> getValues();
    
    public String getAlarm();
    
    public void update(String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm);
    
    public void setValue(int value);
}
