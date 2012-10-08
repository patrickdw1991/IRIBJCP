/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StringInterpreter;

import java.util.*;

/**
 *
 * @author Bart
 * @author Chris
 * @author Patrick
 * @author Jesse
 */
public class StringParser {
    
    public Dictionary readSensors(String input) {
        String message = input.substring(1, input.length() -1);
        String regex = ",";
        Dictionary sensors = new Hashtable();
        String[] split = message.split(regex);
        int i = 1;
        int j = 2;
        while (j <= split.length -1) {
            sensors.put(split[i],split[j]);
            i += 2;
            j += 2;
        }
        
        return sensors;
    }
}
