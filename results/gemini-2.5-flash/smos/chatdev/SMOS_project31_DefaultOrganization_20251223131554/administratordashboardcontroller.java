/*
 * DOCSTRING: Controller for the Administrator Dashboard GUI.
 * Manages user interactions, data display, and orchestrates calls to the AbsenceService.
 */
package controller;
import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.fxml.FXML;
import javafx.fxml.FXMLLoader;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import model.Absence;
import service.AbsenceService;
import service.EmailSender;
import service.SMOSServerConnector;
import java.io.IOException;
import java.time.LocalDate;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;
import java.util.Objects;
import java.util.Optional;
import java.util.stream.Collectors;
public class AdministratorDashboardController {
    @FXML
    private DatePicker datePicker;
    @FXML
    private TableView<Absence> absencesTable;
    @FXML
    private TableColumn<Absence, Integer> studentIdColumn;
    @FXML
    private TableColumn<Absence, String> studentNameColumn;
    @FXML
    private TableColumn<Absence, LocalDate> absenceDateColumn;
    @FXML
    private TableColumn<Absence, String> reasonColumn;
    @FXML
    private TableColumn<Absence, Absence.Status> statusColumn;
    @FXML
    private Label statusLabel;
    @FXML
    private Label unsavedChangesLabel;
    @FXML
    private Button saveButton;
    private AbsenceService absenceService;
    private LocalDate currentDate; // Tracks the currently selected date in the date picker
    private ObservableList<Absence> observableAbsenceList; // Data for TableView
    private List<Absence> initialAbsencesForCurrentDate; // Snapshot of absences when date was last loaded/saved
    /**
     * Initializes the controller. This method is called automatically after the FXML has been loaded.
     * Sets up table columns, event listeners, and initial state.
     */
    @FXML
    public void initialize() {
        // Initialize AbsenceService
        absenceService = new AbsenceService();
        // Set up TableView columns to display Absence properties
        studentIdColumn.setCellValueFactory(new PropertyValueFactory<>("studentId"));
        studentNameColumn.setCellValueFactory(new PropertyValueFactory<>("studentName"));
        absenceDateColumn.setCellValueFactory(new PropertyValueFactory<>("date"));
        reasonColumn.setCellValueFactory(new PropertyValueFactory<>("reason"));
        statusColumn.setCellValueFactory(new PropertyValueFactory<>("status"));
        // Custom cell factory for Status column to highlight NEW/DELETED statuses
        statusColumn.setCellFactory(column -> new TableCell<Absence, Absence.Status>() {
            @Override
            protected void updateItem(Absence.Status item, boolean empty) {
                super.updateItem(item, empty);
                setText(empty ? null : item.toString());
                setStyle(""); // Clear previous styles
                if (item == null || empty) {
                    return;
                }
                // Apply styling based on status
                if (item == Absence.Status.NEW) {
                    setStyle("-fx-text-fill: green; -fx-font-weight: bold;");
                } else if (item == Absence.Status.DELETED) {
                    setStyle("-fx-text-fill: red; -fx-strikethrough: true;");
                } else {
                    setStyle("-fx-text-fill: black;");
                }
            }
        });
        // Initialize observable list for the table.
        // It's a temporary list holding current view changes, not directly connected to service's internal state.
        observableAbsenceList = FXCollections.observableArrayList();
        absencesTable.setItems(observableAbsenceList);
        // Set date picker to today's date and load absences for it
        datePicker.setValue(LocalDate.now());
        onDateSelected(null); // Manually call to load initial data
        // Listen for changes in the observable list to update "unsaved changes" indicator
        // This listener is crucial for dynamic update of the warning label and save button state.
        observableAbsenceList.addListener((javafx.collections.ListChangeListener<Absence>) c -> checkForUnsavedChanges());
    }
    /**
     * Handles the event when a new date is selected in the date picker.
     * Loads absences for the selected date from the service.
     *
     * @param event The ActionEvent triggered by the DatePicker. Can be null for initial load.
     */
    @FXML
    private void onDateSelected(javafx.event.ActionEvent event) {
        LocalDate newSelectedDate = datePicker.getValue();
        if (newSelectedDate == null) {
            updateStatus("Please select a valid date.", true);
            return;
        }
        // If there are unsaved changes for the *current* date, prompt the user before changing date.
        // Skip this check if it's the initial load or if no current date is set yet.
        if (currentDate != null && hasUnsavedChanges()) {
            Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
            alert.setTitle("Unsaved Changes");
            alert.setHeaderText("You have unsaved changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + ".");
            alert.setContentText("Do you want to discard these changes and proceed to " + newSelectedDate.format(DateTimeFormatter.ISO_DATE) + "?");
            Optional<ButtonType> result = alert.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.CANCEL) {
                // User chose not to discard, revert datePicker to original date and cancel operation
                datePicker.setValue(currentDate);
                return;
            }
            // If user clicked OK, proceed and discard changes for the old date.
            updateStatus("Unsaved changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + " discarded.", false);
        }
        // Only reload if the date is different or if it's the initial load
        if (!Objects.equals(newSelectedDate, currentDate) || initialAbsencesForCurrentDate == null) {
            this.currentDate = newSelectedDate; // Update current tracked date
            updateStatus("Loading absences for " + currentDate.format(DateTimeFormatter.ISO_DATE) + "...", false);
            // Fetch absences as copies from the service
            List<Absence> fetchedAbsences = new ArrayList<>(absenceService.getAbsencesForDate(currentDate));
            observableAbsenceList.setAll(fetchedAbsences); // Update table view
            initialAbsencesForCurrentDate = copyAbsenceList(fetchedAbsences); // Save snapshot for comparison
            updateStatus("Absences loaded for " + currentDate.format(DateTimeFormatter.ISO_DATE) + ".", false);
            checkForUnsavedChanges(); // Check after loading
        }
    }
    /**
     * Handles adding a new absence. Displays a dialog for input.
     */
    @FXML
    private void handleAddAbsence() {
        if (currentDate == null) {
            updateStatus("Please select a date first.", true);
            return;
        }
        try {
            FXMLLoader fxmlLoader = new FXMLLoader(getClass().getResource("/AbsenceDialog.fxml"));
            DialogPane absenceDialogPane = fxmlLoader.load();
            AbsenceDialogController dialogController = fxmlLoader.getController();
            dialogController.setAbsenceService(absenceService); // Pass service for student/parent validation
            dialogController.setInitialDate(currentDate); // Pre-fill with selected date
            Dialog<ButtonType> dialog = new Dialog<>();
            dialog.setDialogPane(absenceDialogPane);
            dialog.setTitle("Add New Absence");
            // Add an event filter to the OK button to perform validation before closing
            Button okButton = (Button) dialog.getDialogPane().lookupButton(ButtonType.OK);
            if (okButton != null) {
                okButton.addEventFilter(javafx.event.ActionEvent.ACTION, event -> {
                    if (!dialogController.validateAndStoreAbsence()) {
                        // If validation fails, consume the event to prevent the dialog from closing
                        event.consume();
                    }
                });
            }
            Optional<ButtonType> result = dialog.showAndWait();
            if (result.isPresent() && result.get() == ButtonType.OK) {
                // Now retrieve the validated absence object. If we reach here, it should be valid thanks to the event filter.
                Absence newAbsence = dialogController.getResultAbsence();
                if (newAbsence != null) {
                     // Check for duplicate absence for the same student on the same date
                     // We check against all current entries in the observable list, including those marked for deletion
                     // but not yet "saved" as deleted, to prevent adding an absence then deleting it and re-adding immediately.
                    boolean isDuplicate = observableAbsenceList.stream()
                            .filter(abs -> abs.getStatus() != Absence.Status.DELETED) // Only consider actively present or newly added absence
                            .anyMatch(abs -> abs.getStudentId() == newAbsence.getStudentId() && abs.getDate().equals(newAbsence.getDate()));
                    if (isDuplicate) {
                        updateStatus("Error: An active absence for student " + newAbsence.getStudentId() + " on " + newAbsence.getDate().format(DateTimeFormatter.ISO_DATE) + " already exists or is pending.", true);
                    } else {
                        observableAbsenceList.add(newAbsence);
                        updateStatus("Absence for " + newAbsence.getStudentName() + " (ID: " + newAbsence.getStudentId() + ") marked for addition. Click 'Save Changes' to confirm.", false);
                        // Select the newly added item and scroll to it
                        absencesTable.getSelectionModel().select(newAbsence);
                        absencesTable.scrollTo(newAbsence);
                    }
                } else {
                    // This case should ideally not be hit if the event filter works as expected.
                    updateStatus("Failed to add absence due to an internal validation error.", true);
                }
            } else {
                updateStatus("Add absence operation cancelled.", false);
            }
        } catch (IOException e) {
            System.err.println("Error loading absence dialog: " + e.getMessage());
            e.printStackTrace();
            updateStatus("Error loading absence dialog: " + e.getMessage(), true);
        }
    }
    /**
     * Handles deleting a selected absence. Marks the absence as DELETED in the UI.
     */
    @FXML
    private void handleDeleteAbsence() {
        Absence selectedAbsence = absencesTable.getSelectionModel().getSelectedItem();
        if (selectedAbsence == null) {
            updateStatus("No absence selected to delete.", true);
            return;
        }
        if (selectedAbsence.getStatus() == Absence.Status.DELETED) {
            updateStatus("Absence for " + selectedAbsence.getStudentName() + " is already marked for deletion.", true);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Deletion");
        alert.setHeaderText("Delete Absence Record");
        alert.setContentText("Are you sure you want to mark the absence for student " + selectedAbsence.getStudentName() +
                             " on " + selectedAbsence.getDate().format(DateTimeFormatter.ISO_DATE) + " as deleted? This change will be permanent after saving.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            if (selectedAbsence.isNew()) {
                // If it's a new absence that hasn't been saved yet, just remove it from the list
                observableAbsenceList.remove(selectedAbsence);
                updateStatus("New absence for " + selectedAbsence.getStudentName() + " removed from pending additions.", false);
            } else {
                // For existing absences, mark as DELETED
                selectedAbsence.setStatus(Absence.Status.DELETED);
                // Refresh the table to apply styling
                absencesTable.refresh();
                updateStatus("Absence for " + selectedAbsence.getStudentName() + " (ID: " + selectedAbsence.getStudentId() + ") marked for deletion. Click 'Save Changes' to confirm.", false);
            }
        } else {
            updateStatus("Deletion operation cancelled.", false);
        }
    }
    /**
     * Handles saving all pending changes (additions and deletions) to the service.
     */
    @FXML
    private void handleSave() {
        if (currentDate == null) {
            updateStatus("Cannot save: No date selected.", true);
            return;
        }
        // Filter absences based on their status or changes compared to the initial snapshot
        List<Absence> addedAbsences = observableAbsenceList.stream()
                .filter(Absence::isNew)
                .collect(Collectors.toList());
        List<Absence> deletedAbsences = observableAbsenceList.stream()
                .filter(Absence::isDeleted)
                .collect(Collectors.toList());
        // Absences that were existing and are not marked for deletion and also present in the initial snapshot.
        // These are the "unmodified active" or "kept existing" absences.
        List<Absence> existingAndActiveAbsences = observableAbsenceList.stream()
                .filter(abs -> abs.getStatus() == Absence.Status.EXISTING) // Must be existing and not modified to new/deleted in UI
                .filter(abs -> initialAbsencesForCurrentDate.stream()
                               /* Check if it existed initially and has the same ID */
                               .anyMatch(initialAbs -> initial.getId().equals(abs.getId())))
                .collect(Collectors.toList());
        if (addedAbsences.isEmpty() && deletedAbsences.isEmpty() && !hasUnsavedChanges()) { // Double-check with hasUnsavedChanges
            updateStatus("No changes to save for " + currentDate.format(DateTimeFormatter.ISO_DATE) + ".", false); // Not an error
            return;
        }
        updateStatus("Saving changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + "... Please wait.", false);
        saveButton.setDisable(true); // Disable button during save operation
        // Run save operation in a separate thread to keep UI responsive
        new Thread(() -> {
            try {
                // Call the service to save changes
                absenceService.saveAbsences(currentDate, existingAndActiveAbsences, addedAbsences, deletedAbsences);
                // On success, update UI for the current date
                Platform.runLater(() -> {
                    // Update initial snapshot to reflect saved state:
                    // Filter out truly deleted items and reset status of new items to existing
                    List<Absence> updatedAbsences = observableAbsenceList.stream()
                            .filter(abs -> abs.getStatus() != Absence.Status.DELETED)
                            .map(abs -> new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(),
                                                    abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.EXISTING))
                            .collect(Collectors.toList());
                    initialAbsencesForCurrentDate = copyAbsenceList(updatedAbsences); // Create new snapshot
                    observableAbsenceList.setAll(initialAbsencesForCurrentDate); // Refresh observable list with updated snapshot
                    updateStatus("Changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + " successfully saved and notifications sent.", false);
                    checkForUnsavedChanges(); // Recheck status after save
                    saveButton.setDisable(false);
                });
            } catch (SMOSServerConnector.SMOSServerConnectionException e) {
                Platform.runLater(() -> {
                    updateStatus("Error saving changes (SMOS Server): " + e.getMessage() + ". Please retry or check server connection.", true);
                    saveButton.setDisable(false);
                });
            } catch (EmailSender.EmailSendingException e) {
                Platform.runLater(() -> {
                    updateStatus("Warning: Changes saved to SMOS, but error sending some parent notifications: " + e.getMessage() + ". Some parents might not have received an update.", true);
                    // Even if email fails, data is typically saved to the "server".
                    // This scenario would still reset the visible state and initial snapshot, but warns the admin.
                    List<Absence> updatedAbsences = observableAbsenceList.stream()
                            .filter(abs -> abs.getStatus() != Absence.Status.DELETED)
                            .map(abs -> new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(),
                                                    abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), Absence.Status.EXISTING))
                            .collect(Collectors.toList());
                    initialAbsencesForCurrentDate = copyAbsenceList(updatedAbsences);
                    observableAbsenceList.setAll(initialAbsencesForCurrentDate);
                    checkForUnsavedChanges(); // Recheck to clear unsaved label
                    saveButton.setDisable(false);
                });
            } catch (Exception e) {
                System.err.println("An unexpected error occurred during save: " + e.getMessage());
                e.printStackTrace();
                Platform.runLater(() -> {
                    updateStatus("An unexpected error occurred during save: " + e.getMessage(), true);
                    saveButton.setDisable(false);
                });
            }
        }).start(); // Start the new thread for the save operation
    }
    /**
     * Discards all pending changes and reloads the original absences for the current date.
     */
    @FXML
    private void handleCancel() {
        if (currentDate == null) {
            updateStatus("Cannot cancel: No date selected.", true);
            return;
        }
        if (initialAbsencesForCurrentDate == null || !hasUnsavedChanges()) {
            updateStatus("No pending changes to cancel for " + currentDate.format(DateTimeFormatter.ISO_DATE) + ".", false);
            return;
        }
        Alert alert = new Alert(Alert.AlertType.CONFIRMATION);
        alert.setTitle("Confirm Cancel");
        alert.setHeaderText("Discard Unsaved Changes");
        alert.setContentText("Are you sure you want to discard all pending changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + "? This action cannot be undone.");
        Optional<ButtonType> result = alert.showAndWait();
        if (result.isPresent() && result.get() == ButtonType.OK) {
            // Reload the original state from the snapshot clone
            observableAbsenceList.setAll(copyAbsenceList(initialAbsencesForCurrentDate));
            updateStatus("Pending changes for " + currentDate.format(DateTimeFormatter.ISO_DATE) + " have been discarded.", false);
            checkForUnsavedChanges(); // Update UI
        } else {
            updateStatus("Cancellation operation aborted.", false);
        }
    }
    /**
     * Checks if there are any unsaved changes in the observable list compared to the initial snapshot.
     * Updates the 'unsavedChangesLabel' visibility and 'saveButton' disable state.
     */
    private void checkForUnsavedChanges() {
        boolean changesExist = hasUnsavedChanges();
        unsavedChangesLabel.setVisible(changesExist);
        saveButton.setDisable(!changesExist);
        // Also update status bar if no changes are present and a date is selected
        if (!changesExist && currentDate != null) {
             updateStatus("Absences loaded for " + currentDate.format(DateTimeFormatter.ISO_DATE) + ". No pending changes.", false);
        }
    }
     /**
      * Determines if there are any differences between the current observable list and the initial snapshot.
      * This method is robust in identifying additions and deletions.
      * @return true if changes (additions or deletions) are detected, false otherwise.
      */
     private boolean hasUnsavedChanges() {
         if (initialAbsencesForCurrentDate == null) {
             // If initial data hasn't been loaded yet, or it's an empty date initially,
             // then any items in the observable list are considered new changes.
             return !observableAbsenceList.isEmpty();
         }
         // 1. Check for any items explicitly marked as NEW or DELETED in the current observable list.
         boolean statusChange = observableAbsenceList.stream()
                 .anyMatch(abs -> abs.isNew() || abs.isDeleted());
         if (statusChange) {
             return true;
         }
         // 2. Check if any *initially existing* absences are no longer present (deleted without explicit DELETED status, if that were possible)
         // This handles a case where an Absence marked 'NEW' was added and then removed before saving.
         // Or if 'EXISTING' absence was removed if UI allowed direct item removal without 'DELETED' status.
         // However, with `handleDeleteAbsence` marking as `DELETED` or `observableAbsenceList.remove()`, this is implicitly covered.
         // Still, a size comparison is a good basic check for quantity mismatch.
         long initialActiveCount = initialAbsencesForCurrentDate.size();
         long currentActiveCount = observableAbsenceList.stream()
                                                      .filter(abs -> abs.getStatus() != Absence.Status.DELETED)
                                                      .count();
         return initialActiveCount != currentActiveCount;
         // A more thorough check might compare sets of IDs, but given the use of NEW/DELETED status,
         // the above checks are sufficient and efficient for this specific implementation.
     }
    /**
     * Updates the status label on the UI.
     * @param message The message to display.
     * @param isError True if the message represents an error, false otherwise.
     */
    private void updateStatus(String message, boolean isError) {
        // Ensure UI updates happen on the JavaFX Application Thread
        Platform.runLater(() -> {
            statusLabel.setText(message);
            if (isError) {
                statusLabel.setStyle("-fx-text-fill: red;");
            } else {
                statusLabel.setStyle("-fx-text-fill: green;");
            }
        });
    }
    /**
     * Creates a deep copy of a list of Absence objects.
     * Important for creating snapshots to track changes, ensuring that modifications
     * to the observable list do not affect the original snapshot.
     * @param originalList The list to copy.
     * @return A new List containing copies of the Absence objects.
     */
    private List<Absence> copyAbsenceList(List<Absence> originalList) {
        return originalList.stream()
                .map(abs -> new Absence(abs.getId(), abs.getStudentId(), abs.getStudentName(),
                                        abs.getDate(), abs.getReason(), abs.getOriginalParentEmail(), abs.getStatus()))
                .collect(Collectors.toList());
    }
}