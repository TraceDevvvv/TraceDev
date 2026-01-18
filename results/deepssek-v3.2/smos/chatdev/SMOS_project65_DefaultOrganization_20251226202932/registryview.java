/**
 * View class for displaying the registry GUI.
 * This is a single-file application that implements the "ViewRegister" use case.
 * It displays class registry information organized by date with student attendance status,
 * justifications, and disciplinary notes.
 * The program follows MVC architecture and simulates SMOS server connection.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.*;
public class RegistryView {
    /**
     * Main method - entry point for the application
     */
    public static void main(String[] args) {
        // Schedule GUI creation on the Event Dispatch Thread
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                try {
                    // Set system look and feel for native appearance
                    UIManager.setLookAndFeel(UIManager.getSystemLookAndFeelClassName());
                } catch (Exception e) {
                    e.printStackTrace();
                }
                // Create sample data for demonstration
                ArrayList<Student> students = new ArrayList<>();
                students.add(new Student("ST001", "Alice Johnson"));
                students.add(new Student("ST002", "Bob Smith"));
                students.add(new Student("ST003", "Carol Williams"));
                students.add(new Student("ST004", "David Brown"));
                students.add(new Student("ST005", "Eva Davis"));
                // Create sample dates (today and past 2 days)
                ArrayList<Date> dates = new ArrayList<>();
                Calendar cal = Calendar.getInstance();
                dates.add(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, -1);
                dates.add(cal.getTime());
                cal.add(Calendar.DAY_OF_MONTH, -1);
                dates.add(cal.getTime());
                // Initialize MVC components
                ClassRegistryModel model = new ClassRegistryModel(students, dates);
                RegistryController controller = new RegistryController(model);
                RegistryGUI gui = new RegistryGUI(controller);
                // Set the view in controller
                controller.setView(gui);
                // Create and set up the main application window
                JFrame frame = new JFrame("Class Registry System - Director View");
                frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
                frame.setContentPane(gui.getMainPanel());
                frame.setPreferredSize(new Dimension(900, 650));
                frame.pack();
                frame.setLocationRelativeTo(null); // Center on screen
                frame.setVisible(true);
            }
        });
    }
}
/**
 * Represents a student with identification information.
 */
class Student {
    private String id;
    private String name;
    public Student(String id, String name) {
        this.id = id;
        this.name = name;
    }
    public String getId() { return id; }
    public String getName() { return name; }
    @Override
    public String toString() {
        return name + " (" + id + ")";
    }
    @Override
    public boolean equals(Object obj) {
        if (this == obj) return true;
        if (obj == null || getClass() != obj.getClass()) return false;
        Student student = (Student) obj;
        return id.equals(student.id);
    }
    @Override
    public int hashCode() {
        return id.hashCode();
    }
}
/**
 * Represents an attendance record for a specific student on a specific date.
 * Includes status, justification, and disciplinary notes.
 */
class AttendanceRecord {
    private Student student;
    private Date date;
    private String status; // "Present", "Absent", or "Delayed"
    private String justification;
    private String disciplinaryNotes;
    public AttendanceRecord(Student student, Date date) {
        this.student = student;
        this.date = date;
        this.status = "Present"; // Default status
        this.justification = "";
        this.disciplinaryNotes = "";
    }
    // Getters and setters
    public Student getStudent() { return student; }
    public Date getDate() { return date; }
    public String getStatus() { return status; }
    public void setStatus(String status) { 
        if (status.equals("Present") || status.equals("Absent") || status.equals("Delayed")) {
            this.status = status; 
        }
    }
    public String getJustification() { return justification; }
    public void setJustification(String justification) { this.justification = justification; }
    public String getDisciplinaryNotes() { return disciplinaryNotes; }
    public void setDisciplinaryNotes(String disciplinaryNotes) { this.disciplinaryNotes = disciplinaryNotes; }
    @Override
    public String toString() {
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        return student + " on " + dateFormat.format(date) + ": " + status;
    }
}
/**
 * Model class that manages the data for the class registry system.
 * Maintains lists of students, dates, and attendance records.
 */
