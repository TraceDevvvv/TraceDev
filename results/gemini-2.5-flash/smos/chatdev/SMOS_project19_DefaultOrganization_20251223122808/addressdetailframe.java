'''
Implements the GUI for viewing detailed information about a single address.
'''
package com.chatdev.gui;
import com.chatdev.model.Address;
import com.chatdev.service.AddressService;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
/**
 * This JFrame displays the detailed information of a single address.
 * It fulfills the "ViewAddressDetails" use case, showing elements like the address name.
 */
public class AddressDetailFrame extends JFrame implements ActionListener {
    private AddressService addressService;
    private String addressId;
    // Labels to display address details. These are instance variables so they can be updated.
    private JLabel idLabel;
    private JLabel nameLabel;
    private JLabel streetLabel;
    private JLabel cityLabel;
    private JLabel zipCodeLabel;
    private JLabel countryLabel;
    private JButton closeButton;
    /**
     * Constructs an AddressDetailFrame for a specific address.
     * It fetches and displays the address details upon creation.
     *
     * @param service The address service to fetch address details.
     * @param addressId The ID of the address to display.
     */
    public AddressDetailFrame(AddressService service, String addressId) {
        super("Address Details");
        this.addressService = service;
        this.addressId = addressId;
        // --- Frame Setup ---
        setDefaultCloseOperation(JFrame.DISPOSE_ON_CLOSE); // Close only this window, not the entire application
        setSize(450, 280); // Set a fixed size for the detail frame
        setResizable(false); // Make the window not resizable to maintain layout integrity
        // Use GridBagLayout for a flexible and robust layout that can align components well
        setLayout(new GridBagLayout()); 
        // --- Components Initialization ---
        // Initialize JLabel components which will hold the address data
        idLabel = new JLabel();
        nameLabel = new JLabel();
        streetLabel = new JLabel();
        cityLabel = new JLabel();
        zipCodeLabel = new JLabel();
        countryLabel = new JLabel();
        closeButton = new JButton("Close");
        closeButton.addActionListener(this); // Register action listener for the Close button
        // --- Layout Management with GridBagLayout ---
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around component cells for spacing
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components expand horizontally to fill cell space
        // Add a title label at the top of the frame
        JLabel titleLabel = new JLabel("Detailed Address Information", SwingConstants.CENTER);
        titleLabel.setFont(new Font("SansSerif", Font.BOLD, 16)); // Style the title
        gbc.gridx = 0; // Start at column 0
        gbc.gridy = 0; // Start at row 0
        gbc.gridwidth = 2; // Span across two columns for the title
        add(titleLabel, gbc);
        gbc.gridwidth = 1; // Reset grid width for subsequent data labels to single column
        // Row 1: Address ID
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.EAST; // Align label text to the right
        add(new JLabel("Address ID:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Align data text to the left
        add(idLabel, gbc);
        // Row 2: Address Name (Specifically requested in use case description)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Address Name:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameLabel.setFont(nameLabel.getFont().deriveFont(Font.BOLD, 14f)); // Make address name bold for emphasis
        add(nameLabel, gbc);
        // Row 3: Street
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Street:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(streetLabel, gbc);
        // Row 4: City
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("City:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(cityLabel, gbc);
        // Row 5: Zip Code
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Zip Code:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(zipCodeLabel, gbc);
        // Row 6: Country
        gbc.gridx = 0;
        gbc.gridy = 6;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Country:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        add(countryLabel, gbc);
        // Row 7: Close Button
        gbc.gridx = 0;
        gbc.gridy = 7;
        gbc.gridwidth = 2; // Button spans across two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the button horizontally
        gbc.insets = new Insets(15, 5, 5, 5); // More top padding for separation from data
        add(closeButton, gbc);
        // Load address details after the GUI components have been laid out
        loadAddressDetails();
    }
    /**
     * Fetches the detailed information for the specified address ID
     * using the AddressService and populates the UI labels.
     * This method fulfills the "Displays the screen with the details of a single address"
     * and "The user displays detailed information relating to a single address" requirements.
     * It also handles the edge case where the address ID might not be found.
     */
    private void loadAddressDetails() {
        Address address = addressService.getAddressById(addressId);
        if (address != null) {
            // Postcondition: User displays detailed information relating to a single address.
            // Populate labels with the retrieved address data.
            idLabel.setText(address.getId());
            nameLabel.setText(address.getName()); // Populating the specifically requested "address name"
            streetLabel.setText(address.getStreet());
            cityLabel.setText(address.getCity());
            zipCodeLabel.setText(address.getZipCode());
            countryLabel.setText(address.getCountry());
        } else {
            // Handle edge case: address ID was not found by the service.
            JOptionPane.showMessageDialog(this,
                    "Address with ID '" + addressId + "' not found.",
                    "Address Not Found",
                    JOptionPane.ERROR_MESSAGE);
            // Close this detail frame if the address could not be found to prevent displaying an empty or erroneous screen.
            this.dispose();
        }
    }
    /**
     * Handles action events, specifically the 'Close' button click.
     * When the 'Close' button is clicked, the detail frame will be closed.
     *
     * @param e The ActionEvent generated.
     */
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource() == closeButton) {
            this.dispose(); // Close only the current detail frame window
        }
    }
}