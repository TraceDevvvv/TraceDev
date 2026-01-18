'''
Main entry point for the ClassViewer application.
This class initializes the Java Swing GUI, handles the login process,
and displays the main class management frame upon successful authentication.
It ensures the application starts on the Event Dispatch Thread (EDT) as required by Swing.
'''
import javax.swing.JFrame;
import javax.swing.JOptionPane;
import javax.swing.SwingUtilities;
import javax.swing.UIManager;
import java.awt.Frame;
public class ClassViewerApp {
    /**
     * The main method, which is the application's entry point.
     * It schedules a job for the event-dispatching thread: creating and showing this application's GUI.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(ClassViewerApp::createAndShowGUI);
    }
    /**
     * Creates and shows the application's GUI.
     * This method first presents a login dialog. If authentication is successful,
     * it proceeds to create and display the main ClassManagementFrame.
     */
    private static void createAndShowGUI() {
        // Set the system look and feel for a native appearance
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }
        // Display login dialog
        LoginDialog loginDialog = new LoginDialog(null); // Parent frame is null as it's the first dialog
        loginDialog.setVisible(true);
        // Check if login was successful
        if (loginDialog.isAuthenticated()) {
            // If authenticated, create and show the main ClassManagementFrame
            ClassManagementFrame frame = new ClassManagementFrame("Class Management System");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600); // Set initial size for the frame
            frame.setLocationRelativeTo(null); // Center the frame on the screen
            frame.setVisible(true);
        } else {
            // If not authenticated, exit the application
            JOptionPane.showMessageDialog(null, "Authentication failed or cancelled. Exiting application.", "Login Failed", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
    }
}