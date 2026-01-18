package com.example.repository;

import com.example.model.Student;
import java.util.List;

/**
 * Repository interface for Student entities.
 */
public interface StudentRepository {
    List<Student> findByClassId(String classId);
}