/**
 * Frame that displays the list of digital registers for the selected academic year,
 * grouped by class. This corresponds to step 3 in the event sequence.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.ArrayList;
import java.util.List;
public class RegisterListFrame extends JFrame {
    private int academicYear;
    private JTable registerTable;
    private DefaultTableModel tableModel;
    public RegisterListFrame(int academicYear) {
        this.academicYear = academicYear;
        setTitle("Digital Register - Registers for " + academicYear);
        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE); // Handle close manually for postconditions
        setSize(600, 400);
        setLocationRelativeTo(null);
        // Handle window closing to simulate interruption/connection loss
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                handleInterruption();
            }
        });
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title label
        JLabel titleLabel = new JLabel("Digital Registers for Academic Year: " + academicYear,
                SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Table to display registers
        String[] columnNames = {"Class", "Register ID", "Teacher", "Status"};
        tableModel = new DefaultTableModel(columnNames, 0);
        registerTable = new JTable(tableModel);
        registerTable.setRowHeight(25);
        JScrollPane scrollPane = new JScrollPane(registerTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Refresh button (optional, could simulate re-fetching)
        JButton refreshButton = new JButton("Refresh");
        refreshButton.addActionListener(e -> loadRegisters());
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.add(refreshButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        loadRegisters(); // Load data on startup
    }
    /**
     * Simulates fetching registers from an archive (e.g., a server/database).
     * In a real application, this would involve a network call.
     */
    private void loadRegisters() {
        // Clear existing rows
        tableModel.setRowCount(0);
        // Simulate fetching data
        List<Register> registers = fetchRegistersFromArchive();
        if (registers.isEmpty()) {
            JOptionPane.showMessageDialog(this, "No registers found for the selected year.",
                    "Information", JOptionPane.INFORMATION_MESSAGE);
            return;
        }
        // Populate the table
        for (Register reg : registers) {
            Object[] row = {reg.className, reg.registerId, reg.teacher, reg.status};
            tableModel.addRow(row);
        }
    }
    /**
     * Mock method that returns a list of registers for the selected year.
     * In reality, this would be replaced by a service call to a backend.
     */
    private List<Register> fetchRegistersFromArchive() {
        List<Register> registers = new ArrayList<>();
        // Sample data - in production, this would come from a database or server.
        registers.add(new Register("10A", "REG001", "Mr. Smith", "Active"));
        registers.add(new Register("10B", "REG002", "Ms. Johnson", "Archived"));
        registers.add(new Register("11A", "REG003", "Dr. Williams", "Active"));
        registers.add(new Register("11B", "REG004", "Mrs. Brown", "Pending"));
        registers.add(new Register("12A", "REG005", "Prof. Davis", "Active"));
        return registers;
    }
    /**
     * Handles interruption events (e.g., user closes window or connection is lost).
     * This simulates the postcondition about interruption.
     */
    private void handleInterruption() {
        int confirm = JOptionPane.showConfirmDialog(this,
                "Interrupting the operation will close the connection to the SMOS server.\nDo you want to proceed?",
                "Interrupt Operation", JOptionPane.YES_NO_OPTION, JOptionPane.WARNING_MESSAGE);
        if (confirm == JOptionPane.YES_OPTION) {
            JOptionPane.showMessageDialog(this, "Connection to SMOS server interrupted. Operation cancelled.",
                    "Interrupted", JOptionPane.INFORMATION_MESSAGE);
            dispose(); // Close this window
            System.exit(0); // Exit the application (could also go back to login)
        }
        // If NO, do nothing (window stays open)
    }
    /**
     * Inner class representing a Digital Register entry.
     */
    private static class Register {
        String className;
        String registerId;
        String teacher;
        String status;
        Register(String className, String registerId, String teacher, String status) {
            this.className = className;
            this.registerId = registerId;
            this.teacher = teacher;
            this.status = status;
        }
    }
}