'''
Represents the main application window displayed after a successful login.
This serves as a placeholder for a more complex user workspace.
'''
import javax.swing.JFrame;
import javax.swing.JLabel;
import javax.swing.JPanel;
import javax.swing.BoxLayout; // Not strictly needed for this simple layout, but kept if BoxLayout was intended elsewhere.
import javax.swing.BorderFactory;
import java.awt.BorderLayout;
import java.awt.Dimension;
import java.awt.FlowLayout;
public class WorkspaceFrame extends JFrame {
    /**
     * Constructs a new WorkspaceFrame for a successfully logged-in user.
     * This frame displays a welcome message and establishes the post-login application state.
     *
     * @param username The username of the user who has logged in.
     */
    public WorkspaceFrame(String username) {
        // Set the title of the frame, indicating the logged-in user
        setTitle("Welcome to Your Workspace - " + username);
        // Set the default close operation, terminates the application when this frame is closed
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        // Set a preferred size for the workspace window
        setPreferredSize(new Dimension(600, 400)); // Larger than login frame
        // Use a BorderLayout for the main frame content
        setLayout(new BorderLayout());
        // Create a panel for the welcome message
        JPanel welcomePanel = new JPanel();
        welcomePanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel welcomeLabel = new JLabel("<html><h1 style='color: #333366;'>Welcome, " + username + "!</h1>" +
                                        "<p style='text-align: center;'>This is your personalized workspace.</p>" +
                                        "<p style='text-align: center;'>Further application features would be displayed here.</p></html>");
        welcomeLabel.setBorder(BorderFactory.createEmptyBorder(50, 20, 20, 20)); // Add some padding
        welcomePanel.add(welcomeLabel);
        add(welcomePanel, BorderLayout.CENTER);
        // Optional: Add a simple status bar or footer
        JPanel footerPanel = new JPanel();
        footerPanel.setLayout(new FlowLayout(FlowLayout.CENTER));
        JLabel statusLabel = new JLabel("Status: Connected to SMOS (Simulated)");
        footerPanel.add(statusLabel);
        add(footerPanel, BorderLayout.SOUTH);
        // Pack the components to their preferred sizes
        pack();
        // Center the window on the screen
        setLocationRelativeTo(null);
        // Make the frame resizable, as a workspace often needs flexibility
        setResizable(true);
    }
}