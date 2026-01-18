import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class to demonstrate the 'InsertJustification' use case.
 * This class orchestrates the interaction between Administrator, JustificationService,
 * and JustificationForm to simulate the user flow.
 */
public class MainApp {

    public static void main(String[] args) {
        System.out.println("--- Starting InsertJustification Use Case Simulation ---");

        // 1. Initialize components
        // Create an administrator instance
        Administrator admin = new Administrator("adminUser");
        // Create the service with the administrator
        JustificationService justificationService = new JustificationService(admin);
        // Create the form, passing the service
        JustificationForm justificationForm = new JustificationForm(justificationService);

        try {
            // Precondition: The user must be logged in to the system as an administrator.
            System.out.println("\n--- Step 1: Administrator Login ---");
            if (!admin.login("adminUser", "adminPass")) {
                System.out.println("Simulation aborted: Administrator login failed.");
                return;
            }

            // Simulate the administrator selecting an absence "in red".
            // As per JustificationService, "ABS003" is the simulated absence in red.
            String selectedAbsenceId = "ABS003";
            System.out.println("\n--- Step 2: Administrator selects absence ID: " + selectedAbsenceId + " (simulated as 'in red') ---");

            // Preconditions check:
            // - The user has carried out the case of use "SviewTetTingloregister".
            // - The user has held the case of use "viewellacogiustifies".
            // - The user clicks on one of the absences in red.
            System.out.println("\n--- Step 3: Checking Preconditions ---");
            if (!justificationService.checkPreconditions(selectedAbsenceId)) {
                System.out.println("Simulation aborted: Preconditions not met for inserting justification.");
                return;
            }

            // Event Sequence:
            // System: 1. Show a form in which to insert the fields of justice (date justification).
            // User: 2. Fill out the form
            System.out.println("\n--- Step 4: Displaying Justification Form and User Input ---");
            Optional<LocalDate> enteredDate = justificationForm.showForm(selectedAbsenceId);

            if (enteredDate.isEmpty()) {
                System.out.println("Simulation aborted: Invalid date entered or user interrupted form filling.");
                return;
            }

            // User: 3. Click on "Save"
            // System: 4. Save the justification.
            System.out.println("\n--- Step 5: User Clicks Save and System Saves Justification ---");
            boolean saveSuccessful = justificationForm.clickSave(selectedAbsenceId, enteredDate.get());

            // Postconditions:
            if (saveSuccessful) {
                System.out.println("\n--- Postconditions Met: Justification successfully processed. ---");
                // "The justification data has been entered into the system." (Handled by JustificationService)
                // "The system returns to the registry screen." (Handled by JustificationService)
            } else {
                System.out.println("\n--- Postconditions Not Fully Met: Justification save failed. ---");
                // This covers:
                // "The administrator interrupts the operation" (simulated by SMOS server interruption)
                // "Connection to the SMOS server interrupted" (simulated by JustificationService)
            }

        } finally {
            // Ensure scanner is closed
            justificationForm.closeScanner();
            System.out.println("\n--- InsertJustification Use Case Simulation Finished ---");
        }
    }
}