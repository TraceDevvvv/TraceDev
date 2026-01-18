'''
Represents a student's report card for a specific quarter.
Contains grades for all subjects and teacher comments.
'''
package com.chatdev.reportcardsystem.data;
import java.util.ArrayList;
import java.util.List;
public class ReportCard {
    private String studentId;
    private String studentName;
    private String className;
    private String academicYear;
    private String quarter;
    private List<SubjectGrade> grades;
    private String comments;
    public ReportCard(String studentId, String studentName, String className,
                     String academicYear, String quarter) {
        this.studentId = studentId;
        this.studentName = studentName;
        this.className = className;
        this.academicYear = academicYear;
        this.quarter = quarter;
        this.grades = new ArrayList<>();
        this.comments = "";
    }
    public void addGrade(SubjectGrade grade) {
        grades.add(grade);
    }
    public void setComments(String comments) {
        this.comments = comments;
    }
    public double calculateAverage() {
        if (grades.isEmpty()) {
            return 0.0;
        }
        double sum = 0;
        for (SubjectGrade grade : grades) {
            sum += grade.getScore();
        }
        return sum / grades.size();
    }
    public String calculateOverallGrade() {
        double average = calculateAverage();
        if (average >= 90) return "A";
        else if (average >= 80) return "B";
        else if (average >= 70) return "C";
        else if (average >= 60) return "D";
        else return "F";
    }
    public String getStudentId() {
        return studentId;
    }
    public String getStudentName() {
        return studentName;
    }
    public String getClassName() {
        return className;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public String getQuarter() {
        return quarter;
    }
    public List<SubjectGrade> getGrades() {
        return new ArrayList<>(grades);
    }
    public String getComments() {
        return comments;
    }
}