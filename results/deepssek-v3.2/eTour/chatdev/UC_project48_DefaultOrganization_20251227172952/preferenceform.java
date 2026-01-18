/**
 * Form for editing generic personal preferences.
 * Implements Steps 2-3 of the use case flow.
 */
package modifygenericpreference;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class PreferenceForm extends JDialog {
    private Tourist tourist;
    private Preferences preferences;
    private PreferenceService preferenceService;
    private JFrame parentFrame;
    private JComboBox<String> languageCombo;
    private JComboBox<String> themeCombo;
    private JCheckBox emailNotificationsCheck;
    private JCheckBox pushNotificationsCheck;
    private JComboBox<String> currencyCombo;
    private JComboBox<String> dateFormatCombo;
    private JSpinner resultsPerPageSpinner;
    private JCheckBox accessibilityCheck;
    public PreferenceForm(JFrame parent, Tourist tourist, Preferences preferences, PreferenceService service) {
        super(parent, "Modify Generic Personal Preferences", true);
        this.parentFrame = parent;
        this.tourist = tourist;
        this.preferences = preferences;
        this.preferenceService = service;
        initializeUI();
        loadCurrentPreferences();
    }
    private void initializeUI() {
        setSize(500, 500);
        setLocationRelativeTo(parentFrame);
        setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridBagLayout());
        formPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        GridBagConstraints gbc = new GridBagConstraints();
        gbc.fill = GridBagConstraints.HORIZONTAL;
        gbc.insets = new Insets(5, 5, 5, 5);
        JLabel titleLabel = new JLabel("Edit Your Generic Personal Preferences");
        titleLabel.setFont(new Font("Arial", Font.BOLD, 16));
        gbc.gridx = 0;
        gbc.gridy = 0;
        gbc.gridwidth = 2;
        formPanel.add(titleLabel, gbc);
        gbc.gridwidth = 1;
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Preferred Language:"), gbc);
        gbc.gridx = 1;
        languageCombo = new JComboBox<>(new String[]{"English", "Spanish", "French", "German", "Italian", "Japanese"});
        formPanel.add(languageCombo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Theme:"), gbc);
        gbc.gridx = 1;
        themeCombo = new JComboBox<>(new String[]{"light", "dark", "auto"});
        formPanel.add(themeCombo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Email Notifications:"), gbc);
        gbc.gridx = 1;
        emailNotificationsCheck = new JCheckBox("Receive email notifications");
        formPanel.add(emailNotificationsCheck, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Push Notifications:"), gbc);
        gbc.gridx = 1;
        pushNotificationsCheck = new JCheckBox("Receive push notifications");
        formPanel.add(pushNotificationsCheck, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Currency:"), gbc);
        gbc.gridx = 1;
        currencyCombo = new JComboBox<>(new String[]{"USD", "EUR", "GBP", "JPY", "CAD", "AUD"});
        formPanel.add(currencyCombo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Date Format:"), gbc);
        gbc.gridx = 1;
        dateFormatCombo = new JComboBox<>(new String[]{"YYYY-MM-DD", "MM/DD/YYYY", "DD/MM/YYYY", "YYYY/MM/DD"});
        formPanel.add(dateFormatCombo, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Results Per Page:"), gbc);
        gbc.gridx = 1;
        SpinnerNumberModel spinnerModel = new SpinnerNumberModel(10, 5, 100, 5);
        resultsPerPageSpinner = new JSpinner(spinnerModel);
        formPanel.add(resultsPerPageSpinner, gbc);
        gbc.gridy++;
        gbc.gridx = 0;
        formPanel.add(new JLabel("Accessibility Mode:"), gbc);
        gbc.gridx = 1;
        accessibilityCheck = new JCheckBox("Enable accessibility features");
        formPanel.add(accessibilityCheck, gbc);
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        JButton submitButton = new JButton("Submit Changes");
        submitButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                submitForm();
            }
        });
        JButton cancelButton = new JButton("Cancel");
        cancelButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                dispose();
            }
        });
        buttonPanel.add(submitButton);
        buttonPanel.add(cancelButton);
        add(formPanel, BorderLayout.CENTER);
        add(buttonPanel, BorderLayout.SOUTH);
    }
    private void loadCurrentPreferences() {
        // Add null-safety: if any preference getter returns null, use a sensible default.
        languageCombo.setSelectedItem(preferences.getPreferredLanguage() != null ? preferences.getPreferredLanguage() : "English");
        themeCombo.setSelectedItem(preferences.getTheme() != null ? preferences.getTheme() : "auto");
        emailNotificationsCheck.setSelected(preferences.isEmailNotifications());
        pushNotificationsCheck.setSelected(preferences.isPushNotifications());
        currencyCombo.setSelectedItem(preferences.getCurrency() != null ? preferences.getCurrency() : "USD");
        dateFormatCombo.setSelectedItem(preferences.getDateFormat() != null ? preferences.getDateFormat() : "YYYY-MM-DD");
        // Ensure spinner value is valid: use default if null or out of bounds.
        int currentResults = preferences.getResultsPerPage();
        if (currentResults < 5 || currentResults > 100) {
            currentResults = 10;
        }
        resultsPerPageSpinner.setValue(currentResults);
        accessibilityCheck.setSelected(preferences.isAccessibilityMode());
    }
    private void collectFormData() {
        preferences.setPreferredLanguage((String) languageCombo.getSelectedItem());
        preferences.setTheme((String) themeCombo.getSelectedItem());
        preferences.setEmailNotifications(emailNotificationsCheck.isSelected());
        preferences.setPushNotifications(pushNotificationsCheck.isSelected());
        preferences.setCurrency((String) currencyCombo.getSelectedItem());
        preferences.setDateFormat((String) dateFormatCombo.getSelectedItem());
        // Safely handle the spinner value: ensure it's an Integer before casting.
        Object spinnerValue = resultsPerPageSpinner.getValue();
        if (spinnerValue instanceof Integer) {
            preferences.setResultsPerPage((Integer) spinnerValue);
        } else {
            // Fallback to default if the spinner returns an unexpected type.
            preferences.setResultsPerPage(10);
        }
        preferences.setAccessibilityMode(accessibilityCheck.isSelected());
    }
    private void submitForm() {
        collectFormData();
        int confirmation = JOptionPane.showConfirmDialog(this,
            "Are you sure you want to save these changes?\n\n" +
            "Your preferences will be updated with the following:\n" +
            "• Language: " + preferences.getPreferredLanguage() + "\n" +
            "• Theme: " + preferences.getTheme() + "\n" +
            "• Email Notifications: " + (preferences.isEmailNotifications() ? "On" : "Off") + "\n" +
            "• Push Notifications: " + (preferences.isPushNotifications() ? "On" : "Off") + "\n" +
            "• Currency: " + preferences.getCurrency() + "\n" +
            "• Date Format: " + preferences.getDateFormat(),
            "Confirm Changes",
            JOptionPane.YES_NO_OPTION,
            JOptionPane.QUESTION_MESSAGE);
        if (confirmation == JOptionPane.YES_OPTION) {
            savePreferences();
        }
    }
    private void savePreferences() {
        JProgressBar progressBar = new JProgressBar();
        progressBar.setIndeterminate(true);
        JPanel progressPanel = new JPanel(new BorderLayout());
        progressPanel.add(new JLabel("Storing preferences changed..."), BorderLayout.NORTH);
        progressPanel.add(progressBar, BorderLayout.CENTER);
        JDialog progressDialog = new JDialog(this, "Saving", true);
        progressDialog.add(progressPanel);
        progressDialog.setSize(300, 100);
        progressDialog.setLocationRelativeTo(this);
        SwingWorker<Boolean, Void> worker = new SwingWorker<Boolean, Void>() {
            @Override
            protected Boolean doInBackground() throws Exception {
                return preferenceService.updatePreferences(tourist.getUsername(), preferences);
            }
            @Override
            protected void done() {
                progressDialog.dispose();
                try {
                    boolean success = get();
                    if (success) {
                        dispose();
                        if (parentFrame instanceof MainFrame) {
                            ((MainFrame) parentFrame).showSuccessNotification();
                        }
                    } else {
                        JOptionPane.showMessageDialog(PreferenceForm.this,
                            "Failed to save preferences. Please try again.",
                            "Save Error",
                            JOptionPane.ERROR_MESSAGE);
                    }
                } catch (Exception e) {
                    String message = e.getMessage() != null ? e.getMessage() : "Unknown error";
                    // Provide user‑friendly message for server interruption.
                    if (message.contains("ETOUR")) {
                        message = "Connection to server ETOUR interrupted. Please try again later.";
                    }
                    JOptionPane.showMessageDialog(PreferenceForm.this,
                        "Error: " + message,
                        "Save Error",
                        JOptionPane.ERROR_MESSAGE);
                }
            }
        };
        worker.execute();
        progressDialog.setVisible(true);
    }
}