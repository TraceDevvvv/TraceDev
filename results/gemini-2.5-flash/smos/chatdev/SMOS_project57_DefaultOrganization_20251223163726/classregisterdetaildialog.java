'''
A JDialog window that displays the detailed register information for a selected class.
This dialog uses a JTable to present StudentRegisterEntry data.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableCellRenderer;
import java.awt.*;
import java.util.List;
public class ClassRegisterDetailDialog extends JDialog {
    private JTable registerTable;           // Table to display register entries.
    private ClassRegisterTableModel tableModel; // Model for the register table.
    private DataService dataService;        // Service to fetch register data.
    private ClassInfo currentClass;         // The class for which the register is displayed.
    /**
     * Constructs a new ClassRegisterDetailDialog.
     * @param owner The parent frame of this dialog.
     * @param classInfo The ClassInfo object for which to display the register.
     * @param dataService The DataService to retrieve register entries.
     */
    public ClassRegisterDetailDialog(Frame owner, ClassInfo classInfo, DataService dataService) {
        super(owner, "Class Register for " + classInfo.getClassName(), true); // Modal dialog.
        this.currentClass = classInfo;
        this.dataService = dataService;
        // Set up the dialog properties.
        setSize(800, 400); // Set preferred size.
        setLocationRelativeTo(owner); // Center the dialog relative to its parent.
        setDefaultCloseOperation(DISPOSE_ON_CLOSE); // Close only this dialog.
        initComponents(); // Initialize GUI components.
        loadRegisterData(); // Load and display the register data.
    }
    /**
     * Initializes and lays out the GUI components of the dialog.
     */
    private void initComponents() {
        setLayout(new BorderLayout()); // Use BorderLayout for overall layout.
        // Label to show the class name at the top.
        JLabel classLabel = new JLabel("Register for: " + currentClass.getClassName() + " (" + currentClass.getClassId() + ")", SwingConstants.CENTER);
        classLabel.setFont(new Font("Serif", Font.BOLD, 16));
        add(classLabel, BorderLayout.NORTH);
        // Initialize the table model with empty data initially.
        tableModel = new ClassRegisterTableModel(new java.util.ArrayList<>());
        registerTable = new JTable(tableModel);
        // Configure table appearance.
        registerTable.setFillsViewportHeight(true); // Make the table fill the height of the scroll pane.
        registerTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection.
        registerTable.setAutoCreateRowSorter(true); // Allow sorting by clicking column headers.
        // Custom renderer for all *String* columns to center text in cells for better aesthetics.
        // Boolean columns will use default checkbox renderer.
        DefaultTableCellRenderer centerRenderer = new DefaultTableCellRenderer();
        centerRenderer.setHorizontalAlignment(SwingConstants.CENTER);
        // Apply this renderer to specific String-based columns (0:Date, 1:Student, 3:Notes, 5:Justification).
        registerTable.getColumnModel().getColumn(0).setCellRenderer(centerRenderer); 
        registerTable.getColumnModel().getColumn(1).setCellRenderer(centerRenderer); 
        registerTable.getColumnModel().getColumn(3).setCellRenderer(centerRenderer); 
        registerTable.getColumnModel().getColumn(5).setCellRenderer(centerRenderer); 
        // Note: Columns 2 (Absence) and 4 (Delay) are Boolean.class and will use JTable's default
        // checkbox renderer, which does not need explicit centering.
        // Add the table to a scroll pane to support scrolling if data overflows.
        JScrollPane scrollPane = new JScrollPane(registerTable);
        add(scrollPane, BorderLayout.CENTER);
        // Add a "Close" button at the bottom.
        JButton closeButton = new JButton("Close");
        closeButton.addActionListener(e -> dispose()); // Close the dialog when button is clicked.
        JPanel buttonPanel = new JPanel(); // Panel to hold the close button.
        buttonPanel.add(closeButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads the register data for the current class using the DataService
     * and updates the table model to display it.
     */
    private void loadRegisterData() {
        // Fetch the register entries for the selected class.
        List<StudentRegisterEntry> entries = dataService.getClassRegister(currentClass.getClassId());
        // Update the table model with the fetched data.
        tableModel.setRegisterEntries(entries);
        // Auto-size columns for better readability
        registerTable.setAutoResizeMode(JTable.AUTO_RESIZE_ALL_COLUMNS);
        // Explicitly simulate the interruption/closing of the SMOS server connection
        // after the data has been successfully retrieved and displayed,
        // fulfilling the postcondition "Connection to the interrupted SMOS server."
        dataService.simulateDisconnect();
    }
}