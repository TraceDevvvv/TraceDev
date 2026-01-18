package com.example.delaysystem.repository;

import com.example.delaysystem.model.ClassSession;
import com.example.delaysystem.model.Student;
import java.util.List;

/**
 * Interface for managing class-related data.
 * Defines the contract for retrieving student and class session information.
 */
public interface IClassRepository {
    /**
     * Retrieves a list of students enrolled in a specific class.
     *
     * @param classId The unique identifier of the class.
     * @return A list of Student objects.
     */
    List<Student> getStudentsForClass(String classId);

    /**
     * Retrieves the details of a specific class session.
     *
     * @param classId The unique identifier of the class.
     * @return A ClassSession object containing class details.
     */
    ClassSession getClassSession(String classId);
}