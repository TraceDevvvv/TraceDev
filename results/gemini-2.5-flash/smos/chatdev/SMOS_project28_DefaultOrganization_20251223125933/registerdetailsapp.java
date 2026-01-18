/**
 * GUI application for displaying and managing class register details.
 * Allows administrators to view student attendance, lateness, and update
 * justifications and disciplinary notes for individual students.
 */
package com.chatdev.register.gui;
import com.chatdev.register.model.AttendanceEntry;
import com.chatdev.register.model.AttendanceStatus;
import com.chatdev.register.model.ClassRegisterDetails;
import com.chatdev.register.service.RegisterService;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import javax.swing.table.DefaultTableCellRenderer;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Vector;
import java.util.stream.IntStream;
public class RegisterDetailsApp extends JFrame {
    private final String registerId;
    // Changed from final displayDate to mutable currentSelectedDate to allow date selection
    private LocalDate currentSelectedDate;
    private final RegisterService registerService;
    // GUI Components
    private JLabel headerLabel;
    private JComboBox<LocalDate> datePickerComboBox; // New date picker component
    private JTable attendanceTable;
    private DefaultTableModel tableModel;
    private JPanel detailsPanel; // Panel for managing justification/disciplinary notes
    private JLabel selectedStudentLabel;
    private JComboBox<AttendanceStatus> attendanceStatusComboBox;
    private JCheckBox lateCheckBox;
    private JTextArea justificationTextArea;
    private JTextArea disciplinaryNoteTextArea;
    private JButton updateAttendanceButton;
    private JButton saveJustificationButton;
    private JButton saveDisciplinaryNoteButton;
    private List<AttendanceEntry> currentAttendanceEntries; // Store entries for easier access by row index
    /**
     * Constructs the RegisterDetailsApp GUI.
     *
     * @param registerId The ID of the class register to display.
     */
    public RegisterDetailsApp(String registerId) {
        this.registerId = registerId;
        this.currentSelectedDate = LocalDate.now(); // Initialize to today's date
        this.registerService = new RegisterService();
        this.currentAttendanceEntries = new ArrayList<>();
        setTitle("Class Register Details - Admin View");
        setSize(1000, 700);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents(); // Initialize GUI components
        populateDatePicker(); // Populate the date picker with initial dates
        loadRegisterDetails(); // Load and display data for the initially selected date
        setVisible(true);
    }
    /**
     * Initializes all the GUI components and sets up the layout.
     */
    private void initComponents() {
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(new EmptyBorder(10, 10, 10, 10)); // Padding
        //region Header Panel
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5)); // Added spacing for elements
        headerLabel = new JLabel("Class Register: " + registerId); // Initial text, date will be appended dynamically
        headerLabel.setFont(new Font("SansSerif", Font.BOLD, 18));
        northPanel.add(headerLabel);
        // Date selection components
        northPanel.add(new JLabel("  Select Date: "));
        datePickerComboBox = new JComboBox<>();
        datePickerComboBox.setRenderer(new DefaultListCellRenderer() {
            @Override
            public Component getListCellRendererComponent(JList<?> list, Object value, int index, boolean isSelected, boolean cellHasFocus) {
                super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
                if (value instanceof LocalDate) {
                    setText(((LocalDate) value).format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
                }
                return this;
            }
        });
        datePickerComboBox.addActionListener(e -> {
            LocalDate selectedDate = (LocalDate) datePickerComboBox.getSelectedItem();
            if (selectedDate != null && !selectedDate.equals(currentSelectedDate)) {
                currentSelectedDate = selectedDate; // Update the currently selected date
                loadRegisterDetails(); // Reload data for the newly selected date
            }
        });
        northPanel.add(datePickerComboBox);
        mainPanel.add(northPanel, BorderLayout.NORTH);
        //endregion
        //region Attendance Table Panel
        String[] columnNames = {"Student ID", "Student Name", "Status", "Late", "Justification", "Disciplinary Note"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // All cells should be non-editable directly in the table
                return false;
            }
        };
        attendanceTable = new JTable(tableModel);
        attendanceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
        attendanceTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensures selection is final
                    int selectedRow = attendanceTable.getSelectedRow();
                    if (selectedRow != -1) {
                        displaySelectedStudentDetails(selectedRow); // Show details in the form below
                    } else {
                        clearSelectedStudentDetails(); // Clear form if no row is selected
                    }
                }
            }
        });
        attendanceTable.setRowHeight(25);
        attendanceTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        // Custom renderer for Status column for better visibility if needed (e.g., color coding)
        attendanceTable.getColumnModel().getColumn(2).setCellRenderer(new DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value, boolean isSelected, boolean hasFocus, int row, int column) {
                super.getTableCellRendererComponent(table, value, index, isSelected, hasFocus, row, column);
                if (value instanceof AttendanceStatus) {
                    AttendanceStatus status = (AttendanceStatus) value;
                    switch (status) {
                        case ABSENT:
                            setForeground(Color.RED);
                            break;
                        case LATE:
                            setForeground(Color.ORANGE); // Or another distinct color
                            break;
                        case PRESENT:
                            setForeground(new Color(0, 128, 0)); // Darker green
                            break;
                    }
                } else {
                    setForeground(table.getForeground());
                }
                return this;
            }
        });
        JScrollPane scrollPane = new JScrollPane(attendanceTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        //endregion
        //region Details/Management Panel (South)
        detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createTitledBorder("Manage Student Records"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        selectedStudentLabel = new JLabel("Select a student from the table above to manage records.");
        selectedStudentLabel.setFont(new Font("SansSerif", Font.ITALIC, 14));
        gbc.gridx = 0; gbc.gridy = 0; gbc.gridwidth = 4; gbc.anchor = GridBagConstraints.WEST;
        detailsPanel.add(selectedStudentLabel, gbc);
        // Attendance Status & Lateness
        gbc.gridy++; gbc.gridwidth = 1;
        detailsPanel.add(new JLabel("Attendance Status:"), gbc);
        gbc.gridx = 1;
        attendanceStatusComboBox = new JComboBox<>(AttendanceStatus.values());
        attendanceStatusComboBox.setEnabled(false);
        detailsPanel.add(attendanceStatusComboBox, gbc);
        gbc.gridx = 2;
        lateCheckBox = new JCheckBox("Late");
        lateCheckBox.setEnabled(false);
        detailsPanel.add(lateCheckBox, gbc);
        gbc.gridx = 3;
        updateAttendanceButton = new JButton("Update Attendance");
        updateAttendanceButton.setEnabled(false);
        detailsPanel.add(updateAttendanceButton, gbc);
        // Justification
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 4;
        detailsPanel.add(new JLabel("Justification:"), gbc);
        gbc.gridy++;
        justificationTextArea = new JTextArea(3, 40);
        justificationTextArea.setLineWrap(true);
        justificationTextArea.setWrapStyleWord(true);
        justificationTextArea.setEnabled(false);
        JScrollPane justificationScrollPane = new JScrollPane(justificationTextArea);
        detailsPanel.add(justificationScrollPane, gbc);
        gbc.gridy++; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // Reset fill for button
        saveJustificationButton = new JButton("Save Justification");
        saveJustificationButton.setEnabled(false);
        detailsPanel.add(saveJustificationButton, gbc);
        // Disciplinary Notes
        gbc.gridx = 0; gbc.gridy++; gbc.gridwidth = 4;
        gbc.fill = GridBagConstraints.HORIZONTAL; // Restore fill
        detailsPanel.add(new JLabel("Disciplinary Notes:"), gbc);
        gbc.gridy++;
        disciplinaryNoteTextArea = new JTextArea(3, 40);
        disciplinaryNoteTextArea.setLineWrap(true);
        disciplinaryNoteTextArea.setWrapStyleWord(true);
        disciplinaryNoteTextArea.setEnabled(false);
        JScrollPane disciplinaryNoteScrollPane = new JScrollPane(disciplinaryNoteTextArea);
        detailsPanel.add(disciplinaryNoteScrollPane, gbc);
        gbc.gridy++; gbc.gridwidth = 1;
        gbc.fill = GridBagConstraints.NONE; // Reset fill for button
        saveDisciplinaryNoteButton = new JButton("Save Disciplinary Note");
        saveDisciplinaryNoteButton.setEnabled(false);
        detailsPanel.add(saveDisciplinaryNoteButton, gbc);
        mainPanel.add(detailsPanel, BorderLayout.SOUTH);
        //endregion
        // Add action listeners
        updateAttendanceButton.addActionListener(e -> updateAttendance());
        saveJustificationButton.addActionListener(e -> saveJustification());
        saveDisciplinaryNoteButton.addActionListener(e -> saveDisciplinaryNote());
        add(mainPanel);
    }
    /**
     * Populates the date picker combo box with a range of dates.
     * For simplicity, this populates with today and previous 6 days.
     * In a real application, this might fetch available dates from the register data.
     */
    private void populateDatePicker() {
        datePickerComboBox.removeAllItems(); // Clear any existing items
        // Add today and previous 6 days to the combo box
        IntStream.range(0, 7)
                 .mapToObj(LocalDate.now()::minusDays)
                 .forEach(datePickerComboBox::addItem);
        // Ensure the initially selected date (today) is chosen in the combo box
        datePickerComboBox.setSelectedItem(currentSelectedDate);
    }
    /**
     * Loads the class register details for the specified register ID and
     * the currently selected date from the service and populates the GUI.
     */
    private void loadRegisterDetails() {
        // Clear previous data
        tableModel.setRowCount(0);
        currentAttendanceEntries.clear();
        clearSelectedStudentDetails(); // Reset the details panel
        // Update header label with the currently selected date
        headerLabel.setText("Class Register: " + registerId + " - Date: " + currentSelectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
        // Simulate a service call on a background thread to avoid freezing the UI
        SwingWorker<ClassRegisterDetails, Void> worker = new SwingWorker<>() {
            @Override
            protected ClassRegisterDetails doInBackground() {
                // In a real application, connection to SMOS server would happen here.
                // After data retrieval, the connection might be considered 'interrupted' or closed.
                return registerService.getRegisterDetails(registerId, currentSelectedDate);
            }
            @Override
            protected void done() {
                try {
                    ClassRegisterDetails details = get();
                    if (details != null && !details.getAttendanceEntries().isEmpty()) {
                        headerLabel.setText("Class Register: " + details.getRegisterId() + " - Date: " + details.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
                        populateAttendanceTable(details);
                        // Postcondition: Connection to the interrupted SMOS server
                        // Simulate the SMOS connection being interrupted after successfully displaying details.
                        registerService.setSmosConnectionInterrupted(true);
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this,
                            "Register details loaded successfully for " + details.getDate().format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) + ".\n" +
                            "Connection to SMOS server is now interrupted (as per use case postcondition).",
                            "SMOS Connection Status", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        headerLabel.setText("Class Register: " + registerId + " - Date: " + currentSelectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")) + " (No records found)");
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "No attendance records found for " + registerId + " on " + currentSelectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")), "No Data", JOptionPane.INFORMATION_MESSAGE);
                        registerService.setSmosConnectionInterrupted(true); // Still indicate interruption as operation completed (even if empty)
                    }
                } catch (Exception e) {
                    headerLabel.setText("Error loading details for " + registerId + " on " + currentSelectedDate.format(DateTimeFormatter.ofPattern("dd MMMM yyyy")));
                    JOptionPane.showMessageDialog(RegisterDetailsApp.this,
                            "Failed to load register details: " + e.getMessage() + "\nConnection to SMOS server might be interrupted.",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    System.err.println("Error loading register details: " + e.getMessage());
                    e.printStackTrace();
                    // If an error occurs, the connection is also implicitly interrupted or failed.
                    registerService.setSmosConnectionInterrupted(true);
                }
            }
        };
        worker.execute();
    }
    /**
     * Populates the attendance table with data from the ClassRegisterDetails object.
     *
     * @param details The ClassRegisterDetails object containing attendance information.
     */
    private void populateAttendanceTable(ClassRegisterDetails details) {
        tableModel.setRowCount(0); // Clear existing rows
        currentAttendanceEntries.clear();
        // Convert map values to list and sort them by student name for consistent display
        List<AttendanceEntry> sortedEntries = new ArrayList<>(details.getAttendanceEntries().values());
        sortedEntries.sort(Comparator.comparing(entry -> entry.getStudent().getName()));
        for (AttendanceEntry entry : sortedEntries) {
            Vector<Object> row = new Vector<>();
            row.add(entry.getStudent().getStudentId());
            row.add(entry.getStudent().getName());
            row.add(entry.getStatus()); // Enum will be displayed by its toString()
            row.add(entry.isLate() ? "Yes" : "No");
            row.add(entry.getJustification() != null && !entry.getJustification().isEmpty() ? entry.getJustification() : "-");
            row.add(entry.getDisciplinaryNote() != null && !entry.getDisciplinaryNote().isEmpty() ? entry.getDisciplinaryNote() : "-");
            tableModel.addRow(row);
            currentAttendanceEntries.add(entry); // Keep a local reference for updates
        }
    }
    /**
     * Displays the details of the student selected in the table in the
     * justification/disciplinary notes form area.
     *
     * @param selectedRow The index of the selected row in the table.
     */
    private void displaySelectedStudentDetails(int selectedRow) {
        if (selectedRow < 0 || selectedRow >= currentAttendanceEntries.size()) {
            clearSelectedStudentDetails();
            return;
        }
        AttendanceEntry entry = currentAttendanceEntries.get(selectedRow);
        selectedStudentLabel.setText(String.format("Managing records for: %s (%s)", entry.getStudent().getName(), entry.getStudent().getStudentId()));
        attendanceStatusComboBox.setSelectedItem(entry.getStatus());
        lateCheckBox.setSelected(entry.isLate());
        justificationTextArea.setText(entry.getJustification());
        disciplinaryNoteTextArea.setText(entry.getDisciplinaryNote());
        toggleDetailsPanelEditing(true); // Enable editing fields
        // Highlight the selected row
        attendanceTable.setRowSelectionInterval(selectedRow, selectedRow);
    }
    /**
     * Clears and disables the fields in the justification/disciplinary notes form.
     */
    private void clearSelectedStudentDetails() {
        selectedStudentLabel.setText("Select a student from the table above to manage records.");
        // Set to default 'Present' status if items exist
        if (attendanceStatusComboBox.getItemCount() > 0) {
             attendanceStatusComboBox.setSelectedIndex(0);
        }
        lateCheckBox.setSelected(false);
        justificationTextArea.setText("");
        disciplinaryNoteTextArea.setText("");
        toggleDetailsPanelEditing(false); // Disable editing fields
    }
    /**
     * Enables or disables the editing components in the details panel.
     *
     * @param enable True to enable, false to disable.
     */
    private void toggleDetailsPanelEditing(boolean enable) {
        attendanceStatusComboBox.setEnabled(enable);
        lateCheckBox.setEnabled(enable);
        justificationTextArea.setEnabled(enable);
        disciplinaryNoteTextArea.setEnabled(enable);
        updateAttendanceButton.setEnabled(enable);
        saveJustificationButton.setEnabled(enable);
        saveDisciplinaryNoteButton.setEnabled(enable);
    }
    /**
     * Updates the attendance status and lateness flag for the selected student
     * and persists the changes via the RegisterService.
     */
    private void updateAttendance() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student first.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AttendanceEntry entry = currentAttendanceEntries.get(selectedRow);
        AttendanceStatus newStatus = (AttendanceStatus) attendanceStatusComboBox.getSelectedItem();
        boolean newLate = lateCheckBox.isSelected();
        String justification = justificationTextArea.getText();
        String disciplinaryNote = disciplinaryNoteTextArea.getText();
        // Update in-memory model immediately for responsiveness
        entry.setStatus(newStatus);
        entry.setLate(newLate);
        tableModel.setValueAt(newStatus, selectedRow, 2); // Status column
        tableModel.setValueAt(newLate ? "Yes" : "No", selectedRow, 3); // Late column
        // Simulate persistence in background using SwingWorker
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Use currentSelectedDate for persistence
                return registerService.updateAttendanceEntry(
                    registerId, currentSelectedDate, entry.getStudent().getStudentId(),
                    newStatus, newLate, justification, disciplinaryNote
                );
            }
            @Override
            protected void done() {
                try {
                    if (get()) {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Attendance updated and saved successfully for " + entry.getStudent().getName(), "Update Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Failed to save attendance update for " + entry.getStudent().getName() + ". Please try again.", "Save Error", JOptionPane.ERROR_MESSAGE);
                        // Reload to reflect actual state if persistence failed
                        loadRegisterDetails();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegisterDetailsApp.this, "An error occurred while saving attendance: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    // Reload to reflect actual state on error
                    loadRegisterDetails();
                }
            }
        }.execute();
    }
    /**
     * Saves the justification for the selected student and persists the changes
     * via the RegisterService.
     */
    private void saveJustification() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student first.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AttendanceEntry entry = currentAttendanceEntries.get(selectedRow);
        String newJustification = justificationTextArea.getText();
        // Get other current values to send a complete update request
        AttendanceStatus currentStatus = (AttendanceStatus) attendanceStatusComboBox.getSelectedItem();
        boolean currentLate = lateCheckBox.isSelected();
        String currentDisciplinaryNote = disciplinaryNoteTextArea.getText();
        // Update in-memory model (the entry object is already mutable)
        entry.setJustification(newJustification);
        // Update the table display
        tableModel.setValueAt(newJustification != null && !newJustification.isEmpty() ? newJustification : "-", selectedRow, 4); // Justification column
        // Simulate persistence in background using SwingWorker
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Use currentSelectedDate for persistence
                return registerService.updateAttendanceEntry(
                    registerId, currentSelectedDate, entry.getStudent().getStudentId(),
                    currentStatus, currentLate, newJustification, currentDisciplinaryNote
                );
            }
            @Override
            protected void done() {
                try {
                    if (get()) {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Justification saved successfully for " + entry.getStudent().getName(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Failed to save justification for " + entry.getStudent().getName() + ". Please try again.", "Save Error", JOptionPane.ERROR_MESSAGE);
                        loadRegisterDetails();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegisterDetailsApp.this, "An error occurred while saving justification: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    loadRegisterDetails();
                }
            }
        }.execute();
    }
    /**
     * Saves the disciplinary note for the selected student and persists the changes
     * via the RegisterService.
     */
    private void saveDisciplinaryNote() {
        int selectedRow = attendanceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student first.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        AttendanceEntry entry = currentAttendanceEntries.get(selectedRow);
        String newDisciplinaryNote = disciplinaryNoteTextArea.getText();
        // Get other current values to send a complete update request
        AttendanceStatus currentStatus = (AttendanceStatus) attendanceStatusComboBox.getSelectedItem();
        boolean currentLate = lateCheckBox.isSelected();
        String currentJustification = justificationTextArea.getText();
        // Update in-memory model (the entry object is already mutable)
        entry.setDisciplinaryNote(newDisciplinaryNote);
        // Update the table display
        tableModel.setValueAt(newDisciplinaryNote != null && !newDisciplinaryNote.isEmpty() ? newDisciplinaryNote : "-", selectedRow, 5); // Disciplinary Note column
        // Simulate persistence in background using SwingWorker
        new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                // Use currentSelectedDate for persistence
                return registerService.updateAttendanceEntry(
                    registerId, currentSelectedDate, entry.getStudent().getStudentId(),
                    currentStatus, currentLate, currentJustification, newDisciplinaryNote
                );
            }
            @Override
            protected void done() {
                try {
                    if (get()) {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Disciplinary note saved successfully for " + entry.getStudent().getName(), "Save Successful", JOptionPane.INFORMATION_MESSAGE);
                    } else {
                        JOptionPane.showMessageDialog(RegisterDetailsApp.this, "Failed to save disciplinary note for " + entry.getStudent().getName() + ". Please try again.", "Save Error", JOptionPane.ERROR_MESSAGE);
                        loadRegisterDetails();
                    }
                } catch (Exception ex) {
                    JOptionPane.showMessageDialog(RegisterDetailsApp.this, "An error occurred while saving disciplinary note: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                    ex.printStackTrace();
                    loadRegisterDetails();
                }
            }
        }.execute();
    }
}