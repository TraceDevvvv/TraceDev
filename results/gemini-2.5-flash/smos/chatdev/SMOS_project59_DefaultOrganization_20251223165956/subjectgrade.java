/*
 * Represents a single subject and its corresponding grade in a report card.
 */
public class SubjectGrade {
    private String subject;
    private String grade;
    /**
     * Constructs a new SubjectGrade object.
     * @param subject The name of the subject.
     * @param grade The grade obtained in the subject (e.g., "A", "B+", "Pass").
     */
    public SubjectGrade(String subject, String grade) {
        this.subject = subject;
        this.grade = grade;
    }
    /**
     * Gets the subject name.
     * @return The subject name.
     */
    public String getSubject() {
        return subject;
    }
    /**
     * Gets the grade for the subject.
     * @return The grade.
     */
    public String getGrade() {
        return grade;
    }
    /**
     * Returns a string representation of the SubjectGrade object.
     * @return A string combining the subject and its grade.
     */
    @Override
    public String toString() {
        return "  " + subject + ": " + grade;
    }
}