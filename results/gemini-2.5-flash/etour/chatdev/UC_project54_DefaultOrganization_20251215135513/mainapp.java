/**
 * This is the main application class for the Comment Editor.
 * It sets up the JFrame and hosts the CommentEditorPanel.
 * It initializes a simulated ETourServer with some sample data.
 */
import javax.swing.*;
import java.awt.*;
public class MainApp extends JFrame {
    private ETourServer etourServer;
    private CommentEditorPanel commentEditorPanel;
    /**
     * Constructor for the MainApp.
     * Initializes the simulated server and the UI panel.
     */
    public MainApp() {
        super("ETour Comment Editor"); // Set the window title
        // Initialize simulated backend server
        etourServer = ETourServer.getInstance();
        etourServer.initializeData(); // Populate with some sample data
        // Set up the main window (JFrame)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(800, 600); // Initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        // Create and add the CommentEditorPanel to the frame
        commentEditorPanel = new CommentEditorPanel(etourServer);
        add(commentEditorPanel);
        // Make the window visible
        setVisible(true);
        // Load a sample feedback for demonstration after the panel is displayed
        // We'll assume the tourist is viewing details of Site 1 and wants to edit Feedback 1.
        // In a real application, the site ID and feedback ID would come from prior navigation.
        SwingUtilities.invokeLater(() -> {
            commentEditorPanel.loadFeedback(1); 
        });
    }
    /**
     * The main method to start the application.
     * It ensures the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new MainApp();
        });
    }
}