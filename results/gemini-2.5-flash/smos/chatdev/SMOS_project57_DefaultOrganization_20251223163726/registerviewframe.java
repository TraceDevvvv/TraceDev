'''
The main JFrame for the application. It displays a list of classes the professor teaches
and allows the professor to view the register for a selected class.
Preconditions:
- The user is logged in as a teacher. (Simulated)
- The user has held the "VisualLancoclasses" case (displays classes). (Simulated by loading all classes)
Events sequence:
- User clicks on the "Register" button (simulated by selecting a class and clicking 'View Register').
- System displays information about the class register.
Postconditions:
- The class registry information has been shown.
- Connection to SMOS server interrupted. (Simulated by closing dialog/data fetch completion).
'''
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
public class RegisterViewFrame extends JFrame {
    private JTable classTable;                      // Table to display the list of classes.
    private ClassListTableModel classListTableModel;    // Model for the class table.
    private JButton viewRegisterButton;             // Button to view register details.
    private DataService dataService;                // Service for retrieving data.
    /**
     * Constructs the main application frame.
     * Sets up the window properties and initializes all GUI components.
     */
    public RegisterViewFrame() {
        super("Professor Class Register Viewer"); // Set the window title.
        // Configure frame properties.
        setSize(700, 400); // Set initial size.
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Exit application on close.
        setLocationRelativeTo(null); // Center the window on the screen.
        dataService = new DataService(); // Initialize the data service.
        initComponents(); // Initialize GUI components.
        loadClassData(); // Load and display the initial list of classes.
    }
    /**
     * Initializes and lays out the GUI components of the frame.
     */
    private void initComponents() {
        setLayout(new BorderLayout()); // Use BorderLayout for main layout.
        // Create a panel for the top section (title/instruction).
        JPanel headerPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        JLabel titleLabel = new JLabel("Classes You Teach");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerPanel.add(titleLabel);
        add(headerPanel, BorderLayout.NORTH);
        // Initialize the table model with an empty list initially.
        classListTableModel = new ClassListTableModel(new java.util.ArrayList<>());
        classTable = new JTable(classListTableModel);
        // Configure table appearance and behavior.
        classTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection.
        classTable.setFillsViewportHeight(true); // Make the table fill height of scroll pane.
        classTable.setAutoCreateRowSorter(true); // Allow sorting by column headers.
        // Center align text in all table cells for better aesthetics
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        classTable.setDefaultRenderer(Object.class, centerRenderer);
        // Add the table to a scroll pane, which handles scroll bars if content overflows.
        JScrollPane scrollPane = new JScrollPane(classTable);
        add(scrollPane, BorderLayout.CENTER);
        // Create a panel for buttons at the bottom.
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        viewRegisterButton = new JButton("View Register");
        viewRegisterButton.setEnabled(false); // Initially disable the button until a class is selected.
        // Add action listener to the "View Register" button.
        viewRegisterButton.addActionListener(e -> {
            int selectedRow = classTable.getSelectedRow();
            if (selectedRow != -1) { // Check if a row is actually selected.
                // Convert view row index to model row index if table is sorted.
                int modelRow = classTable.convertRowIndexToModel(selectedRow);
                ClassInfo selectedClass = classListTableModel.getClassAt(modelRow);
                if (selectedClass != null) {
                    // Open a new dialog to display the register details for the selected class.
                    ClassRegisterDetailDialog detailDialog = new ClassRegisterDetailDialog(this, selectedClass, dataService);
                    detailDialog.setVisible(true); // Make the dialog visible (modal).
                } else {
                    JOptionPane.showMessageDialog(this, "Could not retrieve class information.", "Error", JOptionPane.ERROR_MESSAGE);
                }
            } else {
                JOptionPane.showMessageDialog(this, "Please select a class first.", "No Class Selected", JOptionPane.WARNING_MESSAGE);
            }
        });
        // Add a ListSelectionListener to enable/disable the button based on row selection.
        classTable.getSelectionModel().addListSelectionListener(e -> {
            // Check if adjusting (e.g., during a multi-select drag) is finished.
            if (!e.getValueIsAdjusting()) {
                viewRegisterButton.setEnabled(classTable.getSelectedRow() != -1); 
            }
        });
        buttonPanel.add(viewRegisterButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads the list of classes taught by the professor and updates the table model.
     * This simulates the "displaying the list of all classes in which it teaches" precondition.
     */
    private void loadClassData() {
        // Fetch the list of classes from the data service.
        List<ClassInfo> classes = dataService.getProfessorClasses();
        // Update the table model with the fetched classes.
        classListTableModel.setClasses(classes);
        // Ensure initial button state is correct after data load (should be disabled if no row is selected by default).
        viewRegisterButton.setEnabled(classTable.getSelectedRow() != -1);
    }
}