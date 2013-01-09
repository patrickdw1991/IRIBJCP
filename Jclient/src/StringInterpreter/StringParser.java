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
        String regex = ";";
        String[] split = input.split(regex);

        sensorList.updateSensors(split[0], Integer.parseInt(split[1]), split[2]);
        //Toolkit.getDefaultToolkit().beep();
    }
}
