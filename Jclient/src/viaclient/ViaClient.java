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
        SocketHandler sockH = new SocketHandler(sensorList);;

        GraphScreen screen = new GraphScreen(sensorList, sockH);
        screen.setVisible(true);
        AlarmThread thread = new AlarmThread(sensorList, screen);
        thread.start();
        GraphThread thread2 = new GraphThread(screen);
        thread2.start();

        sockH.receiveSensors();

    }
}
