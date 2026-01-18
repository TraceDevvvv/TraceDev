/*
Main application class for the search preferences modification feature.
This class sets up the JavaFX stage, loads the FXML view,
and initializes the controller for managing search preferences.
*/
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class MainApp extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set. The primary stage
     *                     will be constructed by the platform.
     *                     (Note: The user can create other stages if needed).
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        try {
            // Load the FXML file for the preferences view
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/preferences_view.fxml"));
            Parent root = loader.load(); // The standard initialize() method of the controller is called here.
            // Get the controller instance and set up mock authenticated tourist and service
            PreferencesController controller = loader.getController();
            Tourist mockTourist = new Tourist("tourist123"); // Simulate an authenticated tourist
            PreferenceService preferenceService = new PreferenceService();
            // Call the custom method to inject dependencies AFTER FXML's initialize has happened.
            controller.setDependencies(mockTourist, preferenceService);
            // Set the scene and display the stage
            Scene scene = new Scene(root);
            primaryStage.setTitle("Edit Search Preferences");
            primaryStage.setScene(scene);
            primaryStage.show();
        } catch (IOException e) {
            // Log the error or show an alert if FXML loading fails
            System.err.println("Failed to load FXML file: " + e.getMessage());
            e.printStackTrace();
            throw e; // Re-throw to indicate a critical startup failure
        }
    }
    /**
     * The main method, which is the entry point for the Java application.
     * Calls the launch method from Application class to start the JavaFX application.
     *
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}