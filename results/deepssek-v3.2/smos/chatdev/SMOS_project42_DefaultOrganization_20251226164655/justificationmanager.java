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
        // Top panel to combine status and header using GridLayout
        JPanel topPanel = new JPanel(new GridLayout(2, 1));
        // Status panel
        JPanel statusPanel = new JPanel();
        JLabel statusLabel = new JLabel("Status: Connected to SMOS server");
        statusPanel.add(statusLabel);
        // Header panel
        JPanel headerPanel = new JPanel();
        headerPanel.add(new JLabel("Justification Details View"));
        // Add both panels to the top
        topPanel.add(statusPanel);
        topPanel.add(headerPanel);
        frame.add(topPanel, BorderLayout.NORTH);
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
                        // Navigate to registry screen
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
                // Return to registry screen
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
        } catch (Exception e) {
            // Generic exception catch to handle any unexpected errors including server interruption
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
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.add(new JLabel("Registry Screen - List of justifications would appear here", SwingConstants.CENTER), BorderLayout.CENTER);
        JButton backButton = new JButton("Back to Justification Details");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                registryFrame.dispose();
                createAndShowGUI();
            }
        });
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(backButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        registryFrame.add(mainPanel);
        registryFrame.setVisible(true);
    }
}