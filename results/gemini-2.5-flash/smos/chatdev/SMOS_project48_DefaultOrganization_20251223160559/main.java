'''
Main class to run the Administrator Dashboard application.
This class serves as the entry point for the GUI application.
'''
import javax.swing.SwingUtilities;
public class Main {
    /**
     * The main method to start the application.
     * It initializes the ReportCardService and the AdminDashboardFrame on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            ReportCardService reportCardService = new ReportCardService();
            AdminDashboardFrame adminFrame = new AdminDashboardFrame(reportCardService);
            adminFrame.setVisible(true);
        });
    }
}