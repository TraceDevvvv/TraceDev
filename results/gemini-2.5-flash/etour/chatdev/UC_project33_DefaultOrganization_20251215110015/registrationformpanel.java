/*
JPanel responsible for displaying the registration form to the user.
Handles user input, interaction, and communicates with the UserService.
This class now handles passwords securely using char arrays and ensures they are wiped.
*/
import javax.swing.*;
import java.awt.*;
import java.util.logging.Logger;
import java.util.Arrays; // Required for securely clearing char arrays
public class RegistrationFormPanel extends JPanel {
    private static final Logger logger = RegistrationLogger.getLogger();
    private final UserService userService;
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JTextField emailField;
    private JButton registerButton;
    private JButton cancelButton;
    /*
     Constructor for RegistrationFormPanel.
     @param userService The service responsible for handling registration logic.
    */
    public RegistrationFormPanel(UserService userService) {
        this.userService = userService;
        initUI();
        addListeners();
    }
    /*
     Initializes the user interface components (labels, text fields, buttons).
    */
    private void initUI() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        // Labels and Text Fields
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST;
        add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        passwordField = new JPasswordField(20);
        add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        emailField = new JTextField(20);
        add(emailField, gbc);
        // Buttons
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span two columns
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        registerButton = new JButton("Register");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(registerButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, gbc);
    }
    /*
     Adds action listeners to the register and cancel buttons.
    */
    private void addListeners() {
        registerButton.addActionListener(e -> handleRegisterButton());
        cancelButton.addActionListener(e -> handleCancelButton());
    }
    /*
     Handles the action when the "Register" button is clicked.
     This corresponds to Step 3, 4, 5, and 6 of the flow of events.
     Passwords are handled as char arrays for security and wiped after use.
    */
    private void handleRegisterButton() {
        logger.info("User attempting to register.");
        String username = usernameField.getText();
        char[] passwordChars = null; // Declare here so finally block can access
        try {
            passwordChars = passwordField.getPassword(); // Get password as a char array
            String email = emailField.getText();
            // Step 4: Verify the data entered and asks for confirmation of the transaction.
            // Where the data is invalid or insufficient, the system activates the use case Errored.
            RegistrationStatus validationStatus = userService.validateRegistrationData(username, passwordChars, email);
            if (validationStatus != RegistrationStatus.SUCCESS) {
                String errorMessage;
                switch (validationStatus) {
                    case INVALID_DATA:
                        errorMessage = "Invalid or insufficient data provided. Please check all fields.";
                        break;
                    case USERNAME_TAKEN:
                        errorMessage = "Username already exists. Please choose a different one.";
                        break;
                    default:
                        errorMessage = "An unexpected validation error occurred.";
                }
                displayErrorMessage("Registration Failed", errorMessage);
                logger.warning("Registration failed due to validation: " + errorMessage);
                return; // Exit if validation fails (activates Errored use case)
            }
            // Ask for confirmation (Step 4 & 5)
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to create an account with these details?\n" +
                            "Username: " + username + "\n" +
                            "Email: " + email,
                    "Confirm Registration",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                logger.info("User confirmed registration operation.");
                // Step 6: Create a new account with the data entered.
                RegistrationStatus registrationResult = userService.registerUser(username, passwordChars, email); // Pass char array to service
                if (registrationResult == RegistrationStatus.SUCCESS) {
                    displaySuccessMessage("Registration Successful", "Your account has been created successfully!");
                    logger.info("Account successfully created for user: " + username);
                    clearForm(); // Clear the form on successful registration
                } else if (registrationResult == RegistrationStatus.CONNECTION_ERROR) {
                    // Exit condition: Interruption of the connection to the server ETOUR.
                    displayErrorMessage("Registration Failed", "Connection to server interrupted (ETOUR). Please try again later.");
                    logger.severe("Registration failed due to ETOUR for user: " + username);
                } else {
                    // This case should ideally be caught by initial validation but as a fallback.
                    displayErrorMessage("Registration Failed", "Unable to create account. Please try again.");
                    logger.severe("Registration failed for unknown reason after confirmation for user: " + username);
                }
            } else {
                // Exit condition: Guest User cancels the operation.
                logger.info("User cancelled registration operation at confirmation step.");
                displayErrorMessage("Registration Cancelled", "Registration was cancelled by the user.");
                clearForm(); // Clear the form when the user cancels at the confirmation stage.
            }
        } finally {
            // Always wipe the password char array from memory to prevent lingering in memory.
            if (passwordChars != null) {
                Arrays.fill(passwordChars, ' ');
            }
        }
    }
    /*
     Handles the action when the "Cancel" button is clicked.
     Corresponds to the "Guest User cancels the operation" exit condition.
    */
    private void handleCancelButton() {
        logger.info("Guest User cancelled the operation.");
        if (JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel the registration?",
                "Cancel Registration",
                JOptionPane.YES_NO_OPTION) == JOptionPane.YES_OPTION) {
            clearForm();
        }
    }
    /*
     Displays a success message to the user.
     @param title The title of the dialog.
     @param message The message to display.
    */
    private void displaySuccessMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    /*
     Displays an error message to the user.
     @param title The title of the dialog.
     @param message The message to display.
    */
    private void displayErrorMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.ERROR_MESSAGE);
    }
    /*
     Clears all input fields in the registration form.
    */
    private void clearForm() {
        usernameField.setText("");
        // Setting passwordField text to empty string also clears the visual field.
        // The underlying char array for the password *should* be handled by the JPasswordField itself
        // or explicitly wiped if we stored it temporarily, but here we just clear the input.
        passwordField.setText("");
        emailField.setText("");
        logger.fine("Registration form fields cleared.");
    }
}