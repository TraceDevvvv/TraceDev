/**
 * Main entry point for the Justification Management System.
 * This program simulates an admin interface to delete a justification.
 * It includes a simple GUI built with Swing and simulates a backend connection.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JustificationManager {
    public static void main(String[] args) {
        // Ensure GUI creation is done on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("Justification Management System - Admin");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(500, 300);
        frame.setLayout(new BorderLayout());
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Justification Details View"));
        frame.add(headerPanel, BorderLayout.NORTH);
        // Center panel to display justification details (simulated)
        JPanel detailsPanel = new JPanel(new GridLayout(4, 1, 10, 10));
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        detailsPanel.add(new JLabel("Justification ID: JUST001"));
        detailsPanel.add(new JLabel("Date: 2023-10-05"));
        detailsPanel.add(new JLabel("Reason: Annual leave request"));
        detailsPanel.add(new JLabel("Status: Pending"));
        frame.add(detailsPanel, BorderLayout.CENTER);
        // Button panel for actions
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton deleteButton = new JButton("Delete");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(deleteButton);
        buttonPanel.add(cancelButton);
        frame.add(buttonPanel, BorderLayout.SOUTH);
        // Simulate backend connection status
        JLabel statusLabel = new JLabel("Status: Connected to SMOS server");
        frame.add(statusLabel, BorderLayout.NORTH);
        // Action listeners for buttons
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirmation = JOptionPane.showConfirmDialog(frame,
                        "Are you sure you want to delete this justification?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION);
                if (confirmation == JOptionPane.YES_OPTION) {
                    // Simulate deletion process
                    boolean deletionSuccess = performDeletion();
                    if (deletionSuccess) {
                        JOptionPane.showMessageDialog(frame,
                                "Justification deleted successfully. Returning to registry screen.",
                                "Success",
                                JOptionPane.INFORMATION_MESSAGE);
                        // In a real application, we would navigate to the registry screen here.
                        // For this simulation, we'll just show a message and close.
                        frame.dispose();
                        showRegistryScreen();
                    } else {
                        JOptionPane.showMessageDialog(frame,
                                "Deletion failed due to server interruption.",
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                        statusLabel.setText("Status: Connection to SMOS server interrupted");
                    }
                }
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(frame,
                        "Operation cancelled by administrator.",
                        "Cancelled",
                        JOptionPane.WARNING_MESSAGE);
                // Simulate returning to registry screen
                frame.dispose();
                showRegistryScreen();
            }
        });
        frame.setVisible(true);
    }
    /**
     * Simulates the deletion of a justification.
     * Returns true if deletion is successful, false if there's a server interruption.
     */
    private static boolean performDeletion() {
        // Simulate a network call. In a real application, this would be a database or API call.
        try {
            // Simulate a 2-second delay for network operation
            Thread.sleep(2000);
            // Randomly simulate a server interruption for demonstration (10% chance)
            if (Math.random() < 0.1) {
                throw new RuntimeException("SMOS server connection interrupted");
            }
            return true; // Deletion successful
        } catch (InterruptedException e) {
            Thread.currentThread().interrupt();
            return false;
        } catch (RuntimeException e) {
            // Simulated server interruption
            return false;
        }
    }
    /**
     * Displays a simple registry screen after deletion or cancellation.
     * This is a placeholder for the actual registry screen.
     */
    private static void showRegistryScreen() {
        JFrame registryFrame = new JFrame("Registry Screen");
        registryFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        registryFrame.setSize(500, 300);
        registryFrame.add(new JLabel("Registry Screen - List of justifications would appear here", SwingConstants.CENTER));
        registryFrame.setVisible(true);
    }
}