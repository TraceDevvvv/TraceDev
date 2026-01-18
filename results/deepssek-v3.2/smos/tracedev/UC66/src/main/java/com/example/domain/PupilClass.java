package com.example.domain;

import java.util.Collections;
import java.util.List;
import java.util.Objects;

/**
 * Represents a class (e.g., "Grade 5A") within an academic year.
 */
public class PupilClass {
    private final String classId;
    private final String className;
    private final AcademicYear academicYear;
    private final List<Student> students;

    /**
     * Constructs a PupilClass without initial students.
     * @param classId unique identifier
     * @param className name of the class
     * @param academicYear the academic year this class belongs to
     */
    public PupilClass(String classId, String className, AcademicYear academicYear) {
        this(classId, className, academicYear, Collections.emptyList());
    }

    /**
     * Full constructor.
     */
    public PupilClass(String classId, String className, AcademicYear academicYear, List<Student> students) {
        this.classId = Objects.requireNonNull(classId);
        this.className = Objects.requireNonNull(className);
        this.academicYear = Objects.requireNonNull(academicYear);
        this.students = Objects.requireNonNull(students);
    }

    public String getClassId() {
        return classId;
    }

    public String getClassName() {
        return className;
    }

    public AcademicYear getAcademicYear() {
        return academicYear;
    }

    /**
     * Returns the list of students in this class.
     * @return an unmodifiable list of students
     */
    public List<Student> getStudents() {
        return Collections.unmodifiableList(students);
    }

    @Override
    public String toString() {
        return "PupilClass{" +
                "classId='" + classId + '\'' +
                ", className='" + className + '\'' +
                '}';
    }
}