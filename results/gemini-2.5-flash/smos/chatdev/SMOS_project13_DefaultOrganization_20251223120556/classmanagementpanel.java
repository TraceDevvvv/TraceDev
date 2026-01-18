'''
A JPanel that serves as the main screen for class management.
It allows administrators to select an academic year and view a list of classes
for that year. It also includes functionality to simulate connection interruption
to the data service.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.Vector;
public class ClassManagementPanel extends JPanel {
    private JComboBox<String> yearComboBox;
    private JTable classTable;
    private DefaultTableModel tableModel;
    private JScrollPane tableScrollPane;
    private ClassDataService classDataService;
    private JButton toggleConnectionButton;
    private JLabel statusLabel;
    private boolean noYearsAvailable = false; // Flag to track if no years were found
    /**
     * Constructs a new ClassManagementPanel.
     * Initializes the data service, sets up the UI components (year selector,
     * class table, buttons, status label), and loads initial data.
     */
    public ClassManagementPanel() {
        classDataService = new ClassDataService();
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // --- North Panel: Year Selection and Controls ---
        JPanel northPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        northPanel.add(new JLabel("Select Academic Year:"));
        yearComboBox = new JComboBox<>();
        populateYearComboBox(); // Populates with available years, setting noYearsAvailable flag if needed
        // Simulate Connection Interruption Button
        toggleConnectionButton = new JButton("Simulate SMOS Server Disconnect");
        toggleConnectionButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                toggleSmosServerConnection();
            }
        });
        // Add components to the north panel
        northPanel.add(yearComboBox);
        northPanel.add(toggleConnectionButton);
        add(northPanel, BorderLayout.NORTH);
        // --- Center Panel: Class List Table ---
        // Initialize table model with column headers
        String[] columnNames = {"ID", "Name", "Academic Year", "Instructor"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        classTable = new JTable(tableModel);
        classTable.setFillsViewportHeight(true); // Fills the table to the height of its scroll pane
        tableScrollPane = new JScrollPane(classTable); // Add table to scroll pane for scrollability
        add(tableScrollPane, BorderLayout.CENTER);
        // --- South Panel: Status Bar ---
        JPanel southPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        statusLabel = new JLabel("Application Ready.");
        southPanel.add(statusLabel);
        add(southPanel, BorderLayout.SOUTH);
        // Apply logic based on whether years were found
        if (noYearsAvailable) {
            statusLabel.setText("No academic years found in the system. No classes can be viewed.");
            yearComboBox.setEnabled(false); // Disable selection
            toggleConnectionButton.setEnabled(false); // Disable connection toggle
            JOptionPane.showMessageDialog(this,
                    "No academic years currently available. Please ensure classes are configured in the system.",
                    "No Data Available",
                    JOptionPane.INFORMATION_MESSAGE);
            // Additionally, clear the table just in case it somehow got headers without data
            tableModel.setRowCount(0);
        } else {
            // If years are available, add the listener and load initial data
            yearComboBox.addActionListener(new ActionListener() {
                @Override
                public void actionPerformed(ActionEvent e) {
                    String selectedYear = (String) yearComboBox.getSelectedItem();
                    // Only load if a year is actually selected and connection is not interrupted
                    if (selectedYear != null && !classDataService.isConnectionInterrupted()) {
                        loadClasses(selectedYear);
                    } else if (classDataService.isConnectionInterrupted()) {
                        statusLabel.setText("SMOS server connection interrupted. Cannot load new selection.");
                    }
                }
            });
            // Select the first year and load its classes
            if (yearComboBox.getItemCount() > 0) { // Safety check, though !noYearsAvailable already implies this
                yearComboBox.setSelectedIndex(0);
                loadClasses((String) yearComboBox.getSelectedItem());
            }
        }
    }
    /**
     * Populates the academic year combo box with distinct years available from the ClassDataService.
     * Sets a flag if no years are available.
     */
    private void populateYearComboBox() {
        List<String> years = classDataService.getAvailableAcademicYears();
        if (years.isEmpty()) {
            noYearsAvailable = true;
            // No items are added to the combo box if no years are available
        } else {
            for (String year : years) {
                yearComboBox.addItem(year);
            }
        }
    }
    /**
     * Toggles the simulated connection status to the SMOS server.
     * Updates the button text and attempts to reload classes if the connection is restored.
     */
    private void toggleSmosServerConnection() {
        boolean currentStatus = classDataService.isConnectionInterrupted();
        classDataService.setConnectionInterrupted(!currentStatus);
        if (classDataService.isConnectionInterrupted()) {
            toggleConnectionButton.setText("Restore SMOS Server Connection");
            statusLabel.setText("SMOS server connection interrupted.");
            // Clear table immediately to reflect interruption
            tableModel.setRowCount(0);
        } else {
            toggleConnectionButton.setText("Simulate SMOS Server Disconnect");
            statusLabel.setText("SMOS server connection restored.");
            // Try to reload classes upon restoring connection, but only if academic years are available
            if (!noYearsAvailable) {
                String selectedYear = (String) yearComboBox.getSelectedItem();
                if (selectedYear != null) {
                    loadClasses(selectedYear);
                }
            } else {
                statusLabel.setText("SMOS server connection restored, but no academic years available to load classes.");
            }
        }
    }
    /**
     * Loads classes for the specified academic year from the ClassDataService
     * and updates the JTable to display the fetched data.
     * Handles potential connection interruption errors gracefully.
     * This method simulates the system searching for classes in the archive
     * and displaying the class management screen with the updated list.
     *
     * @param academicYear The academic year for which to load classes.
     */
    private void loadClasses(String academicYear) {
        // Clear previous table data
        tableModel.setRowCount(0);
        statusLabel.setText("Loading classes for " + academicYear + "...");
        // Use SwingWorker for background operations to keep UI responsive
        new SwingWorker<List<AcademicClass>, Void>() {
            @Override
            protected List<AcademicClass> doInBackground() throws Exception {
                // This runs on a background thread
                return classDataService.fetchClassesByYear(academicYear);
            }
            @Override
            protected void done() {
                // This runs on the Event Dispatch Thread (EDT)
                try {
                    List<AcademicClass> classes = get(); // Get the result from doInBackground
                    for (AcademicClass clazz : classes) {
                        Vector<Object> row = new Vector<>();
                        row.add(clazz.getId());
                        row.add(clazz.getName());
                        row.add(clazz.getAcademicYear());
                        row.add(clazz.getInstructor());
                        tableModel.addRow(row);
                    }
                    statusLabel.setText("Displayed " + classes.size() + " classes for " + academicYear + ".");
                    // Postcondition: The system shows the list of classes of the selected academic year.
                    // This directly fulfills the postcondition
                } catch (Exception ex) {
                    if (classDataService.isConnectionInterrupted()) {
                        statusLabel.setText("Connection to SMOS server interrupted. Cannot load classes.");
                        JOptionPane.showMessageDialog(ClassManagementPanel.this,
                                "Error: " + ex.getCause().getMessage(),
                                "Connection Error",
                                JOptionPane.ERROR_MESSAGE);
                        // Postcondition: Connection to the SMOS server interrupted.
                    } else {
                        statusLabel.setText("Failed to load classes: " + ex.getCause().getMessage());
                        JOptionPane.showMessageDialog(ClassManagementPanel.this,
                                "An unexpected error occurred: " + ex.getCause().getMessage(),
                                "Error",
                                JOptionPane.ERROR_MESSAGE);
                    }
                }
            }
        }.execute(); // Execute the SwingWorker
    }
}