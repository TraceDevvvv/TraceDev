'''
This JPanel serves as the main user interface for the "Direction" role
to view digital registers. It allows selecting an academic year,
initiating a search, and displaying the results in a table.
It also handles simulated server connection interruptions.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector; // Deprecated by comment, but still in imports if not removed explicitly
public class DigitalRegisterViewer extends JPanel {
    private RegisterService registerService; // Service for data retrieval
    private JComboBox<Integer> academicYearComboBox; // Dropdown for year selection
    private JButton viewRegistersButton; // Button to trigger search
    private JTable registersTable; // Table to display register list
    private DefaultTableModel tableModel; // Model for the JTable
    private JTextArea statusArea; // Area to display messages (e.g., errors, status)
    /**
     * Constructs the DigitalRegisterViewer panel.
     * Initializes the RegisterService and sets up all GUI components.
     * Adds action listeners for interactive elements.
     */
    public DigitalRegisterViewer() {
        registerService = new RegisterService(); // Initialize the service layer
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        initUI(); // Initialize all graphical components
        populateAcademicYears(); // Fill the academic year combo box
        addListeners(); // Add event listeners to buttons and combo box
    }
    /**
     * Initializes and lays out all the GUI components on the panel.
     */
    private void initUI() {
        // --- Top Panel for Year Selection ---
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(new JLabel("Select Academic Year:"));
        academicYearComboBox = new JComboBox<>();
        topPanel.add(academicYearComboBox);
        viewRegistersButton = new JButton("View Registers");
        topPanel.add(viewRegistersButton);
        add(topPanel, BorderLayout.NORTH);
        // --- Center Panel for Register Table ---
        String[] columnNames = {"Register ID", "Academic Year", "Class Name", "Subject", "Teacher"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        registersTable = new JTable(tableModel);
        registersTable.setFillsViewportHeight(true); // Table will fill the height of its scroll pane
        registersTable.getTableHeader().setReorderingAllowed(false); // Prevent column reordering
        JScrollPane scrollPane = new JScrollPane(registersTable);
        add(scrollPane, BorderLayout.CENTER);
        // --- Bottom Panel for Status Messages ---
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusArea = new JTextArea(3, 40); // 3 rows, 40 columns for status messages
        statusArea.setEditable(false); // Make it read-only
        statusArea.setLineWrap(true);
        statusArea.setWrapStyleWord(true); // Wrap text at word boundaries
        JScrollPane statusScrollPane = new JScrollPane(statusArea);
        bottomPanel.add(new JLabel("Status/Messages:"), BorderLayout.NORTH);
        bottomPanel.add(statusScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the academic year combo box with distinct years available in the mock database.
     */
    private void populateAcademicYears() {
        List<Integer> years = registerService.getAvailableAcademicYears();
        for (Integer year : years) {
            academicYearComboBox.addItem(year);
        }
        if (!years.isEmpty()) {
            academicYearComboBox.setSelectedIndex(years.size() - 1); // Select the latest academic year by default
        }
    }
    /**
     * Adds action listeners to the "View Registers" button and the academic year combo box.
     */
    private void addListeners() {
        viewRegistersButton.addActionListener(e -> viewRegisters());
        // Optionally, trigger viewRegisters when a selection is made in the combo box as well
        // academicYearComboBox.addActionListener(e -> viewRegisters());
    }
    /**
     * Initiates the process of viewing registers for the selected academic year.
     * This method is called when the "View Registers" button is clicked.
     * It handles data retrieval from the service and updates the UI accordingly,
     * including error handling for simulated connection disruptions.
     */
    private void viewRegisters() {
        Integer selectedYear = (Integer) academicYearComboBox.getSelectedItem();
        if (selectedYear == null) {
            displayStatus("Please select an academic year.", true);
            return;
        }
        displayStatus("Searching for registers for academic year " + selectedYear + "...", false);
        // Disable UI elements during search to prevent concurrent operations
        viewRegistersButton.setEnabled(false);
        academicYearComboBox.setEnabled(false);
        tableModel.setRowCount(0); // Clear previous results
        // Perform the search operation in a background thread to keep the UI responsive
        SwingWorker<List<AcademicRegister>, Void> worker = new SwingWorker<List<AcademicRegister>, Void>() {
            @Override
            protected List<AcademicRegister> doInBackground() throws Exception {
                try {
                    return registerService.searchRegistersByAcademicYear(selectedYear);
                } catch (SMOSConnectionException e) {
                    // Re-throw as a generic Exception to be caught by done()
                    throw new Exception(e.getMessage(), e);
                }
            }
            @Override
            protected void done() {
                try {
                    List<AcademicRegister> registers = get(); // Get the result from doInBackground
                    updateTable(registers); // Update the table with the retrieved data
                    if (registers.isEmpty()) {
                        displayStatus("No digital registers found for academic year " + selectedYear + ".", false);
                    } else {
                        displayStatus("Successfully displayed " + registers.size() +
                                " registers for academic year " + selectedYear + ".", false);
                    }
                } catch (Exception ex) {
                    // Handle exceptions (e.g., SMOSConnectionException) that might have occurred
                    if (ex.getCause() instanceof SMOSConnectionException) {
                        displayStatus("Error: " + ex.getCause().getMessage() +
                                " (This simulates server interruption)", true);
                    } else {
                        displayStatus("An unexpected error occurred: " + ex.getMessage(), true);
                    }
                    updateTable(new java.util.ArrayList<>()); // Clear table on error
                } finally {
                    // Re-enable UI elements regardless of success or failure
                    viewRegistersButton.setEnabled(true);
                    academicYearComboBox.setEnabled(true);
                }
            }
        };
        worker.execute(); // Start the background worker
    }
    /**
     * Updates the JTable with the provided list of AcademicRegister objects.
     * Clears existing rows and adds new rows based on the list.
     *
     * @param registers The list of AcademicRegister objects to display.
     */
    private void updateTable(List<AcademicRegister> registers) {
        tableModel.setRowCount(0); // Clear existing rows
        if (registers != null && !registers.isEmpty()) {
            for (AcademicRegister register : registers) {
                // Create an Object array for each row's data, which is more idiomatic for JTable rows
                Object[] row = new Object[5]; // Assuming 5 columns based on initUI
                row[0] = register.getId();
                row[1] = register.getAcademicYear();
                row[2] = register.getClassName();
                row[3] = register.getSubject();
                row[4] = register.getTeacherName();
                tableModel.addRow(row); // Add the row to the table model
            }
        }
    }
    /**
     * Displays a status message in the statusArea.
     *
     * @param message The message to display.
     * @param isError If true, the message is treated as an error and its color might be set to red.
     */
    private void displayStatus(String message, boolean isError) {
        statusArea.setForeground(isError ? Color.RED : Color.BLACK); // Set text color based on error status
        statusArea.setText(message); // Set the message
    }
}