/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import java.io.*;
import java.net.*;
import java.util.logging.Level;
import java.util.logging.Logger;

/**
 *
 * @author bmalestein
 */
public class SocketHandler {

    Socket socket = null;
    PrintWriter out = null;
    BufferedReader in = null;

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
//        
//        
//        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
//        String userInput;
//        try {
//            while ((userInput = stdIn.readLine()) != null) {
//                out.println(userInput);
//                System.out.println(in.readLine());
//            }
//
//            out.close();
//            in.close();
//            stdIn.close();
//            socket.close();
//        } catch (IOException ex) {
//            System.out.println("Not connected, reconnecting now...");
//            openSocket();
//        }

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
                    System.out.println(sensor);
                    out.println(sensor.length());
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
}
