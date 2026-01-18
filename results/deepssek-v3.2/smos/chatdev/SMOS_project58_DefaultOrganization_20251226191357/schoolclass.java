'''
Represents a school class with students and subjects.
'''
package com.chatdev.reportcardsystem.model;
import java.util.ArrayList;
import java.util.List;
public class SchoolClass {
    private String className;
    private String academicYear;
    private List<Student> students;
    private List<String> subjects;
    public SchoolClass(String className, String academicYear) {
        this.className = className;
        this.academicYear = academicYear;
        this.students = new ArrayList<>();
        this.subjects = new ArrayList<>();
    }
    public void addStudent(Student student) {
        students.add(student);
    }
    public void addSubject(String subject) {
        subjects.add(subject);
    }
    public String getClassName() {
        return className;
    }
    public String getAcademicYear() {
        return academicYear;
    }
    public List<Student> getStudents() {
        return new ArrayList<>(students);
    }
    public List<String> getSubjects() {
        return new ArrayList<>(subjects);
    }
    @Override
    public String toString() {
        return className;
    }
}