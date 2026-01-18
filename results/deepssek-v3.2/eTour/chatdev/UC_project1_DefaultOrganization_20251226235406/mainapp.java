'''
MainApp is the entry point for the Cultural Heritage Management System.
Creates and displays the GUI application for deleting cultural heritage items.
'''
import javax.swing.*;
public class MainApp {
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI components
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set system look and feel for better appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    System.out.println("Error setting look and feel: " + e.getMessage());
                }
                // Create and display the main GUI
                new DeleteCulturalHeritageGUI();
            }
        });
    }
}