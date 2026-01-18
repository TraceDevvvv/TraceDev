package com.example.repository.impl;

import com.example.entity.Teacher;
import com.example.repository.TeacherRepository;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of TeacherRepository.
 */
public class TeacherRepositoryImpl implements TeacherRepository {
    private Map<String, Teacher> store = new HashMap<>();

    public TeacherRepositoryImpl() {
        // Pre-populate with some sample data for testing
        store.put("T001", new Teacher("T001", "John Doe"));
        store.put("T002", new Teacher("T002", "Jane Smith"));
    }

    @Override
    public Teacher findById(String id) {
        return store.get(id);
    }

    @Override
    public Teacher save(Teacher teacher) {
        store.put(teacher.getId(), teacher);
        return teacher;
    }
}