/**
 * Main application class for the RejectEnrollmentStudent system.
 * This is the entry point that launches the GUI for administrators
 * to reject student registration requests.
 */
import javax.swing.SwingUtilities;
public class RejectEnrollmentApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                try {
                    // Create and display the main admin GUI
                    AdminGUI gui = new AdminGUI();
                    gui.setVisible(true);
                } catch (Exception e) {
                    System.err.println("Failed to start application: " + e.getMessage());
                    e.printStackTrace();
                }
            }
        });
    }
}