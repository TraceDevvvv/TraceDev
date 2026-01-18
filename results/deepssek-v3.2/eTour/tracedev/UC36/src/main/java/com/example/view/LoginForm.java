package com.example.view;

import com.example.controller.LoginController;
import com.example.model.FormState;

import javax.swing.JButton;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JPasswordField;
import javax.swing.JTextField;
import java.awt.BorderLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;

/**
 * GUI form for login. Implements state saving/restoring (Flow-4) and error confirmation (Flow-2, Flow-3).
 */
public class LoginForm extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JLabel errorLabel;
    private FormState currentFormState; // Added to satisfy requirement Flow-4

    private LoginController controller;

    /**
     * Constructs the login form and sets up UI.
     */
    public LoginForm() {
        setTitle("Login");
        setSize(300, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

        JPanel panel = new JPanel();
        getContentPane().add(panel, BorderLayout.CENTER);
        panel.setLayout(null);

        JLabel userLabel = new JLabel("Username:");
        userLabel.setBounds(10, 20, 80, 25);
        panel.add(userLabel);

        usernameField = new JTextField(20);
        usernameField.setBounds(100, 20, 165, 25);
        panel.add(usernameField);

        JLabel passLabel = new JLabel("Password:");
        passLabel.setBounds(10, 50, 80, 25);
        panel.add(passLabel);

        passwordField = new JPasswordField(20);
        passwordField.setBounds(100, 50, 165, 25);
        panel.add(passwordField);

        loginButton = new JButton("Login");
        loginButton.setBounds(10, 80, 80, 25);
        panel.add(loginButton);

        errorLabel = new JLabel("");
        errorLabel.setBounds(10, 110, 300, 25);
        panel.add(errorLabel);

        // Button action
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Simulate user entering invalid credentials and clicking login
                // As per sequence diagram: user -> form : Clicks login button
                // Save form state before authentication (Flow-4).
                saveFormState();
                // Then delegate to controller.
                if (controller != null) {
                    controller.handleLoginRequest(getUsername(), getPassword());
                }
            }
        });

        setVisible(true);
    }

    /**
     * Sets the controller for this form.
     * @param controller the login controller
     */
    public void setController(LoginController controller) {
        this.controller = controller;
    }

    /**
     * Displays an error message on the form.
     * @param message the error message
     */
    public void displayErrorMessage(String message) {
        errorLabel.setText(message);
    }

    /**
     * Returns the entered username.
     * @return username text
     */
    public String getUsername() {
        return usernameField.getText();
    }

    /**
     * Returns the entered password.
     * @return password as string
     */
    public String getPassword() {
        return new String(passwordField.getPassword());
    }

    /**
     * Request error confirmation from user.
     * Implements missing method from class diagram.
     * @return true if user confirms, false otherwise
     */
    public boolean requestErrorConfirmation() {
        return showErrorConfirmationDialog("Invalid credentials");
    }

    /**
     * Shows a confirmation dialog asking user to acknowledge the error.
     * Modified for Flow-2 & Flow-3: system asks for confirmation.
     * @param errorMsg the error message to show in dialog
     * @return true if user confirms, false otherwise
     */
    public boolean showErrorConfirmationDialog(String errorMsg) {
        int response = JOptionPane.showConfirmDialog(this,
                errorMsg + "\nPlease confirm you have read this error.",
                "Error Confirmation",
                JOptionPane.OK_CANCEL_OPTION,
                JOptionPane.ERROR_MESSAGE);
        return response == JOptionPane.OK_OPTION;
    }

    /**
     * Clears the form fields.
     */
    public void clearForm() {
        usernameField.setText("");
        passwordField.setText("");
        errorLabel.setText("");
    }

    /**
     * Saves the current form state into a FormState object (Flow-4).
     * @return the FormState representing current inputs
     */
    public FormState saveFormState() {
        currentFormState = new FormState(getUsername(), getPassword());
        return currentFormState;
    }

    /**
     * Restores the form to a previous state (Flow-4).
     * @param state the FormState to restore
     */
    public void restoreFormState(FormState state) {
        if (state != null) {
            usernameField.setText(state.getUsername());
            passwordField.setText(state.getPassword());
            // Note: password is restored as plain text; in real app, consider security.
        }
    }
}