/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package StringInterpreter;

/**
 *
 * @author Bart
 * @author Chris
 * @author Patrick
 */
public class StringParser {
    
    public static Boolean validate(String test) {
        if (test.substring(0, 4).equals("STRT") && Integer.parseInt(test.substring(test.length() - 4, test.length())) == test.length()) {
            return true;
        }
        return false;
    }
    
    public void readSensorValues(String test) {
        String values = test.substring(5, test.length() -4);
        String regex = ",";
        String[] split = values.split(regex);
        for (int i = 0; i < split.length; i++) {
            System.out.println("V: "+split[i]);
        }
    
    }
}
