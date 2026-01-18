package com.example.justification;

import com.example.justification.controller.AdministratorController;
import com.example.justification.controller.ActionResult;
import com.example.justification.domain.Justification;
import com.example.justification.infrastructure.IDbContext;
import com.example.justification.infrastructure.InMemoryDbContext;
import com.example.justification.infrastructure.IUnitOfWork;
import com.example.justification.infrastructure.UnitOfWork;
import com.example.justification.repository.IJustificationRepository;
import com.example.justification.repository.JustificationRepository;
import com.example.justification.service.JustificationService;

import java.util.Date;
import java.util.UUID;

/**
 * Main class to demonstrate the functionality of the Justification Management system.
 * This class sets up the dependencies and simulates user interactions to test
 * the "Eliminate Justification" use case as described in the sequence diagram.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting Justification Management System Demo ---");

        // 1. Setup Infrastructure Layer (In-memory implementations)
        IDbContext dbContext = new InMemoryDbContext();
        IJustificationRepository justificationRepository = new JustificationRepository(dbContext);
        IUnitOfWork unitOfWork = new UnitOfWork(dbContext);

        // 2. Setup Application Service Layer
        JustificationService justificationService = new JustificationService(justificationRepository, unitOfWork);

        // 3. Setup Presentation Layer (Controller)
        AdministratorController adminController = new AdministratorController(justificationService);

        // --- Prepare some initial data ---
        String jid1 = UUID.randomUUID().toString();
        Justification just1 = new Justification(jid1, "Project Delay", "Delay due to external vendor issues.", new Date());
        dbContext.getJustifications().add(just1);

        String jid2 = UUID.randomUUID().toString();
        Justification just2 = new Justification(jid2, "Budget Overrun", "Additional costs for emergency supplies.", new Date());
        dbContext.getJustifications().add(just2);

        String jid3 = UUID.randomUUID().toString();
        Justification just3 = new Justification(jid3, "Resource Allocation", "Need more developers for critical task.", new Date());
        dbContext.getJustifications().add(just3);

        System.out.println("\n--- Initial Justifications in DB ---");
        dbContext.getJustifications().getAll().forEach(j -> System.out.println("  " + j));

        // --- Scenario 1: Successfully eliminate an existing justification ---
        System.out.println("\n--- Scenario 1: Eliminating existing justification (ID: " + jid1 + ") ---");
        ActionResult result1 = adminController.handleDeleteJustification(jid1);
        System.out.println("Administrator: " + result1.getMessage());
        System.out.println("Current Justifications after Scenario 1:");
        dbContext.getJustifications().getAll().forEach(j -> System.out.println("  " + j));
        if (dbContext.getJustifications().get(jid1) == null) {
            System.out.println("  (Justification " + jid1 + " is indeed removed from DB)");
        } else {
            System.out.println("  (ERROR: Justification " + jid1 + " was not removed!)");
        }


        // --- Scenario 2: Attempt to eliminate a non-existent justification ---
        String nonExistentJid = "non-existent-id";
        System.out.println("\n--- Scenario 2: Attempting to eliminate non-existent justification (ID: " + nonExistentJid + ") ---");
        ActionResult result2 = adminController.handleDeleteJustification(nonExistentJid);
        System.out.println("Administrator: " + result2.getMessage());
        System.out.println("Current Justifications after Scenario 2:");
        dbContext.getJustifications().getAll().forEach(j -> System.out.println("  " + j));


        // --- Scenario 3: Simulate connection interruption during elimination ---
        System.out.println("\n--- Scenario 3: Simulating connection interruption during elimination (ID: " + jid2 + ") ---");
        dbContext.setConnectionInterrupted(true); // Simulate connection loss
        ActionResult result3 = adminController.handleDeleteJustification(jid2);
        System.out.println("Administrator: " + result3.getMessage());
        System.out.println("Current Justifications after Scenario 3:");
        dbContext.getJustifications().getAll().forEach(j -> System.out.println("  " + j));
        if (dbContext.getJustifications().get(jid2) != null) {
            System.out.println("  (Justification " + jid2 + " is still in DB, as expected due to rollback/error)");
        } else {
            System.out.println("  (ERROR: Justification " + jid2 + " was removed despite connection error!)");
        }
        dbContext.setConnectionInterrupted(false); // Reset connection state

        // --- Scenario 4: Successfully eliminate remaining justification after connection restore ---
        System.out.println("\n--- Scenario 4: Eliminating remaining justification (ID: " + jid2 + ") after connection restored ---");
        ActionResult result4 = adminController.handleDeleteJustification(jid2);
        System.out.println("Administrator: " + result4.getMessage());
        System.out.println("Current Justifications after Scenario 4:");
        dbContext.getJustifications().getAll().forEach(j -> System.out.println("  " + j));
        if (dbContext.getJustifications().get(jid2) == null) {
            System.out.println("  (Justification " + jid2 + " is now removed from DB)");
        } else {
            System.out.println("  (ERROR: Justification " + jid2 + " was not removed!)");
        }

        System.out.println("\n--- Demo Finished ---");
    }
}