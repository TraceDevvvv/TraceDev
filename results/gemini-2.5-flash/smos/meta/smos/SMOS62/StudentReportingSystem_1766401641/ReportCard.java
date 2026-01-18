import java.util.Collections;
import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Represents a student's report card for a specific period.
 * This class stores grades for various subjects and general comments.
 */
public class ReportCard {
    private String studentId;
    private String period; // e.g., "Fall 2023", "Semester 1"
    private Map<String, String> grades; // Subject name -> Grade (e.g., "Math" -> "A", "Science" -> "85%")
    private String comments;

    /**
     * Constructs a new ReportCard object.
     *
     * @param studentId The ID of the student this report card belongs to.
     * @param period The academic period this report card covers (e.g., "Fall 2023").
     * @param comments General comments for the report card. Can be null or empty.
     * @throws IllegalArgumentException if studentId or period is null or empty.
     */
    public ReportCard(String studentId, String period, String comments) {
        // Validate inputs
        if (studentId == null || studentId.trim().isEmpty()) {
            throw new IllegalArgumentException("Student ID cannot be null or empty.");
        }
        if (period == null || period.trim().isEmpty()) {
            throw new IllegalArgumentException("Period cannot be null or empty.");
        }

        this.studentId = studentId;
        this.period = period;
        this.grades = new HashMap<>(); // Initialize an empty map for grades
        this.comments = (comments != null) ? comments.trim() : ""; // Ensure comments are not null
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
     * Returns the academic period this report card covers.
     *
     * @return The report card period.
     */
    public String getPeriod() {
        return period;
    }

    /**
     * Returns an unmodifiable map of subjects to their grades.
     *
     * @return A map where keys are subject names and values are grades.
     */
    public Map<String, String> getGrades() {
        return Collections.unmodifiableMap(grades); // Return an unmodifiable map to prevent external modification
    }

    /**
     * Returns the general comments for this report card.
     *
     * @return The comments.
     */
    public String getComments() {
        return comments;
    }

    /**
     * Adds or updates a grade for a specific subject.
     *
     * @param subject The name of the subject (e.g., "Math", "Science").
     * @param grade The grade received (e.g., "A", "B+", "85%").
     * @throws IllegalArgumentException if subject or grade is null or empty.
     */
    public void addOrUpdateGrade(String subject, String grade) {
        if (subject == null || subject.trim().isEmpty()) {
            throw new IllegalArgumentException("Subject cannot be null or empty.");
        }
        if (grade == null || grade.trim().isEmpty()) {
            throw new IllegalArgumentException("Grade cannot be null or empty.");
        }
        this.grades.put(subject.trim(), grade.trim());
    }

    /**
     * Sets the general comments for the report card.
     *
     * @param comments The new comments. Can be null or empty.
     */
    public void setComments(String comments) {
        this.comments = (comments != null) ? comments.trim() : "";
    }

    /**
     * Overrides the equals method to compare ReportCard objects based on studentId and period.
     *
     * @param o The object to compare with.
     * @return true if the objects are equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(period, that.period);
    }

    /**
     * Overrides the hashCode method consistent with the equals method.
     *
     * @return The hash code for this ReportCard object.
     */
    @Override
    public int hashCode() {
        return Objects.hash(studentId, period);
    }

    /**
     * Returns a string representation of the ReportCard object, including student ID, period,
     * grades, and comments.
     *
     * @return A formatted string detailing the report card.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card ---\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Period: ").append(period).append("\n");
        sb.append("Grades:\n");
        if (grades.isEmpty()) {
            sb.append("  No grades recorded.\n");
        } else {
            grades.forEach((subject, grade) ->
                sb.append("  ").append(subject).append(": ").append(grade).append("\n")
            );
        }
        sb.append("Comments: ").append(comments.isEmpty() ? "N/A" : comments).append("\n");
        sb.append("-------------------");
        return sb.toString();
    }
}