package com.example.presentation;

import com.example.domain.User;
import com.example.adapters.controllers.RoleManagementController;
import com.example.adapters.requests.RoleUpdateRequest;
import com.example.adapters.responses.Confirmation;
import com.example.presentation.handlers.ConnectionInterruptionHandler;
import java.util.ArrayList;
import java.util.List;

/**
 * Form for managing user roles (simulated UI).
 */
public class RoleManagementForm {
    private RoleManagementController controller;
    private ConnectionInterruptionHandler interruptionHandler;
    private User currentUser;
    private List<String> selectedRolesToAssign = new ArrayList<>();
    private List<String> selectedRolesToRemove = new ArrayList<>();

    public RoleManagementForm(RoleManagementController controller, ConnectionInterruptionHandler handler) {
        this.controller = controller;
        this.interruptionHandler = handler;
    }

    /**
     * Creates the role management form (simulated UI initialization).
     */
    public void createRoleManagementForm() {
        System.out.println("Role management form created.");
    }

    /**
     * Displays the form with user details.
     * @param userDetails the user to display
     */
    public void display(User userDetails) {
        this.currentUser = userDetails;
        System.out.println("Displaying role management form for user: " + userDetails.getName());
    }

    /**
     * Simulates the user clicking the "User Roles" button.
     */
    public void clickUserRolesButton() {
        createRoleManagementForm();
        display(currentUser);
    }

    /**
     * Simulates selecting a role for assignment or removal.
     * @param roleId the role ID
     * @param action "assign" or "remove"
     */
    public void selectRole(String roleId, String action) {
        if ("assign".equalsIgnoreCase(action)) {
            selectedRolesToAssign.add(roleId);
        } else if ("remove".equalsIgnoreCase(action)) {
            selectedRolesToRemove.add(roleId);
        }
        updateSelectionUI();
    }

    /**
     * Updates the selection UI (simulated).
     */
    public void updateSelectionUI() {
        System.out.println("UI updated. Selected to assign: " + selectedRolesToAssign);
        System.out.println("Selected to remove: " + selectedRolesToRemove);
    }

    /**
     * Creates a RoleUpdateRequest from current selections.
     * @return the request
     */
    public RoleUpdateRequest createRoleUpdateRequest() {
        return new RoleUpdateRequest(currentUser.getId(), selectedRolesToAssign, selectedRolesToRemove);
    }

    /**
     * Simulates the user clicking the "Send" button.
     */
    public void clickSendButton() {
        RoleUpdateRequest request = createRoleUpdateRequest();
        Confirmation confirmation = controller.handleRoleUpdate(request);
        showConfirmationMessage(confirmation);
    }

    /**
     * Shows a confirmation message to the administrator.
     */
    public void showConfirmationMessage(Confirmation confirmation) {
        if (confirmation.isSuccess()) {
            System.out.println("Success: " + confirmation.getMessage());
        } else {
            System.out.println("Failure: " + confirmation.getMessage());
            System.out.println("Errors: " + confirmation.getValidationErrors());
        }
    }

    /**
     * Simulates the user canceling the operation (interruption).
     */
    public void cancelOperation() {
        controller.cancelProcessing();
        showCancellationMessage();
    }

    /**
     * Shows a cancellation message.
     */
    public void showCancellationMessage() {
        System.out.println("Operation cancelled by administrator.");
    }

    /**
     * Gets the current selected roles (for testing).
     */
    public RoleUpdateRequest getSelectedRoles() {
        return createRoleUpdateRequest();
    }
}