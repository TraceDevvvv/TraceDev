/**
 * A JPanel to display a list of teachings in a table and provide options to add or edit them.
 * This panel handles the "displaydeddailsigning" part by allowing selection and opening the edit form.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class TeachingListPanel extends JPanel {
    private JTable teachingTable;
    private DefaultTableModel tableModel;
    private TeachingApp parentApp; // Reference to the main application to switch panels
    private TeachingService teachingService;
    /**
     * Constructs the TeachingListPanel.
     * @param parentApp The main application instance for panel navigation.
     */
    public TeachingListPanel(TeachingApp parentApp) {
        this.parentApp = parentApp;
        this.teachingService = TeachingService.getInstance();
        setLayout(new BorderLayout());
        // Table setup
        String[] columnNames = {"ID", "Name", "Instructor", "Credits"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make cells non-editable
            }
        };
        teachingTable = new JTable(tableModel);
        teachingTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only single row selection
        JScrollPane scrollPane = new JScrollPane(teachingTable);
        add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        JButton addButton = new JButton("Add New Teaching");
        JButton editButton = new JButton("Edit Selected Teaching");
        JButton refreshButton = new JButton("Refresh List"); // Added for manual refresh
        JButton deleteButton = new JButton("Delete Selected Teaching"); // Added for more complete functionality
        buttonPanel.add(addButton);
        buttonPanel.add(editButton);
        buttonPanel.add(deleteButton);
        buttonPanel.add(refreshButton);
        add(buttonPanel, BorderLayout.SOUTH);
        // Action Listeners
        addButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When adding new, pass null to the edit panel
                parentApp.showEditPanel(null);
            }
        });
        editButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = teachingTable.getSelectedRow();
                if (selectedRow != -1) {
                    // Assuming the ID is in the first column
                    String teachingId = (String) teachingTable.getValueAt(selectedRow, 0);
                    Teaching selectedTeaching = teachingService.getTeachingById(teachingId);
                    if (selectedTeaching != null) {
                        parentApp.showEditPanel(selectedTeaching);
                    } else {
                        ErrorDialog.showError("Selected teaching not found in archive. It might have been deleted or an error occurred.");
                        refreshTeachings(); // Refresh the list to reflect accurate state
                    }
                } else {
                    ErrorDialog.showError("Please select a teaching to edit.");
                }
            }
        });
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int selectedRow = teachingTable.getSelectedRow();
                if (selectedRow != -1) {
                    String teachingId = (String) teachingTable.getValueAt(selectedRow, 0);
                    int confirmResult = JOptionPane.showConfirmDialog(
                        TeachingListPanel.this,
                        "Are you sure you want to delete this teaching?",
                        "Confirm Deletion",
                        JOptionPane.YES_NO_OPTION
                    );
                    if (confirmResult == JOptionPane.YES_OPTION) {
                        if (teachingService.deleteTeaching(teachingId)) {
                            JOptionPane.showMessageDialog(TeachingListPanel.this, "Teaching deleted successfully.");
                            refreshTeachings();
                        } else {
                            ErrorDialog.showError("Failed to delete teaching. It might not exist or an error occurred.");
                        }
                    }
                } else {
                    ErrorDialog.showError("Please select a teaching to delete.");
                }
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTeachings();
            }
        });
        // Load initial data
        refreshTeachings();
    }
    /**
     * Fetches all teachings from the service and updates the table display.
     */
    public void refreshTeachings() {
        tableModel.setRowCount(0); // Clear existing data
        List<Teaching> allTeachings = teachingService.getAllTeachings();
        for (Teaching teaching : allTeachings) {
            Object[] rowData = {
                    teaching.getId(),
                    teaching.getName(),
                    teaching.getInstructor(),
                    teaching.getCredits()
            };
            tableModel.addRow(rowData);
        }
    }
}