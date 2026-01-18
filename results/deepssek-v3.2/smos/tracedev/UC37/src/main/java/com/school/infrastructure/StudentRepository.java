package com.school.infrastructure;

import com.school.domain.Student;

/**
 * Repository interface for students.
 */
public interface StudentRepository {
    Student findById(String id);
}