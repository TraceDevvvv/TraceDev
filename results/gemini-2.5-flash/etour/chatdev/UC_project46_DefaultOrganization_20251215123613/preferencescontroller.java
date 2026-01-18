/*
Controller class for the preferences_view.fxml.
This class handles user interactions for modifying search preferences.
It loads existing preferences, validates user input, allows editing,
requests confirmation, saves changes via PreferenceService, and
notifies the user of the outcome or any errors.
*/
import javafx.fxml.FXML;
import javafx.fxml.Initializable; // Import Initializable
import javafx.scene.control.Alert;
import javafx.scene.control.ButtonType;
import javafx.scene.control.CheckBox;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.stage.Stage;
import java.net.URL; // For Initializable
import java.util.HashSet;
import java.util.Optional;
import java.util.ResourceBundle; // For Initializable
import java.util.Set;
/**
 * Controller class for the preferences_view.fxml.
 * This class handles user interactions for modifying search preferences.
 * It loads existing preferences, validates user input, allows editing,
 * requests confirmation, saves changes via PreferenceService, and
 * notifies the user of the outcome or any errors.
 *
 * The error handling for network interruptions during the *initial loading* of search preferences
 * (in `loadAndDisplayPreferences`) is enhanced to provide an explicit option to retry loading
 * or proceed with an empty form, improving user experience for transient connection issues.
 */
