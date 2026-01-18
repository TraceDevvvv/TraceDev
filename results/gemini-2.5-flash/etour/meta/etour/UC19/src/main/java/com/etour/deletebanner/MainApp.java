package com.etour.deletebanner;

import javafx.application.Application;
import javafx.fxml.FXMLLoader;
import javafx.scene.Scene;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;

import java.io.IOException;

/**
 * Main application class for the Delete Banner UI.
 * This class extends Application and is the entry point for the JavaFX application.
 */
public class MainApp extends Application {

    @Override
    public void start(Stage primaryStage) throws IOException {
        // Load the FXML file for the main UI
        FXMLLoader loader = new FXMLLoader();
        loader.setLocation(getClass().getResource("/delete_banner_view.fxml"));
        VBox rootLayout = loader.load();

        // Create a scene with the loaded layout
        Scene scene = new Scene(rootLayout);

        // Set the stage title and scene
        primaryStage.setTitle("Delete Banner");
        primaryStage.setScene(scene);
        primaryStage.show();
    }

    /**
     * The main method is ignored in JavaFX applications
     * if the JAR file is launched with the JavaFX Launcher.
     * However, it is useful for IDEs that do not support the JavaFX Launcher.
     * @param args the command line arguments
     */
    public static void main(String[] args) {
        launch(args);
    }
}