'''
Main GUI class for the Administrator to enter student absences and delays.
This class provides the user interface for selecting a date, displaying student attendance,
marking students as absent or delayed, and saving the entered data.
It interacts with backend serv for data management and notifications.
'''
package com.chatdev.absencetracker.gui;
import com.chatdev.absencetracker.model.AbsenceEntry;
import com.chatdev.absencetracker.model.Student;
import com.chatdev.absencetracker.service.AbsenceService;
import com.chatdev.absencetracker.service.NotificationService;
import com.chatdev.absencetracker.service.StudentService;
import javax.swing.*;
import javax.swing.table.TableCellEditor;
import javax.swing.table.TableCellRenderer;
import javax.swing.text.MaskFormatter;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.text.ParseException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.time.format.DateTimeParseException;
import java.util.EventObject;
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors;
public class EnterAbsencesAdminGUI extends JFrame {
    private final StudentService studentService;
    private final NotificationService notificationService;
    private final AbsenceService absenceService;
    // GUI Components
    private JFormattedTextField dateField;
    private JButton loadDataButton;
    private JTable studentsTable;
    private StudentsTableModel tableModel;
    private JButton saveButton;
    private JTextArea logArea;
    private LocalDate selectedDate; // The date currently being viewed/edited
    // Thread pool for background tasks (e.g., saving data, sending notifications).
    // This prevents the GUI from freezing during potentially long-running operations.
    private final ExecutorService executorService = Executors.newSingleThreadExecutor();
    '''
    Constructor for the EnterAbsencesAdminGUI.
    Initializes all necessary serv and sets up the graphical user interface components.
    '''
    public EnterAbsencesAdminGUI() {
        // Initialize serv
        this.studentService = new StudentService();
        this.notificationService = new NotificationService();
        this.absenceService = new AbsenceService(this.studentService, this.notificationService);
        // Set up the main window properties
        setTitle("Administrator Absence/Delay Entry System");
        setSize(1000, 700); // Increased size to accommodate log area
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window on the screen
        // Initialize GUI components and layout
        initGUI();
        // Set initial selected date to today and load data.
        selectedDate = LocalDate.now();
        // Format the date for the text field.
        dateField.setText(selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")));
        loadStudentsTable(selectedDate); // Initial load of student data for today
    }
    '''
    Initializes and arranges all GUI components within the JFrame.
    '''
    private void initGUI() {
        // Use BorderLayout for the main JFrame to organize major sections.
        setLayout(new BorderLayout(10, 10)); // 10px horizontal and vertical gaps
        // Add date selection panel to the top (NORTH)
        add(createDateSelectionPanel(), BorderLayout.NORTH);
        // Add student entry table panel to the center (CENTER)
        add(createStudentEntryPanel(), BorderLayout.CENTER);
        // Add actions panel (Save button) to the bottom (SOUTH)
        add(createActionsPanel(), BorderLayout.SOUTH);
        // Add log panel to the right (EAST)
        JPanel logPanel = new JPanel(new BorderLayout());
        logPanel.setBorder(BorderFactory.createTitledBorder("System Log and Notifications"));
        logArea = new JTextArea(15, 35); // 15 rows, 35 columns
        logArea.setEditable(false); // Log area should not be editable by the user
        logArea.setLineWrap(true); // Wraps lines if necessary
        logArea.setWrapStyleWord(true); // Wraps at word boundaries
        JScrollPane logScrollPane = new JScrollPane(logArea); // Add scroll capability
        logPanel.add(logScrollPane, BorderLayout.CENTER);
        add(logPanel, BorderLayout.EAST); // Place it on the right side
    }
    '''
    Creates the panel responsible for date selection.
    Includes a JFormattedTextField for date input and a button to load data for that date.
    @return A JPanel containing date selection components.
    '''
    private JPanel createDateSelectionPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10)); // FlowLayout for simple left-aligned components
        panel.setBorder(BorderFactory.createTitledBorder("Select Attendance Date"));
        panel.add(new JLabel("Date (YYYY-MM-DD):"));
        try {
            // MaskFormatter helps enforce the date format YYYY-MM-DD
            MaskFormatter dateFormatter = new MaskFormatter("####-##-##");
            dateFormatter.setPlaceholderCharacter('_'); // Placeholder character
            dateField = new JFormattedTextField(dateFormatter);
            dateField.setColumns(10); // Fixed width for date input
            // Commit on focus lost ensures the value is parsed/validated when user tabs away.
            dateField.setFocusLostBehavior(JFormattedTextField.COMMIT);
            panel.add(dateField);
        } catch (ParseException e) {
            log("Error creating date input field: " + e.getMessage());
            // Fallback to a plain JTextField if MaskFormatter fails
            dateField = new JFormattedTextField();
            dateField.setColumns(10);
            panel.add(dateField);
        }
        loadDataButton = new JButton("Load Data for Date");
        // Add an ActionListener to the button to trigger data loading
        loadDataButton.addActionListener(e -> reloadDataForSelectedDate());
        panel.add(loadDataButton);
        return panel;
    }
    '''
    Creates the panel containing the JTable for displaying and editing
    student attendance statuses (absent/delayed).
    @return A JPanel containing the JTable wrapped in a JScrollPane.
    '''
    private JPanel createStudentEntryPanel() {
        JPanel panel = new JPanel(new BorderLayout());
        panel.setBorder(BorderFactory.createTitledBorder("Mark Absences and Delays for Students"));
        tableModel = new StudentsTableModel(); // Custom table model
        studentsTable = new JTable(tableModel);
        studentsTable.setFillsViewportHeight(true); // Makes table use full height of scrollpane
        studentsTable.getTableHeader().setReorderingAllowed(false); // Prevent users from moving columns
        studentsTable.setAutoCreateRowSorter(true); // Enable sorting of columns
        // Set up custom editor and renderer for Boolean columns (Absent, Delayed)
        // This makes the Boolean columns appear and behave as JCheckBoxes
        studentsTable.setDefaultRenderer(Boolean.class, new JCheckBoxRenderer());
        studentsTable.setDefaultEditor(Boolean.class, new JCheckBoxEditor());
        JScrollPane scrollPane = new JScrollPane(studentsTable); // Add scroll bars if table content exceeds view
        panel.add(scrollPane, BorderLayout.CENTER);
        return panel;
    }
    '''
    Creates the panel containing action buttons, specifically the "Save" button.
    @return A JPanel containing action buttons.
    '''
    private JPanel createActionsPanel() {
        JPanel panel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Right-aligned FlowLayout
        saveButton = new JButton("Save Absences/Delays");
        // Add an ActionListener to the save button
        saveButton.addActionListener(this::saveAbsencesAction);
        panel.add(saveButton);
        return panel;
    }
    '''
    Handles the action when the "Load Data for Date" button is clicked.
    Parses the date from the `dateField` and triggers updating the student table
    with attendance data for the newly selected date. Includes error handling for invalid date formats.
    '''
    private void reloadDataForSelectedDate() {
        String dateText = dateField.getText().trim();
        // Check if the mask formatter's placeholder character is still present, indicating incomplete input.
        if (dateText.contains("_")) {
            log("Please enter a complete date (YYYY-MM-DD).");
            JOptionPane.showMessageDialog(this, "Please enter a complete date (YYYY-MM-DD).", "Incomplete Date", JOptionPane.WARNING_MESSAGE);
            return;
        }
        try {
            // Parse the date string into a LocalDate object
            // FIX: Changed "yyyy-MM-DD" to "yyyy-MM-dd" for correct day-of-month parsing.
            selectedDate = LocalDate.parse(dateText, DateTimeFormatter.ofPattern("yyyy-MM-dd"));
            log("Date selected: " + selectedDate.format(DateTimeFormatter.ofPattern("EEEE, MMMM d, yyyy")));
            // Call method to load student data for this new date
            loadStudentsTable(selectedDate);
        } catch (DateTimeParseException ex) {
            log("Invalid date format. Please use YYYY-MM-DD.");
            JOptionPane.showMessageDialog(this, "Invalid date format. Please use YYYY-MM-DD.", "Date Error", JOptionPane.ERROR_MESSAGE);
        }
    }
    '''
    Loads all student data from the `StudentService` and populates the `JTable`.
    It also fetches any existing absence/delay entries for the currently `selectedDate`
    to pre-fill the checkboxes in the table.
    @param date The `LocalDate` for which to load student attendance status.
                 This date determines which existing absence entries are shown.
    '''
    private void loadStudentsTable(LocalDate date) {
        log("Loading student data for date: " + date.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "...");
        List<Student> allStudents = studentService.getAllStudents();
        List<AbsenceEntry> existingEntries = absenceService.getAbsenceEntriesForDate(date);
        // Pass students and existing entries to the table model to prepare its data.
        tableModel.setStudentsAndAbsenceEntries(allStudents, existingEntries, date);
        log("Student data loaded successfully. Ready for attendance entry.");
    }
    '''
    Action listener method for the "Save Absences/Delays" button.
    This method orchestrates the process of gathering data from the table,
    sending it to the `AbsenceService` for persistence and notification, and
    providing feedback to the user via the log and dialogs.
    The save operation is executed on a separate thread using `executorService`
    to keep the GUI responsive and simulate potential network delays.
    '''
    private void saveAbsencesAction(ActionEvent e) {
        // Stop editing any cells in the table to ensure latest changes are committed to the model.
        if (studentsTable.isEditing()) {
            TableCellEditor editor = studentsTable.getCellEditor();
            if (editor != null) {
                editor.stopCellEditing();
            }
        }
        if (selectedDate == null) {
            log("Please select a date first before attempting to save.");
            JOptionPane.showMessageDialog(this, "Please select a date first.", "Missing Date", JOptionPane.WARNING_MESSAGE);
            return;
        }
        log("Attempting to save absence/delay records for " + selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + "...");
        saveButton.setEnabled(false); // Disable button to prevent multiple submissions
        // Submit the save operation to the background executor service.
        executorService.submit(() -> {
            try {
                // Simulate a random chance of SMOS server interruption, as per use case.
                if (Math.random() < 0.15) { // ~15% chance of interruption
                    absenceService.simulateSMOSServerInterruption();
                    // Update GUI on the Event Dispatch Thread (EDT)
                    SwingUtilities.invokeLater(() -> {
                        log("Operation interrupted due to simulated SMOS server connection issue. Please try again.");
                        JOptionPane.showMessageDialog(this, "Operation interrupted due to simulated SMOS server connection issue.", "Server Error", JOptionPane.ERROR_MESSAGE);
                    });
                    return; // Exit the task as it was interrupted
                }
                // Get only marked absence/delay entries from the table model
                List<AbsenceEntry> entriesToSave = tableModel.getCurrentAbsenceEntries();
                if (entriesToSave.isEmpty()) {
                    SwingUtilities.invokeLater(() -> {
                        log("No absences or delays were marked for " + selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")) + ". Nothing to save.");
                        JOptionPane.showMessageDialog(this, "No absences or delays marked. Nothing to save.", "Info", JOptionPane.INFORMATION_MESSAGE);
                    });
                } else {
                    // Process the entries via the service layer
                    List<AbsenceEntry> processedEntries = absenceService.processAbsenceEntries(selectedDate, entriesToSave);
                    SwingUtilities.invokeLater(() -> {
                        // The `processedEntries` only contains entries that successfully triggered notifications.
                        // However, `processAbsenceEntries` itself internally handles both successful saves
                        // and errors (like student not found). The log below reports on the entries that *led* to a notification.
                        log(String.format("Successfully processed attendance for %s. %d notifications dispatched (for valid absence/delay entries).",
                                selectedDate.format(DateTimeFormatter.ofPattern("yyyy-MM-dd")), processedEntries.size()));
                        JOptionPane.showMessageDialog(this, "Attendance data saved and notifications dispatched (if applicable).", "Success", JOptionPane.INFORMATION_MESSAGE);
                    });
                }
                // Always reload the table after a save attempt to reflect the most current state,
                // including any updates or new entries. This also ensures that if some entries failed
                // validation (e.g., student not found), they are not shown as saved.
                SwingUtilities.invokeLater(() -> {
                    loadStudentsTable(selectedDate); // Refresh the table display
                });
            } catch (Exception ex) {
                // Catch any unexpected exceptions during the save process
                SwingUtilities.invokeLater(() -> {
                    log("An unexpected error occurred during save operation: " + ex.getMessage());
                    JOptionPane.showMessageDialog(this, "An unexpected error occurred: " + ex.getMessage(), "Error", JOptionPane.ERROR_MESSAGE);
                });
            } finally {
                // Ensure the save button is re-enabled even if an error occurs.
                SwingUtilities.invokeLater(() -> saveButton.setEnabled(true));
            }
        });
    }
    '''
    Appends a message to the `logArea` with a timestamp.
    All updates to GUI components should be done on the Event Dispatch Thread (EDT).
    This method uses `SwingUtilities.invokeLater` to ensure thread safety.
    @param message The string message to be logged.
    '''
    private void log(String message) {
        SwingUtilities.invokeLater(() -> {
            logArea.append(String.format("[%s] %s%n",
                    java.time.LocalTime.now().format(java.time.format.DateTimeFormatter.ofPattern("HH:mm:ss")),
                    message));
            // Automatically scroll to the bottom of the log area to show the latest messages.
            logArea.setCaretPosition(logArea.getDocument().getLength());
        });
    }
    '''
    Main method to run the application.
    Ensures that the GUI is created and displayed on the Event Dispatch Thread (EDT)
    as required by Swing for thread safety.
    @param args Command line arguments (not used in this application).
    '''
    public static void main(String[] args) {
        // Schedule a job for the event-dispatching thread:
        // creating and showing this application's GUI.
        SwingUtilities.invokeLater(() -> {
            EnterAbsencesAdminGUI gui = new EnterAbsencesAdminGUI();
            gui.setVisible(true);
        });
    }
    // --- Helper classes for JTable Checkbox Renderer/Editor ---
    '''
    Custom `TableCellRenderer` for rendering Boolean values as `JCheckBox` components within a `JTable`.
    This allows the "Absent" and "Delayed" columns to visually appear as checkboxes.
    '''
    private static class JCheckBoxRenderer extends JCheckBox implements TableCellRenderer {
        '''
        Constructs a new JCheckBoxRenderer.
        Sets the horizontal alignment of the checkbox to center.
        '''
        JCheckBoxRenderer() {
            setHorizontalAlignment(JLabel.CENTER);
        }
        '''
        Returns the component used for drawing the cell.
        @param table The JTable being rendered.
        @param value The value of the cell to be rendered (expected to be a Boolean).
        @param isSelected True if the cell is selected.
        @param hasFocus True if the cell has focus.
        @param row The row index of the cell.
        @param column The column index of the cell.
        @return The JCheckBox component configured to display the cell's value.
        '''
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            if (value instanceof Boolean) {
                setSelected((Boolean) value);
            }
            // Set background and foreground colors based on selection and focus status
            if (isSelected) {
                setForeground(table.getSelectionForeground());
                setBackground(table.getSelectionBackground());
            } else {
                setForeground(table.getForeground());
                setBackground(table.getBackground());
            }
            return this;
        }
    }
    '''
    Custom `TableCellEditor` for editing Boolean values using a `JCheckBox` component within a `JTable`.
    This allows users to interact with the checkboxes in the "Absent" and "Delayed" columns.
    '''
    private static class JCheckBoxEditor extends DefaultCellEditor {
        private final JCheckBox checkBox;
        '''
        Constructs a new JCheckBoxEditor.
        Uses a JCheckBox as the editing component.
        '''
        JCheckBoxEditor() {
            // Pass a JCheckBox to the super constructor.
            // This JCheckBox will be the actual component displayed and interacted with.
            super(new JCheckBox());
            checkBox = (JCheckBox) getComponent();
            checkBox.setHorizontalAlignment(JLabel.CENTER);
            // Add an ActionListener to the checkbox to stop editing when its state changes.
            // This ensures that the new value is committed to the table model immediately.
            checkBox.addActionListener(e -> fireEditingStopped());
        }
        '''
        Returns the component that should be used to edit the cell.
        @param table The JTable in which the cell is being edited.
        @param value The value of the cell to be edited (expected to be a Boolean).
        @param isSelected True if the cell is selected.
        @param row The row index of the cell.
        @param column The column index of the cell.
        @return The JCheckBox component configured for editing.
        '''
        @Override
        public Component getTableCellEditorComponent(JTable table, Object value,
                                                     boolean isSelected, int row, int column) {
            if (value instanceof Boolean) {
                checkBox.setSelected((Boolean) value);
            }
            // Set background and foreground colors for the editor based on selection status
            if (isSelected) {
                checkBox.setForeground(table.getSelectionForeground());
                checkBox.setBackground(table.getSelectionBackground());
            } else {
                checkBox.setForeground(table.getForeground());
                checkBox.setBackground(table.getBackground());
            }
            return checkBox;
        }
        '''
        Returns the value contained in the editor.
        This method is called when the editing process stops to retrieve the new cell value.
        @return The current selected state of the JCheckBox (a Boolean).
        '''
        @Override
        public Object getCellEditorValue() {
            return checkBox.isSelected();
        }
        '''
        Overrides shouldYieldFocus to ensure that the editor can always stop editing.
        @param e An EventObject triggering the focus change.
        @return Always true, allowing focus changes to commit edits.
        '''
        @Override
        public boolean shouldSelectCell(EventObject e) {
            return true;
        }
    }
}