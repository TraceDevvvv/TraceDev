/**
 * JusticeForm provides a GUI for viewing and modifying justice details.
 * Implements the ViewJustificationDetails use case requirements.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class JusticeForm extends JFrame {
    private JusticeService justiceService;
    private Authentication auth;
    private String currentJusticeId;
    // Form components
    private JTextField idField;
    private JTextField employeeField;
    private JTextField dateField;
    private JTextArea reasonArea;
    private JComboBox<String> statusCombo;
    private JButton modifyButton;
    private JButton deleteButton;
    private JButton disconnectButton;
    private JLabel messageLabel;
    /**
     * Constructor initializes the justice details form.
     *
     * @param justiceService The service layer for justice operations.
     * @param auth The authentication system.
     * @param justiceId The ID of the justice to display.
     */
    public JusticeForm(JusticeService justiceService, Authentication auth, String justiceId) {
        this.justiceService = justiceService;
        this.auth = auth;
        this.currentJusticeId = justiceId;
        initializeUI();
        loadJusticeDetails();
    }
    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setTitle("View Justice Details - Administrator");
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE);
        setLayout(new BorderLayout(10, 10));
        setSize(500, 400);
        // Main panel with form
        JPanel formPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Justice ID field
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Justice ID:"), gbc);
        gbc.gridx = 1;
        idField = new JTextField(20);
        idField.setEditable(false);
        formPanel.add(idField, gbc);
        // Employee name field
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Employee Name:"), gbc);
        gbc.gridx = 1;
        employeeField = new JTextField(20);
        formPanel.add(employeeField, gbc);
        // Date field
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Date (YYYY-MM-DD):"), gbc);
        gbc.gridx = 1;
        dateField = new JTextField(20);
        formPanel.add(dateField, gbc);
        // Reason text area
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Reason:"), gbc);
        gbc.gridx = 1;
        reasonArea = new JTextArea(4, 20);
        reasonArea.setLineWrap(true);
        JScrollPane scrollPane = new JScrollPane(reasonArea);
        formPanel.add(scrollPane, gbc);
        // Status combo box
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Status:"), gbc);
        gbc.gridx = 1;
        String[] statusOptions = {"Pending", "Approved", "Rejected"};
        statusCombo = new JComboBox<>(statusOptions);
        formPanel.add(statusCombo, gbc);
        // Message label
        gbc.gridx = 0; gbc.gridy = 5; gbc.gridwidth = 2;
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.BLUE);
        formPanel.add(messageLabel, gbc);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        modifyButton = new JButton("Modify Justice");
        deleteButton = new JButton("Delete Justice");
        disconnectButton = new JButton("Interrupt SMOS Connection");
        modifyButton.addActionListener(new ModifyButtonListener());
        deleteButton.addActionListener(new DeleteButtonListener());
        disconnectButton.addActionListener(new DisconnectButtonListener());
        buttonPanel.add(modifyButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(disconnectButton);
        // Add panels to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // Center the window on screen
        setLocationRelativeTo(null);
    }
    /**
     * Loads justice details from the service and populates the form.
     */
    private void loadJusticeDetails() {
        try {
            JusticeData justice = justiceService.getJusticeDetails(currentJusticeId);
            if (justice != null) {
                idField.setText(justice.getId());
                employeeField.setText(justice.getEmployeeName());
                dateField.setText(justice.getDate());
                reasonArea.setText(justice.getReason());
                statusCombo.setSelectedItem(justice.getStatus());
                messageLabel.setText("Justice details loaded successfully.");
            } else {
                messageLabel.setText("Error: Justice not found.");
                messageLabel.setForeground(Color.RED);
            }
        } catch (ServerConnectionException e) {
            showErrorMessage("Server Error: " + e.getMessage());
        }
    }
    /**
     * Displays an error message in the message label.
     *
     * @param message The error message to display.
     */
    private void showErrorMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForeground(Color.RED);
    }
    /**
     * Displays a success message in the message label.
     *
     * @param message The success message to display.
     */
    private void showSuccessMessage(String message) {
        messageLabel.setText(message);
        messageLabel.setForeground(Color.BLUE);
    }
    /**
     * Action listener for the Modify button.
     */
    private class ModifyButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!auth.isLoggedIn()) {
                showErrorMessage("You must be logged in as administrator to modify justice.");
                return;
            }
            try {
                // Create updated justice object
                JusticeData updatedJustice = new JusticeData(
                    currentJusticeId,
                    employeeField.getText(),
                    dateField.getText(),
                    reasonArea.getText(),
                    (String) statusCombo.getSelectedItem()
                );
                // Validate input
                if (employeeField.getText().trim().isEmpty() || dateField.getText().trim().isEmpty()) {
                    showErrorMessage("Employee name and date are required.");
                    return;
                }
                // Update justice
                boolean success = justiceService.updateJustice(currentJusticeId, updatedJustice);
                if (success) {
                    showSuccessMessage("Justice modified successfully!");
                } else {
                    showErrorMessage("Failed to modify justice. Justice not found.");
                }
            } catch (ServerConnectionException ex) {
                showErrorMessage("Server Error: " + ex.getMessage());
            }
        }
    }
    /**
     * Action listener for the Delete button.
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!auth.isLoggedIn()) {
                showErrorMessage("You must be logged in as administrator to delete justice.");
                return;
            }
            int confirmation = JOptionPane.showConfirmDialog(
                JusticeForm.this,
                "Are you sure you want to delete this justice?\nThis action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                try {
                    boolean success = justiceService.deleteJustice(currentJusticeId);
                    if (success) {
                        showSuccessMessage("Justice deleted successfully!");
                        modifyButton.setEnabled(false);
                        deleteButton.setEnabled(false);
                        // Close form after deletion
                        JOptionPane.showMessageDialog(JusticeForm.this, 
                            "Justice deleted. Form will now close.");
                        dispose();
                    } else {
                        showErrorMessage("Failed to delete justice. Justice not found.");
                    }
                } catch (ServerConnectionException ex) {
                    showErrorMessage("Server Error: " + ex.getMessage());
                }
            }
        }
    }
    /**
     * Action listener for the Disconnect button.
     * Implements the postcondition: "The administrator interrupts the connection to the SMOS server"
     */
    private class DisconnectButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            if (!auth.isLoggedIn()) {
                showErrorMessage("You must be logged in as administrator to interrupt server connection.");
                return;
            }
            int confirmation = JOptionPane.showConfirmDialog(
                JusticeForm.this,
                "Are you sure you want to interrupt the SMOS server connection?\nThis will affect all justice operations.",
                "Interrupt Server Connection",
                JOptionPane.YES_NO_OPTION
            );
            if (confirmation == JOptionPane.YES_OPTION) {
                justiceService.interruptServerConnection();
                showErrorMessage("SMOS server connection interrupted. Further operations may fail.");
                modifyButton.setEnabled(false);
                deleteButton.setEnabled(false);
                disconnectButton.setEnabled(false);
            }
        }
    }
}