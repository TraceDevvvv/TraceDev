package JustificationViewer_1766393399;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a justification for a student's absence.
 * It includes details such as the reason for the justification,
 * the name of the administrator who provided it, and the date it was made.
 */
public class Justification {
    private final String reason; // The reason provided for the absence
    private final String administratorName; // The name of the administrator who justified the absence
    private final LocalDate justificationDate; // The date when the justification was recorded

    /**
     * Constructs a new Justification object.
     *
     * @param reason The detailed reason for the absence.
     * @param administratorName The name of the administrator who approved the justification.
     * @param justificationDate The date on which the justification was made.
     * @throws IllegalArgumentException if reason, administratorName, or justificationDate is null or empty/invalid.
     */
    public Justification(String reason, String administratorName, LocalDate justificationDate) {
        if (reason == null || reason.trim().isEmpty()) {
            throw new IllegalArgumentException("Justification reason cannot be null or empty.");
        }
        if (administratorName == null || administratorName.trim().isEmpty()) {
            throw new IllegalArgumentException("Administrator name cannot be null or empty.");
        }
        if (justificationDate == null) {
            throw new IllegalArgumentException("Justification date cannot be null.");
        }

        this.reason = reason;
        this.administratorName = administratorName;
        this.justificationDate = justificationDate;
    }

    /**
     * Returns the reason for the absence justification.
     *
     * @return The justification reason.
     */
    public String getReason() {
        return reason;
    }

    /**
     * Returns the name of the administrator who provided the justification.
     *
     * @return The administrator's name.
     */
    public String getAdministratorName() {
        return administratorName;
    }

    /**
     * Returns the date when the justification was recorded.
     *
     * @return The date of justification.
     */
    public LocalDate getJustificationDate() {
        return justificationDate;
    }

    /**
     * Provides a string representation of the Justification object.
     *
     * @return A string containing the reason, administrator name, and justification date.
     */
    @Override
    public String toString() {
        return "Justification [Reason: '" + reason + '\'' +
               ", Administrator: '" + administratorName + '\'' +
               ", Date: " + justificationDate +
               ']';
    }

    /**
     * Compares this Justification object to the specified object. The result is true if and only if
     * the argument is not null and is a Justification object that has the same reason, administratorName,
     * and justificationDate as this object.
     *
     * @param o The object to compare this Justification against.
     * @return true if the given object represents a Justification equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Justification that = (Justification) o;
        return reason.equals(that.reason) &&
               administratorName.equals(that.administratorName) &&
               justificationDate.equals(that.justificationDate);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(reason, administratorName, justificationDate);
    }
}