'''
Main application class for the Digital Register system.
This class sets up the main JFrame and initializes the DigitalRegisterPanel,
serving as the entry point for the administrator to view academic registers.
It assumes the administrator is already logged in, as per the simplified scope
of this task focusing on the 'ViewRegistersList' use case.
'''
import javax.swing.*;
import java.awt.*;
public class RegisterApp extends JFrame {
    /**
     * Constructs the main application frame.
     * Initializes the DigitalRegisterPanel and adds it to the frame.
     */
    public RegisterApp() {
        super("Digital Register System - Administrator View"); // Set window title
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define default close operation
        setSize(1000, 700); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize the RegisterService (simulated backend)
        RegisterService registerService = new RegisterService();
        // Create and add the DigitalRegisterPanel to the frame
        // This panel handles the academic year selection and register display
        DigitalRegisterPanel digitalRegisterPanel = new DigitalRegisterPanel(registerService);
        add(digitalRegisterPanel);
        setVisible(true); // Make the frame visible
    }
    /**
     * Main method to start the Digital Register application.
     * Ensures that Swing components are created and updated on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(RegisterApp::new);
    }
}