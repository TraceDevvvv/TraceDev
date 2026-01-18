'''
Main application class for the Admin Delay Editor.
This class sets up the main JFrame and initializes the EditDelayPanel.
'''
import javax.swing.*;
public class AdminDelayEditorApp extends JFrame {
    /**
     * Constructor for AdminDelayEditorApp.
     * Sets up the main window (JFrame) and adds the EditDelayPanel.
     */
    public AdminDelayEditorApp() {
        setTitle("Admin Delay Editor");
        setSize(450, 300); // Set appropriate size for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Initialize the main panel for editing delays
        EditDelayPanel editDelayPanel = new EditDelayPanel();
        add(editDelayPanel); // Add the panel to the frame
        setVisible(true); // Make the frame visible
    }
    /**
     * Main method to run the application.
     * Ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are handled on the Event Dispatch Thread
        SwingUtilities.invokeLater(AdminDelayEditorApp::new);
    }
}