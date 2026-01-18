'''
Represents the main GUI application for parents to view their children's data.
This class handles the display of student records in a tabular format using Swing.
'''
package gui;
import model.ParentUser;
import model.Student;
import model.StudentRecord;
import service.StudentDataService;
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
/**
 * Represents the main GUI application for parents to view their children's data.
 * This class handles the display of student records in a tabular format using Swing.
 */
public class StudentDataViewer extends JFrame {
    private StudentDataService dataService;
    private JComboBox<Student> childrenComboBox;
    private JTable dataTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    /**
     * Constructor for the StudentDataViewer application.
     * Initializes the data service and sets up the GUI components.
     */
    public StudentDataViewer() {
        super("View Student Data");
        // Initialize the data service which also sets up mock parent and student data.
        this.dataService = new StudentDataService();
        initializeGUI();
        // Load data for the first child by default upon application startup.
        // This simulates the "Register" button being clicked for one of his children implicitly.
        loadInitialData();
    }
    /**
     * Initializes all GUI components and lays them out in the window.
     */
    private void initializeGUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window
        // Main panel with BorderLayout for overall layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding around the edges
        add(mainPanel);
        // Top panel for child selection controls
        JPanel topPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        topPanel.add(new JLabel("Select Child:"));
        // Retrieve the logged-in parent from the data service
        ParentUser parent = dataService.getLoggedInParent();
        if (parent == null) {
            // If no parent is "logged in" (mocked), display an error and exit.
            JOptionPane.showMessageDialog(this, "No parent found. Please ensure login is successful.", "Login Error", JOptionPane.ERROR_MESSAGE);
            System.exit(1);
            return;
        }
        // Populate JComboBox with the children associated with the logged-in parent
        childrenComboBox = new JComboBox<>(parent.getChildren().toArray(new Student[0]));
        // Add an action listener to load data when a different child is selected from the combo box
        // This handles the "User clicks the 'Register' button associated with one of his children"
        // by immediately displaying data upon selection.
        childrenComboBox.addActionListener(e -> {
            Student selectedStudent = (Student) childrenComboBox.getSelectedItem();
            if (selectedStudent != null) {
                loadStudentData(selectedStudent);
            }
        });
        topPanel.add(childrenComboBox);
        // The "View Details" button is removed as the JComboBox's action listener
        // already handles displaying data upon selection, making the button redundant.
        mainPanel.add(topPanel, BorderLayout.NORTH); // Add the selection panel to the top
        // Table for displaying student data summary
        String[] columnNames = {"Date", "Absent", "Disciplinary Notes", "Delays", "Justification"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all table cells non-editable
            }
        };
        dataTable = new JTable(tableModel);
        dataTable.setFillsViewportHeight(true); // Table will expand to fill the height of its scroll pane
        JScrollPane scrollPane = new JScrollPane(dataTable); // Provides scrollability for the table
        mainPanel.add(scrollPane, BorderLayout.CENTER); // Add the table to the center
        // Status label to show SMOS server connection status as per postcondition
        statusLabel = new JLabel("SMOS Server Status: Initializing...");
        statusLabel.setHorizontalAlignment(SwingConstants.RIGHT); // Align text to the right
        mainPanel.add(statusLabel, BorderLayout.SOUTH); // Add the status bar to the bottom
        // Set the initial status of the SMOS server.
        updateSmosServerStatus();
        setVisible(true); // Make the JFrame visible
    }
    /**
     * Loads the initial student data when the application starts.
     * It selects the first child in the combo box and displays their data.
     */
    private void loadInitialData() {
        if (childrenComboBox.getItemCount() > 0) {
            childrenComboBox.setSelectedIndex(0); // Select the first child in the combo box
            Student initialStudent = (Student) childrenComboBox.getSelectedItem();
            if (initialStudent != null) {
                loadStudentData(initialStudent); // Load data for the initially selected child
            }
        }
    }
    /**
     * Loads and displays the records for the given student in the JTable.
     * This method clears previous data and populates the table with new records.
     * It also addresses the "Connection to the interrupted SMOS server" postcondition.
     *
     * @param student The Student object whose data needs to be displayed.
     */
    private void loadStudentData(Student student) {
        tableModel.setRowCount(0); // Clear existing rows from the table
        if (student == null) {
            // This scenario should ideally not be reached if the combobox is always populated.
            // If it occurs, it means no valid child was selected, so no specific data fetch happened
            // that would entail interrupting the SMOS server connection.
            statusLabel.setText("Operation cancelled: No child selected.");
            return;
        }
        List<StudentRecord> records = student.getRecords(); // Get records from the student object
        if (records.isEmpty()) {
            // If no records are found for the selected student, display a message within the table.
            tableModel.addRow(new Object[]{"N/A", "N/A", "No records found for " + student.getName() + ".", "N/A", "N/A"});
            // Disconnect the server after attempting to retrieve data, even if empty.
            dataService.disconnectSmosServer();
        } else {
            // Populate the table with each record's details.
            for (StudentRecord record : records) {
                tableModel.addRow(new Object[]{
                        record.getFormattedDate(),
                        record.isAbsent() ? "Yes" : "No",
                        record.getDisciplinaryNotes().isEmpty() ? "N/A" : record.getDisciplinaryNotes(),
                        record.getDelays(),
                        record.getJustification().isEmpty() ? "N/A" : record.getJustification()
                });
            }
            // The postcondition "Connection to the interrupted SMOS server" is now strictly enforced.
            // After data is retrieved and displayed, the connection to the SMOS server is considered interrupted.
            dataService.disconnectSmosServer();
        }
        // Always update the status label based on the service's current connection state after data operation.
        updateSmosServerStatus();
    }
    /**
     * Updates the status label based on the current SMOS server connection status
     * as reported by the StudentDataService.
     */
    private void updateSmosServerStatus() {
        if (dataService.isSmosServerConnected()) {
            // Display connected status if the service reports it.
            statusLabel.setText("SMOS Server Status: Connected");
        } else {
            // Display disconnected status if the service reports it.
            // This reflects the postcondition "Connection to the interrupted SMOS server"
            // after data has been retrieved and processed.
            statusLabel.setText("SMOS Server Status: Disconnected (after data retrieval)");
        }
    }
    /**
     * Main method, the entry point for the application.
     * Creates and runs the StudentDataViewer GUI on the Event Dispatch Thread.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        // Ensure that GUI operations are performed on the Event Dispatch Thread (EDT)
        // to maintain thread safety and responsiveness in Swing applications.
        SwingUtilities.invokeLater(() -> {
            new StudentDataViewer();
        });
    }
}