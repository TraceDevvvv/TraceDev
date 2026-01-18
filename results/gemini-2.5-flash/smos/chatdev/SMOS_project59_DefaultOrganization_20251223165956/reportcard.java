import java.util.List;
import java.util.ArrayList;
/*
 * Represents a student's report card for a specific period.
 * Contains a list of subjects and their grades.
 */
public class ReportCard {
    private String reportId;
    private String studentId;
    private String period; // e.g., "Fall 2023 Semester", "Quarter 1"
    private List<SubjectGrade> grades;
    /**
     * Constructs a new ReportCard object.
     * @param reportId A unique identifier for this report card.
     * @param studentId The ID of the student this report card belongs to.
     * @param period The academic period the report card covers.
     * @param grades A list of SubjectGrade objects for this report card.
     */
    public ReportCard(String reportId, String studentId, String period, List<SubjectGrade> grades) {
        this.reportId = reportId;
        this.studentId = studentId;
        this.period = period;
        this.grades = new ArrayList<>(grades); // Defensive copy to ensure internal list is not modified externally
    }
    /**
     * Gets the report card's unique ID.
     * @return The report ID.
     */
    public String getReportId() {
        return reportId;
    }
    /**
     * Gets the student's ID associated with this report card.
     * @return The student ID.
     */
    public String getStudentId() {
        return studentId;
    }
    /**
     * Gets the academic period of the report card.
     * @return The academic period.
     */
    public String getPeriod() {
        return period;
    }
    /**
     * Gets an unmodifiable list of subject grades associated with this report card.
     * A new ArrayList is returned to prevent direct external modification of the internal grades list.
     * @return A list of SubjectGrade objects.
     */
    public List<SubjectGrade> getGrades() {
        return new ArrayList<>(grades); // Return a copy to prevent external modification
    }
    /**
     * Returns a concise string representation of the ReportCard object,
     * primarily for displaying in lists.
     * @return A string indicating the report card's ID and period.
     */
    @Override
    public String toString() {
        return "Report: " + reportId + " - " + period;
    }
    /**
     * Returns a detailed string representation of the ReportCard,
     * including all subjects and grades.
     * @return A detailed string of the report card.
     */
    public String toDetailedString() {
        StringBuilder sb = new StringBuilder();
        sb.append("--- Report Card Details ---\n");
        sb.append("Report ID: ").append(reportId).append("\n");
        sb.append("Student ID: ").append(studentId).append("\n");
        sb.append("Period: ").append(period).append("\n");
        sb.append("Grades:\n");
        if (grades.isEmpty()) {
            sb.append("  No grades available for this period.\n");
        } else {
            for (SubjectGrade sg : grades) {
                sb.append(sg.toString()).append("\n");
            }
        }
        sb.append("---------------------------\n");
        return sb.toString();
    }
}