/**
 * This is the main entry point for the Tag Management application.
 * It initializes the Swing GUI on the Event Dispatch Thread (EDT)
 * and creates the main application window (TagManagerFrame).
 */
import javax.swing.SwingUtilities;
public class Main {
    /**
     * Main method to start the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are performed on the Event Dispatch Thread (EDT).
        SwingUtilities.invokeLater(() -> {
            // Create a shared TagService instance.
            TagService tagService = new TagService();
            // Create and display the main application window.
            TagManagerFrame frame = new TagManagerFrame("Tag Management System", tagService);
            frame.setVisible(true);
        });
    }
}