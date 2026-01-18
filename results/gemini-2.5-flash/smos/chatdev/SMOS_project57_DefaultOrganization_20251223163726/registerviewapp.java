'''
Main entry point for the Class Register View application.
Initializes the GUI and makes it visible to the user.
'''
import javax.swing.SwingUtilities;
public class RegisterViewApp {
    /**
     * Main method to start the application.
     * It ensures that the GUI creation and updates are done on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            // Create and set up the main frame for the application.
            RegisterViewFrame frame = new RegisterViewFrame();
            frame.setVisible(true); // Make the frame visible to the user.
        });
    }
}