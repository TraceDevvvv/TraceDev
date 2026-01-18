package com.example.repository;

import com.example.entity.Teacher;
import com.example.entity.TeachingAssignment;
import java.util.List;

/**
 * Repository interface for TeachingAssignment entities.
 */
public interface TeachingAssignmentRepository {
    List<TeachingAssignment> findByTeacherAndYear(Teacher teacher, String year);
    TeachingAssignment findById(String id);
    TeachingAssignment save(TeachingAssignment assignment);
    void delete(TeachingAssignment assignment);
}