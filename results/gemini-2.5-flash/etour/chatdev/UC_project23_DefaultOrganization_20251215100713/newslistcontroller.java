/**
 * Controller for the NewsListView.
 * This class displays a list of news items in a TableView and allows the user to select
 * a news item for editing, or refresh the list.
 */
package com.chatdev.newsapp.controllers;
import com.chatdev.newsapp.Main;
import com.chatdev.newsapp.models.News;
import com.chatdev.newsapp.serv.NewsService;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.Parent;
import javafx.scene.Scene;
import javafx.scene.control.Alert;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.stage.Stage;
import java.io.IOException;
public class NewsListController {
    @FXML
    private TableView<News> newsTable; // TableView to display the list of news
    @FXML
    private TableColumn<News, String> idColumn; // Column for news ID
    @FXML
    private TableColumn<News, String> titleColumn; // Column for news title
    @FXML
    private TableColumn<News, String> authorColumn; // Column for news author
    @FXML
    private TableColumn<News, String> dateColumn; // Column for news date
    private NewsService newsService = new NewsService(); // Service to interact with news data
    private ObservableList<News> newsObservableList = FXCollections.observableArrayList(); // List for TableView
    /**
     * Initializes the controller. This method is called automatically after the FXML file has been loaded.
     * It sets up the TableView columns and populates the table with initial news data.
     * (Step 2: View all news in a form.)
     */
    @FXML
    public void initialize() {
        // Configure table columns to link with News object properties
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        titleColumn.setCellValueFactory(new PropertyValueFactory<>("title"));
        authorColumn.setCellValueFactory(new PropertyValueFactory<>("author"));
        dateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        // Set the ObservableList as the items for the TableView
        newsTable.setItems(newsObservableList);
        // Load news data and refresh the table
        loadNewsData();
        // Optional: Handle double-click to edit news for quick access
        newsTable.setOnMouseClicked(event -> {
            if (event.getClickCount() == 2) {
                handleEditNews();
            }
        });
    }
    /**
     * Loads all news data from the service and updates the TableView.
     */
    private void loadNewsData() {
        newsObservableList.clear(); // Clear existing data
        newsObservableList.addAll(newsService.getAllNews()); // Add all news from the service
    }
    /**
     * Handles the "Refresh" button action.
     * Reloads news data to ensure the list is up-to-date.
     */
    @FXML
    private void handleRefreshNews() {
        System.out.println("Refreshing news list.");
        loadNewsData();
        showAlert(Alert.AlertType.INFORMATION, "Refresh Successful", "News list has been refreshed.");
    }
    /**
     * Handles the "Edit" button action (or double-click on a row).
     * (Step 3: Select a news from the list and submit the form.)
     * (Step 4: Load the data of news and displays them in a form for editing.)
     * It gets the selected news from the table and transitions to the NewsEditView.
     */
    @FXML
    private void handleEditNews() {
        News selectedNews = newsTable.getSelectionModel().getSelectedItem(); // Get the selected news item
        if (selectedNews != null) {
            try {
                // Load the NewsEditView FXML
                FXMLLoader loader = new FXMLLoader(getClass().getResource("/com/chatdev/newsapp/views/NewsEditView.fxml"));
                Parent root = loader.load();
                // Get the controller for the NewsEditView
                NewsEditController newsEditController = loader.getController();
                // Pass the selected news item to the edit controller
                newsEditController.setNews(selectedNews);
                // Get the current stage and set the new scene
                Stage stage = Main.getPrimaryStage();
                Scene scene = new Scene(root);
                stage.setScene(scene);
                stage.show();
            } catch (IOException e) {
                // Display error if FXML loading fails
                e.printStackTrace();
                showAlert(Alert.AlertType.ERROR, "Navigation Error", "Could not load news edit view.");
            }
        } else {
            // Display an alert if no news item is selected
            showAlert(Alert.AlertType.WARNING, "No Selection", "Please select a news item from the table to edit.");
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