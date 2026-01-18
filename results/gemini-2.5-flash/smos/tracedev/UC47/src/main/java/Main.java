import application.AuthenticationService;
import application.ReportCardModificationService;
import application.WorkflowService;
import domain.Role;
import domain.User;
import infrastructure.Database;
import infrastructure.ReportCardRepository;
import presentation.ReportCardController;
import presentation.ReportCardEditView;

import java.util.Map;

/**
 * Main class to demonstrate the Report Card Management system flow.
 * This class orchestrates the creation of all components and simulates the sequence diagram interactions.
 */
public class Main {
    public static void main(String[] args) {
        // --- Infrastructure Layer Initialization ---
        Database database = new Database();
        ReportCardRepository reportCardRepository = new ReportCardRepository(database);

        // --- Application Layer Initialization ---
        AuthenticationService authenticationService = new AuthenticationService();
        WorkflowService workflowService = new WorkflowService();
        ReportCardModificationService reportCardModificationService = new ReportCardModificationService(reportCardRepository);

        // --- Presentation Layer Initialization ---
        ReportCardEditView reportCardEditView = new ReportCardEditView();

        // --- Mock Data / Preconditions ---
        // Assume an Administrator user is logged in
        User adminUser = new User("U001", "admin", "hashed_password", Role.ADMINISTRATOR);
        // Simulate a student ID
        String studentIdToEdit = "S123";

        // --- Controller Initialization ---
        ReportCardController reportCardController = new ReportCardController(
            reportCardModificationService,
            reportCardEditView,
            authenticationService,
            workflowService,
            adminUser
        );

        System.out.println("--- Starting Report Card Modification Use Case Simulation ---");

        // --- SCENARIO 1: Successful Report Card Edit and Update ---
        System.out.println("\n=== SCENARIO 1: Successful Update ===");
        database.setSimulatePersistenceError(false); // Ensure no errors for this scenario

        // Admin clicks edit button
        reportCardEditView.clickEditButton(studentIdToEdit);

        // Admin enters new data and clicks confirmation (simulated by view calling controller)
        reportCardEditView.clickConfirmationButton(studentIdToEdit);

        System.out.println("\n--- End SCENARIO 1 ---");


        // --- SCENARIO 2: Update fails due to PersistenceException ---
        System.out.println("\n=== SCENARIO 2: Update Failure (Persistence Error) ===");
        database.setSimulatePersistenceError(true); // Simulate a network failure

        // Admin clicks edit button again (to get the form, though not strictly needed for this scenario's failure)
        reportCardEditView.clickEditButton(studentIdToEdit);

        // Admin enters new data and clicks confirmation, but this time it should fail
        reportCardEditView.clickConfirmationButton(studentIdToEdit);

        System.out.println("\n--- End SCENARIO 2 ---");

        // Reset for any further interactions
        database.setSimulatePersistenceError(false);

        // --- SCENARIO 3: Access Denied (Non-Admin User) ---
        System.out.println("\n=== SCENARIO 3: Access Denied (Non-Admin User) ===");
        User studentUser = new User("U002", "student1", "hashed_password", Role.STUDENT);
        ReportCardController studentController = new ReportCardController(
            reportCardModificationService,
            reportCardEditView,
            authenticationService,
            workflowService,
            studentUser // Use the student user
        );

        // Try to edit with a student user
        System.out.println("\nSimulating student user trying to edit report card:");
        studentController.editReportCard(studentIdToEdit);

        System.out.println("\n--- End SCENARIO 3 ---");

        System.out.println("\n--- Simulation Complete ---");
    }
}