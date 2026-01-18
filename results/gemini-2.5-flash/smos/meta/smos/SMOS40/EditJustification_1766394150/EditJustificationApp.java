import java.time.LocalDate;
import java.util.Optional;
import java.util.Scanner;

/**
 * Main application class that simulates the "EditJustification" use case.
 * This class orchestrates the interactions between the Administrator,
 * the system (represented by JustificationService), and the Justification entity.
 * It demonstrates the flow from preconditions to postconditions as described
 * in the use case.
 */
public class EditJustificationApp {

    private JustificationService justificationService;
    private Administrator currentAdmin;
    private Scanner scanner;

    /**
     * Constructor for the EditJustificationApp.
     * Initializes the JustificationService and a Scanner for user input.
     */
    public EditJustificationApp() {
        this.justificationService = new JustificationService();
        this.scanner = new Scanner(System.in);
    }

    /**
     * Simulates the administrator login process.
     * This method sets the `loggedIn` status of the administrator, fulfilling
     * the first precondition of the use case.
     *
     * @param admin The administrator attempting to log in.
     * @return true if login is successful, false otherwise.
     */
    public boolean loginAdministrator(Administrator admin) {
        // In a real system, this would involve authentication logic (username/password check).
        // For this simulation, we just set the loggedIn status.
        if (admin != null) {
            admin.setLoggedIn(true);
            this.currentAdmin = admin;
            System.out.println("Administrator '" + admin.getUsername() + "' logged in successfully.");
            return true;
        }
        System.out.println("Login failed: Administrator object is null.");
        return false;
    }

    /**
     * Simulates the "viewdetalticaustifica" use case, where the system
     * displays the details of a specific justification. This fulfills the
     * second precondition.
     *
     * @param justificationId The ID of the justification to view.
     * @return An Optional containing the Justification if found, otherwise empty.
     */
    public Optional<Justification> viewJustificationDetails(String justificationId) {
        System.out.println("\n--- Simulating 'viewdetalticaustifica' ---");
        Optional<Justification> justification = justificationService.getJustificationById(justificationId);
        if (justification.isPresent()) {
            System.out.println("Currently viewing details of: " + justification.get());
        } else {
            System.out.println("Justification with ID '" + justificationId + "' not found.");
        }
        return justification;
    }

    /**
     * Executes the "EditJustification" use case flow.
     * This method encapsulates the entire sequence of events and checks preconditions/postconditions.
     *
     * @param admin The administrator performing the action.
     * @param justificationIdToEdit The ID of the justification the administrator intends to edit.
     */
    public void executeEditJustificationUseCase(Administrator admin, String justificationIdToEdit) {
        System.out.println("\n--- Starting 'EditJustification' Use Case ---");

        // Precondition 1: The user must be logged in to the system as an administrator.
        if (admin == null || !admin.isLoggedIn()) {
            System.err.println("Precondition failed: Administrator is not logged in.");
            return;
        }
        System.out.println("Precondition met: Administrator '" + admin.getUsername() + "' is logged in.");

        // Precondition 2: The user has carried out the case of use "viewdetalticaustifica"
        // and the system is viewing the details of a justice.
        Optional<Justification> originalJustificationOpt = viewJustificationDetails(justificationIdToEdit);

        if (originalJustificationOpt.isEmpty()) {
            System.err.println("Precondition failed: Justification with ID '" + justificationIdToEdit + "' not found for viewing/editing.");
            return;
        }
        Justification originalJustification = originalJustificationOpt.get();
        System.out.println("Precondition met: System is viewing details of Justification ID: " + originalJustification.getId());

        // Event Sequence 1: User changes one or more fields of the displayed form: date justification.
        System.out.println("\n--- User Interaction: Changing Justification Date ---");
        System.out.print("Enter new justification date (YYYY-MM-DD) for ID '" + originalJustification.getId() + "': ");
        String newDateString = scanner.nextLine();
        LocalDate newJustificationDate;
        try {
            newJustificationDate = LocalDate.parse(newDateString);
        } catch (java.time.format.DateTimeParseException e) {
            System.err.println("Invalid date format. Please use YYYY-MM-DD. Aborting edit.");
            return;
        }

        // Create a new Justification object with the updated date.
        // The ID remains the same to identify which justification to update.
        Justification updatedJustification = new Justification(originalJustification.getId(), newJustificationDate);
        System.out.println("User changed date to: " + newJustificationDate);

        // Event Sequence 2: Click on "Save"
        System.out.println("User clicked 'Save'.");

        // Event Sequence 3: System changes the justification.
        System.out.println("\n--- System Action: Updating Justification ---");
        try {
            Justification result = justificationService.updateJustification(updatedJustification);
            System.out.println("System successfully changed justification. New details: " + result);

            // Postcondition 1: The justification has been modified.
            System.out.println("Postcondition met: Justification ID '" + result.getId() + "' has been modified.");

            // Postcondition 2: The system returns to the registry screen.
            System.out.println("Postcondition met: System returning to registry screen (simulated).");

            // Postcondition 3: The administrator interrupts the connection to the SMOS server interrupted.
            // This is a bit ambiguous, but can be interpreted as the session ending or a specific
            // connection being closed after the operation. We'll simulate a session logout.
            admin.setLoggedIn(false); // Simulate logout or session termination
            System.out.println("Postcondition met: Administrator '" + admin.getUsername() + "' session interrupted/logged out from SMOS server.");

        } catch (JustificationNotFoundException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Justification could not be updated because it was not found.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error: " + e.getMessage());
            System.err.println("Justification update failed due to invalid input data.");
        } finally {
            // Ensure scanner is closed
            if (scanner != null) {
                scanner.close();
            }
        }
        System.out.println("\n--- 'EditJustification' Use Case Finished ---");
    }

    /**
     * Main method to run the application.
     * Sets up the scenario and executes the use case.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        EditJustificationApp app = new EditJustificationApp();

        // 1. Setup: Create an administrator and simulate login
        Administrator admin = new Administrator("admin001", "AliceAdmin");
        app.loginAdministrator(admin);

        // 2. Define the justification to be edited
        String justificationIdToEdit = "J001"; // Assuming J001 exists from JustificationService's initial data

        // 3. Execute the use case
        app.executeEditJustificationUseCase(admin, justificationIdToEdit);

        // Example of trying to edit a non-existent justification (edge case)
        System.out.println("\n--- Attempting to edit a non-existent justification (J999) ---");
        app.executeEditJustificationUseCase(admin, "J999"); // This will fail at precondition 2
    }
}