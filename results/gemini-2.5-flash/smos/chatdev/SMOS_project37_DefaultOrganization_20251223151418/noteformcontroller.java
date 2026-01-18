'''
Controller class for the note_form.fxml.
Handles user input from the form, validates it, and interacts with the NoteService
to save disciplinary notes and send notifications.
'''
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextArea;
import javafx.scene.control.TextField;
import java.time.LocalDate;
public class NoteFormController {
    // FXML injected UI components
    @FXML
    private TextField studentNameField;
    @FXML
    private DatePicker datePicker;
    @FXML
    private TextField teacherNameField;
    @FXML
    private TextField parentEmailField;
    @FXML
    private TextArea descriptionArea;
    @FXML
    private Label messageLabel; // Label to display user feedback messages
    // Dependency injected NoteService
    private NoteService noteService;
    // Dependency injected SmosService for handling SMOS server connection interruption.
    private SmosService smosService;
    /**
     * Sets the NoteService dependency for this controller.
     * This method is called by the MainApplication after the FXML is loaded.
     *
     * @param noteService The NoteService instance to be used.
     */
    public void setNoteService(NoteService noteService) {
        this.noteService = noteService;
    }
    /**
     * Sets the SmosService dependency for this controller.
     * This method is called by the MainApplication after the FXML is loaded.
     *
     * @param smosService The SmosService instance to be used for connection interruption.
     */
    public void setSmosService(SmosService smosService) {
        this.smosService = smosService;
    }
    /**
     * Initializes the controller after its root element has been completely processed.
     * This method is automatically called by the FXMLLoader.
     */
    @FXML
    private void initialize() {
        // Set a default value for the date picker to today's date for user convenience.
        datePicker.setValue(LocalDate.now());
        // Add a listener to the description area for character limit (e.g., 255 characters).
        descriptionArea.textProperty().addListener((observable, oldValue, newValue) -> {
            if (newValue == null) return;
            if (newValue.length() > 255) {
                // Truncate the text if it exceeds the limit
                descriptionArea.setText(oldValue);
                messageLabel.setText("Description cannot exceed 255 characters.");
            } else {
                // Clear the message if within limit (or other field is interacted with)
                if (messageLabel.getText().equals("Description cannot exceed 255 characters.")) {
                     messageLabel.setText("");
                }
            }
        });
    }
    /**
     * Handles the action when the "Save" button is clicked.
     * Validates input, creates a Note object, saves it via NoteService, and provides feedback.
     */
    @FXML
    private void handleSaveButton() {
        // Clear previous messages and reset style for new messages.
        messageLabel.setText("");
        messageLabel.setStyle("-fx-text-fill: black;");
        // 1. Retrieve data from form fields
        String studentName = studentNameField.getText();
        LocalDate noteDate = datePicker.getValue();
        String teacherName = teacherNameField.getText();
        String parentEmail = parentEmailField.getText();
        String description = descriptionArea.getText();
        // 2. Validate input fields
        if (studentName == null || studentName.trim().isEmpty()) {
            messageLabel.setText("Student Name cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (noteDate == null) {
            messageLabel.setText("Date cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (teacherName == null || teacherName.trim().isEmpty()) {
            messageLabel.setText("Teacher Name cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (parentEmail == null || parentEmail.trim().isEmpty()) {
            messageLabel.setText("Parent Email cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (!isValidEmail(parentEmail)) {
            messageLabel.setText("Invalid Parent Email format.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (description == null || description.trim().isEmpty()) {
            messageLabel.setText("Description cannot be empty.");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        if (description.length() > 255) {
            messageLabel.setText("Description is too long (max 255 characters).");
            messageLabel.setStyle("-fx-text-fill: red;");
            return;
        }
        // 3. Create a Note object
        Note newNote = new Note(studentName, noteDate, teacherName, description, parentEmail);
        // 4. Use NoteService to save the note and send notification
        if (noteService != null) { // Ensure the service is injected
            boolean success = noteService.saveNote(newNote);
            if (success) {
                messageLabel.setText("Note saved and notification sent successfully!");
                messageLabel.setStyle("-fx-text-fill: green;"); // Green for success
                clearForm(); // Clear the form after successful submission
                // Postcondition: Administrator interrupts the connection to the SMOS server.
                if (smosService != null) {
                    try {
                        smosService.interruptConnection();
                    } catch (Exception e) { // Catch any exception that a real SmosService might throw
                        System.err.println("ERROR: Exception occurred while interrupting SMOS connection: " + e.getMessage());
                        e.printStackTrace(); // Log the full stack trace
                        // Optionally, update the user message with a warning
                        messageLabel.setText(messageLabel.getText() + "\nWarning: SMOS connection interruption failed.");
                        messageLabel.setStyle("-fx-text-fill: orange;"); // Indicate a partial success/warning
                    }
                } else {
                    System.err.println("SMOS Service not initialized, cannot interrupt connection as per postcondition.");
                }
            } else {
                messageLabel.setText("Failed to save note or send notification. Please check logs for details.");
                messageLabel.setStyle("-fx-text-fill: red;"); // Red for error
            }
        } else {
            messageLabel.setText("System error: NoteService not initialized.");
            messageLabel.setStyle("-fx-text-fill: red;");
            System.err.println("NoteService is null in NoteFormController. This should not happen.");
        }
    }
    /**
     * Clears all input fields in the form.
     * This is typically called after a successful save operation.
     */
    private void clearForm() {
        studentNameField.clear();
        datePicker.setValue(LocalDate.now()); // Reset to current date
        teacherNameField.clear();
        parentEmailField.clear();
        descriptionArea.clear();
        messageLabel.setText(""); // Clear message label
        messageLabel.setStyle("-fx-text-fill: black;"); // Reset default color
    }
    /**
     * A simple client-side email format validation.
     *
     * @param email The email string to validate.
     * @return true if the email matches a basic pattern, false otherwise.
     */
    private boolean isValidEmail(String email) {
        // A simple regex for email validation. Handles null input safely.
        return email != null && email.matches("^[a-zA-Z0-9._%+-]+@[a-zA-Z0-9.-]+\\.[a-zA-Z]{2,6}$");
    }
}