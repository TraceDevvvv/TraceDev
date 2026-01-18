'''
Main GUI application for the INSERISCIFEEDBACK use case.
Based on Java Swing, it provides an interface for tourists to select a site,
and submit feedback.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
public class FeedbackAppGUI extends JFrame {
    private FeedbackManager feedbackManager;
    private Tourist currentTourist;
    private Site currentSiteSelectedForFeedback; // The site selected in the UI for giving feedback
    // GUI Components
    private JComboBox<Tourist> touristComboBox;
    private JComboBox<Site> touristLocationComboBox; // New: To represent tourist's actual physical location
    private JComboBox<Site> siteComboBoxForFeedback; // Renamed: The site for which feedback is intended
    private JButton activateFeedbackButton;
    private JButton exitButton;
    private JCheckBox simulateErrorCheckBox;
    private JFrame feedbackFormFrame;
    private JSpinner voteSpinner;
    private JTextArea commentTextArea;
    private JButton submitFeedbackButton;
    private JButton cancelFeedbackButton;
    /**
     * Constructs the main GUI application.
     * Initializes the FeedbackManager and pre-populates with sample data.
     */
    public FeedbackAppGUI() {
        super("Tourist Feedback System");
        feedbackManager = new FeedbackManager();
        initializeSampleData();
        initUI();
    }
    /**
     * Initializes sample tourists and sites within the FeedbackManager.
     * This method populates the initial state of the system.
     */
    private void initializeSampleData() {
        // Create Sample Tourists
        Tourist t1 = new Tourist("T101", "Alice Smith");
        Tourist t2 = new Tourist("T102", "Bob Johnson");
        feedbackManager.addTourist(t1);
        feedbackManager.addTourist(t2);
        // Create Sample Sites
        Site s1 = new Site("S001", "Eiffel Tower", "Iconic iron lattice tower in Paris.");
        Site s2 = new Site("S002", "Colosseum", "Ancient Roman amphitheatre.");
        Site s3 = new Site("S003", "Statue of Liberty", "Neoclassical sculpture on Liberty Island.");
        feedbackManager.addSite(s1);
        feedbackManager.addSite(s2);
        feedbackManager.addSite(s3);
        // Simulate initial states and set tourist current locations
        // Alice visits Eiffel Tower and is currently there
        t1.setCurrentSite(s1);
        // Alice has already given feedback for Colosseum
        t1.addVisitedSite(s2); // Alice has visited s2 before
        // This feedback is directly added to bypass normal flow for 'already given feedback' scenario
        Feedback existingFeedback = new Feedback(t1.getId(), s2.getId(), 4, "A bit crowded, but magnificent.");
        t1.giveFeedback(s2, existingFeedback);
        s2.addFeedback(existingFeedback);
        // Bob is currently at Colosseum
        t2.setCurrentSite(s2);
        // Bob has visited Statue of Liberty but not given feedback
        t2.addVisitedSite(s3);
    }
    /**
     * Initializes the main user interface components and layout.
     * This sets up the tourist and site selection, and the action buttons.
     */
    private void initUI() {
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 350); // Adjusted size to accommodate new components
        setLocationRelativeTo(null);
        setLayout(new BorderLayout(10, 10));
        // --- Selection Panel ---
        JPanel selectionPanel = new JPanel(new GridLayout(5, 2, 5, 5)); // Increased grid rows
        selectionPanel.setBorder(BorderFactory.createTitledBorder("User & Site Selection"));
        selectionPanel.add(new JLabel("Select Tourist:"));
        touristComboBox = new JComboBox<>(feedbackManager.getAllTourists().values().toArray(new Tourist[0]));
        touristComboBox.addActionListener(e -> {
            currentTourist = (Tourist) touristComboBox.getSelectedItem();
            if (currentTourist != null) {
                // Update the tourist location combobox to reflect the newly selected tourist's current site
                if (currentTourist.getCurrentSite() != null) {
                    touristLocationComboBox.setSelectedItem(currentTourist.getCurrentSite());
                } else {
                    touristLocationComboBox.setSelectedIndex(-1); // No current site, clear selection
                }
                // The siteComboBoxForFeedback selection (target for feedback) remains independent
            }
        });
        currentTourist = (Tourist) touristComboBox.getSelectedItem(); // Set initial selected tourist
        selectionPanel.add(touristComboBox);
        // New UI component for tourist's actual location
        selectionPanel.add(new JLabel("Tourist's Current Location:"));
        touristLocationComboBox = new JComboBox<>(feedbackManager.getAllSites().values().toArray(new Site[0]));
        touristLocationComboBox.addActionListener(e -> {
            if (currentTourist != null) {
                Site selectedActualLocation = (Site) touristLocationComboBox.getSelectedItem();
                currentTourist.setCurrentSite(selectedActualLocation); // Set the tourist's actual current site
                System.out.println("DEBUG: " + currentTourist.getName() + " is now at " +
                        (selectedActualLocation != null ? selectedActualLocation.getName() : "an unknown location"));
            }
        });
        selectionPanel.add(touristLocationComboBox);
        // Initialize touristLocationComboBox based on the initial currentTourist
        if (currentTourist != null && currentTourist.getCurrentSite() != null) {
            touristLocationComboBox.setSelectedItem(currentTourist.getCurrentSite());
        }
        // Original siteComboBox, now specifically for feedback target
        selectionPanel.add(new JLabel("Select Site for Feedback:")); // Renamed label
        siteComboBoxForFeedback = new JComboBox<>(feedbackManager.getAllSites().values().toArray(new Site[0]));
        siteComboBoxForFeedback.addActionListener(e -> {
            currentSiteSelectedForFeedback = (Site) siteComboBoxForFeedback.getSelectedItem(); // Store the site chosen for feedback
        });
        currentSiteSelectedForFeedback = (Site) siteComboBoxForFeedback.getSelectedItem(); // Set initial selected site for feedback
        selectionPanel.add(siteComboBoxForFeedback);
        selectionPanel.add(new JLabel("Simulate Network Error:"));
        simulateErrorCheckBox = new JCheckBox("Force ETOUR connection failure (100%)"); // More explicit wording
        simulateErrorCheckBox.addActionListener(e -> {
            feedbackManager.setSimulateNetworkFailure(simulateErrorCheckBox.isSelected());
            System.out.println("Network failure simulation set to: " + simulateErrorCheckBox.isSelected());
        });
        selectionPanel.add(simulateErrorCheckBox);
        add(selectionPanel, BorderLayout.NORTH);
        // --- Action Buttons Panel ---
        JPanel actionButtonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 10, 10));
        activateFeedbackButton = new JButton("Activate Feedback Feature");
        activateFeedbackButton.addActionListener(this::handleActivateFeedbackFeature);
        actionButtonPanel.add(activateFeedbackButton);
        exitButton = new JButton("Exit Application");
        exitButton.addActionListener(e -> {
            displayMessage("Exit", "Thank you for using the Tourist Feedback System!");
            System.exit(0);
        });
        actionButtonPanel.add(exitButton);
        add(actionButtonPanel, BorderLayout.CENTER);
        setVisible(true);
    }
    /**
     * Handles the activation of the feedback feature.
     * This corresponds to Step 1 and Step 2 of the use case.
     * Verifies entry conditions and checks if feedback has already been issued.
     * @param e The action event.
     */
    private void handleActivateFeedbackFeature(ActionEvent e) {
        if (currentTourist == null) {
            displayMessage("Error", "Please select a tourist first.");
            return;
        }
        if (currentSiteSelectedForFeedback == null) {
            displayMessage("Error", "Please select a site for feedback first.");
            return;
        }
        // Step 2: Verify that the visitor has not already issued a feedback for the site and displays a form for entering the feedback for the selected site.
        // Entry conditions: The tourist card is in a particular site.
        // This check now correctly compares the tourist's actual current site (tourist.getCurrentSite())
        // with the site selected by the user for which feedback is intended (currentSiteSelectedForFeedback).
        if (!feedbackManager.canGiveFeedback(currentTourist, currentSiteSelectedForFeedback)) {
            // If canGiveFeedback returns false, it means either:
            // 1. Tourist is not at the *selected* site. (Entry Condition Failed)
            // 2. Tourist has already given feedback for the *selected* site. (Flow condition for FeedbackGiàRilasciato)
            // We need to differentiate these for appropriate messages.
            if (currentTourist.getCurrentSite() == null || !currentTourist.getCurrentSite().equals(currentSiteSelectedForFeedback)) {
                // Tourist is not at the site they are trying to give feedback for
                displayMessage("Action Denied", "The tourist card is not in the selected site (" + currentSiteSelectedForFeedback.getName() + ")." +
                        "\nPlease ensure the tourist is physically at the site before activating feedback.");
            } else if (currentTourist.hasGivenFeedback(currentSiteSelectedForFeedback)) {
                // Tourist is at the site but has already given feedback
                handleFeedbackAlreadyIssued(); // Activates the use case FeedbackGiàRilasciato
            } else {
                // Generic failure, should not happen with current logic but good for robustness
                displayMessage("Action Denied", "Cannot activate feedback feature for unknown reasons. Please check tourist and site status.");
            }
            return;
        }
        // If all checks pass, display form for entering feedback (Step 2 positive path)
        showFeedbackForm();
    }
    /**
     * Displays the feedback entry form to the user.
     * This is part of Step 2 (displaying the form) and enables Step 3 (filling out the form).
     */
    private void showFeedbackForm() {
        feedbackFormFrame = new JFrame("Provide Feedback for " + currentSiteSelectedForFeedback.getName());
        feedbackFormFrame.setSize(400, 300);
        feedbackFormFrame.setLocationRelativeTo(this);
        feedbackFormFrame.setLayout(new BorderLayout(10, 10));
        JPanel formPanel = new JPanel(new GridLayout(3, 1, 5, 5));
        formPanel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        // Vote selection
        JPanel votePanel = new JPanel(new FlowLayout(FlowLayout.LEFT));
        votePanel.add(new JLabel("Vote (1-5):"));
        SpinnerModel voteModel = new SpinnerNumberModel(3, 1, 5, 1); // Default to 3, min 1, max 5, step 1
        voteSpinner = new JSpinner(voteModel);
        votePanel.add(voteSpinner);
        formPanel.add(votePanel);
        // Comment area
        JPanel commentPanel = new JPanel(new BorderLayout());
        commentPanel.add(new JLabel("Comment:"), BorderLayout.NORTH);
        commentTextArea = new JTextArea(5, 20);
        commentTextArea.setLineWrap(true);
        commentTextArea.setWrapStyleWord(true);
        JScrollPane scrollPane = new JScrollPane(commentTextArea);
        commentPanel.add(scrollPane, BorderLayout.CENTER);
        formPanel.add(commentPanel);
        feedbackFormFrame.add(formPanel, BorderLayout.CENTER);
        // Action buttons for the form
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.RIGHT, 10, 10));
        submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.addActionListener(this::handleFeedbackSubmission);
        buttonPanel.add(submitFeedbackButton);
        cancelFeedbackButton = new JButton("Cancel");
        cancelFeedbackButton.addActionListener(e -> {
            // Tourist cancel the operation (Exit condition)
            displayMessage("Operation Cancelled", "Feedback submission cancelled by tourist.");
            feedbackFormFrame.dispose();
        });
        buttonPanel.add(cancelFeedbackButton);
        feedbackFormFrame.add(buttonPanel, BorderLayout.SOUTH);
        feedbackFormFrame.setVisible(true);
    }
    /**
     * Handles the submission of feedback from the form.
     * Corresponds to Step 3 (submit), Step 4 (verify data, handle Errored), Step 5 (confirm),
     * and Step 6 (remember feedback). Also handles connection interruption.
     * @param e The action event.
     */
    private void handleFeedbackSubmission(ActionEvent e) {
        int vote = (int) voteSpinner.getValue();
        String comment = commentTextArea.getText();
        try {
            feedbackManager.submitFeedback(currentTourist, currentSiteSelectedForFeedback, vote, comment);
            // Step 5: Confirming the issue of feedback.
            // Step 6: Remember feedback and inserts the selected site in the list of sites visited.
            feedbackFormFrame.dispose();
            displayMessage("Success", "Feedback for " + currentSiteSelectedForFeedback.getName() + " successfully submitted!");
            System.out.println("Tourist " + currentTourist.getName() + " has now visited " + currentTourist.getVisitedSites().size() + " sites.");
            System.out.println("Feedback for " + currentSiteSelectedForFeedback.getName() + ": " + currentSiteSelectedForFeedback.getFeedbacks());
        } catch (InvalidFeedbackDataException ex) {
            // Where the data is invalid or insufficient, the system activates the use case Errored. (Step 4 negative path)
            handleInvalidData(ex.getMessage());
        } catch (NetworkInterruptionException ex) {
            // Interruption of the connection to the server ETOUR (Exit condition)
            handleConnectionInterruption(ex.getMessage());
        }
    }
    /**
     * Displays a message when feedback has already been issued for the selected site.
     * Corresponds to the `FeedbackGiàRilasciato` use case.
     */
    private void handleFeedbackAlreadyIssued() {
        displayMessage("Feedback Already Issued",
                "You have already submitted feedback for " + currentSiteSelectedForFeedback.getName() + ". " +
                "Only one feedback per site is allowed for this use case.");
    }
    /**
     * Displays an error message when the entered data is invalid or insufficient.
     * Corresponds to the `Errored` use case.
     * @param message The detailed error message to display.
     */
    private void handleInvalidData(String message) {
        displayMessage("Invalid Data - Errored!", message);
    }
    /**
     * Displays an error message when connection to the ETOUR server is interrupted.
     * Corresponds to an exit condition for the use case.
     * @param message The detailed error message to display.
     */
    private void handleConnectionInterruption(String message) {
        displayMessage("Connection Error - ETOUR", message + "\nPlease try again later.");
        if (feedbackFormFrame != null) {
            feedbackFormFrame.dispose(); // Close the form as the operation cannot complete
        }
    }
    /**
     * Helper method to display simple message dialogs.
     * @param title The title of the dialog.
     * @param message The message content.
     */
    private void displayMessage(String title, String message) {
        JOptionPane.showMessageDialog(this, message, title, JOptionPane.INFORMATION_MESSAGE);
    }
    /**
     * Main method to run the application.
     * Creates and shows the GUI on the Event Dispatch Thread (EDT).
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        // Ensure GUI updates are done on the Event Dispatch Thread
        SwingUtilities.invokeLater(() -> {
            new FeedbackAppGUI();
        });
    }
}