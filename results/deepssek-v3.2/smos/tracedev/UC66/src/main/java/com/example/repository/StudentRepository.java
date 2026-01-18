package com.example.repository;

import com.example.domain.Student;

import java.util.List;

public interface StudentRepository {
    List<Student> findByClass(String classId);
    Student findById(String studentId);
}