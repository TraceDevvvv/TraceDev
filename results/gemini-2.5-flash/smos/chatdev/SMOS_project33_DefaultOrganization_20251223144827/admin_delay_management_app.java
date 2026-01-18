/*
AdminDelayManagementApp.java
 DOCSTRING
    This is the main application class for the Administrator's Delay Management system.
    It provides a graphical user interface (GUI) for selecting a date,
    displaying student delays for that date, marking delays for removal,
    and deleting them permanently from a simulated archive.
    It leverages Swing for the GUI components.
*/
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.Vector;
public class AdminDelayManagementApp extends JFrame {
    private DelayArchive delayArchive; // Backend service for managing delays
    private JComboBox<LocalDate> dateSelector; // Dropdown to select dates
    private JTable delayTable; // Table to display delays
    private DefaultTableModel tableModel; // Model for the JTable
    private JLabel statusLabel; // For displaying messages to the user
    private JButton selectDateButton;
    private JButton removeDelayButton;
    private JButton saveChangesButton;
    private JButton logoutButton; // To simulate administrator interrupting operation
    // Add this field (e.g., near other JButtons)
    private JCheckBox smosConnectionToggle;
    private LocalDate currentlySelectedDate; // Stores the date currently displayed
    // Stores delays that are marked for deletion from the UI but not yet saved to the archive
    private Set<Delay> delaysMarkedForDeletion; 
    public AdminDelayManagementApp() {
        // Initialize the archive and other components
        delayArchive = new DelayArchive();
        delaysMarkedForDeletion = new HashSet<>();
        currentlySelectedDate = null; // No date selected initially
        // --- JFrame Setup ---
        setTitle("Administrator Delay Management");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // --- Header Panel (Date Selection) ---
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 10));
        headerPanel.setBorder(BorderFactory.createTitledBorder("Select Date"));
        dateSelector = new JComboBox<>();
        populateDateSelector(); // Fill the combobox with dates from the archive
        // --- ADDED LOGIC FOR INITIAL DISPLAY ---
        // Automatically display delays for the first date if available after population
        if (dateSelector.getItemCount() > 0) {
            currentlySelectedDate = (LocalDate) dateSelector.getSelectedItem();
            updateDelayTable(currentlySelectedDate);
            statusLabel.setText("Delays for: " + currentlySelectedDate);
        } else {
            statusLabel.setText("No delays available in the archive.");
        }
        // --- END ADDED LOGIC ---
        selectDateButton = new JButton("Display Delays");
        selectDateButton.addActionListener(e -> {
            LocalDate selectedDate = (LocalDate) dateSelector.getSelectedItem();
            if (selectedDate != null) {
                updateDelayTable(selectedDate);
                currentlySelectedDate = selectedDate;
                statusLabel.setText("Delays for: " + selectedDate);
                // Clear any pending deletions when a new date is selected
                delaysMarkedForDeletion.clear(); 
            } else {
                statusLabel.setText("No date selected or no delays available.");
                // Clear table if no date is selected
                tableModel.setRowCount(0); 
                currentlySelectedDate = null;
            }
            updateButtonStates(); // Update button enable/disable status
        });
        headerPanel.add(new JLabel("Date:"));
        headerPanel.add(dateSelector);
        headerPanel.add(selectDateButton);
        // Add the checkbox to control SMOS server connection simulation
        smosConnectionToggle = new JCheckBox("SMOS Server Connected (Simulated)");
        smosConnectionToggle.setSelected(delayArchive.isSMOSServerConnected()); // Initialize with current status
        smosConnectionToggle.addActionListener(e -> {
            delayArchive.setSMOSServerConnected(smosConnectionToggle.isSelected());
            statusLabel.setText("SMOS Server connection simulated as: " + (smosConnectionToggle.isSelected() ? "Connected" : "Interrupted"));
            updateButtonStates(); // Re-evaluate button states if needed
        });
        headerPanel.add(smosConnectionToggle); // Add to header or another suitable panel
        add(headerPanel, BorderLayout.NORTH);
        // --- Main Content Panel (Table of Delays) ---
        String[] columnNames = {"ID", "Student Name", "Date", "Reason"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells uneditable
            }
        };
        delayTable = new JTable(tableModel);
        delayTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        JScrollPane scrollPane = new JScrollPane(delayTable);
        add(scrollPane, BorderLayout.CENTER);
        // --- Button Panel (Actions) ---
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        removeDelayButton = new JButton("Remove Selected Delay");
        removeDelayButton.addActionListener(e -> removeSelectedDelay());
        saveChangesButton = new JButton("Save Changes");
        saveChangesButton.addActionListener(e -> saveChanges());
        logoutButton = new JButton("Logout (Interrupt)");
        logoutButton.addActionListener(e -> {
            // As per use case postcondition: "The administrator interrupts the operation • Connection to the SMOS server interrupted"
            // When the administrator interrupts (logs out), the SMOS server connection is considered interrupted.           
            delayArchive.setSMOSServerConnected(false); // Explicitly set to interrupted
            smosConnectionToggle.setSelected(false); // Visually update the toggle
            JOptionPane.showMessageDialog(this, "Administrator interrupted operation. SMOS server connection simulated as interrupted. Logging out.", "Interrupted", JOptionPane.WARNING_MESSAGE);
            // Simulate logout by returning to LoginScreen
            new LoginScreen().setVisible(true);
            dispose(); // Close current window
        });
        buttonPanel.add(removeDelayButton);
        buttonPanel.add(saveChangesButton);
        buttonPanel.add(logoutButton);
        add(buttonPanel, BorderLayout.EAST); // Place buttons on the right
        // --- Status Panel ---
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Please select a date to view delays.");
        statusPanel.add(statusLabel);
        add(statusPanel, BorderLayout.SOUTH);
        // Initial state of buttons
        updateButtonStates();
    }
    /**
     * Populates the date selector JComboBox with dates that have delays.
     */
    private void populateDateSelector() {
        dateSelector.removeAllItems(); // Clear existing items
        List<LocalDate> datesWithDelays = delayArchive.getAllDatesWithDelays();
        for (LocalDate date : datesWithDelays) {
            dateSelector.addItem(date);
        }
        if (!datesWithDelays.isEmpty()) {
            dateSelector.setSelectedIndex(0); // Select the first date by default
        }
    }
    /**
     * Updates the delay table with delays for the given date.
     * @param date The date for which to display delays.
     */
    private void updateDelayTable(LocalDate date) {
        // Clear existing table data
        tableModel.setRowCount(0); 
        // Get delays from the archive
        List<Delay> delays = delayArchive.getDelaysByDate(date);
        // Add only delays that are not marked for deletion to the current view
        for (Delay delay : delays) {
            if (!delaysMarkedForDeletion.contains(delay)) {
                 tableModel.addRow(new Object[]{
                    delay.getId(),
                    delay.getStudentName(),
                    delay.getDate(),
                    delay.getReason()
                });
            }
        }
        if (tableModel.getRowCount() == 0) {
            statusLabel.setText("No delays found for " + date + ".");
        }
    }
     /**
     * Handles the action of removing a selected delay from the UI table.
     * The delay is marked for deletion and visually removed, but not yet
     * deleted from the permanent archive until 'Save Changes' is clicked.
     */
    private void removeSelectedDelay() {
        int selectedRow = delayTable.getSelectedRow();
        if (selectedRow >= 0) {
            // Get data of the selected row
            int id = (int) tableModel.getValueAt(selectedRow, 0);
            String studentName = (String) tableModel.getValueAt(selectedRow, 1);
            LocalDate date = (LocalDate) tableModel.getValueAt(selectedRow, 2);
            String reason = (String) tableModel.getValueAt(selectedRow, 3);
            Delay removedDelay = new Delay(id, studentName, date, reason);
            delaysMarkedForDeletion.add(removedDelay); // Add to set of delays to be deleted
            tableModel.removeRow(selectedRow); // Remove from UI
            statusLabel.setText("Delay for " + studentName + " marked for removal. Click 'Save Changes' to confirm.");
            updateButtonStates();
        } else {
            JOptionPane.showMessageDialog(this, "Please select a delay to remove.", "No Selection", JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Commits all delays marked for deletion to the DelayArchive.
     * This simulates deleting the entries from the persistent storage.
     */
    private void saveChanges() {
        if (!delayArchive.isSMOSServerConnected()) {
            // Simulates the "Connection to the SMOS server interrupted" postcondition
            JOptionPane.showMessageDialog(this,
                    "Connection to the SMOS server interrupted. Cannot save changes.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        if (delaysMarkedForDeletion.isEmpty()) {
            statusLabel.setText("No changes to save.");
            return;
        }
        List<String> successfullyDeleted = new ArrayList<>();
        List<String> failedToDelete = new ArrayList<>();
        for (Delay delay : delaysMarkedForDeletion) {
            // Attempt to delete from the archive
            boolean deleted = delayArchive.deleteDelay(delay.getDate(), delay.getId());
            if (deleted) {
                successfullyDeleted.add(delay.getStudentName() + " (ID: " + delay.getId() + ")");
            } else {
                failedToDelete.add(delay.getStudentName() + " (ID: " + delay.getId() + ")");
            }
        }
        // Clear the list of marked-for-deletion delays after attempting to save
        delaysMarkedForDeletion.clear();
        // Update status and re-populate date selector if any dates became empty
        if (!successfullyDeleted.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Successfully deleted: " + String.join(", ", successfullyDeleted),
                "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            statusLabel.setText(successfullyDeleted.size() + " delay(s) deleted. System remains on registry screen.");
            // Re-populate date selector as some dates might no longer have delays
            populateDateSelector(); 
            // Re-display delays for the current date to reflect permanent changes
            if (currentlySelectedDate != null) {
                // Check if the currently selected date still has delays in the archive
                if (delayArchive.getAllDatesWithDelays().contains(currentlySelectedDate)) {
                    updateDelayTable(currentlySelectedDate); 
                } else {
                    // Current date no longer has delays, select the first available date or clear
                    List<LocalDate> remainingDates = delayArchive.getAllDatesWithDelays();
                    if (!remainingDates.isEmpty()) {
                         currentlySelectedDate = remainingDates.get(0);
                         dateSelector.setSelectedItem(currentlySelectedDate);
                         updateDelayTable(currentlySelectedDate);
                    } else {
                        tableModel.setRowCount(0);
                        currentlySelectedDate = null;
                        statusLabel.setText("All delays eliminated. No delays left in the archive.");
                    }
                }
            } else if (!delayArchive.getAllDatesWithDelays().isEmpty()) {
                 // If current date was null and delays were deleted, refresh with a new initial date
                LocalDate newSelectedDate = delayArchive.getAllDatesWithDelays().get(0);
                dateSelector.setSelectedItem(newSelectedDate);
                updateDelayTable(newSelectedDate);
                currentlySelectedDate = newSelectedDate;
            } else {
                tableModel.setRowCount(0); // If no delays left at all
                statusLabel.setText("All delays eliminated. No delays left in the archive.");
                currentlySelectedDate = null; // No date selected
            }
        }
        if (!failedToDelete.isEmpty()) {
            JOptionPane.showMessageDialog(this, 
                "Failed to delete: " + String.join(", ", failedToDelete) + ". Check logs.",
                "Deletion Failed", JOptionPane.WARNING_MESSAGE);
        }
        updateButtonStates();
    }
    /**
     * Updates the enabled/disabled state of buttons based on current application state.
     */
    private void updateButtonStates() {
        boolean dateSelected = currentlySelectedDate != null;
        boolean hasRowsInTable = tableModel.getRowCount() > 0;
        boolean hasPendingDeletions = !delaysMarkedForDeletion.isEmpty();
        selectDateButton.setEnabled(dateSelector.getItemCount() > 0);
        // removeDelayButton should be enabled if a date is selected and there are rows in the table
        removeDelayButton.setEnabled(dateSelected && hasRowsInTable);
        // The saveChanges button should only be enabled if there are pending deletions AND the SMOS server is connected
        saveChangesButton.setEnabled(hasPendingDeletions && delayArchive.isSMOSServerConnected());
    }
}