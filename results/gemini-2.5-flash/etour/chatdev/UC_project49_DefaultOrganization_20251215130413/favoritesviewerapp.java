import javafx.application.Application;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import javafx.concurrent.Task; // Import Task
import java.util.List;
import java.util.concurrent.ExecutorService;
import java.util.concurrent.Executors; // For managed thread pool
/**
 * Main application class for the VISUALIZZASITIPREFERITI use case.
 * This JavaFX application allows a Tourist to view their personal favorites.
 * It simulates authentication and interaction with an ETOUR server.
 */
public class FavoritesViewerApp extends Application {
    private ETOURService etourService;                     // Service for fetching favorites
    private ListView<Favorite> favoritesListView;          // UI element to display the list of favorites
    private Label statusLabel;                             // UI element to display status messages (e.g., loading, error)
    private Button displayFavoritesButton;                 // UI element to trigger displaying favorites
    private ExecutorService executor;                      // For managing background tasks
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set. The primary Stage
     *                     is created by the system.
     */
    @Override
    public void start(Stage primaryStage) {
        etourService = new ETOURService(); // Initialize the ETOUR service
        // Initialize an ExecutorService for background tasks. Using a single thread executor
        // ensures tasks are executed one after another, which is suitable for this case.
        executor = Executors.newSingleThreadExecutor(); 
        primaryStage.setTitle("Personal Favorites Viewer"); // Set the window title
        // Initialize UI components
        favoritesListView = new ListView<>();
        // Placeholder text for the list view when it's empty
        favoritesListView.setPlaceholder(new Label("No favorites loaded. Click 'Display Favorites'.")); 
        statusLabel = new Label("Ready to display favorites.");
        displayFavoritesButton = new Button("Display Favorites");
        // Set action for the button to trigger fetching favorites
        displayFavoritesButton.setOnAction(event -> displayFavorites());
        // Layout setup
        VBox controls = new VBox(10); // Vertical box for controls with 10px spacing
        controls.setPadding(new Insets(10)); // Padding around the controls
        controls.getChildren().addAll(displayFavoritesButton, statusLabel);
        BorderPane root = new BorderPane(); // Main layout container
        root.setTop(controls);             // Place controls at the top
        root.setCenter(favoritesListView); // Place the list view in the center
        // Create the scene and set it on the stage
        Scene scene = new Scene(root, 600, 400); // Set initial scene size
        primaryStage.setScene(scene);
        primaryStage.show();                   // Display the window
    }
    /**
     * Called when the application should stop. This method is called on the JavaFX Application Thread.
     * It's important to shut down the ExecutorService to properly close threads and prevent resource leaks.
     */
    @Override
    public void stop() throws Exception {
        super.stop();
        if (executor != null && !executor.isShutdown()) {
            executor.shutdownNow(); // Attempt to stop all actively executing tasks and shut down the executor.
        }
    }
    /**
     * Simulates successful authentication as per entry conditions.
     * In a real application, this would involve checking user session, token validation, etc.
     * For this simulation, we consider the tourist successfully authenticated.
     *
     * @return true if authentication is considered successful, false otherwise (though currently always true).
     */
    private boolean isAuthenticated() {
        return true;
    }
    /**
     * Handles the logic for displaying the list of personal favorites.
     * This method creates and executes a `javafx.concurrent.Task` to fetch data
     * in a background thread, ensuring the UI remains responsive.
     * UI updates are handled by the Task's callback methods, which run on
     * the JavaFX Application Thread.
     */
    private void displayFavorites() {
        // Entry Condition: Tourist has successfully authenticated
        if (!isAuthenticated()) {
            statusLabel.setText("Error: Tourist not authenticated.");
            return;
        }
        // Clear previous items in the list view before loading new ones
        favoritesListView.setItems(FXCollections.emptyObservableList()); 
        // Define the background task to fetch favorites
        Task<List<Favorite>> fetchFavoritesTask = new Task<List<Favorite>>() {
            @Override
            protected List<Favorite> call() throws Exception {
                // This method runs in a background thread.
                // Update the message property, which can be bound to a UI element.
                updateMessage("Loading favorites..."); 
                // Simulate "uploading" (fetching) the list of bookmarks from the ETOUR service.
                // This call might throw a ConnectionException.
                return etourService.getFavorites(); 
            }
            @Override
            protected void succeeded() {
                super.succeeded();
                // This method runs on the JavaFX Application Thread after call() completes successfully.
                List<Favorite> favorites = getValue(); // Get the result from the call() method.
                ObservableList<Favorite> observableFavorites = FXCollections.observableArrayList(favorites);
                favoritesListView.setItems(observableFavorites); // Update the list view.
                updateMessage("Successfully loaded " + favorites.size() + " favorites."); // Update status message.
            }
            @Override
            protected void failed() {
                super.failed();
                // This method runs on the JavaFX Application Thread if call() throws an exception.
                Throwable exception = getException(); // Get the exception that occurred in the call() method.
                if (exception instanceof ConnectionException) {
                    updateMessage("Error: " + exception.getMessage()); // Display specific connection error.
                } else {
                    updateMessage("An unexpected error occurred: " + exception.getMessage()); // Display general error.
                }
                // Clear the list view on error
                favoritesListView.setItems(FXCollections.emptyObservableList()); 
            }
            @Override
            protected void cancelled() {
                super.cancelled();
                // This method runs on the JavaFX Application Thread if the task is cancelled.
                updateMessage("Loading favorites was cancelled.");
                favoritesListView.setItems(FXCollections.emptyObservableList());
            }
        };
        // Bind UI properties to the task's properties for automatic updates.
        // The status label will display messages from the task.
        statusLabel.textProperty().bind(fetchFavoritesTask.messageProperty());
        // The button will be disabled while the task is running.
        displayFavoritesButton.disableProperty().bind(fetchFavoritesTask.runningProperty());
        // Execute the task in the background using the ExecutorService.
        executor.execute(fetchFavoritesTask);
    }
    /**
     * The main method is ignored in correctly deployed JavaFX application.
     * main() serves only as an entry point for the IDE in case an IDE
     * is not supporting JavaFX Launching directly.
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}