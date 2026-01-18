
package com.example.smos;

import com.example.smos.model.Teaching;
import com.example.smos.repository.JpaTeachingRepository;
import com.example.smos.service.TeachingArchiveService;
import com.example.smos.security.AuthenticationService;
import com.example.smos.security.UserRole;
import com.example.smos.security.User;
import com.example.smos.security.Session;
import com.example.smos.ui.UserInterface;

import java.util.Date;
import java.util.List;

/**
 * Main application class to demonstrate the functionality of the SMOS system.
 * This class acts as the 'Admin' actor in the sequence diagram and orchestrates
 * the setup and execution of the 'Eliminate Teaching' use case, including
 * both successful and alternative (error) flows.
 */
public class MainApplication {

    public static void main(String[] args) {
        System.out.println("--- SMOS Archive System Demonstration ---");

        // 1. Setup the application components (Dependency Injection)
        // Corresponds to the creation of objects before the sequence starts.
        JpaTeachingRepository jpaTeachingRepository = new JpaTeachingRepository();
        TeachingArchiveService teachingArchiveService = new TeachingArchiveService(jpaTeachingRepository);
        AuthenticationService authenticationService = new AuthenticationService();
        UserInterface userInterface = new UserInterface(teachingArchiveService); // Fixed: Removed authenticationService from constructor call
        
        // Pre-condition check (simulated): Administrator is authenticated and authorized.
        System.out.println("\\n--- Precondition: Administrator Authentication ---");
        Session adminSession = authenticationService.login("admin", "password123");
        if (adminSession == null || !authenticationService.isAuthenticated(adminSession)) {
            System.err.println("Administrator login failed. Exiting application.");
            return;
        }
        System.out.println("Precondition Met: Administrator (User ID: " + adminSession.getUserId() + ") is authenticated.");
        System.out.println("  (Note: The 'User' class, 'UserRole' enum, and 'Session' class are also defined as per CD, " +
                           "but their full interaction with this specific sequence is abstracted for brevity.)");

        // Display initial teachings
        System.out.println("\\n--- Initial Teachings in Archive ---");
        try {
            List<Teaching> initialTeachings = jpaTeachingRepository.findAll();
            userInterface.showUpdatedTeachings(initialTeachings);
        } catch (Exception e) {
            userInterface.showErrorMessage("Failed to load initial teachings: " + e.getMessage());
        }

        // 2. Scenario 1: Successful Elimination of a Teaching
        System.out.println("\\n--- Scenario 1: Successful Elimination of Teaching (ID: T001) ---");
        // Reset connection failure flag to ensure success
        jpaTeachingRepository.setSimulateConnectionFailure(false);
        userInterface.requestDeleteTeaching("T001"); // Admin -> UI: requestDeleteTeaching

        // 3. Scenario 2: Elimination with SMOS Connection Failure
        System.out.println("\\n--- Scenario 2: Elimination with SMOS Connection Failure (ID: T002) ---");
        // Simulate a connection failure for the next operation
        jpaTeachingRepository.setSimulateConnectionFailure(true);
        userInterface.requestDeleteTeaching("T002"); // Admin -> UI: requestDeleteTeaching

        // 4. Scenario 3: Attempt to delete a non-existent teaching (should result in updated list but no actual deletion)
        System.out.println("\\n--- Scenario 3: Attempt to delete a non-existent Teaching (ID: T999) ---");
        // Ensure connection is active for this test
        jpaTeachingRepository.setSimulateConnectionFailure(false);
        userInterface.requestDeleteTeaching("T999");

        // Final state of the archive
        System.out.println("\\n--- Final State of Teachings in Archive ---");
        try {
            List<Teaching> finalTeachings = jpaTeachingRepository.findAll();
            userInterface.showUpdatedTeachings(finalTeachings);
        } catch (Exception e) {
            userInterface.showErrorMessage("Failed to load final teachings: " + e.getMessage());
        }

        // Clean up session
        authenticationService.logout(adminSession);
        System.out.println("\\n--- Demonstration End ---");
    }
}
