import javax.swing.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
/**
 * `NewsApp` is the main entry point and application frame for the News Insertion System.
 * It initializes the main JFrame and integrates the `NewsFormPanel` to provide
 * the user interface for inserting news.
 */
public class NewsApp extends JFrame {
    /**
     * Constructs the main application frame.
     * Sets up the frame's title, default close operation, and adds the `NewsFormPanel`.
     */
    public NewsApp() {
        super("ChatDev News Insertion System"); // Set the title bar text for the window
        // Configure the default close operation. JFrame.DO_NOTHING_ON_CLOSE allows custom handling.
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        // Add a WindowListener to intercept the close button click and ask for confirmation.
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                // Display a confirmation dialog before allowing the application to close.
                int confirm = JOptionPane.showConfirmDialog(
                        NewsApp.this, // Parent component for the dialog
                        "Are you sure you want to exit the News Insertion Application?",
                        "Exit Application",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.QUESTION_MESSAGE
                );
                // If the user confirms to exit, terminate the application.
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Exit the Java Virtual Machine.
                }
            }
        });
        // Create an instance of the NewsFormPanel, which encapsulates the news insertion UI.
        NewsFormPanel newsPanel = new NewsFormPanel();
        // Add the news panel to the central content area of the JFrame.
        getContentPane().add(newsPanel);
        pack(); // Adjusts the frame size to fit all its subcomponents
        setLocationRelativeTo(null); // Centers the frame on the screen
        setVisible(true); // Makes the frame and its components visible
    }
    /**
     * The main method is the entry point for the Java application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * for thread safety, which is a best practice for Swing applications.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(NewsApp::new);
    }
}