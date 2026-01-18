/*
 Main address management interface
 Displays the address list from archive and handles server connection interruption
 */
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class AddressManagerFrame extends JFrame {
    private SMOSServerConnection serverConnection;
    private AddressDatabase addressDatabase;
    private JTable addressTable;
    private DefaultTableModel tableModel;
    private JLabel serverStatusLabel; // Field to access server status label
    public AddressManagerFrame(SMOSServerConnection serverConnection) {
        this.serverConnection = serverConnection;
        this.addressDatabase = new AddressDatabase();
        initializeUI();
        loadAddresses();
        // Display the address list first (events sequence step 1)
        System.out.println("Address list displayed successfully.");
        // Schedule server interruption after UI is fully rendered
        SwingUtilities.invokeLater(() -> {
            serverConnection.interruptConnection();
            updateServerStatusInUI();
            System.out.println("Connection to SMOS server interrupted as per postconditions.");
        });
    }
    private void initializeUI() {
        setTitle("Address Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        // Create main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Create title label
        JLabel titleLabel = new JLabel("Address List");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        mainPanel.add(titleLabel, BorderLayout.NORTH);
        // Create address table
        String[] columnNames = {"Name", "Street", "City", "State", "Zip Code", "Country"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make table read-only
            }
        };
        addressTable = new JTable(tableModel);
        addressTable.setRowHeight(30);
        addressTable.getTableHeader().setFont(new Font("Arial", Font.BOLD, 14));
        JScrollPane scrollPane = new JScrollPane(addressTable);
        scrollPane.setBorder(BorderFactory.createTitledBorder("Address Archive"));
        mainPanel.add(scrollPane, BorderLayout.CENTER);
        // Create bottom panel for status and buttons
        JPanel bottomPanel = new JPanel(new BorderLayout());
        // Status labels
        JPanel statusPanel = new JPanel(new GridLayout(2, 1));
        JLabel countLabel = new JLabel("Total addresses: " + addressDatabase.getAddressCount());
        serverStatusLabel = new JLabel("Server Status: " + serverConnection.getServerStatus());
        serverStatusLabel.setForeground(Color.BLUE); // Start with blue (connected)
        statusPanel.add(countLabel);
        statusPanel.add(serverStatusLabel);
        bottomPanel.add(statusPanel, BorderLayout.WEST);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                refreshAddressList();
            }
        });
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                System.exit(0);
            }
        });
        buttonPanel.add(refreshButton);
        buttonPanel.add(exitButton);
        bottomPanel.add(buttonPanel, BorderLayout.EAST);
        mainPanel.add(bottomPanel, BorderLayout.SOUTH);
        add(mainPanel);
    }
    private void loadAddresses() {
        // Clear existing data
        tableModel.setRowCount(0);
        // Load addresses from database
        List<Address> addresses = addressDatabase.getAllAddresses();
        for (Address address : addresses) {
            Object[] row = {
                address.getName(),
                address.getStreet(),
                address.getCity(),
                address.getState(),
                address.getZipCode(),
                address.getCountry()
            };
            tableModel.addRow(row);
        }
        System.out.println("Address list loaded successfully. Showing " + addresses.size() + " addresses.");
    }
    private void refreshAddressList() {
        // Clear and reload addresses
        tableModel.setRowCount(0);
        loadAddresses();
        JOptionPane.showMessageDialog(this, 
            "Address list refreshed!", 
            "Refresh", JOptionPane.INFORMATION_MESSAGE);
    }
    private void updateServerStatusInUI() {
        // Update the server status label to reflect interruption
        if (serverStatusLabel != null) {
            serverStatusLabel.setText("Server Status: " + serverConnection.getServerStatus());
            serverStatusLabel.setForeground(Color.RED); // Red for disconnected
            System.out.println("Server status updated in UI: " + serverConnection.getServerStatus());
        }
    }
}