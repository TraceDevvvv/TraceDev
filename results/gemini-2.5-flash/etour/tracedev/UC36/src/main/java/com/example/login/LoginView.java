package com.example.login;

/**
 * Represents the user interface for login, displaying prompts and handling user input.
 * It interacts with the IncorrectLoginUseCaseController to process login attempts and confirmations.
 */
public class LoginView {
    // Association: LoginView has a controller to delegate actions.
    private IncorrectLoginUseCaseController controller;

    /**
     * Constructs a new LoginView with a reference to its controller.
     *
     * @param controller The IncorrectLoginUseCaseController that this view interacts with.
     */
    public LoginView(IncorrectLoginUseCaseController controller) {
        this.controller = controller;
        System.out.println("[LoginView] Initialized with controller.");
    }

    /**
     * Displays a confirmation prompt message to the user.
     *
     * @param message The message to display in the prompt.
     */
    public void showConfirmationPrompt(String message) {
        System.out.println("\n[LoginView] Displaying Confirmation Prompt:");
        System.out.println("----------------------------------------");
        System.out.println("MESSAGE: " + message);
        System.out.println("Please click 'OK' to confirm.");
        System.out.println("----------------------------------------");
        // Simulate display and user interaction
        System.out.println("[LoginView] Displayed confirmation prompt to User.");
    }

    /**
     * Hides all messages displayed on the view, simulating clearing the UI.
     */
    public void hideAllMessages() {
        System.out.println("\n[LoginView] Hiding all messages.");
        System.out.println("[LoginView] UI cleared.");
        // Simulated return to controller
        // LV --> ILC : messagesHidden (implicitly handled by method completion)
    }

    /**
     * Simulates the user clicking the confirm button on a prompt.
     * This method is triggered by the UI (e.g., a button click listener).
     */
    public void onConfirmButtonClicked() {
        System.out.println("\n[LoginView] User clicked 'Confirm' button.");
        // Delegate to the controller to handle the confirmation.
        controller.userConfirmedNotification();
    }

    /**
     * Simulates a user submitting login credentials from the view.
     * This method creates LoginData and passes it to the controller.
     * This method was added to satisfy the SEQD->CD consistency.
     *
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void submitLogin(String username, String password) {
        System.out.println("\n[LoginView] User is submitting login credentials.");
        LoginData credentials = new LoginData(username, password);
        // Delegate the login attempt to the controller.
        controller.handleLoginAttempt(credentials);
    }
}