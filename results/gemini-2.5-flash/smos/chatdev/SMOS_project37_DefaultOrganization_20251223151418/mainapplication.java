'''
Main class for the Disciplinary Notes application.
Initializes the JavaFX environment and loads the main note insertion form.
'''
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class MainApplication extends Application {
    /**
     * The main entry point for all JavaFX applications.
     * The start method is called after the init method has returned,
     * and after the system is ready for the application to begin running.
     *
     * @param primaryStage The primary stage for this application, onto which
     *                     the application scene can be set. The primary stage
     *                     will be constructed by the platform prior to calling
     *                     its start method.
     * @throws IOException If the FXML file cannot be loaded.
     */
    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file for the note insertion form.
        // It's assumed that 'note_form.fxml' is in the same directory or accessible in the classpath.
        FXMLLoader loader = new FXMLLoader(getClass().getResource("note_form.fxml"));
        Parent root = loader.load();
        // Get the controller instance created by the FXMLLoader.
        NoteFormController controller = loader.getController();
        // Initialize serv and inject them into the controller.
        // This demonstrates dependency injection for better separation of concerns and testability.
        EmailService emailService = new ConsoleEmailService(); // Using a console-based email service for simulation
        NoteService noteService = new NoteService(emailService);
        controller.setNoteService(noteService);
        // Initialize and inject SmosService for the SMOS server connection interruption postcondition.
        SmosService smosService = new ConsoleSmosService();
        controller.setSmosService(smosService);
        // Set the title of the primary stage (window).
        primaryStage.setTitle("Insert Disciplinary Note");
        // Set the scene with the loaded root node.
        primaryStage.setScene(new Scene(root, 600, 450)); // Set preferred window size
        // Display the stage.
        primaryStage.show();
    }
    /**
     * The main method is ignored in correctly deployed JavaFX application.
     * main() serves only as fallback in case the application is launched
     * as a regular Java application (e.g., by double-clicking a JAR file).
     *
     * @param args The command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}