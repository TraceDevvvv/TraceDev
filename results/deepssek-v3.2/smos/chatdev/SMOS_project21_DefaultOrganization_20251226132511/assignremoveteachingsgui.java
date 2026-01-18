/**
 * Main GUI for the AssignRemoveTeachings use case.
 * Allows administrators to assign or remove teachings from addresses.
 */
import javax.swing.*;
import javax.swing.border.TitledBorder;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
public class AssignRemoveTeachingsGUI extends JFrame {
    private AddressTeachingManager manager;
    private JComboBox<String> addressComboBox;
    private JList<Teaching> currentTeachingsList;
    private JList<Teaching> availableTeachingsList;
    private DefaultListModel<Teaching> currentTeachingsModel;
    private DefaultListModel<Teaching> availableTeachingsModel;
    private JLabel addressDetailsLabel;
    private JButton assignButton;
    private JButton removeButton;
    private JButton sendButton;
    private JButton backButton;
    private CardLayout cardLayout;
    private JPanel mainPanel;
    private String currentAddressId;
    /**
     * Constructor creates the main GUI interface.
     * 
     * @param manager The AddressTeachingManager instance
     */
    public AssignRemoveTeachingsGUI(AddressTeachingManager manager) {
        this.manager = manager;
        // Set up the main window
        setTitle("Assign/Remove Teachings from Address - Administrator Console");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(1000, 700);
        setLocationRelativeTo(null);
        // Initialize components
        initializeComponents();
        // Load initial data
        loadAddresses();
    }
    /**
     * Initialize all GUI components.
     */
    private void initializeComponents() {
        // Create card layout for switching between screens
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout);
        // Create panels for different screens
        JPanel addressSelectionPanel = createAddressSelectionPanel();
        JPanel teachingManagementPanel = createTeachingManagementPanel();
        mainPanel.add(addressSelectionPanel, "addressSelection");
        mainPanel.add(teachingManagementPanel, "teachingManagement");
        add(mainPanel, BorderLayout.CENTER);
    }
    /**
     * Create the address selection panel (simulates "viewdettaglizzizzo" use case).
     * 
     * @return Panel for address selection
     */
    private JPanel createAddressSelectionPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Address Details View", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        // Center panel for address information
        JPanel centerPanel = new JPanel(new GridBagLayout());
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(15, 15, 15, 15);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Address selection label
        gbc.gridx = 0;
        gbc.gridy = 0;
        JLabel selectLabel = new JLabel("Select Address:");
        selectLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        centerPanel.add(selectLabel, gbc);
        // Address combo box
        gbc.gridx = 1;
        gbc.gridy = 0;
        addressComboBox = new JComboBox<>();
        addressComboBox.setFont(new Font("Arial", Font.PLAIN, 14));
        addressComboBox.setPreferredSize(new Dimension(350, 30));
        centerPanel.add(addressComboBox, gbc);
        // Address details label
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.gridwidth = 2;
        addressDetailsLabel = new JLabel("", JLabel.CENTER);
        addressDetailsLabel.setFont(new Font("Arial", Font.PLAIN, 14));
        addressDetailsLabel.setBorder(BorderFactory.createCompoundBorder(
                BorderFactory.createLineBorder(Color.GRAY, 2),
                BorderFactory.createEmptyBorder(20, 20, 20, 20)));
        addressDetailsLabel.setPreferredSize(new Dimension(500, 250));
        centerPanel.add(addressDetailsLabel, gbc);
        // Button to navigate to teaching management (simulates "Teachings Address" button)
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.gridwidth = 2;
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 0));
        JButton teachingsButton = new JButton("Manage Teachings for this Address");
        teachingsButton.setFont(new Font("Arial", Font.BOLD, 16));
        teachingsButton.setPreferredSize(new Dimension(350, 45));
        teachingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Show teaching management panel
                showTeachingManagementPanel();
            }
        });
        buttonPanel.add(teachingsButton);
        centerPanel.add(buttonPanel, gbc);
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }
    /**
     * Create the teaching management panel.
     * 
     * @return Panel for teaching assignment/removal
     */
    private JPanel createTeachingManagementPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        panel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Title
        JLabel titleLabel = new JLabel("Manage Address Teachings", JLabel.CENTER);
        titleLabel.setFont(new Font("Arial", Font.BOLD, 20));
        titleLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 20, 0));
        panel.add(titleLabel, BorderLayout.NORTH);
        // Create center panel with two lists and buttons
        JPanel centerPanel = new JPanel(new GridLayout(1, 3, 30, 0));
        centerPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Current teachings panel
        JPanel currentPanel = new JPanel(new BorderLayout(10, 10));
        currentPanel.setBorder(new TitledBorder(
                BorderFactory.createLineBorder(Color.BLUE, 2),
                "Current Teachings (To Remove)",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.BLUE));
        currentTeachingsModel = new DefaultListModel<>();
        currentTeachingsList = new JList<>(currentTeachingsModel);
        currentTeachingsList.setFont(new Font("Arial", Font.PLAIN, 12));
        currentTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        currentTeachingsList.setCellRenderer(new TeachingListRenderer());
        JScrollPane currentScrollPane = new JScrollPane(currentTeachingsList);
        currentScrollPane.setPreferredSize(new Dimension(300, 400));
        currentPanel.add(currentScrollPane, BorderLayout.CENTER);
        // Remove button
        removeButton = new JButton("<< Remove Selected Teachings");
        removeButton.setFont(new Font("Arial", Font.BOLD, 14));
        removeButton.setPreferredSize(new Dimension(280, 35));
        removeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                removeSelectedTeachings();
            }
        });
        JPanel removeButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        removeButtonPanel.add(removeButton);
        currentPanel.add(removeButtonPanel, BorderLayout.SOUTH);
        // Button panel in the middle
        JPanel buttonPanel = new JPanel(new GridLayout(3, 1, 0, 20));
        buttonPanel.setBorder(BorderFactory.createEmptyBorder(100, 10, 100, 10));
        // Back button
        backButton = new JButton("Back to Address Details");
        backButton.setFont(new Font("Arial", Font.PLAIN, 14));
        backButton.setPreferredSize(new Dimension(250, 35));
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                cardLayout.show(mainPanel, "addressSelection");
                updateAddressDetails();
            }
        });
        buttonPanel.add(backButton);
        // Send button
        sendButton = new JButton("SEND CHANGES");
        sendButton.setFont(new Font("Arial", Font.BOLD, 16));
        sendButton.setBackground(new Color(0, 150, 0));
        sendButton.setForeground(Color.WHITE);
        sendButton.setPreferredSize(new Dimension(250, 45));
        sendButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                processTeachings();
            }
        });
        buttonPanel.add(sendButton);
        // Available teachings panel
        JPanel availablePanel = new JPanel(new BorderLayout(10, 10));
        availablePanel.setBorder(new TitledBorder(
                BorderFactory.createLineBorder(Color.GREEN.darker(), 2),
                "Available Teachings (To Assign)",
                TitledBorder.CENTER,
                TitledBorder.TOP,
                new Font("Arial", Font.BOLD, 14),
                Color.GREEN.darker()));
        availableTeachingsModel = new DefaultListModel<>();
        availableTeachingsList = new JList<>(availableTeachingsModel);
        availableTeachingsList.setFont(new Font("Arial", Font.PLAIN, 12));
        availableTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        availableTeachingsList.setCellRenderer(new TeachingListRenderer());
        JScrollPane availableScrollPane = new JScrollPane(availableTeachingsList);
        availableScrollPane.setPreferredSize(new Dimension(300, 400));
        availablePanel.add(availableScrollPane, BorderLayout.CENTER);
        // Assign button
        assignButton = new JButton("Assign Selected Teachings >>");
        assignButton.setFont(new Font("Arial", Font.BOLD, 14));
        assignButton.setPreferredSize(new Dimension(280, 35));
        assignButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                assignSelectedTeachings();
            }
        });
        JPanel assignButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER));
        assignButtonPanel.add(assignButton);
        availablePanel.add(assignButtonPanel, BorderLayout.SOUTH);
        // Add panels to center panel
        centerPanel.add(currentPanel);
        centerPanel.add(buttonPanel);
        centerPanel.add(availablePanel);
        panel.add(centerPanel, BorderLayout.CENTER);
        return panel;
    }
    /**
     * Custom renderer for teaching list to display teaching information nicely.
     */
    private class TeachingListRenderer extends DefaultListCellRenderer {
        @Override
        public Component getListCellRendererComponent(JList<?> list, Object value,
                int index, boolean isSelected, boolean cellHasFocus) {
            super.getListCellRendererComponent(list, value, index, isSelected, cellHasFocus);
            if (value instanceof Teaching) {
                Teaching teaching = (Teaching) value;
                setText("<html><b>" + teaching.getName() + "</b><br>" +
                        "ID: " + teaching.getId() + "<br>" +
                        "Instructor: " + teaching.getInstructor() + "<br>" +
                        "Duration: " + teaching.getDurationHours() + " hours</html>");
                setToolTipText(teaching.getDescription());
            }
            return this;
        }
    }
    /**
     * Load addresses into the combo box.
     */
    private void loadAddresses() {
        List<Address> addresses = manager.getAllAddresses();
        addressComboBox.removeAllItems();
        for (Address address : addresses) {
            addressComboBox.addItem(address.getId() + " - " + address.toString());
        }
        // Add listener to update address details when selection changes
        addressComboBox.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                updateAddressDetails();
            }
        });
        // Load initial address details if available
        if (addressComboBox.getItemCount() > 0) {
            addressComboBox.setSelectedIndex(0);
            updateAddressDetails();
        }
    }
    /**
     * Update the address details display.
     */
    private void updateAddressDetails() {
        if (addressComboBox.getSelectedIndex() >= 0) {
            String selected = (String) addressComboBox.getSelectedItem();
            String[] parts = selected.split(" - ", 2);
            if (parts.length >= 1) {
                String addressId = parts[0];
                Address address = manager.getAddressById(addressId);
                if (address != null) {
                    StringBuilder details = new StringBuilder();
                    details.append("<html><div style='text-align:center; padding:10px;'>");
                    details.append("<h2>Address Details</h2>");
                    details.append("<hr style='width:80%;'>");
                    details.append("<p><b>ID:</b> ").append(address.getId()).append("</p>");
                    details.append("<p><b>Street:</b> ").append(address.getStreet()).append("</p>");
                    details.append("<p><b>City:</b> ").append(address.getCity()).append("</p>");
                    details.append("<p><b>Zip Code:</b> ").append(address.getZipCode()).append("</p>");
                    details.append("<br>");
                    List<Teaching> teachings = address.getTeachings();
                    details.append("<h3>Current Teachings (").append(teachings.size()).append(")</h3>");
                    if (teachings.isEmpty()) {
                        details.append("<p><i>No teachings assigned to this address</i></p>");
                    } else {
                        details.append("<ul style='text-align:left;'>");
                        for (Teaching t : teachings) {
                            details.append("<li><b>").append(t.getName()).append("</b> - ")
                                    .append(t.getInstructor()).append("</li>");
                        }
                        details.append("</ul>");
                    }
                    details.append("</div></html>");
                    addressDetailsLabel.setText(details.toString());
                }
            }
        }
    }
    /**
     * Show the teaching management panel and load data.
     */
    private void showTeachingManagementPanel() {
        if (addressComboBox.getSelectedIndex() >= 0) {
            String selected = (String) addressComboBox.getSelectedItem();
            String[] parts = selected.split(" - ", 2);
            if (parts.length >= 1) {
                currentAddressId = parts[0];
                // Load current and available teachings for the selected address
                loadTeachingsForAddress(currentAddressId);
                // Switch to teaching management panel
                cardLayout.show(mainPanel, "teachingManagement");
            }
        } else {
            JOptionPane.showMessageDialog(this,
                    "Please select an address first.",
                    "No Address Selected",
                    JOptionPane.WARNING_MESSAGE);
        }
    }
    /**
     * Load teachings for the selected address.
     * 
     * @param addressId The address ID
     */
    private void loadTeachingsForAddress(String addressId) {
        // Clear existing models
        currentTeachingsModel.clear();
        availableTeachingsModel.clear();
        // Load current teachings
        List<Teaching> currentTeachings = manager.getTeachingsForAddress(addressId);
        for (Teaching t : currentTeachings) {
            currentTeachingsModel.addElement(t);
        }
        // Load available teachings
        List<Teaching> availableTeachings = manager.getAvailableTeachings(addressId);
        for (Teaching t : availableTeachings) {
            availableTeachingsModel.addElement(t);
        }
        // Update button states based on selection
        updateButtonStates();
    }
    /**
     * Update button states based on list selections.
     */
    private void updateButtonStates() {
        assignButton.setEnabled(!availableTeachingsList.isSelectionEmpty());
        removeButton.setEnabled(!currentTeachingsList.isSelectionEmpty());
        sendButton.setEnabled(currentTeachingsModel.size() > 0 || availableTeachingsModel.size() > 0);
    }
    /**
     * Assign selected teachings from available list to current list.
     */
    private void assignSelectedTeachings() {
        List<Teaching> selected = availableTeachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            return;
        }
        for (Teaching t : selected) {
            currentTeachingsModel.addElement(t);
            availableTeachingsModel.removeElement(t);
        }
        // Clear selection after operation
        availableTeachingsList.clearSelection();
        updateButtonStates();
    }
    /**
     * Remove selected teachings from current list to available list.
     */
    private void removeSelectedTeachings() {
        List<Teaching> selected = currentTeachingsList.getSelectedValuesList();
        if (selected.isEmpty()) {
            return;
        }
        for (Teaching t : selected) {
            availableTeachingsModel.addElement(t);
            currentTeachingsModel.removeElement(t);
        }
        // Clear selection after operation
        currentTeachingsList.clearSelection();
        updateButtonStates();
    }
    /**
     * Process the teachings (simulates clicking "Send" button).
     * This associates or removes selected teachings at the address.
     */
    private void processTeachings() {
        if (currentAddressId == null || currentAddressId.isEmpty()) {
            JOptionPane.showMessageDialog(this,
                    "No address selected. Please go back and select an address.",
                    "Error",
                    JOptionPane.ERROR_MESSAGE);
            return;
        }
        try {
            // Get current teaching IDs from model
            List<String> currentTeachingIds = new ArrayList<>();
            for (int i = 0; i < currentTeachingsModel.getSize(); i++) {
                currentTeachingIds.add(currentTeachingsModel.getElementAt(i).getId());
            }
            // Get original teaching IDs for this address
            Address address = manager.getAddressById(currentAddressId);
            if (address == null) {
                throw new RuntimeException("Address not found");
            }
            List<String> originalTeachingIds = address.getTeachingIds();
            // Determine which teachings to remove (in original but not in current)
            List<String> toRemove = new ArrayList<>();
            for (String originalId : originalTeachingIds) {
                if (!currentTeachingIds.contains(originalId)) {
                    toRemove.add(originalId);
                }
            }
            // Determine which teachings to assign (in current but not in original)
            List<String> toAssign = new ArrayList<>();
            for (String currentId : currentTeachingIds) {
                if (!originalTeachingIds.contains(currentId)) {
                    toAssign.add(currentId);
                }
            }
            // Perform operations
            boolean operationsSuccessful = true;
            String errorMessage = "";
            if (!toRemove.isEmpty()) {
                try {
                    operationsSuccessful = manager.removeTeachings(currentAddressId, toRemove);
                    if (!operationsSuccessful) {
                        errorMessage = "Failed to remove teachings.";
                    }
                } catch (Exception e) {
                    operationsSuccessful = false;
                    errorMessage = "Error removing teachings: " + e.getMessage();
                }
            }
            if (!toAssign.isEmpty() && operationsSuccessful) {
                try {
                    operationsSuccessful = manager.assignTeachings(currentAddressId, toAssign);
                    if (!operationsSuccessful) {
                        errorMessage += " Failed to assign teachings.";
                    }
                } catch (Exception e) {
                    operationsSuccessful = false;
                    errorMessage = "Error assigning teachings: " + e.getMessage();
                }
            }
            if (operationsSuccessful) {
                JOptionPane.showMessageDialog(this,
                        "Teachings successfully updated for the address!\n\n" +
                                "• Assigned: " + toAssign.size() + " teaching(s)\n" +
                                "• Removed: " + toRemove.size() + " teaching(s)\n\n" +
                                "Returning to address details...",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                // Return to address details view (simulates postcondition)
                cardLayout.show(mainPanel, "addressSelection");
                updateAddressDetails();
            } else {
                JOptionPane.showMessageDialog(this,
                        "Some operations failed: " + errorMessage + "\nPlease try again.",
                        "Operation Error",
                        JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException e) {
            // Handle SMOS server interruption or other exceptions
            JOptionPane.showMessageDialog(this,
                    "SMOS Server Error: " + e.getMessage() +
                            "\n\nPlease check your connection and try again.",
                    "Server Communication Error",
                    JOptionPane.ERROR_MESSAGE);
            // Reset server connection for retry
            manager.getAddressById(currentAddressId).getTeachings(); // Force reset if needed
        } catch (Exception e) {
            JOptionPane.showMessageDialog(this,
                    "Unexpected error: " + e.getMessage(),
                    "System Error",
                    JOptionPane.ERROR_MESSAGE);
        }
    }
}