package com.example.presentation;

import com.example.application.InsertUserController;
import com.example.application.dto.InsertUserRequest;
import com.example.application.dto.InsertUserResponse;

/**
 * Boundary class representing the new user input form.
 * Displays the form, captures user input, and communicates with the controller.
 */
public class NewUserForm {
    private InsertUserRequest formData;
    private InsertUserController controller;
    private UserListView userListView;

    public NewUserForm(InsertUserController controller, UserListView userListView) {
        this.controller = controller;
        this.userListView = userListView;
    }

    /**
     * Displays the form to the administrator.
     */
    public void displayForm() {
        System.out.println("Displaying New User Form...");
    }

    /**
     * Sets the form data from the provided request object.
     * This method satisfies the requirement Flow of Events 2.
     *
     * @param data the InsertUserRequest containing form field values.
     */
    public void setFormData(InsertUserRequest data) {
        this.formData = data;
        System.out.println("Form data set: " + data);
    }

    /**
     * Retrieves the current form data.
     *
     * @return the InsertUserRequest containing the form data.
     */
    public InsertUserRequest getFormData() {
        return formData;
    }

    /**
     * Shows an error message to the administrator.
     *
     * @param message the error message to display.
     */
    public void showErrorMessage(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Shows a success message to the administrator.
     */
    public void showSuccessMessage() {
        System.out.println("User created successfully!");
    }

    /**
     * Closes the form.
     */
    public void close() {
        System.out.println("Closing New User Form...");
        // Notify the parent view if needed
    }

    /**
     * Handles the scenario when the administrator corrects data.
     */
    public void correctsData() {
        System.out.println("Admin corrects data...");
    }

    /**
     * Called when the administrator clicks "Save" button.
     */
    public void clicksSave() {
        System.out.println("Admin clicks Save...");
    }

    /**
     * Called when the administrator clicks "Save" again after correction.
     */
    public void clicksSaveAgain() {
        System.out.println("Admin clicks Save again...");
    }

    /**
     * Displays a persistent error message.
     * @param errorMessage the error message to display.
     */
    public void displaysPersistentError(String errorMessage) {
        System.out.println("Persistent error: " + errorMessage);
    }

    /**
     * Called when the administrator cancels or closes the form.
     */
    public void cancelClose() {
        System.out.println("Admin cancels/closes form...");
    }
}