'''
Represents a class in the academic system
Contains class information and teacher assignment
'''
package models;
public class SchoolClass {
    private String classId;
    private String className;
    private String academicYear;
    private String teacherName;
    public SchoolClass() {
    }
    public SchoolClass(String classId, String className, String academicYear, String teacherName) {
        this.classId = classId;
        this.className = className;
        this.academicYear = academicYear;
        this.teacherName = teacherName;
    }
    public String getClassId() {
        return classId;
    }
    public void setClassId(String classId) {
        this.classId = classId;
    }
    public String getClassName() {
        return className;
    }
    public void setClassName(String className) {
        this.className = className;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public void setAcademicYear(String academicYear) {
        this.academicYear = academicYear;
    }
    public String getTeacherName() {
        return teacherName;
    }
    public void setTeacherName(String teacherName) {
        this.teacherName = teacherName;
    }
    @Override
    public String toString() {
        return className + " (" + academicYear + ") - " + teacherName;
    }
}