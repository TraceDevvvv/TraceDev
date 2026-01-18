/**
 * Main application class for ViewDetailsSingleRegister use case.
 * Provides a GUI for the administrator to view details of a class register.
 * Simulates the prerequisite: user has executed "ViewingElCoregistri" and clicked "Details" button.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.*;
public class ViewDetailsSingleRegister extends JFrame {
    private JPanel mainPanel;
    private JComboBox<String> dateComboBox;
    private JTextArea detailsTextArea;
    private JButton logoutButton;
    private JButton submitJustificationButton;
    private JButton submitDisciplinaryNoteButton;
    private JComboBox<String> studentComboBox; // Changed from JTextField to JComboBox for better usability
    private JComboBox<String> statusComboBox;
    private JTextField justificationField;
    private JTextField disciplinaryNoteField;
    // In-memory data store for demonstration purposes
    private Map<String, ClassRegister> registers;
    private String selectedRegisterName; // Track which register was selected from previous screen
    public ViewDetailsSingleRegister(String selectedRegisterName) {
        this.selectedRegisterName = selectedRegisterName;
        initData();
        initUI();
        loadRegisterDetails();
    }
    /**
     * Initialize sample data for demonstration.
     * In a real application, this data would come from a database or server.
     */
    private void initData() {
        registers = new HashMap<>();
        // Sample Register 1
        ClassRegister cr1 = new ClassRegister("Class A");
        cr1.addAttendance(LocalDate.now().minusDays(2), 
            Arrays.asList(
                new StudentAttendance("John Doe", "present", "", ""),
                new StudentAttendance("Jane Smith", "absent", "sick", ""),
                new StudentAttendance("Bob Johnson", "late", "", "")
            )
        );
        cr1.addAttendance(LocalDate.now().minusDays(1),
            Arrays.asList(
                new StudentAttendance("John Doe", "present", "", ""),
                new StudentAttendance("Jane Smith", "present", "", ""),
                new StudentAttendance("Bob Johnson", "absent", "", "Disruptive behavior")
            )
        );
        cr1.addAttendance(LocalDate.now(),
            Arrays.asList(
                new StudentAttendance("John Doe", "present", "", ""),
                new StudentAttendance("Jane Smith", "late", "traffic", ""),
                new StudentAttendance("Bob Johnson", "present", "", "")
            )
        );
        registers.put("Class A", cr1);
        // Sample Register 2
        ClassRegister cr2 = new ClassRegister("Class B");
        cr2.addAttendance(LocalDate.now(), 
            Arrays.asList(
                new StudentAttendance("Alice Brown", "present", "", ""),
                new StudentAttendance("Charlie Davis", "absent", "", ""),
                new StudentAttendance("Eve Wilson", "present", "", "")
            )
        );
        registers.put("Class B", cr2);
    }
    /**
     * Initialize the GUI components and layout.
     */
    private void initUI() {
        setTitle("Class Register Details - Administrator");
        setDefaultCloseOperation(EXIT_ON_CLOSE);
        setSize(800, 600);
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top panel: Information and logout
        JPanel topPanel = new JPanel(new BorderLayout());
        JLabel titleLabel = new JLabel("Class Register Details: " + selectedRegisterName, SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        topPanel.add(titleLabel, BorderLayout.CENTER);
        logoutButton = new JButton("Logout");
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        JPanel logoutPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        logoutPanel.add(logoutButton);
        topPanel.add(logoutPanel, BorderLayout.EAST);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center panel: Date selection and details display
        JPanel centerPanel = new JPanel(new BorderLayout(10, 10));
        // Date selection panel
        JPanel datePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        datePanel.add(new JLabel("Select Date: "));
        dateComboBox = new JComboBox<>();
        updateDateComboBox(selectedRegisterName);
        dateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                displayAttendanceForSelectedDate();
                updateStudentComboBox(); // Update student list when date changes
            }
        });
        datePanel.add(dateComboBox);
        centerPanel.add(datePanel, BorderLayout.NORTH);
        // Details display area
        detailsTextArea = new JTextArea(20, 60);
        detailsTextArea.setEditable(false);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        JScrollPane scrollPane = new JScrollPane(detailsTextArea);
        centerPanel.add(scrollPane, BorderLayout.CENTER);
        mainPanel.add(centerPanel, BorderLayout.CENTER);
        // Bottom panel: Form to manage justifications and disciplinary notes
        JPanel bottomPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        // Student name (now a dropdown)
        gbc.gridx = 0; gbc.gridy = 0;
        bottomPanel.add(new JLabel("Student Name:"), gbc);
        studentComboBox = new JComboBox<>();
        gbc.gridx = 1; gbc.gridy = 0;
        bottomPanel.add(studentComboBox, gbc);
        // Status (present/absent/late)
        gbc.gridx = 0; gbc.gridy = 1;
        bottomPanel.add(new JLabel("Status:"), gbc);
        String[] statuses = {"present", "absent", "late"};
        statusComboBox = new JComboBox<>(statuses);
        gbc.gridx = 1; gbc.gridy = 1;
        bottomPanel.add(statusComboBox, gbc);
        // Justification
        gbc.gridx = 0; gbc.gridy = 2;
        bottomPanel.add(new JLabel("Justification (if any):"), gbc);
        justificationField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 2;
        bottomPanel.add(justificationField, gbc);
        // Disciplinary Note
        gbc.gridx = 0; gbc.gridy = 3;
        bottomPanel.add(new JLabel("Disciplinary Note (if any):"), gbc);
        disciplinaryNoteField = new JTextField(15);
        gbc.gridx = 1; gbc.gridy = 3;
        bottomPanel.add(disciplinaryNoteField, gbc);
        // Buttons
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        submitJustificationButton = new JButton("Submit Justification");
        submitJustificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitJustification();
            }
        });
        buttonPanel.add(submitJustificationButton);
        submitDisciplinaryNoteButton = new JButton("Submit Disciplinary Note");
        submitDisciplinaryNoteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitDisciplinaryNote();
            }
        });
        buttonPanel.add(submitDisciplinaryNoteButton);
        gbc.gridx = 0; gbc.gridy = 4;
        gbc.gridwidth = 2;
        bottomPanel.add(buttonPanel, gbc);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setLocationRelativeTo(null); // Center the window
    }
    /**
     * Load the details of the register selected from the previous "ViewingElCoregistri" screen.
     */
    private void loadRegisterDetails() {
        updateDateComboBox(selectedRegisterName);
        updateStudentComboBox();
        displayAttendanceForSelectedDate();
    }
    /**
     * Update the date combo box with dates available for the given register.
     * @param registerName name of the register.
     */
    private void updateDateComboBox(String registerName) {
        dateComboBox.removeAllItems();
        ClassRegister reg = registers.get(registerName);
        if (reg != null) {
            List<LocalDate> dates = new ArrayList<>(reg.getAttendanceByDate().keySet());
            Collections.sort(dates, Collections.reverseOrder()); // Most recent first
            DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyy-MM-dd");
            for (LocalDate date : dates) {
                dateComboBox.addItem(date.format(formatter));
            }
            // Set today as default if exists
            String todayStr = LocalDate.now().format(DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            for (int i = 0; i < dateComboBox.getItemCount(); i++) {
                if (dateComboBox.getItemAt(i).equals(todayStr)) {
                    dateComboBox.setSelectedIndex(i);
                    break;
                }
            }
        }
    }
    /**
     * Update the student combo box based on currently selected date.
     */
    private void updateStudentComboBox() {
        studentComboBox.removeAllItems();
        String dateStr = (String) dateComboBox.getSelectedItem();
        if (dateStr == null) return;
        ClassRegister reg = registers.get(selectedRegisterName);
        if (reg == null) return;
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<StudentAttendance> attendanceList = reg.getAttendanceByDate().get(date);
        if (attendanceList != null) {
            for (StudentAttendance sa : attendanceList) {
                studentComboBox.addItem(sa.getStudentName());
            }
            if (studentComboBox.getItemCount() > 0) {
                studentComboBox.setSelectedIndex(0);
            }
        }
    }
    /**
     * Display attendance records for the selected date in the details text area.
     */
    private void displayAttendanceForSelectedDate() {
        String dateStr = (String) dateComboBox.getSelectedItem();
        if (dateStr == null) return;
        ClassRegister reg = registers.get(selectedRegisterName);
        if (reg == null) {
            detailsTextArea.setText("No register found: " + selectedRegisterName);
            return;
        }
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        List<StudentAttendance> attendanceList = reg.getAttendanceByDate().get(date);
        StringBuilder sb = new StringBuilder();
        sb.append("Register: ").append(selectedRegisterName).append("\n");
        sb.append("Date: ").append(dateStr).append("\n");
        sb.append("=".repeat(50)).append("\n");
        sb.append(String.format("%-20s %-10s %-30s %-30s\n", 
            "Student", "Status", "Justification", "Disciplinary Note"));
        sb.append("=".repeat(50)).append("\n");
        if (attendanceList != null) {
            for (StudentAttendance sa : attendanceList) {
                sb.append(String.format("%-20s %-10s %-30s %-30s\n",
                    sa.getStudentName(),
                    sa.getStatus(),
                    sa.getJustification(),
                    sa.getDisciplinaryNote()));
            }
        } else {
            sb.append("No attendance records for this date.\n");
        }
        detailsTextArea.setText(sb.toString());
    }
    /**
     * Handle submission of justification for a student.
     * Now properly updates the data model.
     */
    private void submitJustification() {
        String studentName = (String) studentComboBox.getSelectedItem();
        String justification = justificationField.getText().trim();
        String dateStr = (String) dateComboBox.getSelectedItem();
        if (studentName == null || studentName.isEmpty() || dateStr == null) {
            JOptionPane.showMessageDialog(this, "Please select a student and ensure a date is selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ClassRegister reg = registers.get(selectedRegisterName);
        if (reg == null) {
            JOptionPane.showMessageDialog(this, "Register not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<StudentAttendance> attendanceList = reg.getAttendanceByDate().get(date);
        if (attendanceList == null) {
            JOptionPane.showMessageDialog(this, "No attendance records for selected date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean found = false;
        for (StudentAttendance sa : attendanceList) {
            if (sa.getStudentName().equals(studentName)) {
                sa.setJustification(justification);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Student not found in the selected date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this, 
            "Justification updated for " + studentName + ": " + justification,
            "Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
        displayAttendanceForSelectedDate(); // Refresh display
    }
    /**
     * Handle submission of disciplinary note for a student.
     * Now properly updates the data model.
     */
    private void submitDisciplinaryNote() {
        String studentName = (String) studentComboBox.getSelectedItem();
        String note = disciplinaryNoteField.getText().trim();
        String dateStr = (String) dateComboBox.getSelectedItem();
        if (studentName == null || studentName.isEmpty() || dateStr == null) {
            JOptionPane.showMessageDialog(this, "Please select a student and ensure a date is selected.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        LocalDate date = LocalDate.parse(dateStr, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
        ClassRegister reg = registers.get(selectedRegisterName);
        if (reg == null) {
            JOptionPane.showMessageDialog(this, "Register not found.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<StudentAttendance> attendanceList = reg.getAttendanceByDate().get(date);
        if (attendanceList == null) {
            JOptionPane.showMessageDialog(this, "No attendance records for selected date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        boolean found = false;
        for (StudentAttendance sa : attendanceList) {
            if (sa.getStudentName().equals(studentName)) {
                sa.setDisciplinaryNote(note);
                found = true;
                break;
            }
        }
        if (!found) {
            JOptionPane.showMessageDialog(this, "Student not found in the selected date.", "Error", JOptionPane.ERROR_MESSAGE);
            return;
        }
        JOptionPane.showMessageDialog(this,
            "Disciplinary note updated for " + studentName + ": " + note,
            "Success", JOptionPane.INFORMATION_MESSAGE);
        clearForm();
        displayAttendanceForSelectedDate(); // Refresh display
    }
    /**
     * Clear the input form fields.
     */
    private void clearForm() {
        studentComboBox.setSelectedIndex(0);
        statusComboBox.setSelectedIndex(0);
        justificationField.setText("");
        disciplinaryNoteField.setText("");
    }
    /**
     * Logout the administrator and simulate interruption of SMOS server connection.
     * This method satisfies the postcondition about interrupted server connection.
     */
    private void logout() {
        // Simulate server connection interruption with more detail
        System.out.println("Simulating SMOS server connection interruption...");
        // In a real application, you would close network connections here.
        int choice = JOptionPane.showConfirmDialog(this,
            "Logout will interrupt the connection to the SMOS server. Proceed?",
            "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (choice == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Logged out successfully. SMOS server connection terminated.");
            System.exit(0);
        } else {
            System.out.println("Logout cancelled. SMOS server connection remains active.");
        }
    }
    /**
     * Main method to launch the application with simulation of login and previous use case.
     * Simulates administrator login and selection from "ViewingElCoregistri".
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                // Simulate administrator login
                boolean isLoggedIn = simulateLogin();
                if (!isLoggedIn) {
                    JOptionPane.showMessageDialog(null, "Login failed. Exiting.");
                    System.exit(0);
                }
                // Simulate the prerequisite: administrator has viewed registers and clicked "Details"
                String[] registerOptions = {"Class A", "Class B"};
                String selectedRegister = (String) JOptionPane.showInputDialog(
                    null,
                    "Select a register to view details (simulating 'ViewingElCoregistri' use case):",
                    "Register Selection",
                    JOptionPane.QUESTION_MESSAGE,
                    null,
                    registerOptions,
                    registerOptions[0]);
                if (selectedRegister != null) {
                    new ViewDetailsSingleRegister(selectedRegister).setVisible(true);
                } else {
                    JOptionPane.showMessageDialog(null, "No register selected. Exiting.");
                    System.exit(0);
                }
            }
        });
    }
    /**
     * Simulates administrator login.
     * @return true if login is successful, false otherwise.
     */
    private static boolean simulateLogin() {
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Username:"));
        JTextField usernameField = new JTextField(10);
        panel.add(usernameField);
        panel.add(new JLabel("Password:"));
        JPasswordField passwordField = new JPasswordField(10);
        panel.add(passwordField);
        int result = JOptionPane.showConfirmDialog(null, panel, "Administrator Login",
                JOptionPane.OK_CANCEL_OPTION, JOptionPane.PLAIN_MESSAGE);
        if (result == JOptionPane.OK_OPTION) {
            String username = usernameField.getText().trim();
            char[] password = passwordField.getPassword();
            // Simple hardcoded check for simulation purposes
            boolean valid = "admin".equals(username) && "admin123".equals(new String(password));
            if (valid) {
                JOptionPane.showMessageDialog(null, "Login successful!");
                return true;
            } else {
                JOptionPane.showMessageDialog(null, "Invalid username or password.");
                return false;
            }
        }
        return false;
    }
}
/**
 * Represents a class register with attendance records organized by date.
 */
class ClassRegister {
    private String className;
    private Map<LocalDate, List<StudentAttendance>> attendanceByDate;
    public ClassRegister(String className) {
        this.className = className;
        this.attendanceByDate = new HashMap<>();
    }
    public String getClassName() {
        return className;
    }
    public Map<LocalDate, List<StudentAttendance>> getAttendanceByDate() {
        return attendanceByDate;
    }
    public void addAttendance(LocalDate date, List<StudentAttendance> attendance) {
        attendanceByDate.put(date, attendance);
    }
}
/**
 * Represents the attendance record of a single student on a specific date.
 */
class StudentAttendance {
    private String studentName;
    private String status; // present, absent, late
    private String justification;
    private String disciplinaryNote;
    public StudentAttendance(String studentName, String status, String justification, String disciplinaryNote) {
        this.studentName = studentName;
        this.status = status;
        this.justification = justification;
        this.disciplinaryNote = disciplinaryNote;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getStatus() {
        return status;
    }
    public String getJustification() {
        return justification;
    }
    public String getDisciplinaryNote() {
        return disciplinaryNote;
    }
    public void setJustification(String justification) {
        this.justification = justification;
    }
    public void setDisciplinaryNote(String disciplinaryNote) {
        this.disciplinaryNote = disciplinaryNote;
    }
}