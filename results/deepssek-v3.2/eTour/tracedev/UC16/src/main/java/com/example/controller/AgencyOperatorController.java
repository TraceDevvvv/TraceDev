package com.example.controller;

import com.example.dto.TouristDTO;
import com.example.usecase.DeleteTouristUseCase;
import java.util.List;

/**
 * Controller for agency operator actions.
 */
public class AgencyOperatorController {
    private DeleteTouristUseCase deleteTouristUseCase;
    private boolean isAuthenticated;  // Added for Entry Conditions

    public AgencyOperatorController(DeleteTouristUseCase useCase) {
        this.deleteTouristUseCase = useCase;
        this.isAuthenticated = false;
    }

    // Step 1: Verify login - Entry Condition
    public boolean verifyLogin() {
        // Simulate authentication logic
        isAuthenticated = true;
        return isAuthenticated;
    }

    // Step 2: Search tourists
    public List<TouristDTO> searchTourist() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        return deleteTouristUseCase.getTouristList();
    }

    // Step 4: Request deletion of a tourist
    public void requestDelete(String id) {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // The controller may log or validate the request before proceeding
    }

    // Step 5 & 6: Confirm deletion (simulates confirmation dialog)
    public boolean confirmDelete(String id) {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // In a real scenario, this would involve user interaction.
        // For simplicity, we assume confirmation is always successful.
        deleteTouristUseCase.deleteTourist(id);
        return true;
    }

    // New method to activate SearchTourist() as per sequence diagram m1
    public void activateSearchTourist() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method triggers the search, returning the list (handled by searchTourist)
    }

    // New method to select tourist(id) as per sequence diagram m12
    public void selectTourist(String id) {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method sets the selected tourist for further operations
    }

    // New method to activate disposal feature() as per sequence diagram m14
    public void activateDisposalFeature() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method initiates the deletion feature
    }

    // New method to show confirmation dialog as per sequence diagram m17
    public void showConfirmationDialog() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method would display a dialog in a UI context
    }

    // New method to confirm operation as per sequence diagram m19
    public void confirmOperation() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method confirms the operation, likely calling confirmDelete
    }

    // New method to show success notification as per sequence diagram m30
    public void showSuccessNotification() {
        if (!isAuthenticated) {
            throw new IllegalStateException("Operator not authenticated.");
        }
        // This method would display a success message
    }
}