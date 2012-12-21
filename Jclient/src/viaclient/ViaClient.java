/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import StringInterpreter.StringParser;
import java.util.*;
import sensorData.SensorList;
import userInterface.GraphScreen;
import Print.Printerhandler;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bmalestein
 * @author Jesse
 */
public class ViaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {

        //SocketHandler sockH = new SocketHandler();
        //sockH.openSocket();
        
        //Printerhandler print = new Printerhandler();

        SensorList sensorList = new SensorList();
        sensorList.init();

        GraphScreen screen = new GraphScreen(sensorList);
        screen.setVisible(true);

        //String dbString = "S,sensor1,12342,sensor2,123123,E";

        //StringParser parse = new StringParser();

        //Dictionary sensors = parse.readMessage(dbString);

        //for (Enumeration e = sensors.keys(); e.hasMoreElements();) {
        //    Object nextElement = e.nextElement();
            //System.out.println("V: " + nextElement + " = " + sensors.get(nextElement));
        //}

    }
}
