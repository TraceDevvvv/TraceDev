package gui;
import model.SearchCriteria;
import javax.swing.*;
import java.awt.*;
/*
 * Represents the Advanced Search Form panel.
 * This panel allows the tourist to input various criteria for their search.
 */
public class AdvancedSearchFormPanel extends JPanel {
    private final SearchSubmitListener searchSubmitListener;
    // GUI Components for search inputs
    private final JTextField keywordField;
    private final JComboBox<String> categoryComboBox;
    private final JSlider priceRangeSlider; // Simulated price range
    private final JTextField locationField; // Tourist's current location input
    private final JButton submitButton;
    /**
     * Constructs a new AdvancedSearchFormPanel.
     *
     * @param listener The listener to be notified when the search form is submitted.
     */
    public AdvancedSearchFormPanel(SearchSubmitListener listener) {
        this.searchSubmitListener = listener;
        setLayout(new BorderLayout(10, 10)); // Use BorderLayout for overall structure, padding
        // Create input form panel
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder("Advanced Search Criteria"));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Stretch components horizontally
        // Keyword Field
        gbc.gridx = 0;
        gbc.gridy = 0;
        formPanel.add(new JLabel("Keyword:"), gbc);
        gbc.gridx = 1;
        keywordField = new JTextField(20);
        formPanel.add(keywordField, gbc);
        // Category ComboBox
        gbc.gridx = 0;
        gbc.gridy = 1;
        formPanel.add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        String[] categories = {"Any", "Museum", "Park", "Historical", "Monument"};
        categoryComboBox = new JComboBox<>(categories);
        formPanel.add(categoryComboBox, gbc);
        // Price Range Slider (Simulated: 0-Free, 1-Cheap, 2-Moderate, 3-Expensive)
        gbc.gridx = 0;
        gbc.gridy = 2;
        formPanel.add(new JLabel("Max Price Level:"), gbc);
        gbc.gridx = 1;
        priceRangeSlider = new JSlider(JSlider.HORIZONTAL, 0, 3, 2); // Min: 0 (free), Max: 3 (expensive), Default: 2 (moderate)
        priceRangeSlider.setMajorTickSpacing(1);
        priceRangeSlider.setPaintTicks(true);
        priceRangeSlider.setPaintLabels(true);
        priceRangeSlider.addChangeListener(e -> {
            String[] priceLabels = {"Free", "Cheap", "Moderate", "Expensive"};
            // Update slider label or tooltip if needed, for now, just shows value
        });
        formPanel.add(priceRangeSlider, gbc);
        // Tourist Location Field
        gbc.gridx = 0;
        gbc.gridy = 3;
        formPanel.add(new JLabel("Your Current Location:"), gbc);
        gbc.gridx = 1;
        locationField = new JTextField(20);
        locationField.setText("Paris"); // Default value for demonstration
        formPanel.add(locationField, gbc);
        // Submit Button
        submitButton = new JButton("Search Sites");
        submitButton.addActionListener(e -> {
            // Collect all input data and create a SearchCriteria object
            String keyword = keywordField.getText();
            String category = (String) categoryComboBox.getSelectedItem();
            int maxPrice = priceRangeSlider.getValue();
            String touristLocation = locationField.getText();
            // Validate essential fields if necessary, e.g., location should not be empty
            if (touristLocation.trim().isEmpty()) {
                JOptionPane.showMessageDialog(this,
                                              "Please enter your current location for an advanced search.",
                                              "Location Required",
                                              JOptionPane.WARNING_MESSAGE);
                return; // Do not proceed with search if location is missing
            }
            SearchCriteria criteria = new SearchCriteria(keyword, category, maxPrice, touristLocation);
            // Notify the listener (AdvancedSearchApp) that a search has been submitted
            searchSubmitListener.onSearchSubmit(criteria);
        });
        // Add formPanel to the center of the BorderLayout
        add(formPanel, BorderLayout.CENTER);
        // Add submitButton to the south of the BorderLayout
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitButton);
        add(buttonPanel, BorderLayout.SOUTH);
    }
}