'''
Login window for teacher authentication.
Verifies teacher credentials and establishes connection to SMOS server.
'''
package com.chatdev.reportcardsystem.gui;
import com.chatdev.reportcardsystem.model.ReportCardSystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    public LoginFrame() {
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Teacher Login - Report Card System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new GridBagLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(10, 10, 10, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel titleLabel = new JLabel("Teacher Login", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        mainPanel.add(titleLabel, gbc);
        JLabel userLabel = new JLabel("Username:");
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        mainPanel.add(userLabel, gbc);
        usernameField = new JTextField(15);
        gbc.gridx = 1;
        gbc.gridy = 1;
        mainPanel.add(usernameField, gbc);
        JLabel passLabel = new JLabel("Password:");
        gbc.gridx = 0;
        gbc.gridy = 2;
        mainPanel.add(passLabel, gbc);
        passwordField = new JPasswordField(15);
        gbc.gridx = 1;
        gbc.gridy = 2;
        mainPanel.add(passwordField, gbc);
        JButton loginButton = new JButton("Login");
        loginButton.setPreferredSize(new Dimension(100, 30));
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        gbc.anchor = GridBagConstraints.CENTER;
        mainPanel.add(loginButton, gbc);
        add(mainPanel, BorderLayout.CENTER);
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (authenticateTeacher(username, password)) {
                    dispose();
                    TeacherDashboardFrame dashboardFrame = new TeacherDashboardFrame();
                    dashboardFrame.setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this,
                        "Invalid credentials. Please try again.",
                        "Login Failed",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        });
    }
    private boolean authenticateTeacher(String username, String password) {
        return username.equals("teacher") && password.equals("password123");
    }
}