package com.example;

/**
 * Main class to demonstrate the execution of the Administrator deletes disciplinary notes use case.
 * This acts as the entry point and orchestrates the setup and initial call.
 */
public class Main {
    public static void main(String[] args) {
        // --- System Initialization ---
        Logger logger = new ConsoleLogger();
        DatabaseAdapter databaseAdapter = new DatabaseAdapter(logger);
        TransactionManager transactionManager = new DefaultTransactionManager(logger);
        NoteRepositoryPort noteRepository = new NoteRepositoryAdapter(databaseAdapter, logger);
        NotificationServicePort notificationService = new EmailNotificationAdapter(logger);
        AuthenticationService authService = new AuthenticationService(); // Administrator is logged in by default in its constructor

        DeleteNoteUseCase deleteNoteUseCase = new DeleteNoteUseCase(
                noteRepository,
                notificationService,
                transactionManager,
                logger
        );

        AdminNoteScreen adminNoteScreen = new AdminNoteScreen();
        AdminNoteController adminNoteController = new AdminNoteController(
                deleteNoteUseCase,
                adminNoteScreen,
                authService
        );
        // Link the AdminNoteScreen to its controller for message passing,
        // fulfilling the sequence diagram's flow where AdminNoteScreen delegates to AdminNoteController.
        adminNoteScreen.setController(adminNoteController);

        System.out.println("--------------------------------------------------");
        System.out.println("SIMULATION: Administrator Deletes Disciplinary Note");
        System.out.println("--------------------------------------------------");

        // --- Scenario 1: Successful Deletion ---
        System.out.println("\n--- SCENARIO 1: Successful Note Deletion (note101) ---");
        // Simulate Administrator clicks "Delete" button on the screen
        adminNoteScreen.clicksDeleteButton("note101");
        System.out.println("\n--------------------------------------------------");


        // --- Scenario 2: Note Not Found ---
        System.out.println("\n--- SCENARIO 2: Note Not Found (nonExistentNote) ---");
        // Simulate Administrator clicks "Delete" button on the screen
        adminNoteScreen.clicksDeleteButton("nonExistentNote");
        System.out.println("\n--------------------------------------------------");

        // --- Scenario 3: Admin Not Logged In ---
        System.out.println("\n--- SCENARIO 3: Administrator Not Logged In ---");
        authService.logout(); // Simulate logging out the administrator
        // Simulate Administrator clicks "Delete" button on the screen
        adminNoteScreen.clicksDeleteButton("note102"); // Try to delete another existing note
        System.out.println("\n--------------------------------------------------");

        // Re-login admin for potential further tests
        authService.login(new User("admin123", "administrator", java.util.Arrays.asList("ADMIN")));

        // --- Scenario 4: Simulate Database Error during find (low probability, might need multiple runs) ---
        // To reliably test this, one might adjust the random failure rate in DatabaseAdapter.
        System.out.println("\n--- SCENARIO 4: Simulate Database Error during Find (note103) ---");
        // For demonstration, let's assume 'note103' will hit a database error.
        // In a real test, you'd mock the DatabaseAdapter to force this.
        System.out.println("NOTE: Database error simulation has a random chance (5%). May need to run multiple times to hit.");
        // Simulate Administrator clicks "Delete" button on the screen
        adminNoteScreen.clicksDeleteButton("note103");
        System.out.println("\n--------------------------------------------------");

        // --- Scenario 5: Simulate Notification Failure (low probability, might need multiple runs) ---
        // 'note102' should now be back as it was not deleted in scenario 3
        System.out.println("\n--- SCENARIO 5: Simulate Notification Failure (note102) ---");
        System.out.println("NOTE: Notification error simulation has a random chance (10%). May need to run multiple times to hit.");
        // Simulate Administrator clicks "Delete" button on the screen
        adminNoteScreen.clicksDeleteButton("note102");
        System.out.println("\n--------------------------------------------------");
    }
}