/**
 * The main application window (JFrame) that orchestrates the GUI flow.
 * It switches between `LoginPanel`, `TeachingListPanel`, and `TeachingDetailsPanel`
 * using a `CardLayout`. It also acts as the central listener for events
 * from these panels and interacts with the `TeachingService`.
 */
import javax.swing.*;
import java.awt.*;
import java.util.List;
public class MainFrame extends JFrame
        implements LoginPanel.LoginListener,
        TeachingListPanel.TeachingSelectionListener,
        TeachingDetailsPanel.DeleteTeachingListener {
    private CardLayout cardLayout;
    private JPanel mainPanel; // Panel to hold different views
    private LoginPanel loginPanel;
    private TeachingListPanel teachingListPanel;
    private TeachingDetailsPanel teachingDetailsPanel;
    private TeachingService teachingService; // Backend service for teaching data
    // Card identifiers for CardLayout to easily switch between views
    private static final String LOGIN_VIEW = "LoginView";
    private static final String LIST_VIEW = "ListView";
    private static final String DETAILS_VIEW = "DetailsView";
    /**
     * Constructs the MainFrame, setting up the GUI and initializing components.
     */
    public MainFrame() {
        setTitle("Teaching Management System");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600);
        setLocationRelativeTo(null); // Center the window on the screen
        teachingService = new TeachingService(); // Initialize the teaching service
        cardLayout = new CardLayout();
        mainPanel = new JPanel(cardLayout); // Use CardLayout for switching panels
        // Initialize and add LoginPanel
        loginPanel = new LoginPanel();
        loginPanel.setLoginListener(this); // Register MainFrame as listener for login events
        mainPanel.add(loginPanel, LOGIN_VIEW);
        // Initialize and add TeachingListPanel
        teachingListPanel = new TeachingListPanel();
        teachingListPanel.setTeachingSelectionListener(this); // Register MainFrame as listener for selection events
        mainPanel.add(teachingListPanel, LIST_VIEW);
        // Initialize and add TeachingDetailsPanel
        teachingDetailsPanel = new TeachingDetailsPanel();
        teachingDetailsPanel.setDeleteTeachingListener(this); // Register MainFrame as listener for delete events
        mainPanel.add(teachingDetailsPanel, DETAILS_VIEW);
        add(mainPanel); // Add the main panel (with CardLayout) to the frame
        showLoginPanel(); // Start the application with the login panel
    }
    /**
     * Displays the login panel view.
     */
    private void showLoginPanel() {
        cardLayout.show(mainPanel, LOGIN_VIEW);
        setTitle("Teaching Management System - Login");
    }
    /**
     * Displays the teaching list panel view and refreshes the list of teachings.
     */
    private void showTeachingListPanel() {
        List<Teaching> allTeachings = teachingService.getAllTeachings();
        teachingListPanel.refreshTeachings(allTeachings); // Refresh the list displayed
        teachingDetailsPanel.displayTeaching(null); // Clear details panel when returning to list
        cardLayout.show(mainPanel, LIST_VIEW);
        setTitle("Teaching Management System - Teachings List");
    }
    /**
     * Displays the teaching details panel for a specific teaching ID.
     * @param teachingId The ID of the teaching whose details are to be shown.
     */
    private void showTeachingDetailsPanel(String teachingId) {
        Teaching teaching = teachingService.getTeachingById(teachingId);
        if (teaching != null) {
            teachingDetailsPanel.displayTeaching(teaching); // Set and display teaching details
            cardLayout.show(mainPanel, DETAILS_VIEW);
            setTitle("Teaching Management System - Details for " + teaching.getName());
        } else {
            // Handle case where teaching might not be found (e.g., deleted by another admin in a multi-user system, or invalid ID)
            JOptionPane.showMessageDialog(this, "The selected teaching could not be found.", "Error", JOptionPane.ERROR_MESSAGE);
            showTeachingListPanel(); // Return to list view as the detail is invalid
        }
    }
    // --- Implementation of LoginPanel.LoginListener ---
    /**
     * Called when a successful login occurs.
     * @param username The username of the logged-in user.
     */
    @Override
    public void onLoginSuccess(String username) {
        JOptionPane.showMessageDialog(this, "Welcome, " + username + "!", "Login Success", JOptionPane.INFORMATION_MESSAGE);
        // Precondition 1: The user is logged in to the system as an administrator
        showTeachingListPanel(); // After successful login, show the list of teachings
    }
    /**
     * Called when a login attempt fails.
     */
    @Override
    public void onLoginFailure() {
        // No explicit dialog needed here, as LoginPanel itself displays an error message.
    }
    // --- Implementation of TeachingListPanel.TeachingSelectionListener ---
    /**
     * Called when a teaching is selected from the list.
     * @param teachingId The ID of the selected teaching.
     */
    @Override
    public void onTeachingSelected(String teachingId) {
        // Precondition 2: The user has taken the case of use "displaydeddailsignment" (implicitly by selecting)
        // Precondition 3: The user displays the detailed information of a teaching
        showTeachingDetailsPanel(teachingId); // Show details for the selected teaching
    }
    // --- Implementation of TeachingDetailsPanel.DeleteTeachingListener ---
    /**
     * Called when the delete button is clicked in the TeachingDetailsPanel.
     * @param teachingId The ID of the teaching to be deleted.
     */
    @Override
    public void onDeleteTeaching(String teachingId) {
        // Precondition 4: The user click on the "Delete" button
        // Event 1: Eliminate teaching from the archive
        boolean success = teachingService.deleteTeaching(teachingId);
        if (success) {
            JOptionPane.showMessageDialog(this, "Teaching deleted successfully!", "Deletion Success", JOptionPane.INFORMATION_MESSAGE);
            // Postcondition 1: The user has eliminated a teaching
            // Event 2: Displays the list of updated teachings
            showTeachingListPanel(); // Refresh and display the list after deletion
        } else {
            // Handle scenario where deletion might fail (e.g., teaching not found, database error)
            JOptionPane.showMessageDialog(this, "Failed to delete teaching. It might no longer exist.", "Deletion Error", JOptionPane.ERROR_MESSAGE);
            showTeachingListPanel(); // Still show list, as the current details might be stale or incorrect
        }
    }
}