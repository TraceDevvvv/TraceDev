/**
 * Represents a justification record for an absence.
 * This class encapsulates the data required to justify an absence.
 */
import java.time.LocalDate;
public class Justification {
    private String absenceId; // Links this justification to a specific absence
    private LocalDate justificationDate; // The date the justification is provided/registered
    /**
     * Constructs a new Justification object.
     * @param absenceId The ID of the absence being justified.
     * @param justificationDate The date to be recorded for the justification.
     */
    public Justification(String absenceId, LocalDate justificationDate) {
        this.absenceId = absenceId;
        this.justificationDate = justificationDate;
    }
    /**
     * Returns the ID of the absence associated with this justification.
     * @return The absence ID.
     */
    public String getAbsenceId() {
        return absenceId;
    }
    /**
     * Returns the date recorded for the justification.
     * @return The LocalDate object representing the justification date.
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }
    /**
     * Provides a string representation of the Justification object.
     * @return A formatted string showing justification details.
     */
    @Override
    public String toString() {
        return "Justification [Absence ID=" + absenceId + ", Date=" + justificationDate + "]";
    }
}