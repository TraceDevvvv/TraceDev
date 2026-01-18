'''
This is the main application class for the RicercaBeneCulturale (Cultural Object Search) system.
It sets up the main JFrame and integrates the CulturalObjectSearchPanel.
'''
import javax.swing.*;
import java.awt.*;
/**
 * Main application class for the RicercaBeneCulturale (Cultural Object Search) system.
 * This class initializes the graphical user interface by creating a JFrame and adding
 * a CulturalObjectSearchPanel to it.
 */
public class RicercaBeneCulturaleApp extends JFrame {
    /**
     * Constructor for the RicercaBeneCulturaleApp.
     * Initializes the main application window and its components.
     */
    public RicercaBeneCulturaleApp() {
        // Set the title of the JFrame
        super("Ricerca Bene Culturale - Cultural Object Search");
        // Set the default close operation for the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set the preferred size of the frame
        setPreferredSize(new Dimension(800, 600));
        // Create an instance of the search panel
        CulturalObjectSearchPanel searchPanel = new CulturalObjectSearchPanel();
        // Add the search panel to the content pane of the frame
        add(searchPanel, BorderLayout.CENTER);
        // Pack the frame to size its components
        pack();
        // Center the frame on the screen
        setLocationRelativeTo(null);
        // Make the frame visible
        setVisible(true);
    }
    /**
     * The main method, entry point of the application.
     * It creates an instance of RicercaBeneCulturaleApp on the Event Dispatch Thread (EDT).
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure that the GUI is created and updated on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(RicercaBeneCulturaleApp::new);
    }
}