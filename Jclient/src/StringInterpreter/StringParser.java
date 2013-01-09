/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StringInterpreter;

import java.util.*;
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

        for (int i = 0; i < split.length; i++) {
            System.out.println(split[i]);
        }
    }
}
