package com.example;

import com.example.application.InsertUserController;
import com.example.application.dto.InsertUserRequest;
import com.example.entity.Administrator;
import com.example.infrastructure.AuthenticationService;
import com.example.infrastructure.SMOSConnectionManager;
import com.example.infrastructure.UserRepositoryImpl;
import com.example.presentation.NewUserForm;
import com.example.presentation.UserListView;
import com.example.repository.UserRepository;

/**
 * Main class to demonstrate the flow of the Insert New User use case.
 * This class sets up the necessary components and simulates the interactions
 * as described in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("=== Starting Insert New User Use Case ===");

        // Infrastructure layer components
        UserRepository userRepository = new UserRepositoryImpl();
        AuthenticationService authService = new AuthenticationService();
        SMOSConnectionManager connectionMgr = new SMOSConnectionManager();

        // Application layer controller
        InsertUserController controller = new InsertUserController(userRepository, authService, connectionMgr);

        // Presentation layer views
        UserListView userListView = new UserListView(controller);
        NewUserForm newUserForm = new NewUserForm(controller, userListView);

        // Actor
        Administrator admin = new Administrator(1L, "System Admin");

        // --- Preconditions ---
        System.out.println("\n--- Preconditions ---");
        // Administrator is logged in (assumed true)
        boolean sessionValid = authService.validateSession(admin.getId());
        System.out.println("Session valid: " + sessionValid);

        // Administrator views user list (result of use case "viewing theCoutenti")
        userListView.displayUserList();

        // --- Main Flow ---
        System.out.println("\n--- Main Flow ---");
        // Admin clicks "New User"
        userListView.showNewUserForm();

        // Admin fills form fields (simulated)
        InsertUserRequest request = new InsertUserRequest(
                "John",
                "Doe",
                "john.doe@example.com",
                "+1234567890",
                "johndoe",
                "password123",
                "password123"
        );
        newUserForm.setFormData(request);

        // Admin clicks "Save"
        newUserForm.getFormData();
        // The controller's execute method is called (simulated here)
        var response = controller.execute(request);
        if (response.isSuccess()) {
            newUserForm.showSuccessMessage();
            newUserForm.close();
        } else {
            newUserForm.showErrorMessage(response.getErrorMessage());
        }

        // --- Alternative Flow Example ---
        System.out.println("\n--- Alternative Flow (Invalid Data) ---");
        InsertUserRequest invalidRequest = new InsertUserRequest(
                "", // empty name
                "Doe",
                "invalid-email",
                "",
                "",
                "pass",
                "pass2" // mismatch
        );
        var errorResponse = controller.execute(invalidRequest);
        if (!errorResponse.isSuccess()) {
            newUserForm.showErrorMessage(errorResponse.getErrorMessage());
            // Admin corrects data and clicks Save again (simulated)
            System.out.println("Admin corrects data and retries...");
            invalidRequest.setName("Jane");
            invalidRequest.setEmail("jane.doe@example.com");
            invalidRequest.setLogin("janedoe");
            invalidRequest.setConfirmPassword("pass");
            var retryResponse = controller.execute(invalidRequest);
            if (retryResponse.isSuccess()) {
                newUserForm.showSuccessMessage();
            }
        }

        // --- Exit Conditions ---
        System.out.println("\n--- Exit Conditions ---");
        // Connection interruption
        connectionMgr.interrupt();
        // Cancel/close form
        newUserForm.close();
        controller.abort();

        System.out.println("\n=== Use Case Completed ===");
    }
}