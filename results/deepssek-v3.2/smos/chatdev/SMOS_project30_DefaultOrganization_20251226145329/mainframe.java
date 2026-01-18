/**
 * MainFrame.java
 *
 * This class represents the main GUI frame of the application.
 * It includes date selection, form for entering student delay data,
 * and display area for logs. It handles the event sequence as per the use case.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.ArrayList;
import java.util.Date;
import java.util.List;
public class MainFrame extends JFrame {
    private JTextField dateField;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JTextArea logArea;
    private List<StudentDelay> delayRecords;
    private MockServer server;
    // Constructor sets up the GUI components and initializes data
    public MainFrame(String title) {
        super(title);
        delayRecords = new ArrayList<>();
        server = new MockServer();
        initializeUI();
    }
    // Initialize all UI components and layout
    private void initializeUI() {
        setLayout(new BorderLayout());
        // North panel for date selection and info
        JPanel northPanel = new JPanel(new FlowLayout());
        northPanel.add(new JLabel("Select Date:"));
        dateField = new JTextField(15);
        dateField.setText(new SimpleDateFormat("yyyy-MM-dd").format(new Date()));
        JButton updateDateButton = new JButton("Update Display");
        updateDateButton.addActionListener(new UpdateDateListener());
        northPanel.add(dateField);
        northPanel.add(updateDateButton);
        add(northPanel, BorderLayout.NORTH);
        // Center panel for data entry form and table
        JPanel centerPanel = new JPanel(new BorderLayout());
        // Form panel for entering student delay data
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 5, 5));
        JTextField studentIdField = new JTextField();
        JTextField studentNameField = new JTextField();
        JTextField delayReasonField = new JTextField();
        formPanel.add(new JLabel("Student ID:"));
        formPanel.add(studentIdField);
        formPanel.add(new JLabel("Student Name:"));
        formPanel.add(studentNameField);
        formPanel.add(new JLabel("Delay Reason:"));
        formPanel.add(delayReasonField);
        JButton saveButton = new JButton("Save");
        formPanel.add(new JLabel()); // placeholder
        formPanel.add(saveButton);
        centerPanel.add(formPanel, BorderLayout.NORTH);
        // Table to display entered data
        String[] columns = {"Student ID", "Student Name", "Delay Reason", "Date"};
        tableModel = new DefaultTableModel(columns, 0);
        dataTable = new JTable(tableModel);
        centerPanel.add(new JScrollPane(dataTable), BorderLayout.CENTER);
        add(centerPanel, BorderLayout.CENTER);
        // South panel for logs and status
        JPanel southPanel = new JPanel(new BorderLayout());
        logArea = new JTextArea(5, 50);
        logArea.setEditable(false);
        southPanel.add(new JLabel("Logs:"), BorderLayout.NORTH);
        southPanel.add(new JScrollPane(logArea), BorderLayout.CENTER);
        add(southPanel, BorderLayout.SOUTH);
        // Save button action listener to handle data saving and server communication
        saveButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                String studentId = studentIdField.getText().trim();
                String studentName = studentNameField.getText().trim();
                String delayReason = delayReasonField.getText().trim();
                String date = dateField.getText().trim();
                // Validate input fields
                if (studentId.isEmpty() || studentName.isEmpty() || delayReason.isEmpty()) {
                    JOptionPane.showMessageDialog(MainFrame.this, 
                        "All fields must be filled!", "Input Error", JOptionPane.ERROR_MESSAGE);
                    return;
                }
                // Create delay record and add to list
                StudentDelay record = new StudentDelay(studentId, studentName, delayReason, date);
                delayRecords.add(record);
                tableModel.addRow(new Object[]{studentId, studentName, delayReason, date});
                // Attempt to send data to server
                boolean success = server.sendData(record);
                if (success) {
                    logArea.append("Data saved and sent to server for student: " + studentName + "\n");
                    // Simulate notification to parents
                    server.sendNotificationToParents(record);
                    logArea.append("Notification sent to parents of student: " + studentName + "\n");
                } else {
                    logArea.append("Error: Server connection failed for student: " + studentName + "\n");
                }
                // Clear form fields for next entry
                studentIdField.setText("");
                studentNameField.setText("");
                delayReasonField.setText("");
            }
        });
    }
    // Listener to update display when date is changed
    private class UpdateDateListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            String selectedDate = dateField.getText().trim();
            logArea.append("Display updated for date: " + selectedDate + "\n");
            // In a real application, you might fetch and display data for the selected date
        }
    }
}