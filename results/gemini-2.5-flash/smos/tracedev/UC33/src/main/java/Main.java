import application.DelayEliminationService;
import infrastructure.LateEntryRepositoryImpl;
import presentation.RegistryScreenController;
import java.util.Date;
import java.util.Calendar;

/**
 * Main class to demonstrate the "Eliminate Delay" use case based on the provided
 * Class and Sequence Diagrams. This acts as the entry point and simulates the
 * Administrator's interactions with the system.
 *
 * Entry Condition: Administrator IS logged in to the system. (Assumed by running Main)
 * Entry Condition: UseCase "SplitTaTtAlloreGloregistration" HAS been executed. (Assumed for this demonstration)
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("Starting 'Eliminate Delay' Use Case Demonstration...");

        // 1. Setup the application context (Dependency Injection)
        // Infrastructure Layer
        LateEntryRepositoryImpl lateEntryRepository = new LateEntryRepositoryImpl();

        // Application Layer
        DelayEliminationService delayEliminationService = new DelayEliminationService(lateEntryRepository);

        // Presentation Layer
        RegistryScreenController registryScreenController = new RegistryScreenController(delayEliminationService);

        // --- Simulate Administrator's actions based on the Sequence Diagram ---

        // Simulate Admin selecting a date
        Calendar cal = Calendar.getInstance();
        cal.add(Calendar.DAY_OF_MONTH, -1); // Get yesterday's date
        Date selectedDate = cal.getTime();

        System.out.println("\n--- Step 1: Admin selects a date to view late entries ---");
        registryScreenController.selectDate(selectedDate); // Admin -> UI : selectDate()

        // Give some feedback or show current state (implicit from displayLateEntries)
        // In this simulation, let's assume the user sees "LE002" and "LE004" from yesterday's data.
        // Let's pick LE002 to remove.

        System.out.println("\n--- Step 2: Admin removes a specific late entry ---");
        String lateEntryIdToRemove = "LE002"; // Example ID from pre-populated data
        registryScreenController.handleRemoveAction(lateEntryIdToRemove); // Admin -> UI : handleRemoveAction()

        System.out.println("\n--- Step 3: Admin saves changes ---");
        registryScreenController.saveChangesClicked(); // Admin -> UI : saveChangesClicked()

        // --- Verify the outcome (optional, but good for demonstration) ---
        System.out.println("\n--- Verification: Re-checking late entries for the same date ---");
        registryScreenController.selectDate(selectedDate); // Admin re-selects date to see updated list

        System.out.println("\nDemonstration finished.");
    }
}