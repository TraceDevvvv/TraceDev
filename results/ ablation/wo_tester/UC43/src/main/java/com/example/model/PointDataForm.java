package com.example.model;

import java.util.Map;
import java.util.Scanner;

/**
 * Represents the data form for entering/editing point information.
 * Handles user interaction for data input.
 */
public class PointDataForm {
    private String pointId;
    private Map<String, String> formData;

    // Scanner for user input (simulating UI input)
    private static Scanner scanner = new Scanner(System.in);

    /**
     * Displays form data to the user.
     * @param data The data to display.
     */
    public void displayFormData(Map<String, String> data) {
        System.out.println("=== Point Data Form ===");
        for (Map.Entry<String, String> entry : data.entrySet()) {
            System.out.println(entry.getKey() + ": " + entry.getValue());
        }
        System.out.println("=======================");
    }

    /**
     * Gets user input for the form fields.
     * @return Map containing user-entered data.
     */
    public Map<String, String> getUserInput() {
        System.out.println("Enter new data (leave blank to keep current value):");
        System.out.print("Name: ");
        String name = scanner.nextLine();
        System.out.print("Address: ");
        String address = scanner.nextLine();
        System.out.print("Contact Info: ");
        String contactInfo = scanner.nextLine();
        System.out.print("Operating Hours: ");
        String operatingHours = scanner.nextLine();

        // Only include non-empty values
        Map<String, String> input = new java.util.HashMap<>();
        if (!name.trim().isEmpty()) input.put("name", name);
        if (!address.trim().isEmpty()) input.put("address", address);
        if (!contactInfo.trim().isEmpty()) input.put("contactInfo", contactInfo);
        if (!operatingHours.trim().isEmpty()) input.put("operatingHours", operatingHours);

        return input;
    }

    /**
     * Submits the form (triggers processing of user changes).
     * In a real system, this would be called by a UI event.
     */
    public void submitForm() {
        // This method is a trigger; actual submission is handled by controller.
        System.out.println("Form submitted.");
    }

    /**
     * Shows a confirmation prompt to the user.
     * @return true if user confirms, false otherwise.
     */
    public boolean showConfirmationPrompt() {
        System.out.print("Confirm changes? (yes/no): ");
        String response = scanner.nextLine().trim().toLowerCase();
        return response.equals("yes") || response.equals("y");
    }

    // Getters and setters
    public String getPointId() {
        return pointId;
    }

    public void setPointId(String pointId) {
        this.pointId = pointId;
    }

    public Map<String, String> getFormData() {
        return formData;
    }

    public void setFormData(Map<String, String> formData) {
        this.formData = formData;
    }
}