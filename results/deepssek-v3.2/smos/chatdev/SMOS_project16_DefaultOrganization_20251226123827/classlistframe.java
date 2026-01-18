'''
Displays the list of updated classes after deletion.
Implements post-event display: "Displays the list of updated classes"
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class ClassListFrame extends JFrame {
    private DatabaseSimulator db;
    private JTable classTable;
    private DefaultTableModel tableModel;
    public ClassListFrame(DatabaseSimulator db) {
        this.db = db;
        initializeUI();
    }
    private void initializeUI() {
        setTitle("Class List - Updated");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 400);
        setLocationRelativeTo(null);
        // Create main panel
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        JLabel titleLabel = new JLabel("Updated Class List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 18));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create table with data
        String[] columnNames = db.getColumnNames();
        Object[][] data = db.getClassData();
        tableModel = new DefaultTableModel(data, columnNames) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        classTable = new JTable(tableModel);
        classTable.setRowHeight(30);
        classTable.setFont(new Font("Arial", Font.PLAIN, 12));
        classTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        // Add mouse listener for double-click to view details
        classTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) { // Double-click
                    int selectedRow = classTable.getSelectedRow();
                    if (selectedRow >= 0) {
                        int classId = (int) tableModel.getValueAt(selectedRow, 0);
                        viewClassDetails(classId);
                    }
                }
            }
        });
        JScrollPane scrollPane = new JScrollPane(classTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton refreshButton = new JButton("Refresh List");
        JButton exitButton = new JButton("Exit System");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshTable();
            }
        });
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        // Add informational label about postconditions
        JLabel infoLabel = new JLabel(
            "<html><div style='text-align: center;'>" +
            "<b>Postconditions met:</b><br>" +
            "1. The user has deleted a class<br>" +
            "2. Connection to the SMOS server was interrupted after deletion" +
            "</div></html>",
            SwingConstants.CENTER
        );
        infoLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        infoLabel.setFont(new Font("Arial", Font.PLAIN, 12));
        infoLabel.setForeground(new Color(0, 100, 0));
        mainPanel.add(infoLabel, BorderLayout.NORTH);
    }
    /**
     * Refreshes the table with current data from database.
     */
    private void refreshTable() {
        Object[][] newData = db.getClassData();
        tableModel.setDataVector(newData, db.getColumnNames());
        JOptionPane.showMessageDialog(this, 
            "Class list refreshed. Total classes: " + newData.length,
            "Refresh Complete", 
            JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Opens the details view for a specific class.
     * @param classId The ID of the class to view
     */
    private void viewClassDetails(int classId) {
        dispose(); // Close current list window
        ClassDetailsFrame detailsFrame = new ClassDetailsFrame(db, classId);
        detailsFrame.setVisible(true);
    }
}