class ClassRegistryModel {
    private List<Student> students;
    private List<Date> dates;
    private Map<Date, List<AttendanceRecord>> attendanceByDate;
    private boolean smosConnected;
    public ClassRegistryModel(List<Student> students, List<Date> dates) {
        this.students = new ArrayList<>(students);
        this.dates = new ArrayList<>(dates);
        this.attendanceByDate = new HashMap<>();
        this.smosConnected = true;
        // Initialize attendance records for all dates and students
        initializeAttendanceRecords();
    }
    /**
     * Creates attendance records for all students on all dates
     */
    private void initializeAttendanceRecords() {
        for (Date date : dates) {
            List<AttendanceRecord> records = new ArrayList<>();
            for (Student student : students) {
                AttendanceRecord record = new AttendanceRecord(student, date);
                // Add some sample data for demonstration
                Random rand = new Random(date.getTime() + student.getId().hashCode());
                int statusChoice = rand.nextInt(10);
                if (statusChoice < 7) {
                    record.setStatus("Present");
                } else if (statusChoice < 9) {
                    record.setStatus("Absent");
                    record.setJustification("Sick");
                } else {
                    record.setStatus("Delayed");
                    record.setJustification("Traffic");
                }
                if (rand.nextInt(10) < 2) {
                    record.setDisciplinaryNotes("Needs improvement in participation");
                }
                records.add(record);
            }
            attendanceByDate.put(date, records);
        }
    }
    public List<Date> getDates() {
        return new ArrayList<>(dates);
    }
    public List<AttendanceRecord> getAttendanceForDate(Date date) {
        return attendanceByDate.getOrDefault(date, new ArrayList<>());
    }
    public void updateAttendanceRecord(Date date, Student student, 
                                       String status, String justification, 
                                       String disciplinaryNotes) {
        List<AttendanceRecord> records = attendanceByDate.get(date);
        if (records != null) {
            for (AttendanceRecord record : records) {
                if (record.getStudent().equals(student)) {
                    record.setStatus(status);
                    record.setJustification(justification);
                    record.setDisciplinaryNotes(disciplinaryNotes);
                    break;
                }
            }
        }
    }
    // SMOS server connection methods
    public boolean isSmosConnected() { return smosConnected; }
    public void simulateConnectionInterruption() {
        smosConnected = false;
        // In a real application, this would trigger error handling and reconnection logic
    }
    public void reconnectToSmos() {
        smosConnected = true;
    }
    public Student findStudentById(String studentId) {
        for (Student student : students) {
            if (student.getId().equals(studentId)) {
                return student;
            }
        }
        return null;
    }
}
/**
 * Controller class that manages interactions between the view and model.
 * Handles user actions and updates the model accordingly.
 */