public class PreferencesController implements Initializable { // Implement Initializable
    // FXML injected UI components
    @FXML private TextField destinationField;
    @FXML private TextField minPriceField;
    @FXML private TextField maxPriceField;
    @FXML private CheckBox categoryCulture;
    @FXML private CheckBox categoryAdventure;
    @FXML private CheckBox categoryFood;
    @FXML private CheckBox categoryRelaxation;
    @FXML private CheckBox categoryHistory;
    @FXML private Label statusLabel; // For displaying messages (errors, success)
    private Tourist currentTourist;      // The authenticated tourist
    private PreferenceService preferenceService; // Service for preference operations
    private SearchPreferences initialPreferences; // To track original preferences for comparison
    /**
     * The standard JavaFX initialize method.
     * This method is called automatically by FXMLLoader after all @FXML annotated members are injected.
     * Use this for UI component setup that doesn't depend on external data (Tourist, PreferenceService).
     */
    @Override
    public void initialize(URL location, ResourceBundle resources) {
        // Any FXML-specific initializations can go here.
        // For example, setting up listeners or default values for UI elements
        // that do not require Tourist or PreferenceService to be available yet.
        // In this case, there are no such initializations distinct from data loading.
    }
    /**
     * Sets the authenticated tourist and the preference service for the controller.
     * This method effectively replaces the custom `initialize` method previously used
     * to pass dependencies and should be called by the application after FXML loading.
     * It then loads the tourist's current preferences and displays them in the form.
     *
     * @param tourist The Tourist whose preferences are being edited.
     * @param service The service responsible for managing preferences.
     */
    public void setDependencies(Tourist tourist, PreferenceService service) {
        this.currentTourist = tourist;
        this.preferenceService = service;
        loadAndDisplayPreferences();
    }
    /**
     * Loads the current search preferences for the tourist and populates the form fields.
     * Handles potential connection issues during loading, offering retry options.
     */
    private void loadAndDisplayPreferences() {
        if (currentTourist == null || preferenceService == null) {
            statusLabel.setText("Error: System not fully initialized. Dependencies are missing.");
            return;
        }
        boolean loadedSuccessfully = false;
        while (!loadedSuccessfully) { // Loop to allow retrying loading on connection failure
            try {
                // Attempt to load existing preferences
                Optional<SearchPreferences> loadedPrefs = preferenceService.loadPreferences(currentTourist);
                if (loadedPrefs.isPresent()) {
                    // If preferences exist, store them as initial and display
                    this.initialPreferences = loadedPrefs.get();
                    displayPreferences(initialPreferences);
                    statusLabel.setText("Current preferences loaded.");
                } else {
                    // If no preferences found, create a new empty one and display it
                    this.initialPreferences = new SearchPreferences();
                    displayPreferences(initialPreferences);
                    statusLabel.setText("No existing preferences found. Please set new ones.");
                }
                loadedSuccessfully = true; // Preferences loaded successfully, exit loop
            } catch (PreferenceService.ConnectionInterruptionException e) {
                // Handle simulated connection interruption during load
                Alert decisionAlert = new Alert(Alert.AlertType.CONFIRMATION);
                decisionAlert.setTitle("Loading Failed - Connection Error");
                decisionAlert.setHeaderText("Failed to load preferences due to a connection error.");
                decisionAlert.setContentText("Would you like to retry loading or proceed with empty preferences?\nError: " + e.getMessage());
                ButtonType retryButton = new ButtonType("Retry Loading");
                ButtonType continueEmptyButton = new ButtonType("Continue (empty form)");
                decisionAlert.getButtonTypes().setAll(retryButton, continueEmptyButton);
                Optional<ButtonType> decisionResult = decisionAlert.showAndWait();
                if (decisionResult.isPresent() && decisionResult.get() == retryButton) {
                    // User chose to retry, the loop will run again
                    statusLabel.setText("Attempting to retry loading preferences...");
                } else {
                    // User chose to continue with empty preferences or cancelled the dialog (closed it, which maps to CANCEL)
                    statusLabel.setText("Could not load preferences. Showing empty form.");
                    this.initialPreferences = new SearchPreferences(); // Reset to empty on error
                    displayPreferences(initialPreferences); // Display empty/default values
                    loadedSuccessfully = true; // Exit loop as user decided not to retry
                }
            }
        }
    }
    /**
     * Populates the form fields with the given SearchPreferences object.
     *
     * @param preferences The SearchPreferences object to display.
     */
    private void displayPreferences(SearchPreferences preferences) {
        destinationField.setText(preferences.getDestination());
        minPriceField.setText(String.valueOf(preferences.getMinPrice()));
        maxPriceField.setText(String.valueOf(preferences.getMaxPrice()));
        // Set category checkboxes based on the preferences
        Set<String> categories = preferences.getCategories();
        categoryCulture.setSelected(categories.contains("Culture"));
        categoryAdventure.setSelected(categories.contains("Adventure"));
        categoryFood.setSelected(categories.contains("Food"));
        categoryRelaxation.setSelected(categories.contains("Relaxation"));
        categoryHistory.setSelected(categories.contains("History"));
    }
    /**
     * Event handler for the "Save Preferences" button.
     * 1. Collects input from the form.
     * 2. Validates the input.
     * 3. Creates a new SearchPreferences object.
     * 4. Asks for user confirmation if changes were made.
     * 5. Calls PreferenceService to save the preferences.
     * 6. Notifies user of success or error, and provides options for error recovery.
     */
    @FXML
    private void handleSave() {
        statusLabel.setText(""); // Clear previous status messages
        // 1. Collect input
        String destination = destinationField.getText().trim();
        double minPrice;
        double maxPrice;
        Set<String> selectedCategories = new HashSet<>();
        // 2. Validate input and parse pr
        try {
            if (destination.isEmpty()) {
                statusLabel.setText("Error: Destination cannot be empty.");
                return;
            }
            minPrice = Double.parseDouble(minPriceField.getText().trim());
            maxPrice = Double.parseDouble(maxPriceField.getText().trim());
            if (minPrice < 0 || maxPrice < 0) {
                statusLabel.setText("Error: Pr cannot be negative.");
                return;
            }
            if (minPrice > maxPrice) {
                statusLabel.setText("Error: Minimum price cannot be greater than maximum price.");
                return;
            }
        } catch (NumberFormatException e) {
            statusLabel.setText("Error: Invalid price format. Please enter numerical values.");
            return;
        }
        // Collect selected categories
        if (categoryCulture.isSelected()) selectedCategories.add("Culture");
        if (categoryAdventure.isSelected()) selectedCategories.add("Adventure");
        if (categoryFood.isSelected()) selectedCategories.add("Food");
        if (categoryRelaxation.isSelected()) selectedCategories.add("Relaxation");
        if (categoryHistory.isSelected()) selectedCategories.add("History");
        // 3. Create a new preferences object from current form values
        SearchPreferences newPreferences = new SearchPreferences(destination, minPrice, maxPrice, selectedCategories);
        // Check if any preferences have actually changed
        if (initialPreferences != null && newPreferences.equals(initialPreferences)) {
            statusLabel.setText("No changes detected. Preferences are already up to date.");
            return;
        }
        // 4. Ask for confirmation
        Optional<ButtonType> result = showConfirmationAlert("Confirm Changes", "Are you sure you want to save these new preferences?");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // 5. Confirmation received, proceed to save
            try {
                preferenceService.savePreferences(currentTourist, newPreferences);
                this.initialPreferences = newPreferences; // Update initial preferences to the new saved state
                statusLabel.setText("Search preferences modified successfully!");
                showAlert(Alert.AlertType.INFORMATION, "Success", "Preferences saved successfully.");
            } catch (PreferenceService.ConnectionInterruptionException e) {
                // If connection interrupted during save
                showErrorAlert("Connection Error", "Failed to save preferences: " + e.getMessage());
                statusLabel.setText("Saving failed due to connection error.");
                // PROPOSED IMPROVEMENT: Offer an explicit choice to the user
                Alert decisionAlert = new Alert(Alert.AlertType.CONFIRMATION);
                decisionAlert.setTitle("Save Failed - Options");
                decisionAlert.setHeaderText("Connection Interruption during Save");
                decisionAlert.setContentText("Your preferences could not be saved due to a connection error. What would you like to do?");
                ButtonType retryButton = new ButtonType("Retry unsaved changes");
                ButtonType revertButton = new ButtonType("Revert to last saved preferences");
                ButtonType continueEditingButton = new ButtonType("Continue editing current changes"); // Keep current changes visible but don't force retry/revert
                decisionAlert.getButtonTypes().setAll(retryButton, revertButton, continueEditingButton);
                Optional<ButtonType> decisionResult = decisionAlert.showAndWait();
                if (decisionResult.isPresent()) {
                    if (decisionResult.get() == retryButton) {
                        // User wants to retry with current form values
                        // No action needed here, user can click Save again.
                        statusLabel.setText("Attempting to retry save. Please click 'Save Preferences' again.");
                    } else if (decisionResult.get() == revertButton) {
                        // Revert the form fields to the last successfully saved preferences
                        displayPreferences(initialPreferences);
                        statusLabel.setText("Form reverted to last successfully saved preferences.");
                    } else if (decisionResult.get() == continueEditingButton) {
                        // User wants to keep editing, no action needed, form remains as is.
                        statusLabel.setText("You can continue editing the current changes or attempt to save again.");
                    }
                }
            } catch (IllegalArgumentException e) {
                // If PreferenceService throws an unexpected argument error
                showErrorAlert("Save Error", "An internal error occurred: " + e.getMessage());
                statusLabel.setText("An internal error occurred.");
            }
        } else {
            // 6. User cancelled the save operation
            statusLabel.setText("Save operation cancelled by user.");
        }
    }
    /**
     * Event handler for the "Cancel" button.
     * Displays a confirmation dialog before cancelling and closing the window
     * or reverting changes. For this simple example, it just closes the window.
     * In a more complex scenario, it might revert to the initial state without closing.
     */
    @FXML
    private void handleCancel() {
        Optional<ButtonType> result = showConfirmationAlert("Cancel Operation", "Are you sure you want to cancel? Any unsaved changes will be lost.");
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // The Tourist cancels the operation.
            // Close the current window/stage.
            Stage stage = (Stage) destinationField.getScene().getWindow();
            stage.close();
        } else {
            statusLabel.setText("Cancellation operation aborted.");
        }
    }
    /**
     * Displays a generic alert dialog.
     *
     * @param type The type of alert (e.g., Alert.AlertType.INFORMATION, Alert.AlertType.ERROR).
     * @param title The title of the alert window.
     * @param message The main message content of the alert.
     */
    private void showAlert(Alert.AlertType type, String title, String message) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.showAndWait();
    }
    /**
     * Displays an error alert specifically.
     *
     * @param title The title of the error alert.
     * @param message The error message content.
     */
    private void showErrorAlert(String title, String message) {
        showAlert(Alert.AlertType.ERROR, title, message);
    }
    /**
     * Displays a confirmation alert dialog with OK and CANCEL buttons.
     *
     * @param title The title of the confirmation dialog.
     * @param message The message content of the confirmation dialog.
     * @return An Optional containing the ButtonType that the user clicked (e.g., ButtonType.OK, ButtonType.CANCEL).
     */
    private Optional<ButtonType> showConfirmationAlert(String title, String message) {
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle(title);
        alert.setHeaderText(null);
        alert.setContentText(message);
        return alert.showAndWait();
    }
}