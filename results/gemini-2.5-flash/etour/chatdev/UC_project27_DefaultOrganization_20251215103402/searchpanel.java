/*
A JPanel component responsible for displaying the search input field and a search button.
It allows users to type a query and initiate a search.
*/
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionListener;
public class SearchPanel extends JPanel {
    // Text field for user to enter search query
    private JTextField searchField;
    // Button to trigger the search action
    private JButton searchButton;
    /**
     * Constructs a SearchPanel.
     * Sets up the layout, search input field, and search button.
     */
    public SearchPanel() {
        // Use BorderLayout for organizing components
        setLayout(new BorderLayout(5, 5));
        setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10)); // Add some padding
        // Initialize the search field
        searchField = new JTextField(30);
        searchField.setFont(new Font("SansSerif", Font.PLAIN, 14));
        searchField.setText("Type your search query here..."); // Placeholder text
        searchField.setForeground(Color.GRAY); // Placeholder text color
        // Add focus listener to clear placeholder text on focus and restore if empty
        searchField.addFocusListener(new java.awt.event.FocusAdapter() {
            @Override
            public void focusGained(java.awt.event.FocusEvent evt) {
                if (searchField.getText().equals("Type your search query here...")) {
                    searchField.setText("");
                    searchField.setForeground(Color.BLACK);
                }
            }
            @Override
            public void focusLost(java.awt.event.FocusEvent evt) {
                if (searchField.getText().isEmpty()) {
                    searchField.setText("Type your search query here...");
                    searchField.setForeground(Color.GRAY);
                }
            }
        });
        // Initialize the search button
        searchButton = new JButton("Search Site");
        searchButton.setFont(new Font("SansSerif", Font.BOLD, 14));
        // Add components to the panel
        add(searchField, BorderLayout.CENTER);
        add(searchButton, BorderLayout.EAST);
    }
    /**
     * Returns the text currently entered in the search field.
     * @return The user's search query.
     */
    public String getSearchQuery() {
        if (searchField.getText().equals("Type your search query here...")) {
            return ""; // Return empty string if placeholder is still present
        }
        return searchField.getText();
    }
    /**
     * Sets an ActionListener for the search button.
     * This allows external classes to respond to the button click event.
     * @param listener The ActionListener to be added.
     */
    public void addSearchButtonListener(ActionListener listener) {
        searchButton.addActionListener(listener);
    }
    /**
     * Enables or disables the search button and text field.
     * Useful for preventing multiple searches while one is in progress.
     * @param enabled True to enable, false to disable.
     */
    public void setControlsEnabled(boolean enabled) {
        searchField.setEnabled(enabled);
        searchButton.setEnabled(enabled);
    }
}