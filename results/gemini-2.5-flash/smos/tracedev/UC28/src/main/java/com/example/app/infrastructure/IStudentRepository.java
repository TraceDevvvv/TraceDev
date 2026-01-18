package com.example.app.infrastructure;

import com.example.app.Infrastructure;
import com.example.app.domain.Student;
import java.util.List;

/**
 * Interface for repository operations related to Student.
 */
public interface IStudentRepository extends Infrastructure {

    List<Student> findByRegisterId(String registerId);
}