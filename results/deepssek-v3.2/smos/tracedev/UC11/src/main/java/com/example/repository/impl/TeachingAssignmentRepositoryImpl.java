package com.example.repository.impl;

import com.example.entity.Teacher;
import com.example.entity.Teaching;
import com.example.entity.TeachingAssignment;
import com.example.repository.TeachingAssignmentRepository;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

/**
 * In-memory implementation of TeachingAssignmentRepository.
 */
public class TeachingAssignmentRepositoryImpl implements TeachingAssignmentRepository {
    private Map<String, TeachingAssignment> store = new HashMap<>();
    private int idCounter = 1;

    public TeachingAssignmentRepositoryImpl() {
        // Pre-populate with a sample assignment
        Teacher teacher = new Teacher("T001", "John Doe");
        Teaching teaching = new Teaching("TE001", "MATH101", "Calculus");
        TeachingAssignment ta = new TeachingAssignment("TA001", teacher, teaching, "2023-2024");
        store.put(ta.getId(), ta);
    }

    @Override
    public List<TeachingAssignment> findByTeacherAndYear(Teacher teacher, String year) {
        List<TeachingAssignment> result = new ArrayList<>();
        for (TeachingAssignment ta : store.values()) {
            if (ta.getTeacher().getId().equals(teacher.getId()) && year.equals(ta.getAcademicYear())) {
                result.add(ta);
            }
        }
        return result;
    }

    @Override
    public TeachingAssignment findById(String id) {
        return store.get(id);
    }

    @Override
    public TeachingAssignment save(TeachingAssignment assignment) {
        if (assignment.getId() == null) {
            assignment.setId("TA" + (++idCounter));
        }
        store.put(assignment.getId(), assignment);
        return assignment;
    }

    @Override
    public void delete(TeachingAssignment assignment) {
        store.remove(assignment.getId());
    }
}