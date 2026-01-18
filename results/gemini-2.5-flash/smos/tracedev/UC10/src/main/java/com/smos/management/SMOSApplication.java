package com.smos.management;

import com.smos.management.repository.InMemoryRoleRepository;
import com.smos.management.repository.InMemoryUserRepository;
import com.smos.management.service.UserRoleManagementService;
import com.smos.management.view.UserRolesView;

/**
 * Main application class to demonstrate the User Role Management system.
 * This class orchestrates the creation of dependencies and simulates user interaction,
 * following the flow described in the Sequence Diagram.
 */
public class SMOSApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting SMOS User Role Management Application ---");

        // 1. Setup Repositories (Mock Implementations)
        // These replace the 'User Repository' and 'Role Repository' databases in the sequence diagram.
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryRoleRepository roleRepository = new InMemoryRoleRepository();

        // 2. Setup Service Layer
        // This corresponds to the 'UserRoleManagementService' control in the sequence diagram.
        UserRoleManagementService userRoleManagementService =
                new UserRoleManagementService(userRepository, roleRepository);

        // 3. Setup View Layer
        // This corresponds to the 'User Roles View' participant in the sequence diagram.
        UserRolesView userRolesView = new UserRolesView(userRoleManagementService);

        // --- Scenario 1: Successful Role Management ---
        System.out.println("\n===== Scenario 1: Successful Role Modification =====");
        String userIdToManage = "user123"; // An existing user
        // Sequence Diagram: m2: Admin -> View : clicks "User Roles" button for userId "user123" (simulated by this method call)
        userRolesView.initiateRoleManagement(userIdToManage);
        // The displayRoleManagementForm and handleSubmitRoles are called internally by initiateRoleManagement for simulation.

        // --- Scenario 2: Role Management with Simulated Connection Error ---
        System.out.println("\n===== Scenario 2: Role Modification with Simulated Connection Error =====");
        // Simulate a connection error in the user repository
        userRepository.setSimulateConnectionError(true);
        // Note: The error will occur during the 'save' operation in updateUserRoles
        userRolesView.initiateRoleManagement(userIdToManage);

        // Reset connection error for future operations if any
        userRepository.setSimulateConnectionError(false);

        // --- Scenario 3: Attempt to manage roles for a non-existent user ---
        System.out.println("\n===== Scenario 3: Role Modification for Non-Existent User =====");
        String nonExistentUserId = "user999";
        userRolesView.initiateRoleManagement(nonExistentUserId);
        System.out.println("\n--- SMOS User Role Management Application Finished ---");
    }
}