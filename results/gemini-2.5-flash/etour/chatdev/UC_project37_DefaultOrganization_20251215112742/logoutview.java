/*
The graphical user interface for the logout functionality.
*/
package com.chatdev.gui;
import com.chatdev.controller.LogoutController;
import com.chatdev.session.SessionManager;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * The graphical user interface for the logout functionality.
 * This view displays the current login status and provides a button to log out.
 */
public class LogoutView extends JFrame {
    private JButton logoutButton;
    private JLabel statusLabel;
    private LogoutController controller; // Controller to handle UI logic and interact with session manager
    /**
     * Constructs a new LogoutView.
     *
     * @param title The title for the JFrame.
     */
    public LogoutView(String title) {
        super(title);
        // Initialize the controller, passing this view to it
        this.controller = new LogoutController(this);
        initUI();
    }
    /**
     * Initializes the user interface components and layout.
     */
    private void initUI() {
        setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10)); // Simple flow layout
        statusLabel = new JLabel("Status: Not Logged In");
        statusLabel.setFont(new Font("Arial", Font.BOLD, 14));
        add(statusLabel);
        logoutButton = new JButton("Logout");
        logoutButton.setFont(new Font("Arial", Font.PLAIN, 12));
        add(logoutButton);
        // Add action listener to the logout button
        logoutButton.addActionListener(e -> controller.initiateLogout());
        // Set frame properties
        setSize(300, 150); // Small fixed size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close application on window close
        setLocationRelativeTo(null); // Center the window on screen
        updateStatus(); // Set initial status
    }
    /**
     * Updates the status label based on the current login state from SessionManager.
     * This method should be called whenever the login status changes.
     */
    public void updateStatus() {
        if (SessionManager.isLoggedIn()) {
            statusLabel.setText("Status: Logged in as " + SessionManager.getCurrentUser().getUsername());
            logoutButton.setEnabled(true); // Enable logout button if logged in
        } else {
            statusLabel.setText("Status: Not Logged In");
            logoutButton.setEnabled(false); // Disable logout button if not logged in
        }
    }
    /**
     * Displays a confirmation dialog to the user.
     *
     * @param message The message to display in the dialog.
     * @param title The title of the dialog.
     * @return An integer representing the user's choice (e.g., JOptionPane.YES_OPTION).
     */
    public int displayConfirmation(String message, String title) {
        return JOptionPane.showConfirmDialog(this, message, title, JOptionPane.YES_NO_OPTION, JOptionPane.QUESTION_MESSAGE);
    }
    /**
     * Displays an informational message dialog to the user.
     *
     * @param message The message to display.
     * @param title The title of the dialog.
     */
    public void displayMessage(String message, String title) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Notifies the user of a successful logout operation and updates the UI.
     */
    public void displayLogoutSuccess() {
        displayMessage("Logout Successful.", "Logout");
        updateStatus(); // Update status label and button state
    }
    /**
     * Notifies the user that the logout operation was cancelled.
     */
    public void displayLogoutCancelled() {
        displayMessage("Logout cancelled.", "Logout");
    }
    /**
     * Notifies the user that they must be logged in to perform a logout.
     */
    public void displayNotLoggedInError() {
        displayMessage("You must be logged in to perform this operation.", "Logout Error");
    }
    /**
     * Sets the visibility of the frame.
     * @param visible true to show the frame, false to hide it.
     */
    public void setViewVisible(boolean visible) {
        setVisible(visible);
    }
}