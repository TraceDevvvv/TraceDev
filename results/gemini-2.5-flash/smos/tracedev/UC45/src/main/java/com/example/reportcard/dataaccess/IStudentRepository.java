package com.example.reportcard.dataaccess;

import com.example.reportcard.domain.Student;
import java.util.List;

/**
 * Interface for data access operations related to Student.
 */
public interface IStudentRepository {
    /**
     * Finds students belonging to a specific class.
     * @param classId The ID of the class.
     * @return A list of Student objects for the given class.
     */
    List<Student> findByClass(String classId);
}