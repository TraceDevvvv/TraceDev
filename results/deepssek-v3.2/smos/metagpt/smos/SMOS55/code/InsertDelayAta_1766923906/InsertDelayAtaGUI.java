import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import javax.swing.event.TableModelEvent;
import javax.swing.event.TableModelListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.util.ArrayList;

/**
 * InsertDelayAtaGUI - Main GUI class for the InsertDelayAta use case.
 * Shows a screen with all students present and allows ATA staff to 
 * mark delays and specify delay times.
 */
public class InsertDelayAtaGUI extends JFrame implements ActionListener {
    // GUI Components
    private JLabel titleLabel;
    private JTable studentTable;
    private StudentTableModel tableModel;
    private JButton confirmButton;
    private JButton cancelButton;
    private JLabel statusLabel;
    
    // Data
    private List<Student> students;
    private String currentClass;
    private String ataStaffName;
    
    // Reference to data handler (will be set externally)
    private DataHandler dataHandler;
    
    // Constants for table columns
    private static final String[] COLUMN_NAMES = {
        "Present", "Student ID", "Name", "Delay", "Delay Minutes"
    };
    
    private static final int COL_DELAY_CHECKBOX = 3;
    private static final int COL_DELAY_MINUTES = 4;
    
    /**
     * Constructor for InsertDelayAtaGUI
     * @param className The name of the class being viewed
     * @param ataStaffName The name of the logged-in ATA staff
     * @param students List of students in the class
     */
    public InsertDelayAtaGUI(String className, String ataStaffName, List<Student> students) {
        this.currentClass = className;
        this.ataStaffName = ataStaffName;
        this.students = new ArrayList<>(students);
        
        // Initialize GUI
        initComponents();
        setupLayout();
        configureFrame();
    }
    
