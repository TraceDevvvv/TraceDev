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
        setLayout(new GridLayout(6, 2, 10, 10));
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
        // Add components to the frame
        add(usernameLabel);
        add(usernameField);
        add(passwordLabel);
        add(passwordField);
        add(emailLabel);
        add(emailField);
        add(phoneLabel);
        add(phoneField);
        add(new JLabel()); // empty cell for layout
        add(new JLabel());
        add(submitButton);
        add(cancelButton);
        // Add action listeners
        submitButton.addActionListener(new SubmitAction());
        cancelButton.addActionListener(new CancelAction());
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
                        "Invalid or insufficient data. Please check your entries.",
                        "Validation Error",
                        JOptionPane.ERROR_MESSAGE);
                // In a real system, you might activate the Errored use case here.
                return;
            }
            // Ask for confirmation
            int confirm = JOptionPane.showConfirmDialog(RegistrationForm.this,
                    "Please confirm the registration details:\n" +
                            "Username: " + username + "\n" +
                            "Email: " + email + "\n" +
                            "Phone: " + (phone.isEmpty() ? "Not provided" : phone),
                    "Confirm Registration",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                logger.info("User confirmed registration.");
                // Create the account
                createAccount(account);
            } else {
                logger.info("User cancelled the confirmation.");
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
            JOptionPane.showMessageDialog(RegistrationForm.this,
                    "Registration cancelled.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
            // Close the application or go back to a main menu in a real system.
            dispose();
        }
    }
    /**
     * Simulates creating a new account with the provided data.
     * In a real system, this would involve database operations or server communication.
     * @param account the validated UserAccount object.
     */
    private void createAccount(UserAccount account) {
        // Simulate server communication with a delay
        submitButton.setEnabled(false);
        new Thread(() -> {
            try {
                // Simulate network delay
                Thread.sleep(2000);
                // Simulate server interruption (ETOUR) randomly for demonstration
                if (Math.random() < 0.2) { // 20% chance
                    throw new RuntimeException("ETOUR: Interruption of the connection to the server.");
                }
                // Simulate account creation
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            "Account created successfully!\n" + account.toString(),
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                    logger.info("Account created: " + account.toString());
                    dispose(); // Close the form after successful registration
                });
            } catch (InterruptedException ex) {
                Thread.currentThread().interrupt();
                logger.severe("Thread interrupted: " + ex.getMessage());
            } catch (RuntimeException ex) {
                SwingUtilities.invokeLater(() -> {
                    JOptionPane.showMessageDialog(RegistrationForm.this,
                            ex.getMessage(),
                            "Server Error",
                            JOptionPane.ERROR_MESSAGE);
                    logger.severe("Server error: " + ex.getMessage());
                });
            } finally {
                SwingUtilities.invokeLater(() -> submitButton.setEnabled(true));
            }
        }).start();
    }
}