/**
 * GUI class that displays the registry interface with date selection,
 * attendance table, and controls for managing records.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.*;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class RegistryGUI {
    private RegistryController controller;
    private JPanel mainPanel;
    private JComboBox<String> dateComboBox; // Using formatted strings for display
    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private JButton loadButton;
    private JButton updateButton;
    private JButton interruptButton;
    private JButton reconnectButton;
    // Column names for the table
    private final String[] COLUMN_NAMES = {"Student ID", "Student Name", "Status", "Justification", "Disciplinary Notes"};
    private final String[] STATUS_OPTIONS = {"Present", "Absent", "Delayed"};
    // Date formatters
    private SimpleDateFormat dateDisplayFormat = new SimpleDateFormat("MMM dd, yyyy");
    private SimpleDateFormat dateStorageFormat = new SimpleDateFormat("yyyy-MM-dd");
    private java.util.List<Date> availableDates;
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
        scrollPane.setPreferredSize(new Dimension(750, 400));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for update button
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        updateButton = new JButton("Update Selected Record");
        bottomPanel.add(updateButton);
        // Add a status label
        JLabel statusLabel = new JLabel("SMOS: Connected");
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
                // In a real application, this would attempt to reconnect to SMOS
                JOptionPane.showMessageDialog(mainPanel, 
                    "Reconnection to SMOS server simulated.\nData operations are now enabled.", 
                    "Reconnection Successful", 
                    JOptionPane.INFORMATION_MESSAGE);
                reconnectButton.setEnabled(false);
                interruptButton.setEnabled(true);
                statusLabel.setText("SMOS: Connected");
                statusLabel.setForeground(Color.GREEN);
            }
        });
        // Load the first date by default
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
                record.getStudent().getName().split(" ")[0], // First name only for display
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
        // Create a simple student object for the controller
        Student student = new Student(studentId, "Unknown"); // Name not needed for update
        // Update through controller
        controller.updateRecord(selectedDate, student, status, justification, disciplinaryNotes);
        JOptionPane.showMessageDialog(mainPanel, 
            "Attendance record updated successfully.", 
            "Update Successful", 
            JOptionPane.INFORMATION_MESSAGE);
    }
}