package com.example;

/**
 * Represents the login form UI component.
 * It displays a login form, captures user input, and shows error messages.
 */
public class LoginForm {

    // Simulated fields for username and password input
    private String username;
    private String password;

    /**
     * Displays the login form (simulated).
     */
    public void display() {
        System.out.println("Displaying login form...");
    }

    /**
     * Gets the username entered by the user.
     * @return the username
     */
    public String getUsername() {
        return username;
    }

    /**
     * Gets the password entered by the user.
     * @return the password
     */
    public String getPassword() {
        return password;
    }

    /**
     * Shows an error message on the form.
     * @param message the error message to display
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Clears the input fields of the form.
     */
    public void clearFields() {
        username = "";
        password = "";
        System.out.println("Login form fields cleared.");
    }

    /**
     * Simulates a user filling out the form and submitting it.
     * This method is for demonstration to simulate the sequence diagram flow.
     * In a real application, this would be triggered by UI events.
     * @param username the username input
     * @param password the password input
     */
    public void submitForm(String username, String password) {
        this.username = username;
        this.password = password;
        System.out.println("Form submitted with username: " + username);
        // In a real application, this would call the controller
    }
}