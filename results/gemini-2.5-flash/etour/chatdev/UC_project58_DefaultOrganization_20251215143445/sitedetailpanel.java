/**
 * A Swing JPanel component responsible for displaying the details of a single tourist site.
 * It provides methods to update the displayed information or show error messages.
 */
import javax.swing.*;
import java.awt.*;
public class SiteDetailPanel extends JPanel {
    private JLabel nameLabel;
    private JLabel idLabel;
    private JTextArea descriptionArea;
    private JLabel locationLabel;
    private JLabel ratingLabel;
    private JLabel feedbackLabel; // For displaying success/error messages
    /**
     * Constructs a new SiteDetailPanel, setting up its layout and components.
     */
    public SiteDetailPanel() {
        // Use GridBagLayout for flexible component placement
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        // Initialize labels and text area
        idLabel = new JLabel("ID: ");
        nameLabel = new JLabel("Name: ");
        locationLabel = new JLabel("Location: ");
        ratingLabel = new JLabel("Rating: ");
        descriptionArea = new JTextArea(5, 30); // 5 rows, 30 columns
        descriptionArea.setWrapStyleWord(true); // Wrap words
        descriptionArea.setLineWrap(true);       // Wrap lines
        descriptionArea.setEditable(false);      // Not editable by user
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea); // Add scroll for description
        feedbackLabel = new JLabel("Please select a site to view its details.");
        feedbackLabel.setForeground(Color.BLUE); // Default color for initial message
        // Add components to the panel using GridBagConstraints
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.WEST; // Align to the left
        add(new JLabel("Site Details:"), gbc);
        gbc.gridy++;
        add(idLabel, gbc);
        gbc.gridy++;
        add(nameLabel, gbc);
        gbc.gridy++;
        add(locationLabel, gbc);
        gbc.gridy++;
        add(ratingLabel, gbc);
        gbc.gridy++;
        add(new JLabel("Description:"), gbc);
        gbc.gridy++;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.BOTH; // Fill available space
        gbc.weightx = 1.0; // Allow horizontal resizing
        gbc.weighty = 1.0; // Allow vertical resizing
        add(descriptionScrollPane, gbc);
        gbc.gridy++;
        gbc.gridwidth = 2;
        gbc.weightx = 0.0;
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(feedbackLabel, gbc);
        // Set an initial border for visual separation
        setBorder(BorderFactory.createTitledBorder("Selected Site Information"));
        setPreferredSize(new Dimension(400, 300)); // Set a preferred size
    }
    /**
     * Clears all displayed site details from the panel.
     * Resets the text of all labels and the description area.
     */
    public void clearDetails() {
        idLabel.setText("ID: ");
        nameLabel.setText("Name: ");
        descriptionArea.setText("");
        locationLabel.setText("Location: ");
        ratingLabel.setText("Rating: ");
        feedbackLabel.setText(""); // Clear feedback message
        feedbackLabel.setForeground(Color.BLACK);
    }
    /**
     * Updates the panel to display the details of the given Site object.
     * If the site is null, it clears the details.
     *
     * @param site The Site object whose details are to be displayed.
     */
    public void updateSiteDetails(Site site) {
        if (site != null) {
            idLabel.setText("ID: " + site.getId());
            nameLabel.setText("Name: " + site.getName());
            descriptionArea.setText(site.getDescription());
            locationLabel.setText("Location: " + site.getLocation());
            ratingLabel.setText(String.format("Rating: %.1f/5.0", site.getRating()));
            feedbackLabel.setText("Details loaded successfully.");
            feedbackLabel.setForeground(new Color(0, 100, 0)); // Dark green for success
        } else {
            clearDetails();
            feedbackLabel.setText("No site selected or found.");
            feedbackLabel.setForeground(Color.BLACK);
        }
    }
    /**
     * Displays an error message on the panel. All site details are cleared.
     *
     * @param message The error message to display.
     */
    public void displayErrorMessage(String message) {
        clearDetails(); // Clear any previous site details
        feedbackLabel.setText("Error: " + message);
        feedbackLabel.setForeground(Color.RED); // Red color for errors
    }
    /**
     * Displays an informational or success message on the panel.
     *
     * @param message The informational message to display.
     */
    public void displayInfoMessage(String message) {
        // Clear previous details if this is a fresh informational message
        // Or keep previous details if it's just a status update
        // For instructional messages, it's usually better to clear.
        clearDetails(); 
        feedbackLabel.setText(message);
        feedbackLabel.setForeground(new Color(0, 100, 0)); // Dark green for info/success
    }
}