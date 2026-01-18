package com.example.adapters.controllers;

import com.example.application.AssignRoleUseCase;
import com.example.application.RemoveRoleUseCase;
import com.example.application.command.AssignRoleCommand;
import com.example.application.command.RemoveRoleCommand;
import com.example.adapters.requests.RoleUpdateRequest;
import com.example.adapters.responses.Confirmation;

import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;

/**
 * Controller handling role update requests.
 */
public class RoleManagementController {
    private AssignRoleUseCase assignRoleUseCase;
    private RemoveRoleUseCase removeRoleUseCase;

    public RoleManagementController(AssignRoleUseCase assignRoleUseCase, RemoveRoleUseCase removeRoleUseCase) {
        this.assignRoleUseCase = assignRoleUseCase;
        this.removeRoleUseCase = removeRoleUseCase;
    }

    /**
     * Handles a role update request.
     * @param request the request containing roles to assign and remove
     * @return a confirmation object
     */
    public Confirmation handleRoleUpdate(RoleUpdateRequest request) {
        List<String> validationErrors = new ArrayList<>();
        try {
            // Process roles to assign
            for (String roleId : request.getRolesToAssign()) {
                AssignRoleCommand command = new AssignRoleCommand(request.getUserId(), roleId);
                assignRoleUseCase.execute(command);
            }

            // Process roles to remove
            for (String roleId : request.getRolesToRemove()) {
                RemoveRoleCommand command = new RemoveRoleCommand(request.getUserId(), roleId);
                removeRoleUseCase.execute(command);
            }

            return new Confirmation(true, "Roles updated successfully.", LocalDateTime.now(), validationErrors);
        } catch (Exception e) {
            validationErrors.add(e.getMessage());
            return new Confirmation(false, "Failed to update roles.", LocalDateTime.now(), validationErrors);
        }
    }

    /**
     * Cancels any ongoing processing (e.g., due to interruption).
     */
    public void cancelProcessing() {
        assignRoleUseCase.rollback();
        removeRoleUseCase.rollback();
    }

    /**
     * Creates a confirmation response (sequence diagram m34).
     */
    public Confirmation createConfirmation() {
        return new Confirmation(true, "Operation successful", LocalDateTime.now(), new ArrayList<>());
    }
}