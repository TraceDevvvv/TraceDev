package gui;
import model.Student;
import java.util.List;
/**
 * Data Transfer Object for carrying report request parameters.
 * This DTO improves type safety and clarity for callbacks.
 */
public class ReportRequest {
    private final String academicYear;
    private final String className;
    private final Student student;
    private final List<String> selectedMonths;
    /**
     * Constructs a new ReportRequest.
     * @param academicYear The selected academic year.
     * @param className The selected class name.
     * @param student The selected student.
     * @param selectedMonths The list of four selected months.
     */
    public ReportRequest(String academicYear, String className, Student student, List<String> selectedMonths) {
        this.academicYear = academicYear;
        this.className = className;
        this.student = student;
        this.selectedMonths = selectedMonths;
    }
    /**
     * Returns the academic year.
     * @return The academic year string.
     */
    public String getAcademicYear() { return academicYear; }
    /**
     * Returns the class name.
     * @return The class name string.
     */
    public String getClassName() { return className; }
    /**
     * Returns the student.
     * @return The Student object.
     */
    public Student getStudent() { return student; }
    /**
     * Returns the list of selected months.
     * @return A list of month names.
     */
    public List<String> getSelectedMonths() { return selectedMonths; }
}