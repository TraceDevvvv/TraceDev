'''
Main.java
This is the entry point of the "ViewClassDetails" application.
It creates and displays the ViewClassDetailsGUI, ensuring that GUI operations
are performed safely on the Event Dispatch Thread (EDT).
'''
package com.chatdev.smos;
import com.chatdev.smos.gui.ViewClassDetailsGUI;
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method to start the application.
     * Ensures the GUI is created and shown on the Event Dispatch Thread (EDT),
     * which is the standard practice for Swing applications to maintain thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            ViewClassDetailsGUI gui = new ViewClassDetailsGUI();
            gui.setVisible(true);
        });
    }
}