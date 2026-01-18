/*
 * DOCSTRING: Main entry point for the Absence Management application.
 * This class sets up the JavaFX stage and loads the Administrator Dashboard GUI.
 */
import controller.AdministratorDashboardController;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.BorderPane;
import javafx.stage.Stage;
import java.io.IOException;
public class AbsenceManagerApp extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set. The primary stage
     *                     will be provided by the system.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file for the Administrator Dashboard
        FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AdministratorDashboard.fxml"));
        BorderPane root = fxmlLoader.load(); // The root element of the FXML is a BorderPane
        // Get the controller instance for potential future interaction if needed
        AdministratorDashboardController controller = fxmlLoader.getController();
        // Create a new Scene with the loaded root layout
        Scene scene = new Scene(root);
        // Set the title of the primary stage (window)
        primaryStage.setTitle("ChatDev - Absence Management System (Admin)");
        // Set the scene to the primary stage
        primaryStage.setScene(scene);
        // Prohibit resizing for simplicity of layout
        primaryStage.setResizable(false);
        // Display the stage
        primaryStage.show();
        System.out.println("Absence Management Application started. Ready for administrator actions.");
    }
    /**
     * The main method to launch the JavaFX application.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args); // Launches the JavaFX application
    }
}