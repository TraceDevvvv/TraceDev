package com.example.school;

import com.example.school.application.ParentService;
import com.example.school.dataaccess.IStudentRepository;
import com.example.school.dataaccess.SmosClient;
import com.example.school.dataaccess.SmosStudentRepository;
import com.example.school.presentation.ParentUI;

/**
 * Main class to demonstrate the application flow, acting as the system's entry point.
 * It sets up the dependencies and simulates a user interaction.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("Application Starting...\\n");

        // --- Dependency Injection / System Assembly ---
        // Data Access Layer
        SmosClient smosClient = new SmosClient("https://smos.example.com/api");
        IStudentRepository studentRepository = new SmosStudentRepository(smosClient);

        // Application Layer
        ParentService parentService = new ParentService(studentRepository);

        // Presentation Layer
        // Assuming parent with ID "parent456" is logged in
        String loggedInParentId = "parent456";
        ParentUI parentUI = new ParentUI(parentService, loggedInParentId);

        // --- Simulate User Interaction (Sequence Diagram Flow) ---

        // Simulate a parent viewing their child's academic status
        String childToView = "child123";
        parentUI.viewChildAcademicStatus(childToView); // Updated call

        // --- Simulate another scenario (e.g., child not found or belongs to another parent) ---
        System.out.println("\\n--- Simulating Scenario: Child not found ---");
        parentUI.viewChildAcademicStatus("nonExistentChild"); // Updated call

        System.out.println("\\n--- Simulating Scenario: Child belongs to another parent ---");
        // For this, we'd need another parent's ID in the SmosClient dummy data
        // Let's assume child123's parentId is "parent456". If we try to view it with "anotherParentId", it should fail.
        String anotherLoggedInParentId = "anotherParentId789";
        ParentUI anotherParentUI = new ParentUI(parentService, anotherLoggedInParentId);
        anotherParentUI.viewChildAcademicStatus(childToView); // Updated call


        System.out.println("\\nApplication Finished.");
    }
}