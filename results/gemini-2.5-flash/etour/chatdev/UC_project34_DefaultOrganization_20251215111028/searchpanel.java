/**
 * GUI panel for displaying the search form and capturing user input.
 * It allows the user to enter a keyword, a desired location, and select a category.
 * Upon submission, it notifies a {@code SearchListener} with the collected search criteria.
 * This class directly relates to "Flow of events User System: 2 Displays the corresponding form"
 * and "3 Fill out the form and submit."
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.Objects; // Added for Objects.requireNonNullElse
public class SearchPanel extends JPanel {
    private JTextField keywordField;
    private JTextField locationField;
    private JComboBox<String> categoryComboBox;
    private JButton searchButton;
    private SearchListener searchListener;
    /**
     * Constructs a new SearchPanel.
     * Initializes the UI components for the search form and sets up event handling.
     * @param listener The listener to notify when a search is requested.
     */
    public SearchPanel(SearchListener listener) {
        this.searchListener = listener;
        // Use a GridBagLayout for more flexible component placement
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Search Cultural Heritage Sites"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Keyword Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        add(new JLabel("Keyword:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0; // Allow horizontal resizing
        keywordField = new JTextField(20);
        add(keywordField, gbc);
        // Location Field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0; // Reset weight
        add(new JLabel("Location:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        locationField = new JTextField(20);
        add(locationField, gbc);
        // Category ComboBox
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0;
        add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        String[] categories = {"All", "Museum", "Archaeological Site", "Historical Building"};
        categoryComboBox = new JComboBox<>(categories);
        add(categoryComboBox, gbc);
        // Search Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.fill = GridBagConstraints.NONE; // Do not fill horizontally anymore
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        searchButton = new JButton("Search");
        add(searchButton, gbc);
        // Add action listener to the search button
        searchButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // Collect user input
                String keyword = keywordField.getText();
                String location = locationField.getText();
                // Ensure selected item is not null, default to "All" if it somehow is.
                // The JComboBox should always have "All" selected by default or provide a selected item.
                String category = (String) categoryComboBox.getSelectedItem();
                // Create SearchCriteria object
                // Objects.requireNonNullElse was added to handle potential null from getSelectedItem,
                // though with "All" as first item, it should typically not be null.
                SearchCriteria criteria = new SearchCriteria(keyword, location, Objects.requireNonNullElse(category, "All"));
                // Notify the listener (CulturalHeritageApp) that a search is requested
                if (searchListener != null) {
                    searchListener.onSearchRequested(criteria);
                }
            }
        });
    }
}