package com.example.repository;

import com.example.model.Teacher;

import java.util.List;

/**
 * Interface for data access operations related to Teacher entities.
 */
public interface TeacherRepository {
    /**
     * Finds a teacher by their ID.
     * @param id The ID of the teacher.
     * @return The Teacher object if found, null otherwise.
     */
    Teacher findById(String id);

    /**
     * Saves a teacher. If the teacher already exists (based on ID), it might update;
     * otherwise, it creates a new one.
     * @param teacher The teacher to save.
     */
    void save(Teacher teacher);

    /**
     * Finds all teachers.
     * @return A list of all teachers.
     */
    List<Teacher> findAll();
}