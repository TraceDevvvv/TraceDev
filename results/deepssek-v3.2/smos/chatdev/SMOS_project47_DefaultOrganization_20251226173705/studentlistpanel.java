/**
 * Panel displaying list of students with edit buttons.
 * Corresponds to "DisplayedUnapagella" use case in preconditions.
 */
import javax.swing.*;
import javax.swing.table.AbstractTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class StudentListPanel extends JPanel {
    private StudentDataManager dataManager;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private JTable studentTable;
    private StudentTableModel tableModel;
    public StudentListPanel(StudentDataManager dataManager, CardLayout cardLayout, JPanel mainPanel) {
        this.dataManager = dataManager;
        this.cardLayout = cardLayout;
        this.mainPanel = mainPanel;
        setLayout(new BorderLayout());
        initializeUI();
    }
    /**
     * Builds student list UI with table and buttons.
     */
    private void initializeUI() {
        // Title
        JLabel titleLabel = new JLabel("Student Report Cards", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        // Create table with custom model
        tableModel = new StudentTableModel();
        studentTable = new JTable(tableModel);
        studentTable.setRowHeight(30);
        // Set column widths
        studentTable.getColumnModel().getColumn(8).setPreferredWidth(80); // Edit column
        // Add double-click listener for edit
        studentTable.addMouseListener(new java.awt.event.MouseAdapter() {
            @Override
            public void mouseClicked(java.awt.event.MouseEvent evt) {
                if (evt.getClickCount() == 2) { // Double-click
                    int row = studentTable.rowAtPoint(evt.getPoint());
                    int col = studentTable.columnAtPoint(evt.getPoint());
                    if (row >= 0 && col == 8) { // Edit column
                        handleEdit(row);
                    }
                }
            }
        });
        // Add table to scroll pane
        JScrollPane scrollPane = new JScrollPane(studentTable);
        add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel();
        // Refresh button
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        // Exit button (for SMOS server interruption simulation)
        JButton exitButton = new JButton("Exit (SMOS Disconnect)");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                int confirm = JOptionPane.showConfirmDialog(StudentListPanel.this,
                        "Interrupt connection to SMOS server?",
                        "SMOS Server Disconnect",
                        JOptionPane.YES_NO_OPTION);
                if (confirm == JOptionPane.YES_OPTION) {
                    System.exit(0); // Simulate server disconnection
                }
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Handles edit button click for a specific row.
     * @param rowIndex row to edit
     */
    private void handleEdit(int rowIndex) {
        List<Student> students = dataManager.getAllStudents();
        if (rowIndex < students.size()) {
            Student selectedStudent = students.get(rowIndex);
            // Switch to edit panel
            EditFormPanel editPanel = (EditFormPanel) mainPanel.getComponent(1);
            editPanel.setStudent(selectedStudent);
            cardLayout.show(mainPanel, "EDIT");
        }
    }
    /**
     * Refreshes table data from data manager.
     */
    public void refreshTable() {
        tableModel.fireTableDataChanged();
        JOptionPane.showMessageDialog(this, "Student list refreshed.", "Refresh", 
                                     JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Custom TableModel for displaying student data.
     */
    private class StudentTableModel extends AbstractTableModel {
        private final String[] columnNames = {
            "ID", "Name", "Grade Level", "Math", "Science", "English", "History", "Art", "Edit"
        };
        @Override
        public int getRowCount() {
            return dataManager.getStudentCount();
        }
        @Override
        public int getColumnCount() {
            return columnNames.length;
        }
        @Override
        public String getColumnName(int column) {
            return columnNames[column);
        }
        @Override
        public Object getValueAt(int rowIndex, int columnIndex) {
            List<Student> students = dataManager.getAllStudents();
            if (rowIndex >= students.size()) return null;
            Student student = students.get(rowIndex);
            switch (columnIndex) {
                case 0: return student.getId();
                case 1: return student.getName();
                case 2: return student.getGrade();
                case 3: return student.getMathGrade();
                case 4: return student.getScienceGrade();
                case 5: return student.getEnglishGrade();
                case 6: return student.getHistoryGrade();
                case 7: return student.getArtGrade();
                case 8: return "Edit";
                default: return null;
            }
        }
        @Override
        public boolean isCellEditable(int rowIndex, int columnIndex) {
            // Only Edit column is "editable" (triggers edit action)
            return columnIndex == 8;
        }
        @Override
        public void setValueAt(Object aValue, int rowIndex, int columnIndex) {
            // Handle programmatic edit triggers
            if (columnIndex == 8 && "Edit".equals(aValue)) {
                handleEdit(rowIndex);
            }
        }
    }
}