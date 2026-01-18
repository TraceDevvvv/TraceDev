package com.example.ui;

import com.example.dto.RegisterViewDto;
import com.example.controller.ClassRegisterController;
import com.example.exception.ConnectionException;

/**
 * UI boundary class for class register interactions.
 */
public class ClassRegisterUI {
    private ClassRegisterController controller;
    private boolean loggedInAsTeacher = true; // For demo, assume logged in

    public ClassRegisterUI(ClassRegisterController controller) {
        this.controller = controller;
    }

    public ClassRegisterController getController() {
        return controller;
    }

    public void setController(ClassRegisterController controller) {
        this.controller = controller;
    }

    /**
     * Checks if the current user is logged in as a teacher.
     * As per sequence diagram: isLoggedInAsTeacher()
     */
    public boolean isLoggedInAsTeacher() {
        return loggedInAsTeacher;
    }

    public void setLoggedInAsTeacher(boolean loggedInAsTeacher) {
        this.loggedInAsTeacher = loggedInAsTeacher;
    }

    /**
     * Handles the "Register" button click for a class.
     * As per sequence diagram: Clicks "Register" button for a class
     */
    public void onRegisterButtonClick(String classId) {
        try {
            RegisterViewDto viewDto = controller.viewClassRegister(classId);
            displayRegisterView(viewDto);
        } catch (ConnectionException e) {
            showErrorMessage("Connection to SMOS failed: " + e.getMessage());
        }
    }

    /**
     * Displays the register view.
     * As per sequence diagram: displayRegisterView(viewDto)
     */
    public void displayRegisterView(RegisterViewDto viewDto) {
        System.out.println("Class Register View:");
        System.out.println("Class: " + viewDto.getClassName());
        System.out.println("Date: " + viewDto.getDate());
        System.out.println("Absences: " + viewDto.getAbsences().size());
        System.out.println("Disciplinary Notes: " + viewDto.getDisciplinaryNotes().size());
        System.out.println("Delays: " + viewDto.getDelays().size());
        System.out.println("Justifications: " + viewDto.getJustifications().size());
        // In a real UI, this would update GUI components.
    }

    /**
     * Shows an error message.
     * As per sequence diagram: showErrorMessage(...)
     */
    public void showErrorMessage(String message) {
        System.err.println("Error: " + message);
        // In a real UI, this would display a dialog or notification.
    }
}