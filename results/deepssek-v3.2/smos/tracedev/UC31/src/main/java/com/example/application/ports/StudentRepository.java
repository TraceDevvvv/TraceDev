package com.example.application.ports;

import com.example.domain.Student;

/**
 * Repository interface for Student aggregate.
 */
public interface StudentRepository {
    Student findById(String id);
}