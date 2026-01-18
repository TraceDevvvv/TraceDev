/**
 * Main.java
 * Entry point of the application. Implements a user management system for administrators.
 * Features include: administrator authentication, user list viewing, new user creation with validation.
 * The system follows the specified use case requirements.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.regex.Pattern;
public class Main {
    private static List<User> userArchive = new ArrayList<>();
    private static boolean isConnected = true; // Simulating server connection state
    public static void main(String[] args) {
        // Check if the user is logged in as administrator
        boolean isAdmin = simulateAdminLogin();
        if (!isAdmin) {
            JOptionPane.showMessageDialog(null, 
                "You must be logged in as an administrator to use this system.", 
                "Access Denied", JOptionPane.ERROR_MESSAGE);
            System.exit(0);
        }
        // Simulate that the user has viewed the list of users (precondition)
        System.out.println("Administrator has viewed the list of users.");
        // Create and show the main GUI
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                createAndShowGUI();
            }
        });
    }
    /**
     * Simulates administrator login.
     * In production, replace with actual authentication logic.
     * @return true if user is administrator, false otherwise
     */
    private static boolean simulateAdminLogin() {
        // For demo purposes, always returns true
        // In real application, implement proper authentication
        return true;
    }
    /**
     * Creates and displays the main GUI window.
     * Shows current users and provides "New User" button.
     */
    private static void createAndShowGUI() {
        JFrame frame = new JFrame("User Management System - Administrator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 400);
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout());
        // Top label
        JLabel titleLabel = new JLabel("Current Users in the System", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Center panel with user list
        DefaultListModel<String> listModel = new DefaultListModel<>();
        // Sample users for demonstration
        listModel.addElement("John Doe - johndoe@example.com");
        listModel.addElement("Jane Smith - janesmith@example.com");
        JList<String> userList = new JList<>(listModel);
        JScrollPane scrollPane = new JScrollPane(userList);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel with "New User" button
        JPanel bottomPanel = new JPanel();
        JButton newUserButton = new JButton("New User");
        newUserButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                openNewUserForm(frame);
            }
        });
        bottomPanel.add(newUserButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Center window
        frame.setVisible(true);
    }
    /**
     * Opens dialog for entering new user details.
     * Implements form with all required fields according to use case.
     * @param parent Parent frame for modal dialog
     */
    private static void openNewUserForm(JFrame parent) {
        JDialog dialog = new JDialog(parent, "Add New User", true);
        dialog.setSize(400, 450);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Form fields initialization
        JTextField nameField = new JTextField(20);
        JTextField surnameField = new JTextField(20);
        JTextField emailField = new JTextField(20);
        JTextField cellField = new JTextField(20);
        JTextField loginField = new JTextField(20);
        JPasswordField passwordField = new JPasswordField(20);
        JPasswordField confirmField = new JPasswordField(20);
        // Add form components in order
        int row = 0;
        // Name field
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        // Surname field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Surname:"), gbc);
        gbc.gridx = 1;
        dialog.add(surnameField, gbc);
        // Email field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("E-mail:"), gbc);
        gbc.gridx = 1;
        dialog.add(emailField, gbc);
        // Cell field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Cell:"), gbc);
        gbc.gridx = 1;
        dialog.add(cellField, gbc);
        // Login field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Login:"), gbc);
        gbc.gridx = 1;
        dialog.add(loginField, gbc);
        // Password field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        dialog.add(passwordField, gbc);
        // Confirm password field
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        dialog.add(new JLabel("Confirm Password:"), gbc);
        gbc.gridx = 1;
        dialog.add(confirmField, gbc);
        // Buttons panel
        row++;
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, gbc);
        // Save button action - validates and saves user
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String email = emailField.getText().trim();
                String cell = cellField.getText().trim();
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirm = new String(confirmField.getPassword());
                if (validateUserData(name, surname, email, cell, login, password, confirm)) {
                    User newUser = new User(name, surname, email, cell, login, password);
                    userArchive.add(newUser);
                    JOptionPane.showMessageDialog(dialog, 
                        "New user created successfully!", 
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                    dialog.dispose();
                } else {
                    handleDataError();
                }
            }
        });
        // Cancel button action
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dialog.dispose();
            }
        });
        dialog.setLocationRelativeTo(parent);
        dialog.setVisible(true);
    }
    /**
     * Validates user input data with comprehensive checks.
     * @param name User's first name
     * @param surname User's last name
     * @param email User's email address
     * @param cell User's cell phone number
     * @param login User's login ID
     * @param password User's password
     * @param confirm Password confirmation
     * @return true if all validations pass, false otherwise
     */
    private static boolean validateUserData(String name, String surname, String email, 
                                          String cell, String login, String password, 
                                          String confirm) {
        // Required fields check
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || 
            cell.isEmpty() || login.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(null, 
                "All fields are required.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Email format validation
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            JOptionPane.showMessageDialog(null, 
                "Invalid email format. Example: user@example.com", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Cell number validation (10+ digits)
        if (!cell.matches("\\d{10,}")) {
            JOptionPane.showMessageDialog(null, 
                "Cell number must contain at least 10 digits.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Password confirmation check
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(null, 
                "Passwords do not match.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Password strength validation
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, 
                "Password must be at least 8 characters long.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Password must contain both letters and numbers
        boolean hasLetter = password.matches(".*[A-Za-z].*");
        boolean hasDigit = password.matches(".*[0-9].*");
        if (!hasLetter || !hasDigit) {
            JOptionPane.showMessageDialog(null, 
                "Password must contain both letters and numbers.", 
                "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Unique login check
        for (User user : userArchive) {
            if (user.getLogin().equals(login)) {
                JOptionPane.showMessageDialog(null, 
                    "Login already exists. Please choose a different login ID.", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    /**
     * Handles data errors according to use case specifications.
     * Notifies administrator and simulates server connection interruption.
     */
    private static void handleDataError() {
        // Notify administrator of data error
        JOptionPane.showMessageDialog(null, 
            "Data entered is not valid. Please correct the errors.", 
            "Data Error", JOptionPane.ERROR_MESSAGE);
        // Simulate server connection interruption (postcondition)
        if (isConnected) {
            isConnected = false;
            System.out.println("SMOS server connection interrupted due to data error.");
            // In production: implement proper server disconnection logic
        }
    }
    /**
     * Inner class representing a User entity.
     * Contains all user attributes from the use case.
     */
    static class User {
        private String name;
        private String surname;
        private String email;
        private String cell;
        private String login;
        private String password;
        public User(String name, String surname, String email, 
                   String cell, String login, String password) {
            this.name = name;
            this.surname = surname;
            this.email = email;
            this.cell = cell;
            this.login = login;
            this.password = password;
        }
        // Getters
        public String getLogin() { return login; }
        public String getName() { return name; }
        public String getSurname() { return surname; }
        public String getEmail() { return email; }
        public String getCell() { return cell; }
        public String getPassword() { return password; }
        // Setters
        public void setName(String name) { this.name = name; }
        public void setSurname(String surname) { this.surname = surname; }
        public void setEmail(String email) { this.email = email; }
        public void setCell(String cell) { this.cell = cell; }
        public void setLogin(String login) { this.login = login; }
        public void setPassword(String password) { this.password = password; }
        @Override
        public String toString() {
            return name + " " + surname + " (" + email + ")";
        }
    }
}