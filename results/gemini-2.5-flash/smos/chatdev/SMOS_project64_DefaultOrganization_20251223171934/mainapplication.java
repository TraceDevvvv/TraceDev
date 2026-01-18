'''
This is the main entry point for the Digital Register Viewer application.
It sets up the main JFrame and integrates the DigitalRegisterViewer panel.
It also simulates the "logged in as direction" precondition.
'''
import javax.swing.*;
import java.awt.*;
public class MainApplication extends JFrame {
    /**
     * Constructor for the MainApplication frame.
     * Initializes the frame properties and adds the DigitalRegisterViewer panel.
     */
    public MainApplication() {
        setTitle("Digital Register Viewer - Direction Access");
        setSize(800, 600); // Set a reasonable size for the window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        // Simulate successful login of a 'Direction' user
        simulateLoginAsDirection();
        // Add the main panel for viewing registers
        add(new DigitalRegisterViewer());
    }
    /**
     * Simulates the precondition: "The user must be logged in to the system as direction".
     * In a real application, this would involve an actual login process.
     * For this use case, we simply assume the user is logged in.
     */
    private void simulateLoginAsDirection() {
        // In a real system, this would involve authentication logic.
        // For this demonstration, we assume successful login as 'Direction'.
        System.out.println("User 'Direction' successfully logged in.");
        // We could potentially store user role in a session or context.
    }
    /**
     * The main method to start the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            MainApplication app = new MainApplication();
            app.setVisible(true); // Make the frame visible
        });
    }
}