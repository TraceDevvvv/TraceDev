import javax.swing.SwingUtilities;
/**
 * Main.java
 * Entry point for the Report Card Viewing System application
 * Launches the login screen for Direction role
 */
public class Main {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}