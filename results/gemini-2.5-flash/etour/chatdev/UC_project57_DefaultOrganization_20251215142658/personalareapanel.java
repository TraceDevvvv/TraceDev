package gui;
import javax.swing.*;
import java.awt.*;
import java.util.function.Consumer;
/*
 * Represents the "Personal Area" panel in the application.
 * This is the initial screen a user sees after (assumed) authentication.
 * It contains a button to enable the advanced search feature.
 */
public class PersonalAreaPanel extends JPanel {
    private final Consumer<String> showPanelCallback;
    /**
     * Constructs a new PersonalAreaPanel.
     *
     * @param showPanelCallback A callback function to notify the main application
     *                          to switch to a different panel when a button is clicked.
     */
    public PersonalAreaPanel(Consumer<String> showPanelCallback) {
        this.showPanelCallback = showPanelCallback;
        setLayout(new GridBagLayout()); // Use GridBagLayout for flexible centering
        // Create a JLabel for a welcome message
        JLabel titleLabel = new JLabel("Welcome to Your Personal Area, Tourist!");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 24));
        titleLabel.setHorizontalAlignment(SwingConstants.CENTER);
        // Create the button to enable advanced search
        JButton enableSearchButton = new JButton("Enable Advanced Search");
        enableSearchButton.setFont(new Font("Arial", Font.PLAIN, 18));
        enableSearchButton.setPreferredSize(new Dimension(250, 50)); // Set preferred size
        // Add an ActionListener to the button
        enableSearchButton.addActionListener(e -> {
            // When button is clicked, invoke the callback to show the search form panel
            showPanelCallback.accept(AdvancedSearchApp.SEARCH_FORM_PANEL);
        });
        // Set up GridBagConstraints for layout
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.insets = new Insets(10, 10, 30, 10); // Padding around the title
        add(titleLabel, gbc);
        gbc.gridy = 1;
        gbc.insets = new Insets(0, 10, 10, 10); // Padding around the button
        add(enableSearchButton, gbc);
    }
}