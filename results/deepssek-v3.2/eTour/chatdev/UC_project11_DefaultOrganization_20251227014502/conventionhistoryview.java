/**
 * View class for Convention History.
 * This class provides the user interface for viewing convention history.
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionListener;
public class ConventionHistoryView extends JFrame {
    private JTextField pointOfRestField;
    private JButton loadButton;
    private JButton connectButton;
    private JButton disconnectButton;
    private JTable conventionTable;
    private DefaultTableModel tableModel;
    private JLabel statusLabel;
    private JLabel dbInfoLabel;
    public ConventionHistoryView() {
        setTitle("View Convention History - ETOUR System");
        setSize(900, 650);
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Create top panel for controls
        JPanel topPanel = new JPanel(new GridBagLayout());
        topPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        // Point of Rest input
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel pointOfRestLabel = new JLabel("Point of Rest (Restaurant):");
        topPanel.add(pointOfRestLabel, gbc);
        gbc.gridx = 1;
        gbc.gridwidth = 2;
        gbc.weightx = 1.0;
        pointOfRestField = new JTextField(25);
        pointOfRestField.setToolTipText("Enter restaurant name to view convention history");
        // Set default sample value for user convenience
        pointOfRestField.setText("The Gourmet Hub");
        topPanel.add(pointOfRestField, gbc);
        // Buttons
        gbc.gridx = 3;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        loadButton = new JButton("Load Conventions");
        loadButton.setToolTipText("Load convention history for the specified restaurant");
        topPanel.add(loadButton, gbc);
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        connectButton = new JButton("Connect to Server");
        connectButton.setBackground(new Color(144, 238, 144));
        topPanel.add(connectButton, gbc);
        gbc.gridx = 1;
        disconnectButton = new JButton("Disconnect");
        disconnectButton.setBackground(new Color(255, 182, 193));
        topPanel.add(disconnectButton, gbc);
        // Database info label
        gbc.gridx = 2;
        gbc.gridwidth = 2;
        dbInfoLabel = new JLabel("Using: H2 In-Memory Database");
        dbInfoLabel.setForeground(Color.BLUE);
        topPanel.add(dbInfoLabel, gbc);
        // Create table for displaying conventions
        String[] columnNames = {"ID", "Convention Name", "Restaurant", "Date", "Description"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table non-editable
            }
            @Override
            public Class<?> getColumnClass(int columnIndex) {
                // Proper column type handling for sorting
                if (columnIndex == 0) return Integer.class;
                return String.class;
            }
        };
        conventionTable = new JTable(tableModel);
        conventionTable.setRowHeight(25);
        conventionTable.setAutoCreateRowSorter(true);
        conventionTable.getTableHeader().setReorderingAllowed(false);
        JScrollPane tableScrollPane = new JScrollPane(conventionTable);
        tableScrollPane.setBorder(BorderFactory.createTitledBorder("Convention History"));
        // Create bottom panel for status
        JPanel bottomPanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        bottomPanel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10));
        JLabel statusTextLabel = new JLabel("Server Status:");
        statusLabel = new JLabel("Disconnected");
        statusLabel.setForeground(Color.RED);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(0, 10, 0, 20));
        JLabel instructionLabel = new JLabel("Enter restaurant name and click 'Load Conventions'");
        instructionLabel.setForeground(Color.DARK_GRAY);
        instructionLabel.setFont(new Font("Arial", Font.ITALIC, 12));
        bottomPanel.add(statusTextLabel);
        bottomPanel.add(statusLabel);
        bottomPanel.add(Box.createHorizontalStrut(50));
        bottomPanel.add(instructionLabel);
        // Add components to frame
        add(topPanel, BorderLayout.NORTH);
        add(tableScrollPane, BorderLayout.CENTER);
        add(bottomPanel, BorderLayout.SOUTH);
        // Center the window on screen
        setLocationRelativeTo(null);
    }
    /**
     * Get the point of rest entered by the user.
     * @return The point of rest string
     */
    public String getPointOfRest() {
        return pointOfRestField.getText().trim();
    }
    /**
     * Update the convention table with new data.
     * @param conventions List of conventions to display
     */
    public void updateConventionTable(List<ConventionHistoryModel.Convention> conventions) {
        tableModel.setRowCount(0); // Clear existing rows
        for (ConventionHistoryModel.Convention convention : conventions) {
            Object[] row = {
                convention.getId(),
                convention.getName(),
                convention.getPointOfRest(),
                convention.getDate(),
                convention.getDescription()
            };
            tableModel.addRow(row);
        }
    }
    /**
     * Update the server connection status label.
     * @param connected true if connected, false otherwise
     */
    public void updateStatusLabel(boolean connected) {
        if (connected) {
            statusLabel.setText("Connected");
            statusLabel.setForeground(new Color(0, 153, 0)); // Dark green
        } else {
            statusLabel.setText("Disconnected");
            statusLabel.setForeground(Color.RED);
        }
    }
    /**
     * Update database information label.
     * @param dbType The database type being used
     */
    public void updateDbInfoLabel(String dbType) {
        dbInfoLabel.setText("Using: " + dbType);
        if (dbType.contains("H2")) {
            dbInfoLabel.setForeground(Color.BLUE);
        } else {
            dbInfoLabel.setForeground(new Color(139, 0, 0)); // Dark red for MySQL
        }
    }
    /**
     * Show an error message dialog.
     * @param message The error message to display
     */
    public void showError(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
    }
    /**
     * Show an information message dialog.
     * @param message The information message to display
     */
    public void showInfo(String message) {
        JOptionPane.showMessageDialog(this, message, "Information", JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Add action listener for load button.
     * @param listener The action listener
     */
    public void addLoadButtonListener(ActionListener listener) {
        loadButton.addActionListener(listener);
    }
    /**
     * Add action listener for connect button.
     * @param listener The action listener
     */
    public void addConnectButtonListener(ActionListener listener) {
        connectButton.addActionListener(listener);
    }
    /**
     * Add action listener for disconnect button.
     * @param listener The action listener
     */
    public void addDisconnectButtonListener(ActionListener listener) {
        disconnectButton.addActionListener(listener);
    }
    /**
     * Clear the convention table.
     */
    public void clearTable() {
        tableModel.setRowCount(0);
    }
}