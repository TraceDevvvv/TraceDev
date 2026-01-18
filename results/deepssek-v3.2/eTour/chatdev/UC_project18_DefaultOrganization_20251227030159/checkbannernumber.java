/**
 * Main entry point for the Banner Check application.
 * This program simulates checking if a refreshment point can have a new banner added
 * without exceeding the maximum allowed banners. It includes a GUI for user interaction,
 * simulates loading data from a server, and handles notifications, banner addition, and state recovery.
 */
import javax.swing.*;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.HashMap;
import java.util.Random;
public class CheckBannerNumber {
    public static void main(String[] args) {
        // Use the Event Dispatch Thread for GUI creation
        SwingUtilities.invokeLater(() -> {
            MainWindow window = new MainWindow();
            window.setVisible(true);
        });
    }
}
/**
 * The main window of the application.
 * Contains all GUI components and handles the business logic.
 */
class MainWindow extends JFrame {
    private RefreshmentPoint currentPoint;
    private RefreshmentPoint previousState; // Store previous state for recovery
    private JLabel statusLabel;
    private JButton checkButton;
    private JButton confirmButton;
    private JButton recoverButton;
    private JTextArea logArea;
    private ServerSimulator server;
    /**
     * Constructor sets up the GUI and initializes the refreshment point and server.
     */
    public MainWindow() {
        setTitle("Banner Number Check");
        setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);
        setSize(600, 400);
        setLayout(new BorderLayout());
        // Initialize server and load a sample refreshment point
        server = new ServerSimulator();
        // For demonstration, we load a refreshment point with ID 1
        currentPoint = server.loadRefreshmentPoint(1);
        previousState = null;
        // Top panel for status and buttons
        JPanel topPanel = new JPanel(new FlowLayout());
        statusLabel = new JLabel("Status: Ready");
        topPanel.add(statusLabel);
        checkButton = new JButton("Check Banner");
        confirmButton = new JButton("Confirm Notification");
        recoverButton = new JButton("Recover State");
        // Initially disable confirm and recover buttons
        confirmButton.setEnabled(false);
        recoverButton.setEnabled(false);
        topPanel.add(checkButton);
        topPanel.add(confirmButton);
        topPanel.add(recoverButton);
        add(topPanel, BorderLayout.NORTH);
        // Log area for displaying notifications and actions
        logArea = new JTextArea();
        logArea.setEditable(false);
        JScrollPane scrollPane = new JScrollPane(logArea);
        add(scrollPane, BorderLayout.CENTER);
        // Bottom panel for displaying current point info
        JPanel bottomPanel = new JPanel(new FlowLayout());
        JLabel pointInfo = new JLabel("Current Point: " + currentPoint);
        bottomPanel.add(pointInfo);
        add(bottomPanel, BorderLayout.SOUTH);
        // Attach event handlers
        attachHandlers();
    }
    /**
     * Attach action listeners to the buttons.
     */
    private void attachHandlers() {
        // Check Button: Step 1 of use case
        checkButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                checkBanner();
            }
        });
        // Confirm Button: Step 2 of use case (confirm notification reading)
        confirmButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                confirmNotification();
            }
        });
        // Recover Button: Step 3 of use case (recover previous state)
        recoverButton.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                recoverState();
            }
        });
    }
    /**
     * Step 1: Load data and verify if a new banner can be added.
     * If the number of banners is already at or above the maximum,
     * display a notification and disable further actions until confirmation.
     * If allowed, prompts user to add the banner.
     */
    private void checkBanner() {
        logArea.append("--- Checking Banner ---\n");
        logArea.append("Loading data for refreshment point...\n");
        // Save current state before checking for recovery purposes
        previousState = currentPoint;
        // Simulate server call (with possible interruption)
        try {
            // Load the latest data from server (simulated)
            RefreshmentPoint updatedPoint = server.loadRefreshmentPoint(currentPoint.getId());
            currentPoint = updatedPoint; // update local point
            logArea.append("Current banners: " + currentPoint.getCurrentBanners() +
                    ", Max allowed: " + currentPoint.getMaxBanners() + "\n");
            // Check if we can add another banner
            if (currentPoint.getCurrentBanners() < currentPoint.getMaxBanners()) {
                logArea.append("SUCCESS: Can add new banner.\n");
                statusLabel.setText("Status: Can add banner - Proceed?");
                // Prompt user to actually add the banner when allowed
                int choice = JOptionPane.showConfirmDialog(this,
                        "Banner check passed. Do you want to add a banner now?",
                        "Add Banner",
                        JOptionPane.YES_NO_OPTION);
                if (choice == JOptionPane.YES_OPTION) {
                    addBanner();
                } else {
                    logArea.append("Banner addition cancelled by user.\n");
                    statusLabel.setText("Status: Ready");
                }
            } else {
                logArea.append("FAILURE: Maximum banners reached.\n");
                logArea.append("Displaying notification...\n");
                // Enable confirmation button and disable check button
                confirmButton.setEnabled(true);
                checkButton.setEnabled(false);
                statusLabel.setText("Status: Maximum reached - please confirm notification.");
                // Show a popup notification
                JOptionPane.showMessageDialog(this,
                        "Cannot add banner: maximum number of banners already reached.",
                        "Banner Limit Exceeded",
                        JOptionPane.WARNING_MESSAGE);
            }
        } catch (ServerConnectionException ex) {
            // Handle server interruption (Exit condition: Interruption to server)
            logArea.append("ERROR: Connection to server interrupted.\n");
            logArea.append("System will now recover previous state.\n");
            statusLabel.setText("Status: Server error - recovering state.");
            recoverState(); // Automatically recover on interruption
        }
    }
    /**
     * Add a new banner to the current refreshment point.
     * Updates the server and refreshes the local state.
     */
    private void addBanner() {
        try {
            logArea.append("Adding new banner...\n");
            // Update the point with new banner count via server
            currentPoint = server.addBannerToPoint(currentPoint.getId());
            logArea.append("Banner added successfully. New count: " + 
                    currentPoint.getCurrentBanners() + "/" + currentPoint.getMaxBanners() + "\n");
            statusLabel.setText("Status: Banner added successfully");
        } catch (ServerConnectionException ex) {
            logArea.append("ERROR: Could not add banner due to server error.\n");
            statusLabel.setText("Status: Failed to add banner");
            // Optionally recover state on server error during addition
            recoverState();
        }
    }
    /**
     * Step 2: Confirm that the user has read the notification.
     * After confirmation, the recover button is enabled.
     */
    private void confirmNotification() {
        logArea.append("--- Confirming Notification ---\n");
        logArea.append("User has read the notification.\n");
        confirmButton.setEnabled(false);
        recoverButton.setEnabled(true);
        statusLabel.setText("Status: Notification confirmed - ready to recover.");
    }
    /**
     * Step 3: Recover the previous state (before the check).
     * This resets the UI to allow a new check.
     */
    private void recoverState() {
        logArea.append("--- Recovering Previous State ---\n");
        if (previousState != null) {
            // Restore the previous state
            currentPoint = previousState;
            logArea.append("Previous state recovered. Current banners: " +
                    currentPoint.getCurrentBanners() + "\n");
        } else {
            logArea.append("No previous state available. Reloading from server...\n");
            try {
                currentPoint = server.loadRefreshmentPoint(currentPoint.getId());
                logArea.append("State reloaded from server. Current banners: " +
                        currentPoint.getCurrentBanners() + "\n");
            } catch (ServerConnectionException ex) {
                logArea.append("ERROR: Could not recover due to server interruption.\n");
                // If server is down, keep using the current point
            }
        }
        // Reset UI
        checkButton.setEnabled(true);
        confirmButton.setEnabled(false);
        recoverButton.setEnabled(false);
        statusLabel.setText("Status: Ready");
        logArea.append("System control returned to user interaction.\n");
    }
}
/**
 * Represents a refreshment point with a current number of banners and a maximum allowed.
 */
