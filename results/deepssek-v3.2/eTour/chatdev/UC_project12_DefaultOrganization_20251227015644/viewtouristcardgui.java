/**
 * Main GUI class for the ViewTouristCard application.
 * Implements the complete use case flow with all required components.
 */
import javax.swing.*;
import java.awt.*;
import javax.swing.SwingUtilities;
public class ViewTouristCardGUI extends JFrame {
    private SearchTouristPanel searchPanel;
    private TouristCardPanel cardPanel;
    private JPanel mainPanel;
    public ViewTouristCardGUI() {
        initializeApplication();
        setupMainWindow();
        // Simulate agency login (entry condition)
        simulateAgencyLogin();
    }
    private void initializeApplication() {
        setTitle("ETOUR System - View Tourist Card");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
    }
    private void setupMainWindow() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        // Create search panel (left side)
        searchPanel = new SearchTouristPanel(this);
        // Create tourist card panel (right side)
        cardPanel = new TouristCardPanel();
        // Add panels to main window
        mainPanel.add(searchPanel, BorderLayout.WEST);
        mainPanel.add(cardPanel, BorderLayout.CENTER);
        // Add some padding
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(mainPanel);
    }
    /**
     * Simulates agency operator login (entry condition from use case)
     */
    private void simulateAgencyLogin() {
        JOptionPane.showMessageDialog(this,
            "Agency Operator logged in successfully.\n\n" +
            "Entry Condition Satisfied: The agency has logged.",
            "Login Successful",
            JOptionPane.INFORMATION_MESSAGE);
        // Automatically load initial tourist data
        searchPanel.refreshTouristList();
    }
    /**
     * Displays tourist card for selected tourist with retry mechanism
     * @param tourist The tourist to display
     */
    public void displayTouristCard(Tourist tourist) {
        int maxRetries = 3;
        int attempt = 0;
        while (attempt < maxRetries) {
            attempt++;
            try {
                // Simulate uploading data to selected account (step 2 from use case)
                Tourist detailedTourist = ServerConnectionSimulator.fetchTouristById(tourist.getId());
                if (detailedTourist != null) {
                    cardPanel.displayTourist(detailedTourist);
                    JOptionPane.showMessageDialog(this,
                        "Successfully loaded tourist details.\n" +
                        "Exit Condition: System displays the Tourist card selected.",
                        "Tourist Card Displayed",
                        JOptionPane.INFORMATION_MESSAGE);
                    return; // Success - exit method
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Could not retrieve detailed information for selected tourist.",
                        "Data Error",
                        JOptionPane.WARNING_MESSAGE);
                    return; // Tourist not found - exit method
                }
            } catch (ConnectionInterruptedException e) {
                if (attempt < maxRetries) {
                    int option = JOptionPane.showConfirmDialog(this,
                        "Attempt " + attempt + " failed: " + e.getMessage() + "\nRetry?",
                        "Connection Interrupted",
                        JOptionPane.YES_NO_OPTION,
                        JOptionPane.ERROR_MESSAGE);
                    if (option != JOptionPane.YES_OPTION) {
                        break; // User chose not to retry
                    }
                } else {
                    JOptionPane.showMessageDialog(this,
                        "Failed to fetch tourist details after " + maxRetries + " attempts.\n" +
                        "Interruption Condition: Connection to server ETOUR interrupted.",
                        "Server Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
        // Clear the card display if all retries failed or user canceled
        cardPanel.clear();
    }
    public static void main(String[] args) {
        // Run on Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            try {
                // Set system look and feel for native appearance
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                // Fall back to default look and feel
            }
            ViewTouristCardGUI app = new ViewTouristCardGUI();
            app.setVisible(true);
        });
    }
}