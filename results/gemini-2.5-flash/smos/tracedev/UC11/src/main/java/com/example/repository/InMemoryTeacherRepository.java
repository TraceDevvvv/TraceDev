package com.example.repository;

import com.example.model.Teacher;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of TeacherRepository for demonstration purposes.
 * Stores Teacher objects in a HashMap.
 */
public class InMemoryTeacherRepository implements TeacherRepository {
    private final Map<String, Teacher> teachers = new HashMap<>();

    public InMemoryTeacherRepository() {
        // Seed with some initial data
        teachers.put("TeacherX", new Teacher("TeacherX", "John Doe", "EMP001"));
        teachers.put("TeacherY", new Teacher("TeacherY", "Jane Smith", "EMP002"));
    }

    @Override
    public Teacher findById(String id) {
        System.out.println("[TeacherRepo] Searching for Teacher with ID: " + id);
        return teachers.get(id);
    }

    @Override
    public void save(Teacher teacher) {
        System.out.println("[TeacherRepo] Saving Teacher: " + teacher.getName());
        teachers.put(teacher.getId(), teacher);
    }

    @Override
    public List<Teacher> findAll() {
        return new ArrayList<>(teachers.values());
    }
}