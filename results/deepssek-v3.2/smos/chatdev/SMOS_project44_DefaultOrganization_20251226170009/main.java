/**
 * Main class - Entry point for the Student Monitoring System
 * Launches the GUI application
 */
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(() -> {
            StudentMonitoringGUI gui = new StudentMonitoringGUI();
            gui.setVisible(true);
        });
    }
}