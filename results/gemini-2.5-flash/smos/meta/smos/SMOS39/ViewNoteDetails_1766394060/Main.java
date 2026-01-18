import java.time.LocalDate; // Required for LocalDate in Note object

/**
 * Main class to demonstrate the "ViewNoteDetails" use case.
 * This class orchestrates the creation of necessary objects and simulates the
 * sequence of events described in the use case.
 */
public class Main {

    public static void main(String[] args) {
        System.out.println("--- Starting ViewNoteDetails Application ---");

        // 1. Simulate Administrator login
        Administrator admin = new Administrator("adminUser");
        // The use case states "The user must be logged in to the system as an administrator"
        // We simulate this by calling the login method.
        admin.login("adminPass123"); // Password is a placeholder in this simulation

        // 2. Simulate the "ViewElOnconte" case of use and clicking the "Details" button.
        // This implies a specific note has been selected.
        // Let's create a sample Note object.
        Note sampleNote = null;
        try {
            sampleNote = new Note(
                "John Doe",
                "Student showed significant improvement in mathematics. Needs to focus more on geometry.",
                "Ms. Smith",
                LocalDate.of(2023, 10, 26)
            );
            System.out.println("\n--- Sample Note Created ---");
            System.out.println("Note selected for viewing details.");
        } catch (IllegalArgumentException e) {
            System.err.println("Error creating sample note: " + e.getMessage());
            return; // Exit if note creation fails due to invalid data
        }


        // 3. Create an instance of NoteDetailViewer to handle displaying the details.
        NoteDetailViewer viewer = new NoteDetailViewer();

        // 4. Attempt to display the note details.
        // This simulates the system showing a form with the details.
        try {
            if (sampleNote != null) {
                viewer.displayNoteDetails(admin, sampleNote);
            }
        } catch (IllegalStateException e) {
            System.err.println("\nError displaying note details: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("\nError displaying note details (invalid input): " + e.getMessage());
        }

        // --- Demonstrate an edge case: Administrator not logged in ---
        System.out.println("\n--- Demonstrating Edge Case: Administrator Not Logged In ---");
        Administrator unauthorizedAdmin = new Administrator("guestUser");
        // unauthorizedAdmin.login("wrongPass"); // Do not log in this admin

        try {
            System.out.println("Attempting to view note details with an unauthorized administrator...");
            if (sampleNote != null) {
                viewer.displayNoteDetails(unauthorizedAdmin, sampleNote);
            }
        } catch (IllegalStateException e) {
            System.err.println("Successfully caught expected error: " + e.getMessage());
        } catch (IllegalArgumentException e) {
            System.err.println("Error displaying note details (invalid input): " + e.getMessage());
        }

        // --- Demonstrate an edge case: Null Note ---
        System.out.println("\n--- Demonstrating Edge Case: Null Note ---");
        try {
            System.out.println("Attempting to view details of a null note...");
            viewer.displayNoteDetails(admin, null);
        } catch (IllegalArgumentException e) {
            System.err.println("Successfully caught expected error: " + e.getMessage());
        }

        // --- Demonstrating an edge case: Null Administrator ---
        System.out.println("\n--- Demonstrating Edge Case: Null Administrator ---");
        try {
            System.out.println("Attempting to view details with a null administrator...");
            if (sampleNote != null) {
                viewer.displayNoteDetails(null, sampleNote);
            }
        } catch (IllegalArgumentException e) {
            System.err.println("Successfully caught expected error: " + e.getMessage());
        }


        // Clean up: Administrator logs out (optional for this use case, but good practice)
        admin.logout();

        System.out.println("\n--- ViewNoteDetails Application Finished ---");
    }
}