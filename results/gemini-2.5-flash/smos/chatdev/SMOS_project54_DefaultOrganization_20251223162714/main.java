/**
 * Main application entry point for the Absence Tracking System.
 * This class initiates the login process, leading to class selection and then absence entry.
 */
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread (EDT)
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Start the application with the login screen
                new LoginScreen();
            }
        });
    }
}