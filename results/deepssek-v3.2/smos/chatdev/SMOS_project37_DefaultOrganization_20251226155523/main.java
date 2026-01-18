/**
 * Main class for Disciplinary Note Insertion System
 * Provides GUI for administrators to insert disciplinary notes
 * and send email notifications to parents
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.HashMap;
import java.util.Map;
public class Main {
    private JFrame frame;
    private JTextField studentField;
    private JTextField teacherField;
    private JTextArea descriptionArea;
    private JLabel dateLabel;
    private JButton saveButton;
    private JButton newNoteButton;
    private JButton viewRegistryButton;
    private JButton viewElonconoteButton;
    private boolean isAdminLoggedIn = false;
    private boolean hasCompletedSviewTetTingloregister = false;
    private boolean hasCompletedViewElonconote = false;
    private EmailService emailService;
    private DatabaseService databaseService;
    private UserAuthentication authService;
    public Main() {
        // Initialize serv
        emailService = new EmailService();
        databaseService = new DatabaseService();
        authService = new UserAuthentication();
        initializeGUI();
        // Show login dialog after GUI is initialized and visible on EDT
        SwingUtilities.invokeLater(this::showLoginDialog);
    }
    private void initializeGUI() {
        frame = new JFrame("Disciplinary Note Insertion System");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 550);
        frame.setLayout(new BorderLayout());
        // Create main panel
        JPanel mainPanel = new JPanel();
        mainPanel.setLayout(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Disciplinary Note Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Prerequisite panel
        JPanel prerequisitePanel = new JPanel();
        prerequisitePanel.setLayout(new GridLayout(3, 1, 5, 5));
        prerequisitePanel.setBorder(BorderFactory.createTitledBorder("Prerequisite Use Cases"));
        JLabel prerequisiteLabel = new JLabel("<html>Complete these steps before inserting a note:<br>1. SviewTetTingloregister<br>2. ViewElonconote</html>");
        prerequisitePanel.add(prerequisiteLabel);
        viewRegistryButton = new JButton("Complete SviewTetTingloregister");
        viewRegistryButton.addActionListener(e -> completeSviewTetTingloregister());
        prerequisitePanel.add(viewRegistryButton);
        viewElonconoteButton = new JButton("Complete ViewElonconote");
        viewElonconoteButton.addActionListener(e -> completeViewElonconote());
        prerequisitePanel.add(viewElonconoteButton);
        mainPanel.add(prerequisitePanel, BorderLayout.WEST);
        // Form panel
        JPanel formPanel = new JPanel();
        formPanel.setLayout(new BorderLayout(10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Insert Disciplinary Note"));
        JPanel inputPanel = new JPanel();
        inputPanel.setLayout(new GridLayout(4, 2, 10, 10));
        // Student field
        inputPanel.add(new JLabel("Student Name:"));
        studentField = new JTextField();
        studentField.setEnabled(false);
        inputPanel.add(studentField);
        // Date field (auto-populated with today's date)
        inputPanel.add(new JLabel("Date:"));
        dateLabel = new JLabel(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        dateLabel.setBorder(BorderFactory.createLineBorder(Color.LIGHT_GRAY));
        inputPanel.add(dateLabel);
        // Teacher field
        inputPanel.add(new JLabel("Teacher:"));
        teacherField = new JTextField();
        teacherField.setEnabled(false);
        inputPanel.add(teacherField);
        // Description field
        inputPanel.add(new JLabel("Description:"));
        JScrollPane scrollPane = new JScrollPane();
        descriptionArea = new JTextArea(5, 20);
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEnabled(false);
        scrollPane.setViewportView(descriptionArea);
        inputPanel.add(scrollPane);
        formPanel.add(inputPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        buttonPanel.setLayout(new FlowLayout(FlowLayout.CENTER, 10, 10));
        // New Note button
        newNoteButton = new JButton("New Note");
        newNoteButton.setEnabled(false);
        newNoteButton.addActionListener(e -> showNewNoteForm());
        buttonPanel.add(newNoteButton);
        // Save button
        saveButton = new JButton("Save");
        saveButton.setEnabled(false);
        saveButton.addActionListener(e -> saveNote());
        buttonPanel.add(saveButton);
        formPanel.add(buttonPanel, BorderLayout.SOUTH);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        frame.add(mainPanel);
        // Do not set visible here - wait for successful login
    }
    private void showLoginDialog() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2, 5, 5));
        JTextField usernameField = new JTextField();
        JPasswordField passwordField = new JPasswordField();
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        int result = JOptionPane.showConfirmDialog(frame, loginPanel, 
                "Administrator Login", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (authService.login(username, password)) {
                isAdminLoggedIn = true;
                frame.setVisible(true);
                updatePrerequisiteStatus();
            } else {
                retryLoginOrExit();
            }
        } else {
            exitApplication();
        }
    }
    private void retryLoginOrExit() {
        int choice = JOptionPane.showConfirmDialog(frame,
            "Invalid credentials. Would you like to try again?",
            "Login Failed",
            JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            showLoginDialog();
        } else {
            exitApplication();
        }
    }
    private void exitApplication() {
        frame.dispose();
        System.exit(0);
    }
    private void completeSviewTetTingloregister() {
        // Simulate completing the SviewTetTingloregister use case
        JOptionPane.showMessageDialog(frame,
            "SviewTetTingloregister use case completed successfully!\n" +
            "Student registry has been viewed and verified.",
            "Use Case Completed", JOptionPane.INFORMATION_MESSAGE);
        hasCompletedSviewTetTingloregister = true;
        updatePrerequisiteStatus();
        viewRegistryButton.setEnabled(false);
        viewRegistryButton.setText("✓ SviewTetTingloregister Completed");
    }
    private void completeViewElonconote() {
        // Simulate completing the ViewElonconote use case
        JOptionPane.showMessageDialog(frame,
            "ViewElonconote use case completed successfully!\n" +
            "Existing disciplinary notes have been reviewed.",
            "Use Case Completed", JOptionPane.INFORMATION_MESSAGE);
        hasCompletedViewElonconote = true;
        updatePrerequisiteStatus();
        viewElonconoteButton.setEnabled(false);
        viewElonconoteButton.setText("✓ ViewElonconote Completed");
    }
    private void updatePrerequisiteStatus() {
        // Enable New Note button only when all prerequisites are met
        if (hasCompletedSviewTetTingloregister && hasCompletedViewElonconote) {
            newNoteButton.setEnabled(true);
            JOptionPane.showMessageDialog(frame,
                "All prerequisites completed!\nYou can now insert a new disciplinary note.",
                "Ready for Note Insertion", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    private void showNewNoteForm() {
        // Check if all prerequisites are met
        if (!hasCompletedSviewTetTingloregister || !hasCompletedViewElonconote) {
            JOptionPane.showMessageDialog(frame, 
                "Please complete prerequisite use cases first:\n" +
                "1. SviewTetTingloregister\n" +
                "2. ViewElonconote\n\n" +
                "Use the buttons on the left to complete these steps.",
                "Prerequisites Required", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Clear form fields
        studentField.setText("");
        teacherField.setText("");
        descriptionArea.setText("");
        dateLabel.setText(LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        // Enable form for input
        studentField.setEnabled(true);
        teacherField.setEnabled(true);
        descriptionArea.setEnabled(true);
        saveButton.setEnabled(true);
        // Set focus to student field
        studentField.requestFocus();
    }
    private void saveNote() {
        // Validate input
        if (studentField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Student name is required!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            studentField.requestFocus();
            return;
        }
        if (teacherField.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Teacher name is required!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            teacherField.requestFocus();
            return;
        }
        if (descriptionArea.getText().trim().isEmpty()) {
            JOptionPane.showMessageDialog(frame, "Description is required!", 
                    "Validation Error", JOptionPane.ERROR_MESSAGE);
            descriptionArea.requestFocus();
            return;
        }
        // Create Note object
        Note note = new Note(
            studentField.getText().trim(),
            LocalDate.now(),
            teacherField.getText().trim(),
            descriptionArea.getText().trim()
        );
        try {
            // Save to database
            databaseService.saveNote(note);
            // Send email notification
            emailService.sendNotificationToParent(note);
            // Show success message
            JOptionPane.showMessageDialog(frame, 
                    "✓ Note saved successfully!\n✓ Email notification sent to parent.",
                    "Success", JOptionPane.INFORMATION_MESSAGE);
            // Return to registry screen
            returnToRegistryScreen();
        } catch (Exception e) {
            JOptionPane.showMessageDialog(frame, 
                    "Error saving note: " + e.getMessage(),
                    "Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    private void returnToRegistryScreen() {
        // Disable form fields
        studentField.setEnabled(false);
        teacherField.setEnabled(false);
        descriptionArea.setEnabled(false);
        saveButton.setEnabled(false);
        // Clear form
        studentField.setText("");
        teacherField.setText("");
        descriptionArea.setText("");
        // Show confirmation of registry screen return
        JOptionPane.showMessageDialog(frame, 
                "Returning to registry screen...",
                "Operation Complete", JOptionPane.INFORMATION_MESSAGE);
        // Simulate SMOS server interruption (postcondition)
        System.out.println("Note: Administrator interrupts the connection to the SMOS server (simulated)");
        // Reset prerequisites for next note insertion
        resetPrerequisites();
    }
    private void resetPrerequisites() {
        hasCompletedSviewTetTingloregister = false;
        hasCompletedViewElonconote = false;
        newNoteButton.setEnabled(false);
        viewRegistryButton.setEnabled(true);
        viewRegistryButton.setText("Complete SviewTetTingloregister");
        viewElonconoteButton.setEnabled(true);
        viewElonconoteButton.setText("Complete ViewElonconote");
        JOptionPane.showMessageDialog(frame,
            "Prerequisites reset for next note insertion.\n" +
            "Please complete prerequisite use cases again before inserting another note.",
            "Ready for Next Operation", JOptionPane.INFORMATION_MESSAGE);
    }
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            try {
                // Set look and feel to system default for better UI
                UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
            } catch (Exception e) {
                e.printStackTrace();
            }
            new Main();
        });
    }
}