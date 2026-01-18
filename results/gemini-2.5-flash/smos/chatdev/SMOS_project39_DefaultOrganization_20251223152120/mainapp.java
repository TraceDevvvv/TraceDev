/*
 * Main entry point for the "View Note Details" application.
 * This class simulates an administrator viewing note details by creating a sample Note
 * and displaying it using the NoteDetailView GUI.
 */
import javax.swing.SwingUtilities;
import javax.swing.JOptionPane; // Import for error dialog
import java.time.LocalDate;
public class MainApp {
    /**
     * The main method to start the application.
     * This method is responsible for setting up and running the GUI on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure that GUI updates and creation are performed on the Event Dispatch Thread (EDT)
        // This is a requirement for Swing applications to maintain thread safety.
        SwingUtilities.invokeLater(() -> {
            // Simulate a logged-in administrator clicking a "Details" button for a note.
            // Create a sample Note object with predefined details.
            try {
                Note sampleNote = new Note(
                    "Alice Smith",
                    "Student showed significant improvement in problem-solving skills this quarter. " +
                    "She actively participates in class discussions and has a positive attitude towards learning. " +
                    "Needs to work on time management for larger projects. Discussed strategies during parent-teacher conference.",
                    "John Doe",
                    LocalDate.of(2023, 10, 26) // Example date
                );
                // Instantiate and display the NoteDetailView with the sample note.
                // This fulfills the "Show a form with the details of the note" requirement.
                new NoteDetailView(sampleNote);
            } catch (IllegalArgumentException e) {
                // Handles cases where note data is invalid (e.g., null fields),
                // showing an error message to the user and printing to console.
                JOptionPane.showMessageDialog(null, "Error creating note: " + e.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                System.err.println("Error: " + e.getMessage());
            }
        });
    }
}