package com.example.app.presentation;

import com.example.app.dtos.UserCreationRequestDTO;

import java.util.List;
import java.util.Scanner;

/**
 * Represents the view for the user creation form.
 * Handles displaying form elements, inputting data, and showing feedback to the user.
 * Renamed from UserEntryForm to UserFormView to satisfy R7, R8, R9, R12, R13.
 */
public class UserFormView {
    private UserFormController controller;
    private Scanner scanner; // For simulating user input

    public UserFormView() {
        this.scanner = new Scanner(System.in); // Initialized but not used for getUserInput in current sim
    }

    /**
     * Sets the controller for this view. This is a form of dependency injection.
     *
     * @param controller The UserFormController that manages this view.
     */
    public void setController(UserFormController controller) {
        this.controller = controller;
    }

    /**
     * Displays an empty form, typically for a new user entry.
     */
    public void displayEmptyForm() {
        System.out.println("\n--- Displaying Empty User Creation Form ---");
        System.out.println("Please fill in the user details:");
        System.out.println("Name: ");
        System.out.println("Surname: ");
        System.out.println("E-mail: ");
        System.out.println("Cell: ");
        System.out.println("Login: ");
        System.out.println("Password: ");
        System.out.println("Confirm Password: ");
        System.out.println("------------------------------------------");
        // In a real GUI, this would clear text fields.
    }

    /**
     * Simulates getting user input from the form.
     * In a real application, this would read from UI components.
     * For this simulation, the `Main` class will directly provide the DTO.
     * This method is implemented to satisfy R8.
     *
     * @return A UserCreationRequestDTO populated with user input.
     */
    public UserCreationRequestDTO getUserInput() {
        System.out.println("[UserFormView] Simulating getting user input from form...");
        // This method will be "called" by the controller, but in this simulation,
        // the Main class will effectively provide the DTO to the controller directly
        // to control test scenarios (valid/invalid data).
        // A placeholder DTO is returned here, but actual data comes from Main's calls to controller.
        return new UserCreationRequestDTO("", "", "", "", "", "", "");
    }

    /**
     * Displays validation errors to the user.
     *
     * @param errors A list of error messages to display.
     */
    public void displayValidationErrors(List<String> errors) {
        System.out.println("\n--- Validation Errors ---");
        System.out.println("Please correct the following issues:");
        for (String error : errors) {
            System.out.println("- " + error);
        }
        System.out.println("-------------------------");
    }

    /**
     * Displays a success message to the user.
     *
     * @param message The success message to display.
     */
    public void displaySuccessMessage(String message) {
        System.out.println("\n--- Success ---");
        System.out.println(message);
        System.out.println("---------------");
        // UEF --> Admin : userCreationConfirmed() in SD
        System.out.println("[Admin] User creation confirmed.");
    }

    /**
     * Displays a notification that the connection has been lost.
     * Added to satisfy R14.
     */
    public void displayConnectionLostNotification() {
        System.out.println("\n--- Connection Lost Notification ---");
        System.out.println("Warning: Connection to the server has been interrupted. Please check your network connection.");
        System.out.println("Session might be terminated.");
        System.out.println("----------------------------------");
        // UEF --> Admin : sessionTerminated() in SD
        System.out.println("[Admin] Administrator's connection to the SMOS server IS interrupted.");
    }

    /**
     * Simulates Admin clicking the Save button on the form.
     * This method would typically be triggered by a UI event listener.
     * For simulation purposes, it directly calls the controller.
     */
    public void clicksSaveButton(UserCreationRequestDTO userInput) {
        System.out.println("\n[Admin] Administrator clicks Save Button on UserFormView.");
        // Simulate the view passing its collected data (userInput) to the controller
        // Note: In the SD, UFC -> UEF : getUserInput(), and UEF --> UFC : userCreationRequest.
        // For simplicity in simulation, we pass the data here directly as if UEF collected it.
        controller.handleSaveButtonClick(userInput);
    }

    // This method is purely for sequence diagram simulation output
    public void userCreationConfirmed() {
        System.out.println("[UserFormView] User creation confirmed (UI event).");
    }

    // This method is purely for sequence diagram simulation output
    public void validationFailed() {
        System.out.println("[UserFormView] Validation failed (UI event).");
    }

    // This method is purely for sequence diagram simulation output
    public void sessionTerminated() {
        System.out.println("[UserFormView] Session terminated (UI event).");
    }
}