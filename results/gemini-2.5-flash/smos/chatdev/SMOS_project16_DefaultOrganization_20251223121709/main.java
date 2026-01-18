'''
Main class to launch the AdminDashboard application.
It initializes the Swing GUI on the Event Dispatch Thread (EDT).
'''
import javax.swing.SwingUtilities;
public class Main {
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            AdminDashboard adminDashboard = new AdminDashboard();
            adminDashboard.setVisible(true);
        });
    }
}