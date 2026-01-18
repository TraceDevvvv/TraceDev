/**
 * SubjectGrade.java
 * Represents a grade for a specific subject in a report card
 */
public class SubjectGrade {
    private String subjectName;
    private String subjectCode;
    private double grade;
    private String teacher;
    private String comments;
    public SubjectGrade(String subjectName, String subjectCode, double grade,
                         String teacher, String comments) {
        this.subjectName = subjectName;
        this.subjectCode = subjectCode;
        this.grade = grade;
        this.teacher = teacher;
        this.comments = comments;
    }
    public String getSubjectName() {
        return subjectName;
    }
    public String getSubjectCode() {
        return subjectCode;
    }
    public double getGrade() {
        return grade;
    }
    public String getTeacher() {
        return teacher;
    }
    public String getComments() {
        return comments;
    }
    public String getGradeLetter() {
        if (grade >= 90) return "A";
        else if (grade >= 80) return "B";
        else if (grade >= 70) return "C";
        else if (grade >= 60) return "D";
        else return "F";
    }
    @Override
    public String toString() {
        return subjectName + ": " + grade + " (" + getGradeLetter() + ") - " + comments;
    }
}