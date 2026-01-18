/*
 * The main entry point for the Agency Operator Feedback Viewer application.
 * This class is responsible for creating and displaying the GUI.
 */
package com.chatdev.app;
import com.chatdev.gui.AgencyOperatorGUI;
import javax.swing.*;
public class Main {
    /**
     * The main method that starts the Java application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(() -> {
            AgencyOperatorGUI gui = new AgencyOperatorGUI(); // Create an instance of the GUI
            gui.setVisible(true); // Make the GUI window visible
        });
    }
}