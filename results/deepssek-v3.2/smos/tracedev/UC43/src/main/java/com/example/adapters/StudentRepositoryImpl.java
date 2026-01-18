package com.example.adapters;

import com.example.domain.Student;
import com.example.ports.StudentRepository;
import java.util.HashMap;
import java.util.Map;

public class StudentRepositoryImpl implements StudentRepository {
    private Map<String, Student> studentStore = new HashMap<>();

    public StudentRepositoryImpl() {
        // Initialize with some sample data
        studentStore.put("student1", new Student("student1", "parent1@example.com"));
        studentStore.put("student2", new Student("student2", "parent2@example.com"));
    }

    @Override
    public Student findById(String studentId) {
        return studentStore.get(studentId);
    }

    @Override
    public String getStudentEmail(String studentId) {
        Student student = studentStore.get(studentId);
        if (student != null) {
            return student.getParentEmail();
        }
        return null;
    }
}