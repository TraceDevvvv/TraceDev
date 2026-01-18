'''
    RegistrationForm.java
    This class creates the graphical user interface (GUI) for user registration.
    It extends JFrame and contains all the input fields, labels, buttons, and handles user interactions.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class RegistrationForm extends JFrame {
    // GUI Components
    private JTextField nameField, surnameField, mobileField, emailField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton registerButton;
    private JLabel messageLabel; // For displaying success or error messages
    // Service for handling registration logic
    private RegistrationService registrationService;
    /**
     * Constructor for the RegistrationForm.
     * Initializes the GUI components, sets up the layout, and adds event listeners.
     */
    public RegistrationForm() {
        super("Student Registration"); // Set window title
        this.registrationService = new RegistrationService(); // Initialize the service
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Close operation
        setSize(450, 550); // Set window size
        setLocationRelativeTo(null); // Center the window on the screen
        setResizable(false); // Prevent resizing
        // Set a visually appealing look and feel if available
        try {
            UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
        } catch (Exception e) {
            // Fallback to default if system L&F is not available
            System.err.println("Could not set system look and feel: " + e.getMessage());
        }
        setupUI(); // Call method to set up all UI elements
    }
    /**
     * Sets up all the graphical user interface components and their layout.
     * Uses GridBagLayout for a flexible and aligned form structure.
     */
    private void setupUI() {
        JPanel panel = new JPanel(new GridBagLayout());
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 5, 8, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components fill horizontal space
        int row = 0;
        // Title Label
        JLabel titleLabel = new JLabel("Register as a Student", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        gbc.gridx = 0;
        gbc.gridy = row++;
        gbc.gridwidth = 2; // Span across two columns
        panel.add(titleLabel, gbc);
        // Reset gridwidth for subsequent components
        gbc.gridwidth = 1;
        // Name
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        panel.add(nameField, gbc);
        row++;
        // Surname
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Surname:"), gbc);
        gbc.gridx = 1;
        surnameField = new JTextField(20);
        panel.add(surnameField, gbc);
        row++;
        // Mobile Phone
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Mobile Phone:"), gbc);
        gbc.gridx = 1;
        mobileField = new JTextField(20);
        panel.add(mobileField, gbc);
        row++;
        // E-mail
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        panel.add(emailField, gbc);
        row++;
        // Username
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);
        row++;
        // Password
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);
        row++;
        // Confirmation Password
        gbc.gridx = 0;
        gbc.gridy = row;
        panel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20);
        panel.add(confirmPasswordField, gbc);
        row++;
        // Register Button
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2; // Span across two columns
        registerButton = new JButton("Register");
        registerButton.setFont(new Font("Arial", Font.BOLD, 16));
        registerButton.setBackground(new Color(60, 179, 113)); // Teal color
        registerButton.setForeground(Color.WHITE);
        registerButton.setFocusPainted(false); // Remove focus border
        registerButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When register button is clicked, call handleSubmit for validation and registration
                handleSubmit();
            }
        });
        panel.add(registerButton, gbc);
        row++;
        // Message Label for feedback
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        messageLabel = new JLabel("", SwingConstants.CENTER);
        messageLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        messageLabel.setForeground(Color.RED); // Default to red for error messages
        panel.add(messageLabel, gbc);
        row++;
        add(panel); // Add the main panel to the frame
    }
    /**
     * Handles the submission of the registration form.
     * Retrieves user input, and calls the RegistrationService with individual fields
     * for validation and processing. Displays success or error messages to the user.
     */
    private void handleSubmit() {
        // Clear previous messages
        messageLabel.setText("");
        // Get plain text from all fields
        String name = nameField.getText();
        String surname = surnameField.getText();
        String mobile = mobileField.getText();
        String email = emailField.getText();
        String username = usernameField.getText();
        // Convert JPasswordField content to String directly for plain-text validation
        // In a real application, character arrays should be cleared after use.
        String password = new String(passwordField.getPassword());
        String confirmPassword = new String(confirmPasswordField.getPassword());
        try {
            // Call the service method with all individual plain-text parameters.
            // The service will handle validation and internally hash the password before "storage".
            if (registrationService.registerUser(name, surname, mobile, email, username, password, confirmPassword)) {
                // If registration is successful, display a success message
                messageLabel.setForeground(new Color(34, 139, 34)); // Forest green for success
                messageLabel.setText("Registration successful! Welcome, " + name + ".");
                clearFormFields(); // Clear the form for the next registration
            }
        } catch (IllegalArgumentException e) {
            // If validation fails in the service, display the error message
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("Registration failed: " + e.getMessage());
        } catch (Exception e) {
            // Catch any unexpected errors
            messageLabel.setForeground(Color.RED);
            messageLabel.setText("An unexpected error occurred: " + e.getMessage());
            e.printStackTrace(); // Log the stack trace for debugging
        } finally {
            // For security, explicitly clear password fields after use, even on error.
            passwordField.setText("");
            confirmPasswordField.setText("");
        }
    }
    /**
     * Clears all the input fields in the registration form after a successful submission.
     */
    private void clearFormFields() {
        nameField.setText("");
        surnameField.setText("");
        mobileField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText(""); // Already done in finally block, but good to have here too for clarity
        confirmPasswordField.setText(""); // Already done in finally block
    }
}