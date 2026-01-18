/**
 * Main class for the Address Management System.
 * Provides a GUI for administrators to view and delete addresses.
 * Implements the "Delete address" use case as specified.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.*;
import java.util.HashMap;
import java.util.Map;
import java.util.List;
import java.util.ArrayList;
public class main {
    private JFrame frame;
    private JList<String> addressList;
    private DefaultListModel<String> listModel;
    private Map<String, Address> addressMap;
    private JButton deleteButton;
    private JLabel statusLabel;
    private JTextArea detailsArea;
    /**
     * Constructor to initialize the GUI and data.
     */
    public main() {
        // Initialize the address storage with sample data
        addressMap = new HashMap<>();
        listModel = new DefaultListModel<>();
        // Sample addresses (ID, street, city, hasAssociatedClasses)
        addressMap.put("1", new Address("1", "123 Main St", "Springfield", false));
        addressMap.put("2", new Address("2", "456 Oak Ave", "Shelbyville", true));
        addressMap.put("3", new Address("3", "789 Pine Rd", "Capital City", false));
        // Populate the list model for display
        refreshListModel();
        initializeGUI();
    }
    /**
     * Refreshes the list model from the address map
     */
    private void refreshListModel() {
        listModel.clear();
        for (Address addr : addressMap.values()) {
            listModel.addElement(addr.getDisplayString());
        }
    }
    /**
     * Sets up the GUI components and layout.
     */
    private void initializeGUI() {
        frame = new JFrame("Address Management - Administrator");
        frame.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        frame.setSize(600, 500);
        frame.setLayout(new BorderLayout());
        // Title label
        JLabel titleLabel = new JLabel("Address Archive", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        frame.add(titleLabel, BorderLayout.NORTH);
        // Main content panel with split pane
        JSplitPane splitPane = new JSplitPane(JSplitPane.HORIZONTAL_SPLIT);
        splitPane.setDividerLocation(300);
        // Left panel: Address list
        JPanel leftPanel = new JPanel(new BorderLayout());
        leftPanel.setBorder(BorderFactory.createTitledBorder("Address List"));
        addressList = new JList<>(listModel);
        addressList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addressList.addListSelectionListener(e -> {
            if (!e.getValueIsAdjusting()) {
                displayAddressDetails();
            }
        });
        JScrollPane listScrollPane = new JScrollPane(addressList);
        leftPanel.add(listScrollPane, BorderLayout.CENTER);
        // Right panel: Address details
        JPanel rightPanel = new JPanel(new BorderLayout());
        rightPanel.setBorder(BorderFactory.createTitledBorder("Address Details"));
        detailsArea = new JTextArea();
        detailsArea.setEditable(false);
        detailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        detailsArea.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        JScrollPane detailsScrollPane = new JScrollPane(detailsArea);
        rightPanel.add(detailsScrollPane, BorderLayout.CENTER);
        splitPane.setLeftComponent(leftPanel);
        splitPane.setRightComponent(rightPanel);
        frame.add(splitPane, BorderLayout.CENTER);
        // Panel for buttons and status
        JPanel southPanel = new JPanel(new BorderLayout());
        // Status label for messages
        statusLabel = new JLabel("Select an address to view details.", SwingConstants.CENTER);
        statusLabel.setForeground(Color.BLUE);
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        southPanel.add(statusLabel, BorderLayout.NORTH);
        // Button panel
        JPanel buttonPanel = new JPanel(new FlowLayout());
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(5, 5, 5, 5));
        // Delete button - triggers address deletion
        deleteButton = new JButton("Delete Address");
        deleteButton.setEnabled(false);
        deleteButton.addActionListener(new DeleteButtonListener());
        buttonPanel.add(deleteButton);
        // Refresh button - updates the list (simulating viewdettaglizzazione step)
        JButton refreshButton = new JButton("Refresh List");
        refreshButton.addActionListener(e -> refreshAddressList());
        buttonPanel.add(refreshButton);
        // Exit button
        JButton exitButton = new JButton("Exit");
        exitButton.addActionListener(e -> System.exit(0));
        buttonPanel.add(exitButton);
        southPanel.add(buttonPanel, BorderLayout.SOUTH);
        frame.add(southPanel, BorderLayout.SOUTH);
        // Simulate preconditions: user is logged in as admin
        statusLabel.setText("Logged in as Administrator. Select an address to view details.");
        frame.setVisible(true);
    }
    /**
     * Displays detailed information about the selected address.
     * Simulates the "viewdettaglizzazione" use case.
     */
    private void displayAddressDetails() {
        int selectedIndex = addressList.getSelectedIndex();
        if (selectedIndex == -1) {
            detailsArea.setText("");
            deleteButton.setEnabled(false);
            return;
        }
        String selectedDisplay = listModel.get(selectedIndex);
        String selectedId = selectedDisplay.split(" - ")[0];
        Address selectedAddress = addressMap.get(selectedId);
        if (selectedAddress != null) {
            StringBuilder details = new StringBuilder();
            details.append("Address ID: ").append(selectedAddress.getId()).append("\n\n");
            details.append("Street: ").append(selectedAddress.getStreet()).append("\n");
            details.append("City: ").append(selectedAddress.getCity()).append("\n\n");
            details.append("Associated Classes: ").append(selectedAddress.hasAssociatedClasses() ? "YES" : "NO").append("\n\n");
            details.append("Status: ").append(selectedAddress.hasAssociatedClasses() ? 
                "Cannot delete - has associated classes" : "Ready for deletion");
            detailsArea.setText(details.toString());
            deleteButton.setEnabled(true);
            statusLabel.setText("Address details displayed. Click 'Delete' to remove.");
            statusLabel.setForeground(Color.BLUE);
        }
    }
    /**
     * Action listener for the Delete button.
     * Implements the core logic of the use case:
     * 1. Check if selected address has associated classes.
     * 2. If yes, show error message.
     * 3. If no, delete the address and update the list.
     */
    private class DeleteButtonListener implements ActionListener {
        @Override
        public void actionPerformed(ActionEvent e) {
            int selectedIndex = addressList.getSelectedIndex();
            if (selectedIndex == -1) {
                JOptionPane.showMessageDialog(frame, "Please select an address to delete.", "No Selection", JOptionPane.WARNING_MESSAGE);
                return;
            }
            String selectedDisplay = listModel.get(selectedIndex);
            String selectedId = selectedDisplay.split(" - ")[0];
            Address selectedAddress = addressMap.get(selectedId);
            // Confirm deletion
            int confirm = JOptionPane.showConfirmDialog(frame, 
                "Are you sure you want to delete this address?\n" + selectedDisplay,
                "Confirm Deletion", JOptionPane.YES_NO_OPTION);
            if (confirm != JOptionPane.YES_OPTION) {
                return;
            }
            // Step 1: Check if address has associated classes
            if (selectedAddress.hasAssociatedClasses()) {
                // Show error message as per use case
                statusLabel.setText("Error: Unable to delete the address, delete the associated classes and try again.");
                statusLabel.setForeground(Color.RED);
                JOptionPane.showMessageDialog(frame, 
                    "Unable to delete the address, delete the associated classes and try again.",
                    "Deletion Error", JOptionPane.ERROR_MESSAGE);
                detailsArea.setText(detailsArea.getText() + "\n\nDeletion failed: Address has associated classes.");
            } else {
                // Delete the address
                addressMap.remove(selectedId);
                refreshListModel();
                // Clear details area and disable delete button
                detailsArea.setText("");
                deleteButton.setEnabled(false);
                // Step 2: Display updated list
                statusLabel.setText("Address deleted successfully. List updated.");
                statusLabel.setForeground(Color.GREEN);
                // Postcondition: Simulate SMOS server connection interruption
                simulateSMOSInterruption();
                // Show success message
                JOptionPane.showMessageDialog(frame, 
                    "Address deleted successfully.\nList has been updated.",
                    "Deletion Successful", JOptionPane.INFORMATION_MESSAGE);
            }
        }
    }
    /**
     * Refreshes the address list (simulates the "viewdettaglizzazione" use case).
     * This method ensures the list is current and updates the status.
     */
    private void refreshAddressList() {
        // In a real system, this would fetch from a database or server
        // Here we just reaffirm the current data
        refreshListModel();
        detailsArea.setText("");
        deleteButton.setEnabled(false);
        statusLabel.setText("Address list refreshed. Select an address to view details.");
        statusLabel.setForeground(Color.BLUE);
        addressList.setSelectedIndex(-1);
    }
    /**
     * Simulates the postcondition: connection to the SMOS server is interrupted.
     * This is a placeholder for actual server disconnection logic.
     */
    private void simulateSMOSInterruption() {
        // In a real application, this would close network connections, release resources, etc.
        System.out.println("SMOS server connection interrupted per postcondition.");
        // Log the interruption
        System.err.println("[SMOS] Connection interrupted after address deletion.");
    }
    /**
     * Represents an address in the system.
     * Contains ID, street, city, and a flag for associated classes.
     */
    private class Address {
        private String id;
        private String street;
        private String city;
        private boolean hasAssociatedClasses;
        public Address(String id, String street, String city, boolean hasAssociatedClasses) {
            this.id = id;
            this.street = street;
            this.city = city;
            this.hasAssociatedClasses = hasAssociatedClasses;
        }
        public String getId() { return id; }
        public String getStreet() { return street; }
        public String getCity() { return city; }
        public boolean hasAssociatedClasses() { return hasAssociatedClasses; }
        /**
         * Returns a formatted string for display in the list.
         */
        public String getDisplayString() {
            return id + " - " + street + ", " + city;
        }
    }
    /**
     * Entry point of the program.
     * Creates an instance of the main class on the Event Dispatch Thread.
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new main();
            }
        });
    }
}