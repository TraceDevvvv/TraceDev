'''
Main entry point for the ViewPersonalStatistic application.
This application demonstrates viewing statistics for Point Of Restaurants operators.
'''
import javax.swing.SwingUtilities;
public class ViewPersonalStatisticApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI components
        SwingUtilities.invokeLater(() -> {
            // Create and display the login frame first
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}