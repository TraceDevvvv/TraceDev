'''
Main application class for the Class Management System.
This class sets up the main JFrame and integrates the ClassFormPanel
to allow administrators to insert new classes.
It represents the entry point for the GUI application.
'''
import javax.swing.*;
import java.awt.*;
public class ClassManagementApp extends JFrame {
    private ClassArchive classArchive; // The central archive for classes
    /**
     * Constructs the main application window.
     * Initializes the ClassArchive and adds the ClassFormPanel to the frame.
     */
    public ClassManagementApp() {
        super("Class Management System - Administrator View"); // Set window title
        classArchive = new ClassArchive(); // Initialize the in-memory class archive
        // Set up the main frame properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation on window close
        setSize(400, 300); // Set initial window size
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout()); // Use BorderLayout for the frame
        // Create and add the ClassFormPanel
        ClassFormPanel formPanel = new ClassFormPanel(classArchive);
        add(formPanel, BorderLayout.CENTER); // Add the form panel to the center of the frame
    }
    /**
     * Main method to launch the Class Management Application.
     * It ensures that the GUI is created and updated on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread for thread safety in Swing
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new ClassManagementApp().setVisible(true); // Create and show the application window
            }
        });
    }
}