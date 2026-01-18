package com.school.infrastructure;

import com.school.domain.Teacher;

/**
 * Repository interface for teachers.
 */
public interface TeacherRepository {
    Teacher findById(String id);
}