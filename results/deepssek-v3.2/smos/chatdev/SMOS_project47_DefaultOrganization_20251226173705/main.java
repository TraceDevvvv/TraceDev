/**
 * Main entry point for the Student Report Card Editor application.
 * Sets up the main JFrame with CardLayout to switch between
 * student list and edit form views.
 */
import javax.swing.*;
import java.awt.*;
public class Main {
    public static void main(String[] args) {
        // Use SwingUtilities for thread safety
        SwingUtilities.invokeLater(() -> {
            // Create the main frame
            JFrame frame = new JFrame("Student Report Card Editor");
            frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
            frame.setSize(800, 600);
            // Set up CardLayout for switching panels
            CardLayout cardLayout = new CardLayout();
            JPanel mainPanel = new JPanel(cardLayout);
            // Initialize data manager with sample data
            StudentDataManager dataManager = new StudentDataManager();
            dataManager.initializeSampleData();
            // Create panels
            StudentListPanel listPanel = new StudentListPanel(dataManager, cardLayout, mainPanel);
            EditFormPanel editPanel = new EditFormPanel(dataManager, cardLayout, mainPanel);
            // Add panels to main panel
            mainPanel.add(listPanel, "LIST");
            mainPanel.add(editPanel, "EDIT");
            // Add main panel to frame
            frame.add(mainPanel);
            // Center frame on screen
            frame.setLocationRelativeTo(null);
            // Make frame visible
            frame.setVisible(true);
        });
    }
}