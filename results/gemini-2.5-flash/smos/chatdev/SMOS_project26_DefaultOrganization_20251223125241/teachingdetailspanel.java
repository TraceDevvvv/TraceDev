/**
 * A JPanel that displays the detailed information of a selected teaching.
 * It includes a "Delete" button that, when clicked, notifies a listener
 * to perform the deletion operation.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class TeachingDetailsPanel extends JPanel {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextArea descriptionArea;
    private JButton deleteButton;
    private Teaching currentTeaching; // Stores the currently displayed teaching
    // Listener interface for delete teaching events
    public interface DeleteTeachingListener {
        void onDeleteTeaching(String teachingId);
    }
    private DeleteTeachingListener deleteListener;
    /**
     * Constructs a new TeachingDetailsPanel.
     */
    public TeachingDetailsPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding between components
        gbc.anchor = GridBagConstraints.WEST; // Default to left alignment
        gbc.fill = GridBagConstraints.HORIZONTAL; // Default to horizontal fill
        // Title
        JLabel titleLabel = new JLabel("Teaching Details", SwingConstants.CENTER);
        titleLabel.setFont(new Font("Serif", Font.BOLD, 24));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.anchor = GridBagConstraints.CENTER; // Center the title label
        add(titleLabel, gbc);
        // ID display
        gbc.gridwidth = 1; // Reset grid width
        gbc.gridy++; // Move to next row
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST; // Label to the right
        add(new JLabel("ID:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST; // Value to the left
        idLabel = new JLabel("N/A");
        add(idLabel, gbc);
        // Name display
        gbc.gridy++; // Move to next row
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.EAST;
        add(new JLabel("Name:"), gbc);
        gbc.gridx = 1;
        gbc.anchor = GridBagConstraints.WEST;
        nameLabel = new JLabel("N/A");
        add(nameLabel, gbc);
        // Description display
        gbc.gridy++; // Move to next row
        gbc.gridx = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST; // Align label to top-right
        add(new JLabel("Description:"), gbc);
        gbc.gridx = 1; // Second column for the text area
        gbc.weightx = 1.0; // Allow description area to expand horizontally
        gbc.weighty = 1.0; // Allow description area to expand vertically
        gbc.fill = GridBagConstraints.BOTH; // Fill both horizontally and vertically
        descriptionArea = new JTextArea(5, 20); // 5 rows, 20 columns as preferred size
        descriptionArea.setEditable(false); // Make it read-only
        descriptionArea.setLineWrap(true); // Wrap lines
        descriptionArea.setWrapStyleWord(true); // Wrap at word boundaries
        JScrollPane scrollPane = new JScrollPane(descriptionArea); // Add scrollability
        add(scrollPane, gbc);
        // Delete Button
        deleteButton = new JButton("Delete Teaching");
        deleteButton.setBackground(new Color(220, 80, 80)); // Reddish color for delete
        deleteButton.setForeground(Color.WHITE);
        deleteButton.setFocusPainted(false); // No border painted when focused
        deleteButton.setFont(new Font("Dialog", Font.BOLD, 14));
        deleteButton.setEnabled(false); // Initially disabled until a teaching is displayed
        gbc.gridy++; // Move to next row
        gbc.gridx = 0;
        gbc.gridwidth = 2; // Span two columns
        gbc.weightx = 0.0; // Reset weights
        gbc.weighty = 0.0;
        gbc.fill = GridBagConstraints.NONE; // Do not fill space
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        add(deleteButton, gbc);
        // Add action listener to the delete button
        deleteButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Check if a teaching is currently displayed and a listener is set
                if (currentTeaching != null && deleteListener != null) {
                    // Show a confirmation dialog before performing the irreversible delete action
                    int confirm = JOptionPane.showConfirmDialog(
                            TeachingDetailsPanel.this, // Parent component for the dialog
                            "Are you sure you want to delete '" + currentTeaching.getName() + "'?",
                            "Confirm Deletion",
                            JOptionPane.YES_NO_OPTION,
                            JOptionPane.WARNING_MESSAGE
                    );
                    if (confirm == JOptionPane.YES_OPTION) {
                        // If confirmed, notify the listener to delete the teaching
                        deleteListener.onDeleteTeaching(currentTeaching.getId());
                    }
                }
            }
        });
    }
    /**
     * Sets the listener for delete teaching events.
     * @param listener The object that will receive delete teaching notifications.
     */
    public void setDeleteTeachingListener(DeleteTeachingListener listener) {
        this.deleteListener = listener;
    }
    /**
     * Displays the details of a given teaching in the panel.
     * If teaching is null, clears the displayed information.
     * @param teaching The Teaching object whose details are to be displayed, or null to clear.
     */
    public void displayTeaching(Teaching teaching) {
        this.currentTeaching = teaching; // Store the currently displayed teaching
        if (teaching != null) {
            // Update labels and text area with teaching details
            idLabel.setText(teaching.getId());
            nameLabel.setText(teaching.getName());
            descriptionArea.setText(teaching.getDescription());
            deleteButton.setEnabled(true); // Enable delete button if a teaching is displayed
        } else {
            // Clear fields if no teaching is selected or found
            idLabel.setText("N/A");
            nameLabel.setText("N/A");
            descriptionArea.setText("");
            deleteButton.setEnabled(false); // Disable delete button if no teaching to delete
        }
        // Scroll to the top of the description area when new content is displayed
        descriptionArea.setCaretPosition(0);
    }
}