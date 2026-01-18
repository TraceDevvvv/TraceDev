/**
 * Handles the display of note details to an administrator.
 * This class ensures that only logged-in administrators can view note information.
 */
public class NoteDetailViewer {

    /**
     * Displays the details of a given note, provided the administrator is logged in.
     * This method simulates the system showing a form with the note's details.
     *
     * @param admin The Administrator attempting to view the note details. Must not be null.
     * @param note The Note object whose details are to be displayed. Must not be null.
     * @throws IllegalArgumentException if the admin or note parameter is null.
     * @throws IllegalStateException if the administrator is not logged in.
     */
    public void displayNoteDetails(Administrator admin, Note note) {
        // Precondition check: Administrator and Note objects must not be null.
        if (admin == null) {
            throw new IllegalArgumentException("Administrator object cannot be null.");
        }
        if (note == null) {
            throw new IllegalArgumentException("Note object cannot be null.");
        }

        // Precondition check: The user must be logged in as an administrator.
        if (!admin.isLoggedIn()) {
            // If the administrator is not logged in, throw an exception or return an error message.
            // For this use case, we'll throw an IllegalStateException as it's a critical precondition.
            throw new IllegalStateException("Administrator must be logged in to view note details.");
        }

        // Simulate the system showing a form with the details of the note.
        System.out.println("\n--- Displaying Note Details ---");
        System.out.println(note.toString()); // Utilizes the Note's toString method for formatted output
        System.out.println("-------------------------------");

        // Postcondition: The system is displaying the details of a note. (Achieved by the print statements above)

        // Postcondition: The administrator interrupts the connection to the SMOS server interrupted.
        // This is a simulated event. In a real system, this might involve handling network errors
        // or specific server communication protocols. For this simulation, we'll just print a message.
        System.out.println("Simulating: Administrator connection to SMOS server interrupted.");
    }
}