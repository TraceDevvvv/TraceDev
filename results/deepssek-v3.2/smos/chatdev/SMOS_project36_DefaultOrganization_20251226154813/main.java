/**
 * Main entry point for the InsertJustification application
 * This application allows administrators to insert justifications for absences
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            // Start with login screen
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}