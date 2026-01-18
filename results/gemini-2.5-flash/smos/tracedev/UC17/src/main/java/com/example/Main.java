package com.example;

import com.example.application.AddressService;
import com.example.application.DefaultAuthService;
import com.example.application.IAuthService;
import com.example.dataaccess.IAddressRepository;
import com.example.dataaccess.SMOSAddressRepository;
import com.example.dataaccess.SMOSClient;
import com.example.presentation.AddressController;
import com.example.presentation.AddressManagementUI;
import com.example.presentation.AddressView;

/**
 * Main application class to demonstrate the integration of all components.
 * This acts as the application's entry point and sets up the dependency graph.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Address Management Application ---");

        // --- Dependency Injection / Object Graph Setup ---

        // Data Access Layer
        SMOSClient smosClient = new SMOSClient();
        IAddressRepository addressRepository = new SMOSAddressRepository(smosClient);

        // Application Layer
        AddressService addressService = new AddressService(addressRepository);
        IAuthService authService = new DefaultAuthService(); // DefaultAuthService implements IAuthService

        // Presentation Layer
        AddressView addressView = new AddressView();
        AddressController addressController = new AddressController(addressService, authService, addressView);

        // UI Layer - for an admin user
        System.out.println("\n--- Scenario 1: Admin User (Successful Flow) ---");
        AddressManagementUI adminUI = new AddressManagementUI(addressController, "adminUser");
        adminUI.showAddressManagementScreen();
        adminUI.onAddressManagementClicked(); // Simulate admin interaction

        // --- Scenario 2: Non-Admin User (Access Denied) ---
        System.out.println("\n--- Scenario 2: Non-Admin User (Access Denied) ---");
        AddressManagementUI regularUserUI = new AddressManagementUI(addressController, "regularUser");
        regularUserUI.showAddressManagementScreen();
        regularUserUI.onAddressManagementClicked(); // Simulate regular user interaction

        // --- Scenario 3: Admin User with Connection Failure ---
        System.out.println("\n--- Scenario 3: Admin User with Simulated Connection Failure ---");
        // Temporarily set SMOSClient to simulate failure
        smosClient.setSimulatedConnectionFailure(true);
        AddressManagementUI adminUIWithError = new AddressManagementUI(addressController, "adminUser");
        adminUIWithError.showAddressManagementScreen();
        adminUIWithError.onAddressManagementClicked(); // Simulate admin interaction with failure
        smosClient.setSimulatedConnectionFailure(false); // Reset for other potential runs

        System.out.println("\n--- Address Management Application Finished ---");
    }
}