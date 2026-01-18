/**
 * Data model for justification information
 * Contains the justification details to be saved
 */
import java.time.LocalDate;
public class Justification {
    private int absenceId;
    private LocalDate justificationDate;
    private String justificationDetails;
    public Justification(int absenceId, LocalDate justificationDate, String justificationDetails) {
        this.absenceId = absenceId;
        this.justificationDate = justificationDate;
        this.justificationDetails = justificationDetails;
    }
    // Getters
    public int getAbsenceId() { return absenceId; }
    public LocalDate getJustificationDate() { return justificationDate; }
    public String getJustificationDetails() { return justificationDetails; }
    @Override
    public String toString() {
        return "Justification for Absence ID: " + absenceId + 
               " on " + justificationDate + " - Details: " + justificationDetails;
    }
}