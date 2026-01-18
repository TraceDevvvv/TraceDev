/**
 * Main registry screen showing absences in red (unjustified)
 * User has already performed "SviewTetTingloregister" and "viewellacogiustifies"
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import java.awt.event.MouseAdapter;
import java.awt.event.MouseEvent;
public class RegistryScreen extends JFrame {
    private JTable absenceTable;
    private DefaultTableModel tableModel;
    public RegistryScreen() {
        setTitle("Absence Registry - InsertJustification System");
        setSize(800, 400);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLocationRelativeTo(null);
        initComponents();
        loadAbsences();
    }
    private void initComponents() {
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Title
        JLabel titleLabel = new JLabel("Absence Registry", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Table for absences
        String[] columns = {"ID", "Employee Name", "Absence Date", "Reason", "Status"};
        tableModel = new DefaultTableModel(columns, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
        };
        absenceTable = new JTable(tableModel);
        absenceTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        absenceTable.setRowHeight(25);
        // Add double-click listener to select absence in red
        absenceTable.addMouseListener(new MouseAdapter() {
            @Override
            public void mouseClicked(MouseEvent e) {
                if (e.getClickCount() == 2) {
                    int row = absenceTable.rowAtPoint(e.getPoint());
                    if (row != -1) {
                        absenceTable.setRowSelectionInterval(row, row);
                        insertJustification();
                    }
                }
            }
        });
        // Custom renderer to show unjustified absences in red
        absenceTable.setDefaultRenderer(Object.class, new javax.swing.table.DefaultTableCellRenderer() {
            @Override
            public Component getTableCellRendererComponent(JTable table, Object value,
                    boolean isSelected, boolean hasFocus, int row, int column) {
                Component c = super.getTableCellRendererComponent(table, value, 
                        isSelected, hasFocus, row, column);
                // Check if this row represents an unjustified absence
                String status = (String) table.getValueAt(row, 4);
                if ("Not Justified".equals(status)) {
                    c.setForeground(Color.RED);
                    c.setFont(c.getFont().deriveFont(Font.BOLD));
                } else {
                    c.setForeground(Color.BLACK);
                    c.setFont(c.getFont().deriveFont(Font.PLAIN));
                }
                if (isSelected) {
                    c.setBackground(Color.LIGHT_GRAY);
                } else {
                    c.setBackground(Color.WHITE);
                }
                return c;
            }
        });
        JScrollPane scrollPane = new JScrollPane(absenceTable);
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        JButton insertJustificationButton = new JButton("Insert Justification");
        JButton refreshButton = new JButton("Refresh");
        JButton logoutButton = new JButton("Logout");
        insertJustificationButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                insertJustification();
            }
        });
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                loadAbsences();
            }
        });
        logoutButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                logout();
            }
        });
        buttonPanel.add(insertJustificationButton);
        buttonPanel.add(refreshButton);
        buttonPanel.add(logoutButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void loadAbsences() {
        // Clear existing data
        tableModel.setRowCount(0);
        // Get unjustified absences from database
        List<Absence> absences = DatabaseSimulator.getInstance().getUnjustifiedAbsences();
        // Add rows to table
        for (Absence absence : absences) {
            tableModel.addRow(new Object[]{
                absence.getId(),
                absence.getEmployeeName(),
                absence.getAbsenceDate().toString(),
                absence.getReason(),
                absence.isJustified() ? "Justified" : "Not Justified"
            });
        }
    }
    private void insertJustification() {
        int selectedRow = absenceTable.getSelectedRow();
        if (selectedRow == -1) {
            JOptionPane.showMessageDialog(this,
                "Please select an absence (in red) to justify.",
                "No Selection",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Get selected absence ID
        int absenceId = (int) tableModel.getValueAt(selectedRow, 0);
        // Check if the selected absence is already justified
        String status = (String) tableModel.getValueAt(selectedRow, 4);
        if ("Justified".equals(status)) {
            JOptionPane.showMessageDialog(this,
                "This absence is already justified. Please select an unjustified absence (in red).",
                "Already Justified",
                JOptionPane.WARNING_MESSAGE);
            return;
        }
        // Open justification form
        InsertJustificationForm form = new InsertJustificationForm(this, absenceId);
        form.setVisible(true);
    }
    private void logout() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to logout?",
            "Confirm Logout",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            // Administrator interrupts the operation
            LoginFrame loginFrame = new LoginFrame();
            loginFrame.setVisible(true);
            this.dispose();
        }
    }
}