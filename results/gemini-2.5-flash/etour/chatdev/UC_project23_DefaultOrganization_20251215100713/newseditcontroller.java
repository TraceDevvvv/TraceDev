/**
 * Controller for the NewsEditView.
 * This class handles the logic for editing a specific news item. It allows the user to
 * modify news details, validates input, confirms changes, and saves them.
 */
package com.chatdev.newsapp.controllers;
import com.chatdev.newsapp.Main;
import com.chatdev.newsapp.models.News;
import com.chatdev.newsapp.serv.NewsService;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.Button;
import javafx.scene.control.ButtonType;
import javafx.scene.control.TextField;
import javafx.scene.control.TextArea;
import javafx.stage.Stage;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Optional;
public class NewsEditController {
    @FXML
    private TextField idField; // Field for news ID (read-only)
    @FXML
    private TextField titleField; // Field for news title
    @FXML
    private TextArea contentArea; // Area for news content
    @FXML
    private TextField authorField; // Field for news author
    @FXML
    private TextField dateField; // Field for news date (e.g., YYYY-MM-DD)
    @FXML
    private Button simulateServerFailureButton; // FXML field for the button
    private News selectedNews; // The news item currently being edited
    private NewsService newsService = new NewsService(); // Service to interact with news data
    /**
     * Initializes the controller. This method is called automatically after the FXML file has been loaded.
     * Sets up the initial text of the simulate server failure button.
     */
    @FXML
    public void initialize() {
        // Update button text based on current simulation state when the view is loaded
        updateSimulateButtonText();
    }
    /**
     * Sets the News object to be edited and populates the form fields with its data.
     * (Step 4: Load the data of news and displays them in a form for editing.)
     * @param news The News object to be edited.
     */
    public void setNews(News news) {
        this.selectedNews = news;
        if (selectedNews != null) {
            idField.setText(selectedNews.getId());
            titleField.setText(selectedNews.getTitle());
            contentArea.setText(selectedNews.getContent());
            authorField.setText(selectedNews.getAuthor());
            dateField.setText(selectedNews.getDate());
            idField.setEditable(false); // ID should not be editable
        }
    }
    /**
     * Handles the "Save" button action.
     * (Step 5: Change data in the form and submit.)
     * (Step 6: Check the modified information and asks for confirmation of the transaction.)
     * (Step 7: Confirm the operation of changing the data news.)
     * It collects data from the form, validates it, asks for confirmation, and then updates the news.
     */
    @FXML
    private void handleSaveNews() {
        if (selectedNews == null) {
            showAlert(Alert.AlertType.ERROR, "Error", "No news item selected for editing.");
            return;
        }
        // Create a new News object with updated data from the form
        News updatedNews = new News(
                selectedNews.getId(), // ID remains same as it's not editable
                titleField.getText(),
                contentArea.getText(),
                authorField.getText(),
                dateField.getText()
        );
        // Validate the modified information
        String validationError = validateNews(updatedNews);
        if (validationError != null) {
            // (Step 6: ...Where the data is invalid or insufficient, the system activates the use case Errored.)
            showAlert(Alert.AlertType.ERROR, "Validation Error (Errored Use Case)", validationError);
            return; // Stop processing if validation fails
        }
        // Ask for confirmation (Step 6: ...asks for confirmation of the transaction.)
        Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmationAlert.setTitle("Confirm Save");
        confirmationAlert.setHeaderText("Confirm News Modification");
        confirmationAlert.setContentText("Are you sure you want to save the changes to this news item?\n\n" +
                "Title: " + updatedNews.getTitle() + "\n" +
                "Author: " + updatedNews.getAuthor() + "\n" +
                "Date: " + updatedNews.getDate());
        Optional<ButtonType> result = confirmationAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            try {
                // (Step 8: Stores data modified news.)
                newsService.updateNews(updatedNews);
                showAlert(Alert.AlertType.INFORMATION, "Success", "News updated successfully!"); // Exit condition: notify success
                System.out.println("News (ID: " + updatedNews.getId() + ") updated successfully.");
                returnToNewsList(); // Return to the news list view
            } catch (NewsService.ServerConnectionException e) {
                // Handle the ETOUR exit condition
                showAlert(Alert.AlertType.ERROR, "Connection Error (ETOUR)", "An error occurred due to server connection interruption: " + e.getMessage());
                System.err.println("ETOUR caught: " + e.getMessage());
            } catch (NewsService.NewsNotFoundException e) {
                // Handle case where news ID is suddenly no longer valid
                showAlert(Alert.AlertType.ERROR, "Save Error", "Failed to update news: " + e.getMessage());
                System.err.println("NewsNotFoundException caught: " + e.getMessage());
            } catch (IllegalArgumentException e) {
                // Handle validation errors that might slip through or internal logic issues
                showAlert(Alert.AlertType.ERROR, "Internal Data Error", "There was an internal issue with the news data: " + e.getMessage());
                System.err.println("IllegalArgumentException caught: " + e.getMessage());
            } catch (Exception e) { // Catch any other unexpected exceptions
                showAlert(Alert.AlertType.ERROR, "Unexpected Error", "An unexpected error occurred during save: " + e.getMessage());
                e.printStackTrace();
            }
        } else {
            System.out.println("News modification cancelled by user at confirmation."); // User cancelled during confirmation
        }
    }
    /**
     * Validates the fields of a News object.
     * (Part of Step 6: Check the modified information.)
     * @param news The News object to validate.
     * @return A string with error message if invalid, or null if valid.
     */
    private String validateNews(News news) {
        if (news.getTitle() == null || news.getTitle().trim().isEmpty()) {
            return "Title cannot be empty.";
        }
        if (news.getContent() == null || news.getContent().trim().isEmpty()) {
            return "Content cannot be empty.";
        }
        if (news.getAuthor() == null || news.getAuthor().trim().isEmpty()) {
            return "Author cannot be empty.";
        }
        if (news.getDate() == null || news.getDate().trim().isEmpty()) {
            return "Date cannot be empty.";
        }
        // Basic date format validation (YYYY-MM-DD)
        try {
            LocalDate.parse(news.getDate());
        } catch (DateTimeParseException e) {
            return "Date is not in a valid format (YYYY-MM-DD).";
        }
        return null; // All valid
    }
    /**
     * Handles the "Cancel" button action.
     * (Exit condition: The Operator Agency cancels the operation.)
     * Returns to the NewsList view without saving any changes.
     */
    @FXML
    private void handleCancelEdit() {
        System.out.println("News modification operation cancelled by Operator Agency.");
        returnToNewsList(); // Return to the news list view
    }
    /**
     * Handles toggling the "Simulate Server Failure" button action.
     * Activates or deactivates the simulation of a server connection failure in the NewsService.
     * This allows for testing the 'ETOUR' exit condition and subsequent successful saves.
     */
    @FXML
    private void handleToggleServerFailureSimulation() {
        // Query the state using the getter method from NewsService
        if (NewsService.isServerFailureSimulated()) {
            newsService.deactivateServerInterruption();
            showAlert(Alert.AlertType.INFORMATION, "Simulation Deactivated", "Server failure simulation is OFF. Saves should now work.");
        } else {
            newsService.activateServerInterruption(); // Use the existing service method to activate
            showAlert(Alert.AlertType.WARNING, "Simulation Activated", "Server failure simulation is ON. Next save might fail!");
        }
        updateSimulateButtonText(); // Update button text after toggling
    }
    /**
     * Updates the text of the simulate server failure button based on the current simulation state.
     * This ensures the button label accurately reflects its next action.
     */
    private void updateSimulateButtonText() {
        if (simulateServerFailureButton != null) {
            // Query the state using the getter method from NewsService
            if (NewsService.isServerFailureSimulated()) {
                simulateServerFailureButton.setText("Deactivate Server Failure");
            } else {
                simulateServerFailureButton.setText("Simulate Server Failure");
            }
        }
    }
    /**
     * Navigates back to the NewsList view.
     */
    private void returnToNewsList() {
        try {
            FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatdev/newsapp/views/NewsListView.fxml"));
            Parent root = loader.load();
            Stage stage = Main.getPrimaryStage();
            Scene scene = new Scene(root);
            stage.setScene(scene);
            stage.show();
        } catch (IOException e) {
            e.printStackTrace();
            showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not return to news list view.");
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