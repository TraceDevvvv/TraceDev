'''
This file is the main entry point for the Administrator application.
It initializes the Swing GUI on the Event Dispatch Thread (EDT) to ensure
thread safety and proper rendering of UI components.
This class effectively launches the UserListView, fulfilling the initial
setup described in the use case (administrator logged in, viewing user list).
'''
package com.chatdev.adminapp;
import com.chatdev.adminapp.view.UserListView;
import javax.swing.SwingUtilities;
public class AdminApp {
    /**
     * The main method to start the Administrator application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Run the GUI creation on the Event Dispatch Thread (EDT) for thread safety.
        SwingUtilities.invokeLater(() -> {
            // Precondition: The user is logged in as administrator.
            // For this mock application, we assume the administrator is already logged in
            // upon launching this application. No explicit login screen is provided.
            System.out.println("LOG: Administrator assumed logged in.");
            // Create and display the main user list view.
            // Precondition: The system is viewing the list of users in the system.
            UserListView adminFrame = new UserListView();
            adminFrame.setVisible(true);
        });
    }
}