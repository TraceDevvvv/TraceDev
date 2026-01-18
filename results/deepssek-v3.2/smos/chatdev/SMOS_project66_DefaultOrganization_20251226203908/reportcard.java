import java.util.List;
import java.util.ArrayList;
/**
 * ReportCard.java
 * Represents a student's report card for a specific quadrimestre
 */
public class ReportCard {
    private String studentId;
    private String studentName;
    private String academicYear;
    private String quadrimestre;
    private List<SubjectGrade> subjectGrades;
    private String teacherComments;
    private String principalComments;
    public ReportCard(String studentId, String studentName, String academicYear, String quadrimestre) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.academicYear = academicYear;
        this.quadrimestre = quadrimestre;
        this.subjectGrades = new ArrayList<>();
        this.teacherComments = "";
        this.principalComments = "";
    }
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public String getQuadrimestre() {
        return quadrimestre;
    }
    public List<SubjectGrade> getSubjectGrades() {
        return new ArrayList<>(subjectGrades);
    }
    public String getTeacherComments() {
        return teacherComments;
    }
    public void setTeacherComments(String teacherComments) {
        this.teacherComments = teacherComments;
    }
    public String getPrincipalComments() {
        return principalComments;
    }
    public void setPrincipalComments(String principalComments) {
        this.principalComments = principalComments;
    }
    public void addSubjectGrade(SubjectGrade subjectGrade) {
        subjectGrades.add(subjectGrade);
    }
    public double calculateAverageGrade() {
        if (subjectGrades.isEmpty()) {
            return 0.0;
        }
        double sum = 0.0;
        for (SubjectGrade grade : subjectGrades) {
            sum += grade.getGrade();
        }
        return sum / subjectGrades.size();
    }
    public String getAverageGradeLetter() {
        double average = calculateAverageGrade();
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
}