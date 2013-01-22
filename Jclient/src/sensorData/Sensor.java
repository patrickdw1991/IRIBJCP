/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package sensorData;

import java.util.List;

/**
 *
 * @author Bart
 */
public interface Sensor {

    public String getName();

    public List<Integer> getValues();
    
    public String getAlarm();
    
    public String getTimestamp();
    
    public void update(boolean save, String sensorName, int value, String unit, String timestamp, String alarm,
            int low, int high, String lowAlarm, String highAlarm);
    
    public void setValue(int value);
}
