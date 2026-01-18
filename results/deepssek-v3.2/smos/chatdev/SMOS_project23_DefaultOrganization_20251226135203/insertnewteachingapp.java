import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.List;
/**
 * Main application class that implements the InsertNewTeaching use case.
 * This is a fully runnable program with GUI for administrators to insert new teachings.
 */
public class InsertNewTeachingApp {
    private JFrame mainFrame;
    private JPanel teachingListPanel;
    private JPanel formPanel;
    private DefaultListModel<String> teachingListModel;
    private JTextField teachingNameField;
    public InsertNewTeachingApp() {
        initializeUI();
    }
    /**
     * Initializes the main application UI with login, teaching list, and form.
     */
    private void initializeUI() {
        mainFrame = new JFrame("Teaching Management System");
        mainFrame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        mainFrame.setSize(600, 500);
        mainFrame.setLayout(new BorderLayout());
        if (!AuthenticationManager.isAdminLoggedIn()) {
            showLoginDialog();
        }
        createTeachingListPanel();
        createFormPanel();
        mainFrame.setVisible(true);
        refreshTeachingList();
    }
    /**
     * Shows login dialog for admin authentication.
     */
    private void showLoginDialog() {
        JPanel loginPanel = new JPanel(new GridLayout(3, 2));
        JTextField usernameField = new JTextField(10);
        JPasswordField passwordField = new JPasswordField(10);
        loginPanel.add(new JLabel("Username:"));
        loginPanel.add(usernameField);
        loginPanel.add(new JLabel("Password:"));
        loginPanel.add(passwordField);
        int result = JOptionPane.showConfirmDialog(mainFrame, loginPanel, 
                "Admin Login", JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText();
            String password = new String(passwordField.getPassword());
            if (!AuthenticationManager.login(username, password)) {
                JOptionPane.showMessageDialog(mainFrame, 
                        "Login failed. You must be an administrator to access this system.",
                        "Login Error", JOptionPane.ERROR_MESSAGE);
                System.exit(0);
            }
        } else {
            JOptionPane.showMessageDialog(mainFrame, 
                    "Login cancelled. Application will exit.",
                    "Login Cancelled", JOptionPane.WARNING_MESSAGE);
            System.exit(0);
        }
    }
    /**
     * Creates the panel that displays the list of teachings.
     */
    private void createTeachingListPanel() {
        teachingListPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Existing Teachings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        teachingListModel = new DefaultListModel<>();
        JList<String> teachingList = new JList<>(teachingListModel);
        JScrollPane scrollPane = new JScrollPane(teachingList);
        JButton newTeachingButton = new JButton("New Teaching");
        newTeachingButton.addActionListener(e -> {
            formPanel.setVisible(true);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        teachingListPanel.add(titleLabel, BorderLayout.NORTH);
        teachingListPanel.add(scrollPane, BorderLayout.CENTER);
        teachingListPanel.add(newTeachingButton, BorderLayout.SOUTH);
        teachingListPanel.setPreferredSize(new Dimension(300, 500));
        mainFrame.add(teachingListPanel, BorderLayout.WEST);
    }
    /**
     * Creates the form panel for entering new teaching data.
     */
    private void createFormPanel() {
        formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Insert New Teaching"));
        formPanel.setVisible(false);
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Teaching Name:"), gbc);
        gbc.gridx = 1;
        teachingNameField = new JTextField(20);
        formPanel.add(teachingNameField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        JButton saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveTeaching());
        formPanel.add(saveButton, gbc);
        gbc.gridy = 2;
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            teachingNameField.setText("");
            formPanel.setVisible(false);
            mainFrame.revalidate();
            mainFrame.repaint();
        });
        formPanel.add(cancelButton, gbc);
        formPanel.setPreferredSize(new Dimension(300, 500));
        mainFrame.add(formPanel, BorderLayout.EAST);
    }
    /**
     * Saves the teaching after validation and inserts it into the archive.
     * Implements the save functionality described in the events sequence.
     */
    private void saveTeaching() {
        String teachingName = teachingNameField.getText().trim();
        Teaching newTeaching = new Teaching(teachingName);
        try {
            boolean success = DatabaseManager.insertTeaching(newTeaching);
            if (success) {
                JOptionPane.showMessageDialog(mainFrame, 
                        "Teaching '" + teachingName + "' has been successfully inserted into the archive.",
                        "Success", JOptionPane.INFORMATION_MESSAGE);
                teachingNameField.setText("");
                formPanel.setVisible(false);
                refreshTeachingList();
            } else {
                ErrodatiHandler.reportInvalidTeachingData(teachingName, "Invalid teaching name data");
                showErrorDialog("The data entered is not valid. Please enter a valid teaching name.");
            }
        } catch (Exception ex) {
            showErrorDialog("Error: " + ex.getMessage() + "\nOperation failed.");
        }
        mainFrame.revalidate();
        mainFrame.repaint();
    }
    /**
     * Shows error dialog for invalid data or server issues.
     *
     * @param message The error message to display
     */
    private void showErrorDialog(String message) {
        JOptionPane.showMessageDialog(mainFrame, 
                message,
                "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Refreshes the teaching list display with current archive data.
     */
    private void refreshTeachingList() {
        teachingListModel.clear();
        List<Teaching> teachings = DatabaseManager.getAllTeachings();
        for (Teaching teaching : teachings) {
            teachingListModel.addElement(teaching.getTeachingName());
        }
    }
    /**
     * Main method to run the application.
     *
     * @param args command line arguments (not used)
     */
    public static void main(String[] args) {
        DatabaseManager.setServerConnected(true);
        DatabaseManager.insertTeaching(new Teaching("Introduction to Programming"));
        DatabaseManager.insertTeaching(new Teaching("Data Structures"));
        DatabaseManager.insertTeaching(new Teaching("Algorithms Design"));
        SwingUtilities.invokeLater(() -> {
            new InsertNewTeachingApp();
        });
    }
}