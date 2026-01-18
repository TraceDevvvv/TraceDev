package com.example.ui;

import com.example.controller.ManageTeachingAssignmentsController;
import com.example.dto.*;
import java.util.List;

/**
 * Web UI boundary class that interacts with the controller and administrator.
 */
public class WebUI {
    private ManageTeachingAssignmentsController controller;

    public WebUI(ManageTeachingAssignmentsController controller) {
        this.controller = controller;
    }

    /**
     * Displays teacher details to the administrator.
     * @param dto the TeacherDetailsDTO
     */
    public void displayTeacherDetails(TeacherDetailsDTO dto) {
        System.out.println("Teacher Details:");
        System.out.println("ID: " + dto.getTeacherId());
        System.out.println("Name: " + dto.getTeacherName());
        System.out.println("Current Assignments:");
        for (TeachingAssignmentDTO ta : dto.getCurrentAssignments()) {
            System.out.println("  - " + ta.getTeaching().getTeachingName() + " (Assigned: " + ta.isAssigned() + ")");
        }
    }

    /**
     * Displays a list of classes.
     * @param dtos list of ClassDTO objects
     */
    public void displayClasses(List<ClassDTO> dtos) {
        System.out.println("Classes for selected year:");
        for (ClassDTO c : dtos) {
            System.out.println("  - " + c.getClassName() + " (" + c.getClassId() + ")");
        }
    }

    /**
     * Displays a list of teachings.
     * @param dtos list of TeachingDTO objects
     */
    public void displayTeachings(List<TeachingDTO> dtos) {
        System.out.println("Teachings for selected class:");
        for (TeachingDTO t : dtos) {
            System.out.println("  - " + t.getTeachingName() + " (" + t.getTeachingCode() + ")");
        }
    }

    /**
     * Shows a success message after assignments are updated.
     */
    public void showSuccessMessage() {
        System.out.println("Teaching assignments updated successfully!");
    }

    /**
     * Handles and displays errors.
     * @param error the error message
     */
    public void handleError(String error) {
        System.err.println("Error: " + error);
    }

    // Simulated UI methods that would be triggered by user actions
    public void onSelectAcademicYear(String year) {
        List<ClassDTO> classes = controller.getClassesForYear(year);
        displayClasses(classes);
    }

    public void onSelectClass(String classId) {
        List<TeachingDTO> teachings = controller.getTeachingsForClass(classId);
        displayTeachings(teachings);
    }

    public void onSubmitAssignments(UpdateAssignmentsRequestDTO request) {
        try {
            controller.updateAssignments(request);
            showSuccessMessage();
        } catch (Exception e) {
            handleError(e.getMessage());
        }
    }

    /**
     * Receives selectAcademicYear message from Admin (sequence diagram).
     * @param year the academic year
     */
    public void selectAcademicYear(String year) {
        // Forward to controller
        controller.selectAcademicYear(year);
    }

    /**
     * Receives selectClass message from Admin (sequence diagram).
     * @param classId the class ID
     */
    public void selectClass(String classId) {
        // Forward to controller
        controller.selectClass(classId);
    }

    /**
     * Receives toggleTeachingAssignment message from Admin (sequence diagram).
     * @param teachingId the teaching ID
     * @param assigned true if assigned, false if unassigned
     */
    public void toggleTeachingAssignment(String teachingId, boolean assigned) {
        // Forward to controller
        controller.toggleTeachingAssignment(teachingId, assigned);
    }

    /**
     * Receives submitAssignments message from Admin (sequence diagram).
     */
    public void submitAssignments() {
        // This would normally gather data from UI state and create a request DTO.
        // For traceability, we provide a placeholder that logs.
        System.out.println("Submit assignments triggered from UI.");
    }
}