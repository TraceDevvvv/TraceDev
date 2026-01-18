/**
 * Main entry point for the DeleteMenu application.
 * This program provides a GUI for a restaurant operator to delete a daily menu.
 * It simulates authentication, displays a form with days of the week,
 * handles selection, confirmation, and deletion with appropriate error handling.
 * 
 * @version 1.0
 * @since 2024
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
public class DeleteMenu {
    /**
     * Instance variable for per-session server connection state.
     * Each operator session has its own connection status.
     */
    private boolean serverConnected = true;
    /**
     * Instance variable for per-session daily menus data.
     * Each operator session manages its own menu data independently.
     */
    private Map<String, String> dailyMenus = new HashMap<>();
    // GUI components
    private JFrame mainFrame;
    private JComboBox<String> dayComboBox;
    private JButton deleteButton, cancelButton;
    private JLabel statusLabel;
    public DeleteMenu() {
        initializeData();
        createGUI();
        simulateAuthentication();
    }
    /**
     * Initialize sample daily menus for demonstration.
     * Uses instance variable to ensure data is session-specific.
     */
    private void initializeData() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            dailyMenus.put(day, "Special menu for " + day);
        }
        System.out.println("Sample menus initialized for this session.");
    }
    /**
     * Simulate that the operator is already authenticated.
     * In a real application, this would involve checking credentials.
     */
    private void simulateAuthentication() {
        System.out.println("Operator authenticated successfully for this session.");
        enableDeletionFunctionality();
    }
    /**
     * Enable the delete functionality (step 1 of use case).
     */
    private void enableDeletionFunctionality() {
        deleteButton.setEnabled(true);
        dayComboBox.setEnabled(true);
        statusLabel.setText("Select a day to delete its menu.");
    }
    /**
     * Create and display the GUI with improved layout management.
     * Uses GridBagLayout for better component arrangement and responsiveness.
     */
    private void createGUI() {
        mainFrame = new JFrame("Delete Daily Menu");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new BorderLayout());
        JPanel panel = new JPanel();
        panel.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Step 2: Display form with days of the week
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel promptLabel = new JLabel("Choose a day:");
        panel.add(promptLabel, gbc);
        gbc.gridx = 1;
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setEnabled(false); // disabled until authenticated
        panel.add(dayComboBox, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout());
        // Delete button (step 1, 3)
        deleteButton = new JButton("Delete Menu");
        deleteButton.setEnabled(false); // disabled until authenticated
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeleteAction();
            }
        });
        buttonPanel.add(deleteButton);
        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelOperation();
            }
        });
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        panel.add(buttonPanel, gbc);
        statusLabel = new JLabel("Please wait... authenticating.", SwingConstants.CENTER);
        gbc.gridy = 2;
        panel.add(statusLabel, gbc);
        mainFrame.add(panel, BorderLayout.CENTER);
        mainFrame.pack();
        mainFrame.setLocationRelativeTo(null);
        mainFrame.setVisible(true);
    }
    /**
     * Handle delete button click (steps 3-6).
     */
    private void handleDeleteAction() {
        // Step 3: Get selected day
        String selectedDay = (String) dayComboBox.getSelectedItem();
        if (selectedDay == null) {
            JOptionPane.showMessageDialog(mainFrame, "Please select a day.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Step 4: Call to confirm deletion
        int confirm = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to delete the menu for " + selectedDay + "?",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION);
        // Step 5: Confirm the operation
        if (confirm == JOptionPane.YES_OPTION) {
            // Step 6: Delete the daily menu selection
            deleteMenu(selectedDay);
        } else {
            statusLabel.setText("Deletion cancelled.");
        }
    }
    /**
     * Delete the menu for the given day and handle exit conditions.
     * Uses instance variables to ensure per-session data isolation.
     * @param day The day whose menu is to be deleted.
     */
    private void deleteMenu(String day) {
        // Check server connection interruption (exit condition)
        if (!serverConnected) {
            JOptionPane.showMessageDialog(mainFrame,
                    "Connection to server ETOUR interrupted. Deletion failed.",
                    "Server Error",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Server connection error.");
            return;
        }
        // Simulate deletion by removing from instance map
        if (dailyMenus.containsKey(day)) {
            dailyMenus.remove(day);
            System.out.println("Menu for " + day + " deleted in this session.");
            // Exit condition: notify success
            JOptionPane.showMessageDialog(mainFrame,
                    "Menu for " + day + " successfully deleted.",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText("Menu for " + day + " deleted successfully.");
        } else {
            JOptionPane.showMessageDialog(mainFrame,
                    "Menu for " + day + " not found.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Menu not found.");
        }
    }
    /**
     * Handle cancellation (exit condition: operator cancels).
     */
    private void cancelOperation() {
        int confirm = JOptionPane.showConfirmDialog(mainFrame,
                "Are you sure you want to cancel the operation?",
                "Cancel Operation",
                JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            statusLabel.setText("Operation cancelled.");
            // Optionally reset the form
            dayComboBox.setSelectedIndex(0);
        }
    }
    /**
     * Main method to launch the application.
     * Creates independent session instances.
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                // Create new instance for this operator session
                new DeleteMenu();
            }
        });
    }
}