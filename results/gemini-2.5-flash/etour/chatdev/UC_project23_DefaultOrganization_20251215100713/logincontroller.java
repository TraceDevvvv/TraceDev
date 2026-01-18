/**
 * Controller for the LoginView.
 * This class handles the logic for the login screen. It includes a simulated login process
 * where successful login transitions the user to the NewsListView.
 */
package com.chatdev.newsapp.controllers;
import com.chatdev.newsapp.Main;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TextField;
import javafx.scene.control.PasswordField;
import javafx.stage.Stage;
import java.io.IOException;
public class LoginController {
    @FXML
    private TextField usernameField; // Field to input username
    @FXML
    private PasswordField passwordField; // Field to input password
    /**
     * Handles the action when the login button is clicked.
     * It performs a simulated login check and navigates to the News List view on "success".
     */
    @FXML
    private void handleLoginButton() {
        String username = usernameField.getText();
        String password = passwordField.getText();
        // Simulate login: In a real app, this would involve validating credentials against a database.
        // For this use case, any non-empty username/password is considered "logged in".
        if (!username.isEmpty() && !password.isEmpty()) {
            System.out.println("Login successful for user: " + username); // Log success
            try {
                // Load the NewsListView (Step 2: View all news in a form)
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatdev/newsapp/views/NewsListView.fxml"));
                Parent root = loader.load();
                // Get the current stage and set the new scene
                Stage stage = Main.getPrimaryStage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                // Display error if FXML loading fails
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Login Error", "Could not load news list view.");
            }
        } else {
            // Display an alert for invalid credentials
            showAlert(Alert.AlertType.WARNING, "Login Failed", "Please enter username and password.");
        }
    }
    /**
     * Displays an alert dialog to the user.
     * @param alertType The type of alert (e.g., information, warning, error).
     * @param title The title of the alert dialog.
     * @param message The content message of the alert dialog.
     */
    private void showAlert(Alert.AlertType alertType, String title, String message) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(null); // No header text
        alert.setContentText(message);
        alert.showAndWait();
    }
}