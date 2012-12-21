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

    public Dictionary readMessage(String input) {
	String message = input.substring(1, input.length() - 1);
	String regex = ",";
	Dictionary sensors = new Hashtable();
	String[] split = message.split(regex);
	int i = 1;
	int j = 2;
	while (j <= split.length - 1) {
	    sensors.put(split[i], split[j]);
	    i += 2;
	    j += 2;
	}

	return sensors;
    }

    public String[] getSensorNames(String input) {
	String message = input.substring(1, input.length() - 1);
	String regex = ",";
	List<String> names = new ArrayList<String>();
	String[] split = message.split(regex);
	int j = 1;
	while (j <= split.length - 1) {
	    names.add(split[j]);
	    j += 2;
	}

	String[] namesArray = new String[names.size()];
	namesArray = names.toArray(namesArray);

	return namesArray;
    }
}
