/**
 * Main application class for the Address Management GUI.
 * This class extends JFrame to create the main window and handles user interactions
 * for viewing address details and attempting to delete addresses as an administrator.
 */
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class AddressApp extends JFrame {
    private AddressService addressService; // Service layer for address business logic
    private JList<Address> addressList; // Displays a list of addresses
    private DefaultListModel<Address> addressListModel; // Model for the JList
    private JTextArea detailsTextArea; // Displays detailed information of the selected address
    private JButton deleteButton; // Button to trigger the address deletion
    private JLabel statusLabel; // Displays status messages or error feedback to the user
    /**
     * Constructor for the AddressApp GUI.
     * Initializes the service, sets up the main window, and arranges GUI components.
     */
    public AddressApp() {
        super("Address Management System - Admin Panel"); // Set window title
        addressService = new AddressService(); // Initialize the address service
        // Configure the main window
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout with gaps
        // --- Addresses List Panel ---
        JPanel listPanel = new JPanel(new BorderLayout());
        listPanel.setBorder(BorderFactory.createTitledBorder("Available Addresses"));
        addressListModel = new DefaultListModel<>();
        addressList = new JList<>(addressListModel);
        addressList.setSelectionMode(ListSelectionModel.SINGLE_SELECTION); // Allow only one selection at a time
        addressList.setFont(new Font("SansSerif", Font.PLAIN, 14));
        // Add a listener to display details when an address is selected
        addressList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure the selection is final
                    Address selectedAddress = addressList.getSelectedValue();
                    if (selectedAddress != null) {
                        displayAddressDetails(selectedAddress);
                        statusLabel.setText("Selected address details are displayed.");
                    } else {
                        // Clear details if no address is selected
                        detailsTextArea.setText("");
                        statusLabel.setText("No address selected.");
                    }
                }
            }
        });
        listPanel.add(new JScrollPane(addressList), BorderLayout.CENTER); // Add list to scroll pane
        add(listPanel, BorderLayout.WEST); // Place the list on the left
        // --- Details and Actions Panel ---
        JPanel detailsAndActionsPanel = new JPanel(new BorderLayout(5, 5));
        detailsAndActionsPanel.setBorder(BorderFactory.createTitledBorder("Address Details and Actions"));
        // Details Text Area
        detailsTextArea = new JTextArea();
        detailsTextArea.setEditable(false);
        detailsTextArea.setLineWrap(true);
        detailsTextArea.setWrapStyleWord(true);
        detailsTextArea.setFont(new Font("Monospaced", Font.PLAIN, 14));
        detailsAndActionsPanel.add(new JScrollPane(detailsTextArea), BorderLayout.CENTER);
        // Action Panel (for buttons)
        JPanel actionPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        deleteButton = new JButton("Delete Address");
        deleteButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Add action listener for the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                deleteAddressAction();
            }
        });
        actionPanel.add(deleteButton);
        detailsAndActionsPanel.add(actionPanel, BorderLayout.SOUTH);
        add(detailsAndActionsPanel, BorderLayout.CENTER); // Place details and actions in the center
        // --- Status Bar ---
        statusLabel = new JLabel("Welcome, Administrator! Please select an address to view its details.");
        statusLabel.setBorder(BorderFactory.createEmptyBorder(5, 10, 5, 10)); // Add some padding
        add(statusLabel, BorderLayout.SOUTH); // Place status bar at the bottom
        // Initial population of the address list
        displayAddresses();
        // Simulate precondition: user has taken the case of use "viewdettaglizzazione" which means addresses are displayed and selectable.
        // Also system is ready for user to display detailed information and click delete.
    }
    /**
     * Populates the JList with addresses retrieved from the AddressService.
     * This method is called upon initialization and after any address deletion.
     */
    private void displayAddresses() {
        addressListModel.clear(); // Clear existing items
        List<Address> addresses = addressService.getAllAddresses();
        for (Address address : addresses) {
            addressListModel.addElement(address); // Add each address to the list model
        }
        detailsTextArea.setText(""); // Clear details when list is refreshed
        statusLabel.setText("Addresses loaded successfully.");
    }
    /**
     * Displays the detailed information of a given address in the details text area.
     * @param address The Address object whose details are to be displayed.
     */
    private void displayAddressDetails(Address address) {
        if (address == null) {
            detailsTextArea.setText("");
            return;
        }
        // Format the details for display
        String details = String.format(
            "Address ID: %s\nStreet: %s\nCity: %s\nHas Associated Classes: %s",
            address.getId(),
            address.getStreet(),
            address.getCity(),
            address.hasAssociatedClasses() ? "Yes" : "No"
        );
        detailsTextArea.setText(details);
    }
    /**
     * Handles the action when the 'Delete Address' button is clicked.
     * Implements the core logic of the use case: checking associated classes,
     * deleting the address if allowed, and updating the UI.
     */
    private void deleteAddressAction() {
        Address selectedAddress = addressList.getSelectedValue();
        // Precondition: user has selected an address
        if (selectedAddress == null) {
            JOptionPane.showMessageDialog(this, "Please select an address to delete.", "Deletion Error", JOptionPane.WARNING_MESSAGE);
            statusLabel.setText("Deletion failed: No address selected.");
            return;
        }
        String addressIdToDelete = selectedAddress.getId();
        // Step 1: Check if the address has associated classes
        if (addressService.hasAssociatedClasses(addressIdToDelete)) {
            // If so, show an error message
            JOptionPane.showMessageDialog(this,
                    "Unable to delete the address, delete the associated classes and try again.",
                    "Deletion Blocked",
                    JOptionPane.ERROR_MESSAGE);
            statusLabel.setText("Deletion failed: Address has associated classes.");
        } else {
            // Otherwise, proceed to delete the address after user confirmation
            int confirm = JOptionPane.showConfirmDialog(this,
                    "Are you sure you want to delete address: " + selectedAddress.toString() + "?",
                    "Confirm Deletion",
                    JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                boolean deleted = addressService.deleteAddress(addressIdToDelete);
                if (deleted) {
                    // Update the list of addresses
                    displayAddresses();
                    detailsTextArea.setText(""); // Clear details after deletion
                    statusLabel.setText("Address '" + selectedAddress.toString() + "' successfully deleted.");
                    // Postcondition: Connection to the interrupted SMOS server (simulated)
                    // In a real system, this might involve logging or a specific network call.
                    System.out.println("SIMULATION: Connection to SMOS server interrupted for deletion process.");
                    JOptionPane.showMessageDialog(this,
                            "Address deleted. Connection to SMOS server interrupted (simulated).",
                            "Deletion Successful",
                            JOptionPane.INFORMATION_MESSAGE);
                } else {
                    // This case should ideally not be hit if hasAssociatedClasses was checked properly
                    // but serves as a fallback for unexpected service failures (e.g., address not found anymore).
                    JOptionPane.showMessageDialog(this,
                            "Failed to delete the address due to an unexpected error or address not found.",
                            "Deletion Error",
                            JOptionPane.ERROR_MESSAGE);
                    statusLabel.setText("Deletion failed: Unexpected error.");
                }
            } else {
                statusLabel.setText("Address deletion cancelled by user.");
            }
        }
    }
    /**
     * Main method to run the application.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread (EDT)
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new AddressApp().setVisible(true); // Create and show the main application window
            }
        });
    }
}