package com.example.presenter;

import com.example.dto.InvalidLoginResponseDTO;
import com.example.service.LoginService;

/**
 * Web implementation of the login presenter.
 * Implements requirement: Presents error notifications and manages UI state recovery.
 */
public class WebLoginPresenter implements ILoginPresenter {
    private LoginService loginService;

    public WebLoginPresenter(LoginService loginService) {
        this.loginService = loginService;
    }

    /**
     * Handles login submission from the user.
     * @param username the username
     * @param password the password
     */
    public void submitLogin(String username, String password) {
        // Step 1: Validate credentials via LoginService
        boolean isValid = loginService.validateCredentials(username, password);
        if (!isValid) {
            // Step 2: If invalid, create request DTO and call interactor
            String errorMessage = "Invalid login credentials.";
            InvalidLoginRequestDTO requestDTO = new InvalidLoginRequestDTO(username, password, errorMessage);
            LoginUseCaseInteractor interactor = new LoginUseCaseInteractor(
                loginService,
                new NotificationService(),
                new StateRecoveryService()
            );
            interactor.setPresenter(this);
            InvalidLoginResponseDTO response = interactor.handleInvalidLogin(requestDTO);
            interactor.returnResponseDTO(response); // m12 return
            presentInvalidLogin(response);
        } else {
            // Valid login flow (not part of this sequence)
            System.out.println("Login successful for user: " + username);
        }
    }

    /**
     * Presents the invalid login response to the user.
     * Implements sequence diagram steps: display notification, request confirmation, restore UI state.
     * @param response the invalid login response DTO
     */
    @Override
    public void presentInvalidLogin(InvalidLoginResponseDTO response) {
        // Step 1: Display invalid login notification
        displayInvalidLoginNotification();
        // Step 2: Request confirmation from the user
        String confirmationMessage = "Invalid login. Confirm you've read this error to return to the login screen.";
        boolean confirmed = requestConfirmation(confirmationMessage);
        if (confirmed && response.requiresConfirmation()) {
            // Step 3: Restore previous UI state using recovery state from response
            restorePreviousUIState(response.getRecoveryState());
            // Step 4: Continue interaction (simulated by printing a message)
            continueInteraction(); // m17 sync message
        }
    }

    /**
     * Requests confirmation from the user.
     * @param message the confirmation message
     * @return true if confirmed, false otherwise
     */
    @Override
    public boolean requestConfirmation(String message) {
        // In a real web app, this might show a modal dialog and await user click.
        System.out.println("Web confirmation requested: " + message);
        // For this example, assume user confirms.
        confirmReading(); // m15 return
        return true;
    }

    /**
     * Displays an invalid login notification to the user.
     */
    public void displayInvalidLoginNotification() {
        System.out.println("Web UI: Invalid login notification displayed.");
    }

    /**
     * Restores the previous UI state.
     * @param state the recovery state object
     */
    public void restorePreviousUIState(Object state) {
        System.out.println("Web UI: Previous UI state restored with state: " + state);
    }

    /**
     * Confirms reading as per sequence diagram message m15 (return from User to Presenter).
     * This method simulates the user's confirmation.
     */
    public void confirmReading() {
        System.out.println("User confirmed reading.");
    }

    /**
     * Continues interaction as per sequence diagram message m17 (sync from User to Presenter).
     * This method simulates the user continuing the interaction.
     */
    public void continueInteraction() {
        System.out.println("User continues interaction.");
    }
}