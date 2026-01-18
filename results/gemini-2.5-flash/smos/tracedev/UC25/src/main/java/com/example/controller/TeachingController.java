package com.example.controller;

import com.example.exceptions.ServiceException;
import com.example.model.Administrator;
import com.example.model.TeachingDTO;
import com.example.model.ValidationResult;
import com.example.service.AuthenticationService;
import com.example.usecase.EditTeachingUseCase;

import java.util.Collections;
import java.util.List;

/**
 * Controller layer for managing Teaching-related operations,
 * acting as an intermediary between the UI and the business logic (Use Case).
 */
public class TeachingController {
    private final EditTeachingUseCase editTeachingUseCase;
    private final AuthenticationService authenticationService;

    /**
     * Constructs a TeachingController.
     * @param useCase The use case for editing teachings.
     * @param authService The authentication service to check user permissions.
     */
    public TeachingController(EditTeachingUseCase useCase, AuthenticationService authService) {
        this.editTeachingUseCase = useCase;
        this.authenticationService = authService;
    }

    /**
     * Handles saving an edited teaching.
     * Performs authentication check and delegates to the use case.
     * @param editedTeachingDTO The DTO containing the edited teaching data.
     * @return A ValidationResult indicating the success or failure of the save operation.
     * @throws ServiceException if a business logic or underlying data access error occurs.
     */
    public ValidationResult saveTeaching(TeachingDTO editedTeachingDTO) throws ServiceException {
        System.out.println("CONTROLLER: Received request to save teaching: " + editedTeachingDTO.id);

        // Precondition: Administrator is logged in (from Sequence Diagram note)
        if (!authenticationService.isAuthenticated()) {
            System.err.println("CONTROLLER: User not authenticated. Cannot save teaching.");
            throw new ServiceException("Authentication required to save teaching.");
        }
        Administrator currentUser = authenticationService.getCurrentUser();
        System.out.println("CONTROLLER: Authenticated user: " + (currentUser != null ? currentUser.getUsername() : "N/A"));


        // Delegate to the use case for business logic and persistence
        return editTeachingUseCase.editTeaching(editedTeachingDTO);
    }

    /**
     * Refreshes the list of all teachings.
     * @return A list of TeachingDTOs representing all current teachings.
     * @throws ServiceException if an error occurs while retrieving the list.
     */
    public List<TeachingDTO> refreshTeachingsList() throws ServiceException {
        System.out.println("CONTROLLER: Received request to refresh teachings list.");
        return editTeachingUseCase.getTeachingsList();
    }

    /**
     * Handles the cancellation of an edit teaching operation.
     * Currently, this is a no-op at the controller level as the sequence diagram
     * indicates it primarily involves UI feedback.
     */
    public void cancelEditTeaching() {
        System.out.println("CONTROLLER: Cancel edit teaching operation received. No server-side action required.");
        // No specific server-side state change or action is implied by the sequence diagram
        // beyond the controller receiving the call.
    }
}