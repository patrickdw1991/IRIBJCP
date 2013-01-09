/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import sensorData.SensorList;
import userInterface.GraphScreen;

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


        SensorList sensorList = new SensorList();
        //sensorList.init();
        
        GraphScreen screen = new GraphScreen(sensorList);
        screen.setVisible(true);
        
        SocketHandler sockH = new SocketHandler(sensorList);
        sockH.openSocket();

        //Printerhandler print = new PrinterHandler();




    }
}