    /**
     * Initialize GUI components
     */
    private void initComponents() {
        // Title label
        titleLabel = new JLabel("Insert Delay - " + currentClass);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create table model and table
        tableModel = new StudentTableModel(students);
        studentTable = new JTable(tableModel);
        
        // Configure table
        studentTable.setRowHeight(25);
        studentTable.getColumnModel().getColumn(0).setPreferredWidth(60);  // Present
        studentTable.getColumnModel().getColumn(1).setPreferredWidth(100); // Student ID
        studentTable.getColumnModel().getColumn(2).setPreferredWidth(150); // Name
        studentTable.getColumnModel().getColumn(3).setPreferredWidth(60);  // Delay checkbox
        studentTable.getColumnModel().getColumn(4).setPreferredWidth(100); // Delay minutes
        
        // Make the delay minutes column editable only when delay is checked
        studentTable.getModel().addTableModelListener(new TableModelListener() {
            @Override
            public void tableChanged(TableModelEvent e) {
                if (e.getColumn() == COL_DELAY_CHECKBOX) {
                    int row = e.getFirstRow();
                    boolean hasDelay = (Boolean) tableModel.getValueAt(row, COL_DELAY_CHECKBOX);
                    tableModel.fireTableCellUpdated(row, COL_DELAY_MINUTES);
                    
                    // Enable/disable delay minutes editing
                    if (!hasDelay) {
                        // Clear delay minutes when delay is unchecked
                        tableModel.setValueAt(0, row, COL_DELAY_MINUTES);
                    }
                }
            }
        });
        
        // Make delay minutes column use a spinner editor
        JSpinner hourSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 23, 1));
        JSpinner minuteSpinner = new JSpinner(new SpinnerNumberModel(0, 0, 59, 5));
        
        // Create a custom editor for delay minutes
        studentTable.getColumnModel().getColumn(COL_DELAY_MINUTES).setCellEditor(new DefaultCellEditor(
                new JTextField() {
                    @Override
                    public void setText(String t) {
                        super.setText(t);
                    }
                }
        ));
        
        // Buttons
        confirmButton = new JButton("Confirm");
        confirmButton.addActionListener(this);
        confirmButton.setBackground(new Color(76, 175, 80));
        confirmButton.setForeground(Color.WHITE);
        confirmButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(this);
        cancelButton.setBackground(new Color(244, 67, 54));
        cancelButton.setForeground(Color.WHITE);
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        
        // Status label
        statusLabel = new JLabel("Ready - Logged in as: " + ataStaffName);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        statusLabel.setHorizontalAlignment(SwingConstants.CENTER);
        
        // Create scroll pane for table
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setPreferredSize(new Dimension(600, 300));
    }
    
    /**
     * Set up the layout of the GUI
     */
    private void setupLayout() {
        // Set layout
        setLayout(new BorderLayout(10, 10));
        
        // Title panel
        JPanel titlePanel = new JPanel(new BorderLayout());
        titlePanel.add(titleLabel, BorderLayout.CENTER);
        titlePanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        add(titlePanel, BorderLayout.NORTH);
        
        // Table panel
        JScrollPane scrollPane = new JScrollPane(studentTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Students"));
        add(scrollPane, BorderLayout.CENTER);
        
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(confirmButton);
        buttonPanel.add(cancelButton);
        add(buttonPanel, BorderLayout.SOUTH);
        
        // Status bar
        JPanel statusPanel = new JPanel(new BorderLayout());
        statusPanel.setBorder(BorderFactory.createEtchedBorder());
        statusPanel.add(statusLabel, BorderLayout.CENTER);
        add(statusPanel, BorderLayout.PAGE_END);
        
        // Add padding
        ((JPanel) getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
    }
    
    /**
     * Configure the main frame
     */
    private void configureFrame() {
        setTitle("ATA System - Insert Delay");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(null); // Center on screen
        setResizable(true);
    }
    
    /**
     * Set the data handler for server communication
     * @param dataHandler The DataHandler instance
     */
    public void setDataHandler(DataHandler dataHandler) {
        this.dataHandler = dataHandler;
    }
    
    /**
     * Handle button click events
     * @param e The ActionEvent
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == confirmButton) {
            handleConfirm();
        } else if (e.getSource() == cancelButton) {
            handleCancel();
        }
    }
    
    /**
     * Handle Confirm button click
     */
    private void handleConfirm() {
        try {
            // Collect delay entries
            List<DelayEntry> delayEntries = collectDelayEntries();
            
            if (delayEntries.isEmpty()) {
                JOptionPane.showMessageDialog(this,
                    "No delays selected. Please select at least one student with delay.",
                    "No Delays Selected",
                    JOptionPane.INFORMATION_MESSAGE);
                return;
            }
            
            // Validate delay entries
            StringBuilder validationErrors = new StringBuilder();
            for (DelayEntry entry : delayEntries) {
                if (!entry.isValid()) {
                    validationErrors.append("Invalid entry for student: ")
                                   .append(entry.getStudentName())
                                   .append("\n");
                }
            }
            
            if (validationErrors.length() > 0) {
                JOptionPane.showMessageDialog(this,
                    "Validation errors:\n" + validationErrors.toString(),
                    "Validation Error",
                    JOptionPane.ERROR_MESSAGE);
                return;
            }
            
            // Ask for confirmation
            int confirmation = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to submit " + delayEntries.size() + " delay entries?",
                "Confirm Submission",
                JOptionPane.YES_NO_OPTION);
            
            if (confirmation == JOptionPane.YES_OPTION) {
                // Send data to server
                if (dataHandler != null) {
                    boolean success = dataHandler.sendDelayData(delayEntries);
                    
                    if (success) {
                        statusLabel.setText("Data sent successfully!");
                        JOptionPane.showMessageDialog(this,
                            "Delay data has been successfully entered into the system.",
                            "Success",
                            JOptionPane.INFORMATION_MESSAGE);
                        
                        // Reset form as per postconditions (initial screen shown again)
                        resetForm();
                    } else {
                        statusLabel.setText("Error sending data to server");
                        JOptionPane.showMessageDialog(this,
                            "Failed to send data to server. Please try again.",
                            "Server Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } else {
                    // Simulate success if no data handler is set (for testing)
                    statusLabel.setText("Data ready (no server connection)");
                    JOptionPane.showMessageDialog(this,
                        delayEntries.size() + " delay entries collected successfully.\n" +
                        "Simulated server submission (DataHandler not configured).",
                        "Simulation Mode",
                        JOptionPane.INFORMATION_MESSAGE);
                    resetForm();
                }
            }
            
        } catch (Exception ex) {
            statusLabel.setText("Error: " + ex.getMessage());
            JOptionPane.showMessageDialog(this,
                "An error occurred: " + ex.getMessage(),
                "Error",
                JOptionPane.ERROR_MESSAGE);
        }
    }
    
    /**
     * Handle Cancel button click
     */
    private void handleCancel() {
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to cancel? All unsaved changes will be lost.",
            "Confirm Cancel",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.WARNING_MESSAGE);
        
        if (confirmation == JOptionPane.YES_OPTION) {
            // Close the application as per postconditions
            dispose();
            System.exit(0);
        }
    }
    
    /**
     * Collect delay entries from the table
     * @return List of DelayEntry objects
     */
    private List<DelayEntry> collectDelayEntries() {
        List<DelayEntry> entries = new ArrayList<>();
        
        for (int i = 0; i < students.size(); i++) {
            Student student = students.get(i);
            Boolean hasDelay = (Boolean) tableModel.getValueAt(i, COL_DELAY_CHECKBOX);
            Object minutesObj = tableModel.getValueAt(i, COL_DELAY_MINUTES);
            
            // Convert delay minutes to integer
            int delayMinutes = 0;
            if (minutesObj != null) {
                if (minutesObj instanceof Number) {
                    delayMinutes = ((Number) minutesObj).intValue();
                } else if (minutesObj instanceof String) {
                    try {
                        delayMinutes = Integer.parseInt(minutesObj.toString());
                    } catch (NumberFormatException e) {
                        delayMinutes = 0;
                    }
                }
            }
            
            // Only create entry if delay is checked and minutes > 0
            if (hasDelay != null && hasDelay && delayMinutes > 0) {
                DelayEntry entry = new DelayEntry(
                    student.getStudentId(),
                    student.getFullName(),
                    delayMinutes,
                    currentClass,
                    ataStaffName
                );
                entries.add(entry);
            }
        }
        
        return entries;
    }
    
    /**
     * Reset the form to initial state
     */
    private void resetForm() {
        // Clear all delay selections
        for (int i = 0; i < students.size(); i++) {
            tableModel.setValueAt(false, i, COL_DELAY_CHECKBOX);
            tableModel.setValueAt(0, i, COL_DELAY_MINUTES);
        }
        
        // Reset status
        statusLabel.setText("Form reset - Ready for new entries");
        studentTable.repaint();
    }
    
    /**
     * Custom table model for student data
     */
    private class StudentTableModel extends AbstractTableModel {
        private final List<Student> students;
        private final Class<?>[] columnClasses = {
            Boolean.class, String.class, String.class, Boolean.class, Integer.class
        };
        
        public StudentTableModel(List<Student> students) {
            this.students = students;
        }
        
        @Override
        public int getRowCount() {
            return students.size();
        }
        
        @Override
        public int getColumnCount() {
            return COLUMN_NAMES.length;
        }
        
        @Override
        public String getColumnName(int column) {
            return COLUMN_NAMES[column];
        }
        
        @Override
        public Class<?> getColumnClass(int columnIndex) {
            return columnClasses[columnIndex];
        }
        
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Only delay checkbox and delay minutes are editable
            // Delay minutes is only editable when delay is checked (handled elsewhere)
            return columnIndex == COL_DELAY_CHECKBOX || columnIndex == COL_DELAY_MINUTES;
        }
        
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            Student student = students.get(rowIndex);
            switch (columnIndex) {
                case 0: // Present
                    return student.isPresent();
                case 1: // Student ID
                    return student.getStudentId();
                case 2: // Name
                    return student.getFullName();
                case 3: // Delay checkbox
                    return student.hasDelay();
                case 4: // Delay minutes
                    return student.getDelayMinutes();
                default:
                    return null;
            }
        }
        
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            Student student = students.get(rowIndex);
            switch (columnIndex) {
                case 0: // Present
                    student.setPresent((Boolean) aValue);
                    break;
                case 3: // Delay checkbox
                    boolean hasDelay = (Boolean) aValue;
                    student.setHasDelay(hasDelay);
                    if (!hasDelay) {
                        // Auto-clear delay minutes when delay is unchecked
                        student.setDelayMinutes(0);
                    }
                    break;
                case 4: // Delay minutes
                    try {
                        int minutes = 0;
                        if (aValue != null) {
                            if (aValue instanceof Number) {
                                minutes = ((Number) aValue).intValue();
                            } else if (aValue instanceof String) {
                                minutes = Integer.parseInt(aValue.toString());
                            }
                        }
                        student.setDelayMinutes(minutes);
                        // Auto-check delay when minutes are set
                        if (minutes > 0) {
                            student.setHasDelay(true);
                            fireTableCellUpdated(rowIndex, COL_DELAY_CHECKBOX);
                        }
                    } catch (NumberFormatException e) {
                        // Keep current value if invalid
                    }
                    break;
            }
            fireTableCellUpdated(rowIndex, columnIndex);
        }
    }
    
    /**
     * Main method for standalone testing
     * @param args Command line arguments
     */
    public static void main(String[] args) {
        // Start GUI in Event Dispatch Thread as per Swing guidelines
        SwingUtilities.invokeLater(() -> {
            try {
                // Create sample data for testing
                List<Student> sampleStudents = new ArrayList<>();
                sampleStudents.add(new Student("S001", "John", "Doe", true));
                sampleStudents.add(new Student("S002", "Jane", "Smith", true));
                sampleStudents.add(new Student("S003", "Robert", "Johnson", true));
                sampleStudents.add(new Student("S004", "Emily", "Davis", true));
                sampleStudents.add(new Student("S005", "Michael", "Wilson", true));
                
                // Create and display the GUI
                InsertDelayAtaGUI gui = new InsertDelayAtaGUI(
                    "ATA Class 2023", "ATA_Staff_01", sampleStudents);
                gui.setVisible(true);
                
            } catch (Exception e) {
                e.printStackTrace();
                JOptionPane.showMessageDialog(null,
                    "Error starting application: " + e.getMessage(),
                    "Startup Error",
                    JOptionPane.ERROR_MESSAGE);
            }
        });
    }
}