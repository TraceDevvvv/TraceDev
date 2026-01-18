/*
Controller class for the Logout functionality.
*/
package com.chatdev.controller;
import com.chatdev.gui.LogoutView;
import com.chatdev.session.SessionManager;
import javax.swing.*;
/**
 * Controller class for the Logout functionality.
 * It coordinates interaction between the LogoutView and the SessionManager.
 * This class handles the flow of events for the LOGOUT use case.
 */
public class LogoutController {
    private LogoutView view;
    /**
     * Constructs a LogoutController with a reference to its associated view.
     *
     * @param view The LogoutView instance this controller manages.
     */
    public LogoutController(LogoutView view) {
        this.view = view;
    }
    /**
     * Initiates the logout process.
     * This method implements the "Flow of events" described in the use case.
     */
    public void initiateLogout() {
        // Entry condition check: A registered user has previously made a successful Login.
        if (!SessionManager.isLoggedIn()) {
            view.displayNotLoggedInError();
            return;
        }
        // 1. Access the functionality of disconnection from the system. (Triggered by button click in view)
        // 2. Asks for confirmation of the transaction.
        int confirmResult = view.displayConfirmation("Are you sure you want to log out?", "Confirm Logout");
        // 3. Confirm the request for disconnection.
        if (confirmResult == JOptionPane.YES_OPTION) {
            // 4. Disconnects the Registered User.
            SessionManager.logout();
            // Exit condition: The system shall notify the successful operation logout.
            view.displayLogoutSuccess();
        } else {
            view.displayLogoutCancelled();
        }
    }
}