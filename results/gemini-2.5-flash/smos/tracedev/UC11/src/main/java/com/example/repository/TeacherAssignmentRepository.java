package com.example.repository;

import com.example.model.TeacherAssignment;

import java.util.List;

/**
 * Interface for data access operations related to TeacherAssignment entities.
 */
public interface TeacherAssignmentRepository {
    /**
     * Finds all teacher assignments for a given teacher ID.
     * @param teacherId The ID of the teacher.
     * @return A list of TeacherAssignment objects.
     */
    List<TeacherAssignment> findByTeacherId(String teacherId);

    /**
     * Finds a specific teacher assignment by teacher ID and teaching ID.
     * @param teacherId The ID of the teacher.
     * @param teachingId The ID of the teaching.
     * @return The TeacherAssignment object if found, null otherwise.
     */
    TeacherAssignment findByTeacherAndTeaching(String teacherId, String teachingId);

    /**
     * Saves a teacher assignment. If an assignment with the same ID exists, it updates it;
     * otherwise, it creates a new one.
     * @param assignment The teacher assignment to save.
     */
    void save(TeacherAssignment assignment);

    /**
     * Deletes a teacher assignment.
     * @param assignment The teacher assignment to delete.
     */
    void delete(TeacherAssignment assignment);

    /**
     * Finds an assignment by its unique ID.
     * @param id The ID of the assignment.
     * @return The TeacherAssignment object if found, null otherwise.
     */
    TeacherAssignment findById(String id);
}