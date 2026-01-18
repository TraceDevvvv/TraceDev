package com.example.ui;

import com.example.controller.TeachingDetailsController;
import com.example.dto.TeachingDetailsDTO;

/**
 * UI boundary for displaying teaching details.
 */
public class TeachingDetailsScreen {
    private TeachingDetailsController controller;

    public TeachingDetailsScreen(TeachingDetailsController controller) {
        this.controller = controller;
    }

    public void displayTeachingDetails(TeachingDetailsDTO details) {
        if (details == null) {
            showError("Teaching details are not available.");
            return;
        }
        // Render the details (in a real UI, this would update UI components)
        System.out.println("Teaching Details:");
        System.out.println("Course Name: " + details.getCourseName());
        System.out.println("Instructor: " + details.getInstructorName());
        System.out.println("Schedule: " + details.getSchedule());
        System.out.println("Location: " + details.getLocation());
        System.out.println("Student Count: " + details.getStudentCount());
    }

    public void showError(String message) {
        System.err.println("Error: " + message);
    }

    // Simulates a user clicking the "Teaching details" button.
    public void onTeachingDetailsButtonClicked(int teachingId) {
        System.out.println("Teaching details button clicked for ID: " + teachingId);
        // sequence diagram message: clicks "Teaching details" button (teachingId)
        controller.loadTeachingDetails(teachingId);
    }

    // sequence diagram message: render details
    public void renderDetails(TeachingDetailsDTO details) {
        // This method performs rendering. It is called by the controller or use case.
        // For now, we can call displayTeachingDetails.
        displayTeachingDetails(details);
    }

    // sequence diagram message: handle error
    public void handleError(String errorMessage) {
        showError(errorMessage);
    }

    // sequence diagram message: display teaching details (return to Admin)
    // This is implicit; the screen displays and the Admin sees it.
    // No explicit method needed for the return.
}