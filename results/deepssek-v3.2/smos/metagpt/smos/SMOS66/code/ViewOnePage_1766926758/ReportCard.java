import java.util.HashMap;
import java.util.Map;
import java.util.Objects;

/**
 * Data model class representing a report card for a student in a specific semester.
 * Contains subject grades, average grade, and teacher comments.
 */
public class ReportCard {
    private final Student student;
    private final Semester semester;
    private final Map<String, Double> subjectGrades; // Subject name -> grade (0-20 scale)
    private final double averageGrade;
    private final String teacherComments;

    /**
     * Constructor for ReportCard.
     * @param student The student this report card belongs to.
     * @param semester The semester this report card is for.
     * @param subjectGrades Map of subject names to grades (0-20 scale).
     * @param teacherComments Teacher's comments for the student.
     * @throws NullPointerException if student, semester, or subjectGrades is null.
     * @throws IllegalArgumentException if subjectGrades is empty or contains invalid grades.
     */
    public ReportCard(Student student, Semester semester, Map<String, Double> subjectGrades, String teacherComments) {
        this.student = Objects.requireNonNull(student, "Student cannot be null");
        this.semester = Objects.requireNonNull(semester, "Semester cannot be null");
        this.subjectGrades = new HashMap<>(Objects.requireNonNull(subjectGrades, "Subject grades cannot be null"));
        this.teacherComments = teacherComments != null ? teacherComments : "";
        
        // Validate subject grades
        if (this.subjectGrades.isEmpty()) {
            throw new IllegalArgumentException("Subject grades cannot be empty");
        }
        for (Map.Entry<String, Double> entry : this.subjectGrades.entrySet()) {
            if (entry.getKey() == null || entry.getKey().trim().isEmpty()) {
                throw new IllegalArgumentException("Subject name cannot be null or empty");
            }
            Double grade = entry.getValue();
            if (grade == null || grade < 0.0 || grade > 20.0) {
                throw new IllegalArgumentException(
                    "Grade for subject '" + entry.getKey() + "' must be between 0.0 and 20.0, got: " + grade
                );
            }
        }
        
        // Calculate average grade
        this.averageGrade = calculateAverageGrade();
    }

    /**
     * Calculates the average grade from all subject grades.
     * @return Average grade (0-20 scale).
     */
    private double calculateAverageGrade() {
        double sum = 0.0;
        for (Double grade : subjectGrades.values()) {
            sum += grade;
        }
        return Math.round((sum / subjectGrades.size()) * 100.0) / 100.0; // Round to 2 decimal places
    }

    /**
     * Gets the student this report card belongs to.
     * @return Student object.
     */
    public Student getStudent() {
        return student;
    }

    /**
     * Gets the semester this report card is for.
     * @return Semester object.
     */
    public Semester getSemester() {
        return semester;
    }

    /**
     * Gets the map of subject names to grades.
     * @return Unmodifiable copy of subject grades.
     */
    public Map<String, Double> getSubjectGrades() {
        return Map.copyOf(subjectGrades);
    }

    /**
     * Gets the average grade across all subjects.
     * @return Average grade (0-20 scale).
     */
    public double getAverageGrade() {
        return averageGrade;
    }

    /**
     * Gets the teacher's comments.
     * @return Teacher comments.
     */
    public String getTeacherComments() {
        return teacherComments;
    }

    /**
     * Gets the grade for a specific subject.
     * @param subjectName Name of the subject.
     * @return Grade for the subject, or null if subject not found.
     */
    public Double getGradeForSubject(String subjectName) {
        return subjectGrades.get(subjectName);
    }

    /**
     * Returns a string representation of the report card.
     * @return String representation.
     */
    @Override
    public String toString() {
        return "ReportCard{" +
                "student=" + student.getFullName() +
                ", semester=" + semester.getName() +
                ", averageGrade=" + averageGrade +
                ", subjectCount=" + subjectGrades.size() +
                '}';
    }

    /**
     * Checks equality based on student and semester.
     * @param o Object to compare.
     * @return True if equal, false otherwise.
     */
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return student.equals(that.student) && semester.equals(that.semester);
    }

    /**
     * Hash code based on student and semester.
     * @return Hash code.
     */
    @Override
    public int hashCode() {
        return Objects.hash(student, semester);
    }
}