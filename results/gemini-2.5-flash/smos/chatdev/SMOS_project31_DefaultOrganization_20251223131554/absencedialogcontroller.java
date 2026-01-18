/*
 * DOCSTRING: Controller for the Absence Dialog used when adding a new absence.
 * Handles input validation and provides the constructed Absence object.
 */
package controller;
import javafx.fxml.FXML;
import javafx.scene.control.DatePicker;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import model.Absence;
import model.Parent;
import model.Student;
import service.AbsenceService;
import java.time.LocalDate;
public class AbsenceDialogController {
    @FXML
    private TextField studentIdField;
    @FXML
    private DatePicker dateField;
    @FXML
    private TextField reasonField;
    @FXML
    private Label errorMessageLabel;
    private AbsenceService absenceService;
    private Absence resultAbsence; // To hold the validated absence object
    /**
     * Initializes the controller. This method is called automatically after the FXML has been loaded.
     */
    @FXML
    public void initialize() {
        // Clear error message on input change
        studentIdField.textProperty().addListener((obs, oldVal, newVal) -> errorMessageLabel.setText(""));
        dateField.valueProperty().addListener((obs, oldVal, newVal) -> errorMessageLabel.setText(""));
        reasonField.textProperty().addListener((obs, oldVal, newVal) -> errorMessageLabel.setText(""));
    }
    /**
     * Sets the AbsenceService instance for validation purposes.
     * @param absenceService The AbsenceService instance.
     */
    public void setAbsenceService(AbsenceService absenceService) {
        this.absenceService = absenceService;
    }
    /**
     * Sets the initial date in the date picker.
     * @param initialDate The date to set.
     */
    public void setInitialDate(LocalDate initialDate) {
        if (initialDate != null) {
            dateField.setValue(initialDate);
        } else {
            // Default to today if no initial date is provided
            dateField.setValue(LocalDate.now());
        }
    }
    /**
     * Attempts to process the input fields, performing validation.
     * If valid, sets the internal resultAbsence.
     * @return true if input is valid and an Absence object can be constructed, false otherwise.
     */
    public boolean validateAndStoreAbsence() {
        errorMessageLabel.setText(""); // Clear previous errors
        int studentId;
        try {
            studentId = Integer.parseInt(studentIdField.getText().trim());
        } catch (NumberFormatException e) {
            errorMessageLabel.setText("Invalid Student ID. Please enter a number.");
            resultAbsence = null;
            return false;
        }
        LocalDate date = dateField.getValue();
        if (date == null) {
            errorMessageLabel.setText("Please select an absence date.");
            resultAbsence = null;
            return false;
        }
        String reason = reasonField.getText().trim();
        if (reason.isEmpty()) {
            errorMessageLabel.setText("Absence reason cannot be empty.");
            resultAbsence = null;
            return false;
        }
        // Validate student existence
        Student student = absenceService.getStudentById(studentId);
        if (student == null) {
            errorMessageLabel.setText("Student with ID " + studentId + " not found.");
            resultAbsence = null;
            return false;
        }
        // Get parent email for notification
        Parent parent = absenceService.getParentById(student.getParentId());
        String parentEmail = (parent != null) ? parent.getEmail() : "";
        if (parentEmail.isEmpty()) {
            System.err.println("Warning: Parent email not found for student " + student.getName() + " (ID: " + student.getId() + "). Notification may fail.");
            // Still proceed, just log a warning.
        }
        // Construct a new Absence object. Its status will automatically be NEW.
        resultAbsence = new Absence(studentId, student.getName(), date, reason, parentEmail);
        return true;
    }
    /**
     * Returns the validated Absence object if validation was successful,
     * otherwise returns null. This method should be called after validateAndStoreAbsence().
     * @return The constructed Absence object or null.
     */
    public Absence getResultAbsence() {
        return resultAbsence;
    }
}