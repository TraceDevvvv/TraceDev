/**
Main entry point for the Search Preference Modification application.
Launches the authentication screen to start the application.
*/
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities.invokeLater to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            // Start with authentication frame
            new AuthenticationFrame();
        });
    }
}