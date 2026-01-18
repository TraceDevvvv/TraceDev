/**
 * This class displays the list of teachings in a tabular format.
 * It fulfills the core use case: "View teachings" and "View the management of the list of teachings."
 * It also handles the postcondition: "The system shows the list of teachings present in the archive"
 * and simulates "Connection to the SMOS server interrupted."
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent; // Explicitly import ActionEvent
import java.util.List;
public class TeachingsListFrame extends JFrame {
    private TeachingService teachingService;
    private JTable teachingsTable;
    private DefaultTableModel tableModel;
    private JLabel smosStatusLabel; // Declare as a member variable
    /**
     * Constructs the TeachingsListFrame.
     *
     * @param teachingService The service to manage teaching data and simulated SMOS connection.
     */
    public TeachingsListFrame(TeachingService teachingService) {
        this.teachingService = teachingService;
        setTitle("Teachings Management - List View");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null); // Center the window
        initComponents();
        loadTeachings();
        handlePostconditions(); // Fulfill postconditions after displaying
    }
    /**
     * Initializes the GUI components for the teachings list frame.
     */
    private void initComponents() {
        setLayout(new BorderLayout(10, 10)); // Add some padding
        JLabel titleLabel = new JLabel("List of Teachings", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 22));
        add(titleLabel, BorderLayout.NORTH);
        // Setup table model with column headers
        String[] columnNames = {"ID", "Title", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                // Make cells non-editable
                return false;
            }
        };
        teachingsTable = new JTable(tableModel);
        teachingsTable.setFillsViewportHeight(true); // Makes table take up entire height
        teachingsTable.setRowHeight(25); // Set custom row height for better readability
        teachingsTable.setFont(new Font("Arial", Font.PLAIN, 14));
        teachingsTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14)); // Header font
        JScrollPane scrollPane = new JScrollPane(teachingsTable);
        add(scrollPane, BorderLayout.CENTER);
        // Add a status panel at the bottom
        JPanel statusPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        // Initialize the member variable here
        this.smosStatusLabel = new JLabel("SMOS Connection Status: Active");
        this.smosStatusLabel.setName("smosStatusLabel"); // Set a name for easy identification if needed
        statusPanel.add(this.smosStatusLabel);
        add(statusPanel, BorderLayout.SOUTH);
    }
    /**
     * Loads the teaching data from the service and populates the JTable.
     */
    private void loadTeachings() {
        // Clear existing data (if any)
        tableModel.setRowCount(0);
        List<Teaching> teachings = teachingService.getTeachings();
        if (teachings.isEmpty()) {
            // Handle case where no teachings are found
            tableModel.addRow(new Object[]{"N/A", "No teachings available", ""});
            System.out.println("System: No teachings found in archive.");
        } else {
            // Populate the table with teaching data
            for (Teaching teaching : teachings) {
                tableModel.addRow(new Object[]{teaching.getId(), teaching.getTitle(), teaching.getDescription()});
            }
            System.out.println("System: Displayed list of " + teachings.size() + " teachings.");
        }
    }
    /**
     * Handles the postconditions defined in the use case.
     * Currently, this involves simulating the interruption of the SMOS server connection.
     */
    private void handlePostconditions() {
        // Postcondition: "Connection to the SMOS server interrupted"
        // Simulate a brief delay if desired, then disconnect.
        Timer timer = new Timer(1500, new AbstractAction() { // 1.5 second delay
            @Override
            public void actionPerformed(ActionEvent ae) {
                teachingService.disconnectSMOS();
                // Directly access the member variable to update the status label
                if (TeachingsListFrame.this.smosStatusLabel != null) {
                    TeachingsListFrame.this.smosStatusLabel.setText("SMOS Connection Status: Interrupted");
                    TeachingsListFrame.this.smosStatusLabel.setForeground(Color.RED);
                }
                JOptionPane.showMessageDialog(TeachingsListFrame.this,
                        "SMOS server connection has been interrupted.",
                        "Connection Interrupted",
                        JOptionPane.WARNING_MESSAGE);
            }
        });
        timer.setRepeats(false); // Only run once
        timer.start();
    }
}