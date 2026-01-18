'''
/**
 * A Swing dialog window that allows an administrator to assign or remove one or more teachings
 * from a specific address. It presents two lists: one for available teachings and one for
 * teachings currently assigned to the address, with buttons to move teachings between them.
 * Changes made in this dialog are saved back to the repository upon confirmation ("Send").
 */
'''
import javax.swing.*;
import javax.swing.event.ListSelectionEvent;
import javax.swing.event.ListSelectionListener;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.List;
import java.util.stream.Collectors;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
public class TeachingAssignmentDialog extends JDialog {
    private final Address currentAddress;
    private final TeachingRepository repository;
    private DefaultListModel<Teaching> availableTeachingsModel;
    private DefaultListModel<Teaching> assignedTeachingsModel;
    private JList<Teaching> availableTeachingsList;
    private JList<Teaching> assignedTeachingsList;
    private JButton assignButton;
    private JButton removeButton;
    private JButton sendButton;
    private JButton cancelButton;
    /**
     * Constructs a new TeachingAssignmentDialog.
     * @param owner The parent frame of this dialog.
     * @param address The Address object for which teachings are being managed.
     * @param repository The TeachingRepository to fetch and update data.
     */
    public TeachingAssignmentDialog(Frame owner, Address address, TeachingRepository repository) {
        super(owner, "Assign/Remove Teachings for: " + address.getDescription(), true); // Modal dialog
        this.currentAddress = address;
        this.repository = repository;
        setDefaultCloseOperation(DISPOSE_ON_CLOSE);
        setSize(700, 500);
        setLocationRelativeTo(owner); // Center the dialog relative to its parent
        initComponents();
        setupLayout();
        populateLists();
        addListeners();
    }
    /**
     * Initializes the GUI components of the dialog.
     */
    private void initComponents() {
        // Models for the JLists
        availableTeachingsModel = new DefaultListModel<>();
        assignedTeachingsModel = new DefaultListModel<>();
        // JLists for displaying teachings
        availableTeachingsList = new JList<>(availableTeachingsModel);
        assignedTeachingsList = new JList<>(assignedTeachingsModel);
        // Allow multiple selections
        availableTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        assignedTeachingsList.setSelectionMode(ListSelectionModel.MULTIPLE_INTERVAL_SELECTION);
        // Buttons for actions
        assignButton = new JButton("Add Selected ->");
        removeButton = new JButton("<- Remove Selected");
        sendButton = new JButton("Send");
        cancelButton = new JButton("Cancel");
        // Initially disable buttons to avoid actions on empty selections
        assignButton.setEnabled(false);
        removeButton.setEnabled(false);
    }
    /**
     * Sets up the layout of the dialog.
     * Uses BorderLayout for overall structure and separate panels for lists and buttons.
     */
    private void setupLayout() {
        setLayout(new BorderLayout(10, 10)); // Outer layout with some padding
        // Overall panel padding for the dialog itself
        ((JPanel)getContentPane()).setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Main content panel to hold lists and central buttons using GridBagLayout for flexible positioning
        JPanel mainContentPanel = new JPanel(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        // --- Left List Panel (Available Teachings) ---
        JPanel leftListPanelWithScroll = new JPanel(new BorderLayout());
        leftListPanelWithScroll.setBorder(BorderFactory.createTitledBorder("Available Teachings"));
        leftListPanelWithScroll.add(new JScrollPane(availableTeachingsList), BorderLayout.CENTER);
        gbc.gridx = 0; // First column
        gbc.gridy = 0; // First row
        gbc.weightx = 0.45; // Give this column 45% of horizontal space
        gbc.weighty = 1.0;  // Occupy all vertical space
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        mainContentPanel.add(leftListPanelWithScroll, gbc);
        // --- Central Button Panel (assign/remove buttons) ---
        JPanel centralButtonPanel = new JPanel();
        centralButtonPanel.setLayout(new BoxLayout(centralButtonPanel, BoxLayout.Y_AXIS)); // Stack buttons vertically
        centralButtonPanel.add(Box.createVerticalGlue()); // Push buttons to center vertically
        Dimension buttonSize = new Dimension(150, 30); // Fixed size for buttons for consistency
        assignButton.setPreferredSize(buttonSize);
        assignButton.setMinimumSize(buttonSize);
        assignButton.setMaximumSize(buttonSize);
        assignButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        centralButtonPanel.add(assignButton);
        centralButtonPanel.add(Box.createRigidArea(new Dimension(0, 10))); // Space between buttons
        removeButton.setPreferredSize(buttonSize);
        removeButton.setMinimumSize(buttonSize);
        removeButton.setMaximumSize(buttonSize);
        removeButton.setAlignmentX(Component.CENTER_ALIGNMENT); // Center button horizontally
        centralButtonPanel.add(removeButton);
        centralButtonPanel.add(Box.createVerticalGlue()); // Push buttons to center vertically
        gbc.gridx = 1; // Second column (middle)
        gbc.weightx = 0.1; // Give this column 10% of horizontal space
        gbc.fill = GridBagConstraints.VERTICAL; // Fill vertically, but not horizontally (buttons have fixed width)
        mainContentPanel.add(centralButtonPanel, gbc);
        // --- Right List Panel (Assigned Teachings) ---
        JPanel rightListPanelWithScroll = new JPanel(new BorderLayout());
        rightListPanelWithScroll.setBorder(BorderFactory.createTitledBorder("Assigned Teachings for " + currentAddress.getDescription()));
        rightListPanelWithScroll.add(new JScrollPane(assignedTeachingsList), BorderLayout.CENTER);
        gbc.gridx = 2; // Third column
        gbc.weightx = 0.45; // Give this column 45% of horizontal space
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        mainContentPanel.add(rightListPanelWithScroll, gbc);
        // Add the main content panel to the center of the dialog
        add(mainContentPanel, BorderLayout.CENTER);
        // Panel for bottom action buttons (Send/Cancel)
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10)); // Align right with padding
        actionButtonPanel.add(sendButton);
        actionButtonPanel.add(cancelButton);
        add(actionButtonPanel, BorderLayout.SOUTH);
    }
    /**
     * Populates the available and assigned teachings lists.
     * It fetches all teachings and identifies which ones are already assigned to the current address.
     */
    private void populateLists() {
        // Clear models before populating to avoid duplicates on re-opening
        availableTeachingsModel.clear();
        assignedTeachingsModel.clear();
        // Get all available teachings from the repository
        Collection<Teaching> allAvailable = repository.getAllTeachings();
        // Get teachings currently assigned to the address
        Set<Teaching> currentAssigned = currentAddress.getAssignedTeachings();
        // Populate assignedTeachingsModel with current assigned teachings
        for (Teaching t : currentAssigned) {
            assignedTeachingsModel.addElement(t);
        }
        // Populate availableTeachingsModel with teachings not yet assigned
        for (Teaching t : allAvailable) {
            if (!currentAssigned.contains(t)) {
                availableTeachingsModel.addElement(t);
            }
        }
    }
    /**
     * Adds action listeners to buttons and list selection listeners to JLists.
     */
    private void addListeners() {
        // Listener for the Assign (Add) button
        assignButton.addActionListener(e -> assignTeachings());
        // Listener for the Remove button
        removeButton.addActionListener(e -> removeTeachings());
        // Listener for the Send button (Save changes)
        sendButton.addActionListener(e -> saveChanges());
        // Listener for the Cancel button
        cancelButton.addActionListener(e -> {
            setVisible(false); // Hide the dialog
            dispose();         // Release system resources
        });
        // List Selection Listener for availableTeachingsList
        // Enables/disables assignButton based on selection
        availableTeachingsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure event is not fired multiple times
                    assignButton.setEnabled(!availableTeachingsList.isSelectionEmpty());
                }
            }
        });
        // List Selection Listener for assignedTeachingsList
        // Enables/disables removeButton based on selection
        assignedTeachingsList.addListSelectionListener(new ListSelectionListener() {
            @Override
            public void valueChanged(ListSelectionEvent e) {
                if (!e.getValueIsAdjusting()) { // Ensure event is not fired multiple times
                    removeButton.setEnabled(!assignedTeachingsList.isSelectionEmpty());
                }
            }
        });
    }
    /**
     * Logic to move selected teachings from the 'Available' list to the 'Assigned' list.
     */
    private void assignTeachings() {
        // Get all selected teachings from the available list
        List<Teaching> selected = availableTeachingsList.getSelectedValuesList();
        for (Teaching teaching : selected) {
            // Add to assigned list model
            assignedTeachingsModel.addElement(teaching);
            // Remove from available list model
            availableTeachingsModel.removeElement(teaching);
        }
        // Clear selection after moving
        availableTeachingsList.clearSelection();
        assignedTeachingsList.clearSelection();
    }
    /**
     * Logic to move selected teachings from the 'Assigned' list to the 'Available' list.
     */
    private void removeTeachings() {
        // Get all selected teachings from the assigned list
        List<Teaching> selected = assignedTeachingsList.getSelectedValuesList();
        for (Teaching teaching : selected) {
            // Add to available list model
            availableTeachingsModel.addElement(teaching);
            // Remove from assigned list model
            availableTeachingsModel.removeElement(teaching);
        }
        // Clear selection after moving
        availableTeachingsList.clearSelection();
        assignedTeachingsList.clearSelection();
    }
    /**
     * Handles saving the changes when the "Send" button is clicked.
     * Collects all teachings from the assigned list and updates the repository.
     * Handles potential connection errors as per postconditions.
     */
    private void saveChanges() {
        // Convert the assignedTeachingsModel elements into a Set<Teaching>
        Set<Teaching> finalAssignedTeachings = new HashSet<>();
        for (int i = 0; i < assignedTeachingsModel.size(); i++) {
            finalAssignedTeachings.add(assignedTeachingsModel.getElementAt(i));
        }
        try {
            // Attempt to update the address's teachings in the repository
            boolean success = repository.updateAddressTeachings(currentAddress.getId(), finalAssignedTeachings);
            if (success) {
                JOptionPane.showMessageDialog(this, "Teachings updated successfully!", "Success", JOptionPane.INFORMATION_MESSAGE);
                // The currentAddress object itself in the repository is updated.
                // We could also update our local currentAddress object for consistency,
                // but since we are closing the dialog, it's not strictly necessary here.
                setVisible(false);
                dispose();
            } else {
                JOptionPane.showMessageDialog(this, "Failed to find address " + currentAddress.getId() + " for update.", "Error", JOptionPane.ERROR_MESSAGE);
            }
        } catch (RuntimeException ex) {
            // Handle simulated connection interruption (Postcondition: connection to the SMOS server interrupted)
            JOptionPane.showMessageDialog(this, ex.getMessage() + "\nTeachings changes could not be saved.", "Connection Error", JOptionPane.ERROR_MESSAGE);
        }
    }
}