/**
 * Entry point for the Banner Insertion Application
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure GUI is created on Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            try {
                // Create and display the main application window
                BannerInsertionApp app = new BannerInsertionApp();
                app.setVisible(true);
            } catch (Exception e) {
                System.err.println("Failed to start application: " + e.getMessage());
                e.printStackTrace();
            }
        });
    }
}