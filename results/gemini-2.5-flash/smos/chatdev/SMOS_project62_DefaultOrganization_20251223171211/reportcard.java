'''
ReportCard.java
Represents a student's report card for a specific period, including grades for various subjects.
'''
import java.util.Collections;
import java.util.Map;
import java.util.Objects;
/**
 * ReportCard.java
 * Represents a student's report card for a specific period, including grades for various subjects.
 */
public class ReportCard {
    private String studentId;
    private String date; // E.g., "Fall 2023", "Q1 2024"
    private Map<String, String> grades; // Subject -> Grade (e.g., "Math" -> "A-")
    private final boolean isPlaceholder; // NEW: Indicates if this is a placeholder object
    /**
     * Constructs a new ReportCard object.
     *
     * @param studentId The ID of the student this report card belongs to.
     * @param date The period or date of the report card (e.g., "Fall 2023").
     * @param grades A map where keys are subjects and values are grades.
     */
    public ReportCard(String studentId, String date, Map<String, String> grades) {
        this(studentId, date, grades, false); // Call the new internal constructor
    }
    /**
     * Internal constructor allowing to set the placeholder flag.
     * @param studentId The ID of the student this report card belongs to.
     * @param date The period or date of the report card or a placeholder message.
     * @param grades A map where keys are subjects and values are grades.
     * @param isPlaceholder True if this report card is a placeholder, false otherwise.
     */
    public ReportCard(String studentId, String date, Map<String, String> grades, boolean isPlaceholder) {
        this.studentId = studentId;
        this.date = date;
        // Create an unmodifiable copy to ensure immutability from outside.
        // For placeholders, grades can be empty, so no explicit check here beyond null-safety.
        this.grades = Collections.unmodifiableMap(Objects.requireNonNull(grades, "Grades map cannot be null"));
        this.isPlaceholder = isPlaceholder;
    }
    /**
     * Returns the ID of the student associated with this report card.
     *
     * @return The student's ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Returns the date or period of the report card.
     *
     * @return The report card's date/period.
     */
    public String getDate() {
        return date;
    }
    /**
     * Returns an unmodifiable map of subjects to grades.
     *
     * @return An unmodifiable map containing the grades.
     */
    public Map<String, String> getGrades() {
        return grades;
    }
    /**
     * Returns true if this ReportCard object is a placeholder for informational messages.
     * @return True if placeholder, false otherwise.
     */
    public boolean isPlaceholder() {
        return isPlaceholder;
    }
    /**
     * Provides a string representation of the ReportCard object, primarily for display in JList components.
     *
     * @return The report card's date or placeholder message.
     */
    @Override
    public String toString() {
        if (isPlaceholder) {
            return date; // For placeholder messages, the 'date' field holds the message.
        }
        return "Report Card: " + date;
    }
}