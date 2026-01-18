'''
Main application class for the RICERCATURISTA use case.
It sets up the JFrame and adds the TouristSearchPanel to it.
This class serves as the entry point for the GUI application.
'''
import javax.swing.*;
import java.awt.*;
public class TouristSearchApp extends JFrame {
    /**
     * Constructor for TouristSearchApp.
     * Sets up the main application window (JFrame).
     */
    public TouristSearchApp() {
        super("RICERCATURISTA - Tourist Search System"); // Set window title
        // Set default close operation for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set a preferred size for the window
        setPreferredSize(new Dimension(800, 600));
        // Add the TouristSearchPanel to the frame's content pane
        // The TouristSearchPanel contains all the search UI logic
        add(new TouristSearchPanel());
        // Pack the components to their preferred sizes and make the window visible
        pack();
        setLocationRelativeTo(null); // Center the window on the screen
        setVisible(true);
    }
    /**
     * Main method to start the Tourist Search Application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT)
     * as per Swing's thread-safety guidelines.
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event dispatch thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Operator conditions: "The agency has logged."
                // For this simulation, we assume the operator is already logged in
                // and has access to this functionality.
                new TouristSearchApp();
            }
        });
    }
}