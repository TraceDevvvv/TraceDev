package com.example.presentation;

import com.example.domain.DayOfWeek;
import com.example.application.IDeleteDailyMenuUseCase;
import com.example.auth.IAuthenticationService;

/**
 * Controller coordinating between view, use case, and serv.
 */
public class DeleteDailyMenuController {
    private IDeleteDailyMenuUseCase useCase;
    private IAuthenticationService authService;
    private DeleteDailyMenuView view;
    private DayOfWeek selectedDay; // Store selected day for later use

    public DeleteDailyMenuController(IDeleteDailyMenuUseCase useCase,
                                     IAuthenticationService authService,
                                     DeleteDailyMenuView view) {
        this.useCase = useCase;
        this.authService = authService;
        this.view = view;
    }

    /**
     * Handles delete request without a day (initial request).
     */
    public void handleDeleteRequest() {
        // Check authentication
        String currentUser = "operator123"; // Assume current user ID
        if (!authService.isAuthenticated(currentUser)) {
            view.showErrorMessage("Authentication required");
            return;
        }
        view.displayWeekSelectionForm();
    }

    /**
     * Handles delete request with a selected day.
     */
    public void handleDeleteRequest(DayOfWeek day) {
        String currentUser = "operator123";
        if (!authService.isAuthenticated(currentUser)) {
            view.showErrorMessage("Authentication required");
            return;
        }
        this.selectedDay = day;
        useCase.deleteDailyMenu(day);
    }

    /**
     * Called by interactor to request confirmation.
     */
    public void requestConfirmation() {
        view.showConfirmationDialog();
    }

    /**
     * Called by view when operator confirms deletion.
     */
    public void proceedWithDeletion() {
        if (useCase instanceof com.example.application.DeleteDailyMenuInteractor) {
            ((com.example.application.DeleteDailyMenuInteractor) useCase).proceedWithDeletion(selectedDay);
        }
    }

    /**
     * Called by interactor to notify success.
     */
    public void notifySuccess() {
        view.showSuccessMessage();
    }

    /**
     * Called by interactor to notify error.
     */
    public void notifyError(String message) {
        view.showErrorMessage(message);
    }

    /**
     * Called by view when operator cancels deletion.
     */
    public void notifyCancellation() {
        view.showCancellationMessage();
    }

    /**
     * Called by view to show cancellation message (as per sequence diagram).
     */
    public void showCancellation() {
        view.showCancellationMessage();
    }

    /**
     * Shows error message as per sequence diagram m26 and m34.
     */
    public void showsErrorMessage(String message) {
        view.showErrorMessage(message);
    }

    /**
     * Shows confirmation popup as per message m12.
     */
    public void showsConfirmationPopup() {
        view.showConfirmationDialog();
    }

    /**
     * Displays success message as per message m22.
     */
    public void showsDailyMenuSuccessfullyDeleted() {
        view.showSuccessMessage();
    }

    /**
     * Shows operation cancelled as per message m24.
     */
    public void showsOperationCancelled() {
        view.showCancellationMessage();
    }
}