class RegistryController {
    private ClassRegistryModel model;
    private RegistryGUI view;
    public RegistryController(ClassRegistryModel model) {
        this.model = model;
    }
    public void setView(RegistryGUI view) {
        this.view = view;
        // Initialize view with data from model
        view.setDates(model.getDates());
        if (!model.getDates().isEmpty()) {
            loadDate(model.getDates().get(0));
        }
    }
    /**
     * Loads attendance data for a specific date
     */
    public void loadDate(Date date) {
        if (!model.isSmosConnected()) {
            JOptionPane.showMessageDialog(null, 
                "Cannot load data: Connection to SMOS server interrupted.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        List<AttendanceRecord> records = model.getAttendanceForDate(date);
        if (view != null) {
            view.displayAttendanceRecords(records);
        }
    }
    /**
     * Updates an attendance record
     */
    public void updateRecord(Date date, Student student, 
                            String status, String justification, 
                            String disciplinaryNotes) {
        if (!model.isSmosConnected()) {
            JOptionPane.showMessageDialog(null, 
                "Cannot update: Connection to SMOS server interrupted.", 
                "Connection Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        model.updateAttendanceRecord(date, student, status, justification, disciplinaryNotes);
        loadDate(date); // Refresh the view
    }
    /**
     * Simulates SMOS server connection interruption
     */
    public void simulateConnectionInterruption() {
        model.simulateConnectionInterruption();
        JOptionPane.showMessageDialog(null, 
            "Connection to SMOS server has been interrupted.\n" +
            "All data operations are now disabled until reconnection.", 
            "SMOS Connection Interrupted", 
            JOptionPane.WARNING_MESSAGE);
        if (view != null) {
            view.disableDataOperations();
        }
    }
    /**
     * Simulates reconnection to SMOS server
     */
    public void reconnectToSmos() {
        model.reconnectToSmos();
        if (view != null) {
            view.enableDataOperations();
        }
    }
    public Student findStudentById(String studentId) {
        return model.findStudentById(studentId);
    }
}
/**
 * GUI class that displays the registry interface with date selection,
 * attendance table, and controls for managing records.
 */
class RegistryGUI {
    private RegistryController controller;
    private JPanel mainPanel;
    private JComboBox<String> dateComboBox; // Using formatted strings for display
    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private JButton loadButton;
    private JButton updateButton;
    private JButton interruptButton;
    private JButton reconnectButton;
    private JLabel statusLabel;
    // Column names for the table
    private final String[] COLUMN_NAMES = {"Student ID", "Student Name", "Status", "Justification", "Disciplinary Notes"};
    private final String[] STATUS_OPTIONS = {"Present", "Absent", "Delayed"};
    // Date formatters
    private SimpleDateFormat dateDisplayFormat = new SimpleDateFormat("MMM dd, yyyy");
    private List<Date> availableDates;
    public RegistryGUI(RegistryController controller) {
        this.controller = controller;
        createUI();
    }
    /**
     * Creates and arranges all GUI components.
     */
    private void createUI() {
        mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Top panel for date selection and controls
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(new JLabel("Select Date:"));
        dateComboBox = new JComboBox<>();
        dateComboBox.setPreferredSize(new Dimension(150, 25));
        topPanel.add(dateComboBox);
        loadButton = new JButton("Load Registry");
        topPanel.add(loadButton);
        topPanel.add(Box.createHorizontalStrut(20)); // Spacer
        interruptButton = new JButton("Simulate SMOS Interruption");
        interruptButton.setForeground(Color.RED);
        topPanel.add(interruptButton);
        reconnectButton = new JButton("Reconnect to SMOS");
        reconnectButton.setEnabled(false);
        topPanel.add(reconnectButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center panel for the attendance table
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make only Status, Justification, and Disciplinary Notes editable
                return column >= 2;
            }
        };
        attendanceTable = new JTable(tableModel);
        attendanceTable.setRowHeight(25);
        attendanceTable.getColumnModel().getColumn(2).setCellEditor(new DefaultCellEditor(new JComboBox<>(STATUS_OPTIONS)));
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        scrollPane.setPreferredSize(new Dimension(850, 450));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for update button and status
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        updateButton = new JButton("Update Selected Record");
        bottomPanel.add(updateButton);
        // Add a status label
        statusLabel = new JLabel("SMOS: Connected");
        statusLabel.setForeground(Color.GREEN);
        bottomPanel.add(statusLabel);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        // Add action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadSelectedDate();
            }
        });
        updateButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateSelectedRecord();
            }
        });
        interruptButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.simulateConnectionInterruption();
                interruptButton.setEnabled(false);
                reconnectButton.setEnabled(true);
                statusLabel.setText("SMOS: Disconnected");
                statusLabel.setForeground(Color.RED);
            }
        });
        reconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                controller.reconnectToSmos();
                JOptionPane.showMessageDialog(mainPanel, 
                    "Reconnection to SMOS server successful.\nData operations are now enabled.", 
                    "Reconnection Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
                reconnectButton.setEnabled(false);
                interruptButton.setEnabled(true);
                statusLabel.setText("SMOS: Connected");
                statusLabel.setForeground(Color.GREEN);
            }
        });
        // Load data when date selection changes
        dateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (dateComboBox.getSelectedIndex() >= 0 && availableDates != null) {
                    loadSelectedDate();
                }
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    /**
     * Populates the date combo box with available dates.
     * @param dates List of available dates
     */
    public void setDates(List<Date> dates) {
        dateComboBox.removeAllItems();
        availableDates = dates;
        for (Date date : dates) {
            dateComboBox.addItem(dateDisplayFormat.format(date));
        }
        if (dates.size() > 0) {
            dateComboBox.setSelectedIndex(0);
        }
    }
    /**
     * Displays attendance records in the table for a specific date.
     * @param records List of attendance records to display
     */
    public void displayAttendanceRecords(List<AttendanceRecord> records) {
        tableModel.setRowCount(0); // Clear existing rows
        for (AttendanceRecord record : records) {
            Object[] row = {
                record.getStudent().getId(),
                record.getStudent().getName(),
                record.getStatus(),
                record.getJustification(),
                record.getDisciplinaryNotes()
            };
            tableModel.addRow(row);
        }
    }
    /**
     * Disables data operations when SMOS connection is interrupted.
     */
    public void disableDataOperations() {
        loadButton.setEnabled(false);
        updateButton.setEnabled(false);
        dateComboBox.setEnabled(false);
    }
    /**
     * Enables data operations when SMOS connection is restored.
     */
    public void enableDataOperations() {
        loadButton.setEnabled(true);
        updateButton.setEnabled(true);
        dateComboBox.setEnabled(true);
    }
    /**
     * Loads the attendance data for the currently selected date.
     */
    private void loadSelectedDate() {
        int selectedIndex = dateComboBox.getSelectedIndex();
        if (selectedIndex >= 0 && availableDates != null) {
            Date selectedDate = availableDates.get(selectedIndex);
            controller.loadDate(selectedDate);
        }
    }
    /**
     * Updates the selected record with values from the table.
     */
    private void updateSelectedRecord() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(mainPanel, 
                "Please select a student record to update.", 
                "No Selection", 
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        int selectedDateIndex = dateComboBox.getSelectedIndex();
        if (selectedDateIndex < 0 || availableDates == null) {
            return;
        }
        Date selectedDate = availableDates.get(selectedDateIndex);
        // Get values from the table
        String studentId = (String) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 2);
        String justification = (String) tableModel.getValueAt(selectedRow, 3);
        String disciplinaryNotes = (String) tableModel.getValueAt(selectedRow, 4);
        // Find the student object
        Student student = controller.findStudentById(studentId);
        if (student == null) {
            JOptionPane.showMessageDialog(mainPanel, 
                "Student not found.", 
                "Error", 
                JOptionPane.ERROR_MESSAGE);
            return;
        }
        // Update through controller
        controller.updateRecord(selectedDate, student, status, justification, disciplinaryNotes);
        JOptionPane.showMessageDialog(mainPanel, 
            "Attendance record updated successfully for " + student.getName() + ".", 
            "Update Successful", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}