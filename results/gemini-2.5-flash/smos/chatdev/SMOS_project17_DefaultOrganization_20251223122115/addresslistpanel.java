'''
JPanel responsible for displaying a list of addresses in a tabular format.
This panel fetches the address data and then simulates the interruption
of the SMOS server connection as per the use case.
'''
import javax.swing.*;
import javax.swing.table.DefaultTableModel;
import java.awt.*;
import java.util.List;
public class AddressListPanel extends JPanel {
    private AddressService addressService; // Service to fetch address data
    private JTable addressTable;           // Table to display addresses
    private DefaultTableModel tableModel;  // Model for the JTable
    private JLabel statusLabel;            // Label to show connection status
    /**
     * Constructs an AddressListPanel.
     *
     * @param addressService The service used to retrieve address data.
     */
    public AddressListPanel(AddressService addressService) {
        this.addressService = addressService;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add padding
        // Title for the panel
        JLabel titleLabel = new JLabel("Address List", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 20));
        add(titleLabel, BorderLayout.NORTH);
        // Initialize table model with column headers
        String[] columnNames = {"Street", "City", "State", "Zip Code"};
        tableModel = new DefaultTableModel(columnNames, 0) {
            @Override
            public boolean isCellEditable(int row, int column) {
                return false; // Make all cells non-editable
            }
        };
        addressTable = new JTable(tableModel);
        addressTable.setFillsViewportHeight(true); // Make table take up full height
        addressTable.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Only allow single row selection
        // Add table to a JScrollPane for scrollability if many addresses
        JScrollPane scrollPane = new JScrollPane(addressTable);
        add(scrollPane, BorderLayout.CENTER);
        // Status label at the bottom for notifications
        statusLabel = new JLabel("Loading addresses...", SwingConstants.CENTER);
        statusLabel.setFont(new Font("SansSerif", Font.PLAIN, 12));
        add(statusLabel, BorderLayout.SOUTH);
        // Load addresses and interrupt connection in a new thread
        // to avoid blocking the Event Dispatch Thread (EDT) and keeping UI responsive
        loadAddressesAndInterruptConnection();
    }
    /**
     * Fetches the list of addresses using the AddressService, populates the table,
     * and then triggers the SMOS server connection interruption.
     * This method runs in a background thread to prevent UI freezing during data loading.
     */
    private void loadAddressesAndInterruptConnection() {
        // Use a SwingWorker to perform background tasks (fetching data, interrupting connection)
        // and update the UI safely on the EDT.
        SwingWorker<List<Address>, Void> worker = new SwingWorker<List<Address>, Void>() {
            @Override
            protected List<Address> doInBackground() throws Exception {
                // This code runs in a background thread
                return addressService.getAddresses();
            }
            @Override
            protected void done() {
                // This code runs on the Event Dispatch Thread (EDT) once doInBackground is complete
                try {
                    List<Address> addresses = get(); // Get the result from doInBackground
                    populateTable(addresses); // Populate table with fetched data
                    statusLabel.setText("Address list displayed. Interrupting SMOS connection...");
                    // Postcondition: "Connection to the SMOS server interrupted"
                    addressService.interruptSMOSConnection();
                    statusLabel.setText("Address list displayed. SMOS server connection interrupted.");
                } catch (Exception e) {
                    System.err.println("Failed to load addresses or interrupt connection: " + e.getMessage());
                    statusLabel.setText("Error loading addresses: " + e.getMessage());
                    JOptionPane.showMessageDialog(AddressListPanel.this,
                            "Error loading address list: " + e.getMessage(),
                            "Loading Error",
                            JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute(); // Start the background worker
    }
    /**
     * Populates the JTable with the provided list of Address objects.
     *
     * @param addresses The list of addresses to display.
     */
    private void populateTable(List<Address> addresses) {
        // Clear existing rows
        tableModel.setRowCount(0);
        // Add each address as a new row in the table
        for (Address address : addresses) {
            tableModel.addRow(new Object[]{
                    address.getStreet(),
                    address.getCity(),
                    address.getState(),
                    address.getZipCode()
            });
        }
        // Postcondition: "The system shows the list of addresses in the archive"
        System.out.println("GUI: Address list populated in table.");
    }
}