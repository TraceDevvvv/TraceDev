'''
Represents a student's report card
Contains grades for multiple subjects and teacher comments
'''
package models;
import java.util.HashMap;
import java.util.Map;
public class ReportCard {
    private String reportId;
    private String studentId;
    private String classId;
    private String dateCreated;
    private Map<String, Integer> grades;
    private String comments;
    public ReportCard() {
        this.grades = new HashMap<>();
        this.dateCreated = java.time.LocalDate.now().toString();
        this.reportId = "RPT" + System.currentTimeMillis();
    }
    public String getReportId() {
        return reportId;
    }
    public void setReportId(String reportId) {
        this.reportId = reportId;
    }
    public String getStudentId() {
        return studentId;
    }
    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getDateCreated() {
        return dateCreated;
    }
    public void setDateCreated(String dateCreated) {
        this.dateCreated = dateCreated;
    }
    public Map<String, Integer> getGrades() {
        return grades;
    }
    public void setGrades(Map<String, Integer> grades) {
        this.grades = grades;
    }
    public void setGrade(String subject, int grade) {
        this.grades.put(subject, grade);
    }
    public Integer getGrade(String subject) {
        return this.grades.get(subject);
    }
    public String getComments() {
        return comments;
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        int sum = 0;
        int count = 0;
        for (Integer grade : grades.values()) {
            if (grade != null) {
                sum += grade;
                count++;
            }
        }
        return count > 0 ? (double) sum / count : 0.0;
    }
}