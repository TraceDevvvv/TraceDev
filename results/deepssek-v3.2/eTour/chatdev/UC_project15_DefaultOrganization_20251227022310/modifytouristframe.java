'''
Main GUI frame for modifying tourist account data.
Implements Steps 2-6 of the use case flow.
'''
package com.etour.agency;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class ModifyTouristFrame extends JFrame {
    private TouristService touristService;
    private Tourist originalTourist;
    private Tourist modifiedTourist;
    // Form fields
    private JTextField idField;
    private JTextField firstNameField;
    private JTextField lastNameField;
    private JTextField emailField;
    private JTextField phoneField;
    private JTextField nationalityField;
    private JTextField passportField;
    private JTextArea addressArea;
    private JTextField dobField;
    private JTextField emergencyContactField;
    // Buttons
    private JButton saveButton;
    private JButton cancelButton;
    private JButton backButton;
    public ModifyTouristFrame(TouristService touristService, Tourist tourist) {
        this.touristService = touristService;
        this.originalTourist = tourist;
        this.modifiedTourist = tourist.copy(); // Work with a copy
        initializeUI();
        loadTouristData();
    }
    /**
     * Initialize the GUI components
     */
    private void initializeUI() {
        setTitle("Modify Tourist Account Data - Agency Operator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 650);
        setLocationRelativeTo(null);
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10));
        // Create form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(new TitledBorder("Tourist Account Information"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        // Row 0: ID (non-editable)
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Tourist ID:"), gbc);
        idField = new JTextField(20);
        idField.setEditable(false);
        gbc.gridx = 1; gbc.gridy = 0;
        formPanel.add(idField, gbc);
        // Row 1: First Name
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("First Name:*"), gbc);
        firstNameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 1;
        formPanel.add(firstNameField, gbc);
        // Row 2: Last Name
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Last Name:*"), gbc);
        lastNameField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 2;
        formPanel.add(lastNameField, gbc);
        // Row 3: Email
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Email:*"), gbc);
        emailField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 3;
        formPanel.add(emailField, gbc);
        // Row 4: Phone
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Phone:*"), gbc);
        phoneField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 4;
        formPanel.add(phoneField, gbc);
        // Row 5: Nationality
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Nationality:*"), gbc);
        nationalityField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 5;
        formPanel.add(nationalityField, gbc);
        // Row 6: Passport
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Passport Number:*"), gbc);
        passportField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 6;
        formPanel.add(passportField, gbc);
        // Row 7: Date of Birth
        gbc.gridx = 0; gbc.gridy = 7;
        formPanel.add(new JLabel("Date of Birth (YYYY-MM-DD):*"), gbc);
        dobField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 7;
        formPanel.add(dobField, gbc);
        // Row 8: Emergency Contact
        gbc.gridx = 0; gbc.gridy = 8;
        formPanel.add(new JLabel("Emergency Contact:*"), gbc);
        emergencyContactField = new JTextField(20);
        gbc.gridx = 1; gbc.gridy = 8;
        formPanel.add(emergencyContactField, gbc);
        // Row 9: Address (spanning 2 rows)
        gbc.gridx = 0; gbc.gridy = 9;
        gbc.gridheight = 2;
        formPanel.add(new JLabel("Address:*"), gbc);
        addressArea = new JTextArea(3, 20);
        addressArea.setLineWrap(true);
        addressArea.setWrapStyleWord(true);
        JScrollPane addressScroll = new JScrollPane(addressArea);
        gbc.gridx = 1; gbc.gridy = 9;
        gbc.gridheight = 2;
        formPanel.add(addressScroll, gbc);
        // Reset gridheight
        gbc.gridheight = 1;
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Create instruction panel
        JPanel instructionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        instructionPanel.setBorder(new TitledBorder("Instructions"));
        instructionPanel.add(new JLabel("<html>Fields marked with * are required. Modify data and click 'Save Changes'.<br/>"
            + "System will validate data and ask for confirmation before saving.</html>"));
        mainPanel.add(instructionPanel, BorderLayout.NORTH);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        backButton = new JButton("Back to Search");
        backButton.addActionListener(e -> MainApp.showSearchTouristFrame());
        buttonPanel.add(backButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Discard all changes and return to search?",
                "Confirm Cancel",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                MainApp.showSearchTouristFrame();
            }
        });
        buttonPanel.add(cancelButton);
        saveButton = new JButton("Save Changes");
        saveButton.addActionListener(new SaveButtonListener());
        buttonPanel.add(saveButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Load tourist data into form fields (Step 2 of use case)
     */
    private void loadTouristData() {
        idField.setText(originalTourist.getId());
        firstNameField.setText(originalTourist.getFirstName());
        lastNameField.setText(originalTourist.getLastName());
        emailField.setText(originalTourist.getEmail());
        phoneField.setText(originalTourist.getPhoneNumber());
        nationalityField.setText(originalTourist.getNationality());
        passportField.setText(originalTourist.getPassportNumber());
        addressArea.setText(originalTourist.getAddress());
        dobField.setText(originalTourist.getDateOfBirth());
        emergencyContactField.setText(originalTourist.getEmergencyContact());
    }
    /**
     * Update modifiedTourist object with form data (Step 3 of use case)
     */
    private void updateModifiedTourist() {
        modifiedTourist.setFirstName(firstNameField.getText().trim());
        modifiedTourist.setLastName(lastNameField.getText().trim());
        modifiedTourist.setEmail(emailField.getText().trim());
        modifiedTourist.setPhoneNumber(phoneField.getText().trim());
        modifiedTourist.setNationality(nationalityField.getText().trim());
        modifiedTourist.setPassportNumber(passportField.getText().trim());
        modifiedTourist.setAddress(addressArea.getText().trim());
        modifiedTourist.setDateOfBirth(dobField.getText().trim());
        modifiedTourist.setEmergencyContact(emergencyContactField.getText().trim());
    }
    /**
     * Action listener for save button
     * Implements Steps 4-6 of the use case
     */
    private class SaveButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Step 3: Update tourist object with form data
            updateModifiedTourist();
            // Step 4: Verify information
            String validationError = modifiedTourist.getValidationErrorMessage();
            if (validationError != null) {
                // Activate use case Errored
                MainApp.showErrorDialog(validationError);
                return;
            }
            // Step 4: Ask for confirmation
            int confirmation = JOptionPane.showConfirmDialog(
                ModifyTouristFrame.this,
                "Are you sure you want to update this tourist account?\n" +
                "Tourist: " + modifiedTourist.getFirstName() + " " + modifiedTourist.getLastName() + "\n" +
                "ID: " + modifiedTourist.getId(),
                "Confirm Update",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.QUESTION_MESSAGE);
            // Step 5: Confirm the operation
            if (confirmation == JOptionPane.YES_OPTION) {
                // Step 6: Store the modified data
                boolean success = touristService.updateTourist(modifiedTourist);
                if (success) {
                    JOptionPane.showMessageDialog(
                        ModifyTouristFrame.this,
                        "Tourist account updated successfully!\n" +
                        "Modified account for: " + modifiedTourist.getFirstName() + " " + modifiedTourist.getLastName(),
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Return to search screen
                    MainApp.showSearchTouristFrame();
                } else {
                    JOptionPane.showMessageDialog(
                        ModifyTouristFrame.this,
                        "Failed to update tourist account. Please try again.",
                        "Update Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        }
    }
}