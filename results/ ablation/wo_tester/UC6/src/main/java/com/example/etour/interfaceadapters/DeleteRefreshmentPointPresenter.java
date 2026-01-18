package com.example.etour.interfaceadapters;

import com.example.etour.usecases.DeleteRefreshmentPointOutputPort;
import com.example.etour.views.RefreshmentPointView;

/**
 * Presenter that implements the output port and updates the view.
 */
public class DeleteRefreshmentPointPresenter implements DeleteRefreshmentPointOutputPort {
    private RefreshmentPointView view;

    public DeleteRefreshmentPointPresenter(RefreshmentPointView view) {
        this.view = view;
    }

    @Override
    public void presentSuccess(String message) {
        view.showSuccess(message);
        // Also call the new display method for sequence diagram traceability
        view.displaySuccessNotification(message);
    }

    @Override
    public void presentError(String error) {
        view.showError(error);
        // Also call the new display method for sequence diagram traceability
        view.displayErrorMessage(error);
    }
    
    // New method to handle the refreshed list display
    public void presentRefreshedList() {
        view.displayRefreshedList();
    }
    
    // New method to handle cancellation message
    public void presentCancellationMessage(String message) {
        view.displayCancellationMessage(message);
    }
}