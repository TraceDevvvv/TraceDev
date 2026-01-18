// RegistrationAtSite.java
/**
 * Main entry point for the RegistrationAtSite application.
 * This program provides a GUI for student registration following the specified use case.
 * It handles user input for: name, surname, mobile phone, email, username, password, and confirmation password.
 * Upon submission, the registration request is inserted into the system (simulated as writing to a file).
 * Edge cases such as empty fields, password mismatch, and invalid email format are handled.
 * The GUI is built using Java Swing.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.io.*;
import java.util.regex.*;
public class RegistrationAtSite {
    public static void main(String[] args) {
        // Create and display the registration form on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new RegistrationFrame().setVisible(true);
        });
    }
}
/**
 * The main frame for the registration form.
 * It contains input fields and a submit button.
 */
class RegistrationFrame extends JFrame {
    private JTextField nameField, surnameField, mobileField, emailField, usernameField;
    private JPasswordField passwordField, confirmPasswordField;
    private JButton submitButton, cancelButton;
    public RegistrationFrame() {
        setTitle("Student Registration");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Create a panel for input fields with GridBagLayout for flexible alignment
        JPanel inputPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Name field
        inputPanel.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        nameField = new JTextField(20);
        inputPanel.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Surname field
        inputPanel.add(new JLabel("Surname:"), gbc);
        gbc.gridx = 1;
        surnameField = new JTextField(20);
        inputPanel.add(surnameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Mobile phone field
        inputPanel.add(new JLabel("Mobile Phone:"), gbc);
        gbc.gridx = 1;
        mobileField = new JTextField(20);
        inputPanel.add(mobileField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Email field
        inputPanel.add(new JLabel("Email:"), gbc);
        gbc.gridx = 1;
        emailField = new JTextField(20);
        inputPanel.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Username field
        inputPanel.add(new JLabel("Username:"), gbc);
        gbc.gridx = 1;
        usernameField = new JTextField(20);
        inputPanel.add(usernameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Password field
        inputPanel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        passwordField = new JPasswordField(20);
        inputPanel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        // Confirm Password field
        inputPanel.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        confirmPasswordField = new JPasswordField(20);
        inputPanel.add(confirmPasswordField, gbc);
        add(inputPanel, BorderLayout.CENTER);
        // Create a panel for buttons
        JPanel buttonPanel = new JPanel();
        submitButton = new JButton("Submit");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Add action listeners
        submitButton.addActionListener(new SubmitAction());
        cancelButton.addActionListener(e -> System.exit(0)); // Close application on cancel
        pack(); // Size the frame to fit the components
        setLocationRelativeTo(null); // Center the window on screen
    }
    /**
     * Action listener for the submit button.
     * Validates input and inserts the registration request into the system (writes to a file).
     */
    private class SubmitAction implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            // Retrieve input values
            String name = nameField.getText().trim();
            String surname = surnameField.getText().trim();
            String mobile = mobileField.getText().trim();
            String email = emailField.getText().trim();
            String username = usernameField.getText().trim();
            String password = new String(passwordField.getPassword());
            String confirmPassword = new String(confirmPasswordField.getPassword());
            // Validate inputs
            if (name.isEmpty() || surname.isEmpty() || mobile.isEmpty() || email.isEmpty() ||
                username.isEmpty() || password.isEmpty() || confirmPassword.isEmpty()) {
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "All fields are required!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate email format using a simple regex
            String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
            Pattern pattern = Pattern.compile(emailRegex);
            if (!pattern.matcher(email).matches()) {
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "Please enter a valid email address!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Validate password match
            if (!password.equals(confirmPassword)) {
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "Passwords do not match!",
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            // Simulate inserting registration request into the system (write to a file)
            try (FileWriter fw = new FileWriter("registrations.txt", true);
                 BufferedWriter bw = new BufferedWriter(fw);
                 PrintWriter out = new PrintWriter(bw)) {
                out.println("Registration Request:");
                out.println("Name: " + name);
                out.println("Surname: " + surname);
                out.println("Mobile: " + mobile);
                out.println("Email: " + email);
                out.println("Username: " + username);
                out.println("Password: " + password); // In a real app, password should be hashed
                out.println("-------------------------");
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "Registration request submitted successfully!",
                    "Success",
                    JOptionPane.INFORMATION_MESSAGE);
                clearForm(); // Clear fields after successful submission
            } catch (IOException ex) {
                JOptionPane.showMessageDialog(RegistrationFrame.this,
                    "Error saving registration: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        }
    }
    /**
     * Clear all input fields after successful submission.
     */
    private void clearForm() {
        nameField.setText("");
        surnameField.setText("");
        mobileField.setText("");
        emailField.setText("");
        usernameField.setText("");
        passwordField.setText("");
        confirmPasswordField.setText("");
    }
}