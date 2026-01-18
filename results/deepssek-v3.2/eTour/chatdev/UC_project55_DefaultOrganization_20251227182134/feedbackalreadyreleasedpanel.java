// GUI panel that implements the FeedbackAlreadyReleased use case.
// Displays a notification when a user tries to submit feedback for a site
// where they've already submitted feedback. Handles state recovery properly.
package feedbacksystem;
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.text.SimpleDateFormat;
/**
 * GUI panel that implements the FeedbackAlreadyReleased use case.
 * Displays a notification when a user tries to submit feedback for a site
 * where they've already submitted feedback. Handles state recovery properly.
 */
public class FeedbackAlreadyReleasedPanel extends JPanel {
    private FeedbackManager feedbackManager;
    private FeedbackSubmissionHandler submissionHandler;
    private JLabel notificationLabel;
    private JTextArea feedbackDetailsArea;
    private JButton closeButton;
    private JButton viewAllFeedbackButton;
    private JPanel mainContentPanel;
    private JPanel feedbackFormPanel;
    private ApplicationState originalState;
    /**
     * Creates a new panel for the FeedbackAlreadyReleased use case.
     * 
     * @param feedbackManager the feedback manager containing business logic
     */
    public FeedbackAlreadyReleasedPanel(FeedbackManager feedbackManager) {
        this.feedbackManager = feedbackManager;
        this.submissionHandler = new FeedbackSubmissionHandler(feedbackManager);
        initializeUI();
        simulateFeedbackSubmissionAttempt();
    }
    /**
     * Initializes the user interface components.
     */
    private void initializeUI() {
        setLayout(new BorderLayout(10, 10));
        setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20));
        // Create main content panel for normal interaction
        mainContentPanel = createMainContentPanel();
        // Create feedback form panel (simulated)
        feedbackFormPanel = createFeedbackFormPanel();
        // Initially show the feedback form
        add(feedbackFormPanel, BorderLayout.CENTER);
        // Create notification components (initially not visible)
        initializeNotificationComponents();
    }
    /**
     * Creates the main content panel for normal user interaction.
     */
    private JPanel createMainContentPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel welcomeLabel = new JLabel("Welcome to Feedback System", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Arial", Font.BOLD, 24));
        welcomeLabel.setForeground(Color.BLUE);
        JTextArea description = new JTextArea(
            "This is the main user interaction screen.\n\n" +
            "From here, you can:\n" +
            "1. Browse different websites\n" +
            "2. Submit feedback for websites\n" +
            "3. View your previous feedback\n" +
            "4. Manage your account\n\n" +
            "Click 'Submit Feedback' to simulate a feedback submission attempt."
        );
        description.setEditable(false);
        description.setFont(new Font("Arial", Font.PLAIN, 14));
        description.setBackground(getBackground());
        JButton submitFeedbackButton = new JButton("Submit Feedback");
        submitFeedbackButton.setFont(new Font("Arial", Font.BOLD, 16));
        submitFeedbackButton.addActionListener(e -> simulateFeedbackSubmissionAttempt());
        panel.add(welcomeLabel, BorderLayout.NORTH);
        panel.add(new JScrollPane(description), BorderLayout.CENTER);
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(submitFeedbackButton);
        panel.add(buttonPanel, BorderLayout.SOUTH);
        return panel;
    }
    /**
     * Creates a simulated feedback form panel.
     */
    private JPanel createFeedbackFormPanel() {
        JPanel panel = new JPanel(new BorderLayout(10, 10));
        JLabel formTitle = new JLabel("Submit Feedback for: " + feedbackManager.getCurrentSite(), 
                                      SwingConstants.CENTER);
        formTitle.setFont(new Font("Arial", Font.BOLD, 18));
        JPanel formPanel = new JPanel(new GridLayout(4, 2, 10, 10));
        formPanel.setBorder(BorderFactory.createTitledBorder("Feedback Form"));
        // Simulated form fields
        formPanel.add(new JLabel("Rating (1-5):"));
        formPanel.add(new JTextField("4"));
        formPanel.add(new JLabel("Comments:"));
        formPanel.add(new JTextField("This site is very useful."));
        formPanel.add(new JLabel("User ID:"));
        formPanel.add(new JLabel(feedbackManager.getCurrentUserId()));
        formPanel.add(new JLabel("Site:"));
        formPanel.add(new JLabel(feedbackManager.getCurrentSite()));
        JButton submitButton = new JButton("Submit Feedback");
        submitButton.addActionListener(e -> simulateFeedbackSubmissionAttempt());
        panel.add(formTitle, BorderLayout.NORTH);
        panel.add(formPanel, BorderLayout.CENTER);
        panel.add(submitButton, BorderLayout.SOUTH);
        return panel;
    }
    /**
     * Initializes notification components.
     */
    private void initializeNotificationComponents() {
        notificationLabel = new JLabel();
        notificationLabel.setFont(new Font("Arial", Font.BOLD, 16));
        notificationLabel.setHorizontalAlignment(SwingConstants.CENTER);
        feedbackDetailsArea = new JTextArea(8, 40);
        feedbackDetailsArea.setEditable(false);
        feedbackDetailsArea.setFont(new Font("Monospaced", Font.PLAIN, 12));
        feedbackDetailsArea.setBorder(BorderFactory.createCompoundBorder(
            BorderFactory.createTitledBorder("Existing Feedback Details"),
            BorderFactory.createEmptyBorder(5, 5, 5, 5)
        ));
        closeButton = new JButton("Close Notification & Return to Main Screen");
        closeButton.setFont(new Font("Arial", Font.BOLD, 14));
        viewAllFeedbackButton = new JButton("View All Site Feedback");
        viewAllFeedbackButton.setFont(new Font("Arial", Font.PLAIN, 12));
        // Add action listeners
        closeButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                closeNotification();
            }
        });
        viewAllFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                viewAllFeedback();
            }
        });
    }
    /**
     * Simulates a feedback submission attempt with proper validation.
     */
    private void simulateFeedbackSubmissionAttempt() {
        // Save current state before showing notification
        submissionHandler.saveCurrentState("FeedbackForm", "Form data goes here");
        originalState = submissionHandler.getPreviousState();
        // Validate entry conditions
        if (submissionHandler.shouldShowNotification()) {
            showNotificationPanel();
        } else {
            // Entry condition not met - allow feedback submission
            JOptionPane.showMessageDialog(this,
                "No existing feedback found. Proceeding with submission...",
                "Feedback Submission",
                JOptionPane.INFORMATION_MESSAGE);
        }
    }
    /**
     * Shows the notification panel when duplicate feedback is detected.
     */
    private void showNotificationPanel() {
        // Remove current panel
        removeAll();
        // Create notification panel
        JPanel notificationPanel = new JPanel(new BorderLayout(10, 10));
        // Step 1: Notification that user has already issued feedback
        FeedbackData existingFeedback = feedbackManager.getExistingFeedback();
        String notificationText = String.format(
            "<html><div style='text-align: center;'>" +
            "<font color='red' size='+1'>⚠ FEEDBACK ALREADY SUBMITTED ⚠</font><br><br>" +
            "User <b>%s</b> has already submitted feedback for site <b>%s</b>.<br>" +
            "The operation to insert new feedback has been cancelled." +
            "</div></html>",
            feedbackManager.getCurrentUserId(),
            feedbackManager.getCurrentSite()
        );
        notificationLabel.setText(notificationText);
        // Display details of existing feedback
        SimpleDateFormat dateFormat = new SimpleDateFormat("yyyy-MM-dd HH:mm:ss");
        String details = String.format(
            "User ID: %s\n" +
            "Site: %s\n" +
            "Rating: %d/5\n" +
            "Submitted on: %s\n" +
            "Feedback Text:\n%s\n\n" +
            "--- Operation Status ---\n" +
            "New feedback submission: CANCELLED\n" +
            "Reason: Duplicate feedback detected\n" +
            "Action: User notified, operation aborted",
            existingFeedback.getUserId(),
            existingFeedback.getSiteName(),
            existingFeedback.getRating(),
            dateFormat.format(existingFeedback.getSubmissionDate()),
            existingFeedback.getFeedbackText()
        );
        feedbackDetailsArea.setText(details);
        feedbackDetailsArea.setCaretPosition(0);
        // Create button panel
        JPanel buttonPanel = new JPanel(new FlowLayout(FlowLayout.CENTER, 20, 10));
        buttonPanel.add(closeButton);
        buttonPanel.add(viewAllFeedbackButton);
        // Assemble notification panel
        notificationPanel.add(notificationLabel, BorderLayout.NORTH);
        notificationPanel.add(new JScrollPane(feedbackDetailsArea), BorderLayout.CENTER);
        notificationPanel.add(buttonPanel, BorderLayout.SOUTH);
        // Add notification panel
        add(notificationPanel, BorderLayout.CENTER);
        // Step 2: Confirmation of reading (simulated - shown when user views the panel)
        submissionHandler.acknowledgeNotification();
        // Revalidate and repaint
        revalidate();
        repaint();
    }
    /**
     * Handles closing the notification and returning to previous state.
     * This implements Step 3 of the use case: "Recovers the previous state"
     * and Step 5: "The system returns control to the user interaction"
     */
    private void closeNotification() {
        // Step 3: Recover previous state
        ApplicationState restoredState = submissionHandler.restorePreviousState();
        if (restoredState != null) {
            System.out.println("Successfully restored state: " + restoredState);
        }
        // Remove notification panel
        removeAll();
        // Return to main content (recovering previous state)
        add(mainContentPanel, BorderLayout.CENTER);
        // Revalidate and repaint
        revalidate();
        repaint();
        // Show confirmation message
        JOptionPane.showMessageDialog(
            this,
            String.format("✅ State Successfully Restored\n\n" +
                         "Previous application state has been recovered:\n" +
                         "• Screen: %s\n" +
                         "• Site: %s\n" +
                         "• User: %s\n\n" +
                         "Control has been returned to user interaction.",
                         restoredState != null ? restoredState.getCurrentScreen() : "Main Screen",
                         restoredState != null ? restoredState.getCurrentSite() : feedbackManager.getCurrentSite(),
                         restoredState != null ? restoredState.getCurrentUser() : feedbackManager.getCurrentUserId()),
            "State Recovery Complete",
            JOptionPane.INFORMATION_MESSAGE
        );
        System.out.println("System returning control to user interaction (Exit conditions met).");
    }
    /**
     * Displays all feedback in the system for informational purposes.
     */
    private void viewAllFeedback() {
        JOptionPane.showMessageDialog(
            this,
            String.format("Total feedback entries in system: %d\n" +
                         "Current user: %s\n" +
                         "Current site: %s\n" +
                         "User already has feedback: %s\n\n" +
                         "Navigation: Close this notification to return to main screen.",
                         feedbackManager.getTotalFeedbackCount(),
                         feedbackManager.getCurrentUserId(),
                         feedbackManager.getCurrentSite(),
                         feedbackManager.hasUserAlreadySubmittedFeedback() ? "Yes" : "No"),
            "System Feedback Summary",
            JOptionPane.INFORMATION_MESSAGE
        );
    }
}