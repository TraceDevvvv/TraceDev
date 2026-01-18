package com.example.etour.controllers;

import com.example.etour.usecases.DeleteRefreshmentPointInputPort;
import com.example.etour.views.RefreshmentPointView;

/**
 * Controller that handles user actions and coordinates between view and use case.
 */
public class RefreshmentPointController {
    private DeleteRefreshmentPointInputPort useCase;
    private RefreshmentPointView view;

    public RefreshmentPointController(DeleteRefreshmentPointInputPort useCase, RefreshmentPointView view) {
        this.useCase = useCase;
        this.view = view;
    }

    /**
     * Triggered when the agency operator requests deletion.
     * @param id the refreshment point ID.
     */
    public void onDeleteRequest(String id) {
        boolean confirmed = view.showConfirmation("Confirm deletion?");
        if (confirmed) {
            onConfirmDeletion(id);
        } else {
            onCancel();
        }
    }

    /**
     * Called when the user confirms the deletion.
     * @param id the refreshment point ID.
     */
    public void onConfirmDeletion(String id) {
        boolean result = useCase.delete(id);
        // After successful deletion or error, show the refreshed list
        view.showList();
        view.displayRefreshedList();
    }

    /**
     * Called when the user cancels the operation.
     */
    public void onCancel() {
        view.onCancel();
        view.displayCancellationMessage("Operation cancelled by user");
        // After cancellation, show the list as per exit conditions
        view.showList();
        view.displayRefreshedList();
    }
}