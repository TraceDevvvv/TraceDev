package com.example.smos;

/**
 * Presentation Layer View for displaying teaching details.
 * Responsible for rendering information to the user.
 */
public class TeachingDetailView {
    /**
     * Displays the details of a teaching using a DTO.
     * This method implements the success path interaction from the sequence diagram.
     * @param dto The TeachingDto containing details to display.
     */
    public void displayTeachingDetails(TeachingDto dto) {
        System.out.println("\n--- Teaching Details ---");
        if (dto != null) {
            System.out.println("ID: " + dto.getId());
            System.out.println("Name: " + dto.getName());
            System.out.println("Course Code: " + dto.getCourseCode());
            System.out.println("Teacher: " + dto.getTeacherName());
            System.out.println("Semester: " + dto.getSemester());
            System.out.println("------------------------");
            System.out.println("View: showTeachingDetailsScreen(TeachingDto)"); // Administrator view output
        } else {
            System.out.println("No teaching details to display.");
        }
    }

    /**
     * Displays an error message to the user.
     * This method implements the alternative path interaction from the sequence diagram.
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        System.err.println("\n--- Error ---");
        System.err.println("Error displaying teaching details: " + message);
        System.err.println("-------------");
        System.err.println("View: showErrorMessage('" + message + "')"); // Administrator view output
    }
}