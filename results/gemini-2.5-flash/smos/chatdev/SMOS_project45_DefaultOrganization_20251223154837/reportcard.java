/*
 * Represents a student's report card for a specific academic period and selected months.
 * Contains student details, academic year, selected months, and a mapping of grades by month.
 */
package model;
import java.util.List;
import java.util.Map;
public class ReportCard {
    private Student student;
    private String academicYear;
    private List<String> months; // The four specific months selected for the report
    private Map<String, List<CourseGrade>> gradesByMonth; // Map: Month Name -> List of CourseGrade
    /**
     * Constructs a new ReportCard.
     * @param student The student for whom the report card is generated.
     * @param academicYear The academic year the report pertains to.
     * @param months The list of four months selected for this report.
     * @param gradesByMonth A map containing course grades for each selected month.
     */
    public ReportCard(Student student, String academicYear, List<String> months, Map<String, List<CourseGrade>> gradesByMonth) {
        this.student = student;
        this.academicYear = academicYear;
        this.months = months;
        this.gradesByMonth = gradesByMonth;
    }
    /**
     * Returns the student associated with this report card.
     * @return The Student object.
     */
    public Student getStudent() {
        return student;
    }
    /**
     * Returns the academic year of the report card.
     * @return The academic year string (e.g., "2023-2024").
     */
    public String getAcademicYear() {
        return academicYear;
    }
    /**
     * Returns the list of months included in this report card.
     * @return A list of month names.
     */
    public List<String> getMonths() {
        return months;
    }
    /**
     * Returns the map of grades, where each key is a month and the value is a list of CourseGrade objects for that month.
     * @return A map from month names to a list of CourseGrade objects.
     */
    public Map<String, List<CourseGrade>> getGradesByMonth() {
        return gradesByMonth;
    }
}