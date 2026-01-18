'''
This is the entry point for the Administrator Delay Registration Application.
It initializes and displays the main GUI window.
'''
import javax.swing.SwingUtilities;
import java.time.LocalDate;
public class Main {
    /**
     * The main method, which is the entry point of the Java application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread for thread safety.
        SwingUtilities.invokeLater(() -> {
            // Simulate that a date has been selected prior to this use case.
            // For a real application, this date would come from a previous UI interaction.
            LocalDate selectedDate = LocalDate.now();
            AdminDelayRegistrationApp app = new AdminDelayRegistrationApp("Delay Registration for " + selectedDate.toString(), selectedDate);
            app.setVisible(true);
        });
    }
}