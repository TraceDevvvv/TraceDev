'''
Main entry point for the Digital Register application.
This application allows a user (Direction role) to view a list of registers
for a selected academic year. It provides a GUI for selecting the year and
displays the registers grouped by class.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.time.Year;
import java.util.ArrayList;
import java.util.List;
public class Main {
    /**
     * Main entry point for the Digital Register application.
     * 
     * @param args command line arguments (unused)
     */
    public static void main(String[] args) {
        // Launch the GUI on the Event Dispatch Thread for thread safety
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
/**
 * Login frame for authenticating the user as Direction.
 * Precondition: User must be logged in as Direction to access the main functionality.
 * This is a simulation - in a real system, authentication would connect to a server/database.
 */
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JComboBox<String> roleComboBox;
    public LoginFrame() {
        setTitle("Digital Register - Login");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 250);
        setLocationRelativeTo(null); // Center the window
        // Main panel with padding
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title label
        JLabel titleLabel = new JLabel("Please login as Direction", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        formPanel.add(new JLabel("Role:"));
        roleComboBox = new JComboBox<>(new String[]{"Select role", "Teacher", "Student", "Admin", "Direction"});
        formPanel.add(roleComboBox);
        // Login button
        JButton loginButton = new JButton("Login");
        loginButton.addActionListener(e -> authenticate());
        formPanel.add(new JLabel()); // placeholder
        formPanel.add(loginButton);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Add informational label
        JLabel infoLabel = new JLabel("Note: This is a simulation. Default role is 'Direction'", 
                                     SwingConstants.CENTER);
        infoLabel.setFont(new Font("Arial", Font.ITALIC, 10));
        infoLabel.setForeground(Color.GRAY);
        mainPanel.add(infoLabel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    /**
     * Authenticates the user. This is a simulation - in a real application, 
     * this would connect to a server. For this simulation, we verify that 
     * the user has selected the "Direction" role.
     */
    private void authenticate() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        String selectedRole = (String) roleComboBox.getSelectedItem();
        // Validate input fields
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this, "Username and password cannot be empty.",
                    "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check if role is selected
        if (selectedRole == null || selectedRole.equals("Select role")) {
            JOptionPane.showMessageDialog(this, "Please select a user role.",
                    "Role Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Verify user has "Direction" role as specified in the preconditions
        if (!selectedRole.equals("Direction")) {
            JOptionPane.showMessageDialog(this, 
                    "Access denied. This application requires 'Direction' role.\n" +
                    "Selected role: " + selectedRole,
                    "Authorization Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Simulate successful authentication for Direction role
        JOptionPane.showMessageDialog(this, 
                "Login successful as Direction user: " + username,
                "Success", JOptionPane.INFORMATION_MESSAGE);
        dispose(); // close login window
        new YearSelectionFrame().setVisible(true); // open year selection
    }
}
/**
 * Frame for selecting the academic year of interest.
 * This corresponds to step 1 in the event sequence: 
 * "Show a screen for the selection of the academic year of interest."
 */
class YearSelectionFrame extends JFrame {
    private JComboBox<Integer> yearComboBox;
    public YearSelectionFrame() {
        setTitle("Digital Register - Select Academic Year");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(400, 200);
        setLocationRelativeTo(null);
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Instruction label
        JLabel instructionLabel = new JLabel("Select the academic year:", SwingConstants.CENTER);
        instructionLabel.setFont(new Font("Arial", Font.BOLD, 14));
        mainPanel.add(instructionLabel, BorderLayout.NORTH);
        // Year selection panel
        JPanel selectionPanel = new JPanel(new FlowLayout());
        yearComboBox = new JComboBox<>(getAcademicYears());
        yearComboBox.setPreferredSize(new Dimension(150, 30));
        selectionPanel.add(yearComboBox);
        // "Digital Register" button (as per precondition)
        JButton digitalRegisterButton = new JButton("Digital Register");
        digitalRegisterButton.addActionListener(e -> selectYear());
        selectionPanel.add(digitalRegisterButton);
        mainPanel.add(selectionPanel, BorderLayout.CENTER);
        add(mainPanel);
    }
    /**
     * Generates a list of recent academic years for the combo box.
     * In a real application, this could be fetched from a database.
     * 
     * @return array of academic years
     */
    private Integer[] getAcademicYears() {
        List<Integer> years = new ArrayList<>();
        int currentYear = Year.now().getValue();
        // Show years from 5 years ago to 1 year in the future
        for (int i = currentYear - 5; i <= currentYear + 1; i++) {
            years.add(i);
        }
        return years.toArray(new Integer[0]);
    }
    /**
     * Handles the year selection event (step 2 in the sequence).
     * This simulates the user selecting the year and clicking "Digital Register".
     */
    private void selectYear() {
        Integer selectedYear = (Integer) yearComboBox.getSelectedItem();
        if (selectedYear == null) {
            JOptionPane.showMessageDialog(this, "Please select an academic year.",
                    "Selection Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // In a real system, this would trigger a server request to fetch registers.
        // For this example, we simulate fetching and displaying.
        dispose(); // close this window
        new RegisterListFrame(selectedYear).setVisible(true);
    }
}
/**
 * Frame that displays the list of digital registers for the selected academic year,
 * grouped by class. This corresponds to step 3 in the event sequence.
 */
class RegisterListFrame extends JFrame {
    private int academicYear;
    private JTable registerTable;
    private DefaultTableModel tableModel;
    public RegisterListFrame(int academicYear) {
        this.academicYear = academicYear;
        setTitle("Digital Register - Registers for " + academicYear);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually for postconditions
        setSize(600, 400);
        setLocationRelativeTo(null);
        // Handle window closing to simulate interruption/connection loss
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleInterruption();
            }
        });
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title label
        JLabel titleLabel = new JLabel("Digital Registers for Academic Year: " + academicYear,
                SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Table to display registers
        String[] columnNames = {"Class", "Register ID", "Teacher", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        registerTable = new JTable(tableModel);
        registerTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(registerTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Button panel with Back and Refresh buttons
        JPanel buttonPanel = new JPanel(new FlowLayout());
        JButton backButton = new JButton("Back");
        backButton.addActionListener(e -> goBack());
        buttonPanel.add(backButton);
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadRegisters());
        buttonPanel.add(refreshButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        loadRegisters(); // Load data on startup
    }
    /**
     * Simulates fetching registers from an archive (e.g., a server/database).
     * In a real application, this would involve a network call.
     */
    private void loadRegisters() {
        // Clear existing rows
        tableModel.setRowCount(0);
        // Simulate fetching data
        List<Register> registers = fetchRegistersFromArchive();
        if (registers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No registers found for the selected year.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Populate the table
        for (Register reg : registers) {
            Object[] row = {reg.className, reg.registerId, reg.teacher, reg.status};
            tableModel.addRow(row);
        }
    }
    /**
     * Mock method that returns a list of registers for the selected year.
     * In reality, this would be replaced by a service call to a backend.
     * 
     * @return list of register objects
     */
    private List<Register> fetchRegistersFromArchive() {
        List<Register> registers = new ArrayList<>();
        // Sample data - in production, this would come from a database or server.
        registers.add(new Register("10A", "REG001", "Mr. Smith", "Active"));
        registers.add(new Register("10B", "REG002", "Ms. Johnson", "Archived"));
        registers.add(new Register("11A", "REG003", "Dr. Williams", "Active"));
        registers.add(new Register("11B", "REG004", "Mrs. Brown", "Pending"));
        registers.add(new Register("12A", "REG005", "Prof. Davis", "Active"));
        return registers;
    }
    /**
     * Handles interruption events (e.g., user closes window or connection is lost).
     * This simulates the postcondition about interruption.
     */
    private void handleInterruption() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Interrupting the operation will close the connection to the SMOS server.\nDo you want to proceed?",
                "Interrupt Operation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Connection to SMOS server interrupted. Operation cancelled.",
                    "Interrupted", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close this window
            // Return to login screen instead of exiting application
            new LoginFrame().setVisible(true);
        }
        // If NO, do nothing (window stays open)
    }
    /**
     * Navigates back to the year selection screen.
     */
    private void goBack() {
        dispose(); // Close current window
        new YearSelectionFrame().setVisible(true); // Open year selection
    }
    /**
     * Inner class representing a Digital Register entry.
     */
    private static class Register {
        String className;
        String registerId;
        String teacher;
        String status;
        Register(String className, String registerId, String teacher, String status) {
            this.className = className;
            this.registerId = registerId;
            this.teacher = teacher;
            this.status = status;
        }
    }
}