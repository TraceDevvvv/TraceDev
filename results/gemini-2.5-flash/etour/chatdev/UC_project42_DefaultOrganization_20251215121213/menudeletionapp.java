/*
Main application class for the Menu Deletion System.
This class sets up the GUI and handles user interactions to delete a daily menu.
It implements the use case "ELIMINAMENÙ" by providing a form to select a day
and confirm its deletion.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class MenuDeletionApp extends JFrame {
    private JComboBox<String> dayComboBox;
    private JButton deleteButton;
    private MenuManager menuManager;
    /**
     * Constructor for the MenuDeletionApp.
     * Initializes the GUI components and sets up the application window.
     * Assumes the "Point Of Restaurant Operator has successfully authenticated to the system".
     */
    public MenuDeletionApp() {
        // Set the title of the application window
        super("Menu Deletion System - ELIMINAMENÙ");
        // Initialize the MenuManager, which handles the simulated backend deletion logic.
        menuManager = new MenuManager();
        // Configure the main window properties
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application when window is closed
        setSize(450, 200); // Set initial window dimensions (width, height)
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize and arrange the user interface components
        initUI();
    }
    /**
     * Initializes all user interface components.
     * This method creates the dropdown for selecting days, the delete button,
     * and arranges them using a GridBagLayout for flexible positioning.
     */
    private void initUI() {
        // Create a JPanel to hold all GUI components, using GridBagLayout for organization
        JPanel panel = new JPanel(new GridBagLayout());
        // Add padding around the panel's content
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // GridBagConstraints are used to control the layout of components within GridBagLayout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10); // Define spacing between components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components will expand horizontally
        // Label for the day selection dropdown, mapping to Use Case Step 2: "Displays a form"
        JLabel dayLabel = new JLabel("Select Day to Delete Menu:");
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        panel.add(dayLabel, gbc);
        // Dropdown (JComboBox) containing the seven days of the week, as per Use Case Step 2
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        panel.add(dayComboBox, gbc);
        // Delete button, serves as the "submit form" action from Use Case Step 3
        deleteButton = new JButton("Delete Daily Menu");
        gbc.gridx = 0; // Start at Column 0
        gbc.gridy = 1; // Row 1
        gbc.gridwidth = 2; // Span across two columns
        panel.add(deleteButton, gbc);
        // Add an ActionListener to the delete button to handle user clicks
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, initiate the menu deletion process
                deleteSelectedMenu();
            }
        });
        // Add the configured panel to the frame's content pane
        add(panel);
    }
    /**
     * Handles the entire process of deleting the selected menu.
     * This method fetches the selected day, prompts for confirmation,
     * calls the backend service for deletion, and displays the outcome
     * to the user.
     */
    private void deleteSelectedMenu() {
        // Retrieve the currently selected day from the combo box
        String selectedDay = (String) dayComboBox.getSelectedItem();
        // Basic validation: ensure a day is selected (though JComboBox usually guarantees this)
        if (selectedDay == null || selectedDay.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Please select a day to delete.", "Input Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Use Case Step 4: Calls to confirm the deletion.
        // A confirmation dialog box is displayed to the operator.
        int confirmResult = JOptionPane.showConfirmDialog(
            this, // Parent component for the dialog
            "Are you sure you want to delete the menu for " + selectedDay + "?\nThis action cannot be undone.",
            "Confirm Menu Deletion", // Dialog title
            JOptionPane.YES_NO_OPTION, // Show Yes and No buttons
            JOptionPane.WARNING_MESSAGE // Display a warning icon
        );
        // Use Case Step 5: Confirm the operation.
        // Check if the operator clicked 'Yes'.
        if (confirmResult == JOptionPane.YES_OPTION) {
            // Use Case Step 6: Delete the daily menu selection.
            // Call the MenuManager to perform the simulated deletion.
            boolean success = menuManager.deleteMenu(selectedDay);
            if (success) {
                // Exit condition: The system shall notify the successful elimination.
                JOptionPane.showMessageDialog(this, "Menu for " + selectedDay + " successfully deleted!", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            } else {
                // This branch handles backend errors (e.g., network issues, DB errors, or simulated failures).
                // This covers the implicit "failure to delete" scenario, potentially related to "Interruption of the connection to the server ETOUR".
                JOptionPane.showMessageDialog(this, "Failed to delete menu for " + selectedDay + ".\nPlease try again later or check server connection.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Exit condition: Restaurant Point Of Operator cancels the operation.
            // Notify the operator that the deletion was cancelled.
            JOptionPane.showMessageDialog(this, "Menu deletion for " + selectedDay + " cancelled by operator.", "Operation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Main method to start the application.
     * It ensures that the GUI creation and updates are safely handled
     * on the Event Dispatch Thread (EDT) according to Swing best pract.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Create an instance of the main application window
                MenuDeletionApp app = new MenuDeletionApp();
                // Make the window visible to the user
                app.setVisible(true);
            }
        });
    }
}