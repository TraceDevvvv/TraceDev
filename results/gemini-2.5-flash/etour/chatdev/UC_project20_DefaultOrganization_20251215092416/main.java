'''
Main class to start the Agency Operator application.
This is the entry point for the entire application, creating and showing the main application window.
'''
package com.chatdev.bannerapp;
import com.chatdev.bannerapp.gui.AgencyOperatorApp;
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method, which is the entry point of the Java application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * for thread safety in Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            AgencyOperatorApp app = new AgencyOperatorApp();
            app.setVisible(true);
        });
    }
}