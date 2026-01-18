import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.ArrayList;
/**
 * Main GUI for the attendance entry system.
 * Allows administrator to select date, view students, mark attendance,
 * save data, and send notifications.
 */
public class AttendanceGUI extends JFrame {
    private JComboBox<String> dateComboBox;
    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private JButton saveButton;
    private JLabel statusLabel;
    private List<Student> studentList;
    private HashMap<String, String> attendanceMap; // date -> serialized attendance data (simplified)
    private EmailSender emailSender;
    public AttendanceGUI() {
        super("Enter Absences - Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 500);
        setLocationRelativeTo(null); // center on screen
        initializeStudents(); // populate with sample data
        attendanceMap = new HashMap<>();
        emailSender = new EmailSender(); // Initialize email sender
        // Create UI components
        createUI();
        // Load today's date as default
        loadDate(new Date());
    }
    private void initializeStudents() {
        studentList = new ArrayList<>();
        // Sample data - in real app, load from database
        studentList.add(new Student("S001", "Alice Johnson", "alice.parent@example.com"));
        studentList.add(new Student("S002", "Bob Smith", "bob.parent@example.com"));
        studentList.add(new Student("S003", "Charlie Brown", "charlie.parent@example.com"));
        studentList.add(new Student("S004", "Diana Prince", "diana.parent@example.com"));
    }
    private void createUI() {
        setLayout(new BorderLayout());
        // Top panel for date selection
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        topPanel.add(new JLabel("Select Date: "));
        dateComboBox = new JComboBox<>(new String[] {
            "Today",
            "Yesterday",
            "2023-10-01",
            "2023-10-02"
        });
        dateComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                onDateSelected();
            }
        });
        topPanel.add(dateComboBox);
        add(topPanel, BorderLayout.NORTH);
        // Center panel with table for attendance
        String[] columns = {"Student ID", "Student Name", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public Class<?> getColumnClass(int column) {
                if (column == 2) return String.class;
                return Object.class;
            }
            @Override
            public boolean isCellEditable(int row, int column) {
                // Only the status column is editable
                return column == 2;
            }
        };
        attendanceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel with buttons and status
        JPanel bottomPanel = new JPanel(new BorderLayout());
        saveButton = new JButton("Save Attendance");
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                saveAttendance();
            }
        });
        bottomPanel.add(saveButton, BorderLayout.WEST);
        statusLabel = new JLabel("Ready.");
        bottomPanel.add(statusLabel, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    private void onDateSelected() {
        String selected = (String) dateComboBox.getSelectedItem();
        Date date = new Date(); // default to today
        if (selected.equals("Yesterday")) {
            date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
        } else if (selected.startsWith("2023")) {
            try {
                SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
                date = sdf.parse(selected);
            } catch (Exception e) {
                e.printStackTrace();
            }
        }
        loadDate(date);
    }
    private void loadDate(Date date) {
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr = sdf.format(date);
        statusLabel.setText("Loading attendance for " + dateStr + "...");
        // Clear table
        tableModel.setRowCount(0);
        // Populate with student list
        for (Student s : studentList) {
            // Default status is "Present"
            tableModel.addRow(new Object[]{s.getId(), s.getName(), "Present"});
        }
        // If we have saved data for this date, load it
        if (attendanceMap.containsKey(dateStr)) {
            // Parse saved data and set rows accordingly
            String savedData = attendanceMap.get(dateStr);
            String[] records = savedData.split(";");
            for (String record : records) {
                if (record.isEmpty()) continue;
                String[] parts = record.split(":");
                if (parts.length == 2) {
                    String studentId = parts[0];
                    String status = parts[1];
                    // Update the row for this student
                    for (int i = 0; i < tableModel.getRowCount(); i++) {
                        if (tableModel.getValueAt(i, 0).equals(studentId)) {
                            tableModel.setValueAt(status, i, 2);
                            break;
                        }
                    }
                }
            }
            statusLabel.setText("Loaded saved data for " + dateStr);
        } else {
            statusLabel.setText("New entry for " + dateStr);
        }
    }
    private void saveAttendance() {
        String selectedDate = (String) dateComboBox.getSelectedItem();
        SimpleDateFormat sdf = new SimpleDateFormat("yyyy-MM-dd");
        String dateStr;
        if (selectedDate.equals("Today") || selectedDate.equals("Yesterday")) {
            Date date = new Date();
            if (selectedDate.equals("Yesterday")) {
                date = new Date(System.currentTimeMillis() - 24 * 60 * 60 * 1000);
            }
            dateStr = sdf.format(date);
        } else {
            dateStr = selectedDate;
        }
        // Collect attendance data from table
        StringBuilder data = new StringBuilder();
        List<Student> absentStudents = new ArrayList<>();
        for (int i = 0; i < tableModel.getRowCount(); i++) {
            String id = (String) tableModel.getValueAt(i, 0);
            String name = (String) tableModel.getValueAt(i, 1);
            String status = (String) tableModel.getValueAt(i, 2);
            data.append(id).append(":").append(status).append(";");
            if ("Absent".equals(status)) {
                // Find student and add to absent list
                for (Student s : studentList) {
                    if (s.getId().equals(id)) {
                        absentStudents.add(s);
                        break;
                    }
                }
            }
        }
        attendanceMap.put(dateStr, data.toString());
        // Simulate sending data to server
        boolean serverSuccess = sendToServer(dateStr, data.toString());
        if (!serverSuccess) {
            statusLabel.setText("Error: SMOS server connection interrupted.");
            JOptionPane.showMessageDialog(this,
                "Connection to the server was interrupted. Attendance saved locally but notifications may not have been sent.",
                "Server Error",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Send email notifications for absent students
        for (Student s : absentStudents) {
            sendEmailNotification(s, dateStr);
        }
        statusLabel.setText("Attendance saved for " + dateStr + ". Notifications sent.");
        JOptionPane.showMessageDialog(this, "Attendance data saved and notifications sent.", "Success", JOptionPane.INFORMATION_MESSAGE);
    }
    private boolean sendToServer(String date, String data) {
        // Simulate server communication
        // In a real application, this would be an HTTP request or database call
        try {
            Thread.sleep(500); // Simulate network delay
            // Simulate random server failure (for demonstration)
            if (Math.random() < 0.1) { // 10% chance of failure
                return false;
            }
            System.out.println("Data sent to server for " + date + ": " + data);
            return true;
        } catch (InterruptedException e) {
            return false;
        }
    }
    private void sendEmailNotification(Student student, String date) {
        // Try to send real email, fallback to simulation if fails
        boolean emailSent = emailSender.sendAbsenceNotification(
            student.getParentEmail(), 
            student.getName(), 
            date
        );
        // Fallback to simulation if real email fails
        if (!emailSent) {
            emailSender.sendSimulatedNotification(
                student.getParentEmail(), 
                student.getName(), 
                date
            );
        }
    }
}