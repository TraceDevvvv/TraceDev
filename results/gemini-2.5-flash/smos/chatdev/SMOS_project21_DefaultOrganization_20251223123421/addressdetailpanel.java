'''
/**
 * A Swing JPanel component responsible for displaying the detailed information of a
 * selected address. It shows the address ID, description, and a list of currently
 * assigned teachings.
 * This panel also includes a "Teachings Address" button which, when clicked,
 * opens the {@code TeachingAssignmentDialog} to manage the address's teachings.
 * It simulates the "viewdettaglizzizzo" and "click on the 'Teachings Address' button"
 * steps of the use case.
 */
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.stream.Collectors;
public class AddressDetailPanel extends JPanel {
    private Address currentDisplayedAddress;
    private TeachingRepository repository;
    private JLabel addressIdLabel;
    private JLabel addressDescriptionLabel;
    private JTextArea assignedTeachingsArea;
    private JButton assignTeachingsButton;
    /**
     * Constructs an AddressDetailPanel.
     * @param repository The TeachingRepository instance to use for data operations.
     */
    public AddressDetailPanel(TeachingRepository repository) {
        this.repository = repository;
        setLayout(new BorderLayout(10, 10)); // Padding between components
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Overall panel padding
        initComponents();
        setupLayout();
        // Initially, no address is loaded, so fields might be empty or show default.
        // An address will be loaded via setAddress.
    }
    /**
     * Initializes the GUI components for displaying address details.
     */
    private void initComponents() {
        addressIdLabel = new JLabel("ID: ");
        addressDescriptionLabel = new JLabel("Description: ");
        assignedTeachingsArea = new JTextArea(5, 30); // 5 rows, 30 columns for teachings list
        assignedTeachingsArea.setEditable(false);
        assignedTeachingsArea.setLineWrap(true);
        assignedTeachingsArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(assignedTeachingsArea);
        assignTeachingsButton = new JButton("Teachings Address"); // As per use case name: "Teachings Address" button
        assignTeachingsButton.setEnabled(false); // Disable until an address is loaded
    }
    /**
     * Sets up the layout of the panel.
     */
    private void setupLayout() {
        // Panel for address basic info
        JPanel infoPanel = new JPanel(new GridLayout(0, 1, 5, 5)); // One column, vertical gap
        infoPanel.add(addressIdLabel);
        infoPanel.add(addressDescriptionLabel);
        // Panel for assigned teachings with a title
        JPanel teachingsPanel = new JPanel(new BorderLayout());
        teachingsPanel.setBorder(BorderFactory.createTitledBorder("Assigned Teachings"));
        teachingsPanel.add(new JScrollPane(assignedTeachingsArea), BorderLayout.CENTER);
        // Panel for the button
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER)); // Center the button
        buttonPanel.add(assignTeachingsButton);
        // Add components to the main panel
        add(infoPanel, BorderLayout.NORTH);
        add(teachingsPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
        // Add action listener to the button
        assignTeachingsButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                if (currentDisplayedAddress != null) {
                    // Precondition: user has clicked on "Teachings Address" button
                    // Open the TeachingAssignmentDialog
                    // 'SwingUtilities.getWindowAncestor(AddressDetailPanel.this)' gets the parent JFrame
                    TeachingAssignmentDialog dialog = new TeachingAssignmentDialog((Frame) SwingUtilities.getWindowAncestor(AddressDetailPanel.this), currentDisplayedAddress, repository);
                    dialog.setVisible(true); // This call blocks until dialog is closed if modal
                    // Post-condition: back to display details of the address.
                    // Refresh the display after the dialog closes, in case changes were made.
                    refreshAddressDetails();
                }
            }
        });
    }
    /**
     * Loads and displays the details of a given Address object.
     * @param address The Address object to display.
     */
    public void setAddress(Address address) {
        this.currentDisplayedAddress = address;
        if (address != null) {
            addressIdLabel.setText("ID: " + address.getId());
            addressDescriptionLabel.setText("Description: " + address.getDescription());
            refreshAssignedTeachingsDisplay();
            assignTeachingsButton.setEnabled(true);
        } else {
            // Clear display if address is null
            addressIdLabel.setText("ID: N/A");
            addressDescriptionLabel.setText("Description: No Address Selected");
            assignedTeachingsArea.setText("");
            assignTeachingsButton.setEnabled(false);
        }
    }
    /**
     * Refreshes only the assigned teachings display for the current address.
     * Useful after the TeachingAssignmentDialog closes and changes might have occurred.
     */
    private void refreshAssignedTeachingsDisplay() {
        if (currentDisplayedAddress != null) {
            String teachingsList = currentDisplayedAddress.getAssignedTeachings().stream()
                    .map(Teaching::getName)
                    .sorted() // Sort for consistent display
                    .collect(Collectors.joining("\n"));
            assignedTeachingsArea.setText(teachingsList.isEmpty() ? "No teachings assigned." : teachingsList);
        } else {
            assignedTeachingsArea.setText("");
        }
    }
    /**
     * Refreshes all address details, usually called after modifications.
     */
    public void refreshAddressDetails() {
        // Re-fetch the address from the repository to ensure we have the latest state
        // This simulates 'viewdettaglizzizzo' for the updated state.
        if (currentDisplayedAddress != null) {
            try {
                Address updatedAddress = repository.getAddressById(currentDisplayedAddress.getId());
                if (updatedAddress != null) {
                    this.currentDisplayedAddress = updatedAddress; // Update our internal reference
                    addressIdLabel.setText("ID: " + updatedAddress.getId());
                    addressDescriptionLabel.setText("Description: " + updatedAddress.getDescription());
                    refreshAssignedTeachingsDisplay();
                } else {
                    // Handle case where address might have been deleted (unlikely in this example)
                    setAddress(null);
                    JOptionPane.showMessageDialog(this, "The displayed address could not be found after update.", "Error", JOptionPane.WARNING_MESSAGE);
                }
            } catch (RuntimeException ex) {
                // Handle simulated connection interruption
                JOptionPane.showMessageDialog(this, ex.getMessage() + "\nCould not refresh address details.", "Connection Error", JOptionPane.ERROR_MESSAGE);
                // Keep displaying the old data if refresh failed
            }
        }
    }
}