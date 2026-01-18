package com.example;

import com.example.application.EnrollmentService;
import com.example.domain.RegistrationRequest;
import com.example.infrastructure.*;
import com.example.presentation.AdministratorUI;

import java.util.List;

/**
 * Main class to demonstrate the student enrollment acceptance use case.
 * It sets up the dependencies (repositories, service, UI) and simulates
 * user interaction, including a successful flow and an error flow.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup Infrastructure Layer (In-memory repositories) ---
        InMemoryStudentRepository studentRepository = new InMemoryStudentRepository();
        InMemoryRegistrationRequestRepository registrationRequestRepository = new InMemoryRegistrationRequestRepository();
        InMemoryUserRepository userRepository = new InMemoryUserRepository();
        InMemoryEnrollmentRepository enrollmentRepository = new InMemoryEnrollmentRepository();

        // --- Setup Application Layer ---
        EnrollmentService enrollmentService = new EnrollmentService(
            studentRepository,
            registrationRequestRepository,
            userRepository,
            enrollmentRepository
        );

        // --- Setup Presentation Layer ---
        AdministratorUI administratorUI = new AdministratorUI(enrollmentService);

        // --- Simulate Initial Data: Pending Registration Requests ---
        RegistrationRequest req1 = new RegistrationRequest("Alice Smith", "alice.smith@example.com");
        RegistrationRequest req2 = new RegistrationRequest("Bob Johnson", "bob.johnson@example.com");
        RegistrationRequest req3 = new RegistrationRequest("Charlie Brown", "charlie.brown@example.com");

        registrationRequestRepository.save(req1);
        registrationRequestRepository.save(req2);
        registrationRequestRepository.save(req3);

        System.out.println("--- DEMO START ---");

        // --- Scenario 1: Successful Enrollment Acceptance ---
        System.out.println("\n=== Scenario 1: Successful Enrollment Acceptance ===");
        administratorUI.displayRegistrationRequests(enrollmentService.getPendingRegistrationRequests()); // Renamed method
        administratorUI.handleAcceptButtonClick(req1.getId()); // Renamed method for admin action

        // Verify state (optional, for demonstration)
        System.out.println("\n--- After Alice's Enrollment ---");
        System.out.println("Alice's request status: " + registrationRequestRepository.findById(req1.getId()).getStatus());
        System.out.println("Alice's student record exists: " + (studentRepository.findById("student-" + req1.getId().split("-")[1]) != null)); // Crude ID matching
        System.out.println("Alice's user record exists: " + (userRepository.findByUsername(req1.getStudentEmail()) != null));

        // --- Scenario 2: Enrollment Acceptance with SMOS Connection Failure ---
        System.out.println("\n=== Scenario 2: Enrollment Acceptance with SMOS Connection Failure ===");
        // Simulate SMOS connection failure for this scenario
        registrationRequestRepository.setSimulateSMOSConnectionFailure(true);

        administratorUI.displayRegistrationRequests(enrollmentService.getPendingRegistrationRequests()); // Renamed method
        administratorUI.handleAcceptButtonClick(req2.getId()); // Renamed method for admin action

        // Verify state (optional, for demonstration)
        System.out.println("\n--- After Bob's Enrollment Attempt (Failure) ---");
        // The request should still be PENDING if the update failed transactionally
        // In our in-memory implementation, the domain object might have been marked ACCEPTED,
        // but the repository threw an exception, meaning it was not persisted.
        // For this demo, the in-memory object was updated, but the exception path was taken.
        // If we were strictly transactional, it would be PENDING.
        // Here, we check the state in the repository:
        System.out.println("Bob's request status in repository: " + registrationRequestRepository.findById(req2.getId()).getStatus());
        System.out.println("Bob's student record exists (should be false if transactional): " + (studentRepository.findById("student-" + req2.getId().split("-")[1]) != null));
        registrationRequestRepository.setSimulateSMOSConnectionFailure(false); // Reset for future operations

        // --- Scenario 3: Attempt to accept an already accepted request ---
        System.out.println("\n=== Scenario 3: Attempt to accept an already accepted request ===");
        administratorUI.handleAcceptButtonClick(req1.getId()); // Renamed method for admin action, Admin tries to accept Alice's request again

        System.out.println("\n--- DEMO END ---");

        // Display final pending requests
        System.out.println("\n--- Final Pending Requests ---");
        administratorUI.displayRegistrationRequests(enrollmentService.getPendingRegistrationRequests()); // Renamed method
    }
}