/**
 * Main class for the News Editing Application.
 * This class extends Application from JavaFX and serves as the entry point for the GUI.
 * It manages the primary stage and the initial scene (Login view).
 */
package com.chatdev.newsapp;
import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.stage.Stage;
import java.io.IOException;
public class Main extends Application {
    /**
     * The primary stage of the application.
     */
    private static Stage primaryStage;
    /**
     * Gets the primary stage of the application.
     * @return The primary Stage.
     */
    public static Stage getPrimaryStage() {
        return primaryStage;
    }
    /**
     * The start method is the main entry point for all JavaFX applications.
     * This method is called after the init method has returned, and after the system is ready for the application to begin running.
     * @param stage The primary stage for this application, onto which the application scene can be set.
     * @throws IOException If the FXML file for the login view cannot be loaded.
     */
    @Override
    public void start(Stage stage) throws IOException {
        primaryStage = stage; // Store the primary stage for global access
        primaryStage.setTitle("News Management System");
        // Load the LoginView FXML to start the application with the login screen
        FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatdev/newsapp/views/LoginView.fxml"));
        Parent root = loader.load();
        Scene scene = new Scene(root);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * The main method, which is the standard entry point for a Java application.
     * It calls the launch method from Application to start the JavaFX runtime.
     * @param args Command line arguments.
     */
    public static void main(String[] args) {
        launch(args);
    }
}