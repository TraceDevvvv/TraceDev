package com.example.view;

import com.example.controller.SchoolDataController;
import com.example.dto.StudentDto;
import com.example.exception.StudentNotFoundException;
import com.example.security.AuthenticationService;

/**
 * Boundary class representing the presentation layer.
 * Satisfies requirement REQ-UI-001 (User HAS clicked the "Digital Log" button).
 */
public class SchoolDataView {
    private SchoolDataController controller;
    private AuthenticationService authenticationService;

    public SchoolDataView(SchoolDataController controller, AuthenticationService authService) {
        this.controller = controller;
        this.authenticationService = authService;
    }
    
    /**
     * Handles the "Digital Log" button click event.
     * Implements the sequence diagram flow starting from UI interaction.
     */
    public void onDigitalLogButtonClick(String studentId) {
        try {
            // Step 0: Authentication check (as per sequence diagram)
            if (!authenticationService.verifyLogin(studentId)) {
                displayError("User not authenticated");
                return;
            }
            
            // Step 1: Call controller method
            StudentDto dto = controller.getStudentSchoolData(studentId);
            
            // Step 8: Display result to user
            displayStudentSchoolData(dto);
        } catch (StudentNotFoundException e) {
            // Handle student not found exception
            displayError("Student not found in register");
        } catch (Exception e) {
            // Handle other exceptions
            displayError("Error retrieving student data: " + e.getMessage());
        }
    }
    
    /**
     * Displays student school data to the user.
     */
    public void displayStudentSchoolData(StudentDto dto) {
        System.out.println("=== Student School Data ===");
        System.out.println("Student ID: " + dto.getStudentId());
        System.out.println("Class: " + dto.getClassName());
        System.out.println("Date: " + dto.getDate());
        System.out.println("Absences: " + dto.getAbsences().size() + " records");
        System.out.println("Disciplinary Notes: " + dto.getDisciplinaryNotes().size() + " records");
        System.out.println("Delays: " + dto.getDelays().size() + " records");
        System.out.println("Justification: " + dto.getJustification());
        System.out.println("==========================");
    }
    
    /**
     * Displays error messages to the user.
     */
    public void displayError(String message) {
        System.err.println("ERROR: " + message);
    }
}