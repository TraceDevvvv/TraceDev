package com.example.ui;

/**
 * Boundary class for the UI.
 * Renders class details or shows error messages.
 */
public class ClassDetailsView {
    /**
     * Renders the class details from the DTO.
     * @param dto The ClassDetailsDTO containing the class details.
     */
    public void render(ClassDetailsDTO dto) {
        System.out.println("Class Details:");
        System.out.println("ID: " + dto.id);
        System.out.println("Name: " + dto.name);
        System.out.println("Address: " + dto.address);
        System.out.println("School Year: " + dto.schoolYear);
    }

    /**
     * Shows an error message.
     * @param message The error message to display.
     */
    public void showError(String message) {
        System.out.println("Error: " + message);
    }

    /**
     * Handles the click event for "Show class details".
     * @param classId The class ID.
     */
    public void clicksShowClassDetails(String classId) {
        System.out.println("UI: Received click for class details with classId: " + classId);
    }

    /**
     * Renders the details (name, address, school year).
     * @param name The class name.
     * @param address The class address.
     * @param schoolYear The school year.
     */
    public void renderDetails(String name, String address, String schoolYear) {
        System.out.println("UI rendering details: Name=" + name + ", Address=" + address + ", SchoolYear=" + schoolYear);
    }

    /**
     * Displays class details fencing.
     */
    public void displayClassDetailsFencing() {
        System.out.println("Displaying class details fencing.");
    }

    /**
     * Displays an error message.
     * @param errorMessage The error message.
     */
    public void displayErrorMessage(String errorMessage) {
        System.out.println("Displaying error message: " + errorMessage);
    }
}