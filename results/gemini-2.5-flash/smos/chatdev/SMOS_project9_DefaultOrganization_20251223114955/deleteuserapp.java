/*
Main application class for the DeleteUser use case.
Implements a JavaFX GUI to display users, select a user for details,
and delete a selected user as an Administrator. It now includes
an abstraction for SMOS server interaction.
*/
package com.chatdev.deleteuser;
import javafx.application.Application;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.Scene;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.BorderPane;
import javafx.scene.layout.HBox;
import javafx.scene.layout.VBox;
import javafx.stage.Stage;
import java.util.Optional;
public class DeleteUserApp extends Application {
    private UserManager userManager; // Manages user data
    private TableView<User> userTable; // Displays the list of users
    private Label selectedUserDetailsLabel; // Displays details of the currently selected user
    private Button deleteButton; // Button to trigger user deletion
    private SMOSAdapter smosAdapter; // Adapter for SMOS server interaction
    public static void main(String[] args) {
        launch(args);
    }
    @Override
    public void start(Stage primaryStage) {
        // Initialize the user manager with some dummy data
        userManager = new UserManager();
        // Initialize the SMOS adapter for simulated SMOS interactions
        smosAdapter = new MockSMOSAdapter();
        primaryStage.setTitle("User Management System - Delete User");
        // --- GUI Components Setup ---
        // 1. User Table (Displays all users)
        userTable = new TableView<>();
        initializeUserTable(); // Configure columns
        updateUserTable(); // Populate with initial data
        // Listener for table row selection: updates selected user details
        userTable.getSelectionModel().selectedItemProperty().addListener((obs, oldSelection, newSelection) -> {
            if (newSelection != null) {
                displayUserDetails(newSelection);
                deleteButton.setDisable(false); // Enable delete button when a user is selected
            } else {
                selectedUserDetailsLabel.setText("Select a user to view details.");
                deleteButton.setDisable(true); // Disable delete button if no user is selected
            }
        });
        // 2. Selected User Details Label
        selectedUserDetailsLabel = new Label("Select a user from the table to view their details.");
        selectedUserDetailsLabel.setStyle("-fx-font-weight: bold; -fx-padding: 10px;"); // Basic styling
        // 3. Delete Button
        deleteButton = new Button("Delete Selected User");
        deleteButton.setStyle("-fx-background-color: #f44336; -fx-text-fill: white; -fx-font-weight: bold;"); // Red button
        deleteButton.setDisable(true); // Initially disabled until a user is selected
        deleteButton.setOnAction(e -> handleDeleteUser()); // Attach event handler
        // --- Layout Setup ---
        // Controls for selected user details and delete button
        VBox detailsPanel = new VBox(10);
        detailsPanel.setAlignment(Pos.CENTER_LEFT);
        detailsPanel.setPadding(new Insets(10));
        detailsPanel.getChildren().addAll(new Label("User Details:"), selectedUserDetailsLabel, deleteButton);
        detailsPanel.setStyle("-fx-border-color: #ccc; -fx-border-width: 1px; -fx-border-radius: 5px;");
        // Main layout using BorderPane
        BorderPane root = new BorderPane();
        root.setPadding(new Insets(10));
        root.setCenter(userTable);
        root.setBottom(detailsPanel); // Details and button at the bottom
        // Add a title label at the top
        Label titleLabel = new Label("Administrator Dashboard: Delete User");
        titleLabel.setStyle("-fx-font-size: 20px; -fx-font-weight: bold; -fx-padding: 0 0 10px 0;");
        BorderPane.setAlignment(titleLabel, Pos.CENTER);
        // Administrator status simulation (Precondition: User is logged in as administrator)
        Label adminStatusLabel = new Label("Administrator: Logged In");
        adminStatusLabel.setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
        HBox statusBar = new HBox(adminStatusLabel);
        statusBar.setPadding(new Insets(5, 0, 0, 0));
        statusBar.setAlignment(Pos.CENTER_RIGHT);
        root.setTop(new VBox(titleLabel, statusBar)); // Combine title and status bar
        // Set up the scene and show the stage
        Scene scene = new Scene(root, 700, 500);
        primaryStage.setScene(scene);
        primaryStage.show();
    }
    /**
     * Initializes the columns of the userTable.
     * Each column corresponds to a property of the User object.
     */
    private void initializeUserTable() {
        // Create columns
        TableColumn<User, String> idColumn = new TableColumn<>("User ID");
        idColumn.setCellValueFactory(new PropertyValueFactory<>("id"));
        idColumn.setMinWidth(100);
        TableColumn<User, String> nameColumn = new TableColumn<>("Name");
        nameColumn.setCellValueFactory(new PropertyValueFactory<>("name"));
        nameColumn.setMinWidth(200);
        TableColumn<User, String> emailColumn = new TableColumn<>("Email");
        emailColumn.setCellValueFactory(new PropertyValueFactory<>("email"));
        emailColumn.setMinWidth(250);
        // Add columns to the table
        userTable.getColumns().addAll(idColumn, nameColumn, emailColumn);
    }
    /**
     * Refreshes the userTable with the current list of users from the UserManager.
     * This method is called after any changes to the user list (e.g., deletion).
     */
    private void updateUserTable() {
        ObservableList<User> userObservableList = FXCollections.observableArrayList(userManager.getAllUsers());
        userTable.setItems(userObservableList);
        userTable.refresh(); // Ensure the table view updates
    }
    /**
     * Displays the details of the selected user in the dedicated label.
     * Simulates the "viewdetTailsente" (view user details) use case.
     *
     * @param user The User object whose details are to be displayed.
     */
    private void displayUserDetails(User user) {
        if (user != null) {
            selectedUserDetailsLabel.setText(
                    "ID: " + user.getId() + "\n" +
                    "Name: " + user.getName() + "\n" +
                    "Email: " + user.getEmail()
            );
        } else {
            selectedUserDetailsLabel.setText("No user selected.");
        }
    }
    /**
     * Handles the action when the "Delete" button is clicked.
     * Performs user deletion after a confirmation, updates the UI,
     * and triggers the "SMOS server interrupted" postcondition via the SMOS adapter.
     */
    private void handleDeleteUser() {
        User selectedUser = userTable.getSelectionModel().getSelectedItem();
        if (selectedUser == null) {
            showAlert(Alert.AlertType.WARNING, "No User Selected", "Please select a user from the table to delete.");
            return;
        }
        // Confirmation dialog for deletion
        Alert confirmAlert = new Alert(Alert.AlertType.CONFIRMATION);
        confirmAlert.setTitle("Confirm Deletion");
        confirmAlert.setHeaderText("Delete User: " + selectedUser.getName() + " (ID: " + selectedUser.getId() + ")?");
        confirmAlert.setContentText("Are you sure you want to permanently delete this user? This action cannot be undone.");
        Optional<ButtonType> result = confirmAlert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Precondition: Admin is logged in (simulated by having the app open)
            // Precondition: User details are displayed (selectedUser is not null)
            // Precondition: Delete button is clicked (handled by this method)
            // 1. Delete the user from the archive
            boolean deleted = userManager.deleteUser(selectedUser.getId());
            if (deleted) {
                // Postcondition: The user has been canceled
                showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "User '" + selectedUser.getName() + "' (ID: " + selectedUser.getId() + ") has been successfully deleted.");
                // 2. Displays the list of updated users
                updateUserTable();
                selectedUserDetailsLabel.setText("User deleted. Select another user.");
                deleteButton.setDisable(true); // Disable button after deletion until new selection
                // Postcondition: Connection to the SMOS server interrupted
                // Delegate this action to the SMOSAdapter for simulation
                smosAdapter.disconnectUser(selectedUser.getId(), selectedUser.getName());
                // Explicitly show the alert for SMOS interruption from the UI layer
                showAlert(Alert.AlertType.INFORMATION, 
                          "SMOS Connection Status",
                          "Connection to SMOS server for " + selectedUser.getName() + " (ID: " + selectedUser.getId() + ") interrupted.",
                          "This simulates the system severing ties with external serv for the canceled user.");
            } else {
                showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Could not delete user '" + selectedUser.getName() + "'. User might not exist.");
            }
        }
    }
    /**
     * Helper method to display various types of alerts.
     * @param type The type of alert (e.g., INFORMATION, WARNING, ERROR).
     * @param title The title of the alert window.
     * @param header The header text of the alert.
     * @param content The main content message of the alert.
     */
    private void showAlert(Alert.AlertType type, String title, String header, String content) {
        Alert alert = new Alert(type);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
    /**
     * Overloaded helper method to display various types of alerts without specific content text.
     * @param type The type of alert (e.g., INFORMATION, WARNING, ERROR).
     * @param title The title of the alert window.
     * @param header The header text of the alert.
     */
    private void showAlert(Alert.AlertType type, String title, String header) {
        showAlert(type, title, header, null); // Call the more general method
    }
}