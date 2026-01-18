package com.example.login;

/**
 * Represents the user interface for the login process.
 * It's responsible for displaying forms, messages, and capturing user input.
 */
public class LoginView {
    private LoginController loginController;

    /**
     * Sets the LoginController for this view.
     * This method is used for dependency injection.
     * @param loginController The controller responsible for handling login logic.
     */
    public void setLoginController(LoginController loginController) {
        this.loginController = loginController;
    }

    /**
     * Simulates the activation of the login feature.
     * As per REQ-1.
     */
    public void activateLogin() {
        System.out.println("\n--- LoginView: Login feature activated ---");
        displayLoginForm();
    }

    /**
     * Displays the login form to the user.
     * In a real UI, this would render HTML, a GUI window, etc.
     */
    public void displayLoginForm() {
        System.out.println("LoginView: Please enter your credentials.");
        System.out.println("   Username:");
        System.out.println("   Password:");
    }

    /**
     * Simulates getting login input from the user.
     * In a real UI, this would read from input fields.
     * This method is conceptual and directly implemented via `fillAndSubmitForm`.
     * @return A LoginCredentials object containing the input.
     */
    public LoginCredentials getLoginInput() {
        // This method's logic is typically handled by the fillAndSubmitForm in this sequence.
        // For direct call if needed, it would block and wait for user input.
        System.out.println("LoginView: (Simulating input retrieval, usually interactive)");
        return null; // Or throw UnsupportedOperationException if it's strictly via fillAndSubmitForm
    }

    /**
     * Simulates a user filling out the form and submitting it.
     * This method orchestrates the creation of LoginCredentials and delegates to the controller.
     * As per REQ-3, REQ-4.
     * @param username The username entered by the user.
     * @param password The password entered by the user.
     */
    public void fillAndSubmitForm(String username, String password) {
        System.out.println("LoginView: User fills form with username '" + username + "' and password '"+ password +"'");
        System.out.println("LoginView: Submitting login form...");
        LoginCredentials credentials = new LoginCredentials(username, password);
        // Delegate to controller
        if (loginController != null) {
            loginController.submitLogin(credentials);
        } else {
            System.err.println("LoginView Error: LoginController not set.");
        }
    }

    /**
     * Displays a success message upon successful login.
     * @param message The success message to display.
     */
    public void displayLoginSuccess(String message) {
        System.out.println("LoginView: --- Login SUCCESS ---");
        System.out.println("LoginView: " + message);
    }

    /**
     * Displays an error message upon failed login.
     * @param errorMessage The error message to display.
     */
    public void displayLoginError(String errorMessage) {
        System.out.println("LoginView: --- Login FAILED ---");
        System.err.println("LoginView: " + errorMessage);
    }

    /**
     * Displays the main work area of the system.
     */
    public void displayWorkArea() {
        System.out.println("LoginView: Navigating to the main work area...");
        System.out.println("LoginView: Welcome to the system!");
    }

    /**
     * Shows a generic login error page, often for unrecoverable errors.
     */
    public void showLoginErrorPage() {
        System.out.println("LoginView: Displaying a generic login error page. Please try again or contact support.");
    }

    /**
     * Simulates a user cancelling the login operation.
     * As per REQ-Cancel.
     */
    public void cancelLogin() {
        System.out.println("LoginView: User initiated login cancellation.");
        if (loginController != null) {
            loginController.cancelLoginRequest();
        } else {
            System.err.println("LoginView Error: LoginController not set.");
        }
    }

    /**
     * Displays a confirmation message after cancellation.
     */
    public void showCancellationConfirmation() {
        System.out.println("LoginView: Login operation cancelled. Returning to previous state.");
    }
}