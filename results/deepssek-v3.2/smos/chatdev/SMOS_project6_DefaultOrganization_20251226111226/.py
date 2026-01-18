/**
 * Main.java
 *
 * Entry point of the application. This class sets up the GUI and initializes the main window.
 * It also handles the connection to the server and the administrator's session.
 *
 * Note: This is a simplified version to meet the use case. In a real application,
 * you would have a more robust architecture with proper separation of concerns.
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
        // Check if the user is logged in as administrator (simulated)
        boolean isAdmin = simulateAdminLogin();
        if (!isAdmin) {
            JOptionPane.showMessageDialog(null, "You must be logged in as an administrator to use this system.", "Access Denied", JOptionPane.ERROR_MESSAGE);
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
     * Simulates an administrator login. In a real application, this would involve
     * authentication against a server or database.
     *
     * @return true if the user is an administrator, false otherwise.
     */
    private static boolean simulateAdminLogin() {
        // For simplicity, we assume the user is an admin. In reality, you would check credentials.
        return true;
    }
    /**
     * Creates and displays the main GUI window with a list of users and a "New User" button.
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
        // Center panel with user list (simulated)
        DefaultListModel<String> listModel = new DefaultListModel<>();
        // Add some dummy users for demonstration
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
                // Open the new user form
                openNewUserForm(frame);
            }
        });
        bottomPanel.add(newUserButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        frame.add(mainPanel);
        frame.setLocationRelativeTo(null); // Center the window
        frame.setVisible(true);
    }
    /**
     * Opens a dialog for entering a new user's details.
     *
     * @param parent The parent frame for the dialog.
     */
    private static void openNewUserForm(JFrame parent) {
        // Create a custom dialog for the form
        JDialog dialog = new JDialog(parent, "Add New User", true);
        dialog.setSize(400, 400);
        dialog.setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        // Form fields
        JLabel nameLabel = new JLabel("Name:");
        JTextField nameField = new JTextField(20);
        gbc.gridy++;
        dialog.add(nameLabel, gbc);
        gbc.gridx = 1;
        dialog.add(nameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel surnameLabel = new JLabel("Surname:");
        JTextField surnameField = new JTextField(20);
        dialog.add(surnameLabel, gbc);
        gbc.gridx = 1;
        dialog.add(surnameField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel emailLabel = new JLabel("E-mail:");
        JTextField emailField = new JTextField(20);
        dialog.add(emailLabel, gbc);
        gbc.gridx = 1;
        dialog.add(emailField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel cellLabel = new JLabel("Cell:");
        JTextField cellField = new JTextField(20);
        dialog.add(cellLabel, gbc);
        gbc.gridx = 1;
        dialog.add(cellField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel loginLabel = new JLabel("Login:");
        JTextField loginField = new JTextField(20);
        dialog.add(loginLabel, gbc);
        gbc.gridx = 1;
        dialog.add(loginField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel passwordLabel = new JLabel("Password:");
        JPasswordField passwordField = new JPasswordField(20);
        dialog.add(passwordLabel, gbc);
        gbc.gridx = 1;
        dialog.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy++;
        JLabel confirmLabel = new JLabel("Confirm Password:");
        JPasswordField confirmField = new JPasswordField(20);
        dialog.add(confirmLabel, gbc);
        gbc.gridx = 1;
        dialog.add(confirmField, gbc);
        // Buttons
        gbc.gridx = 0;
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        JPanel buttonPanel = new JPanel();
        JButton saveButton = new JButton("Save");
        JButton cancelButton = new JButton("Cancel");
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        dialog.add(buttonPanel, gbc);
        // Save button action
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect data from fields
                String name = nameField.getText().trim();
                String surname = surnameField.getText().trim();
                String email = emailField.getText().trim();
                String cell = cellField.getText().trim();
                String login = loginField.getText().trim();
                String password = new String(passwordField.getPassword());
                String confirm = new String(confirmField.getPassword());
                // Validate data
                if (validateUserData(name, surname, email, cell, login, password, confirm)) {
                    // Create new user and add to archive
                    User newUser = new User(name, surname, email, cell, login, password);
                    userArchive.add(newUser);
                    JOptionPane.showMessageDialog(dialog, "New user created successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                    // In a real application, you would update the list view here.
                    dialog.dispose();
                } else {
                    // Data error - simulate the "Errodati" use case (error handling)
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
     * Validates the user input data according to the requirements.
     *
     * @param name      The user's name.
     * @param surname   The user's surname.
     * @param email     The user's email.
     * @param cell      The user's cell number.
     * @param login     The user's login.
     * @param password  The user's password.
     * @param confirm   The confirmation password.
     * @return true if all data is valid, false otherwise.
     */
    private static boolean validateUserData(String name, String surname, String email, String cell,
                                            String login, String password, String confirm) {
        // Check for empty fields
        if (name.isEmpty() || surname.isEmpty() || email.isEmpty() || cell.isEmpty() ||
                login.isEmpty() || password.isEmpty() || confirm.isEmpty()) {
            JOptionPane.showMessageDialog(null, "All fields are required.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Email validation using regex
        String emailRegex = "^[A-Za-z0-9+_.-]+@[A-Za-z0-9.-]+$";
        Pattern pattern = Pattern.compile(emailRegex);
        if (!pattern.matcher(email).matches()) {
            JOptionPane.showMessageDialog(null, "Invalid email format.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Cell number validation (simple: must be digits and at least 10 characters)
        if (!cell.matches("\\d{10,}")) {
            JOptionPane.showMessageDialog(null, "Cell number must contain at least 10 digits.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Password confirmation
        if (!password.equals(confirm)) {
            JOptionPane.showMessageDialog(null, "Passwords do not match.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Password strength (at least 8 characters, containing letters and numbers)
        if (password.length() < 8) {
            JOptionPane.showMessageDialog(null, "Password must be at least 8 characters long.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        if (!password.matches(".*[A-Za-z].*") || !password.matches(".*[0-9].*")) {
            JOptionPane.showMessageDialog(null, "Password must contain both letters and numbers.", "Validation Error", JOptionPane.ERROR_MESSAGE);
            return false;
        }
        // Check if login is unique (simplified)
        for (User user : userArchive) {
            if (user.getLogin().equals(login)) {
                JOptionPane.showMessageDialog(null, "Login already exists. Please choose a different one.", "Validation Error", JOptionPane.ERROR_MESSAGE);
                return false;
            }
        }
        return true;
    }
    /**
     * Handles data errors as per the use case. This could involve logging, notifying the administrator,
     * and possibly interrupting the connection to the server.
     */
    private static void handleDataError() {
        // Notify the administrator of the data error
        JOptionPane.showMessageDialog(null, "Data entered is not valid. Please correct the errors.", "Data Error", JOptionPane.ERROR_MESSAGE);
        // Simulate server connection interruption (postcondition)
        if (isConnected) {
            isConnected = false;
            System.out.println("SMOS server connection interrupted due to data error.");
            // In a real application, you would have proper server disconnection logic here.
        }
    }
}
/**
 * User.java
 *
 * Represents a user in the system with all required fields.
 */
class User {
    private String name;
    private String surname;
    private String email;
    private String cell;
    private String login;
    private String password;
    public User(String name, String surname, String email, String cell, String login, String password) {
        this.name = name;
        this.surname = surname;
        this.email = email;
        this.cell = cell;
        this.login = login;
        this.password = password;
    }
    // Getters and setters (only getter for login is used in validation)
    public String getLogin() {
        return login;
    }
    public String getName() {
        return name;
    }
    public void setName(String name) {
        this.name = name;
    }
    public String getSurname() {
        return surname;
    }
    public void setSurname(String surname) {
        this.surname = surname;
    }
    public String getEmail() {
        return email;
    }
    public void setEmail(String email) {
        this.email = email;
    }
    public String getCell() {
        return cell;
    }
    public void setCell(String cell) {
        this.cell = cell;
    }
    public void setLogin(String login) {
        this.login = login;
    }
    public String getPassword() {
        return password;
    }
    public void setPassword(String password) {
        this.password = password;
    }
    @Override
    public String toString() {
        return name + " " + surname + " (" + email + ")";
    }
}