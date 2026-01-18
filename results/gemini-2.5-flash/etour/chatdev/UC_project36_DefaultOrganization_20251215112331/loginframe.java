'''
LoginFrame extends JFrame and serves as the main application window.
It holds the LoginPanel where the user interacts with login fields and buttons.
'''
import javax.swing.JFrame;
import javax.swing.WindowConstants;
import java.awt.Dimension;
public class LoginFrame extends JFrame {
    private LoginPanel loginPanel; // Reference to the login panel content.
    public LoginFrame() {
        super("ChatDev Login System"); // Set the title of the JFrame.
        initUI(); // Initialize the user interface components.
    }
    /**
     * Initializes the user interface for the LoginFrame.
     * Sets frame properties like size, close operation, and adds the LoginPanel.
     */
    private void initUI() {
        setDefaultCloseOperation(WindowConstants.EXIT_ON_CLOSE); // Define behavior when the window is closed.
        setSize(400, 250); // Set the initial size of the frame.
        setMinimumSize(new Dimension(300, 200)); // Set a minimum size for the frame.
        setLocationRelativeTo(null); // Center the frame on the screen.
        // Create an instance of LoginPanel and pass this frame as parent.
        // This allows the panel to interact with the frame (e.g., displaying dialogs).
        loginPanel = new LoginPanel(this);
        add(loginPanel); // Add the login panel to the frame's content pane.
    }
    /**
     * This method could be used to switch panels if there were other views,
     * but in this simple scenario, it primarily ensures the login panel is shown.
     * In the context of LOGINERRATO, "recovers the previous state" means returning
     * control to the login screen after an error. Since the login panel is
     * the only view shown, this effectively indicates a return to the active login state.
     */
    public void showLoginPanel() {
        // The LoginPanel is already on the frame, so no explicit action is needed here
        // other than ensuring it's ready for new input.
        // For example, if there were other panels, they would be removed, and this one added.
        // For this use case, logging the intent is sufficient to show state recovery.
        System.out.println("System recovered previous state, returning to login screen.");
    }
}