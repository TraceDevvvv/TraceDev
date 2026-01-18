'''
Main entry point for the Address Management application.
'''
package com.chatdev;
import com.chatdev.gui.AddressListFrame;
import com.chatdev.service.AddressService;
import javax.swing.*;
/**
 * The main class to run the Address Management application.
 * It initializes the address service and launches the GUI.
 */
public class Main {
    /**
     * The main method, which is the entry point for the application.
     * It ensures that all GUI operations are created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety and responsiveness.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Swing applications must create and update GUI components on the Event Dispatch Thread.
        // This prevents potential race conditions and ensures a smooth user interface.
        SwingUtilities.invokeLater(() -> {
            // Initialize the AddressService. This service acts as a data layer,
            // providing mock address data for this demonstration.
            AddressService addressService = new AddressService();
            // Create the main application frame, which displays the list of addresses.
            // This `AddressListFrame` represents the "ViewingLenchIndirizzi" precondition
            // mentioned in the use case.
            AddressListFrame listFrame = new AddressListFrame(addressService);
            // Make the list frame visible to the user.
            listFrame.setVisible(true);
            // Center the initial frame on the screen for better user experience.
            listFrame.setLocationRelativeTo(null);
        });
    }
}