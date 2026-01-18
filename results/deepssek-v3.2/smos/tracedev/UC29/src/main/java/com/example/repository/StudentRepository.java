package com.example.repository;

import com.example.entity.Student;
import java.util.List;
import java.util.ArrayList;

/**
 * Repository for student entities.
 */
public class StudentRepository {
    // Simulating in‑memory storage.
    private List<Student> students = new ArrayList<>();

    public StudentRepository() {
        // Populate with some dummy data for demonstration.
        students.add(new Student("S001", "Alice Johnson", "Class A", null));
        students.add(new Student("S002", "Bob Smith", "Class A", null));
        students.add(new Student("S003", "Charlie Brown", "Class B", null));
    }

    public List<Student> findAll() {
        return new ArrayList<>(students);
    }

    public Student findById(String studentId) {
        for (Student s : students) {
            if (s.getStudentId().equals(studentId)) {
                return s;
            }
        }
        return null;
    }

    public List<Student> findByClass(String classCode) {
        List<Student> result = new ArrayList<>();
        for (Student s : students) {
            if (s.getClassName().equals(classCode)) {
                result.add(s);
            }
        }
        return result;
    }
}