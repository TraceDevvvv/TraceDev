// Main entry point for the FeedbackAlreadyReleased application.
// This application demonstrates a use case where a user tries to submit
// feedback for a site but is notified they've already submitted feedback.
package feedbacksystem;
import javax.swing.SwingUtilities;
/**
 * Main entry point for the FeedbackAlreadyReleased application.
 * This application demonstrates a use case where a user tries to submit
 * feedback for a site but is notified they've already submitted feedback.
 */
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Create and display the main application frame
                MainApplicationFrame frame = new MainApplicationFrame();
                frame.setVisible(true);
            } catch (Exception e) {
                System.err.println("Error starting application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}