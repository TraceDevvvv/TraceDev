/**
 * Main class for the Login application.
 * Initializes the GUI on the Event Dispatch Thread (EDT) for thread safety.
 */
public class LoginApp {
    /**
     * Main method to start the Login application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Creates a new LoginFrame instance.
                LoginFrame loginFrame = new LoginFrame();
                // Makes the LoginFrame visible to the user.
                loginFrame.setVisible(true);
            }
        });
    }
}