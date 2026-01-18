/*
The main entry point for the InsertDelayAta application.
This class is responsible for creating and displaying the application's GUI.
*/
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method that starts the Java application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * which is crucial for Swing applications to maintain thread safety and responsiveness.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            new DelayEntryScreen(); // Create and display the main GUI screen
        });
    }
}