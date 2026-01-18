/**
 * View class for displaying the registry GUI.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
import java.util.Date;
import java.util.List;
public class RegistryView {
    private RegistryController controller;
    private JPanel mainPanel;
    private JComboBox<Date> dateComboBox;
    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private JButton loadButton;
    private JButton updateButton;
    private JButton interruptButton;
    // Column names for the table
    private final String[] COLUMN_NAMES = {"Student", "Status", "Justification", "Disciplinary Notes"};
    public RegistryView(RegistryController controller) {
        this.controller = controller;
        createUI();
    }
    private void createUI() {
        mainPanel = new JPanel(new BorderLayout());
        // Top panel for date selection
        JPanel topPanel = new JPanel(new FlowLayout());
        topPanel.add(new JLabel("Select Date:"));
        dateComboBox = new JComboBox<>();
        topPanel.add(dateComboBox);
        loadButton = new JButton("Load Registry");
        topPanel.add(loadButton);
        interruptButton = new JButton("Simulate SMOS Interruption");
        topPanel.add(interruptButton);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // Center panel for the attendance table
        tableModel = new DefaultTableModel(COLUMN_NAMES, 0);
        attendanceTable = new JTable(tableModel);
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for update button
        JPanel bottomPanel = new JPanel(new FlowLayout());
        updateButton = new JButton("Update Selected Record");
        bottomPanel.add(updateButton);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        // Add action listeners
        loadButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                Date selectedDate = (Date) dateComboBox.getSelectedItem();
                if (selectedDate != null) {
                    controller.loadDate(selectedDate);
                }
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
            }
        });
    }
    public JPanel getMainPanel() {
        return mainPanel;
    }
    // Populate the date combo box with available dates
    public void setDates(List<Date> dates) {
        dateComboBox.removeAllItems();
        for (Date date : dates) {
            dateComboBox.addItem(date);
        }
        // Select the first date by default
        if (dates.size() > 0) {
            dateComboBox.setSelectedIndex(0);
        }
    }
    // Display attendance records in the table
    public void displayAttendanceRecords(List<AttendanceRecord> records) {
        // Clear existing rows
        tableModel.setRowCount(0);
        // Add new rows
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd");
        for (AttendanceRecord record : records) {
            Object[] row = {
                    record.getStudent().toString(),
                    record.getStatus(),
                    record.getJustification(),
                    record.getDisciplinaryNotes()
            };
            tableModel.addRow(row);
        }
    }
    // Update the selected record with justification and disciplinary notes from a dialog
    private void updateSelectedRecord() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(mainPanel, "Please select a record to update.", "No Selection", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get current values from the table (simplified, in a real app you'd fetch from model)
        String studentName = (String) tableModel.getValueAt(selectedRow, 0);
        String status = (String) tableModel.getValueAt(selectedRow, 1);
        String currentJustification = (String) tableModel.getValueAt(selectedRow, 2);
        String currentNotes = (String) tableModel.getValueAt(selectedRow, 3);
        // Create a dialog for editing
        JPanel panel = new JPanel(new GridLayout(3, 2));
        panel.add(new JLabel("Justification:"));
        JTextField justificationField = new JTextField(currentJustification, 20);
        panel.add(justificationField);
        panel.add(new JLabel("Disciplinary Notes:"));
        JTextField notesField = new JTextField(currentNotes, 20);
        panel.add(notesField);
        panel.add(new JLabel("Status:"));
        JComboBox<String> statusCombo = new JComboBox<>(new String[]{"Present", "Absent", "Delayed"});
        statusCombo.setSelectedItem(status);
        panel.add(statusCombo);
        int result = JOptionPane.showConfirmDialog(mainPanel, panel, "Update Record", JOptionPane.OK_CANCEL_OPTION);
        if (result == JOptionPane.OK_OPTION) {
            // Update the table (for demo, in real app you'd update the model)
            tableModel.setValueAt(justificationField.getText(), selectedRow, 2);
            tableModel.setValueAt(notesField.getText(), selectedRow, 3);
            tableModel.setValueAt(statusCombo.getSelectedItem(), selectedRow, 1);
            // In a real application, you would also update the model via controller
            // For simplicity, we are updating the table directly. In a full implementation,
            // you would retrieve the corresponding AttendanceRecord from the model and update it.
            JOptionPane.showMessageDialog(mainPanel, "Record updated successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
}