package com.example.etour;

/**
 * Represents the login form UI component.
 * Handles user interactions and displays results.
 */
public class LoginForm {
    public String username;
    public String password;

    /**
     * Activates the login process by checking system access.
     */
    public void activateLogin() {
        // Step 1: Check system access as per sequence diagram.
        checkSystemAccess();
    }

    /**
     * Checks system access by verifying server status.
     */
    private void checkSystemAccess() {
        ConnectionManager connMgr = new ConnectionManager();
        if (connMgr.checkServerStatus()) {
            displayLoginForm();
        } else {
            displayConnectionError();
        }
    }

    /**
     * Submits the filled form data.
     */
    public void submit() {
        // Assumption: This method is called internally after form submission.
        LoginDTO loginDTO = new LoginDTO();
        loginDTO.username = this.username;
        loginDTO.password = this.password;
        submitForm(loginDTO);
    }

    /**
     * Clears the form fields.
     */
    public void clear() {
        this.username = null;
        this.password = null;
    }
    
    /**
     * Clears the form fields (alternative method name as per sequence diagram).
     */
    public void clearForm() {
        clear();
    }

    /**
     * Displays the login form to the user.
     */
    public void displayLoginForm() {
        System.out.println("Login form displayed.");
    }

    /**
     * Handles successful authentication result.
     * @param authResultDTO the authentication result.
     */
    public void handleSuccess(AuthResultDTO authResultDTO) {
        System.out.println("Login successful: " + authResultDTO.message);
        displayWorkArea(authResultDTO.user);
    }

    /**
     * Handles authentication error.
     * @param authResultDTO the authentication result.
     */
    public void handleError(AuthResultDTO authResultDTO) {
        System.out.println("Login error: " + authResultDTO.message);
        displayErrorMessage("Invalid login data");
    }

    /**
     * Handles connection error.
     */
    public void handleConnectionError() {
        System.out.println("Connection error occurred.");
        displayErrorMessage("Connection error");
    }

    /**
     * Displays the work area after successful login.
     * @param userDTO the user data.
     */
    public void displayWorkArea(UserDTO userDTO) {
        System.out.println("Work area displayed for user: " + userDTO.username);
    }

    /**
     * Displays an error message.
     * @param message the error message.
     */
    public void displayErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Called by user to fill and submit form (from sequence diagram).
     * @param username the username.
     * @param password the password.
     */
    public void fillForm(String username, String password) {
        this.username = username;
        this.password = password;
    }

    /**
     * Submits the form data to the controller.
     * @param loginDTO the login data.
     */
    public void submitForm(LoginDTO loginDTO) {
        LoginController controller = new LoginController();
        AuthResultDTO result = controller.handleLoginRequest(loginDTO);
        if (result.success) {
            handleSuccess(result);
        } else {
            handleError(result);
        }
    }

    /**
     * Cancels the login operation.
     */
    public void cancelLogin() {
        clear();
        closeLoginForm();
    }

    /**
     * Closes the login form.
     */
    public void closeLoginForm() {
        System.out.println("Login form closed.");
    }

    /**
     * Displays connection error to user.
     */
    public void displayConnectionError() {
        System.out.println("Connection error displayed to user.");
    }
}