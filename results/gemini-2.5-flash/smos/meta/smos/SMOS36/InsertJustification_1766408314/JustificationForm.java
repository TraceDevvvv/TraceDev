import java.time.LocalDate;
import java.time.format.DateTimeParseException;
import java.util.Scanner;
import java.util.Optional;

/**
 * Simulates a UI form for inputting justification details.
 * This class handles user interaction for filling out the justification form
 * and triggering the save operation via the JustificationService.
 */
public class JustificationForm {
    private JustificationService justificationService;
    private Scanner scanner;

    /**
     * Constructs a new JustificationForm.
     *
     * @param justificationService The service responsible for handling justification logic.
     */
    public JustificationForm(JustificationService justificationService) {
        this.justificationService = justificationService;
        this.scanner = new Scanner(System.in);
    }

    /**
     * Displays the justification form to the user and collects input.
     * This method simulates "System: Show a form in which to insert the fields of justice (date justification)."
     * and "User: Fill out the form".
     *
     * @param absenceId The ID of the absence for which the justification is being created.
     *                  This is typically passed from the previous screen where the user clicked on an absence.
     * @return An Optional containing the LocalDate entered by the user if valid, otherwise empty.
     */
    public Optional<LocalDate> showForm(String absenceId) {
        System.out.println("\n--- Justification Form for Absence ID: " + absenceId + " ---");
        System.out.println("Please enter the justification date (YYYY-MM-DD):");

        String dateInput = scanner.nextLine();
        try {
            LocalDate justificationDate = LocalDate.parse(dateInput);
            System.out.println("Date entered: " + justificationDate);
            return Optional.of(justificationDate);
        } catch (DateTimeParseException e) {
            System.err.println("Invalid date format. Please use YYYY-MM-DD. Error: " + e.getMessage());
            return Optional.empty();
        }
    }

    /**
     * Simulates the user clicking the "Save" button on the form.
     * This method attempts to save the justification using the JustificationService.
     *
     * @param absenceId The ID of the absence for which the justification is being created.
     * @param justificationDate The date provided by the user in the form.
     * @return true if the justification was successfully saved, false otherwise.
     */
    public boolean clickSave(String absenceId, LocalDate justificationDate) {
        System.out.println("\nUser: Clicked on 'Save'");
        if (justificationDate == null) {
            System.err.println("Cannot save: Justification date is missing.");
            return false;
        }

        // System: Save the justification.
        Optional<Justification> savedJustification = justificationService.insertJustification(absenceId, justificationDate);

        if (savedJustification.isPresent()) {
            System.out.println("Justification successfully saved: " + savedJustification.get());
            return true;
        } else {
            System.out.println("Justification save failed. Check service logs for details.");
            // This covers "The administrator interrupts the operation" and "Connection to the SMOS server interrupted"
            // as the service returns an empty Optional in those cases.
            return false;
        }
    }

    /**
     * Closes the scanner resource. Should be called when the form is no longer needed.
     */
    public void closeScanner() {
        if (scanner != null) {
            scanner.close();
        }
    }
}