class RefreshmentPoint {
    private int id;
    private String name;
    private int currentBanners;
    private int maxBanners;
    public RefreshmentPoint(int id, String name, int currentBanners, int maxBanners) {
        this.id = id;
        this.name = name;
        this.currentBanners = currentBanners;
        this.maxBanners = maxBanners;
    }
    public int getId() { return id; }
    public String getName() { return name; }
    public int getCurrentBanners() { return currentBanners; }
    public int getMaxBanners() { return maxBanners; }
    @Override
    public String toString() {
        return name + " (ID: " + id + ") - Banners: " + currentBanners + "/" + maxBanners;
    }
}
/**
 * Simulates a server that can be interrupted and holds refreshment point data.
 */
class ServerSimulator {
    private HashMap<Integer, RefreshmentPoint> points;
    private Random random;
    public ServerSimulator() {
        points = new HashMap<>();
        random = new Random();
        // Initialize with some sample data
        points.put(1, new RefreshmentPoint(1, "Main Convention Hall", 3, 5));
        points.put(2, new RefreshmentPoint(2, "Outdoor Cafe", 2, 3));
        points.put(3, new RefreshmentPoint(3, "Lobby Stand", 5, 5));
    }
    /**
     * Load a refreshment point by its ID.
     * Simulates a possible server interruption (throws ServerConnectionException).
     * Returns the stored point directly to ensure data consistency.
     *
     * @param id the refreshment point ID
     * @return the RefreshmentPoint object
     * @throws ServerConnectionException if the server connection is interrupted (randomly simulated)
     */
    public RefreshmentPoint loadRefreshmentPoint(int id) throws ServerConnectionException {
        // Simulate random server interruption (10% chance)
        if (random.nextInt(100) < 10) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted.");
        }
        RefreshmentPoint point = points.get(id);
        if (point == null) {
            // If point not found, return a default point (for demo)
            return new RefreshmentPoint(id, "Unknown Point", 0, 1);
        }
        // Return the stored point directly to reflect true state
        // This ensures consistency with any previous modifications (e.g., banner additions)
        return point;
    }
    /**
     * Add a banner to a refreshment point identified by ID.
     * Increments the current banner count and updates stored data.
     *
     * @param id the refreshment point ID
     * @return the updated RefreshmentPoint object
     * @throws ServerConnectionException if the server connection is interrupted (randomly simulated)
     * @throws IllegalArgumentException if point not found
     */
    public RefreshmentPoint addBannerToPoint(int id) throws ServerConnectionException {
        // Simulate possible server interruption (10% chance)
        if (random.nextInt(100) < 10) {
            throw new ServerConnectionException("Connection to ETOUR server interrupted.");
        }
        RefreshmentPoint point = points.get(id);
        if (point == null) {
            throw new IllegalArgumentException("Refreshment point with ID " + id + " not found.");
        }
        // Check if adding a banner would exceed max (safety check)
        if (point.getCurrentBanners() >= point.getMaxBanners()) {
            throw new IllegalStateException("Cannot add banner: maximum limit already reached.");
        }
        // Create new point with incremented banner count
        RefreshmentPoint updatedPoint = new RefreshmentPoint(
            point.getId(),
            point.getName(),
            point.getCurrentBanners() + 1,
            point.getMaxBanners()
        );
        // Update the stored point
        points.put(id, updatedPoint);
        return updatedPoint;
    }
}
/**
 * Custom exception to represent a server connection interruption.
 */
class ServerConnectionException extends Exception {
    public ServerConnectionException(String message) {
        super(message);
    }
}