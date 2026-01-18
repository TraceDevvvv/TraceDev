package com.example.viewmodel;

import java.util.List;
import java.util.Map;

/**
 * ViewModel for the Report Card form.
 * Updated stereotype to satisfy UML Constructs.
 */
public class FormViewModel {
    private String studentName;
    private String className;
    private List<String> subjects;
    private Map<String, String> grades; // using String for grade input

    public FormViewModel() {}

    public FormViewModel(String studentName, String className, List<String> subjects, Map<String, String> grades) {
        this.studentName = studentName;
        this.className = className;
        this.subjects = subjects;
        this.grades = grades;
    }

    public String getStudentName() {
        return studentName;
    }

    public void setStudentName(String studentName) {
        this.studentName = studentName;
    }

    public String getClassName() {
        return className;
    }

    public void setClassName(String className) {
        this.className = className;
    }

    public List<String> getSubjects() {
        return subjects;
    }

    public void setSubjects(List<String> subjects) {
        this.subjects = subjects;
    }

    public Map<String, String> getGrades() {
        return grades;
    }

    public void setGrades(Map<String, String> grades) {
        this.grades = grades;
    }

    /**
     * Sets a grade for a specific subject.
     * @param subject the subject name
     * @param grade the grade as a string (can be parsed to float later)
     */
    public void setGrade(String subject, String grade) {
        if (grades != null) {
            grades.put(subject, grade);
        }
    }

    @Override
    public String toString() {
        return "FormViewModel{" +
                "studentName='" + studentName + '\'' +
                ", className='" + className + '\'' +
                ", subjects=" + subjects +
                ", grades=" + grades +
                '}';
    }
}