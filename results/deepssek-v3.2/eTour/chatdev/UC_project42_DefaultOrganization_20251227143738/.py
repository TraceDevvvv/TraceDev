/**
 * Main entry point for the DeleteMenu application.
 * This program provides a GUI for a restaurant operator to delete a daily menu.
 * It simulates authentication, displays a form with days of the week,
 * handles selection, confirmation, and deletion with appropriate error handling.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
public class DeleteMenu {
    // Simulated server connection state
    private static boolean serverConnected = true;
    // Simulated data: mapping day name to menu description
    private static Map<String, String> dailyMenus = new HashMap<>();
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
     */
    private void initializeData() {
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        for (String day : days) {
            dailyMenus.put(day, "Special menu for " + day);
        }
        System.out.println("Sample menus initialized.");
    }
    /**
     * Simulate that the operator is already authenticated.
     * In a real application, this would involve checking credentials.
     */
    private void simulateAuthentication() {
        System.out.println("Operator authenticated successfully.");
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
     * Create and display the GUI.
     */
    private void createGUI() {
        mainFrame = new JFrame("Delete Daily Menu");
        mainFrame.setSize(400, 200);
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setLayout(new FlowLayout());
        // Step 2: Display form with days of the week
        JLabel promptLabel = new JLabel("Choose a day:");
        mainFrame.add(promptLabel);
        String[] days = {"Monday", "Tuesday", "Wednesday", "Thursday", "Friday", "Saturday", "Sunday"};
        dayComboBox = new JComboBox<>(days);
        dayComboBox.setEnabled(false); // disabled until authenticated
        mainFrame.add(dayComboBox);
        // Delete button (step 1, 3)
        deleteButton = new JButton("Delete Menu");
        deleteButton.setEnabled(false); // disabled until authenticated
        deleteButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                handleDeleteAction();
            }
        });
        mainFrame.add(deleteButton);
        // Cancel button
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            public void actionPerformed(ActionEvent e) {
                cancelOperation();
            }
        });
        mainFrame.add(cancelButton);
        // Status label for feedback
        statusLabel = new JLabel("Please wait... authenticating.");
        mainFrame.add(statusLabel);
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
        // Simulate deletion by removing from map
        if (dailyMenus.containsKey(day)) {
            dailyMenus.remove(day);
            System.out.println("Menu for " + day + " deleted.");
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
            // Optionally close or reset the form
            dayComboBox.setSelectedIndex(0);
        }
    }
    /**
     * Main method to launch the application.
     */
    public static void main(String[] args) {
        // Use SwingUtilities to ensure thread safety for GUI
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new DeleteMenu();
            }
        });
    }
}