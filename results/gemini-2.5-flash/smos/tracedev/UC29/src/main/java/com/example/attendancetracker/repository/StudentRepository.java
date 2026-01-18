package com.example.attendancetracker.repository;

import com.example.attendancetracker.model.Parent;
import com.example.attendancetracker.model.Student;

import java.util.List;

/**
 * Interface for Data Access operations related to Student.
 * <<Repository>> pattern.
 */
public interface StudentRepository {
    /**
     * Finds a student by their ID.
     * @param studentId The ID of the student.
     * @return The Student object, or null if not found.
     */
    Student findById(String studentId);

    /**
     * Finds the parent associated with a student.
     * @param studentId The ID of the student.
     * @return The Parent object, or null if no parent is found or student does not exist.
     */
    Parent findParentByStudentId(String studentId);

    /**
     * Finds all students. This method is added for the `displayAttendanceForm` flow
     * to populate the list of students for the attendance form.
     * @return A list of all registered students.
     */
    List<Student> findAllStudents(); // Added for populating AttendanceModel for form display
}