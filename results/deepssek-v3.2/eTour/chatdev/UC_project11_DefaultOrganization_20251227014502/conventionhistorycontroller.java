/**
 * Controller class for Convention History.
 * This class handles the interaction between the model and the view.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
import javax.swing.JOptionPane;
public class ConventionHistoryController {
    private ConventionHistoryModel model;
    private ConventionHistoryView view;
    // Constructor
    public ConventionHistoryController(ConventionHistoryModel model, ConventionHistoryView view) {
        this.model = model;
        this.view = view;
        // Set up action listeners
        setupListeners();
        // Initialize view with current state
        view.updateStatusLabel(model.isServerConnected());
        view.updateDbInfoLabel("H2 In-Memory Database");
    }
    /**
     * Set up all action listeners for view components.
     */
    private void setupListeners() {
        // Load conventions button listener
        view.addLoadButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleLoadConventions();
            }
        });
        // Connect to server button listener
        view.addConnectButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleConnectToServer();
            }
        });
        // Disconnect from server button listener
        view.addDisconnectButtonListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent e) {
                handleDisconnectFromServer();
            }
        });
    }
    /**
     * Handles loading conventions when the load button is clicked.
     */
    public void handleLoadConventions() {
        // Get the point of rest from the view
        String pointOfRest = view.getPointOfRest();
        // Validate input
        if (pointOfRest == null || pointOfRest.isEmpty()) {
            view.showError("Please enter a restaurant name (point of rest).");
            return;
        }
        // Check server connection
        if (!model.isServerConnected()) {
            int option = JOptionPane.showConfirmDialog(view, 
                "Not connected to database. Would you like to connect now?", 
                "Connection Required", 
                JOptionPane.YES_NO_OPTION);
            if (option == JOptionPane.YES_OPTION) {
                handleConnectToServer();
                if (!model.isServerConnected()) {
                    return; // Connection failed
                }
            } else {
                return; // User chose not to connect
            }
        }
        // Set the selected point of rest in the model
        model.setSelectedPointOfRest(pointOfRest);
        // Clear previous results
        view.clearTable();
        // Load conventions from the model
        model.loadConventionHistory();
        // Get the loaded conventions
        List<ConventionHistoryModel.Convention> conventions = model.getConventions();
        // Update the view with the conventions
        view.updateConventionTable(conventions);
        // Show appropriate message based on results
        if (!conventions.isEmpty()) {
            view.showInfo("Successfully loaded " + conventions.size() + " conventions for: " + pointOfRest);
        } else {
            view.showInfo("No conventions found for: " + pointOfRest + ". Try 'The Gourmet Hub' or 'Vineyard Restaurant' for sample data.");
        }
    }
    /**
     * Handles connecting to the server.
     */
    public void handleConnectToServer() {
        // Show connection attempt message
        view.updateStatusLabel(false);
        view.showInfo("Attempting to connect to database...");
        // Connect to server through model
        model.connectToServer();
        // Update view based on connection result
        boolean connected = model.isServerConnected();
        view.updateStatusLabel(connected);
        if (connected) {
            view.updateDbInfoLabel("H2 In-Memory Database");
            view.showInfo("Connected to database successfully. Ready to load conventions.");
        } else {
            view.showError("Failed to connect to database. Please check your Java installation and try again.");
        }
    }
    /**
     * Handles disconnecting from the server.
     */
    public void handleDisconnectFromServer() {
        if (!model.isServerConnected()) {
            view.showInfo("Already disconnected from database.");
            return;
        }
        int option = JOptionPane.showConfirmDialog(view, 
            "Are you sure you want to disconnect from the database?",
            "Confirm Disconnect",
            JOptionPane.YES_NO_OPTION);
        if (option == JOptionPane.YES_OPTION) {
            model.disconnectFromServer();
            view.updateStatusLabel(false);
            view.clearTable();
            view.showInfo("Disconnected from database.");
        }
    }
}