package com.example.reportcard;

import java.util.Arrays;
import java.util.List;

/**
 * Main application class to demonstrate the Report Card Elimination Use Case.
 * This class sets up the dependencies and simulates the interactions described
 * in the sequence diagram.
 */
public class Main {
    public static void main(String[] args) {
        System.out.println("--- Starting Report Card Elimination Simulation ---\n");

        // 1. Setup Dependencies
        TransactionManager transactionManager = new TransactionManager();
        ReportCardRepositoryImpl reportCardRepository = new ReportCardRepositoryImpl(transactionManager);
        ReportCardEliminationService eliminationService = new ReportCardEliminationService(reportCardRepository);
        ConfirmationPresenter confirmationPresenter = new ConfirmationPresenter();
        ReportCardDisplayServiceImpl reportCardDisplayService = new ReportCardDisplayServiceImpl();

        // Simulate an administrator user
        Administrator currentAdmin = new Administrator("adminUser");
        currentAdmin.setLoggedIn(true); // Ensure admin is logged in for the simulation

        ReportCardController controller = new ReportCardController(
                eliminationService,
                confirmationPresenter,
                reportCardDisplayService,
                currentAdmin
        );

        // Add some initial report cards to the repository
        reportCardRepository.addReportCard(new ReportCard("RC004", "S002", "Physics", "A-"));
        reportCardRepository.addReportCard(new ReportCard("RC005", "S003", "Chemistry", "B"));
        reportCardRepository.addReportCard(new ReportCard("RC006", "S004", "Art", "C+"));

        reportCardRepository.printAllReportCards();

        // --- Sequence Diagram Simulation: Eliminate a Report Card ---
        System.out.println("\n--- Simulating 'Eliminate a Report Card' Use Case ---\n");

        // Entry Conditions Met:
        // - Logged in as Administrator (currentAdmin is set)
        // - Report Card displayed (simulate this by setting selected ID and displaying)
        String reportCardToDeleteId = "RC004";
        reportCardDisplayService.setCurrentSelectedReportCardId(reportCardToDeleteId);
        reportCardDisplayService.displayReportCard(reportCardToDeleteId);
        System.out.println("Administrator: 'Delete' button clicked for Report Card ID: " + reportCardToDeleteId);

        // Administrator -> ReportCardController : requestReportCardDeletion(reportCardId)
        controller.requestReportCardDeletion(reportCardToDeleteId);

        // Administrator : (simulates accepting confirmation)
        System.out.println("\nAdministrator: Administrator accepts cancellation by pressing confirmation key (simulating 'true').");

        // Administrator -> ReportCardController : confirmReportCardDeletion(reportCardId, confirmation)
        controller.confirmReportCardDeletion(reportCardToDeleteId, true); // Administrator confirms deletion

        // Verify the outcome
        reportCardRepository.printAllReportCards();

        System.out.println("\n--- Simulating an attempt to delete a non-existent report card ---");
        String nonExistentReportCardId = "RC999";
        reportCardDisplayService.setCurrentSelectedReportCardId(nonExistentReportCardId);
        reportCardDisplayService.displayReportCard(nonExistentReportCardId);
        System.out.println("Administrator: 'Delete' button clicked for Report Card ID: " + nonExistentReportCardId);
        controller.requestReportCardDeletion(nonExistentReportCardId);
        System.out.println("\nAdministrator: Administrator accepts cancellation by pressing confirmation key (simulating 'true').");
        controller.confirmReportCardDeletion(nonExistentReportCardId, true); // Administrator confirms deletion
        reportCardRepository.printAllReportCards();

        System.out.println("\n--- Simulating cancellation of deletion by administrator ---");
        String reportCardToCancelDeleteId = "RC005";
        reportCardDisplayService.setCurrentSelectedReportCardId(reportCardToCancelDeleteId);
        reportCardDisplayService.displayReportCard(reportCardToCancelDeleteId);
        System.out.println("Administrator: 'Delete' button clicked for Report Card ID: " + reportCardToCancelDeleteId);
        controller.requestReportCardDeletion(reportCardToCancelDeleteId);
        System.out.println("\nAdministrator: Administrator declines cancellation by pressing confirmation key (simulating 'false').");
        controller.confirmReportCardDeletion(reportCardToCancelDeleteId, false); // Administrator declines deletion
        reportCardRepository.printAllReportCards();


        System.out.println("\n--- End of Report Card Elimination Simulation ---");
    }
}