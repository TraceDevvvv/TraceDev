package com.example.repository;

import com.example.model.ClassRegister;
import com.example.model.Student;

/**
 * Interface for student data repository.
 */
public interface StudentRepository {
    ClassRegister findClassRegisterData(String studentId);
    Student findStudentData(String studentId);
}