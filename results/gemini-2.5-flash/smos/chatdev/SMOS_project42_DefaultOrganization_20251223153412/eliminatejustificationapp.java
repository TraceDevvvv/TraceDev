/*
This is the main application class for the Eliminate Justification feature.
It provides a graphical user interface (GUI) for an administrator to log in,
view a justification, and then initiate its deletion.
It simulates various scenarios including successful deletion, administrator
interruption, and SMOS server connection issues.
*/
package com.chatdev.eliminatejustification.app;
import com.chatdev.eliminatejustification.models.Justification;
import com.chatdev.eliminatejustification.serv.AdminAuthService;
import com.chatdev.eliminatejustification.serv.JustificationService;
import com.chatdev.eliminatejustification.serv.SMOSConnectionService;
import com.chatdev.eliminatejustification.exceptions.SMOSConnectionException;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class EliminateJustificationApp extends JFrame {
    // Serv
    private AdminAuthService adminAuthService;
    private JustificationService justificationService;
    private SMOSConnectionService smosConnectionService;
    // GUI Components
    private JLabel statusLabel;
    private JTextArea justificationDetailsArea;
    private JButton loginButton;
    private JButton viewJustificationButton;
    private JButton deleteJustificationButton;
    private JButton smosConnectToggle_Button;
    // Application state
    private Justification currentJustification;
    private boolean isViewingJustification;
    /**
     * Constructor for the EliminateJustificationApp.
     * Initializes the serv and sets up the GUI.
     */
    public EliminateJustificationApp() {
        super("Eliminate Justification System");
        adminAuthService = new AdminAuthService();
        smosConnectionService = new SMOSConnectionService();
        justificationService = new JustificationService(smosConnectionService);
        currentJustification = null;
        isViewingJustification = false;
        setupUI();
        updateButtonStates();
    }
    /**
     * Sets up all the graphical user interface components.
     */
    private void setupUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // Status Panel
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Status: Please log in as administrator.");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.NORTH);
        // Justification Details Panel
        JPanel detailsPanel = new JPanel(new BorderLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Justification Details"));
        justificationDetailsArea = new JTextArea(10, 30);
        justificationDetailsArea.setEditable(false);
        justificationDetailsArea.setLineWrap(true);
        justificationDetailsArea.setWrapStyleWord(true);
        detailsPanel.add(new JScrollPane(justificationDetailsArea), BorderLayout.CENTER);
        add(detailsPanel, BorderLayout.CENTER);
        // Control Panel
        JPanel controlPanel = new JPanel();
        controlPanel.setLayout(new GridLayout(4, 1, 5, 5)); // 4 rows, 1 column with gaps
        loginButton = new JButton("Simulate Admin Login");
        viewJustificationButton = new JButton("View Justification Details");
        deleteJustificationButton = new JButton("Delete Justification");
        smosConnectToggle_Button = new JButton("Simulate SMOS Disconnect");
        controlPanel.add(loginButton);
        controlPanel.add(viewJustificationButton);
        controlPanel.add(deleteJustificationButton);
        controlPanel.add(smosConnectToggle_Button);
        add(controlPanel, BorderLayout.EAST);
        // Add action listeners
        loginButton.addActionListener(e -> simulateAdminLogin());
        viewJustificationButton.addActionListener(e -> viewJustification());
        deleteJustificationButton.addActionListener(e -> deleteJustification());
        smosConnectToggle_Button.addActionListener(e -> toggleSMOSConnection());
        setLocationRelativeTo(null); // Center the window
        setVisible(true);
    }
    /**
     * Updates the enabled/disabled state of buttons based on the application's current state.
     */
    private void updateButtonStates() {
        boolean isAdminLoggedIn = adminAuthService.isLoggedIn();
        loginButton.setText(isAdminLoggedIn ? "Simulate Admin Logout" : "Simulate Admin Login");
        viewJustificationButton.setEnabled(isAdminLoggedIn);
        deleteJustificationButton.setEnabled(isAdminLoggedIn && isViewingJustification && currentJustification != null);
        smosConnectToggle_Button.setText(smosConnectionService.isSMOSConnected() ? "Simulate SMOS Disconnect" : "Simulate SMOS Connect");
    }
    /**
     * Simulates the administrator login/logout process.
     * Toggles the login status and updates the UI accordingly.
     * Precondition: None.
     * Postcondition: Administrator login status is toggled, UI updated.
     */
    private void simulateAdminLogin() {
        if (adminAuthService.isLoggedIn()) {
            adminAuthService.logout();
            statusLabel.setText("Status: Logged out. Please log in as administrator.");
            isViewingJustification = false;
            currentJustification = null;
            justificationDetailsArea.setText("");
        } else {
            adminAuthService.login();
            statusLabel.setText("Status: Administrator logged in successfully.");
        }
        updateButtonStates();
    }
    /**
     * Simulates viewing the details of a justification.
     * This method now uses the JustificationService to retrieve the justification,
     * reflecting a more layered architecture.
     * Requires the user to be logged in as an administrator.
     * Precondition: User logged in as administrator.
     * Postcondition: Justification details are displayed if successful, UI updated.
     */
    private void viewJustification() {
        if (!adminAuthService.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Error: You must be logged in as an administrator to view justifications.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate fetching a justification from the service.
        // For this example, we assume we want to view a justification with a hardcoded ID "J1001"
        currentJustification = justificationService.getJustificationById("J1001");
        if (currentJustification != null) {
            isViewingJustification = true;
            justificationDetailsArea.setText(
                    "ID: " + currentJustification.getId() + "\n" +
                            "Description: " + currentJustification.getDescription() + "\n" +
                            "Status: " + currentJustification.getStatus()
            );
            statusLabel.setText("Status: Viewing details for Justification ID: " + currentJustification.getId() + ". Click 'Delete' to eliminate.");
        } else {
            isViewingJustification = false;
            justificationDetailsArea.setText("No justification found for ID J1001.");
            statusLabel.setText("Status: Failed to retrieve justification J1001. It might have been deleted or doesn't exist.");
            JOptionPane.showMessageDialog(this, "Error: Could not find justification J1001.", "Justification Not Found", JOptionPane.ERROR_MESSAGE);
        }
        updateButtonStates();
    }
    /**
     * Handles the elimination of a justification.
     * This method follows the use case sequence: check preconditions,
     * prompt for confirmation, attempt deletion, and update UI.
     * Preconditions: User logged in as administrator, currently viewing a justification.
     * Postconditions: Justification eliminated (or an error/cancellation message displayed),
     * system returns to a 'registry screen' (simulated by clearing details).
     */
    private void deleteJustification() {
        // Precondition 1: The user must be logged in to the system as an administrator
        if (!adminAuthService.isLoggedIn()) {
            JOptionPane.showMessageDialog(this, "Error: You must be logged in as an administrator to delete justifications.", "Access Denied", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Precondition 2: The user has held the case of use "viewdetalticaustifica" and the System is viewing the details of a justification.
        if (!isViewingJustification || currentJustification == null) {
            JOptionPane.showMessageDialog(this, "Error: No justification is currently being viewed. Please view a justification first.", "No Justification Selected", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Precondition 3: Click on "Delete" -> handled by calling this method.
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete Justification ID: " + currentJustification.getId() + "?\n" +
                        "This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            // User confirmed, proceed with elimination
            try {
                justificationService.deleteJustification(currentJustification);
                statusLabel.setText("Status: Justification ID: " + currentJustification.getId() + " was successfully eliminated. Returning to registry screen.");
                JOptionPane.showMessageDialog(this, "Justification ID: " + currentJustification.getId() + " was eliminated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
                // Simulate returning to the registry screen
                currentJustification = null;
                isViewingJustification = false;
                justificationDetailsArea.setText("");
            } catch (SMOSConnectionException e) {
                // Postcondition: Connection to the SMOS server interrupted
                statusLabel.setText("Error: " + e.getMessage());
                JOptionPane.showMessageDialog(this, e.getMessage() + "\nJustification not deleted.", "SMOS Connection Error", JOptionPane.ERROR_MESSAGE);
            } catch (Exception e) {
                // Generic error during deletion
                statusLabel.setText("Error: Failed to eliminate justification: " + e.getMessage());
                JOptionPane.showMessageDialog(this, "An unexpected error occurred during deletion: " + e.getMessage(), "Deletion Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // Postcondition: The administrator interrupts the operation
            statusLabel.setText("Status: Administrator interrupted the deletion operation for Justification ID: " + currentJustification.getId() + ".");
            JOptionPane.showMessageDialog(this, "Deletion operation cancelled by administrator.", "Operation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
        updateButtonStates();
    }
    /**
     * Toggles the simulated SMOS server connection status.
     * This helps in testing the SMOS connection interrupted scenario.
     */
    private void toggleSMOSConnection() {
        if (smosConnectionService.isSMOSConnected()) {
            smosConnectionService.disconnectSMOS();
            statusLabel.setText("Status: SMOS server connection simulated as DISCONNECTED.");
        } else {
            smosConnectionService.connectSMOS();
            statusLabel.setText("Status: SMOS server connection simulated as CONNECTED.");
        }
        updateButtonStates();
    }
    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> new EliminateJustificationApp());
    }
}