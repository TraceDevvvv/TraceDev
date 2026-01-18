package com.example;

import com.example.model.Teaching;
import com.example.model.User;
import com.example.repository.AddressRepository;
import com.example.repository.IAddressRepository;
import com.example.repository.ITeachingRepository;
import com.example.repository.TeachingRepository;
import com.example.service.AddressTeachingManagementService;
import com.example.service.AuthenticationService;
import com.example.service.NavigationService;
import com.example.uow.IUnitOfWork;
import com.example.uow.UnitOfWork;
import com.example.view.AddressViewAdapter;

import java.util.Arrays;
import java.util.Collections;
import java.util.List;
import java.util.Map;
import java.util.Set;
import java.util.stream.Collectors;

/**
 * Main application class to demonstrate the integration of all components
 * based on the class and sequence diagrams.
 * This class simulates the "Administrator" actor and the application startup.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- Starting Address Teaching Management System Simulation ---");

        // 1. Initialize Infrastructure (Repositories, Unit of Work)
        IAddressRepository addressRepository = new AddressRepository();
        ITeachingRepository teachingRepository = new TeachingRepository();
        IUnitOfWork unitOfWork = new UnitOfWork();

        // 2. Initialize Serv
        AddressTeachingManagementService teachingService = new AddressTeachingManagementService(addressRepository, teachingRepository, unitOfWork);
        AuthenticationService authenticationService = new AuthenticationService();
        NavigationService navigationService = new NavigationService();

        // 3. Initialize View Adapter
        AddressViewAdapter addressViewAdapter = new AddressViewAdapter(teachingService, authenticationService, navigationService);

        // --- Simulate Administrator Use Case: Assign/Remove Teachings from an Address ---
        System.out.println("\n--- Scenario: Administrator assigns or removes teachings from an address ---");

        // Simulate an administrator user (for potential future auth checks)
        User admin = new User("admin", Set.of("ADMIN"));
        System.out.println("\n(Assuming Admin user '" + admin.getUsername() + "' is logged in and authorized)");

        String targetAddressId = "addr001"; // The address we want to modify

        // Step 1 & 2: Admin clicks 'Teachings Address' button -> System displays form
        System.out.println("\n1. Administrator clicks 'Teachings Address' button for address '" + targetAddressId + "'");
        Map<String, Object> formData = addressViewAdapter.displayTeachingsForm(targetAddressId);

        // Extract data from the form to simulate Admin's view
        if (!formData.isEmpty()) {
            com.example.model.Address currentAddress = (com.example.model.Address) formData.get("address");
            List<String> currentTeachingIds = (List<String>) formData.get("currentTeachingIds");
            List<Teaching> allAvailableTeachings = (List<Teaching>) formData.get("allAvailableTeachings");

            System.out.println("\n2. System displays form for Address: " + currentAddress.getName());
            System.out.println("   Current Teachings: " + currentTeachingIds);
            System.out.println("   Available Teachings (IDs): " + allAvailableTeachings.stream().map(Teaching::getId).collect(Collectors.toList()));

            // Simulate Admin selecting new teachings
            // Let's say Admin wants to remove 't101' and add 't103' and 't104', keeping 't102'
            // Original: [t101, t102]
            // New selection: [t102, t103, t104]
            List<String> selectedTeachingIds = Arrays.asList("t102", "t103", "t104"); // Example new selection

            // Step 3 & 4: Admin selects teachings, clicks 'Send' button
            System.out.println("\n3. Administrator selects teachings: " + selectedTeachingIds);
            System.out.println("4. Administrator clicks 'Send' button.");
            try {
                addressViewAdapter.handleTeachingUpdate(targetAddressId, selectedTeachingIds);

                // Step 6: System confirms updated teachings
                System.out.println("\n6. System confirms updated teachings for address '" + targetAddressId + "'");
                // Verify the update by fetching the address again
                System.out.println("\n--- Verification: Fetching address after update ---");
                Map<String, Object> updatedFormData = addressViewAdapter.displayTeachingsForm(targetAddressId);
                com.example.model.Address updatedAddress = (com.example.model.Address) updatedFormData.get("address");
                System.out.println("Updated Address: " + updatedAddress);
                System.out.println("Updated Teachings: " + updatedAddress.getTeachings().stream()
                                                               .map(Teaching::getId)
                                                               .collect(Collectors.toList()));

            } catch (RuntimeException e) {
                System.err.println("Scenario failed: " + e.getMessage());
            }

            // --- Another Scenario: Admin removes all teachings ---
            System.out.println("\n--- Scenario: Administrator removes all teachings from an address ---");
            System.out.println("1. Administrator clicks 'Teachings Address' button for address '" + targetAddressId + "'");
            addressViewAdapter.displayTeachingsForm(targetAddressId); // Redisplay form
            List<String> noTeachings = Collections.emptyList(); // Admin selects no teachings
            System.out.println("\n3. Administrator selects NO teachings.");
            System.out.println("4. Administrator clicks 'Send' button.");
            try {
                addressViewAdapter.handleTeachingUpdate(targetAddressId, noTeachings);
                System.out.println("\n6. System confirms teachings removed for address '" + targetAddressId + "'");

                System.out.println("\n--- Verification: Fetching address after removing all teachings ---");
                Map<String, Object> finalFormData = addressViewAdapter.displayTeachingsForm(targetAddressId);
                com.example.model.Address finalAddress = (com.example.model.Address) finalFormData.get("address");
                System.out.println("Final Address: " + finalAddress);
                System.out.println("Final Teachings: " + finalAddress.getTeachings().stream()
                                                               .map(Teaching::getId)
                                                               .collect(Collectors.toList()));
            } catch (RuntimeException e) {
                System.err.println("Scenario failed: " + e.getMessage());
            }

            // --- Scenario: Attempt to update a non-existent address ---
            System.out.println("\n--- Scenario: Administrator attempts to update a non-existent address ---");
            String nonExistentAddressId = "addr999";
            System.out.println("1. Administrator clicks 'Teachings Address' button for address '" + nonExistentAddressId + "'");
            try {
                addressViewAdapter.displayTeachingsForm(nonExistentAddressId);
            } catch (RuntimeException e) {
                System.out.println("Successfully caught expected error for non-existent address: " + e.getMessage());
            }
            System.out.println("\n3. Administrator attempts to submit changes for non-existent address '" + nonExistentAddressId + "'");
            try {
                addressViewAdapter.handleTeachingUpdate(nonExistentAddressId, Arrays.asList("t101"));
            } catch (RuntimeException e) {
                System.out.println("Successfully caught expected error for non-existent address: " + e.getMessage());
            }


        } else {
            System.err.println("Failed to initialize form data. Exiting.");
        }

        System.out.println("\n--- Address Teaching Management System Simulation Finished ---");
    }
}