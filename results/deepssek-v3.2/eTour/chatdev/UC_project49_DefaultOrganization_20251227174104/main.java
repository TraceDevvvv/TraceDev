'''
@file Main.java
@brief Main entry point for the ViewPreferredSite application.
@details This application simulates a Tourist's bookmark viewing system.
         It launches the authentication interface.
@version 1.0
@date 2023-10-15
'''
package project;
public class Main {
    /**
     * Main method to start the application.
     * @param args Command line arguments (not used)
     */
    public static void main(String[] args) {
        // Run GUI in Event Dispatch Thread for thread safety
        javax.swing.SwingUtilities.invokeLater(() -> new LoginFrame());
    }
}