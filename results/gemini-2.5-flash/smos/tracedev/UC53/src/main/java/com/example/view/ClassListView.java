package com.example.view;

import com.example.dto.ClassDTO;
import com.example.service.AuthenticationService;
import com.example.service.ClassService;

import java.util.List;

/**
 * Represents the UI component responsible for displaying a list of classes.
 * It interacts with the ClassService to retrieve data and AuthenticationService for access control.
 */
public class ClassListView {
    private ClassService classService;
    private AuthenticationService authService; // Added for authentication/authorization

    /**
     * Constructor for ClassListView, typically used for dependency injection.
     * @param classService The service for retrieving class data.
     * @param authService The service for user authentication and authorization.
     */
    public ClassListView(ClassService classService, AuthenticationService authService) {
        this.classService = classService;
        this.authService = authService;
    }

    /**
     * Handles the request to view the list of classes.
     * This method implements the full sequence diagram flow, including
     * authentication, authorization, data retrieval, and display.
     *
     * @param userId The ID of the user requesting the list.
     */
    public void requestClassList(String userId) {
        System.out.println("\nClassListView: User '" + userId + "' requests to view classes (m1).");
        System.out.println("ClassListView: Verifying user '"+ userId +"' is logged in and has role 'ATA staff' (m2).");

        // 1. Authenticate user
        System.out.println("ClassListView -> AuthenticationService: isAuthenticated(" + userId + ")");
        boolean isUserAuthenticated = authService.isAuthenticated(userId);
        System.out.println("ClassListView <-- AuthenticationService: isUserAuthenticated = " + isUserAuthenticated);

        if (isUserAuthenticated) {
            // 2. Authorize user for 'ATA staff' role
            String requiredRole = "ATA staff";
            System.out.println("ClassListView -> AuthenticationService: hasRole(" + userId + ", '" + requiredRole + "')");
            boolean hasStaffRole = authService.hasRole(userId, requiredRole);
            System.out.println("ClassListView <-- AuthenticationService: hasStaffRole = " + hasStaffRole);

            if (hasStaffRole) {
                // User is authenticated and authorized, proceed with fetching class list
                System.out.println("ClassListView -> ClassService: getAllClasses()");
                List<ClassDTO> classes = classService.getAllClasses();
                System.out.println("ClassListView <-- ClassService: List<ClassDTO> (size: " + classes.size() + ")");

                displayClassList(classes); // Display the list to the user
                System.out.println("ClassListView --> ATAStaff: displayed list of classes (m13)");
            } else {
                // Authorization failed
                System.out.println("ClassListView --> ATAStaff: display 'Access Denied' message.");
                System.out.println("Access Denied: User '" + userId + "' does not have the required role '" + requiredRole + "'.");
            }
        } else {
            // Authentication failed
            System.out.println("ClassListView --> ATAStaff: display 'Please log in' message.");
            System.out.println("Authentication Failed: Please log in to view classes.");
        }
        System.out.println("ClassListView: Request processing finished for user '" + userId + "'.");
    }

    /**
     * Displays the provided list of ClassDTOs to the user.
     * Corresponds to the "ClassListView -> ClassListView : displayClassList" self-call.
     * In a real UI, this would render to a screen. Here, it prints to console.
     *
     * @param classes The list of ClassDTOs to display.
     */
    public void displayClassList(List<ClassDTO> classes) {
        System.out.println("\nClassListView: Displaying Class List (accurate and complete) (m12):");
        if (classes.isEmpty()) {
            System.out.println("  No classes to display.");
            return;
        }
        for (ClassDTO classDTO : classes) {
            System.out.println("  - ID: " + classDTO.id + ", Name: " + classDTO.name + ", Description: " + classDTO.description);
        }
        System.out.println("ClassListView: Display complete.");
    }
}