/**
 * Controller class for Convention History.
 * This class handles the interaction between the model and the view.
 */
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.util.List;
public class ConventionHistoryController {
    private ConventionHistoryModel model;
    private ConventionHistoryView view;
    // Constructor
    public ConventionHistoryController(ConventionHistoryModel model, ConventionHistoryView view) {
        this.model = model;
        this.view = view;
    }
    /**
     * Handles loading conventions when the load button is clicked.
     */
    public void handleLoadConventions() {
        // Get the point of rest from the view
        String pointOfRest = view.getPointOfRest();
        // Validate input
        if (pointOfRest == null || pointOfRest.isEmpty()) {
            view.showError("Please enter a point of rest (restaurant) name.");
            return;
        }
        // Check server connection
        if (!model.isServerConnected()) {
            view.showError("Cannot load conventions. Server is disconnected.");
            return;
        }
        // Set the selected point of rest in the model
        model.setSelectedPointOfRest(pointOfRest);
        // Load conventions from the model
        model.loadConventionHistory();
        // Get the loaded conventions
        List<ConventionHistoryModel.Convention> conventions = model.getConventions();
        // Update the view with the conventions
        view.updateConventionTable(conventions);
        // Show success message
        view.showInfo("Conventions loaded successfully for: " + pointOfRest);
    }
    /**
     * Handles connecting to the server.
     */
    public void handleConnectToServer() {
        model.connectToServer();
        view.updateStatusLabel(true);
        view.showInfo("Connected to ETOUR server.");
    }
    /**
     * Handles disconnecting from the server.
     */
    public void handleDisconnectFromServer() {
        model.disconnectFromServer();
        view.updateStatusLabel(false);
        view.showInfo("Disconnected from ETOUR server.");
    }
}