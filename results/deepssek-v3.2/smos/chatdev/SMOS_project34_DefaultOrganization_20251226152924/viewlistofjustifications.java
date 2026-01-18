/**
 * Main class to run the ViewListofJustifications application.
 * Contains the main method that first authenticates an administrator
 * before showing the main justification viewing interface.
 * This program simulates an administrator viewing a list of student absences with justifications.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class ViewListofJustifications {
    private static boolean isAdminAuthenticated = false;
    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // First, show login dialog for administrator authentication
                showLoginDialog();
                // Only show main frame if admin authentication was successful
                if (isAdminAuthenticated) {
                    new JustificationFrame().setVisible(true);
                } else {
                    System.exit(0); // Close application if login failed or cancelled
                }
            }
        });
    }
    /**
     * Displays a login dialog to authenticate the administrator.
     * Simulates the precondition that user must be logged in as administrator.
     */
    private static void showLoginDialog() {
        LoginDialog loginDialog = new LoginDialog();
        loginDialog.setVisible(true);
        isAdminAuthenticated = loginDialog.isAuthenticated();
    }
}
/**
 * Login Dialog for administrator authentication.
 * This simulates the login process required by the use case preconditions.
 */
class LoginDialog extends JDialog {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton, cancelButton;
    private boolean authenticated = false;
    // Hardcoded admin credentials for simulation (in real application, these would be validated against a database)
    private static final String ADMIN_USERNAME = "admin";
    private static final String ADMIN_PASSWORD = "admin123";
    public LoginDialog() {
        super((Frame) null, "Administrator Login", true); // Modal dialog
        setSize(350, 200);
        setLocationRelativeTo(null);
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        initializeComponents();
        setupEventHandlers();
    }
    private void initializeComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Form panel
        JPanel formPanel = new JPanel(new GridLayout(2, 2, 10, 10));
        formPanel.add(new JLabel("Username:"));
        usernameField = new JTextField();
        formPanel.add(usernameField);
        formPanel.add(new JLabel("Password:"));
        passwordField = new JPasswordField();
        formPanel.add(passwordField);
        mainPanel.add(formPanel, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        loginButton = new JButton("Login");
        cancelButton = new JButton("Cancel");
        buttonPanel.add(cancelButton);
        buttonPanel.add(loginButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Info label
        JLabel infoLabel = new JLabel("Please login as administrator to continue.");
        infoLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(infoLabel, BorderLayout.NORTH);
        add(mainPanel);
        getRootPane().setDefaultButton(loginButton);
    }
    private void setupEventHandlers() {
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                authenticated = false;
                dispose();
            }
        });
        // Allow pressing Enter on username field to move to password
        usernameField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                passwordField.requestFocus();
            }
        });
        // Allow pressing Enter on password field to login
        passwordField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                attemptLogin();
            }
        });
        // Handle window closing
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                authenticated = false;
            }
        });
    }
    /**
     * Attempts to authenticate the user with provided credentials.
     * Simulates the administrator login requirement from the use case.
     */
    private void attemptLogin() {
        String username = usernameField.getText().trim();
        String password = new String(passwordField.getPassword());
        // Validate credentials
        if (username.isEmpty() || password.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                "Please enter both username and password.",
                "Login Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Check against hardcoded admin credentials (for simulation)
        if (username.equals(ADMIN_USERNAME) && password.equals(ADMIN_PASSWORD)) {
            authenticated = true;
            JOptionPane.showMessageDialog(this,
                "Login successful! Welcome, Administrator.",
                "Success", JOptionPane.INFORMATION_MESSAGE);
            dispose();
        } else {
            authenticated = false;
            JOptionPane.showMessageDialog(this,
                "Invalid username or password. Please try again.\n\n" +
                "Demo credentials:\nUsername: admin\nPassword: admin123",
                "Login Failed", JOptionPane.ERROR_MESSAGE);
            // Clear password field for retry
            passwordField.setText("");
            passwordField.requestFocus();
        }
    }
    public boolean isAuthenticated() {
        return authenticated;
    }
}
/**
 * Main frame for the Justification View application.
 * Displays a table of student absences with color-coded rows for justified and unjustified absences.
 * Simulates the use case where an administrator clicks the justification button for a student.
 * This frame is only shown after successful administrator authentication.
 */
