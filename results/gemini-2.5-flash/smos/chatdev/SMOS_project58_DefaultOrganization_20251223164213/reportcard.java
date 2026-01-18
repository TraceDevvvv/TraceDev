import java.util.Map;
/*
 * Represents a student's report card for a specific quadrimestre.
 * Contains academic details, grades, and comments.
 */
class ReportCard {
    private String studentId;
    private String studentName;
    private String academicYear;
    private String className;
    private String quadrimestre;
    private Map<String, String> grades; // Subject -> Grade
    private String comments;
    /**
     * Constructs a new ReportCard object.
     *
     * @param studentId The ID of the student the report card belongs to.
     * @param studentName The name of the student.
     * @param academicYear The academic year of the report card.
     * @param className The name of the class for the report card.
     * @param quadrimestre The specific quadrimestre (e.g., "1st Quadrimestre", "2nd Quadrimestre").
     * @param grades A map of subjects to their respective grades.
     * @param comments Any general comments on the report card.
     */
    public ReportCard(String studentId, String studentName, String academicYear, String className,
                      String quadrimestre, Map<String, String> grades, String comments) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicYear = academicYear;
        this.className = className;
        this.quadrimestre = quadrimestre;
        this.grades = grades;
        this.comments = comments;
    }
    /**
     * Provides a formatted string representation of the report card.
     * This method renders the report card details in a human-readable format,
     * including student info, academic period, grades, and comments.
     *
     * @return A formatted string containing all report card details.
     */
    @Override
    public String toString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Student Report Card ---\n");
        sb.append(String.format("Student: %s (ID: %s)\n", studentName, studentId));
        sb.append(String.format("Academic Year: %s\n", academicYear));
        sb.append(String.format("Class: %s\n", className));
        sb.append(String.format("Quadrimestre: %s\n", quadrimestre));
        sb.append("\nGrades:\n");
        if (grades != null && !grades.isEmpty()) {
            grades.forEach((subject, grade) -> sb.append(String.format("  - %s: %s\n", subject, grade)));
        } else {
            sb.append("  No grades available.\n");
        }
        sb.append("\nComments:\n");
        sb.append(String.format("  %s\n", comments != null && !comments.isEmpty() ? comments : "No comments."));
        sb.append("---------------------------\n");
        return sb.toString();
    }
}