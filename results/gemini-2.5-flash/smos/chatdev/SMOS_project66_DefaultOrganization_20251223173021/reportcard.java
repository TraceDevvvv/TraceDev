/**
 * Represents a student's report card for a specific semester (quadrimestre) and academic year.
 * Contains grades for various subjects and general comments.
 */
package models;
import java.util.Map;
import java.util.Objects;
import java.util.StringJoiner;
public class ReportCard {
    private String studentId;
    private String academicYearId;
    private String quadrimestre;
    private Map<String, String> grades; // Subject -> Grade
    private String comments;
    public ReportCard(String studentId, String academicYearId, String quadrimestre, Map<String, String> grades, String comments) {
        this.studentId = studentId;
        this.academicYearId = academicYearId;
        this.quadrimestre = quadrimestre;
        this.grades = grades;
        this.comments = comments;
    }
    public String getStudentId() {
        return studentId;
    }
    public String getAcademicYearId() {
        return academicYearId;
    }
    public String getQuadrimestre() {
        return quadrimestre;
    }
    public Map<String, String> getGrades() {
        return grades;
    }
    public String getComments() {
        return comments;
    }
    /**
     * Formats the report card data into a human-readable string.
     * This method is used to display the report in the GUI.
     *
     * @return A formatted string representation of the report card.
     */
    public String formatReportCard() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card ---\n");
        sb.append("Quadrimestre: ").append(quadrimestre).append("\n");
        sb.append("-------------------\n");
        sb.append("Grades:\n");
        if (grades != null && !grades.isEmpty()) {
            grades.forEach((subject, grade) -> sb.append(String.format("  - %s: %s\n", subject, grade)));
        } else {
            sb.append("  No grades available.\n");
        }
        sb.append("-------------------\n");
        sb.append("Comments: ").append(comments != null && !comments.isEmpty() ? comments : "No comments.").append("\n");
        sb.append("-------------------\n");
        return sb.toString();
    }
    @Override
    public boolean equals(Object o) {
        if (this == o) return true;
        if (o == null || getClass() != o.getClass()) return false;
        ReportCard that = (ReportCard) o;
        return Objects.equals(studentId, that.studentId) &&
               Objects.equals(academicYearId, that.academicYearId) &&
               Objects.equals(quadrimestre, that.quadrimestre);
    }
    @Override
    public int hashCode() {
        return Objects.hash(studentId, academicYearId, quadrimestre);
    }
}