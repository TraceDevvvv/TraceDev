import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.Comparator;
import java.util.List;
import java.util.Optional;
/*
 * The main application window for the ViewRegister system.
 * It provides a GUI for selecting a date, viewing student attendance,
 * and managing justifications and disciplinary notes.
 */
public class RegisterApp extends JFrame {
    private RegisterService registerService;
    private ClassRegister currentClassRegister;
    private String currentClassName = "10A"; // Example hardcoded class
    private String currentAcademicYear = "2023-2024"; // Example hardcoded academic year
    private LocalDate selectedDate;
    // GUI Components
    private JComboBox<LocalDate> dateComboBox;
    private JTable registerTable;
    private RegisterTableModel tableModel;
    private JButton justifyButton;
    private JButton addDisciplinaryNoteButton;
    private JButton updateStatusButton; // New button to update attendance status
    // New line: Declare the listener as a field
    private ActionListener dateSelectionListener; 
    /**
     * Constructs the main application frame.
     */
    public RegisterApp() {
        super("Class Registry Viewer");
        registerService = new RegisterService();
        currentClassRegister = registerService.getClassRegister(currentClassName, currentAcademicYear);
        // Initialize the GUI
        initUI();
        // Load initial data for the latest date if available
        List<LocalDate> dates = currentClassRegister.getAvailableDates();
        if (!dates.isEmpty()) {
            selectedDate = dates.get(dates.size() - 1); // Select the latest date by default
            // Temporarily remove listener to prevent programmatic selection from triggering it unnecessarily
            dateComboBox.removeActionListener(dateSelectionListener); 
            dateComboBox.setSelectedItem(selectedDate); // Set the combo box to this date programmatically
            dateComboBox.addActionListener(dateSelectionListener); // Re-add listener
            loadRegisterData(); // Now load the data based on the selectedDate
        } else {
             // Handle case where no dates exist (e.g., brand new register)
             JOptionPane.showMessageDialog(this, "No register data available for " + currentClassName + " for " + currentAcademicYear + ".", "Information", JOptionPane.INFORMATION_MESSAGE);
             // Optionally, initialize with today's date if appropriate and create empty entries
             // For now, if no dates, the table just shows empty and dateComboBox will be empty.
             // The "Add Today's Register" button can then be used.
             selectedDate = null; // Ensure selectedDate is null if no dates are loaded
        }
        // Simulate SMOS server connection interruption as described in postconditions
        SwingUtilities.invokeLater(() -> {
            // This message should ideally be separated or only shown if a connection actually failed.
            // For this use case, it's a fixed Postcondition.
            JOptionPane.showMessageDialog(this, "Connection to the SMOS server interrupted.", "Server Status", JOptionPane.INFORMATION_MESSAGE);
        });
    }
    /**
     * Initializes all GUI components and sets up the layout.
     */
    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null); // Center the window
        // Main panel with BorderLayout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        add(mainPanel);
        // --- Top Panel: Class/Year Info and Date Selection ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(new JLabel("Class: " + currentClassName + " | Academic Year: " + currentAcademicYear));
        topPanel.add(new JSeparator(SwingConstants.VERTICAL));
        topPanel.add(new JLabel("Select Date:"));
        dateComboBox = new JComboBox<>();
        populateDateComboBox();
        // Assign the lambda expression to the field
        dateSelectionListener = e -> { 
            selectedDate = (LocalDate) dateComboBox.getSelectedItem();
            loadRegisterData();
        };
        dateComboBox.addActionListener(dateSelectionListener); // Add the named listener
        topPanel.add(dateComboBox);
        mainPanel.add(topPanel, BorderLayout.NORTH);
        // --- Center Panel: Register Table ---
        List<RegisterEntry> initialEntries = (selectedDate != null) ? currentClassRegister.getRegisterForDate(selectedDate) : new ArrayList<>();
        tableModel = new RegisterTableModel(initialEntries, selectedDate);
        registerTable = new JTable(tableModel);
        registerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only one row can be selected at a time
        registerTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        JScrollPane scrollPane = new JScrollPane(registerTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // --- Bottom Panel: Action Buttons ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 15, 10));
        // Create an "Add Today's Register" feature for when no register exists for a date
        JButton addTodayRegisterButton = new JButton("Add Today's Register");
        addTodayRegisterButton.addActionListener(e -> addTodayRegister());
        buttonPanel.add(addTodayRegisterButton);
        justifyButton = new JButton("Manage Justification");
        justifyButton.addActionListener(e -> manageJustification());
        buttonPanel.add(justifyButton);
        addDisciplinaryNoteButton = new JButton("Add Disciplinary Note");
        addDisciplinaryNoteButton.addActionListener(e -> addDisciplinaryNote());
        buttonPanel.add(addDisciplinaryNoteButton);
        updateStatusButton = new JButton("Update Attendance Status");
        updateStatusButton.addActionListener(e -> updateAttendanceStatus());
        buttonPanel.add(updateStatusButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the date combo box with available dates from the `ClassRegister`.
     * Sorts dates in descending order (most recent first).
     */
    private void populateDateComboBox() {
        dateComboBox.removeAllItems(); // Clear existing items
        List<LocalDate> dates = currentClassRegister.getAvailableDates();
        dates.sort(Comparator.reverseOrder()); // Show most recent date first
        for (LocalDate date : dates) {
            dateComboBox.addItem(date);
        }
    }
    /**
     * Loads register data for the currently selected date and updates the table model.
     * If `selectedDate` is null, clears the table.
     */
    private void loadRegisterData() {
        if (selectedDate == null) {
            tableModel.updateData(new java.util.ArrayList<>(), null);
            return;
        }
        List<RegisterEntry> entries = currentClassRegister.getRegisterForDate(selectedDate);
        boolean initialEntriesEmpty = entries.isEmpty(); // Flag to remember initial state
        if (initialEntriesEmpty) { // If no entries exist for this selected date
            List<Student> studentsInClass = registerService.getStudentsForClass(currentClassName, currentAcademicYear);
            if (!studentsInClass.isEmpty()) {
                List<LocalDate> datesBeforeInit = new ArrayList<>(currentClassRegister.getAvailableDates()); // Capture existing dates
                initializeEmptyRegister(selectedDate, studentsInClass); // Populates currentClassRegister with default entries
                entries = currentClassRegister.getRegisterForDate(selectedDate); // Get the newly created entries
                // If initializing added a date that wasn't already in the combo box's source dates, repopulate
                if (!datesBeforeInit.contains(selectedDate)) {
                    dateComboBox.removeActionListener(dateSelectionListener); // Prevent re-trigger
                    populateDateComboBox(); // Repopulate to include the new date
                    dateComboBox.setSelectedItem(selectedDate); // Ensure the selectedDate is still the one shown
                    dateComboBox.addActionListener(dateSelectionListener); // Re-add listener
                }
                System.out.println("Initialized empty register for " + selectedDate);
            } else {
                JOptionPane.showMessageDialog(this, "No students found for this class. Cannot create register.", "Error", JOptionPane.ERROR_MESSAGE);
                // Clear entries if no students
                entries = new ArrayList<>();
            }
        }
        tableModel.updateData(entries, selectedDate); // Always update table with current state for selectedDate
    }
    /**
     * Initializes a register for a given date with all students marked as PRESENT.
     * This is useful when creating a register for a new date.
     * @param date The date for which to initialize the register.
     * @param students The list of students to include in the register.
     */
    private void initializeEmptyRegister(LocalDate date, List<Student> students) {
        for (Student student : students) {
            RegisterEntry newEntry = new RegisterEntry(student, AttendanceStatus.PRESENT);
            currentClassRegister.addOrUpdateEntry(date, newEntry);
        }
        // The combo box population and selection should be handled by the caller (e.g., addTodayRegister or loadRegisterData)
        // to avoid redundant listener triggers and ensure correct state management.
    }
    /**
     * Handles the action to add today's register if it doesn't already exist.
     */
    private void addTodayRegister() {
        LocalDate today = LocalDate.now();
        // Check if entries already exist for today in the underlying data model
        boolean registerExistsForToday = !currentClassRegister.getRegisterForDate(today).isEmpty();
        if (!registerExistsForToday) {
            int confirm = JOptionPane.showConfirmDialog(this, "Do you want to create a register for today (" + today.format(DateTimeFormatter.ISO_LOCAL_DATE) + ")? All students will be marked Present initially.", "Create Today's Register", JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                // Temporarily remove listener to prevent programmatic selection from triggering it
                dateComboBox.removeActionListener(dateSelectionListener);
                List<Student> studentsInClass = registerService.getStudentsForClass(currentClassName, currentAcademicYear);
                if (!studentsInClass.isEmpty()) {
                    initializeEmptyRegister(today, studentsInClass); // Populates the data model (currentClassRegister)
                    populateDateComboBox(); // Refresh items in the combo box (reads from currentClassRegister)
                    selectedDate = today; // Update internal selection state
                    dateComboBox.setSelectedItem(selectedDate); // Programmatically select the newly added date
                    loadRegisterData(); // Load table data for the now selected date
                } else {
                    JOptionPane.showMessageDialog(this, "No students found for this class. Cannot create register.", "Error", JOptionPane.ERROR_MESSAGE);
                }
                dateComboBox.addActionListener(dateSelectionListener); // Re-add listener
            }
        } else {
            JOptionPane.showMessageDialog(this, "Register for today (" + today.format(DateTimeFormatter.ISO_LOCAL_DATE) + ") already exists.", "Information", JOptionPane.INFORMATION_MESSAGE);
            // If register already exists, ensure it's selected in the UI if not already.
            if (!today.equals(selectedDate) || dateComboBox.getSelectedItem() == null || !today.equals(dateComboBox.getSelectedItem())) {
                 dateComboBox.removeActionListener(dateSelectionListener); // Temporarily remove listener
                 selectedDate = today; // Update internal state
                 dateComboBox.setSelectedItem(today); // Programmatically select
                 loadRegisterData(); // Ensure the table is updated if a different date was previously selected
                 dateComboBox.addActionListener(dateSelectionListener); // Re-add listener
            }
        }
    }
    /**
     * Shows a dialog to manage justifications for the selected student.
     */
    private void manageJustification() {
        int selectedRow = registerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a date first.", "No Date Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RegisterEntry entry = tableModel.getRegisterEntryAt(selectedRow);
        if (entry == null) return; // Should not happen with valid selectedRow
        Justification justification;
        if (entry.getJustification() != null) {
            // Edit existing justification
            JustificationDialog dialog = new JustificationDialog(this, entry.getJustification(), entry.getStudent().getName());
            dialog.setVisible(true);
            justification = dialog.getJustification();
        } else {
            // Add new justification
            JustificationDialog dialog = new JustificationDialog(this, entry.getStudent().getId(), entry.getStudent().getName(), selectedDate);
            dialog.setVisible(true);
            justification = dialog.getJustification();
        }
        if (justification != null) { // If justification was saved/updated in the dialog
            if (entry.getJustification() == null || !entry.getJustification().equals(justification)) { // Check if new or a different object reference
                 // If the dialog returned a new Justification (meaning it was created) or a modified existing one
                 // and the reference in entry might have been different initially.
                 // The service.addJustification handles both adding and implicitly updating its internal map,
                 // and setting the entry's justification reference.
                 registerService.addJustification(currentClassName, currentAcademicYear, selectedDate, justification);
                 entry.setJustification(justification); // Ensure the entry refers to the latest justification object
            } else {
                // If the Justification object was directly modified in the dialog and it was already the one in `entry`
                // (which is how `JustificationDialog` works for existing ones), then `updateJustification` keeps the service in sync.
                registerService.updateJustification(justification);
            }
            loadRegisterData(); // Refresh table to reflect changes
            JOptionPane.showMessageDialog(this, "Justification saved successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Shows a dialog to add a disciplinary note for the selected student.
     */
    private void addDisciplinaryNote() {
        int selectedRow = registerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a date first.", "No Date Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RegisterEntry entry = tableModel.getRegisterEntryAt(selectedRow);
        if (entry == null) return;
        DisciplinaryNoteDialog dialog = new DisciplinaryNoteDialog(this, entry.getStudent().getId(), entry.getStudent().getName(), selectedDate);
        dialog.setVisible(true);
        DisciplinaryNote newNote = dialog.getDisciplinaryNote();
        if (newNote != null) {
            registerService.addDisciplinaryNote(currentClassName, currentAcademicYear, selectedDate, entry.getStudent().getId(), newNote.getDescription());
            loadRegisterData(); // Refresh table to show updated notes
            JOptionPane.showMessageDialog(this, "Disciplinary note added successfully.", "Success", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Allows updating the attendance status of the selected student.
     */
    private void updateAttendanceStatus() {
        int selectedRow = registerTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this, "Please select a student from the table.", "No Student Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        if (selectedDate == null) {
            JOptionPane.showMessageDialog(this, "Please select a date first.", "No Date Selected", JOptionPane.WARNING_MESSAGE);
            return;
        }
        RegisterEntry entry = tableModel.getRegisterEntryAt(selectedRow);
        if (entry == null) return;
        AttendanceStatus[] statuses = AttendanceStatus.values();
        AttendanceStatus currentStatus = entry.getStatus();
        // Show input dialog to select new status
        AttendanceStatus newStatus = (AttendanceStatus) JOptionPane.showInputDialog(
            this,
            "Select new attendance status for " + entry.getStudent().getName() + ":",
            "Update Status",
            JOptionPane.QUESTION_MESSAGE,
            null,
            statuses,
            currentStatus // Pre-select current status
        );
        if (newStatus != null && newStatus != currentStatus) {
            entry.setStatus(newStatus);
            registerService.saveRegisterEntry(currentClassName, currentAcademicYear, selectedDate, entry); // Save the updated entry
            loadRegisterData(); // Refresh table
            JOptionPane.showMessageDialog(this, "Attendance status updated to " + newStatus + ".", "Status Updated", JOptionPane.INFORMATION_MESSAGE);
        } else if (newStatus == currentStatus) {
            JOptionPane.showMessageDialog(this, "Attendance status is already " + currentStatus + ".", "No Change", JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new RegisterApp().setVisible(true);
        });
    }
}