class JustificationFrame extends JFrame {
    private JTable absenceTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JButton disconnectButton;
    private JLabel userLabel; // Display logged in user
    private boolean connectedToSMOS = true; // Simulate server connection status
    public JustificationFrame() {
        setTitle("View List of Justifications - Administrator");
        setSize(900, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout());
        // Initialize components
        initializeComponents();
        // Load sample data for demonstration
        loadSampleData();
        // Set up event handlers
        setupEventHandlers();
    }
    private void initializeComponents() {
        // Top panel with title and user info
        JPanel topPanel = new JPanel(new BorderLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JLabel topLabel = new JLabel("Student Absences - Justified (Green) vs Unjustified (Red)");
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setFont(new Font("Arial", Font.BOLD, 18));
        topPanel.add(topLabel, BorderLayout.NORTH);
        // User info (showing authenticated administrator)
        userLabel = new JLabel("Logged in as: Administrator | Connected to SMOS Server");
        userLabel.setHorizontalAlignment(SwingConstants.RIGHT);
        userLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        userLabel.setForeground(new Color(0, 100, 0));
        topPanel.add(userLabel, BorderLayout.SOUTH);
        add(topPanel, BorderLayout.NORTH);
        // Create table model with columns: Date, Reason, Status (Justified/Unjustified)
        String[] columns = {"Date", "Reason", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            // Make rows non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        absenceTable = new JTable(tableModel);
        absenceTable.setRowHeight(35);
        absenceTable.setFont(new Font("Arial", Font.PLAIN, 14));
        // Use a custom renderer to color rows based on status
        absenceTable.setDefaultRenderer(Object.class, new JustificationRenderer());
        // Set column widths
        absenceTable.getColumnModel().getColumn(0).setPreferredWidth(120);
        absenceTable.getColumnModel().getColumn(1).setPreferredWidth(400);
        absenceTable.getColumnModel().getColumn(2).setPreferredWidth(120);
        // Center align the status column
        absenceTable.getColumnModel().getColumn(2).setCellRenderer(new CenterRenderer());
        JScrollPane scrollPane = new JScrollPane(absenceTable);
        scrollPane.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for status and disconnect button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        statusLabel = new JLabel("Showing absences for selected student.");
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(statusLabel, BorderLayout.WEST);
        disconnectButton = new JButton("Disconnect from SMOS Server");
        disconnectButton.setFont(new Font("Arial", Font.PLAIN, 14));
        bottomPanel.add(disconnectButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private void loadSampleData() {
        // Simulate data for a student's absences during the year
        List<AbsenceRecord> absences = new ArrayList<>();
        absences.add(new AbsenceRecord("2023-01-10", "Flu", true));
        absences.add(new AbsenceRecord("2023-02-15", "Unknown", false));
        absences.add(new AbsenceRecord("2023-03-22", "Family Event", true));
        absences.add(new AbsenceRecord("2023-04-05", "Unknown", false));
        absences.add(new AbsenceRecord("2023-05-18", "Medical Appointment", true));
        absences.add(new AbsenceRecord("2023-06-01", "Unknown", false));
        absences.add(new AbsenceRecord("2023-09-12", "Sports Competition", true));
        absences.add(new AbsenceRecord("2023-10-30", "Unknown", false));
        absences.add(new AbsenceRecord("2023-11-14", "Dental Appointment", true));
        absences.add(new AbsenceRecord("2023-12-05", "Unknown", false));
        // Add all absence records to the table
        for (AbsenceRecord absence : absences) {
            String status = absence.isJustified() ? "Justified" : "Unjustified";
            tableModel.addRow(new Object[]{absence.getDate(), absence.getReason(), status});
        }
        // Update status to show count of absences
        updateStatusLabel(absences);
    }
    private void updateStatusLabel(List<AbsenceRecord> absences) {
        int totalAbsences = absences.size();
        long justifiedCount = absences.stream().filter(AbsenceRecord::isJustified).count();
        long unjustifiedCount = totalAbsences - justifiedCount;
        String statusText = String.format(
            "Showing %d absences (%d justified, %d unjustified) for the selected student.",
            totalAbsences, justifiedCount, unjustifiedCount
        );
        statusLabel.setText(statusText);
    }
    private void setupEventHandlers() {
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectedToSMOS) {
                    connectedToSMOS = false;
                    statusLabel.setText("Disconnected from SMOS Server. Connection interrupted.");
                    userLabel.setText("Logged in as: Administrator | DISCONNECTED from SMOS Server");
                    userLabel.setForeground(Color.RED);
                    disconnectButton.setEnabled(false);
                    JOptionPane.showMessageDialog(JustificationFrame.this,
                            "Disconnected from SMOS server as per postcondition.\n" +
                            "The system will continue to display cached data.",
                            "Server Disconnected", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    /**
     * Custom renderer to color table rows based on justification status.
     * Justified absences appear in green, unjustified in red.
     */
    class JustificationRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) table.getModel().getValueAt(row, 2);
            // Color coding based on justification status
            if ("Justified".equals(status)) {
                c.setBackground(new Color(220, 255, 220)); // Light green for justified
                c.setForeground(Color.BLACK);
            } else if ("Unjustified".equals(status)) {
                c.setBackground(new Color(255, 220, 220)); // Light red for unjustified
                c.setForeground(Color.BLACK);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }
            // Darken color if row is selected
            if (isSelected) {
                c.setBackground(c.getBackground().darker());
            }
            return c;
        }
    }
    /**
     * Center alignment renderer for table cells
     */
    class CenterRenderer extends DefaultTableCellRenderer {
        public CenterRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }
    }
}
/**
 * Data class representing a single absence record for a student.
 * Encapsulates date, reason, and justification status.
 */
class AbsenceRecord {
    private String date;
    private String reason;
    private boolean justified;
    public AbsenceRecord(String date, String reason, boolean justified) {
        this.date = date;
        this.reason = reason;
        this.justified = justified;
    }
    public String getDate() {
        return date;
    }
    public String getReason() {
        return reason;
    }
    public boolean isJustified() {
        return justified;
    }
}