/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import java.io.*;
import java.net.*;
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

        BufferedReader stdIn = new BufferedReader(new InputStreamReader(System.in));
        String userInput;
        try {
            while ((userInput = stdIn.readLine()) != null) {
                out.println(userInput);
                System.out.println(in.readLine());
            }

            out.close();
            in.close();
            stdIn.close();
            socket.close();
        } catch (IOException ex) {
            System.out.println("Not connected, reconnecting now...");
            openSocket();
        }

    }
}
