'''
GUI for the Administrator to view and delete classes.
It uses Swing components to display a list of classes,
show details of a selected class, and provide a delete button.
This version uses SwingWorker to perform long-running operations
(like fetching or deleting classes) in a background thread, keeping the UI responsive.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.util.List;
import java.util.Optional;
public class AdminDashboard extends JFrame {
    private ClassService classService;
    private JTable classTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JTextArea detailsArea;
    private JButton deleteButton;
    private String selectedClassId = null; // Stores the ID of the currently selected class
    /**
     * Constructor for the AdminDashboard.
     * Initializes the GUI components and sets up event listeners.
     */
    public AdminDashboard() {
        super("Administrator - Class Management"); // Set the window title
        classService = new ClassService(); // Initialize the service
        // Set up the main window properties
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        // Initialize GUI components
        initComponents();
        // Load initial class data into the table
        refreshClassList();
    }
    /**
     * Initializes all the Swing components and lays them out.
     */
    private void initComponents() {
        // Use BorderLayout for main frame
        setLayout(new BorderLayout(10, 10)); // Add some padding
        // --- Top Panel for Title/Header ---
        JPanel headerPanel = new JPanel();
        JLabel titleLabel = new JLabel("Class Archive Management", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);
        // --- Center Panel for Class Table and Details ---
        JSplitPane centerSplitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        centerSplitPane.setDividerLocation(0.6); // Table takes 60% width
        // Left side: Class List Table
        tableModel = new DefaultTableModel(new Object[]{"Class ID", "Name"}, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        classTable = new JTable(tableModel);
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        JScrollPane tableScrollPane = new JScrollPane(classTable);
        centerSplitPane.setLeftComponent(tableScrollPane);
        // Right side: Class Details and Controls
        JPanel rightPanel = new JPanel(new BorderLayout(5, 5));
        rightPanel.setBorder(BorderFactory.createTitledBorder("Selected Class Details"));
        detailsArea = new JTextArea("Select a class from the list to view its details.");
        detailsArea.setEditable(false);
        detailsArea.setLineWrap(true);
        detailsArea.setWrapStyleWord(true);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        rightPanel.add(detailsScrollPane, BorderLayout.CENTER);
        deleteButton = new JButton("Delete Selected Class");
        deleteButton.setEnabled(false); // Disable until a class is selected
        rightPanel.add(deleteButton, BorderLayout.SOUTH);
        centerSplitPane.setRightComponent(rightPanel);
        add(centerSplitPane, BorderLayout.CENTER);
        // --- Bottom Panel for Status Messages ---
        statusLabel = new JLabel("Ready", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        add(statusLabel, BorderLayout.SOUTH);
        // --- Add Event Listeners ---
        // Listener for table row selection (simulates "viewdettagliSlasse")
        classTable.getSelectionModel().addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) {
                    int selectedRow = classTable.getSelectedRow();
                    if (selectedRow != -1) {
                        selectedClassId = (String) tableModel.getValueAt(selectedRow, 0); // Get Class ID from first column
                        displayClassDetails(selectedClassId);
                        deleteButton.setEnabled(true); // Enable delete button when a class is selected
                    } else {
                        selectedClassId = null;
                        detailsArea.setText("Select a class from the list to view its details.");
                        deleteButton.setEnabled(false); // Disable delete button if no class is selected
                        statusLabel.setText("Ready");
                    }
                }
            }
        });
        // Listener for the delete button click
        deleteButton.addActionListener(e -> deleteSelectedClass());
    }
    /**
     * Refreshes the list of classes displayed in the JTable.
     * Fetches all classes from ClassService using a SwingWorker to keep the UI responsive.
     */
    private void refreshClassList() {
        // Clear any previous selection state before starting the refresh
        this.selectedClassId = null; 
        deleteButton.setEnabled(false); 
        detailsArea.setText("Loading class data. Please wait...");
        statusLabel.setText("Loading classes...");
        setUiEnabled(false); // Disable UI interactions during loading
        new SwingWorker<List<ClassModel>, Void>() {
            @Override
            protected List<ClassModel> doInBackground() throws Exception {
                // Perform the long-running operation in a background thread
                return classService.getAllClasses();
            }
            @Override
            protected void done() {
                try {
                    List<ClassModel> allClasses = get(); // Get the result from doInBackground
                    tableModel.setRowCount(0); // Clear existing data
                    if (allClasses.isEmpty()) {
                        statusLabel.setText("No classes found in the archive.");
                        detailsArea.setText("No classes available.");
                        deleteButton.setEnabled(false); // Explicitly disable if no classes
                    } else {
                        for (ClassModel classModel : allClasses) {
                            tableModel.addRow(new Object[]{classModel.getId(), classModel.getName()});
                        }
                        statusLabel.setText(allClasses.size() + " classes loaded. Select a class to delete.");
                        // The delete button enablement will implicitly be handled by the selection listener
                        // if a row is selected programmatically or by user,
                        // or remain disabled if no rows are selected.
                    }
                } catch (Exception e) {
                    statusLabel.setText("Error loading classes: " + e.getMessage());
                    detailsArea.setText("Failed to load classes due to an error.");
                    deleteButton.setEnabled(false); // Disable on error
                    e.printStackTrace();
                } finally {
                    setUiEnabled(true); // Re-enable UI elements. Note: deleteButton state adjusted later.
                    // If no classes, selectedClassId will be null, so deleteButton.setEnabled(true && null) -> false.
                    // If classes exist but none are selected, selectedClassId is null, deleteButton -> false.
                    // If a class was previously selected (and still exists), selectedClassId is not null, deleteButton -> true.
                }
            }
        }.execute();
    }
    /**
     * Displays the detailed information of the class with the given ID in the details area.
     * This simulates the "viewdettagliSlasse" and displaying of detailed info.
     * @param classId The ID of the class whose details are to be displayed.
     */
    private void displayClassDetails(String classId) {
        if (classId == null) {
            detailsArea.setText("Select a class from the list to view its details.");
            deleteButton.setEnabled(false);
            return;
        }
        Optional<ClassModel> classModelOpt = classService.findClassById(classId);
        if (classModelOpt.isPresent()) {
            ClassModel classModel = classModelOpt.get();
            detailsArea.setText(String.format(
                "Class ID: %s\nName: %s\nDescription:\n%s",
                classModel.getId(),
                classModel.getName(),
                classModel.getDescription()
            ));
            statusLabel.setText("Details for class " + classModel.getName() + " displayed.");
            deleteButton.setEnabled(true); // Ensure delete button is enabled if details are shown
        } else {
            detailsArea.setText("Details not found for selected class ID: " + classId + ". It might have been deleted.");
            statusLabel.setText("Error: Class details could not be retrieved.");
            deleteButton.setEnabled(false); // Disable if details are missing (e.g., deleted by another admin)
        }
    }
    /**
     * Handles the deletion of the currently selected class.
     * This method is triggered when the "Delete" button is clicked.
     * It performs the delete operation via ClassService using a SwingWorker,
     * then refreshes the list, and simulates the SMOS server disconnection.
     */
    private void deleteSelectedClass() {
        if (selectedClassId == null) {
            JOptionPane.showMessageDialog(this, "Please select a class to delete.", "Deletion Error", JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Confirmation dialog for deletion
        int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to delete class ID: " + selectedClassId + "? This action cannot be undone.",
                "Confirm Deletion",
                JOptionPane.YES_NO_OPTION,
                JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            statusLabel.setText("Attempting to delete class ID: " + selectedClassId + "...");
            setUiEnabled(false); // Disable UI interaction while deletion is in progress
            final String classIdToDelete = selectedClassId; // Capture for SwingWorker
            new SwingWorker<Boolean, Void>() {
                @Override
                protected Boolean doInBackground() throws Exception {
                    // Perform the long-running operation in a background thread
                    return classService.deleteClass(classIdToDelete);
                }
                @Override
                protected void done() {
                    try {
                        boolean success = get(); // Get the result from doInBackground
                        if (success) {
                            JOptionPane.showMessageDialog(AdminDashboard.this, "Class ID: " + classIdToDelete + " successfully deleted.", "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
                            statusLabel.setText("Class ID: " + classIdToDelete + " deleted. Updating list...");
                            refreshClassList(); // Update the table after deletion
                            detailsArea.setText("Class ID: " + classIdToDelete + " has been deleted.");
                            AdminDashboard.this.selectedClassId = null; // Clear selected class ID
                            deleteButton.setEnabled(false); // Disable delete button until another class is selected
                        } else {
                            JOptionPane.showMessageDialog(AdminDashboard.this, "Failed to delete class ID: " + classIdToDelete + ". It might not exist.", "Deletion Failed", JOptionPane.ERROR_MESSAGE);
                            statusLabel.setText("Deletion failed for class ID: " + classIdToDelete + ".");
                        }
                    } catch (Exception ex) {
                        JOptionPane.showMessageDialog(AdminDashboard.this, "An error occurred during deletion: " + ex.getMessage(), "Deletion Error", JOptionPane.ERROR_MESSAGE);
                        statusLabel.setText("An unexpected error occurred during deletion.");
                        ex.printStackTrace();
                    } finally {
                        setUiEnabled(true); // Re-enable UI elements. Note: actual deleteButton state depends on selectedClassId.
                        // As per postcondition, simulate SMOS server disconnection
                        classService.simulateSMOSDisconnect();
                    }
                }
            }.execute();
        } else {
            statusLabel.setText("Deletion cancelled for class ID: " + selectedClassId + ".");
        }
    }
    /**
     * Helper method to enable or disable relevant UI components during an operation.
     * @param enabled True to enable, false to disable.
     */
    private void setUiEnabled(boolean enabled) {
        classTable.setEnabled(enabled);
        // Only enable delete button if overall UI is enabled AND a class is currently selected.
        // This takes into account if selectedClassId was reset or if no class is truly selected.
        if (enabled) {
             deleteButton.setEnabled(selectedClassId != null);
        } else {
             deleteButton.setEnabled(false);
             // Ensure the current selection is cleared visually to avoid confusion
             classTable.clearSelection();
             // The detailsArea message is set at the start of refreshClassList
             // or by the selection listener, so this might be redundant here,
             // but keeping it doesn't hurt for visual feedback during process.
             // detailsArea.setText("Operation in progress. Please wait..."); 
        }
        // Change cursor for visual feedback
        setCursor(enabled ? Cursor.getDefaultCursor() : Cursor.getPredefinedCursor(Cursor.WAIT_CURSOR));
    }
}