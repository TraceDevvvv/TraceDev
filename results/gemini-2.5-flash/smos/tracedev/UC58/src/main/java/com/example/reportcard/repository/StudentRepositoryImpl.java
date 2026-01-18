package com.example.reportcard.repository;

import com.example.reportcard.domain.Student;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * Mock implementation of IStudentRepository for demonstration purposes.
 * Simulates an in-memory database.
 */
public class StudentRepositoryImpl implements IStudentRepository {
    private Map<String, Student> students = new HashMap<>();

    public StudentRepositoryImpl() {
        // Seed data
        students.put("S101", new Student("S101", "Alice Smith", "C101"));
        students.put("S102", new Student("S102", "Bob Johnson", "C101"));
        students.put("S103", new Student("S103", "Charlie Brown", "C102"));
        students.put("S104", new Student("S104", "Diana Prince", "C103"));
    }

    @Override
    public List<Student> findStudentsByClassId(String classId) {
        return students.values().stream()
                .filter(s -> s.getClassId().equals(classId))
                .collect(Collectors.toList());
    }

    @Override
    public Student findById(String id) {
        return students.get(id);
    }
}