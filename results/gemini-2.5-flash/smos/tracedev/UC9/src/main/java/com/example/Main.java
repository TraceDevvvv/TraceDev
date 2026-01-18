package com.example;

import java.util.List;

/**
 * Main class to demonstrate the user deletion sequence and system interactions.
 * This acts as the entry point and simulates the Administrator's actions.
 */
public class Main {
    public static void main(String[] args) {
        // --- System Initialization ---
        System.out.println("--- Initializing User Management System ---");

        // Instantiate concrete implementations of interfaces
        SMOSConnectionManager smosConnectionManager = new SMOSConnectionManager();
        SecurityServiceImpl securityService = new SecurityServiceImpl();
        
        // Inject dependencies into the repository
        UserRepositoryImpl userRepository = new UserRepositoryImpl(smosConnectionManager, securityService);
        
        // Inject repository into the service
        UserManagementService userManagementService = new UserManagementService(userRepository);
        
        // Instantiate the view
        UserManagementView userManagementView = new UserManagementView();
        
        // Inject service and view into the controller
        UserManagementController userManagementController = new UserManagementController(userManagementService, userManagementView);

        System.out.println("\\n--- System Initialization Complete ---\\n");

        // --- Scenario 1: Successful User Deletion ---
        System.out.println("\\n--- Scenario 1: Attempting to delete an existing user (U001) successfully ---\\n");
        // Entry Condition: Administrator is logged in and UserDetails for U001 is displayed
        userManagementController.displayUserDetails("U001");
        // Administrator clicks "Delete" button (userId "U001")
        Object result1 = userManagementController.handleDeleteUserRequest("U001");
        if (result1 instanceof List) {
            System.out.println("Main: Scenario 1 - Deletion successful, updated list received from controller.");
        } else if (result1 instanceof String) {
            System.out.println("Main: Scenario 1 - Deletion failed, error message received from controller: " + result1);
        }
        // Exit Condition: Updated user list displayed, U001 is gone.


        // --- Scenario 2: Deleting a non-existent user ---
        System.out.println("\\n--- Scenario 2: Attempting to delete a non-existent user (U999) ---\\n");
        // Entry Condition: Administrator is logged in and UserDetails for U999 is attempted (will show not found)
        userManagementController.displayUserDetails("U999");
        // Administrator clicks "Delete" button (userId "U999")
        Object result2 = userManagementController.handleDeleteUserRequest("U999");
        if (result2 instanceof List) {
            System.out.println("Main: Scenario 2 - Deletion successful, updated list received from controller.");
        } else if (result2 instanceof String) {
            System.out.println("Main: Scenario 2 - Deletion failed, error message received from controller: " + result2);
        }
        // Exit Condition: Error message displayed. User list remains unchanged.
        userManagementView.displayUserList(userManagementService.getAllUsers()); // Show current list to verify


        // --- Scenario 3: Simulate Connection Failure (R9) ---
        System.out.println("\\n--- Scenario 3: Attempting to delete a user (U002) with simulated connection failure ---\\n");
        // Entry Condition: Administrator is logged in, UserDetails for U002 displayed
        userManagementController.displayUserDetails("U002");
        smosConnectionManager.simulateConnectionFailure(true); // Simulate connection interruption
        // Administrator clicks "Delete" button (userId "U002")
        Object result3 = userManagementController.handleDeleteUserRequest("U002");
        if (result3 instanceof List) {
            System.out.println("Main: Scenario 3 - Deletion successful, updated list received from controller.");
        } else if (result3 instanceof String) {
            System.out.println("Main: Scenario 3 - Deletion failed, error message received from controller: " + result3);
        }
        smosConnectionManager.simulateConnectionFailure(false); // Reset connection for further tests
        // Exit Condition: Error message displayed about connection failure. U002 should still be there.
        userManagementView.displayUserList(userManagementService.getAllUsers()); // Show current list to verify


        // --- Scenario 4: Simulate Secure Erase Failure (R10) ---
        System.out.println("\\n--- Scenario 4: Attempting to delete a user (U003) with simulated secure erase failure ---\\n");
        // Entry Condition: Administrator is logged in, UserDetails for U003 displayed
        userManagementController.displayUserDetails("U003");
        securityService.simulateEraseFailure(true); // Simulate secure erase failure
        // Administrator clicks "Delete" button (userId "U003")
        Object result4 = userManagementController.handleDeleteUserRequest("U003");
        if (result4 instanceof List) {
            System.out.println("Main: Scenario 4 - Deletion successful, updated list received from controller.");
        } else if (result4 instanceof String) {
            System.out.println("Main: Scenario 4 - Deletion failed, error message received from controller: " + result4);
        }
        securityService.simulateEraseFailure(false); // Reset erase for further tests
        // Exit Condition: Error message displayed about erase failure. U003 should still be there.
        userManagementView.displayUserList(userManagementService.getAllUsers()); // Show current list to verify
    }
}