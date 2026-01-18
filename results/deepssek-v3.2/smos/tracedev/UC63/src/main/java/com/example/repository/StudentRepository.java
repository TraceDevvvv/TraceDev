package com.example.repository;

import com.example.model.Student;

import java.util.List;

/**
 * Repository interface for student data access.
 */
public interface StudentRepository {
    List<Student> findAll();
    int countAbsencesByStudent(String studentId);
    int countNotesByStudent(String studentId);
}