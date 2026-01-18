/*
MainApplication.java - Entry point for the Administrator Dashboard application.
This class initializes and displays the main GUI window for the administrator.
*/
import javax.swing.SwingUtilities;
public class MainApplication {
    /**
     * Main method to start the application.
     * It ensures that the GUI creation and updates are performed on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the AdminDashboardFrame
                AdminDashboardFrame frame = new AdminDashboardFrame();
                // Make the frame visible to the user
                frame.setVisible(true);
            }
        });
    }
}