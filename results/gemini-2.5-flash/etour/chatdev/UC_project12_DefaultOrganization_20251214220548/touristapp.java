/**
 * The main application class for the VISUALIZZASCHEDATURISTA use case.
 * It orchestrates the entire application flow, including operator login,
 * displaying a tourist list, and showing selected tourist details.
 * It uses a CardLayout to switch between different views (panels).
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.WindowAdapter;
import java.awt.event.WindowEvent;
import java.util.concurrent.ExecutionException; // Required for SwingWorker.get() exception handling
public class TouristApp extends JFrame {
    // Constants for CardLayout panel names
    private static final String LOGIN_CARD = "LoginCard";
    private static final String TOURIST_LIST_CARD = "TouristListCard";
    private static final String TOURIST_DETAIL_CARD = "TouristDetailCard";
    private ETOURService etourService; // Service for fetching tourist data
    private AgencyOperatorSession operatorSession; // Manages login state
    private JPanel cardPanel; // Panel using CardLayout to switch views
    private CardLayout cardLayout;
    private JMenuBar menuBar;
    private JMenu optionsMenu;
    private JMenuItem logoutMenuItem;
    private JCheckBoxMenuItem simulateErrorMenuItem;
    private TouristListPanel touristListPanel;
    private TouristDetailPanel touristDetailPanel;
    /**
     * Main constructor for the TouristApp.
     * Initializes serv, session, GUI components, and sets up the window.
     */
    public TouristApp() {
        super("ETOUR Agency Tourist Viewer"); // Set window title
        // Initialize core components
        etourService = new ETOURService();
        operatorSession = new AgencyOperatorSession();
        // Set up the main window (JFrame)
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(800, 600); // Set initial window size
        setLocationRelativeTo(null); // Center window on screen
        initComponents(); // Initialize GUI components
        createMenuBar(); // Create application menu bar
        // Handle window closing to ensure proper logout if desired
        addWindowListener(new WindowAdapter() {
            @Override
            public void windowClosing(WindowEvent e) {
                if (operatorSession.isLoggedIn()) {
                    int confirm = JOptionPane.showConfirmDialog(TouristApp.this,
                            "You are logged in. Do you want to log out before exiting?", "Exit Confirmation",
                            JOptionPane.YES_NO_CANCEL_OPTION); // Allow cancel to keep app open
                    if (confirm == JOptionPane.YES_OPTION) {
                        operatorSession.logout();
                    } else if (confirm == JOptionPane.CANCEL_OPTION) {
                        // Prevent default close operation if user cancels
                        setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
                        return; // Do not call dispose() yet
                    }
                }
                // If not logged in, or logged out gracefully, or chose NO to logout, dispose and exit
                setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE); // Reset to default close
                dispose(); // Close the frame
            }
        });
    }
    /**
     * Initializes and configures the main GUI components, including the CardLayout.
     */
    private void initComponents() {
        cardLayout = new CardLayout();
        cardPanel = new JPanel(cardLayout);
        add(cardPanel, BorderLayout.CENTER);
        // Initialize panels
        touristListPanel = new TouristListPanel(etourService, this);
        touristDetailPanel = new TouristDetailPanel(this);
        // Add panels to the card layout
        // For the login "card", we actually use a placeholder, as the login is handled by a separate dialog.
        JPanel emptyPanel = new JPanel(new GridBagLayout());
        JLabel welcomeLabel = new JLabel("Please log in to proceed...", SwingConstants.CENTER);
        welcomeLabel.setFont(new Font("Serif", Font.ITALIC, 18));
        emptyPanel.add(welcomeLabel);
        cardPanel.add(emptyPanel, LOGIN_CARD); // This is just a placeholder, login dialog will pop up
        cardPanel.add(touristListPanel, TOURIST_LIST_CARD);
        cardPanel.add(touristDetailPanel, TOURIST_DETAIL_CARD);
        showLogin(); // Start with the login process
    }
    /**
     * Creates and sets up the application's menu bar.
     * Includes login/logout options and a checkbox to simulate connection errors.
     */
    private void createMenuBar() {
        menuBar = new JMenuBar();
        optionsMenu = new JMenu("Options");
        logoutMenuItem = new JMenuItem("Logout");
        logoutMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLogout();
            }
        });
        logoutMenuItem.setEnabled(false); // Initially disabled until logged in
        optionsMenu.add(logoutMenuItem);
        optionsMenu.addSeparator();
        simulateErrorMenuItem = new JCheckBoxMenuItem("Simulate ETOUR Connection Error");
        simulateErrorMenuItem.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                etourService.setSimulateConnectionError(simulateErrorMenuItem.isSelected());
                String status = simulateErrorMenuItem.isSelected() ? "ON" : "OFF";
                JOptionPane.showMessageDialog(TouristApp.this,
                        "ETOUR connection error simulation is now " + status + ".\n" +
                        "Refresh the list or view details to see the effect.",
                        "Simulation Status", JOptionPane.INFORMATION_MESSAGE);
            }
        });
        optionsMenu.add(simulateErrorMenuItem);
        menuBar.add(optionsMenu);
        setJMenuBar(menuBar);
    }
    /**
     * Displays the login dialog and handles its outcome.
     * If login is successful, it transitions to the tourist list view.
     */
    private void showLogin() {
        cardLayout.show(cardPanel, LOGIN_CARD); // Show the placeholder card before login dialog
        LoginDialog loginDialog = new LoginDialog(this, operatorSession);
        loginDialog.setVisible(true); // This call blocks until dialog is closed
        if (loginDialog.isLoginSuccessful()) {
            logoutMenuItem.setEnabled(true); // Enable logout after successful login
            showTouristList(); // Proceed to the tourist list
        } else {
            // If login failed or cancelled, exit the application
            JOptionPane.showMessageDialog(this, "Login failed or cancelled. Exiting application.", "Login", JOptionPane.INFORMATION_MESSAGE);
            System.exit(0);
        }
    }
    /**
     * Transitions the view to the TouristListPanel and loads the tourist data.
     * This corresponds to the system displaying the tourist list after 'RicercaTurista'.
     * This method ensures the operator is logged in and then delegates to TouristListPanel to load data
     * in a non-blocking manner.
     */
    public void showTouristList() {
        if (!operatorSession.isLoggedIn()) {
            showLogin(); // Ensure operator is logged in before showing internal content
            return;
        }
        cardLayout.show(cardPanel, TOURIST_LIST_CARD);
        touristListPanel.loadTouristList(); // Load or refresh the list using SwingWorker
    }
    /**
     * Transitions the view to the TouristDetailPanel and displays the details of the selected tourist.
     * This corresponds to the 'VISUALIZZASCHEDATURISTA' use case.
     * This method ensures the operator is logged in and then fetches data using SwingWorker
     * to prevent UI freezing.
     * @param touristId The ID of the tourist whose details are to be displayed.
     */
    public void showTouristDetails(String touristId) {
        if (!operatorSession.isLoggedIn()) {
            showLogin(); // Ensure operator is logged in
            return;
        }
        // Immediately switch to the detail card and show a loading message
        touristDetailPanel.clearDetails(); // Clear previous details
        touristDetailPanel.displayTouristDetails(new Tourist("", "Loading details for " + touristId + "...", "", "", "", ""));
        cardLayout.show(cardPanel, TOURIST_DETAIL_CARD);
        // Use SwingWorker to perform the potentially long-running operation in a background thread
        new SwingWorker<Tourist, Void>() {
            private ETOURConnectionException connectionException = null; // Store potential connection errors
            /**
             * Fetches the tourist details from the service in a background thread.
             * @return The Tourist object, or null if not found or a connection error occurred.
             */
            @Override
            protected Tourist doInBackground() {
                try {
                    return etourService.getTouristDetails(touristId);
                } catch (ETOURConnectionException e) {
                    connectionException = e; // Store the exception to handle on the EDT
                    return null; // Return null to indicate failure or no result
                }
            }
            /**
             * This method is executed on the Event Dispatch Thread after doInBackground() completes.
             * It updates the GUI with the results or error messages.
             */
            @Override
            protected void done() {
                try {
                    // If a connection error was caught in doInBackground, re-throw it here
                    if (connectionException != null) {
                        throw connectionException;
                    }
                    Tourist tourist = get(); // Retrieve the result from doInBackground()
                    if (tourist != null) {
                        touristDetailPanel.displayTouristDetails(tourist);
                    } else {
                        // This case means ID was not found, as connection error is handled above
                        JOptionPane.showMessageDialog(TouristApp.this,
                                "Tourist with ID " + touristId + " not found or data is missing.",
                                "Tourist Not Found", JOptionPane.WARNING_MESSAGE);
                        showTouristList(); // Go back to list if tourist not found
                    }
                } catch (ETOURConnectionException e) {
                    // Handle and display connection errors
                    JOptionPane.showMessageDialog(TouristApp.this,
                            "<html><b>Error:</b> " + e.getMessage() + "<br>Could not retrieve tourist details.</html>",
                            "Connection Error", JOptionPane.ERROR_MESSAGE);
                    showTouristList(); // Go back to list on connection error
                } catch (InterruptedException | ExecutionException e) {
                    // Handle other exceptions that might occur during SwingWorker execution
                    JOptionPane.showMessageDialog(TouristApp.this,
                            "<html>An unexpected error occurred while fetching tourist details: " + e.getMessage() + "</html>",
                            "Error", JOptionPane.ERROR_MESSAGE);
                    showTouristList(); // Go back to list on internal error
                }
            }
        }.execute(); // Start the SwingWorker
    }
    /**
     * Handles the logout process, clearing session and returning to the login state.
     */
    private void handleLogout() {
        int confirm = JOptionPane.showConfirmDialog(this, "Are you sure you want to log out?", "Confirm Logout", JOptionPane.YES_NO_OPTION);
        if (confirm == JOptionPane.YES_OPTION) {
            operatorSession.logout();
            logoutMenuItem.setEnabled(false); // Disable logout button
            simulateErrorMenuItem.setSelected(false); // Reset error simulation on logout
            etourService.setSimulateConnectionError(false); // Ensure error simulation is off upon logout
            showLogin(); // Go back to login screen/dialog to force re-login
        }
    }
    /**
     * Main method to start the application.
     * Ensures GUI updates are done on the Event Dispatch Thread.
     * @param args Command line arguments (not used).
     */
    public static void main(String[] args) {
        SwingUtilities.invokeLater(new Runnable() {
            @Override
            public void run() {
                new TouristApp().setVisible(true); // Create and show the main application window
            }
        });
    }
}