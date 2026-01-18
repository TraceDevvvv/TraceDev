package com.example.repositories;

import com.example.entities.Student;
import java.util.List;

/**
 * Interface for Student repository operations.
 * Corresponds to IStudentRepository in the class diagram.
 */
public interface IStudentRepository {
    List<Student> findByIds(List<Integer> ids);
}