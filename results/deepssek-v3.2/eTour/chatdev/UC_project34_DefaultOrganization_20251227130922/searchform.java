'''
SearchForm.java
Form component for collecting search criteria from Guest User.
'''
package culturalheritage.search;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class SearchForm extends JPanel {
    private JTextField keywordField;
    private JComboBox<String> categoryComboBox;
    private JTextField locationField;
    private JButton submitButton;
    public SearchForm() {
        initializeComponents();
        setupLayout();
    }
    private void initializeComponents() {
        // Initialize form fields
        keywordField = new JTextField(25);
        // Categories for cultural heritage
        String[] categories = {
            "All Categories",
            "Archaeological Site",
            "Historic Building",
            "Museum",
            "Art Gallery",
            "Monument",
            "Cultural Landscape",
            "Library",
            "Archive",
            "Traditional Craft"
        };
        categoryComboBox = new JComboBox<>(categories);
        locationField = new JTextField(20);
        submitButton = new JButton("Search Cultural Heritage");
        // Style the submit button
        submitButton.setBackground(new Color(70, 130, 180));
        submitButton.setForeground(Color.WHITE);
        submitButton.setFont(new Font("Arial", Font.BOLD, 14));
    }
    private void setupLayout() {
        setLayout(new GridBagLayout());
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Row 0: Title
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        JLabel titleLabel = new JLabel("Cultural Heritage Search Form");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        add(titleLabel, gbc);
        // Row 1: Keyword
        gbc.gridwidth = 1;
        gbc.gridy = 1;
        gbc.gridx = 0;
        add(new JLabel("Keyword:"), gbc);
        gbc.gridx = 1;
        add(keywordField, gbc);
        // Row 2: Category
        gbc.gridy = 2;
        gbc.gridx = 0;
        add(new JLabel("Category:"), gbc);
        gbc.gridx = 1;
        add(categoryComboBox, gbc);
        // Row 3: Location
        gbc.gridy = 3;
        gbc.gridx = 0;
        add(new JLabel("Location (optional):"), gbc);
        gbc.gridx = 1;
        add(locationField, gbc);
        // Row 4: Submit button
        gbc.gridy = 4;
        gbc.gridx = 0;
        gbc.gridwidth = 2;
        gbc.fill = GridBagConstraints.NONE;
        add(submitButton, gbc);
        // Set preferred size
        setPreferredSize(new Dimension(600, 200));
        setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Step 1-3: Activate search, fill form, submit"
        ));
    }
    // Getters for form data
    public String getKeyword() {
        return keywordField.getText().trim();
    }
    public String getCategory() {
        return (String) categoryComboBox.getSelectedItem();
    }
    public String getLocation() {
        return locationField.getText().trim();
    }
    // Set submit button listener
    public void setSubmitListener(ActionListener listener) {
        submitButton.addActionListener(listener);
    }
    // Clear form method
    public void clearForm() {
        keywordField.setText("");
        categoryComboBox.setSelectedIndex(0);
        locationField.setText("");
    }
}