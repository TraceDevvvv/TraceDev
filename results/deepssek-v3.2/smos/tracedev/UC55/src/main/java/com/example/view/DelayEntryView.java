package com.example.view;

import com.example.dto.StudentDTO;
import java.util.List;

/**
 * View class for delay entry interface.
 * Handles UI display and user interactions.
 */
public class DelayEntryView {
    public void renderStudentList(List<StudentDTO> students) {
        // Implementation to render student list
        System.out.println("Rendering student list with " + students.size() + " students");
    }

    public void displayDelayCheckbox(String studentId) {
        // Display/update delay checkbox for specific student
        System.out.println("Displaying delay checkbox for student: " + studentId);
    }

    public void showTimeSelection(String studentId) {
        // Show time selection UI for specific student
        System.out.println("Showing time selection for student: " + studentId);
    }

    public void updateDelayDisplay(String studentId, int delayTime) {
        // Update display with selected delay time
        System.out.println("Updating display for student " + studentId + " with delay time: " + delayTime);
    }

    public void showConfirmation() {
        // Show confirmation dialog/message
        System.out.println("Showing confirmation dialog");
    }

    public void resetScreen() {
        // Reset screen to initial state
        System.out.println("Resetting screen to initial state");
    }

    public void showErrorMessage(String message) {
        // Display error message
        System.out.println("Error: " + message);
    }
}