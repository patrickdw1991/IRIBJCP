/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StringInterpreter;

import java.awt.Toolkit;
import sensorData.SensorList;

/**
 *
 * @author Bart
 * @author Chris
 * @author Patrick
 * @author Jesse
 */
public class StringParser {

    private SensorList sensorList;

    public StringParser(SensorList sensorList) {
        this.sensorList = sensorList;
    }

    public void readMessage(String input) {
        System.out.println(input);
        String regex = ";";
        String[] split = input.split(regex);

        if(split[0].equals("A")){
            //String name, int value, String unit, String timestamp, String alarm,
            //int low limit, int high limit, String low alarm, String high alarm
            sensorList.updateAnalogSensors(split[1], Integer.parseInt(split[2]), split[3],
                    split[4], split[6], Integer.parseInt(split[7]), Integer.parseInt(split[8]), 
                    split[9], split[10]);
        }else if(split[0].equals("B")){
            //String name, int value, String unit, String timestamp, String alarm
            sensorList.updateBinarySensors(split[1], Integer.parseInt(split[2]), split[3],
                    split[4], split[6]);
        }
        
        //sensorList.updateSensors(split[0], Integer.parseInt(split[1]), split[2]);
        //Toolkit.getDefaultToolkit().beep();
    }
}
