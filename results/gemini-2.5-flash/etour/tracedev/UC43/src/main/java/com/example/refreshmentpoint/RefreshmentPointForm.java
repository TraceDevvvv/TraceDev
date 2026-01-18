package com.example.refreshmentpoint;

import java.util.List;

/**
 * Simulates a UI form for editing RefreshmentPoint details.
 * Handles displaying data, getting user input, and showing messages/errors.
 */
public class RefreshmentPointForm {
    private String currentPointId;
    private String nameInput;
    private String locationInput;
    private String typeInput;
    // Other input fields for refreshment point data

    /**
     * Loads RefreshmentPointDTO data into the form fields.
     *
     * @param dto The DTO containing data to display.
     */
    public void load(RefreshmentPointDTO dto) {
        System.out.println("Form: Loading data for point ID: " + dto.id);
        this.currentPointId = dto.id;
        this.nameInput = dto.name;
        this.locationInput = dto.location;
        this.typeInput = dto.type;
        System.out.println("Form: Data loaded successfully. Displaying current values:");
        System.out.println("  ID: " + this.currentPointId);
        System.out.println("  Name: " + this.nameInput);
        System.out.println("  Location: " + this.locationInput);
        System.out.println("  Type: " + this.typeInput);
    }

    /**
     * Gets the current data from the form fields as a RefreshmentPointDTO.
     *
     * @return A RefreshmentPointDTO with current form data.
     */
    public RefreshmentPointDTO getData() {
        // In a real UI, this would fetch data from actual input fields.
        // Here, we return the internal state.
        System.out.println("Form: Getting data from form fields.");
        return new RefreshmentPointDTO(currentPointId, nameInput, locationInput, typeInput);
    }

    /**
     * Simulates displaying validation errors to the user.
     *
     * @param errors A list of error messages.
     */
    public void displayErrors(List<String> errors) {
        System.err.println("Form: Displaying validation errors:");
        for (String error : errors) {
            System.err.println("  - " + error);
        }
        System.out.println("Form: Please correct the errors and resubmit.");
    }

    /**
     * Simulates displaying a confirmation prompt to the user.
     */
    public void displayConfirmationPrompt() {
        System.out.println("Form: Changes detected. Do you want to confirm these changes? (Y/N)");
        System.out.println("Form: Name: " + nameInput + ", Location: " + locationInput + ", Type: " + typeInput);
    }

    /**
     * Simulates displaying a success notification.
     *
     * @param message The success message to display.
     */
    public void displaySuccessNotification(String message) {
        System.out.println("Form: === SUCCESS === " + message);
    }

    /**
     * Simulates displaying an error message (XC3).
     *
     * @param message The error message to display.
     */
    public void displayError(String message) {
        System.err.println("Form: !!! ERROR !!! " + message);
    }

    /**
     * Simulates operator changing data in the form fields.
     * Corresponds to the 'changeData(newData)' message from Operator to Form in the sequence diagram.
     *
     * @param name The new name for the refreshment point.
     * @param location The new location for the refreshment point.
     * @param type The new type for the refreshment point.
     */
    public void changeData(String name, String location, String type) {
        System.out.println("Form: Operator changing data...");
        this.nameInput = name;
        this.locationInput = location;
        this.typeInput = type;
        System.out.println("Form: Input fields updated. New values: Name='" + name + "', Location='" + location + "', Type='" + type + "'");
    }
}