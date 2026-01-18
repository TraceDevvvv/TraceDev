package com.example.attendancetracker.repository;

import com.example.attendancetracker.model.Parent;
import com.example.attendancetracker.model.Student;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

/**
 * In-memory implementation of StudentRepository for demonstration.
 * Simulates database operations.
 */
public class StudentRepositoryImpl implements StudentRepository {
    // Mimics a database table for Students and Parents
    private final Map<String, Student> students = new HashMap<>();
    private final Map<String, Parent> parents = new HashMap<>();

    public StudentRepositoryImpl() {
        // Pre-populate with mock data (as per prerequisite use case "SeveralTetTingloregister")
        Parent parent1 = new Parent("P001", "Alice Smith", "alice.smith@example.com");
        Parent parent2 = new Parent("P002", "Bob Johnson", "bob.johnson@example.com");
        Parent parent3 = new Parent("P003", "Charlie Davis", "charlie.davis@example.com");

        parents.put(parent1.getParentId(), parent1);
        parents.put(parent2.getParentId(), parent2);
        parents.put(parent3.getParentId(), parent3);

        Student student1 = new Student("S001", "Emma Smith", parent1.getParentId());
        Student student2 = new Student("S002", "Liam Johnson", parent2.getParentId());
        Student student3 = new Student("S003", "Olivia Davis", parent3.getParentId());
        Student student4 = new Student("S004", "Noah Smith", parent1.getParentId()); // Another child for Alice

        students.put(student1.getStudentId(), student1);
        students.put(student2.getStudentId(), student2);
        students.put(student3.getStudentId(), student3);
        students.put(student4.getStudentId(), student4);
    }

    @Override
    public Student findById(String studentId) {
        System.out.println("[Database] SELECT * FROM Students WHERE studentId = " + studentId);
        return students.get(studentId);
    }

    @Override
    public Parent findParentByStudentId(String studentId) {
        System.out.println("[Database] SELECT parentId, email FROM Students JOIN Parents ON Students.parentId = Parents.parentId WHERE studentId = " + studentId);
        Student student = students.get(studentId);
        if (student != null && student.getParentId() != null) {
            return parents.get(student.getParentId());
        }
        return null;
    }

    @Override
    public List<Student> findAllStudents() {
        System.out.println("[Database] SELECT * FROM Students");
        return new ArrayList<>(students.values());
    }
}