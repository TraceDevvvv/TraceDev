package com.example.controllers;

import com.example.dtos.RegistryDetailsDTO;
import com.example.dtos.UserInputDTO;
import com.example.entities.Direction;
import com.example.entities.User;
import com.example.exceptions.SMOSConnectionException;
import com.example.serv.AuthenticationService;
import com.example.serv.RegistryDetailsService;

/**
 * Controller for registry details operations.
 */
public class RegistryDetailsController {
    private RegistryDetailsService registryDetailsService;
    private AuthenticationService authService;
    private User authenticatedUser;

    public RegistryDetailsController(RegistryDetailsService service, AuthenticationService authService) {
        this.registryDetailsService = service;
        this.authService = authService;
        // Assume user is authenticated via session in real scenario
        this.authenticatedUser = new User("user123", "admin", "DIRECTION");
    }

    public RegistryDetailsDTO getRegistryDetails(String academicYear, String className) {
        return registryDetailsService.getRegistryDetails(academicYear, className);
    }

    public void updateJustification(String studentId, String justification) throws SMOSConnectionException {
        registryDetailsService.updateJustification(studentId, justification);
    }

    public void updateDisciplinaryNote(String studentId, String note) throws SMOSConnectionException {
        registryDetailsService.updateDisciplinaryNote(studentId, note);
    }

    public User getAuthenticatedUser() {
        return authenticatedUser;
    }

    // Methods corresponding to sequence diagram messages
    public void showDate(Direction direction) {
        // Implementation for showing date
    }

    public void showListOfStudents(Direction direction) {
        // Implementation for showing list of students
    }

    public void showAbsentPresentStatus(Direction direction) {
        // Implementation for showing absent/present status
    }

    public void showDelayedStatus(Direction direction) {
        // Implementation for showing delayed status
    }

    public void showErrorMessage(Direction direction, String message) {
        // Implementation for showing error message
    }

    public void updateSuccessful(Direction direction) {
        // Implementation for update successful message
    }
}