'''
This is the main class that launches the login application.
It creates an instance of the LoginForm and makes it visible on the event dispatch thread.
'''
import javax.swing.SwingUtilities;
public class LoginApp {
    /**
     * The main method is the entry point of the application.
     * It ensures the GUI creation and updates are done on the Event Dispatch Thread (EDT)
     * for thread safety in Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            new LoginForm().setVisible(true); // Create and display the login form
        });
    }
}