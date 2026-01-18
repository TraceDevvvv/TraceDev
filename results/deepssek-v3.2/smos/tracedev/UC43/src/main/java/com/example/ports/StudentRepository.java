package com.example.ports;

import com.example.domain.Student;

public interface StudentRepository {
    Student findById(String studentId);
    String getStudentEmail(String studentId);
}