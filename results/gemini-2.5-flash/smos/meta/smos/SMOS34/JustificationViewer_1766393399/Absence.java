package JustificationViewer_1766393399;

import java.time.LocalDate;
import java.util.Objects;

/**
 * Represents a student's absence on a specific date.
 * It includes the date of the absence and whether it has been justified.
 * If justified, it can optionally hold a reference to the Justification object.
 */
public class Absence {
    private final String studentId; // The ID of the student this absence belongs to
    private final LocalDate absenceDate; // The date of the absence
    private boolean isJustified; // True if the absence is justified, false otherwise
    private Justification justification; // The justification details, if any

    /**
     * Constructs a new Absence object.
     * Initially, an absence is considered unjustified.
     *
     * @param studentId The ID of the student associated with this absence.
     * @param absenceDate The date on which the absence occurred.
     * @throws IllegalArgumentException if studentId is null or empty, or absenceDate is null.
     */
    public Absence(String studentId, LocalDate absenceDate) {
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (absenceDate == null) {
            throw new IllegalArgumentException("Absence date cannot be null.");
        }
        this.studentId = studentId;
        this.absenceDate = absenceDate;
        this.isJustified = false; // By default, an absence is not justified
        this.justification = null; // No justification initially
    }

    /**
     * Returns the ID of the student associated with this absence.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }

    /**
     * Returns the date of the absence.
     *
     * @return The date of the absence.
     */
    public LocalDate getAbsenceDate() {
        return absenceDate;
    }

    /**
     * Checks if the absence is justified.
     *
     * @return true if the absence is justified, false otherwise.
     */
    public boolean isJustified() {
        return isJustified;
    }

    /**
     * Returns the justification details for this absence.
     *
     * @return The Justification object if the absence is justified, otherwise null.
     */
    public Justification getJustification() {
        return justification;
    }

    /**
     * Sets the justification for this absence.
     * If a justification is provided, the absence is marked as justified.
     * If null is provided, the absence is marked as unjustified and any existing justification is removed.
     *
     * @param justification The Justification object to associate with this absence, or null to remove justification.
     */
    public void setJustification(Justification justification) {
        this.justification = justification;
        this.isJustified = (justification != null);
    }

    /**
     * Provides a string representation of the Absence object.
     *
     * @return A string containing the student ID, absence date, and justification status.
     */
    @Override
    public String toString() {
        String status = isJustified ? "Justified" : "Unjustified";
        return "Absence [Student ID: " + studentId + ", Date: " + absenceDate + ", Status: " + status + "]";
    }

    /**
     * Compares this Absence object to the specified object. The result is true if and only if
     * the argument is not null and is an Absence object that has the same studentId and absenceDate
     * as this object. Justification status is not considered for equality.
     *
     * @param o The object to compare this Absence against.
     * @return true if the given object represents an Absence equivalent to this one, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        Absence absence = (Absence) o;
        return studentId.equals(absence.studentId) &&
               absenceDate.equals(absence.absenceDate);
    }

    /**
     * Returns a hash code value for the object. This method is supported for the benefit of
     * hash tables such as those provided by HashMap.
     *
     * @return A hash code value for this object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, absenceDate);
    }
}