'''
A custom JPanel component responsible for displaying the detailed information
of a single {@link BeneCulturale} object. It arranges labels for ID,
name, description, location, and type in a user-friendly format.
'''
package com.chatdev.culturalviewer.gui;
import com.chatdev.culturalviewer.model.BeneCulturale;
import javax.swing.*;
import javax.swing.border.EmptyBorder;
import java.awt.*;
/**
 * '''
 * A custom JPanel component responsible for displaying the detailed information
 * of a single {@link BeneCulturale} object. It arranges labels for ID,
 * name, description, location, and type in a user-friendly format.
 * '''
 */
public class CulturalGoodDetailPanel extends JPanel {
    private JLabel idLabel;
    private JLabel nameLabel;
    private JTextArea descriptionArea;
    private JLabel locationLabel;
    private JLabel typeLabel;
    /**
     * '''
     * Constructs a new CulturalGoodDetailPanel, initializing and laying out
     * its internal GUI components.
     * '''
     */
    public CulturalGoodDetailPanel() {
        setLayout(new GridBagLayout());
        setBorder(BorderFactory.createTitledBorder("Cultural Good Details"));
        setPreferredSize(new Dimension(400, 300));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(5, 5, 5, 5); // Padding
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Initialize and add labels
        JLabel titleId = new JLabel("ID:");
        JLabel titleName = new JLabel("Name:");
        JLabel titleDescription = new JLabel("Description:");
        JLabel titleLocation = new JLabel("Location:");
        JLabel titleType = new JLabel("Type:");
        titleId.setFont(titleId.getFont().deriveFont(Font.BOLD));
        titleName.setFont(titleName.getFont().deriveFont(Font.BOLD));
        titleDescription.setFont(titleDescription.getFont().deriveFont(Font.BOLD));
        titleLocation.setFont(titleLocation.getFont().deriveFont(Font.BOLD));
        titleType.setFont(titleType.getFont().deriveFont(Font.BOLD));
        idLabel = new JLabel("N/A");
        nameLabel = new JLabel("N/A");
        descriptionArea = new JTextArea("N/A");
        locationLabel = new JLabel("N/A");
        typeLabel = new JLabel("N/A");
        // Description area styling
        descriptionArea.setLineWrap(true);
        descriptionArea.setWrapStyleWord(true);
        descriptionArea.setEditable(false);
        descriptionArea.setBackground(getBackground()); // Match panel background
        descriptionArea.setFont(idLabel.getFont()); // Match other labels' font
        JScrollPane descriptionScrollPane = new JScrollPane(descriptionArea);
        descriptionScrollPane.setVerticalScrollBarPolicy(JScrollPane.VERTICAL_SCROLLBAR_AS_NEEDED);
        descriptionScrollPane.setPreferredSize(new Dimension(300, 100)); // Fixed size for description area
        // Add ID row
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(titleId, gbc);
        gbc.gridx = 1;
        gbc.gridy = 0;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(idLabel, gbc);
        // Add Name row
        gbc.gridx = 0;
        gbc.gridy = 1;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(titleName, gbc);
        gbc.gridx = 1;
        gbc.gridy = 1;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(nameLabel, gbc);
        // Add Description row
        gbc.gridx = 0;
        gbc.gridy = 2;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(titleDescription, gbc);
        gbc.gridx = 1;
        gbc.gridy = 2;
        gbc.weightx = 1.0;
        gbc.weighty = 1.0; // Allow description to expand vertically
        gbc.fill = GridBagConstraints.BOTH;
        add(descriptionScrollPane, gbc);
        // Add Location row
        gbc.gridx = 0;
        gbc.gridy = 3;
        gbc.weighty = 0.0; // Reset weight
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(titleLocation, gbc);
        gbc.gridx = 1;
        gbc.gridy = 3;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(locationLabel, gbc);
        // Add Type row
        gbc.gridx = 0;
        gbc.gridy = 4;
        gbc.anchor = GridBagConstraints.NORTHEAST;
        add(titleType, gbc);
        gbc.gridx = 1;
        gbc.gridy = 4;
        gbc.weightx = 1.0;
        gbc.anchor = GridBagConstraints.NORTHWEST;
        add(typeLabel, gbc);
    }
    /**
     * '''
     * Updates the panel to display the details of the given {@link BeneCulturale} object.
     * If the culturalGood is null, it clears the current details.
     *
     * @param culturalGood The {@link BeneCulturale} object whose details are to be displayed.
     * '''
     */
    public void displayCulturalGood(BeneCulturale culturalGood) {
        if (culturalGood != null) {
            idLabel.setText(culturalGood.getId());
            nameLabel.setText(culturalGood.getName());
            descriptionArea.setText(culturalGood.getDescription());
            locationLabel.setText(culturalGood.getLocation()); // Fixed: Changed from Goodlocation() to getLocation()
            typeLabel.setText(culturalGood.getType());
        } else {
            clearDetails();
        }
    }
    /**
     * '''
     * Clears all displayed cultural good details, resetting them to "N/A".
     * '''
     */
    public void clearDetails() {
        idLabel.setText("N/A");
        nameLabel.setText("N/A");
        descriptionArea.setText("N/A");
        locationLabel.setText("N/A");
        typeLabel.setText("N/A");
    }
}