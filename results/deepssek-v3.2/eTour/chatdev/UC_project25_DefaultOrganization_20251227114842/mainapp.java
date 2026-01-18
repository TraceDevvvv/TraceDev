/**
 * Main Application Entry Point
 * This class launches the Report Statistics Dashboard application
 */
import javax.swing.SwingUtilities;
public class MainApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread-safe GUI creation
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                ReportDashboard dashboard = new ReportDashboard();
                dashboard.setVisible(true);
                dashboard.setLocationRelativeTo(null); // Center the window
            }
        });
    }
}