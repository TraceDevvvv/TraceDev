/**
 * A JPanel dedicated to displaying the detailed information of a selected tourist.
 * It represents the "card" of the selected tourist, as specified in the use case.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TouristDetailPanel extends JPanel {
    private JLabel idLabel, firstNameLabel, lastNameLabel, emailLabel, phoneNumberLabel, addressLabel;
    private JButton backButton;
    private TouristApp parentApp; // Reference to the main application frame
    /**
     * Constructs a new TouristDetailPanel.
     * @param parentApp The main application frame to communicate back with (e.g., for 'back' action).
     */
    public TouristDetailPanel(TouristApp parentApp) {
        this.parentApp = parentApp;
        setLayout(new BorderLayout(10, 10)); // Add some spacing
        initComponents();
        clearDetails(); // Initialize with empty details or loading state
    }
    /**
     * Initializes and lays out the GUI components for displaying tourist details.
     */
    private void initComponents() {
        // Title
        JLabel titleLabel = new JLabel("Tourist Details (Card)", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        add(titleLabel, BorderLayout.NORTH);
        // Details Panel using GridBagLayout for alignment
        JPanel detailsPanel = new JPanel(new GridBagLayout());
        detailsPanel.setBorder(BorderFactory.createEmptyBorder(10, 20, 10, 20)); // Padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 8, 8, 8); // Padding between components
        gbc.anchor = GridBagConstraints.WEST; // Align labels to the left
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.weightx = 1.0; // Allow value labels to expand
        // Helper method to add a label-value pair
        int row = 0;
        row = addDetailRow(detailsPanel, gbc, row, "ID:", idLabel = new JLabel());
        row = addDetailRow(detailsPanel, gbc, row, "First Name:", firstNameLabel = new JLabel());
        row = addDetailRow(detailsPanel, gbc, row, "Last Name:", lastNameLabel = new JLabel());
        row = addDetailRow(detailsPanel, gbc, row, "Email:", emailLabel = new JLabel());
        row = addDetailRow(detailsPanel, gbc, row, "Phone:", phoneNumberLabel = new JLabel());
        row = addDetailRow(detailsPanel, gbc, row, "Address:", addressLabel = new JLabel());
        add(detailsPanel, BorderLayout.CENTER);
        // Button Panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        backButton = new JButton("Back to List");
        backButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                parentApp.showTouristList(); // Navigate back to the list panel
            }
        });
        buttonPanel.add(backButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    /**
     * Helper method to add a label and a value label to the details panel.
     * @param panel The JPanel to add components to.
     * @param gbc The GridBagConstraints for layout.
     * @param row The current row index.
     * @param labelText The text for the static label.
     * @param valueLabel The JLabel to display the dynamic value.
     * @return The next row index.
     */
    private int addDetailRow(JPanel panel, GridBagConstraints gbc, int row, String labelText, JLabel valueLabel) {
        // Static label
        gbc.gridx = 0;
        gbc.gridy = row;
        gbc.weightx = 0; // Don't expand
        JLabel staticLabel = new JLabel("<html><b>" + labelText + "</b></html>");
        staticLabel.setFont(staticLabel.getFont().deriveFont(Font.BOLD, 14f));
        panel.add(staticLabel, gbc);
        // Value label
        gbc.gridx = 1;
        gbc.gridy = row;
        gbc.weightx = 1.0; // Allow to expand
        valueLabel.setFont(valueLabel.getFont().deriveFont(Font.PLAIN, 14f));
        panel.add(valueLabel, gbc);
        return row + 1;
    }
    /**
     * Displays the details of the given Tourist object on the panel.
     * @param tourist The Tourist object whose details are to be displayed.
     */
    public void displayTouristDetails(Tourist tourist) {
        if (tourist != null) {
            idLabel.setText(tourist.getId() != null && !tourist.getId().isEmpty() ? tourist.getId() : "N/A");
            firstNameLabel.setText(tourist.getFirstName() != null && !tourist.getFirstName().isEmpty() ? tourist.getFirstName() : "N/A");
            lastNameLabel.setText(tourist.getLastName() != null && !tourist.getLastName().isEmpty() ? tourist.getLastName() : ""); // Last name can be empty
            emailLabel.setText(tourist.getEmail() != null && !tourist.getEmail().isEmpty() ? tourist.getEmail() : "N/A");
            phoneNumberLabel.setText(tourist.getPhoneNumber() != null && !tourist.getPhoneNumber().isEmpty() ? tourist.getPhoneNumber() : "N/A");
            addressLabel.setText(tourist.getAddress() != null && !tourist.getAddress().isEmpty() ? tourist.getAddress() : "N/A");
        } else {
            clearDetails(); // If tourist is null, clear all fields
        }
    }
    /**
     * Clears all displayed tourist details, setting them to a default message.
     */
    public void clearDetails() {
        idLabel.setText("..."); // Indicate loading or no data
        firstNameLabel.setText("No tourist selected or loading data.");
        lastNameLabel.setText("");
        emailLabel.setText("");
        phoneNumberLabel.setText("");
        addressLabel.setText("");
    }
}