// Main application window that hosts the FeedbackAlreadyReleased panel.
// Provides the primary GUI container for the application.
package feedbacksystem;
import javax.swing.*;
import java.awt.*;
/**
 * Main application window that hosts the FeedbackAlreadyReleased panel.
 * Provides the primary GUI container for the application.
 */
public class MainApplicationFrame extends JFrame {
    private FeedbackManager feedbackManager;
    private FeedbackAlreadyReleasedPanel mainPanel;
    /**
     * Creates the main application frame.
     */
    public MainApplicationFrame() {
        super("Feedback System - Already Released Use Case");
        initialize();
    }
    /**
     * Initializes the main application frame with all components.
     */
    private void initialize() {
        // Set default sample data for demonstration
        String sampleUserId = "user123";
        String sampleSite = "SampleShoppingSite.com";
        // Initialize feedback manager
        feedbackManager = new FeedbackManager(sampleUserId, sampleSite);
        // Let user choose scenario (for demonstration)
        Object[] options = {"Test: User HAS existing feedback", "Test: User does NOT have existing feedback"};
        int choice = JOptionPane.showOptionDialog(this,
            "Select test scenario:",
            "Feedback System Test Mode",
            JOptionPane.DEFAULT_OPTION,
            JOptionPane.QUESTION_MESSAGE,
            null,
            options,
            options[0]);
        if (choice == 0) {
            feedbackManager.initializeWithExistingFeedback();
        } else if (choice == 1 || choice == -1) {
            feedbackManager.initializeWithoutExistingFeedback();
        }
        // Create main panel with use case implementation
        mainPanel = new FeedbackAlreadyReleasedPanel(feedbackManager);
        // Configure the frame
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setLayout(new BorderLayout());
        // Add header
        JLabel headerLabel = new JLabel("Feedback Management System - Use Case: FeedbackAlreadyReleased", 
                                        SwingConstants.CENTER);
        headerLabel.setFont(new Font("Arial", Font.BOLD, 18));
        headerLabel.setBorder(BorderFactory.createEmptyBorder(10, 10, 10, 10));
        headerLabel.setForeground(Color.BLUE);
        // Add footer with use case summary
        JPanel footerPanel = new JPanel(new BorderLayout());
        JTextArea useCaseSummary = new JTextArea(
            "Use Case Flow Implemented:\n" +
            "1. ✅ Entry conditions validated: User already has feedback for selected site\n" +
            "2. ✅ System notifies user and cancels new feedback operation\n" +
            "3. ✅ User confirms reading notification\n" +
            "4. ✅ System recovers previous state\n" +
            "5. ✅ Control returns to user interaction\n"
        );
        useCaseSummary.setEditable(false);
        useCaseSummary.setBackground(getBackground());
        useCaseSummary.setFont(new Font("Arial", Font.ITALIC, 11));
        footerPanel.add(useCaseSummary, BorderLayout.CENTER);
        footerPanel.setBorder(BorderFactory.createTitledBorder("Use Case Implementation Status"));
        // Assemble the frame
        add(headerLabel, BorderLayout.NORTH);
        add(mainPanel, BorderLayout.CENTER);
        add(footerPanel, BorderLayout.SOUTH);
        // Set frame properties
        setSize(800, 600);
        setLocationRelativeTo(null); // Center on screen
        // Handle window closing
        addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent windowEvent) {
                System.out.println("Application closing. Use case completed.");
            }
        });
    }
}