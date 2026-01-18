/**
 * Main class to run the ViewListofJustifications application.
 * Contains the main method to start the GUI application.
 * This program simulates an administrator viewing a list of student absences with justifications.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class ViewListofJustifications {
    public static void main(String[] args) {
        // Run the GUI in the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new JustificationFrame().setVisible(true);
            }
        });
    }
}
/**
 * Main frame for the Justification View application.
 * Displays a table of student absences with color-coded rows for justified and unjustified absences.
 * Simulates the use case where an administrator clicks the justification button for a student.
 */
class JustificationFrame extends JFrame {
    private JTable absenceTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JButton disconnectButton;
    private boolean connectedToSMOS = true; // Simulate server connection status
    public JustificationFrame() {
        setTitle("View List of Justifications - Administrator");
        setSize(800, 600);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Initialize components
        initializeComponents();
        // Load sample data for demonstration
        loadSampleData();
        // Set up event handlers
        setupEventHandlers();
    }
    private void initializeComponents() {
        // Create table model with columns: Date, Reason, Status (Justified/Unjustified)
        String[] columns = {"Date", "Reason", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            // Make rows non-editable
            @Override
            public boolean isCellEditable(int row, int column) {
                return false;
            }
        };
        absenceTable = new JTable(tableModel);
        absenceTable.setRowHeight(30);
        // Use a custom renderer to color rows based on status
        absenceTable.setDefaultRenderer(Object.class, new JustificationRenderer());
        JScrollPane scrollPane = new JScrollPane(absenceTable);
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for status and disconnect button
        JPanel bottomPanel = new JPanel(new BorderLayout());
        statusLabel = new JLabel("Connected to SMOS Server. Showing absences for selected student.");
        bottomPanel.add(statusLabel, BorderLayout.WEST);
        disconnectButton = new JButton("Disconnect from SMOS Server");
        bottomPanel.add(disconnectButton, BorderLayout.EAST);
        add(bottomPanel, BorderLayout.SOUTH);
        // Top label
        JLabel topLabel = new JLabel("Student Absences - Justified (Green) vs Unjustified (Red)");
        topLabel.setHorizontalAlignment(SwingConstants.CENTER);
        topLabel.setFont(new Font("Arial", Font.BOLD, 16));
        add(topLabel, BorderLayout.NORTH);
    }
    private void loadSampleData() {
        // Simulate data for a student's absences during the year
        List<AbsenceRecord> absences = new ArrayList<>();
        absences.add(new AbsenceRecord("2023-01-10", "Flu", true));  // Justified
        absences.add(new AbsenceRecord("2023-02-15", "Unknown", false)); // Unjustified
        absences.add(new AbsenceRecord("2023-03-22", "Family Event", true));
        absences.add(new AbsenceRecord("2023-04-05", "Unknown", false));
        absences.add(new AbsenceRecord("2023-05-18", "Medical Appointment", true));
        absences.add(new AbsenceRecord("2023-06-01", "Unknown", false));
        absences.add(new AbsenceRecord("2023-09-12", "Sports Competition", true));
        absences.add(new AbsenceRecord("2023-10-30", "Unknown", false));
        for (AbsenceRecord absence : absences) {
            String status = absence.isJustified() ? "Justified" : "Unjustified";
            tableModel.addRow(new Object[]{absence.getDate(), absence.getReason(), status});
        }
    }
    private void setupEventHandlers() {
        disconnectButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (connectedToSMOS) {
                    connectedToSMOS = false;
                    statusLabel.setText("Disconnected from SMOS Server. Connection interrupted.");
                    JOptionPane.showMessageDialog(JustificationFrame.this,
                            "Disconnected from SMOS server as per postcondition.",
                            "Server Disconnected", JOptionPane.INFORMATION_MESSAGE);
                } else {
                    JOptionPane.showMessageDialog(JustificationFrame.this,
                            "Already disconnected from SMOS server.",
                            "Info", JOptionPane.INFORMATION_MESSAGE);
                }
            }
        });
    }
    /**
     * Custom renderer to color table rows based on justification status.
     * Justified absences appear in green, unjustified in red.
     */
    class JustificationRenderer extends javax.swing.table.DefaultTableCellRenderer {
        @Override
        public Component getTableCellRendererComponent(JTable table, Object value,
                                                       boolean isSelected, boolean hasFocus,
                                                       int row, int column) {
            Component c = super.getTableCellRendererComponent(table, value, isSelected, hasFocus, row, column);
            String status = (String) table.getModel().getValueAt(row, 2); // Status column index 2
            if ("Justified".equals(status)) {
                c.setBackground(Color.GREEN);
                c.setForeground(Color.BLACK);
            } else if ("Unjustified".equals(status)) {
                c.setBackground(Color.RED);
                c.setForeground(Color.WHITE);
            } else {
                c.setBackground(table.getBackground());
                c.setForeground(table.getForeground());
            }
            if (isSelected) {
                c.setBackground(c.getBackground().darker());
            }
            return c;
        }
    }
}
/**
 * Data class representing a single absence record for a student.
 * Encapsulates date, reason, and justification status.
 */
class AbsenceRecord {
    private String date;
    private String reason;
    private boolean justified;
    public AbsenceRecord(String date, String reason, boolean justified) {
        this.date = date;
        this.reason = reason;
        this.justified = justified;
    }
    public String getDate() {
        return date;
    }
    public String getReason() {
        return reason;
    }
    public boolean isJustified() {
        return justified;
    }
 }