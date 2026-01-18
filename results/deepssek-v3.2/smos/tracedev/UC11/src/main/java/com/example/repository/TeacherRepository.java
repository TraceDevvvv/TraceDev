package com.example.repository;

import com.example.entity.Teacher;

/**
 * Repository interface for Teacher entities.
 */
public interface TeacherRepository {
    Teacher findById(String id);
    Teacher save(Teacher teacher);
}