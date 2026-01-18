package com.example.view;

import com.example.model.Address;
import com.example.model.Teaching;
import com.example.model.User;
import com.example.service.AddressTeachingManagementService;
import com.example.service.AuthenticationService;
import com.example.service.NavigationService;

import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Adapter layer between the UI (Admin actions) and the core business logic (AddressTeachingManagementService).
 * It translates UI requests into service calls and prepares data for display.
 * It also incorporates dependencies on AuthenticationService and NavigationService as noted in the class diagram,
 * although their methods are not explicitly called in the sequence diagram for this specific use case.
 */
public class AddressViewAdapter {
    private final AddressTeachingManagementService addressTeachingService;
    private final AuthenticationService authenticationService; // Dependency added as per Class Diagram
    private final NavigationService navigationService; // Dependency added as per Class Diagram

    /**
     * Constructs an AddressViewAdapter with its required service dependencies.
     *
     * @param service             The AddressTeachingManagementService for business logic.
     * @param authenticationService The AuthenticationService for user authentication/authorization.
     * @param navigationService   The NavigationService for displaying views.
     */
    public AddressViewAdapter(AddressTeachingManagementService service, AuthenticationService authenticationService, NavigationService navigationService) {
        this.addressTeachingService = service;
        this.authenticationService = authenticationService;
        this.navigationService = navigationService;
    }

    /**
     * Prepares and returns data necessary to display a form for managing teachings of an address.
     * This method acts as the entry point for the Admin's initial click.
     *
     * @param addressId The ID of the address for which to manage teachings.
     * @return A map containing address details, its current teachings, and all available teachings.
     * @throws RuntimeException if the address cannot be found or an internal error occurs.
     */
    public Map<String, Object> displayTeachingsForm(String addressId) {
        System.out.println("AddressViewAdapter: Admin clicks 'Teachings Address' button for address ID: " + addressId);
        // Simulate authentication and authorization checks (as per diagram notes, but not explicitly in SD)
        // User currentUser = new User("adminUser", Collections.singleton("ADMIN"));
        // if (!authenticationService.isAuthenticated(currentUser) || !authenticationService.hasRole(currentUser, "ADMIN")) {
        //     System.out.println("AddressViewAdapter: User not authorized to view teaching management form.");
        //     navigationService.displayView("loginView", Collections.singletonMap("message", "Authorization Required"));
        //     return Collections.emptyMap(); // Or throw an UnauthorizedException
        // }

        try {
            // Delegate to the service to get the necessary data
            Map<String, Object> serviceData = addressTeachingService.getTeachingsForAddress(addressId);

            Address address = (Address) serviceData.get("address");
            List<Teaching> allTeachings = (List<Teaching>) serviceData.get("allTeachings");

            // Prepare data for the form (e.g., current selected teachings, all available options)
            Map<String, Object> formData = new HashMap<>();
            formData.put("address", address);
            formData.put("addressId", address.getId());
            formData.put("addressName", address.getName());
            formData.put("currentTeachingIds", address.getTeachings().stream()
                                                       .map(Teaching::getId)
                                                       .collect(Collectors.toList()));
            formData.put("allAvailableTeachings", allTeachings);

            System.out.println("AddressViewAdapter: Displaying form for address '" + address.getName() + "'");
            return formData;
        } catch (IllegalArgumentException e) {
            System.err.println("AddressViewAdapter: Error displaying form: " + e.getMessage());
            // In a real application, navigate to an error page or show a message
            navigationService.displayView("errorView", Collections.singletonMap("message", "Error: " + e.getMessage()));
            throw new RuntimeException("Could not display teachings form for address ID: " + addressId, e);
        }
    }

    /**
     * Handles the submission of the teaching update form, delegating the actual update to the service.
     * This method is triggered when the Administrator clicks the 'Send' button.
     *
     * @param addressId         The ID of the address to update.
     * @param selectedTeachingIds The list of teaching IDs that should now be associated with the address.
     * @throws RuntimeException if the update fails due to business logic errors.
     */
    public void handleTeachingUpdate(String addressId, List<String> selectedTeachingIds) {
        System.out.println("AddressViewAdapter: Admin submits changes for address ID: " + addressId +
                           ", selected teachings: " + selectedTeachingIds);

        // Simulate authentication and authorization checks (as per diagram notes)
        // User currentUser = new User("adminUser", Collections.singleton("ADMIN"));
        // if (!authenticationService.isAuthenticated(currentUser) || !authenticationService.hasRole(currentUser, "ADMIN")) {
        //     System.out.println("AddressViewAdapter: User not authorized to handle teaching update.");
        //     navigationService.displayView("loginView", Collections.singletonMap("message", "Authorization Required"));
        //     return; // Or throw an UnauthorizedException
        // }

        try {
            // Delegate the update logic to the management service
            addressTeachingService.assignOrRemoveTeachings(addressId, selectedTeachingIds);

            // Upon successful update, confirm to the Admin (e.g., redirect to address details)
            System.out.println("AddressViewAdapter: Teachings for address ID " + addressId + " updated successfully. Confirming to Admin.");
            navigationService.displayView("addressDetailsConfirmation", Collections.singletonMap("addressId", addressId));
        } catch (Exception e) {
            System.err.println("AddressViewAdapter: Failed to update teachings for address ID " + addressId + ": " + e.getMessage());
            // In a real application, display an error message on the form or navigate to an error page
            navigationService.displayView("errorView", Collections.singletonMap("message", "Update failed: " + e.getMessage()));
            throw new RuntimeException("Failed to update teachings for address ID: " + addressId, e);
        }
    }
}