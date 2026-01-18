'''
This class implements a simple Java Swing application that simulates a feedback system,
specifically demonstrating the "FEEDBACKGIA'RILASCIATO" use case.
In this scenario, the system checks if feedback has already been provided for a selected site.
If feedback exists (which is the entry condition for this use case), it notifies the user
and cancels the operation, then returns control to the user interface.
'''
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
public class FeedbackApp extends JFrame {
    private JButton provideFeedbackButton;
    private JLabel statusLabel;
    private String selectedSiteId = "Site-A"; // Simulates a site for which feedback is to be given
    /**
     * For this specific "FEEDBACKGIA'RILASCIATO" use case, the entry condition is
     * that feedback has *already* been issued. Thus, this flag is initialized to true
     * to fulfill the use case requirement.
     * In a real system, this would be determined by checking a database or service.
     */
    private boolean hasFeedbackAlreadyBeenIssued = true;
    /**
     * Constructor for the FeedbackApp.
     * Initializes the main window and all GUI components.
     */
    public FeedbackApp() {
        setTitle("Feedback System Simulation");
        setSize(450, 200); // Set window size
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Define close operation
        setLocationRelativeTo(null); // Center the window on the screen
        // Set up the main panel using BorderLayout for organized component placement
        JPanel mainPanel = new JPanel(new BorderLayout());
        mainPanel.setBorder(BorderFactory.createEmptyBorder(20, 20, 20, 20)); // Add padding
        // Label to display instructions or current status to the user
        statusLabel = new JLabel("Click 'Provide Feedback' to simulate the process for " + selectedSiteId + ".", SwingConstants.CENTER);
        statusLabel.setFont(new Font("Arial", Font.PLAIN, 14)); // Set font for better readability
        mainPanel.add(statusLabel, BorderLayout.CENTER); // Place label in the center of the panel
        // Button that triggers the feedback provision attempt
        provideFeedbackButton = new JButton("Provide Feedback for " + selectedSiteId);
        provideFeedbackButton.setFocusPainted(false); // Remove focus border for aesthetics
        // Add an ActionListener to the button to handle clicks
        provideFeedbackButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                // When the button is clicked, execute the logic for providing feedback
                attemptToProvideFeedback();
            }
        });
        // Create a sub-panel for the button to control its placement,
        // typically in the south region for action buttons.
        JPanel buttonPanel = new JPanel();
        buttonPanel.add(provideFeedbackButton);
        mainPanel.add(buttonPanel, BorderLayout.SOUTH);
        add(mainPanel); // Add the main panel to the JFrame
        setVisible(true); // Make the JFrame visible
    }
    /**
     * Simulates the process of a user attempting to provide feedback for a site.
     * This method directly implements the "FEEDBACKGIA'RILASCIATO" use case flow.
     */
    private void attemptToProvideFeedback() {
        // Entry condition check: "E 'already been issued with a feedback for the selected site."
        if (hasFeedbackAlreadyBeenIssued) {
            // Use Case Step 1: "Notification that the user has already issued a feedback for the site
            // and cancel the operation to insert a new feedback."
            JOptionPane.showMessageDialog(this,
                    "You have already provided feedback for " + selectedSiteId + ".\n" +
                            "Cannot submit new feedback at this time. Operation cancelled.",
                    "Feedback Already Submitted",
                    JOptionPane.INFORMATION_MESSAGE);
            // Use Case Step 2: "Confirmation of the reading of the notification."
            // This is implicitly handled by the user clicking the "OK" button on the JOptionPane.
            // The program execution pauses until the user acknowledges the dialog.
            // Use Case Step 3 & Exit Condition: "Recovers the previous state." and
            // "The system returns control to the user interaction."
            // After the JOptionPane closes, control automatically returns to the main application window,
            // effectively recovering the previous state and allowing further user interaction.
            statusLabel.setText("Previous state recovered. Ready for further interaction.");
        } else {
            // This 'else' block would be executed if feedback has NOT been given yet.
            // It is not part of the "FEEDBACKGIA'RILASCIATO" use case's entry conditions,
            // but is shown here for context in a more complete system.
            // For this specific use case, only the 'if' block is relevant.
            JOptionPane.showMessageDialog(this,
                    "Thank you for your feedback for " + selectedSiteId + "!",
                    "Feedback Submitted Successfully",
                    JOptionPane.INFORMATION_MESSAGE);
            // In a real system, 'hasFeedbackAlreadyBeenIssued' would be set to true here.
            // For this simulation, we'll keep it true from the start.
            statusLabel.setText("Feedback successfully provided for " + selectedSiteId + ".");
            hasFeedbackAlreadyBeenIssued = true; // Update state if this path were active
        }
    }
    /**
     * Main method to start the FeedbackApp.
     * Ensures that the GUI creation and updates are performed on the Event Dispatch Thread (EDT)
     * as required by Swing for thread safety.
     *
     * @param args Command line arguments (not used in this application).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            public void run() {
                new FeedbackApp(); // Create and show the application window
            }
        });
    }
}