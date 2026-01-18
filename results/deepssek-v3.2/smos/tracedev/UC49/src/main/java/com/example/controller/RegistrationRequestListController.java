package com.example.controller;

import com.example.auth.AuthenticationService;
import com.example.connection.ConnectionManager;
import com.example.dto.RegistrationRequestDTO;
import com.example.service.RegistrationRequestService;
import com.example.view.RegistrationRequestListView;
import java.util.List;

/**
 * Controller for handling registration request list operations.
 * Manages authentication, service calls, and view updates.
 */
public class RegistrationRequestListController {
    private RegistrationRequestService registrationRequestService;
    private RegistrationRequestListView registrationRequestListView;
    private AuthenticationService authenticationService;
    private ConnectionManager connectionManager;

    public RegistrationRequestListController(
            RegistrationRequestService registrationRequestService,
            RegistrationRequestListView registrationRequestListView,
            AuthenticationService authenticationService,
            ConnectionManager connectionManager) {
        this.registrationRequestService = registrationRequestService;
        this.registrationRequestListView = registrationRequestListView;
        this.authenticationService = authenticationService;
        this.connectionManager = connectionManager;
    }

    /**
     * Handles the click event when user requests to view the request list.
     * Satisfies entry condition from sequence diagram.
     * Corresponds to message m1: clicks "View request list"
     */
    public void clicksViewRequestList() {
        // Authentication & Authorization Check
        if (!authenticationService.isAdminLoggedIn()) {
            displayErrorAccessDenied();
            return;
        }

        // Check connection status
        if (!connectionManager.isConnectionActive()) {
            System.out.println("Error: Connection to SMOS server is interrupted.");
            handleConnectionInterrupt();
            return;
        }

        // Retrieve Registration Requests
        List<RegistrationRequestDTO> requests = registrationRequestService.getAllPendingRequests();
        
        // Display Results - corresponds to message m23
        displayRegistrationRequestList(requests);
    }

    /**
     * Handles the click event when user requests to view the request list.
     * Alternative method that calls the new method.
     */
    public void handleViewRequestListClick() {
        clicksViewRequestList();
    }

    /**
     * Displays registration requests (alternative method if needed).
     */
    public void displayRegistrationRequests() {
        clicksViewRequestList();
    }

    /**
     * Handles connection interruptions.
     * Satisfies exit condition (SMOS server interruption).
     */
    public void handleConnectionInterrupt() {
        registrationRequestListView.clearDisplay();
        System.out.println("Connection interrupted. Please check your network connection.");
    }

    /**
     * Simulates user cancel/disconnect action from sequence diagram.
     * Also handles sequence termination.
     */
    public void cancelOrDisconnect() {
        connectionManager.interruptConnection();
        handleConnectionInterrupt();
        userClosesConnection();
    }

    // New method for error message m6: display error "Access denied"
    public void displayErrorAccessDenied() {
        System.out.println("Error: Access denied. Administrator login required.");
    }

    // New method for message m23: display registration request list
    public void displayRegistrationRequestList(List<RegistrationRequestDTO> requests) {
        registrationRequestListView.renderRequestList(requests);
    }

    // New method for note m25: User closes connection, Sequence terminates
    public void userClosesConnection() {
        connectionManager.closeConnection();
        System.out.println("User closes connection. Sequence terminates.");
    }
}