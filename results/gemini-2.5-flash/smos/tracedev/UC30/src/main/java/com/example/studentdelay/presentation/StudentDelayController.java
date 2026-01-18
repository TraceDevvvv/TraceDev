package com.example.studentdelay.presentation;

import com.example.studentdelay.application.StudentDelayService;
import com.example.studentdelay.dataaccess.StudentDelayDTO;
import com.example.studentdelay.util.ConnectionException;
import com.example.studentdelay.util.ValidationException;

import java.time.LocalDate;
import java.util.List;

/**
 * StudentDelayController acts as an intermediary between the StudentDelayView
 * and the StudentDelayService. It handles user actions, marshals data,
 * and orchestrates responses.
 *
 * Prerequisite: R4 - Use Case "SeveralTetTingloregister" IS completed.
 * (This note refers to a prerequisite for the system, not a specific code implementation detail,
 * so it's documented here but not directly translated into Java code beyond acknowledging it).
 */
public class StudentDelayController {
    private final StudentDelayService studentDelayService;
    private StudentDelayView studentDelayView; // View is updated by controller

    public StudentDelayController(StudentDelayService studentDelayService) {
        this.studentDelayService = studentDelayService;
    }

    /**
     * Sets the view for this controller. This allows the controller to update the UI.
     *
     * @param studentDelayView The view instance.
     */
    public void setView(StudentDelayView studentDelayView) {
        this.studentDelayView = studentDelayView;
    }

    /**
     * Initializes the screen by fetching existing delay records for the selected date
     * and instructing the view to display them.
     *
     * @param selectedDate The date for which to display delay records.
     */
    public void initializeScreen(LocalDate selectedDate) {
        System.out.println("Controller: Initializing screen for date: " + selectedDate);
        // Ensure the view knows which date is currently selected for form data
        if (studentDelayView != null) {
            studentDelayView.simulateFormDataForDate(selectedDate);
        }

        try {
            // Controller -> Service: getDelayRecordsByDate(selectedDate)
            List<StudentDelayDTO> delayDTOs = studentDelayService.getDelayRecordsByDate(selectedDate);
            // Controller -> View: displayForm(delayDTOs)
            if (studentDelayView != null) {
                studentDelayView.displayForm(delayDTOs);
            }
        } catch (ConnectionException e) {
            // Handle connection errors during initialization
            System.err.println("Controller: Error initializing screen: " + e.getMessage());
            if (studentDelayView != null) {
                studentDelayView.showErrorMessage("Failed to load records. " + e.getMessage());
            }
        }
    }

    /**
     * Handles saving new delay data submitted from the form.
     * It marshals the form data, passes it to the service, and updates the view.
     *
     * @param formData The StudentDelayFormData submitted from the UI.
     */
    public void saveDelayData(StudentDelayFormData formData) {
        System.out.println("Controller: Saving delay data from form: " + formData);

        // Client-side validation of StudentDelayFormData (placeholder)
        if (formData == null || formData.studentId == null || formData.studentId.trim().isEmpty() ||
            formData.delayReason == null || formData.delayReason.trim().isEmpty() ||
            formData.selectedDate == null) {
            System.err.println("Controller: Client-side validation failed for form data.");
            if (studentDelayView != null) {
                studentDelayView.showErrorMessage("Validation Failed: All fields are required.");
            }
            return; // Exit if client-side validation fails
        }

        // Marshal StudentDelayFormData to StudentDelayDTO for the service layer
        // The DTO from form does not have an ID or entryTimestamp yet
        StudentDelayDTO delayDTO = new StudentDelayDTO(
            null,
            formData.studentId,
            null, // studentName will be populated by the service
            formData.selectedDate,
            formData.delayReason,
            null // entryTimestamp will be set by the domain/service layer
        );

        try {
            // Controller -> Service: registerStudentDelay(delayDTO)
            StudentDelayDTO registeredDTO = studentDelayService.registerStudentDelay(delayDTO);

            // Controller -> View: showSuccessMessage("Delay recorded successfully!")
            if (studentDelayView != null) {
                studentDelayView.showSuccessMessage("Delay recorded successfully!");
                // Controller -> View: displayUpdatedLog(registeredDTO)
                studentDelayView.displayUpdatedLog(registeredDTO);
                // Re-initialize screen to show the full updated list
                initializeScreen(formData.selectedDate);
            }
        } catch (ValidationException e) {
            // Service --x Controller: throws ValidationException
            // Controller -> View: showErrorMessage("Validation Failed. Please check inputs.")
            System.err.println("Controller: Validation error during save: " + e.getMessage());
            if (studentDelayView != null) {
                studentDelayView.showErrorMessage("Validation Failed. Please check inputs: " + e.getMessage());
            }
        } catch (ConnectionException e) {
            // Service --x Controller: throws ConnectionException
            // Controller -> View: showErrorMessage("Failed to save data. SMOS server connection error.")
            System.err.println("Controller: Connection error during save: " + e.getMessage());
            if (studentDelayView != null) {
                studentDelayView.showErrorMessage("Failed to save data. SMOS server connection error: " + e.getMessage());
            }
        } catch (Exception e) {
            // Catch any other unexpected errors
            System.err.println("Controller: An unexpected error occurred: " + e.getMessage());
            e.printStackTrace();
            if (studentDelayView != null) {
                studentDelayView.showErrorMessage("An unexpected error occurred: " + e.getMessage());
            }
        }
    }
}