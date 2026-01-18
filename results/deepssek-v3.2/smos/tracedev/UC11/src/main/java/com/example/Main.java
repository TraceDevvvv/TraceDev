package com.example;

import com.example.actor.Administrator;
import com.example.controller.ManageTeachingAssignmentsController;
import com.example.dto.UpdateAssignmentsRequestDTO;
import com.example.repository.impl.*;
import com.example.ui.WebUI;
import java.util.Arrays;

/**
 * Main class to demonstrate the flow of the system.
 * Simulates the sequence diagram steps.
 */
public class Main {
    public static void main(String[] args) {
        // Initialize repositories
        TeacherRepositoryImpl teacherRepo = new TeacherRepositoryImpl();
        ClassRepositoryImpl classRepo = new ClassRepositoryImpl();
        TeachingRepositoryImpl teachingRepo = new TeachingRepositoryImpl();
        TeachingAssignmentRepositoryImpl assignmentRepo = new TeachingAssignmentRepositoryImpl();

        // Initialize controller and UI
        ManageTeachingAssignmentsController controller = new ManageTeachingAssignmentsController(
                teacherRepo, classRepo, teachingRepo, assignmentRepo);
        WebUI ui = new WebUI(controller);

        // Simulate an administrator
        Administrator admin = new Administrator("A001", "Admin User", "ADMIN");

        System.out.println("=== Simulating Teacher Teaching Assignments Management ===\n");

        // Step 1: Get teacher details
        System.out.println("Step 1: Get teacher details for teacher T001");
        try {
            ui.displayTeacherDetails(controller.getTeacherDetails("T001"));
        } catch (Exception e) {
            ui.handleError(e.getMessage());
        }

        // Step 2 & 3: Select academic year and view classes
        System.out.println("\nStep 2 & 3: Select academic year '2023-2024' and view classes");
        ui.onSelectAcademicYear("2023-2024");

        // Step 4 & 5: Select class and view teachings
        System.out.println("\nStep 4 & 5: Select class C001 and view teachings");
        ui.onSelectClass("C001");

        // Step 6 & 7: Submit assignment updates
        System.out.println("\nStep 6 & 7: Submit assignment updates");
        UpdateAssignmentsRequestDTO request = new UpdateAssignmentsRequestDTO(
                "T001",
                "2023-2024",
                "C001",
                Arrays.asList("TE002"),  // add Physics teaching
                Arrays.asList("TA001")   // remove existing assignment
        );
        ui.onSubmitAssignments(request);

        System.out.println("\n=== Flow completed ===");
    }
}