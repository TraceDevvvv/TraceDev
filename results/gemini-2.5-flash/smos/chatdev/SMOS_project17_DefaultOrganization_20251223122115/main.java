'''
Main class to launch the Address Management application.
This class serves as the entry point for the GUI application.
'''
public class Main {
    /**
     * The main method, which is the entry point of the application.
     * It sets up the Swing GUI to run on the Event Dispatch Thread (EDT)
     * and initializes the LoginScreen.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        // for thread safety and responsiveness.
        javax.swing.SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create and show the login screen.
                // This is the first screen the user interacts with.
                new LoginScreen();
            }
        });
    }
}