package com.example.infrastructure;

import com.example.domain.Student;
import java.util.HashMap;
import java.util.Map;

/**
 * In-memory implementation of IStudentRepository for demonstration purposes.
 * Stores Student objects in a HashMap.
 */
public class InMemoryStudentRepository implements IStudentRepository {
    private final Map<String, Student> students = new HashMap<>();

    @Override
    public void save(Student student) {
        System.out.println("[Repo] Saving Student: " + student.getStudentId());
        students.put(student.getStudentId(), student);
    }

    @Override
    public Student findById(String studentId) {
        System.out.println("[Repo] Finding Student by ID: " + studentId);
        return students.get(studentId);
    }
}