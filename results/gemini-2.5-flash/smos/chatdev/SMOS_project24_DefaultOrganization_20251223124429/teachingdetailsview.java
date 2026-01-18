'''
A Swing-based JPanel that displays the detailed information of a single teaching.
It provides a user interface to view all attributes of a Teaching object.
'''
import javax.swing.*;
import java.awt.*;
/**
 * A Swing-based JPanel that displays the detailed information of a single teaching.
 * It provides a user interface to view all attributes of a Teaching object.
 */
public class TeachingDetailsView extends JPanel {
    private JLabel idLabel;
    private JLabel titleLabel;
    // descriptionLabel itself is not used to display text, but rather a title for the JTextArea
    private JLabel instructorLabel;
    private JLabel durationLabel;
    private JLabel startDateLabel;
    private JTextArea descriptionTextArea;
    /**
     * Constructs a new TeachingDetailsView.
     * Initializes the GUI components and their layout.
     */
    public TeachingDetailsView() {
        // Set up the panel layout
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.anchor = GridBagConstraints.WEST; // Align components to the left
        // Initialize JLabel components without initial text. Text will be set dynamically.
        idLabel = new JLabel();
        titleLabel = new JLabel();
        // descriptionLabel is the title for the textarea, not filled with data
        instructorLabel = new JLabel();
        durationLabel = new JLabel();
        startDateLabel = new JLabel();
        // Use JTextArea for multi-line description
        descriptionTextArea = new JTextArea(5, 30); // 5 rows, 30 columns for preferred size
        descriptionTextArea.setEditable(false);
        descriptionTextArea.setLineWrap(true);
        descriptionTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(descriptionTextArea); // Add scroll for long descriptions
        // Add components to the panel using GridBagLayout
        // Row 0: ID Label and Value
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.weightx = 0; // Reset weight for title columns
        gbc.fill = GridBagConstraints.NONE; // Do not fill for title labels
        add(new JLabel("Teaching ID:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0; // Allow value labels to expand horizontally
        gbc.fill = GridBagConstraints.HORIZONTAL; // Allow the label to grow horizontally
        add(idLabel, gbc);
        // Row 1: Title Label and Value
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Title:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(titleLabel, gbc);
        // Row 2: Instructor Label and Value
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Instructor:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(instructorLabel, gbc);
        // Row 3: Duration Label and Value
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Duration:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(durationLabel, gbc);
        // Row 4: Start Date Label and Value
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        add(new JLabel("Start Date:"), gbc);
        gbc.gridx = 1;
        gbc.weightx = 1.0;
        gbc.fill = GridBagConstraints.HORIZONTAL;
        add(startDateLabel, gbc);
        // Row 5: Description (occupies two columns for label and scroll pane)
        gbc.gridx = 0;
        gbc.gridy = 5;
        gbc.gridwidth = 1;
        gbc.weightx = 0;
        gbc.fill = GridBagConstraints.NONE;
        gbc.anchor = GridBagConstraints.NORTHWEST; // Align label to top-left
        add(new JLabel("Description:"), gbc);
        gbc.gridx = 1;
        gbc.gridwidth = GridBagConstraints.REMAINDER; // Span remaining columns
        gbc.fill = GridBagConstraints.BOTH; // Expand to fill available space
        gbc.weightx = 1.0; // Allow description area to expand horizontally
        gbc.weighty = 1.0; // Allow description area to expand vertically
        add(scrollPane, gbc);
        // Set initial state to empty
        clearDetails();
    }
    /**
     * Displays the detailed information of a given Teaching object in the GUI.
     * If the teaching object is null, it clears all displayed details.
     *
     * @param teaching The Teaching object whose details are to be displayed.
     *                 If null, all displayed details will be cleared.
     */
    public void displayTeachingDetails(Teaching teaching) {
        if (teaching != null) {
            idLabel.setText(teaching.getId());
            titleLabel.setText(teaching.getTitle());
            descriptionTextArea.setText(teaching.getDescription());
            instructorLabel.setText(teaching.getInstructor());
            durationLabel.setText(teaching.getDurationHours() + " hours");
            startDateLabel.setText(teaching.getStartDate());
        } else {
            clearDetails();
        }
    }
    /**
     * Clears all the displayed teaching details in the view.
     * This is useful when no teaching is selected or an error occurs.
     */
    private void clearDetails() {
        idLabel.setText("N/A");
        titleLabel.setText("N/A");
        descriptionTextArea.setText("No teaching selected or an error occurred.");
        instructorLabel.setText("N/A");
        durationLabel.setText("N/A");
        startDateLabel.setText("N/A");
    }
    /**
     * Displays an error message to the user, typically in a dialog box.
     *
     * @param message The error message to be displayed.
     */
    public void displayErrorMessage(String message) {
        JOptionPane.showMessageDialog(this, message, "Error", JOptionPane.ERROR_MESSAGE);
        clearDetails(); // Clear any partial or old details on error
    }
}