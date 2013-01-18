/*
 * To change this template, choose Tools | Templates
 * and open the template in the editor.
 */
package viaclient;

import userInterface.GraphScreen;

/**
 *
 * @author Bart
 */
public class GraphThread extends Thread {

    GraphScreen screen;

    public GraphThread(GraphScreen screen) {
        this.screen = screen;
    }

    @Override
    public void run() {
        while (true) {
            screen.update();
            try {
                sleep(1000);
            } catch (InterruptedException ex) {
            }
        }
    }
}
