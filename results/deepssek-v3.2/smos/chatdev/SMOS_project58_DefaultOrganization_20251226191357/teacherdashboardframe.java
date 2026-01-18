'''
Dashboard for teachers after successful login.
Contains the "On-line report cards" button as required by use case preconditions.
'''
package com.chatdev.reportcardsystem.gui;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TeacherDashboardFrame extends JFrame {
    private ReportCardSystem system;
    public TeacherDashboardFrame() {
        this.system = new ReportCardSystem();
        system.getServerConnection().connect("teacher", "password123");
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Teacher Dashboard - SMOS System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(500, 400);
        setLocationRelativeTo(null);
        setLayout(new BorderLayout());
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        topPanel.setBackground(new Color(70, 130, 180));
        JLabel titleLabel = new JLabel("SMOS Teacher Portal", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 22));
        titleLabel.setForeground(Color.WHITE);
        titleLabel.setBorder(BorderFactory.createEmptyBorder(20, 0, 20, 0));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        add(topPanel, BorderLayout.NORTH);
        JPanel centerPanel = new JPanel();
        centerPanel.setLayout(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(40, 20, 40, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(20, 20, 20, 20);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        JLabel welcomeLabel = new JLabel("Welcome, Teacher!", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        centerPanel.add(welcomeLabel, gbc);
        JLabel infoLabel = new JLabel("What would you like to do today?", SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        gbc.gridy = 1;
        centerPanel.add(infoLabel, gbc);
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new GridLayout(3, 1, 15, 15));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(20, 50, 20, 50));
        JButton reportCardsButton = new JButton("On-line report cards");
        reportCardsButton.setFont(new Font("Arial", Font.BOLD, 14));
        reportCardsButton.setPreferredSize(new Dimension(200, 50));
        reportCardsButton.setBackground(new Color(70, 130, 180));
        reportCardsButton.setForeground(Color.WHITE);
        JButton profileButton = new JButton("My Profile");
        profileButton.setFont(new Font("Arial", Font.PLAIN, 14));
        profileButton.setPreferredSize(new Dimension(200, 40));
        JButton settingsButton = new JButton("System Settings");
        settingsButton.setFont(new Font("Arial", Font.PLAIN, 14));
        settingsButton.setPreferredSize(new Dimension(200, 40));
        buttonPanel.add(reportCardsButton);
        buttonPanel.add(profileButton);
        buttonPanel.add(settingsButton);
        gbc.gridy = 2;
        gbc.fill = GridBagConstraints.NONE;
        centerPanel.add(buttonPanel, gbc);
        add(centerPanel, BorderLayout.CENTER);
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20));
        JButton logoutButton = new JButton("Logout");
        logoutButton.setPreferredSize(new Dimension(100, 30));
        bottomPanel.add(logoutButton);
        add(bottomPanel, BorderLayout.SOUTH);
        reportCardsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
                MainTeacherFrame teacherFrame = new MainTeacherFrame(system);
                teacherFrame.setVisible(true);
            }
        });
        profileButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TeacherDashboardFrame.this,
                    "Profile feature coming soon!",
                    "Feature Not Available",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        settingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                JOptionPane.showMessageDialog(TeacherDashboardFrame.this,
                    "System settings feature coming soon!",
                    "Feature Not Available",
                    JOptionPane.INFORMATION_MESSAGE);
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(
                    TeacherDashboardFrame.this,
                    "Are you sure you want to logout?",
                    "Confirm Logout",
                    JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    system.disconnectFromServer();
                    JOptionPane.showMessageDialog(
                        TeacherDashboardFrame.this,
                        "Successfully disconnected from SMOS server.\nSession terminated.",
                        "Server Disconnected",
                        JOptionPane.INFORMATION_MESSAGE
                    );
                    dispose();
                    LoginFrame loginFrame = new LoginFrame();
                    loginFrame.setVisible(true);
                }
            }
        });
    }
}