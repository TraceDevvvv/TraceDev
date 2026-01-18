package com.etour.deletebanner.controller;

import com.etour.deletebanner.model.Banner;
import com.etour.deletebanner.model.RefreshmentPoint;
import com.etour.deletebanner.service.BannerService;
import com.etour.deletebanner.util.NetworkException;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.fxml.FXML;
import javafx.scene.control.*;

import java.util.List;
import java.util.Optional;

/**
 * Controller for the Delete Banner UI. Handles user interactions and orchestrates
 * data flow between the view and the BannerService.
 */
public class DeleteBannerController {

    @FXML
    private ComboBox<RefreshmentPoint> refreshmentPointComboBox;
    @FXML
    private ListView<Banner> bannerListView;
    @FXML
    private Button deleteBannerButton;
    @FXML
    private Label statusLabel;

    private final BannerService bannerService;

    public DeleteBannerController() {
        this.bannerService = new BannerService();
    }

    /**
     * Initializes the controller. This method is automatically called
     * after the FXML file has been loaded.
     */
    @FXML
    private void initialize() {
        // Disable delete button initially
        deleteBannerButton.setDisable(true);

        // Load refreshment points when the application starts
        loadRefreshmentPoints();

        // Listener for refreshment point selection changes
        refreshmentPointComboBox.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> {
                    if (newValue != null) {
                        loadBannersForRefreshmentPoint(newValue.getId());
                    } else {
                        bannerListView.getItems().clear();
                        deleteBannerButton.setDisable(true);
                    }
                }
        );

        // Listener for banner selection changes
        bannerListView.getSelectionModel().selectedItemProperty().addListener(
                (observable, oldValue, newValue) -> deleteBannerButton.setDisable(newValue == null)
        );
    }

    /**
     * Loads all available refreshment points into the ComboBox.
     */
    private void loadRefreshmentPoints() {
        statusLabel.setText("Loading refreshment points...");
        new Thread(() -> {
            try {
                List<RefreshmentPoint> points = bannerService.getRefreshmentPoints();
                Platform.runLater(() -> {
                    refreshmentPointComboBox.setItems(FXCollections.observableArrayList(points));
                    statusLabel.setText("Refreshment points loaded.");
                });
            } catch (NetworkException e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Error loading refreshment points: " + e.getMessage());
                    showAlert(Alert.AlertType.ERROR, "Network Error", "Could not load refreshment points.", e.getMessage());
                });
            }
        }).start();
    }

    /**
     * Loads banners for the selected refreshment point into the ListView.
     * @param refreshmentPointId The ID of the selected refreshment point.
     */
    private void loadBannersForRefreshmentPoint(String refreshmentPointId) {
        statusLabel.setText("Loading banners...");
        new Thread(() -> {
            try {
                List<Banner> banners = bannerService.getBannersForRefreshmentPoint(refreshmentPointId);
                Platform.runLater(() -> {
                    bannerListView.setItems(FXCollections.observableArrayList(banners));
                    statusLabel.setText("Banners loaded.");
                });
            } catch (NetworkException e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Error loading banners: " + e.getMessage());
                    showAlert(Alert.AlertType.ERROR, "Network Error", "Could not load banners.", e.getMessage());
                });
            }
        }).start();
    }

    /**
     * Handles the action when the delete banner button is clicked.
     * Confirms deletion with the user and then attempts to delete the selected banner.
     */
    @FXML
    private void handleDeleteBanner() {
        Banner selectedBanner = bannerListView.getSelectionModel().getSelectedItem();
        if (selectedBanner != null) {
            Alert confirmationAlert = new Alert(Alert.AlertType.CONFIRMATION);
            confirmationAlert.setTitle("Confirm Deletion");
            confirmationAlert.setHeaderText("Delete Banner: " + selectedBanner.getName() + "?");
            confirmationAlert.setContentText("Are you sure you want to delete this banner? This action cannot be undone.");

            Optional<ButtonType> result = confirmationAlert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                performBannerDeletion(selectedBanner);
            }
        } else {
            showAlert(Alert.AlertType.WARNING, "No Banner Selected", "Please select a banner to delete.", "");
        }
    }

    /**
     * Performs the actual banner deletion by calling the BannerService.
     * @param banner The banner to be deleted.
     */
    private void performBannerDeletion(Banner banner) {
        statusLabel.setText("Deleting banner: " + banner.getName() + "...");
        new Thread(() -> {
            try {
                boolean success = bannerService.deleteBanner(banner.getId());
                Platform.runLater(() -> {
                    if (success) {
                        statusLabel.setText("Banner '" + banner.getName() + "' deleted successfully.");
                        showAlert(Alert.AlertType.INFORMATION, "Deletion Successful", "Banner deleted.", "The banner '" + banner.getName() + "' has been successfully removed.");
                        // Refresh the banner list after deletion
                        RefreshmentPoint selectedPoint = refreshmentPointComboBox.getSelectionModel().getSelectedItem();
                        if (selectedPoint != null) {
                            loadBannersForRefreshmentPoint(selectedPoint.getId());
                        }
                    } else {
                        statusLabel.setText("Failed to delete banner: " + banner.getName());
                        showAlert(Alert.AlertType.ERROR, "Deletion Failed", "Could not delete banner.", "An unknown error occurred during deletion.");
                    }
                });
            } catch (NetworkException e) {
                Platform.runLater(() -> {
                    statusLabel.setText("Error deleting banner: " + e.getMessage());
                    showAlert(Alert.AlertType.ERROR, "Network Error", "Could not delete banner.", e.getMessage());
                });
            }
        }).start();
    }

    /**
     * Displays an alert dialog to the user.
     * @param alertType The type of alert (e.g., ERROR, INFORMATION, WARNING).
     * @param title The title of the alert dialog.
     * @param header The header text of the alert dialog.
     * @param content The main content message of the alert dialog.
     */
    private void showAlert(Alert.AlertType alertType, String title, String header, String content) {
        Alert alert = new Alert(alertType);
        alert.setTitle(title);
        alert.setHeaderText(header);
        alert.setContentText(content);
        alert.showAndWait();
    }
}