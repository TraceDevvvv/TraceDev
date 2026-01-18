package com.example.repository;

import com.example.entity.StudentEntity;
import java.util.List;

/**
 * Repository interface for Student entities.
 */
public interface StudentRepository {
    /**
     * Finds all students belonging to a specific class.
     * @param classId the class id
     * @return list of StudentEntity objects
     */
    List<StudentEntity> findAllByClassId(int classId);

    /**
     * Finds a student by its id.
     * @param id the student id
     * @return the StudentEntity or null if not found
     */
    StudentEntity findById(int id);
}