/**
 * Provides the graphical user interface for the administrator to
 * view existing addresses and enter new ones.
 * Implements the "EnterNewAddress" use case.
 * Uses Swing for GUI components.
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class AdministratorConsole extends JFrame {
    private final AddressService addressService;
    // GUI Components for different views
    private JPanel mainPanel;
    private CardLayout cardLayout;
    // Components for "ViewingLenchindirizzi" (viewing addresses)
    private JPanel viewingAddressesPanel;
    private JList<String> addressListDisplay;
    private DefaultListModel<String> addressListModel;
    private JButton newAddressButton;
    private JButton simulateServerErrorButton;
    // Components for "EnterNewAddress" (new address form)
    private JPanel newAddressPanel;
    private JTextField addressNameField;
    private JButton saveButton;
    private JButton cancelButton;
    private JLabel messageLabel; // To display success/error messages
    /**
     * Constructs the AdministratorConsole GUI.
     * Initializes the AddressService and sets up all Swing components.
     */
    public AdministratorConsole() {
        this.addressService = new AddressService();
        initializeUI();
    }
    /**
     * Initializes all GUI components and sets up the layout.
     */
    private void initializeUI() {
        setTitle("Address Management System - Administrator");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLocationRelativeTo(null); // Center the window
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Create the panels for different views
        createViewingAddressesPanel();
        createNewAddressPanel();
        // Add panels to the main card layout
        mainPanel.add(viewingAddressesPanel, "VIEW_ADDRESSES");
        mainPanel.add(newAddressPanel, "NEW_ADDRESS");
        add(mainPanel);
        // Show the initial view (viewing addresses)
        showViewingAddresses();
    }
    /**
     * Creates and configures the panel for viewing existing addresses.
     * This simulates the "ViewingLenchindirizzi" state.
     */
    private void createViewingAddressesPanel() {
        viewingAddressesPanel = new JPanel(new BorderLayout(10, 10));
        viewingAddressesPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Label for the view title
        JLabel titleLabel = new JLabel("Existing Addresses", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        viewingAddressesPanel.add(titleLabel, BorderLayout.NORTH);
        // List to display addresses
        addressListModel = new DefaultListModel<>();
        addressListDisplay = new JList<>(addressListModel);
        addressListDisplay.setSelectionMode(ListSelectionModel.SINGLE_SELECTION);
        addressListDisplay.setVisibleRowCount(10);
        JScrollPane scrollPane = new JScrollPane(addressListDisplay);
        viewingAddressesPanel.add(scrollPane, BorderLayout.CENTER);
        // Panel for buttons at the bottom
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        newAddressButton = new JButton("New Address");
        newAddressButton.addActionListener(e -> showNewAddressForm());
        buttonPanel.add(newAddressButton);
        simulateServerErrorButton = new JButton("Toggle Server Error Sim (" + (addressService.isSimulateServerError() ? "ON" : "OFF") + ")");
        simulateServerErrorButton.addActionListener(e -> toggleServerErrorSimulation());
        buttonPanel.add(simulateServerErrorButton);
        viewingAddressesPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Refresh the list initially
        refreshAddressList();
    }
    /**
     * Creates and configures the panel for entering a new address.
     * This simulates the form completion step in the use case.
     */
    private void createNewAddressPanel() {
        newAddressPanel = new JPanel(new GridBagLayout());
        newAddressPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Form Title
        JLabel formTitleLabel = new JLabel("Enter New Address", SwingConstants.CENTER);
        formTitleLabel.setFont(new Font("Serif", Font.BOLD, 18));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        newAddressPanel.add(formTitleLabel, gbc);
        // Address Name Label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 1;
        newAddressPanel.add(new JLabel("Address Name:"), gbc);
        // Address Name Text Field
        addressNameField = new JTextField(30);
        gbc.gridx = 1;
        gbc.gridy = 1;
        newAddressPanel.add(addressNameField, gbc);
        // Message Label (for errors/success)
        messageLabel = new JLabel(" ");
        messageLabel.setForeground(Color.RED); // Default to red for errors
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        newAddressPanel.add(messageLabel, gbc);
        // Buttons Panel (Save and Cancel)
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT));
        saveButton = new JButton("Save");
        saveButton.addActionListener(e -> saveNewAddress());
        buttonPanel.add(saveButton);
        cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(e -> {
            clearForm(); // Clear form on cancel
            showViewingAddresses(); // Return to the list view
            displayMessage("", false); // Clear any pending messages
        });
        buttonPanel.add(cancelButton);
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2;
        newAddressPanel.add(buttonPanel, gbc);
    }
    /**
     * Switches the view to the panel displaying the list of addresses.
     * Corresponds to the "ViewingLenchindirizzi" use case.
     */
    private void showViewingAddresses() {
        // Ensure any previous messages are cleared
        displayMessage("", false);
        refreshAddressList(); // Refresh list to show newly added addresses
        cardLayout.show(mainPanel, "VIEW_ADDRESSES");
    }
    /**
     * Switches the view to the form for entering a new address.
     * This is triggered when the "New Address" button is clicked.
     */
    private void showNewAddressForm() {
        // Ensure form is clear when opening
        clearForm();
        // Clear any previous error/success messages
        displayMessage("", false);
        cardLayout.show(mainPanel, "NEW_ADDRESS");
    }
    /**
     * Handles the logic for saving a new address.
     * This method is called when the "Save" button is clicked.
     * It validates input, interacts with the AddressService, and handles postconditions.
     */
    private void saveNewAddress() {
        String addressName = addressNameField.getText().trim();
        try {
            // 4. Checks on the validity of the data entered and inserts a new address in the archive
            // If validation passes and no simulated server error, save the address
            Address newAddress = new Address(addressName);
            addressService.saveAddress(newAddress); // This will throw an exception on error
            // Postcondition: The user has entered an address.
            // Display success message using JOptionPane for clearer notification
            JOptionPane.showMessageDialog(this,
                    "Address '" + addressName + "' entered successfully into the archive.",
                    "Address Saved",
                    JOptionPane.INFORMATION_MESSAGE);
            clearForm(); // Clear the form fields after successful entry
            showViewingAddresses(); // Return to the address list view
        } catch (AddressServiceException ex) {
            // In the event that the data entered are not valid, activates the case of "Errodati" use.
            // Postcondition: is notified the connection data error to the SMOS server interrupted
            // if it's a server error, or validation error.
            displayMessage(ex.getMessage(), false); // Display the specific error message on the form
            // Administrator interrupts the operation (by seeing the error and potentially correcting/canceling)
        }
    }
    /**
     * Toggles the simulation of a server error in the AddressService.
     * Updates the button text to reflect the current state.
     */
    private void toggleServerErrorSimulation() {
        boolean currentState = addressService.isSimulateServerError();
        addressService.setSimulateServerError(!currentState);
        simulateServerErrorButton.setText("Toggle Server Error Sim (" + (addressService.isSimulateServerError() ? "ON" : "OFF") + ")");
        JOptionPane.showMessageDialog(this,
                "Server error simulation is now: " + (addressService.isSimulateServerError() ? "ON" : "OFF"),
                "Simulation Status",
                JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Refreshes the `addressListModel` with the latest data from the `AddressService`.
     */
    private void refreshAddressList() {
        addressListModel.clear(); // Clear existing items
        List<Address> addresses = addressService.getAllAddresses();
        for (Address address : addresses) {
            addressListModel.addElement(address.getAddressName());
        }
    }
    /**
     * Clears the input fields in the new address form.
     */
    private void clearForm() {
        addressNameField.setText("");
        messageLabel.setText(" "); // Clear message
    }
    /**
     * Displays a message to the user, either success or error.
     *
     * @param message The message to display.
     * @param isSuccess true if it's a success message, false for an error message.
     */
    private void displayMessage(String message, boolean isSuccess) {
        messageLabel.setText(message);
        messageLabel.setForeground(isSuccess ? new Color(0, 128, 0) : Color.RED); // Green for success, Red for error
    }
}