/**
 * The main class to run the Teaching Management Application.
 * It creates and displays the MainFrame GUI.
 */
import javax.swing.SwingUtilities;
public class TeachingManagementApp {
    /**
     * Main method to start the application.
     * It ensures that the GUI creation and updates are performed on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT) for thread safety.
        // This is standard practice for Swing applications.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the main application frame
                MainFrame frame = new MainFrame();
                // Make the frame visible to the user
                frame.setVisible(true);
            }
        });
    }
}