package com.example.absencejustification;

import com.example.absencejustification.application.AbsenceJustificationController;
import com.example.absencejustification.dataaccess.Database;
import com.example.absencejustification.dataaccess.JustificationRepository;
import com.example.absencejustification.presentation.AbsenceJustificationForm;
import com.example.absencejustification.presentation.AbsenceListScreen;
import com.example.absencejustification.presentation.RegistryScreen;

import java.util.Arrays;
import java.util.Scanner;

/**
 * Main class to simulate the "Insert Justification" use case described in the Sequence Diagram.
 * This acts as the "Admin" actor and orchestrates the interactions between the UI,
 * Application, and Data Access layers.
 */
public class Main {
    public static void main(String[] args) {
        // --- Setup: Instantiate all components ---
        // Data Access Layer
        Database database = new Database();
        JustificationRepository justificationRepository = new JustificationRepository(database);

        // Application Layer
        AbsenceJustificationController controller = new AbsenceJustificationController(justificationRepository);

        // Presentation Layer
        AbsenceListScreen absenceListScreen = new AbsenceListScreen();
        AbsenceJustificationForm justificationForm = new AbsenceJustificationForm();
        RegistryScreen registryScreen = new RegistryScreen();

        // Inject dependencies between presentation layer components and controller
        absenceListScreen.setAbsenceJustificationForm(justificationForm);
        justificationForm.setController(controller);
        justificationForm.setRegistryScreen(registryScreen);
        justificationForm.setAbsenceListScreen(absenceListScreen); // For cancel operation

        Scanner scanner = new Scanner(System.in);
        String selectedAbsenceId = "ABS001"; // Pre-selected absence ID for demonstration

        System.out.println("--- Simulating Insert Justification Use Case ---");

        // Scenario 1: Successful Justification
        System.out.println("\n--- SCENARIO 1: Successful Justification ---");
        database.setSimulateConnectionFailure(false); // Ensure database is working

        // Admin -> AbsList : clicksOnAbsence(absenceId)
        absenceListScreen.displayAbsences(Arrays.asList("ABS001", "ABS002", "ABS003")); // Display some absences
        absenceListScreen.onAbsenceSelected(selectedAbsenceId);

        // JustForm --> Admin : displayForm(absenceId)
        // Admin -> JustForm : fillJustificationData(date)
        justificationForm.fillJustificationData("2023-10-26");

        // Admin -> JustForm : clickSave()
        justificationForm.onSaveButtonClick();
        // Expected: Justification saved, navigate to RegistryScreen.

        System.out.println("\nPress Enter to continue to next scenario...");
        scanner.nextLine();

        // Scenario 2: Invalid Justification Data (e.g., bad date format)
        System.out.println("\n--- SCENARIO 2: Invalid Justification Data ---");
        absenceListScreen.onAbsenceSelected(selectedAbsenceId); // Re-open form
        justificationForm.fillJustificationData("not-a-date"); // Invalid date
        justificationForm.onSaveButtonClick();
        // Expected: Error message displayed, remains on form.

        System.out.println("\nPress Enter to continue to next scenario...");
        scanner.nextLine();

        // Scenario 3: Admin interrupts operation (cancels)
        System.out.println("\n--- SCENARIO 3: Administrator interrupts operation (cancel) ---");
        absenceListScreen.onAbsenceSelected(selectedAbsenceId); // Re-open form
        justificationForm.fillJustificationData("2023-11-01"); // Fill some valid data
        // Admin -> JustForm : cancelOperation()
        justificationForm.cancelOperation();
        // Expected: Operation cancelled, navigate back to AbsenceListScreen.

        System.out.println("\nPress Enter to continue to next scenario...");
        scanner.nextLine();

        // Scenario 4: Database connection interrupted during save
        System.out.println("\n--- SCENARIO 4: Connection to SMOS server (database) interrupted ---");
        database.setSimulateConnectionFailure(true); // Simulate database connection failure

        absenceListScreen.onAbsenceSelected(selectedAbsenceId); // Re-open form
        justificationForm.fillJustificationData("2023-10-27"); // Valid data

        // Admin -> JustForm : clickSave()
        justificationForm.onSaveButtonClick();
        // Expected: Persistence error, error message displayed.

        System.out.println("\n--- End of Simulation ---");
        scanner.close();
    }
}