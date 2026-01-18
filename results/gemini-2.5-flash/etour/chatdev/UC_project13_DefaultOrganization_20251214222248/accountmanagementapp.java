/**
 * This is the main application class for managing tourist accounts.
 * It provides a graphical user interface (GUI) for an Agency Operator
 * to activate or deactivate a tourist's account.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
// import java.util.Optional; // REMOVED: This import is not used in the class.
public class AccountManagementApp extends JFrame {
    private TouristAccountService touristService;
    private JList<TouristAccount> touristList;
    private DefaultListModel<TouristAccount> touristListModel;
    private JLabel statusLabel;
    private JLabel selectedTouristNameLabel; // Declare a new JLabel field to display the selected tourist's name
    private JButton activateButton;
    private JButton deactivateButton;
    /**
     * Constructor for the AccountManagementApp.
     * Initializes the service and sets up the GUI.
     */
    public AccountManagementApp() {
        touristService = new TouristAccountService();
        initializeUI();
        loadTouristAccounts();
    }
    /**
     * Initializes all GUI components and lays them out.
     */
    private void initializeUI() {
        setTitle("Tourist Account Management (ACTIVE / DISATTIVAACCOUNTTURISTA)");
        setSize(600, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10));
        // Panel for tourist list
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Select Tourist Account"));
        touristListModel = new DefaultListModel<>();
        touristList = new JList<>(touristListModel);
        touristList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(touristList);
        listPanel.add(scrollPane, BorderLayout.CENTER);
        add(listPanel, BorderLayout.WEST);
        // Selection listener for the list
        touristList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) { // Ensures selection is final
                updateStatusDisplay();
            }
        });
        // Panel for details and actions
        JPanel detailPanel = new JPanel(new GridBagLayout());
        detailPanel.setBorder(BorderFactory.createTitledBorder("Account Details and Actions"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Tourist ID (placeholder for now, actual ID is internal)
        gbc.gridx = 0;
        gbc.gridy = 0;
        detailPanel.add(new JLabel("Selected Tourist:"), gbc);
        gbc.gridx = 1;
        // The original line was: detailPanel.add(new JLabel(""), gbc); // Placeholder, updated by updateStatusDisplay
        // Replaced to explicitly show selected tourist's name for clarity
        selectedTouristNameLabel = new JLabel("None Selected"); // Initialize with a placeholder
        detailPanel.add(selectedTouristNameLabel, gbc);
        // Current Status
        gbc.gridx = 0;
        gbc.gridy = 1;
        detailPanel.add(new JLabel("Current Status:"), gbc);
        gbc.gridx = 1;
        statusLabel = new JLabel("N/A");
        detailPanel.add(statusLabel, gbc);
        // Buttons for activation/deactivation
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        activateButton = new JButton("Activate Account");
        deactivateButton = new JButton("Deactivate Account");
        // Action listeners for buttons
        activateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performStatusChange(true); // Activate
            }
        });
        deactivateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                performStatusChange(false); // Deactivate
            }
        });
        buttonPanel.add(activateButton);
        buttonPanel.add(deactivateButton);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        detailPanel.add(buttonPanel, gbc);
        add(detailPanel, BorderLayout.CENTER);
        // Initially disable buttons until a tourist is selected
        activateButton.setEnabled(false);
        deactivateButton.setEnabled(false);
    }
    /**
     * Loads tourist accounts into the JList from the service.
     * Simulates fetching available tourist accounts.
     */
    private void loadTouristAccounts() {
        // Clear existing items
        touristListModel.clear();
        // Add sample accounts from the service
        for (TouristAccount account : touristService.getAllAccounts()) {
            touristListModel.addElement(account);
        }
        if (!touristListModel.isEmpty()) {
            touristList.setSelectedIndex(0); // Select the first one initially
        }
    }
    /**
     * Updates the status display label, selected tourist name label, and button enabled states
     * based on the currently selected tourist account.
     */
    private void updateStatusDisplay() {
        TouristAccount selectedAccount = touristList.getSelectedValue();
        if (selectedAccount != null) {
            // Update the selected tourist name label
            selectedTouristNameLabel.setText(selectedAccount.getName() + " (ID: " + selectedAccount.getTouristId() + ")"); // Show name and ID
            statusLabel.setText(selectedAccount.isActive() ? "ACTIVE" : "DISABLED");
            activateButton.setEnabled(!selectedAccount.isActive());
            deactivateButton.setEnabled(selectedAccount.isActive());
        } else {
            // Reset if no selection
            selectedTouristNameLabel.setText("None Selected");
            statusLabel.setText("N/A");
            activateButton.setEnabled(false);
            deactivateButton.setEnabled(false);
        }
    }
    /**
     * Handles the process of enabling or disabling a tourist account.
     * Includes confirmation and notification of the outcome.
     *
     * @param newStatus The desired new status for the account (true for active, false for disabled).
     */
    private void performStatusChange(boolean newStatus) {
        TouristAccount selectedAccount = touristList.getSelectedValue();
        if (selectedAccount == null) {
            JOptionPane.showMessageDialog(this, "Please select a tourist account first.",
                    "No Account Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        String action = newStatus ? "activate" : "deactivate";
        String currentStatus = selectedAccount.isActive() ? "active" : "disabled";
        // 2. Asks for confirmation of activation / deactivation.
        int confirmResult = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to " + action + " account for " + selectedAccount.getName() +
                        "? Current status: " + currentStatus + ".",
                "Confirm Operation",
                JOptionPane.YES_NO_OPTION);
        // 3. Confirm the operation.
        if (confirmResult == JOptionPane.YES_OPTION) {
            try {
                // 4. Enable / disable the account of the visitor you selected.
                boolean success = touristService.updateAccountStatus(selectedAccount.getTouristId(), newStatus);
                // Exit condition: Notification of the outcome.
                if (success) {
                    JOptionPane.showMessageDialog(this,
                            "Successfully " + action + "d account for " + selectedAccount.getName() + ".",
                            "Operation Successful", JOptionPane.INFORMATION_MESSAGE);
                    // Refresh the display after successful operation
                    updateStatusDisplay();
                } else {
                    // This scenario would be handled if service returns false for other reasons (e.g., account not found)
                    JOptionPane.showMessageDialog(this,
                            "Failed to " + action + " account for " + selectedAccount.getName() + " because the account was not found.",
                            "Operation Failed", JOptionPane.ERROR_MESSAGE);
                }
            } catch (Exception e) {
                // Exit condition: Interruption of the connection to the server ETOUR.
                JOptionPane.showMessageDialog(this,
                        "Error " + action + "ing account for " + selectedAccount.getName() +
                                ": " + e.getMessage() + "\n(Simulated ETOUR server connection interruption)",
                        "Connection Error", JOptionPane.ERROR_MESSAGE);
            }
        } else {
            // User chose not to confirm, operation cancelled.
            JOptionPane.showMessageDialog(this, "Operation " + action + " cancelled.",
                    "Operation Cancelled", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Main method to run the application.
     *
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are run on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AccountManagementApp().setVisible(true);
            }
        });
    }
}