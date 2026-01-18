package com.example;

/**
 * Data Transfer Object for student monitoring data.
 */
public class StudentMonitoringDto {
    private String studentId;
    private String studentName;
    private int absenceCount;
    private int noteCount;
    private String enrollmentYear;

    public String getStudentId() {
        return studentId;
    }

    public void setStudentId(String studentId) {
        this.studentId = studentId;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public int getAbsenceCount() {
        return absenceCount;
    }

    public void setAbsenceCount(int absenceCount) {
        this.absenceCount = absenceCount;
    }

    public int getNoteCount() {
        return noteCount;
    }

    public void setNoteCount(int noteCount) {
        this.noteCount = noteCount;
    }

    public String getEnrollmentYear() {
        return enrollmentYear;
    }

    public void setEnrollmentYear(String enrollmentYear) {
        this.enrollmentYear = enrollmentYear;
    }

    @Override
    public String toString() {
        return "StudentMonitoringDto{" +
                "studentId='" + studentId + '\'' +
                ", studentName='" + studentName + '\'' +
                ", absenceCount=" + absenceCount +
                ", noteCount=" + noteCount +
                ", enrollmentYear='" + enrollmentYear + '\'' +
                '}';
    }
}