package com.example.login;

/**
 * Controller for the "Incorrect Login" use case.
 * It orchestrates interactions between the LoginView, LoginService, and ApplicationState.
 */
public class IncorrectLoginUseCaseController {
    // Association: Controller controls the view.
    private LoginView view;
    // Association: Controller uses the login service.
    private LoginService loginService;
    // Association: Controller manages the application state.
    private ApplicationState applicationState;

    /**
     * Constructs an IncorrectLoginUseCaseController.
     *
     * @param view The LoginView associated with this controller.
     * @param service The LoginService to be used for credential validation.
     * @param appState The ApplicationState to manage state saving and recovery.
     */
    public IncorrectLoginUseCaseController(LoginView view, LoginService service, ApplicationState appState) {
        this.view = view;
        this.loginService = service;
        this.applicationState = appState;
        System.out.println("[IncorrectLoginUseCaseController] Initialized.");
    }

    /**
     * Handles a user's login attempt. This method is the entry point for the sequence diagram.
     * It saves the current application state, validates credentials, and handles incorrect login.
     *
     * @param loginData The LoginData object containing the user's input.
     * @return true if login was successful, false otherwise. (Based on SD, it returns false if login is incorrect)
     */
    public boolean handleLoginAttempt(LoginData loginData) {
        System.out.println("\n[IncorrectLoginUseCaseController] Handling login attempt for user: " + loginData.getUsername());

        // As per Class Diagram, call the private method to store the current state.
        // This method then delegates to ApplicationState.
        this.storeCurrentState();

        // ILC -> LS : validateLoginCredentials(loginData)
        boolean isValid = loginService.validateLoginCredentials(loginData); // LS --> ILC : result = false

        // alt Login data is NOT valid (as per Entry Condition in SD)
        if (!isValid) {
            System.out.println("[IncorrectLoginUseCaseController] Login data is NOT valid.");
            // 1. System not that the data entered for the login is not valid.

            // ILC -> LV : showConfirmationPrompt("Incorrect login data...")
            view.showConfirmationPrompt("Incorrect login data. Please confirm you have read this message.");
            // LV --> U : displayConfirmationPrompt (simulated by print in view)

            // The flow now waits for user interaction (U -> LV : onConfirmButtonClicked),
            // which will then call userConfirmedNotification() on this controller.
            return false; // Login attempt failed, waiting for user confirmation
        } else {
            // This path is not taken based on the sequence diagram's entry condition (incorrect login).
            System.out.println("[IncorrectLoginUseCaseController] Login data is valid. (This path is not expected by SD)");
            // Proceed with successful login flow...
            return true;
        }
    }

    /**
     * Called by the LoginView when the user confirms having read a notification.
     * This method continues the sequence diagram after user interaction.
     */
    public void userConfirmedNotification() {
        System.out.println("\n[IncorrectLoginUseCaseController] User confirmed notification.");
        // 3. User confirms the reading of the notification.

        // ILC -> AS : recoverPreviousState()
        applicationState.recoverPreviousState(); // AS --> ILC : stateRecovered

        // ILC -> LV : hideAllMessages()
        view.hideAllMessages(); // LV --> ILC : messagesHidden
        System.out.println("[IncorrectLoginUseCaseController] Use case completed. Control returned to user interaction.");
    }

    /**
     * Stores the current application state.
     * This private method is invoked by the controller before processing a login attempt,
     * as specified in the class diagram.
     */
    private void storeCurrentState() {
        System.out.println("[IncorrectLoginUseCaseController] Storing current application state.");
        // The controller delegates the actual state saving to the ApplicationState.
        // ILC -> AS : saveCurrentState(currentState)
        // Assume "LoginScreenState" as the context to save for this use case,
        // as suggested by the existing logic and the sequence diagram.
        applicationState.saveCurrentState("LoginScreenState"); // AS --> ILC : stateSaved (implicitly by AS method completion)
        System.out.println("[IncorrectLoginUseCaseController] Application state stored.");
    }

    /**
     * Setter for the LoginView. This method is added to resolve a circular
     * dependency during initialization in the Main class for demonstration purposes.
     * While not explicitly in the class diagram, it's necessary for the provided
     * simulation setup to compile and run correctly.
     *
     * @param view The LoginView instance to be set.
     */
    public void setLoginView(LoginView view) {
        if (this.view == null) { // Only set if not already set (e.g., initially null from constructor)
            this.view = view;
            System.out.println("[IncorrectLoginUseCaseController] LoginView instance set via setter.");
        }
    }
}