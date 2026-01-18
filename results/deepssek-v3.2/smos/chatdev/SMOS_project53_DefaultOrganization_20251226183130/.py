/**
 * Main file for the ViewClassesList application.
 * This application provides a GUI for ATA staff to view all classes in the database.
 * It includes a login screen and a main screen to display classes with a key to access each class's registry.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.sql.*;
import java.util.ArrayList;
import java.util.List;
/**
 * The Main class that contains the main method to run the application.
 */
public class ViewClassesList {
    public static void main(String[] args) {
        SwingUtilities.invokeLater(() -> {
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
        });
    }
}
/**
 * Class representing a Class entity.
 */
class ClassRecord {
    private int id;
    private String className;
    private String instructor;
    private String schedule;
    public ClassRecord(int id, String className, String instructor, String schedule) {
        this.id = id;
        this.className = className;
        this.instructor = instructor;
        this.schedule = schedule;
    }
    public int getId() {
        return id;
    }
    public String getClassName() {
        return className;
    }
    public String getInstructor() {
        return instructor;
    }
    public String getSchedule() {
        return schedule;
    }
}
/**
 * Database connection and operations class.
 * In a real application, you would use a connection pool and handle errors more robustly.
 */
class DatabaseConnection {
    private static final String URL = "jdbc:mysql://localhost:3306/school_db";
    private static final String USER = "root";
    private static final String PASSWORD = "password";
    /**
     * Get a connection to the database.
     * @return Connection object
     * @throws SQLException if connection fails
     */
    public static Connection getConnection() throws SQLException {
        return DriverManager.getConnection(URL, USER, PASSWORD);
    }
    /**
     * Fetch all classes from the database.
     * @return List of ClassRecord objects
     */
    public static List<ClassRecord> getAllClasses() {
        List<ClassRecord> classes = new ArrayList<>();
        String query = "SELECT id, class_name, instructor, schedule FROM classes";
        try (Connection conn = getConnection();
             Statement stmt = conn.createStatement();
             ResultSet rs = stmt.executeQuery(query)) {
            while (rs.next()) {
                int id = rs.getInt("id");
                String className = rs.getString("class_name");
                String instructor = rs.getString("instructor");
                String schedule = rs.getString("schedule");
                classes.add(new ClassRecord(id, className, instructor, schedule));
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Error fetching classes from database: " + e.getMessage(),
                    "Database Error", JOptionPane.ERROR_MESSAGE);
        }
        return classes;
    }
    /**
     * Validate user login.
     * In a real application, you should hash passwords and use prepared statements to prevent SQL injection.
     * @param username the username
     * @param password the password
     * @return true if login is successful, false otherwise
     */
    public static boolean validateLogin(String username, String password) {
        String query = "SELECT COUNT(*) FROM staff WHERE username = ? AND password = ? AND role = 'staff'";
        try (Connection conn = getConnection();
             PreparedStatement pstmt = conn.prepareStatement(query)) {
            pstmt.setString(1, username);
            pstmt.setString(2, password);
            ResultSet rs = pstmt.executeQuery();
            if (rs.next()) {
                return rs.getInt(1) > 0;
            }
        } catch (SQLException e) {
            e.printStackTrace();
            JOptionPane.showMessageDialog(null, "Database error during login: " + e.getMessage(),
                    "Login Error", JOptionPane.ERROR_MESSAGE);
        }
        return false;
    }
}
/**
 * Login frame for ATA staff.
 */
class LoginFrame extends JFrame {
    private JTextField usernameField;
    private JPasswordField passwordField;
    private JButton loginButton;
    private JPanel panel;
    public LoginFrame() {
        setTitle("ATA Staff Login");
        setSize(400, 200);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.gridx = 0;
        gbc.gridy = 0;
        panel.add(new JLabel("Username:"), gbc);
        gbc.gridy = 1;
        panel.add(new JLabel("Password:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        usernameField = new JTextField(20);
        panel.add(usernameField, gbc);
        gbc.gridy = 1;
        passwordField = new JPasswordField(20);
        panel.add(passwordField, gbc);
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        loginButton = new JButton("Login");
        panel.add(loginButton, gbc);
        // Add action listener to login button
        loginButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String username = usernameField.getText();
                String password = new String(passwordField.getPassword());
                if (username.isEmpty() || password.isEmpty()) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Please enter both username and password.",
                            "Input Error", JOptionPane.WARNING_MESSAGE);
                    return;
                }
                // Validate login
                if (DatabaseConnection.validateLogin(username, password)) {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Login successful!",
                            "Success", JOptionPane.INFORMATION_MESSAGE);
                    // Open the main frame
                    MainFrame mainFrame = new MainFrame();
                    mainFrame.setVisible(true);
                    LoginFrame.this.dispose();
                } else {
                    JOptionPane.showMessageDialog(LoginFrame.this, "Invalid username or password.",
                            "Login Failed", JOptionPane.ERROR_MESSAGE);
                }
            }
        });
        add(panel);
    }
}
/**
 * Main frame that displays the list of classes.
 */
class MainFrame extends JFrame {
    private JTable classTable;
    private DefaultTableModel tableModel;
    private JButton viewRegistryButton;
    private JPanel panel;
    public MainFrame() {
        setTitle("Class List - ATA Staff");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        panel = new JPanel(new BorderLayout());
        // Table setup
        String[] columnNames = {"ID", "Class Name", "Instructor", "Schedule"};
        tableModel = new DefaultTableModel(columnNames, 0);
        classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        JScrollPane scrollPane = new JScrollPane(classTable);
        panel.add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        viewRegistryButton = new JButton("View Registry");
        buttonPanel.add(viewRegistryButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        // Populate table with data
        loadClassData();
        // Add action listener to view registry button
        viewRegistryButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = classTable.getSelectedRow();
                if (selectedRow >= 0) {
                    int classId = (int) tableModel.getValueAt(selectedRow, 0);
                    String className = (String) tableModel.getValueAt(selectedRow, 1);
                    // In a complete application, you would open a new window showing the registry for this class.
                    // For now, we show a message.
                    JOptionPane.showMessageDialog(MainFrame.this,
                            "Opening registry for class: " + className + " (ID: " + classId + ")",
                            "View Registry", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(MainFrame.this, "Please select a class from the table.",
                            "No Selection", JOptionPane.WARNING_MESSAGE);
                }
            }
        });
        add(panel);
    }
    /**
     * Load class data from database into the table.
     */
    private void loadClassData() {
        List<ClassRecord> classes = DatabaseConnection.getAllClasses();
        for (ClassRecord cr : classes) {
            Object[] row = {cr.getId(), cr.getClassName(), cr.getInstructor(), cr.getSchedule()};
            tableModel.addRow(row);
        }
        if (classes.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No classes found in the database.",
                    "No Data", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}