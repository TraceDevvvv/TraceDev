'''
JPanel for displaying academic registers.
It allows the administrator to select an academic year and then displays
all digital registers associated with that year in a JTable.
It also includes buttons to simulate connection/disconnection to a backend
service (SMOS server) to demonstrate handling that postcondition.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
import java.util.Vector;
public class DigitalRegisterPanel extends JPanel {
    private JLabel selectedYearLabel;
    private JButton selectYearButton;
    private JTable registerTable;
    private DefaultTableModel tableModel;
    private RegisterService registerService;
    private int currentSelectedAcademicYear = -1; // Stores the currently selected year
    private JButton connectSMOSButton;
    private JButton disconnectSMOSButton;
    private JLabel connectionStatusLabel;
    /**
     * Constructs the DigitalRegisterPanel.
     *
     * @param registerService An instance of RegisterService to fetch register data.
     */
    public DigitalRegisterPanel(RegisterService registerService) {
        this.registerService = registerService;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall layout
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        initUI(); // Initialize the user interface components
        updateConnectionStatus(); // Set initial connection status display
    }
    /**
     * Initializes all UI components within the panel.
     */
    private void initUI() {
        // --- Top Panel for Year Selection and Connection Status ---
        JPanel topPanel = new JPanel();
        topPanel.setLayout(new BorderLayout());
        // Left side of topPanel: Year Selection
        JPanel yearSelectionPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        selectedYearLabel = new JLabel("Selected Academic Year: N/A");
        selectedYearLabel.setFont(new Font("SansSerif", Font.BOLD, 14));
        selectYearButton = new JButton("Select Academic Year");
        selectYearButton.addActionListener(e -> showAcademicYearSelectionDialog());
        yearSelectionPanel.add(selectedYearLabel);
        yearSelectionPanel.add(selectYearButton);
        topPanel.add(yearSelectionPanel, BorderLayout.WEST);
        // Right side of topPanel: SMOS Connection Controls
        JPanel connectionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        connectionStatusLabel = new JLabel("SMOS Connection: Unknown");
        connectionStatusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        connectSMOSButton = new JButton("Connect to SMOS");
        connectSMOSButton.addActionListener(e -> {
            registerService.connectSMOS();
            updateConnectionStatus();
            // If a year was previously selected, try to re-fetch
            if (currentSelectedAcademicYear != -1) {
                fetchAndDisplayRegisters(currentSelectedAcademicYear);
            }
        });
        disconnectSMOSButton = new JButton("Disconnect from SMOS");
        disconnectSMOSButton.addActionListener(e -> {
            registerService.disconnectSMOS();
            updateConnectionStatus();
            // Clear displayed data when disconnected
            clearRegisterTable();
            JOptionPane.showMessageDialog(this,
                    "SMOS server connection interrupted.",
                    "Connection Interrupted",
                    JOptionPane.INFORMATION_MESSAGE);
            // After disconnection, reset the selected year state
            currentSelectedAcademicYear = -1;
            selectedYearLabel.setText("Selected Academic Year: N/A");
        });
        connectionPanel.add(connectionStatusLabel);
        connectionPanel.add(connectSMOSButton);
        connectionPanel.add(disconnectSMOSButton);
        topPanel.add(connectionPanel, BorderLayout.EAST);
        add(topPanel, BorderLayout.NORTH);
        // --- Center Panel for Register Table ---
        // Initialize table model with column names
        String[] columnNames = {"ID", "Academic Year", "Class Name", "Subject", "Date", "Content"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        registerTable = new JTable(tableModel);
        registerTable.setFillsViewportHeight(true); // Table uses full height of scroll pane
        registerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        JScrollPane scrollPane = new JScrollPane(registerTable); // Add table to a scroll pane
        add(scrollPane, BorderLayout.CENTER); // Place scroll pane in the center
    }
    /**
     * Shows a dialog for the administrator to select an academic year.
     * Upon selection, triggers fetching and displaying of registers.
     */
    private void showAcademicYearSelectionDialog() {
        // Ensure connected before allowing year selection
        if (!registerService.isConnected()) {
            JOptionPane.showMessageDialog(this,
                    "Please connect to the SMOS server first.",
                    "Connection Required",
                    JOptionPane.WARNING_MESSAGE);
            return;
        }
        AcademicYearSelectionDialog dialog = new AcademicYearSelectionDialog(SwingUtilities.getWindowAncestor(this));
        dialog.setVisible(true);
        if (dialog.getSelectedYear() != null) {
            currentSelectedAcademicYear = dialog.getSelectedYear();
            selectedYearLabel.setText("Selected Academic Year: " + currentSelectedAcademicYear);
            fetchAndDisplayRegisters(currentSelectedAcademicYear);
        }
    }
    /**
     * Fetches registers for the given academic year from the RegisterService
     * and displays them in the JTable.
     *
     * @param academicYear The academic year for which to fetch registers.
     */
    private void fetchAndDisplayRegisters(int academicYear) {
        clearRegisterTable(); // Clear existing data first
        try {
            List<Register> registers = registerService.getRegistersByAcademicYear(academicYear);
            if (registers.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                        "No digital registers found for academic year " + academicYear + ".",
                        "No Records Found",
                        JOptionPane.INFORMATION_MESSAGE);
            } else {
                displayRegisters(registers);
            }
        } catch (IllegalStateException ex) {
            JOptionPane.showMessageDialog(this,
                    ex.getMessage() + "\nPlease connect to SMOS server first.",
                    "Connection Error",
                    JOptionPane.ERROR_MESSAGE);
            // After an error, clear the current selected year from the label
            selectedYearLabel.setText("Selected Academic Year: N/A");
            currentSelectedAcademicYear = -1;
            // Also update connection status as it might have become disconnected internally if that logic were more complex
            updateConnectionStatus();
        } catch (Exception ex) {
            JOptionPane.showMessageDialog(this,
                    "An unexpected error occurred: " + ex.getMessage(),
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
    /**
     * Populates the JTable with the provided list of Register objects.
     *
     * @param registers The list of Register objects to display.
     */
    private void displayRegisters(List<Register> registers) {
        for (Register reg : registers) {
            // Create a row vector from Register object properties
            Vector<Object> row = new Vector<>();
            row.add(reg.getId());
            row.add(reg.getAcademicYear());
            row.add(reg.getClassName());
            row.add(reg.getSubject());
            row.add(reg.getDate());
            row.add(reg.getContent());
            tableModel.addRow(row); // Add the row to the table model
        }
    }
    /**
     * Clears all rows from the JTable.
     */
    private void clearRegisterTable() {
        tableModel.setRowCount(0); // Removes all rows
    }
    /**
     * Updates the connection status label and button states based on the RegisterService's connection state.
     */
    private void updateConnectionStatus() {
        if (registerService.isConnected()) {
            connectionStatusLabel.setText("SMOS Connection: Connected");
            connectionStatusLabel.setForeground(Color.BLUE);
            connectSMOSButton.setEnabled(false);
            disconnectSMOSButton.setEnabled(true);
            selectYearButton.setEnabled(true); // Enable year selection when connected
        } else {
            connectionStatusLabel.setText("SMOS Connection: Disconnected");
            connectionStatusLabel.setForeground(Color.RED);
            connectSMOSButton.setEnabled(true);
            disconnectSMOSButton.setEnabled(false);
            selectYearButton.setEnabled(false); // Disable year selection when disconnected
        }
    }
}