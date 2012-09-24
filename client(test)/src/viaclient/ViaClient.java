/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import StringInterpreter.StringParser;
import userInterface.GraphScreen;

/**
 *
 * @author bmalestein
 */
public class ViaClient {

    /**
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        //SocketHandler sockH = new SocketHandler();
        //sockH.openSocket();

        GraphScreen screen = new GraphScreen();
        screen.setVisible(true);
        
        String dbString = "STRT,sensor1,12342,0019";

        StringParser parse = new StringParser();

        System.out.println(parse.validate(dbString));
        
        parse.readSensorValues(dbString);
    }
}
