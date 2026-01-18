package com.example.delaysystem.controller;

import com.example.delaysystem.model.Student;
import com.example.delaysystem.service.DelayRegistrationService;
import com.example.delaysystem.view.DelayRegistrationView;

import java.time.Duration;
import java.util.List;
import java.util.Map;

/**
 * Controller for the delay registration feature.
 * Acts as an intermediary between the View and the Service layers.
 */
public class DelayRegistrationController {
    // View associated with this controller
    protected DelayRegistrationView view;
    // Service layer for business logic
    protected DelayRegistrationService service;
    // Stores the ID of the class currently being processed
    protected String currentClassId;

    /**
     * Constructs a new DelayRegistrationController.
     *
     * @param view The view component for displaying information and receiving input.
     * @param service The service component for business logic operations.
     * @param classId The initial class ID to work with.
     */
    public DelayRegistrationController(DelayRegistrationView view, DelayRegistrationService service, String classId) {
        this.view = view;
        this.service = service;
        this.currentClassId = classId;
        // In a real application, the view might need to set its controller reference.
        // For simplicity here, we assume circular dependency is managed or not an issue.
    }

    /**
     * Sets the current class ID for the controller.
     * This method might be called if the controller needs to switch contexts.
     *
     * @param classId The new class ID.
     */
    public void setCurrentClassId(String classId) {
        this.currentClassId = classId;
    }

    /**
     * Loads the students for the current class and instructs the view to display them.
     * This method is typically called when the registration screen is initialized.
     * Implements part of the "Load Students" sequence.
     */
    public void loadClassStudents() {
        System.out.println("[Controller] Loading students for class: " + currentClassId);
        // As per sequence diagram: Controller -> Service : getStudentsInClass()
        List<Student> students = service.getStudentsInClass(currentClassId);
        // As per sequence diagram: Controller -> View : displayStudents()
        view.displayStudents(students);
    }

    /**
     * Handles the event when a delay checkbox for a student is selected or deselected.
     * This updates the view to activate/deactivate corresponding input fields.
     * Implements part of the "Student Selection/Delay Input" loop in the sequence diagram.
     *
     * @param studentId The ID of the student whose checkbox was toggled.
     * @param isSelected True if the checkbox is selected (delayed), false otherwise.
     */
    public void onDelayCheckboxSelected(String studentId, boolean isSelected) {
        System.out.println("[Controller] Student '" + studentId + "' delay checkbox " + (isSelected ? "selected" : "deselected") + ".");
        // As per sequence diagram: Controller -> View : activateDelayFields()
        view.activateDelayFields(studentId, isSelected);
    }

    /**
     * Handles the event when the "Confirm" button is clicked by the user.
     * This initiates the process of registering the selected delays.
     * Implements the "Confirm Button Click" sequence.
     */
    public void onConfirmButtonClick() {
        System.out.println("[Controller] Confirm button clicked. Attempting to register delays.");
        // As per sequence diagram: Controller -> View : getSelectedDelayDataInput()
        Map<String, Duration> studentDelayInputs = view.getSelectedDelayDataInput();

        if (studentDelayInputs.isEmpty()) {
            view.showErrorMessage("No students selected for delay or no delay times entered.");
            view.showInitialScreen();
            return;
        }

        // As per sequence diagram: Controller -> Service : registerDelays()
        boolean success = service.registerDelays(currentClassId, studentDelayInputs);

        if (success) {
            // As per sequence diagram: Controller -> View : showConfirmationMessage()
            view.showConfirmationMessage("Delay data registered successfully for class " + currentClassId + ".");
        } else {
            // As per sequence diagram: Controller -> View : showErrorMessage() (for connection error example)
            view.showErrorMessage("Failed to register delays for class " + currentClassId + ". Please try again.");
        }
        // As per sequence diagram: Controller -> View : showInitialScreen()
        view.showInitialScreen();
    }

    /**
     * Handles the event when the "Cancel" button is clicked by the user.
     * This aborts the current registration process and returns to an initial state.
     * Implements the "Cancel Operation" optional flow in the sequence diagram (REQ-015).
     */
    public void onCancelButtonClick() {
        System.out.println("[Controller] Cancel button clicked. Aborting delay registration.");
        // As per sequence diagram: Controller -> View : showInitialScreen()
        view.showInitialScreen();
    }
}