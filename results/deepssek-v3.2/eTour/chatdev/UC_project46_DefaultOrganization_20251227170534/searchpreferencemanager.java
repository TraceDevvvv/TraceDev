/**
Main GUI for displaying and modifying search preferences.
Implements the flow of events from the use case:
1. Access modification functionality
2. Display preferences in form
3. Edit and submit form
4-6. Handle confirmation and saving
*/
import javax.swing.BorderFactory;
import javax.swing.ButtonGroup;
import javax.swing.JButton;
import javax.swing.JCheckBox;
import javax.swing.JComboBox;
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JOptionPane;
import javax.swing.JPanel;
import javax.swing.JRadioButton;
import javax.swing.JScrollPane;
import javax.swing.JSlider;
import javax.swing.JSpinner;
import javax.swing.SpinnerNumberModel;
import javax.swing.SwingConstants;
import javax.swing.border.TitledBorder;
import javax.swing.event.ChangeEvent;
import javax.swing.event.ChangeListener;
import java.awt.BorderLayout;
import java.awt.Color;
import java.awt.FlowLayout;
import java.awt.Font;
import java.awt.GridBagConstraints;
import java.awt.GridBagLayout;
import java.awt.Insets;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class SearchPreferenceManager extends JFrame {
    private String username;
    private DatabaseSimulator dbSimulator;
    private SearchPreference currentPreferences;
    // Form components
    private JComboBox<String> destinationCombo;
    private JSpinner budgetSpinner;
    private JSlider distanceSlider;
    private JComboBox<String> accommodationCombo;
    private JCheckBox familyFriendlyCheck;
    private JCheckBox adventureCheck;
    private ButtonGroup foodGroup;
    private JRadioButton vegRadio;
    private JRadioButton nonVegRadio;
    private JRadioButton veganRadio;
    private JButton saveButton;
    private JButton cancelButton;
    private JButton resetButton;
    public SearchPreferenceManager(String username) {
        this.username = username;
        this.dbSimulator = DatabaseSimulator.getInstance();
        try {
            // Load user preferences from database
            this.currentPreferences = dbSimulator.getUserPreferences(username);
            initializeUI();
        } catch (Exception e) {
            handleServerError(e);
        }
    }
    private void initializeUI() {
        setTitle("ETOUR - Modify Search Preferences");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 550);
        setLocationRelativeTo(null);
        // Main panel with border layout
        JPanel mainPanel = new JPanel(new BorderLayout(10, 10));
        mainPanel.setBorder(BorderFactory.createEmptyBorder(15, 15, 15, 15));
        // Header
        JLabel headerLabel = new JLabel("Modify Your Search Preferences", SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 20));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(0, 0, 15, 0));
        mainPanel.add(headerLabel, BorderLayout.NORTH);
        // Form panel
        JPanel formPanel = createFormPanel();
        mainPanel.add(new JScrollPane(formPanel), BorderLayout.CENTER);
        // Button panel
        JPanel buttonPanel = createButtonPanel();
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel);
        setVisible(true);
    }
    private JPanel createFormPanel() {
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createTitledBorder(
            BorderFactory.createLineBorder(Color.GRAY), 
            "Search Preference Form", 
            TitledBorder.DEFAULT_JUSTIFICATION, 
            TitledBorder.DEFAULT_POSITION, 
            new Font("Arial", Font.BOLD, 14)
        ));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.insets = new Insets(8, 10, 8, 10);
        gbc.fill = GridBagConstraints.HORIZONTAL;
        // Row 0: Destination Type
        gbc.gridx = 0; gbc.gridy = 0;
        formPanel.add(new JLabel("Destination Type:"), gbc);
        gbc.gridx = 1;
        String[] destinations = {"Beach", "Mountain", "City", "Countryside", "Historical", "Island"};
        destinationCombo = new JComboBox<>(destinations);
        destinationCombo.setSelectedItem(currentPreferences.getDestinationType());
        formPanel.add(destinationCombo, gbc);
        // Row 1: Max Budget
        gbc.gridx = 0; gbc.gridy = 1;
        formPanel.add(new JLabel("Max Budget ($):"), gbc);
        gbc.gridx = 1;
        SpinnerNumberModel budgetModel = new SpinnerNumberModel(
            currentPreferences.getMaxBudget(), 100, 10000, 100);
        budgetSpinner = new JSpinner(budgetModel);
        formPanel.add(budgetSpinner, gbc);
        // Row 2: Travel Distance
        gbc.gridx = 0; gbc.gridy = 2;
        formPanel.add(new JLabel("Max Travel Distance:"), gbc);
        gbc.gridx = 1;
        distanceSlider = new JSlider(0, 2000, currentPreferences.getTravelDistance());
        distanceSlider.setMajorTickSpacing(500);
        distanceSlider.setMinorTickSpacing(100);
        distanceSlider.setPaintTicks(true);
        distanceSlider.setPaintLabels(true);
        JPanel sliderPanel = new JPanel(new BorderLayout());
        sliderPanel.add(distanceSlider, BorderLayout.CENTER);
        JLabel distanceValue = new JLabel(currentPreferences.getTravelDistance() + " km");
        distanceValue.setHorizontalAlignment(SwingConstants.CENTER);
        sliderPanel.add(distanceValue, BorderLayout.SOUTH);
        // Update label when slider moves
        distanceSlider.addChangeListener(e -> {
            distanceValue.setText(distanceSlider.getValue() + " km");
        });
        formPanel.add(sliderPanel, gbc);
        // Row 3: Accommodation Type
        gbc.gridx = 0; gbc.gridy = 3;
        formPanel.add(new JLabel("Accommodation Type:"), gbc);
        gbc.gridx = 1;
        String[] accommodations = {"Hotel", "Hostel", "Airbnb", "Resort", "Camping"};
        accommodationCombo = new JComboBox<>(accommodations);
        accommodationCombo.setSelectedItem(currentPreferences.getAccommodationType());
        formPanel.add(accommodationCombo, gbc);
        // Row 4: Family Friendly
        gbc.gridx = 0; gbc.gridy = 4;
        formPanel.add(new JLabel("Family Friendly:"), gbc);
        gbc.gridx = 1;
        familyFriendlyCheck = new JCheckBox("Include family-friendly options");
        familyFriendlyCheck.setSelected(currentPreferences.isFamilyFriendly());
        formPanel.add(familyFriendlyCheck, gbc);
        // Row 5: Adventure Activities
        gbc.gridx = 0; gbc.gridy = 5;
        formPanel.add(new JLabel("Adventure Activities:"), gbc);
        gbc.gridx = 1;
        adventureCheck = new JCheckBox("Include adventure activities");
        adventureCheck.setSelected(currentPreferences.isAdventureActivities());
        formPanel.add(adventureCheck, gbc);
        // Row 6: Food Preference
        gbc.gridx = 0; gbc.gridy = 6;
        formPanel.add(new JLabel("Food Preference:"), gbc);
        gbc.gridx = 1;
        JPanel foodPanel = new JPanel(new FlowLayout(FlowLayout.LEFT, 10, 5));
        foodGroup = new ButtonGroup();
        vegRadio = new JRadioButton("Vegetarian");
        nonVegRadio = new JRadioButton("Non-vegetarian");
        veganRadio = new JRadioButton("Vegan");
        foodGroup.add(vegRadio);
        foodGroup.add(nonVegRadio);
        foodGroup.add(veganRadio);
        foodPanel.add(vegRadio);
        foodPanel.add(nonVegRadio);
        foodPanel.add(veganRadio);
        // Set current selection
        String currentFood = currentPreferences.getFoodPreference();
        if (currentFood.equals("Vegetarian")) vegRadio.setSelected(true);
        else if (currentFood.equals("Vegan")) veganRadio.setSelected(true);
        else nonVegRadio.setSelected(true);
        formPanel.add(foodPanel, gbc);
        return formPanel;
    }
    private JPanel createButtonPanel() {
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        saveButton = new JButton("Save Changes");
        saveButton.setFont(new Font("Arial", Font.BOLD, 14));
        saveButton.setBackground(new Color(70, 130, 180));
        saveButton.setForeground(Color.WHITE);
        saveButton.addActionListener(e -> savePreferences());
        cancelButton = new JButton("Cancel");
        cancelButton.setFont(new Font("Arial", Font.BOLD, 14));
        cancelButton.addActionListener(e -> {
            int confirm = JOptionPane.showConfirmDialog(this,
                "Are you sure you want to cancel? All unsaved changes will be lost.",
                "Confirm Cancellation",
                JOptionPane.YES_NO_OPTION);
            if (confirm == JOptionPane.YES_OPTION) {
                JOptionPane.showMessageDialog(this,
                    "Operation cancelled. Returning to main menu.",
                    "Cancelled",
                    JOptionPane.INFORMATION_MESSAGE);
                dispose();
                new AuthenticationFrame();
            }
        });
        resetButton = new JButton("Reset to Defaults");
        resetButton.setFont(new Font("Arial", Font.PLAIN, 12));
        resetButton.addActionListener(e -> resetToDefaults());
        buttonPanel.add(saveButton);
        buttonPanel.add(cancelButton);
        buttonPanel.add(resetButton);
        return buttonPanel;
    }
    private void savePreferences() {
        try {
            // Get updated preferences from form
            SearchPreference updatedPref = getPreferencesFromForm();
            // Use the dedicated confirmation dialog (Step 4-5 from use case)
            PreferenceChangeDialog confirmDialog = new PreferenceChangeDialog(
                this, currentPreferences, updatedPref);
            confirmDialog.setVisible(true);
            if (confirmDialog.isConfirmed()) {
                // Save to database (Step 6: Memorize search preferences changed)
                boolean success = dbSimulator.saveUserPreferences(username, updatedPref);
                if (success) {
                    currentPreferences = updatedPref;
                    // Show success message (Exit condition: successful modification)
                    JOptionPane.showMessageDialog(this,
                        "Search preferences have been successfully updated!",
                        "Success",
                        JOptionPane.INFORMATION_MESSAGE);
                    // Return to authentication screen
                    dispose();
                    new AuthenticationFrame();
                }
            }
        } catch (Exception e) {
            handleServerError(e);
        }
    }
    private SearchPreference getPreferencesFromForm() {
        String selectedFood;
        if (vegRadio.isSelected()) selectedFood = "Vegetarian";
        else if (veganRadio.isSelected()) selectedFood = "Vegan";
        else selectedFood = "Non-vegetarian";
        return new SearchPreference(
            (String) destinationCombo.getSelectedItem(),
            (Integer) budgetSpinner.getValue(),
            distanceSlider.getValue(),
            (String) accommodationCombo.getSelectedItem(),
            familyFriendlyCheck.isSelected(),
            adventureCheck.isSelected(),
            selectedFood
        );
    }
    private void resetToDefaults() {
        int confirm = JOptionPane.showConfirmDialog(this,
            "Reset all preferences to default values?",
            "Confirm Reset",
            JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            destinationCombo.setSelectedItem("Beach");
            budgetSpinner.setValue(2000);
            distanceSlider.setValue(500);
            accommodationCombo.setSelectedItem("Hotel");
            familyFriendlyCheck.setSelected(true);
            adventureCheck.setSelected(false);
            nonVegRadio.setSelected(true);
        }
    }
    private void handleServerError(Exception e) {
        JOptionPane.showMessageDialog(this,
            "Error: " + e.getMessage() + "\n\nPlease check your connection and try again.",
            "ETOUR Server Error",
            JOptionPane.ERROR_MESSAGE);
        dispose();
        new AuthenticationFrame();
    }
}