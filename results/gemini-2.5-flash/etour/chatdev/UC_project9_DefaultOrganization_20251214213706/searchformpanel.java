'''
JPanel component representing the search form for points of rest.
It provides input fields for name, location, and type, and a search button.
The panel communicates search requests via a {@link SearchActionListener} interface.
'''
package com.chatdev.ricercapuntidiristoro.ui;
import javax.swing.*;
import java.awt.*;
/**
 * JPanel component representing the search form for points of rest.
 * It provides input fields for name, location, and type, and a search button.
 * The panel communicates search requests via a {@link SearchActionListener} interface.
 */
public class SearchFormPanel extends JPanel {
    private JTextField nameField;
    private JTextField locationField;
    private JTextField typeField;
    private JButton searchButton;
    /**
     * Interface for callback when the search button is pressed.
     * This allows the parent component (e.g., the main application frame)
     * to perform the actual search logic without tight coupling.
     */
    public interface SearchActionListener {
        /**
         * Called when the search button is clicked.
         * @param name The text from the name field, trimmed.
         * @param location The text from the location field, trimmed.
         * @param type The text from the type field, trimmed.
         */
        void onSearch(String name, String location, String type);
    }
    /**
     * Constructs a new SearchFormPanel and initializes its components and layout.
     * A {@link SearchActionListener} must be provided to handle search button clicks.
     *
     * @param listener The callback listener to be notified when the search button is clicked.
     */
    public SearchFormPanel(SearchActionListener listener) {
        // Use GridBagLayout for a flexible and aligned form layout.
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Cerca Punti di Ristoro")); // Add a border with a title
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding around components
        gbc.fill = GridBagConstraints.HORIZONTAL; // Components expand horizontally
        // Configure and add Name field
        gbc.gridx = 0; // Column 0
        gbc.gridy = 0; // Row 0
        gbc.weightx = 0.0; // Don't give extra horizontal space to label
        add(new JLabel("Nome:"), gbc);
        gbc.gridx = 1; // Column 1
        gbc.gridy = 0; // Row 0
        gbc.weightx = 1.0; // Give extra horizontal space to text field
        nameField = new JTextField(20); // 20 columns wide hint
        add(nameField, gbc);
        // Configure and add Location field
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.weightx = 0.0;
        add(new JLabel("Località:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        locationField = new JTextField(20);
        add(locationField, gbc);
        // Configure and add Type field
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.weightx = 0.0;
        add(new JLabel("Tipo:"), gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        typeField = new JTextField(20);
        add(typeField, gbc);
        // Configure and add Search Button
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.gridwidth = 2; // Span across two columns
        gbc.weightx = 0; // Don't allow button to expand horizontally
        gbc.fill = GridBagConstraints.NONE; // Button should not fill horizontally
        gbc.anchor = GridBagConstraints.CENTER; // Center the button
        searchButton = new JButton("Cerca");
        // Add an ActionListener to the search button.
        // It retrieves text from input fields and calls the onSearch method of the listener.
        searchButton.addActionListener(e -> {
            listener.onSearch(
                    nameField.getText().trim(),     // Get text, trim whitespace
                    locationField.getText().trim(), // Get text, trim whitespace
                    typeField.getText().trim()      // Get text, trim whitespace
            );
        });
        add(searchButton, gbc);
    }
    /**
     * Enables or disables all input fields and the search button on the form.
     * This is useful for providing visual feedback to the user that an operation
     * is in progress and preventing further input until the operation completes.
     *
     * @param enabled True to enable the form components, false to disable them.
     */
    public void setFormEnabled(boolean enabled) {
        nameField.setEnabled(enabled);
        locationField.setEnabled(enabled);
        typeField.setEnabled(enabled);
        searchButton.setEnabled(enabled);
    }
}