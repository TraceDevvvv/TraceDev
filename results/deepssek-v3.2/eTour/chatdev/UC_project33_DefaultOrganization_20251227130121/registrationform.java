/**
 * RegistrationForm class provides the GUI for the registration process.
 * It allows the guest user to view the form, fill it, submit, and handles confirmation.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.logging.Logger;
public class RegistrationForm extends JFrame {
    private static final Logger logger = Logger.getLogger(RegistrationForm.class.getName());
    // Form components
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JTextField phoneField;
    private JButton submitButton;
    private JButton cancelButton;
    // Constructor
    public RegistrationForm() {
        setTitle("Registration System");
        setSize(400, 300);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Use BorderLayout for better arrangement
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // Initialize components
        JLabel usernameLabel = new JLabel("Username:");
        usernameField = new JTextField();
        JLabel passwordLabel = new JLabel("Password:");
        passwordField = new JPasswordField();
        JLabel emailLabel = new JLabel("Email:");
        emailField = new JTextField();
        JLabel phoneLabel = new JLabel("Phone (optional):");
        phoneField = new JTextField();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        // Add components to form panel
        formPanel.add(usernameLabel);
        formPanel.add(usernameField);
        formPanel.add(passwordLabel);
        formPanel.add(passwordField);
        formPanel.add(emailLabel);
        formPanel.add(emailField);
        formPanel.add(phoneLabel);
        formPanel.add(phoneField);
        // Add buttons to button panel
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        // Add panels to frame
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // Add action listeners
        submitButton.addActionListener(new SubmitAction());
        cancelButton.addActionListener(new CancelAction());
        // Center the window
        setLocationRelativeTo(null);
        logger.info("Registration form initialized.");
    }
    /**
     * ActionListener for the Submit button.
     * Validates the data and asks for confirmation before creating the account.
     */
    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve data from fields
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword()).trim();
            String email = emailField.getText().trim();
            String phone = phoneField.getText().trim();
            // Create a UserAccount object
            UserAccount account = new UserAccount(username, password, email, phone);
            // Validate the data
            if (!account.validate()) {
                logger.warning("Invalid or insufficient data entered.");
                JOptionPane.showMessageDialog(RegistrationForm.this,
                        "Invalid or insufficient data. Please check your entries.\n" +
                        "Requirements:\n" +
                        "- Username cannot be empty\n" +
                        "- Password must be at least 6 characters\n" +
                        "- Email must be valid format\n" +
                        "- Phone (if provided) must be at least 10 digits",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                // Activate the Errored use case
                return;
            }
            // Ask for confirmation
            int confirm = JOptionPane.showConfirmDialog(RegistrationForm.this,
                    "Please confirm the registration details:\n" +
                            "Username: " + username + "\n" +
                            "Email: " + email + "\n" +
                            "Phone: " + (phone.isEmpty() ? "Not provided" : phone),
                    "Confirm Registration",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                logger.info("User confirmed registration.");
                // Create the account
                createAccount(account);
            } else {
                logger.info("User cancelled the confirmation.");
                JOptionPane.showMessageDialog(RegistrationForm.this,
                        "Registration confirmation cancelled.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    /**
     * ActionListener for the Cancel button.
     * Allows the guest user to cancel the operation.
     */
    private class CancelAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            logger.info("Registration cancelled by user.");
            int confirm = JOptionPane.showConfirmDialog(RegistrationForm.this,
                    "Are you sure you want to cancel registration?",
                    "Cancel Registration",
                    JOptionPane.YES_NO_OPTION,
                    JOptionPane.QUESTION_MESSAGE);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(RegistrationForm.this,
                        "Registration cancelled.",
                        "Cancelled",
                        JOptionPane.INFORMATION_MESSAGE);
                // Close the application
                dispose();
            }
        }
    }
    /**
     * Simulates creating a new account with the provided data.
     * In a real system, this would involve database operations or server communication.
     * @param account the validated UserAccount object.
     */
    private void createAccount(UserAccount account) {
        // Disable submit button during processing
        submitButton.setEnabled(false);
        cancelButton.setEnabled(false);
        new Thread(() -> {
            try {
                // Simulate network delay (step 6: create account)
                Thread.sleep(2000);
                // Simulate server interruption (ETOUR) randomly for demonstration
                if (Math.random() < 0.2) { // 20% chance
                    throw new RuntimeException("ETOUR: Interruption of the connection to the server.");
                }
                // Successful account creation
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Account created successfully!\n\n" +
                            "Details:\n" +
                            "Username: " + account.getUsername() + "\n" +
                            "Email: " + account.getEmail() + "\n" +
                            "Phone: " + (account.getPhone().isEmpty() ? "Not provided" : account.getPhone()),
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    logger.info("Account created: " + account.toString());
                    // Exit conditions: notify successful operation
                    dispose();
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.severe("Thread interrupted: " + ex.getMessage());
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Registration process was interrupted.",
                            "Process Interrupted",
                            JOptionPane.WARNING_MESSAGE);
                });
            } catch (RuntimeException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            ex.getMessage() + "\n\nPlease try again later.",
                            "Server Error",
                            JOptionPane.ERROR_MESSAGE);
                    logger.severe("Server error: " + ex.getMessage());
                });
            } finally {
                SwingUtilities.invokeLater(() -> {
                    submitButton.setEnabled(true);
                    cancelButton.setEnabled(true);
                });
            }
        }).start();
    }
    public static void main(String[] args) {
        // Set look and feel to system default
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            logger.warning("Could not set system look and feel: " + e.getMessage());
        }
        SwingUtilities.invokeLater(() -> {
            RegistrationForm form = new RegistrationForm();
            form.setVisible(true);
            logger.info("Registration system started.");
        });
    }
}