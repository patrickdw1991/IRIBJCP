/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import StringInterpreter.StringParser;
import java.io.*;
import java.net.*;
import sensorData.SensorList;

/**
 *
 * @author bmalestein
 */
public class SocketHandler {

    private Socket socket = null;
    private PrintWriter out = null;
    private BufferedReader in = null;
    private SensorList sensorList;
    private StringParser parser;

    public SocketHandler(SensorList sensorList) {
        this.sensorList = sensorList;

        parser = new StringParser(sensorList);
    }

    public void openSocket() {
        try {
            socket = new Socket("127.0.0.1", 55555);
            out = new PrintWriter(socket.getOutputStream(), true);
            in = new BufferedReader(new InputStreamReader(socket.getInputStream()));
        } catch (UnknownHostException e) {
            System.err.println("Unreachable host");
            System.exit(1);
        } catch (IOException e) {
            System.err.println("Server offline");
            System.exit(1);
        }
        handShake();
        receiveSensors();
    }


    private void handShake() {
        while (true) {
            try {
                String handShakeIn = in.readLine();
                System.out.println(handShakeIn);
                if (handShakeIn.equals("shakeStart")) {
                    out.println("shake");
                    break;
                }
            } catch (IOException ex) {
                System.out.println("Socket died");
            }
            try {
                Thread.sleep(100);
            } catch (InterruptedException ex) {
                System.out.println("sleep error");
            }
        }

    }

    private void receiveSensors() {
        while (true) {
            try {
                String sensor = in.readLine();
                sensor = sensor.trim();
                if (sensor.length() > 0) {
                    parser.readMessage(sensor);
                    //out.println(sensor.length());
                    
                }

            } catch (IOException ex) {
                System.exit(0);
            }
            try {
                Thread.sleep(10);
            } catch (InterruptedException ex) {
                System.out.println("sleep error");
            }
        }
    }
}
