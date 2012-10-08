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
    
    public void readSensorValues(String test) {
        String message = test.substring(1, test.length() -1);
        String regex = ",";
        Dictionary values = new Hashtable();
        String[] split = message.split(regex);
        int i = 1;
        int j = 2;
        while (j <= split.length -1) {
            values.put(split[i],split[j]);
            i += 2;
            j += 2;
        }
        for (Enumeration e = values.keys(); e.hasMoreElements();){
            System.out.println("V: "+e.nextElement());
        }
    
    }
}
